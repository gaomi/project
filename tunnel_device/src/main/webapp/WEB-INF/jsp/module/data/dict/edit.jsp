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
        <form id="data_dict_edit_form">
            <div class="row">
                <div class="col-md-6">
                    <input type="hidden" name="id">
                    <!-- text input -->
                    <div class="form-group">
                        <label>键<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="dictKey" class="form-control " placeholder="请输入">
                    </div>
                </div>
                <div class="col-md-6">
                    <input type="hidden" name="id">
                    <!-- text input -->
                    <div class="form-group">
                        <label>值<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="dictValue" class="form-control " placeholder="请输入">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <input type="hidden" name="id">
                    <!-- text input -->
                    <div class="form-group">
                        <label>排序<span class="form-bt"></span></label>
                        <input type="text" name="seq" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')" maxlength="2" class="form-control " placeholder="请输入小于两位数的数字">
                    </div>
                </div>
                <div class="col-md-6">
                    <input type="hidden" name="id">
                    <input type="hidden" name="status" value="1">
                    <!-- text input -->
                    <div class="form-group">
                        <label>归属模块<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="forModlue" class="form-control " placeholder="请输入">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label>备注</label>
                        <textarea class="form-control" name="remark" rows="3" placeholder="请输入"></textarea>
                    </div>
                </div>
            </div>
            <div class="row" style="display: none">
                <div class="col-md-12">
                    <div class="form-group">
                        <label>pid</label>
                        <textarea class="form-control" name="pid" rows="3" placeholder="请输入"></textarea>
                    </div>
                </div>

            </div>
        </form>
    </div>
    <!-- /.box-body -->
    <div class="box-footer">
        <%--<button type="reset" class="btn btn-default resetLxsz_btn" name="resetLxsz">重置</button>--%>
        <button type="submit" class="btn btn-primary saveLxsz_btn pull-right" name="saveLxsz">提交</button>
    </div>
</div>
<script src="${ctx }/static/js/module/data/dict/edit.js"></script>


