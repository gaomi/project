<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<section class="content-header">
    <h1> 用户管理
        <small>系统用户</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
        <li class="active">用户管理</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">
    <div class="row">
        <div class="col-md-3">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">部门列表</h3>
                </div>
                <div class="box-body">
                    <%--<div id="configure-tree-menu" class="easyui-menu" style="width:150px;">
                        <div id="configureAdd" data-options="iconCls:'icon-add'">新增节点</div>
                        <div id="configureEdit" data-options="iconCls:'icon-edit'">编辑节点</div>
                        <div id="configureDelete" data-options="iconCls:'icon-remove'">删除节点</div>
                    </div>
                </div>--%>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="margin btn-group">
                                <button type="button" class="btn btn-info btn-flat" id="treeControl">全部折叠
                                </button>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="margin btn-group pull-right">
                                <button type="button" class="btn btn-info btn-flat" paoding-modal-size="500_600" onclick="openEditPage('sys_dept','add',this);"
                                        data-target="#commonModal">添加部门
                                </button>
                            </div>
                        </div>
                    </div>
                    <div id="sys_dept_tree" class="ztree" style="height: 500px;overflow-y :scroll;"></div>

                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">用户列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="table-responsive col-md-12">
                            <div id="toolbar">
                                <form id="sys_user_search_form"  class="form-inline" style="margin-bottom: 10px;">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <div class="input-group-addon">关键字</div>
                                            <input type="text" class="form-control input-sm" placeholder="用户名、姓名、邮箱或手机号关键字" name="keyWord"/>
                                        </div>
                                    </div>
                                    <div class="pull-right">
                                        <button type="button" class="btn btn-primary" onclick="search();">查询</button>
                                        <button type="button" class="btn btn-primary" paoding-modal-size="800_600" onclick="openEditPage('sys_user','add',this);">添加
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <table id="sys_user_table" class="table table-bordered table-striped text-nowrap">
                            </table>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
    </div>
</section>
<style type="text/css">
    div#rMenu {
        position: absolute;
        visibility: hidden;
        top: 0;
        background-color: #555;
        text-align: left;
        padding: 2px;
    }

    div#rMenu a {
        cursor: pointer;
        list-style: none outside none;
    }
</style>
<div id="rMenu">
    <ul class="dropdown-menu">
        <li id="m_add"><a href="javascript:;" onclick="addTreeNode();">增加部门</a></li>
        <li id="m_edit"><a href="javascript:;" onclick="editTreeNode();">修改部门</a></li>
        <li id="m_del"><a href="javascript:;" onclick="removeTreeNode();">删除部门</a></li>
    </ul>
</div>

<script src="${ctx }/static/js/module/sys/user/list.js"></script>