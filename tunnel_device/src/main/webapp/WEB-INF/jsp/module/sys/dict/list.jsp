<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<section class="content-header">
    <h1> 字典管理
        <small>系统字典</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
        <li class="active">字典管理</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">
    <div class="row">
        <div class="col-md-3">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">字典分类</h3>
                </div>
                <div class="box-body">
                    <div id="sys_dict_tree" style="height: 400px;overflow-y :scroll;">

                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">字典列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="table-responsive col-md-12">
                            <div id="toolbar">
                                <form id="sys_dict_search_form"  class="form-inline" style="margin-bottom: 10px;">
                                    <div class="form-group">
                                        <label class="sr-only">状态</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">状态</div>
                                            <select class="form-control input-sm" id="statusType_search_select">
                                                <option value="">请选择...</option>
                                                <option value="1">有效</option>
                                                <option value="0">无效</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <div class="input-group-addon">关键字</div>
                                            <input type="text" class="form-control input-sm" placeholder="请输入关键字" name="keyWord"/>
                                        </div>
                                    </div>
                                    <div class="pull-right">
                                        <button type="button" class="btn btn-primary" onclick="search();">查询</button>
                                        <button type="button" class="btn btn-primary" paoding-modal-size="600_600" onclick="openEditPage('sys_dict','add',this);">添加
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <table id="sys_dict_table" class="table table-bordered table-striped text-nowrap">
                            </table>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
    </div>
</section>
<script src="${ctx }/webjars/bootstrap-treeview/1.2.0/bootstrap-treeview.min.js"></script>
<script src="${ctx }/static/js/module/sys/dict/list.js"></script>