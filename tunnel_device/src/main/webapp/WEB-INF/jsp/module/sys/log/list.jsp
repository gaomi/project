<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<style>
    .box-body table {
        table-layout: fixed;
    }

    .box-body .txt {

        /* 只对英文起作用，以字母作为换行依据 */
        word-break: break-all;
        /* 只对英文起作用，以单词作为换行依据 */
        overflow: hidden;
        text-overflow: ellipsis;
        word-wrap: break-word;
        white-space: nowrap;

    }
</style>
<section class="content-header">
    <h1> 日志管理
        <small>系统日志</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
        <li class="active">日志管理</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">
    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">日志列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="table-responsive col-md-12">
                            <div id="toolbar">
                                <form id="sys_log_search_form" class="form-inline" style="margin-bottom: 10px;">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <div class="input-group-addon">用户名</div>
                                            <input type="text" class="form-control input-sm" placeholder="请输入用户名" name="keyWord"/>
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
                                        <button type="button" class="btn btn-primary" onclick="deleteLogs();">删除
                                        </button>
                                        <button type="button" class="btn btn-primary" onclick="deleteAllLogs();">清空
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <table id="sys_log_table" class="table table-bordered table-striped">
                            </table>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
    </div>
</section>
<script src="${ctx }/static/js/module/sys/log.js"></script>