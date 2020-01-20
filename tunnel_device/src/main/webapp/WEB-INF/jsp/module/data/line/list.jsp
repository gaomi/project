<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<section class="content-header">
    <h1> 数据管理
        <small>线路列表</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 数据管理</a></li>
        <li class="active">线路列表</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">
    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">线路列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="table-responsive col-md-12">
                            <div>
                                <form id="data_line_search_form" class="form-inline" style="margin-bottom: 10px;">
                                    <div class="form-group">
                                        <label class="sr-only">关键字</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">关键字</div>
                                            <input type="text" class="form-control" name="keyWord" placeholder="请输入关键字..."/>
                                        </div>
                                    </div>
                                    <%--<div class="input-group margin">
                                        <input type="text" class="form-control" name="keyWord" placeholder="请输入关键字">
                                        <span class="input-group-btn"><button type="button" class="btn btn-info btn-flat" onclick="search();">查询</button></span>
                                    </div>--%>

                                    <div class="pull-right">
                                        <button type="button" class="btn btn-primary" onclick="search();">查询</button>
                                        <button type="button" class="btn btn-primary" paoding-modal-size="600_600" onclick="openEditPage('data_line','add',this);">添加
                                        </button>
                                    </div>
                                </form>
                            </div>

                            <table id="data_line_table" class="table table-bordered table-striped text-nowrap">
                            </table>
                        </div>


                    </div>
                </div>
            </div>
            <!-- /.box-body -->
        </div>
    </div>
</section>
<script src="${ctx }/static/js/module/data/line/list.js"></script>
