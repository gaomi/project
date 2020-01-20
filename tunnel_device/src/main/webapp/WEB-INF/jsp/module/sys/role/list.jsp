<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<section class="content-header">
    <h1> 角色管理
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
        <li class="active">角色管理</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">
    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">角色列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="table-responsive col-md-12">
                            <div id="toolbar">
                                <form id="sys_role_search_form"  class="form-inline" style="margin-bottom: 10px;">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <div class="input-group-addon">关键字</div>
                                            <input type="text" class="form-control input-sm" placeholder="名称或备注关键字" name="keyWord"/>
                                        </div>
                                    </div>
                                    <div class="pull-right">
                                        <button type="button" class="btn btn-primary" onclick="search();">查询</button>
                                        <button type="button" class="btn btn-primary" paoding-modal-size="600_600" onclick="openEditPage('sys_role','add',this);">添加
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <table id="sys_role_table" class="table table-bordered table-striped text-nowrap">
                            </table>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
    </div>
</section>
<script src="${ctx }/static/js/module/sys/role/list.js"></script>