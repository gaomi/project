<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<section class="content-header">
    <h1> 定时任务日志管理
        <small>定时执行的任务日志</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
        <li class="active">任务日志</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">日志列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="table-responsive col-md-12">
                            <div id="toolbar">
                                <form id="job_log_search_form" class="form-inline" style="margin-bottom: 10px;">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <div class="input-group-addon">关键字</div>
                                            <input type="text" class="form-control input-sm" placeholder="请输入关键字" name="keyWord"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <div class="input-group-addon">起始时间</div>
                                            <input type="text" class="form-control input-sm time-select" placeholder="起始时间" name="sCreateTime"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <div class="input-group-addon">结束时间</div>
                                            <input type="text" class="form-control input-sm time-select" placeholder="结束时间" name="eCreateTime"/>
                                        </div>
                                    </div>
                                    <div class="pull-right">
                                        <button type="button" class="btn btn-primary" onclick="search();">查询</button>
                                        <button type="button" class="btn btn-primary" onclick="deleteJobLogs();">删除
                                        </button>
                                        <button type="button" class="btn btn-primary" onclick="deleteJobAllLogs();">清空
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <table id="job_log_table" class="table table-bordered table-striped">
                            </table>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
    </div>
</section>
<!-- /.row -->
<script src="${ctx }/static/js/module/job/log.js"></script>
