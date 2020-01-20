<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">修改个人配置</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool paoding_btn_hide paoding_btn_hide" data-widget="remove" onclick=" $('#commonModal').modal('hide');"><i
                class="fa fa-remove"></i></button>
        </div>
    </div>
    <!-- /.box-header -->
    <div class="box-body">

        <form id="user_config_edit_form">
            <div class="post">
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label>分页数：</label>
                            <input type="text" name="pageSize" class="form-control input-sm" placeholder="无" value="${user.pageSize }"/>
                            <input type="hidden" name="id" value="${user.id }"/>
                        </div>
                    </div>

                </div>
            </div>
        </form>

    </div>
    <!-- /.box-body -->
    <div class="box-footer">
        <button type="submit" class="btn btn-primary paoding_btn_save pull-right" name="update">提交</button>
    </div>
</div>
<script src="${ctx }/static/js/module/sys/user/configEdit.js"></script>

