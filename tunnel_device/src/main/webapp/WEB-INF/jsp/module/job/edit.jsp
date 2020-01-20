<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">编辑任务</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool paoding_btn_hide" data-widget="remove" onclick="$('#commonModal').modal('hide')"><i class="fa fa-remove"></i></button>
        </div>
    </div>
    <!-- /.box-header -->


                <form id="job_info_edit_form" class="form-horizontal">
                    <div class="box-body">
                    <input type="hidden" name="jobId"/>
                    <input type="hidden" name="status"/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">类名称</label>
                        <div class="col-sm-10"><input type="text" class="form-control" name="beanName" placeholder="请输入类名称"/></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">方法名</label>
                        <div class="col-sm-10"><input type="text" class="form-control" name="methodName" placeholder="请输方法名称"/></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">参数</label>
                        <div class="col-sm-10"><input type="text" class="form-control" name="params" placeholder="请输入参数"/></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">cron表达式</label>
                        <div class="col-sm-10"><input type="text" class="form-control" name="cronExpression" placeholder="请输入cron表达式"/></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">备注</label>
                        <div class="col-sm-10">
                            <textarea class="col-sm-12" name="remark" placeholder="请输入备注"></textarea>
                        </div>
                    </div>
                    </div>
                </form>


    <!-- /.box-body -->
    <div class="box-footer">
        <button type="reset" class="btn btn-default">重置</button>
        <button type="submit" class="btn btn-primary  paoding_btn_save pull-right">提交</button>
    </div>
</div>
<script src="${ctx }/static/js/module/job/edit.js"></script>


