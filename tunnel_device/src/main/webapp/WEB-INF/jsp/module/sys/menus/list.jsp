<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<section class="content-header">
    <h1> 菜单管理
        <small>系统菜单</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
        <li class="active">菜单管理</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">
    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">菜单列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="table-responsive col-md-12">
                            <div id="toolbar">
                                <form id="sys_menus_search_form" class="form-inline" style="margin-bottom: 10px;">
                                    <div class="form-group">
                                        <label class="sr-only">类型</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">类型</div>
                                            <select class="form-control input-sm" id="menusType_search_select">
                                                <option value="">请选择...</option>
                                                <option value="1">菜单</option>
                                                <option value="0">按钮</option>
                                            </select>
                                        </div>
                                    </div>
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
                                            <input type="text" class="form-control input-sm" name="keyWord" placeholder="请输入关键字"/>
                                        </div>
                                    </div>
                                    <div class="pull-right">
                                        <button type="button" class="btn btn-primary" onclick="search();">查询</button>
                                        <button type="button" class="btn btn-primary" paoding-modal-size="600_600" onclick="openEditPage('sys_menus','add',this);">添加
                                        </button>
                                        <button type="button" class="btn btn-primary" onclick="deleteMenus();">删除
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <table id="sys_menus_table" class="table table-bordered table-striped text-nowrap">
                            </table>
                        </div>

                    </div>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
    </div>
</section>
<script src="${ctx }/static/js/module/sys/menus/list.js"></script>