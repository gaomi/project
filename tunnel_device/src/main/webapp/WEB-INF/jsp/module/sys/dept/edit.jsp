<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">编辑部门</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool paoding_btn_hide" data-widget="remove" onclick="$('#commonModal').modal('hide')"><i class="fa fa-remove"></i></button>
        </div>
    </div>
    <form id="sys_dept_edit_form" class="form-horizontal">
        <div class="box-body">
            <input type="hidden" name="id"/>
            <div class="form-group">
                <label for="deptName" class="col-sm-3 control-label">*部门名称</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="deptName" name="deptName" placeholder="请输入">
                </div>
            </div>
            <div class="form-group">
                <label for="parentDeptName" class="col-sm-3 control-label">上级部门</label>
                <input type="hidden" name="parentId"/>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="parentDeptName" name="parentDeptName" placeholder="上级部门" disabled="disabled">
                </div>
            </div>
            <div class="form-group">
                <label for="seq" class="col-sm-3 control-label">排序</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="seq" placeholder="请输入">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">备注</label>
                <div class="col-sm-9">
                    <textarea class="form-control" name="remark" rows="3" placeholder="备注 ..."></textarea>
                </div>
            </div>

        </div>
    </form>
    <!-- /.box-body -->
    <div class="box-footer">
        <button type="reset" class="btn btn-default">重置</button>
        <button type="submit" class="btn btn-primary  pull-right">提交</button>
    </div>
</div>
<script src="${ctx }/static/js/module/sys/dept.js"></script>

