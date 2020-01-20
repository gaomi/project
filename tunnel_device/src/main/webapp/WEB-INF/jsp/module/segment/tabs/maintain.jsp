<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
            </div>
            <div class="box-body">
                <div class="toolbar paoding-dg-toolbar">
                    <div class="easyui-panel toolbar-north-panel tab_title">
                        <div class="toolbar_box_bt">
                            <ul>
                                <li>
                                    <label>关键字</label>
                                    <input id="maintainType" type="text" datatype="trim" class="li_select check-input"/>
                                </li>
                                <li onclick="queryMainTain()" class="tbale_aqbhq_bt remst_bt">
                                    查询
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <table id="maintain_list" style="word-break:break-all"></table>

            </div>
            <!-- /.box-body -->
        </div>
        <!-- /.box -->
    </div>
    <!-- /.col -->
    <script src="${ctx }/static/js/module/segment/tabs/maintain.js"></script>
</div>