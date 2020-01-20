<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">查看设备信息</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool paoding_btn_hide paoding_btn_hide" data-widget="remove"
                    onclick="$('#commonModal').modal('hide')"><i
                    class="fa fa-remove"></i></button>
        </div>
    </div>
    <!-- /.box-header -->
    <div class="box-body">
        <form id="device_detail_form">
            <div>
                <div class="col-xs-6 row-style">
                    <div class="col-xs-4 col-label">设备编号：</div>
                    <div name="EQUIPNO" class="col-xs-8 col-desc"></div>
                </div>
                <div class="col-xs-6 row-style">

                    <div class="col-xs-4 col-label">设备名称：</div>
                    <div name="EQUIPNAME" class="col-xs-8 col-desc"></div>
                </div>
            </div>
            <div>
                <div class="col-xs-6 row-style">
                    <div class="col-xs-4 col-label">线路：</div>
                    <div name="LINE_CODE" class="col-xs-8 col-desc"></div>
                </div>
                <div class="col-xs-6 row-style">
                    <div class="col-xs-4 col-label">上下行：</div>
                    <div name="UPDOWN_V" class="col-xs-8 col-desc"></div>
                </div>
            </div>
            <div>
                <div class="col-xs-6 row-style">
                    <div class="col-xs-4 col-label">起始站：</div>
                    <div name="START_STATION_NAME" class="col-xs-8 col-desc"></div>
                </div>
                <div class="col-xs-6 row-style">
                    <div class="col-xs-4 col-label">结束站：</div>
                    <div name="END_STATION_NAME" class="col-xs-8 col-desc"></div>
                </div>
            </div>
            <div>
                <div class="col-xs-6 row-style">
                    <div class="col-xs-4 col-label">开始环：</div>
                    <div name="DUCT_CODE_START" class="col-xs-8 col-desc"></div>
                </div>
                <div class="col-xs-6 row-style">
                    <div class="col-xs-4 col-label">结束环：</div>
                    <div name="DUCT_CODE_END" class="col-xs-8 col-desc"></div>
                </div>
            </div>
            <div>
                <div class="col-xs-6 row-style">
                    <div class="col-xs-4 col-label">开始里程：</div>
                    <div name="MILEAGE_CODE_START" class="col-xs-8 col-desc"></div>
                </div>
                <div class="col-xs-6 row-style">
                    <div class="col-xs-4 col-label">结束里程：</div>
                    <div name="MILEAGE_CODE_END" class="col-xs-8 col-desc"></div>
                </div>
            </div>
            <div>
                <div class="col-xs-6 row-style">
                    <div class="col-xs-4 col-label">开始里程标：</div>
                    <div name="START_MILEAGE_FLAG" class="col-xs-8 col-desc"></div>
                </div>
                <div class="col-xs-6 row-style">
                    <div class="col-xs-4 col-label">结束里程标：</div>
                    <div name="END_MILEAGE_FLAG" class="col-xs-8 col-desc"></div>
                </div>
            </div>
        </form>
    </div>
    <div class="box-footer">
        <%--<button type="reset" class="btn btn-default resetLxsz_btn" name="resetLxsz">重置</button>--%>
        <button type="submit" onclick="$('#commonModal').modal('hide')" class="btn btn-primary saveBhgl_btn pull-right"
                name="saveBhgl">关闭
        </button>
    </div>
</div>
<script src="${ctx }/static/js/module/inspect/device/detail.js"></script>