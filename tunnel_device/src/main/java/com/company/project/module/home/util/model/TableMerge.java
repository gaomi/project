package com.company.project.module.home.util.model;

import com.deepoove.poi.data.RowRenderData;

import java.util.List;

public class TableMerge {

    private String mergeType;

    private RowRenderData header;

    private List<Integer> mergeCol;

    private TableMerge itemMerge;

    public TableMerge(String mergeType, List<Integer> mergeCol) {
        this.mergeType = mergeType;
        this.mergeCol = mergeCol;
    }

    public TableMerge() {
    }

    public TableMerge getItemMerge() {
        return itemMerge;
    }

    public void setItemMerge(TableMerge itemMerge) {
        this.itemMerge = itemMerge;
    }

    public String getMergeType() {
        return mergeType;
    }

    public void setMergeType(String mergeType) {
        this.mergeType = mergeType;
    }
    public RowRenderData getHeader() {
        return header;
    }

    public void setHeader(RowRenderData header) {
        this.header = header;
    }

    public List<Integer> getMergeCol() {
        return mergeCol;
    }

    public void setMergeCol(List<Integer> mergeCol) {
        this.mergeCol = mergeCol;
    }
}
