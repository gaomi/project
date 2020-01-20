package com.company.project.module.home.util.poilcy;


import com.alibaba.fastjson.JSONObject;
import com.company.project.module.home.util.model.MergeEnum;
import com.company.project.module.home.util.model.TableMerge;
import com.deepoove.poi.NiceXWPFDocument;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.CellRenderData;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.TableStyle;
import com.deepoove.poi.policy.AbstractRenderPolicy;
import com.deepoove.poi.policy.TextRenderPolicy;
import com.deepoove.poi.render.RenderContext;
import com.deepoove.poi.template.run.RunTemplate;
import com.deepoove.poi.util.ObjectUtils;
import com.deepoove.poi.util.StyleUtils;
import com.deepoove.poi.util.TableTools;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyRenderPolicy extends AbstractRenderPolicy<MiniTableRenderData> {

    @Override
    protected boolean validate(MiniTableRenderData data) {
        if (!(data).isSetBody() && !(data).isSetHeader()) {
            logger.debug("Empty MiniTableRenderData datamodel: {}", data);
            return false;
        }
        return true;
    }

    @Override
    public void doRender(RunTemplate runTemplate, MiniTableRenderData data, XWPFTemplate template)
            throws Exception {
        NiceXWPFDocument doc = template.getXWPFDocument();
        XWPFRun run = runTemplate.getRun();

        if (!data.isSetBody()) {
            renderNoDataTable(doc, run, data);
        } else {
            renderTable(doc, run, data);
        }
    }

    @Override
    protected void afterRender(RenderContext context) {
        clearPlaceholder(context, true);
    }

    private void renderTable(NiceXWPFDocument doc, XWPFRun run, MiniTableRenderData tableData) {
        // 1.计算行和列
        int row = tableData.getDatas().size(), col = 0;
        if (!tableData.isSetHeader()) {
            col = getMaxColumFromData(tableData.getDatas());
        } else {
            row++;
            col = tableData.getHeader().size();
        }

        TableMerge tableMerge = JSONObject.parseObject(tableData.getNoDatadesc(), TableMerge.class);
        if (tableMerge != null && tableMerge.getMergeType().equals(MergeEnum.HORIZONAL.getCode()) && tableMerge != null) {
            row++;
        }

        // 2.创建表格
        XWPFTable table = doc.insertNewTable(run, row, col);
        initBasicTable(table, col, tableData.getWidth(), tableData.getStyle());
        // 3.渲染数据
        int startRow = 0;
        if (tableData.isSetHeader()) {
            Helper.renderRow(table, startRow++, tableData.getHeader());
            if (tableMerge != null && tableMerge.getMergeType().equals(MergeEnum.HORIZONAL.getCode())) {
                //添加多表頭
                if (tableMerge.getHeader() != null) {
                    Helper.renderRow(table, startRow++, tableMerge.getHeader());
                }
                //设置第一行合并行
                Map<String, Integer> mergeCellsMap = new HashMap();
                mergeCellsMap.put("count", 0);
                List<CellRenderData> cellDatas = tableData.getHeader().getCellDatas();
                for (int i = cellDatas.size() - 1; i >= 0; i--) {
                    if (i < cellDatas.size() - 1) {
                        if (cellDatas.get(i + 1).getRenderData().getText().equals(cellDatas.get(i).getRenderData().getText())) {
                            mergeCellsMap.put("count", mergeCellsMap.get("count") + 1);
                            if (i == 0 && mergeCellsMap.get("count") > 0) {
                                TableTools.mergeCellsHorizonal(table, 0, i, i + mergeCellsMap.get("count"));
                            }
                        } else {
                            if (mergeCellsMap.get("count") > 0) {
                                TableTools.mergeCellsHorizonal(table, 0, i + 1, i + mergeCellsMap.get("count") + 1);
                                mergeCellsMap.put("count", 0);
                            }
                        }
                    }
                }
                if (tableMerge.getHeader() != null && tableMerge.getItemMerge() != null && tableMerge.getItemMerge().getMergeType().equals(MergeEnum.VERTICALLY.getCode())) {
                    Map<Integer, Integer> mergeCells = new HashMap();
                    for (Integer row_item : tableMerge.getItemMerge().getMergeCol()) {
                        mergeCells.put(row_item, 0);
                    }
//                    程序自定义头与添加头合并列（获取已生成的头文件）
                    List<RowRenderData> heardRow = Arrays.asList(tableData.getHeader(), tableMerge.getHeader());
                    List<Integer> colNumber = tableMerge.getItemMerge().getMergeCol();
                    RowRenderData data = heardRow.get(1);
                    RowRenderData after = heardRow.get(0);
                    for (int j : colNumber) {
                        if (data.getCellDatas().get(j).getRenderData().getText().equals(after.getCellDatas().get(j).getRenderData().getText())) {
                            TableTools.mergeCellsVertically(table, j, 0, 1);
                        }
                    }
                }

            }
        }
        List<RowRenderData> listRow = tableData.getDatas();
        // 定义表格宽度、边框和样式
        TableTools.widthTable(table, MiniTableRenderData.WIDTH_A4_FULL, col);
        //4.合并表格
        if (listRow.size() > 0) {
            if (tableMerge == null || tableMerge.getMergeType().equals(MergeEnum.HORIZONAL.getCode())) {
                for (int i = 0; i < listRow.size(); i++) {
                    RowRenderData data = listRow.get(i);
                    Helper.renderRow(table, startRow++, data);
                }
            } else if (tableMerge.getMergeType().equals(MergeEnum.VERTICALLY.getCode())) {
                Map<Integer, Integer> mergeCellsMap = new HashMap();
                for (Integer row_item : tableMerge.getMergeCol()) {
                    mergeCellsMap.put(row_item, 0);
                }
                for (int i = 0; i < listRow.size(); i++) {
                    RowRenderData data = listRow.get(i);
                    Helper.renderRow(table, startRow++, data);
                    if (i > 0) {
                        RowRenderData after = listRow.get(i - 1);
                        for (Integer key : mergeCellsMap.keySet()) {
                            if (data.getCellDatas().get(key).getRenderData().getText().equals(after.getCellDatas().get(key).getRenderData().getText())) {
                                mergeCellsMap.put(key, mergeCellsMap.get(key) + 1);
                                if (i == listRow.size() - 1 && mergeCellsMap.get(key) > 0) {
                                    TableTools.mergeCellsVertically(table, key, i - mergeCellsMap.get(key) + 1, i + 1);
                                }
                            } else {
                                if (mergeCellsMap.get(key) > 0) {
                                    TableTools.mergeCellsVertically(table, key, i - mergeCellsMap.get(key), i);
                                    mergeCellsMap.put(key, 0);
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    private void renderNoDataTable(NiceXWPFDocument doc, XWPFRun run,
                                   MiniTableRenderData tableData) {
        int row = 2, col = tableData.getHeader().size();

        XWPFTable table = doc.insertNewTable(run, row, col);
        initBasicTable(table, col, tableData.getWidth(), tableData.getStyle());

        Helper.renderRow(table, 0, tableData.getHeader());

        TableTools.mergeCellsHorizonal(table, 1, 0, tableData.getHeader().size() - 1);
        XWPFTableCell cell = table.getRow(1).getCell(0);
        cell.setText(tableData.getNoDatadesc());

    }

    private void initBasicTable(XWPFTable table, int col, float width, TableStyle style) {
        TableTools.widthTable(table, width, col);
        TableTools.borderTable(table, 4);
        StyleUtils.styleTable(table, style);
    }

    private int getMaxColumFromData(List<RowRenderData> datas) {
        int maxColom = 0;
        for (RowRenderData obj : datas) {
            if (null == obj) continue;
            if (obj.size() > maxColom) maxColom = obj.size();
        }
        return maxColom;
    }

    public static class Helper {

        /**
         * 填充表格一行的数据
         *
         * @param table
         * @param row     第几行
         * @param rowData 行数据：确保行数据的大小不超过表格该行的单元格数量
         */
        public static void renderRow(XWPFTable table, int row, RowRenderData rowData) {
            if (null == rowData || rowData.size() <= 0) return;
            XWPFTableRow tableRow = table.getRow(row);
            ObjectUtils.requireNonNull(tableRow, "Row " + row + " do not exist in the table");
            TableStyle rowStyle = rowData.getRowStyle();
            List<CellRenderData> cellList = rowData.getCellDatas();
            XWPFTableCell cell = null;

            for (int i = 0; i < cellList.size(); i++) {
                cell = tableRow.getCell(i);
                if (null == cell) {
                    logger.warn("Extra cell data at row {}, but no extra cell: col {}", row, cell);
                    break;
                }
                renderCell(cell, cellList.get(i), rowStyle);
            }
        }

        public static void renderCell(XWPFTableCell cell, CellRenderData cellData,
                                      TableStyle rowStyle) {
            TableStyle cellStyle = (null == cellData.getCellStyle() ? rowStyle
                    : cellData.getCellStyle());

            // 处理单元格样式
            if (null != cellStyle && null != cellStyle.getBackgroundColor()) {
                cell.setColor(cellStyle.getBackgroundColor());
            }

            TextRenderData renderData = cellData.getRenderData();
            String cellText = renderData.getText();
            if (StringUtils.isBlank(cellText)) return;

            // 处理单元格数据
            CTTc ctTc = cell.getCTTc();
            CTP ctP = (ctTc.sizeOfPArray() == 0) ? ctTc.addNewP() : ctTc.getPArray(0);
            XWPFParagraph par = new XWPFParagraph(ctP, cell);
            StyleUtils.styleTableParagraph(par, cellStyle);
            cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER); //垂直居中
            String text = renderData.getText();
            String[] fragment = text.split(TextRenderPolicy.REGEX_LINE_CHARACTOR, -1);

            if (fragment.length <= 1) {
                TextRenderPolicy.Helper.renderTextRun(par.createRun(), renderData);
            } else {
                // 单元格内换行的用不同段落来特殊处理
                XWPFRun run;
                for (int j = 0; j < fragment.length; j++) {
                    if (0 != j) {
                        par = cell.addParagraph();
                        StyleUtils.styleTableParagraph(par, cellStyle);
                    }
                    run = par.createRun();
                    StyleUtils.styleRun(run, renderData.getStyle());
                    run.setText(fragment[j]);
                }
            }
        }
    }
}