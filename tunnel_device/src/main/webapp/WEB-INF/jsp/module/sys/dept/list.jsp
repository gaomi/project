<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<section class="content-header">
    <h1> 部门管理
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
        <li class="active">部门管理</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">部门列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="input-group margin">
                                <input type="text" class="form-control">
                                <%--<span class="input-group-btn"><button type="button" class="btn btn-info btn-flat">查询</button></span>--%>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="margin btn-group pull-right">
                                <button type="button" class="btn btn-info btn-flat">查询</button>
                                <button type="button" class="btn btn-info btn-flat" onclick="openEditPage('sys_dept','add');">添加部门
                                </button>
                                <button type="button" class="btn btn-info btn-flat">批量删除
                                </button>
                                <button type="button" class="btn btn-info btn-flat ">导入
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <table id="sys_dept_table" class="table table-bordered table-striped text-nowrap">
                            </table>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
    </div>
</section>
<%--<script src="${ctx }/static/js/module/sys/dept.js"></script>--%>