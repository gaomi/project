<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<style>
    .has-error{
        color: red;
    }
</style>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">编辑数据</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool paoding_btn_hide paoding_btn_hide" data-widget="remove" onclick="$('#commonModal').modal('hide')"><i
                    class="fa fa-remove"></i></button>
        </div>
    </div>
    <!-- /.box-header -->
    <div class="box-body">
        <form id="data_device_edit_form">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>线路编号<span class="form-bt"></span></label>
                        <select class="form-control " name="lineCode" id="device_line_code"></select>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>上下行<span class="form-bt"></span></label>
                        <select  class="form-control " name="HIGH_LOW" id="">
                            <option value="">--请选择--</option>
                            <option value="U">上行</option>
                            <option value="D">下行</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">

                    <div class="form-group">
                        <label>开始站<span class="form-bt"></span></label>
                        <select name="START_STATION_NAME" class="form-control ">
                            <option value="">--请选择--</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-6">

                    <div class="form-group">
                        <label>结束站<span class="form-bt"></span></label>
                        <select name="END_STATION_NAME" class="form-control ">
                            <option value="">--请选择--</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>设备名称<span class="form-bt"></span></label>
                        <input type="text" name="text"  class="form-control " placeholder="请输入设备名称" >
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>开始环号<span class="form-bt"></span></label>
                        <input type="text" name="START_DUCT_CODE"  class="form-control "placeholder="请输入开始环号" >
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>结束环号<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="END_DUCT_CODE"  class="form-control " placeholder="请输入结束环号">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>起始里程<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="START_MILEAGE_CODE"  class="form-control " placeholder="请输入起始里程">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>结束里程<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="END_MILEAGE_CODE"  class="form-control " placeholder="请输入结束里程">
                    </div>
                </div>
                <div class="col-md-6">
                    <input type="hidden" name="id">
                    <!-- text input -->
                    <div class="form-group">
                        <label>里程标<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="START_MILEAGE_FLAG"  class="form-control " placeholder="请输入里程标">
                    </div>
                </div>
            </div>

        </form>
    </div>
    <!-- /.box-body -->
    <div class="box-footer">
        <%--<button type="reset" class="btn btn-default resetQj_btn" name="resetQj">重置</button>--%>
        <button type="submit" class="btn btn-primary saveQj_btn pull-right" name="saveQj">提交</button>
    </div>
</div>
<script src="${ctx }/static/js/module/data/device/edit.js"></script>

