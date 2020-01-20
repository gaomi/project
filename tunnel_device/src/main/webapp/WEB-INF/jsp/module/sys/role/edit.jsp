<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<style>
    .has-error {
        color: red;
    }
</style>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">编辑角色</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool paoding_btn_hide" data-widget="remove"
                    onclick="$('#commonModal').modal('hide')"><i class="fa fa-remove"></i></button>
        </div>
    </div>
    <!-- /.box-header -->
    <form id="sys_role_edit_form" class="form-horizontal">
        <div class="box-body">
            <input type="hidden" name="id">
            <div class="form-group">
                <label for="inputRoleName" class="col-sm-3 control-label">角色名称<span class="form-bt">(*必填)</span></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="name" id="inputRoleName" placeholder="角色名称">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-3 control-label">角色状态</label>

                <div class="col-sm-9">
                    <div id="inputPassword3">
                        <div class="row">
                            <div class="col-xs-4">
                                <div class="edit_radio_div">
                                    <input type="radio" name="status" value="1" checked="">
                                    有效
                                </div>
                            </div>
                            <div class="col-xs-4">
                                <div class="edit_radio_div">
                                    <input type="radio" name="status" value="0">
                                    冻结
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="roleRemark" class="col-sm-3 control-label">备注</label>
                <div class="col-sm-9">
                    <textarea class="form-control" id="roleRemark" name="remark" rows="3" placeholder="请输入"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label for="editFormMenuTree" class="col-sm-3 control-label">菜单权限<span
                        class="form-bt">(*必填)</span></label>
                <div class="col-sm-8" style="max-height:400px;overflow-y: auto;">
                    <ul id="editFormMenuTree" class="ztree"></ul>
                    <input type="hidden" name="menuId">
                </div>
            </div>
        </div>
    </form>
    <!-- /.box-body -->
    <div class="box-footer">
        <button type="reset" class="btn btn-default paoding_btn_reset" name="reset">重置</button>
        <button type="submit" class="btn btn-primary paoding_btn_save pull-right" name="save">提交</button>
    </div>
</div>
<script src="${ctx }/static/js/module/sys/role/edit.js"></script>


