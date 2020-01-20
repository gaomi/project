<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<style>
    .form-horizontal .has-error {
        color: red;
    }
</style>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">编辑数据</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool paoding_btn_hide paoding_btn_hide" data-widget="remove"
                    onclick="$('#commonModal').modal('hide')"><i class="fa fa-remove"></i></button>
        </div>
    </div>
    <!-- /.box-header -->
    <form id="data_station_edit_form" class="form-horizontal">
        <div class="box-body">
            <input type="hidden" name="id">
            <div class="form-group">
                <label class="col-sm-3 control-label">线路编号<span class="form-bt">(*必填)</span></label>
                <div class="col-sm-9">
                    <select class="form-control" name="lineCode" id="line2Type_search_select">
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">站编号<span class="form-bt">(*必填)</span></label>
                <div class="col-sm-9">
                <input type="text" name="stationCode" class="form-control ">
                </div>
            </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">站名称<span class="form-bt">(*必填)</span></label>
            <div class="col-sm-9">
            <input type="text" name="stationName" class="form-control ">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">位置<span class="form-bt">(*必填)</span></label>
            <div class="col-sm-9">
              <select class="form-control"  name="isTunnel">
                <option value="">--请选择--</option>
                <option value="0">高架/桥梁</option>
                <option value="1">隧道</option>
              </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">站类型<span class="form-bt">(*必填)</span></label>
            <div class="col-sm-9">
            <select class="form-control" name="classType" id="class2Type_search_select">
            </select>
            </div>
        </div>
</div>
</form>
<!-- /.box-body -->
<div class="box-footer">
    <%--<button type="reset" class="btn btn-default resetQj_btn" name="resetQj">重置</button>--%>
    <button type="submit" class="btn btn-primary saveStation_btn pull-right" name="saveStation">提交</button>
</div>
</div>
<script src="${ctx }/static/js/module/data/station/edit.js"></script>

