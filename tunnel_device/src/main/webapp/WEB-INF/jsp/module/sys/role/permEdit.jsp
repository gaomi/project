<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">编辑角色</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool paoding_btn_hide" data-widget="remove" onclick="$('#commonModal').modal('hide')"><i class="fa fa-remove"></i></button>
        </div>
    </div>
    <!-- /.box-header -->
    <form id="sys_role_edit_form" class="form-horizontal">
        <div class="box-body">
            <input type="hidden" name="id">
            <div class="form-group">
                <label for="inputRoleName" class="col-sm-2 control-label">角色名称</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="name" id="inputRoleName" placeholder="角色名称" readonly="readonly"/>
                </div>
            </div>
            <div class="form-group">
                <label for="editFormMenuTree" class="col-sm-2 control-label">菜单权限</label>
                <div class="col-sm-10">
                    <ul id="editFormMenuTree" class="ztree"></ul>
                    <input type="hidden" name="menuId">
                </div>
            </div>
        </div>
    </form>
    <!-- /.box-body -->
    <div class="box-footer">
        <button type="reset" class="btn btn-default paoding_btn_reset" name="reset">重置</button>
        <button type="submit" class="btn btn-primary paoding_btn_save pull-right" name="updateRole">提交</button>
    </div>
</div>
<script src="${ctx }/static/js/module/sys/role/permEdit.js"></script>


