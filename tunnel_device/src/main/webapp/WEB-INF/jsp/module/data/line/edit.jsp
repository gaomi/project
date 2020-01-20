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
    <form id="data_line_edit_form" class="form-horizontal">
    <div class="box-body">
        <input type="hidden" name="id">
                    <div class="form-group">
                            <label class="col-sm-3 control-label" >线路编号<span class="form-bt">(*必填)</span></label>
                          <div class="col-sm-9">
                        <input type="text" name="lineCode"  class="form-control"  >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">线路名称<span class="form-bt">(*必填)</span></label>
                        <div class="col-sm-9">
                            <input type="text" name="lineName"  class="form-control">
                        </div>
                   </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注<span class="form-bt"></span></label>
                        <div class="col-sm-9">
                            <textarea  name="remark"  class="form-control "rows="2"></textarea>
                        </div>
                    </div>
              </div>
    </form>
    <!-- /.box-body -->
    <div class="box-footer">
        <%--<button type="reset" class="btn btn-default resetQj_btn" name="resetQj">重置</button>--%>
        <button type="submit" class="btn btn-primary saveLine_btn pull-right" name="saveLine">提交</button>
    </div>
</div>
<script src="${ctx }/static/js/module/data/line/edit.js"></script>

