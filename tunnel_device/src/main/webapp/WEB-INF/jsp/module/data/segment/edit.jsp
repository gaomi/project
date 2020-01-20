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
        <form id="data_segment_edit_form">
            <div class="row">
                <div class="col-md-6">
                    <input type="hidden" name="id">
                    <!-- text input -->
                    <div class="form-group">
                        <label>线路编号<span class="form-bt"></span></label>
                        <input type="text" name="lineCode"  class="form-control " disabled="disabled" >
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>上下行<span class="form-bt"></span></label>
                        <select type="text" name="updown"  class="form-control " disabled="disabled" >
                            <option value="0">上行</option>
                            <option value="1">下行</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>开始站<span class="form-bt"></span></label>
                        <input type="text" name="startStationName"  class="form-control " disabled="disabled">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>结束站<span class="form-bt"></span></label>
                        <input type="text" name="endStationName"  class="form-control " disabled="disabled">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>开始环号<span class="form-bt"></span></label>
                        <input type="text" name="startDuctCode"  class="form-control " disabled="disabled">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>结束环号<span class="form-bt"></span></label>
                        <input type="text" name="endDuctCode"  class="form-control " disabled="disabled">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>起始里程<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="startMileageCode"  class="form-control " placeholder="请输入">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>结束里程<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="endMileageCode"  class="form-control " placeholder="请输入">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>起始里程标<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="startMileageFlag"  class="form-control " placeholder="请输入">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>结束里程标<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="endMileageFlag"  class="form-control " placeholder="请输入">
                    </div>
                </div>
            </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>管片宽度<span class="form-bt"></span></label>
                            <input type="text" name="gpWidth" disabled="disabled" class="form-control ">
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
<script src="${ctx }/static/js/module/data/segment/edit.js"></script>

