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
        <form id="data_sandy_edit_form">
            <input type="hidden" name="lineUuid" id="sandy_line_uuid">
            <input type="hidden" name="uuid">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>线路编号<span class="form-bt">(*必填)</span></label>
                        <select class="form-control " name="lineCode" id="sandy_line_code"></select>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>上下行<span class="form-bt">(*必填)</span></label>
                        <select  class="form-control " name="updown" id="updown_sandy_change">
                            <option value="">--请选择--</option>
                            <option value="0" >上行</option>
                            <option value="1" >下行</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>站区间<span class="form-bt">(*必填)</span></label>
                        <select  class="form-control " id="edit_sandyName">
                        </select>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <input type="hidden" name="segmentUuid" id="edit_segmentSy_uuid">
                        <input type="hidden" name="segmentName" id="edit_segmentSy_name">
                        <label>小区间<span class="form-bt">(*必填)</span></label>
                        <select class="form-control " id="edit_sandySmillName">
                        </select>
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>与隧道位置<span class="form-bt">(*必填)</span></label>
                        <select name="sandyTunnelPosition" class="form-control ">
                        </select>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>土层<span class="form-bt">(*必填)</span></label>
                        <select type="text" name="soilLevel"  class="form-control "placeholder="请输入土层" >
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">

                <div class="col-md-6">
            <div class="form-group">
                <label>开始里程<span class="form-bt">(*必填)</span></label>
                <input type="text" name="startMileage"  class="form-control " placeholder="请输入开始里程">
            </div>
                </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>结束里程<span class="form-bt">(*必填)</span></label>
                            <input type="text" name="endMileage"  class="form-control " placeholder="请输入结束里程">
                        </div>
                    </div>

    </div>
        </form>
    </div>
    <!-- /.box-body -->
    <div class="box-footer">
        <%--<button type="reset" class="btn btn-default resetQj_btn" name="resetQj">重置</button>--%>
        <button type="submit" class="btn btn-primary saveTc_btn pull-right" name="saveQj">提交</button>
    </div>
</div>
<script src="${ctx }/static/js/module/data/sandy/edit.js"></script>

