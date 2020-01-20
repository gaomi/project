<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<section class="content-header">
    <h1> 定时任务管理
        <small>系统定时执行的任务</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
        <li class="active">定时任务</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">任务列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <%--<div class="row">
                        <div class="col-md-4">
                            <form id="sys_dict_search_form">
                                <div class="form-group">
                                    <label class="sr-only">关键字</label>
                                    <div class="input-group margin">
                                        <div class="input-group-addon">关键字</div>
                                        <input type="text" class="form-control" placeholder="请输入关键字">
                                        &lt;%&ndash;<span class="input-group-btn"><button type="button" class="btn btn-info btn-flat">查询</button></span>&ndash;%&gt;
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-md-8">
                            <div class="margin btn-group pull-right">
                                <button type="button" class="btn btn-info btn-flat">查询</button>
                                <button type="button" class="btn btn-info btn-flat" onclick="openEditPage('job_info','add');">添加任务
                                </button>
                                &lt;%&ndash;  <button type="button" class="btn btn-info btn-flat " data-toggle="modal" onclick="javascript:document.getElementById('addTask').reset()"
                                          data-target="#jobModal_add">添加任务
                                  </button>&ndash;%&gt;
                                <button type="button" class="btn btn-info btn-flat">批量暂停
                                </button>
                                <button type="button" class="btn btn-info btn-flat ">批量删除
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <table id="job_info_table" class="table table-bordered table-striped text-nowrap">
                            </table>
                        </div>
                    </div>--%>
                    <div class="row">
                        <div class="table-responsive col-md-12">
                            <div id="toolbar">
                                <form id="job_info_search_form" class="form-inline" style="margin-bottom: 10px;">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <div class="input-group-addon">关键字</div>
                                            <input type="text" class="form-control input-sm" placeholder="请输入关键字" name="keyWord"/>
                                        </div>
                                    </div>
                                    <div class="pull-right">
                                        <button type="button" class="btn btn-primary" onclick="search();">查询</button>
                                        <button type="button" class="btn btn-primary" paoding-modal-size="600_600" onclick="openEditPage('job_info','add',this);">添加
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <table id="job_info_table" class="table table-bordered table-striped text-nowrap">
                            </table>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>

        </div>
    </div>
</section>
<script src="${ctx }/static/js/module/job/info.js"></script>