<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<section class="content-header">
    <h1> 数据配置
        <small>系统数据配置</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 数据管理</a></li>
        <li class="active">数据配置</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">
    <div class="row">
        <div class="col-md-3">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">数据分类</h3>
                </div>
                <style>
                    #accordion  .dict_frist_warpper{
                        width: 100%;
                        height: 35px;
                        border-radius: 4px;
                        margin-top: 2px;
                        border: 1px solid #d2d6de;
                        box-shadow: 1px 1px 1px #ddd;
                    }
                    #accordion  .dict_frist_text:hover{
                        /*background-color:#f6f6f6;*/
                        background-color: #00abfc;
                        cursor: pointer;
                        color: #fff;
                    }
                    #accordion  .dict_frist_text{
                        display: block;
                        text-align: left;
                        line-height: 35px;
                        font-size: 14px;
                        padding-left: 25px;
                        position: relative;
                    }
                    #accordion  .item_dict{
                        width: 100%;
                        height: 30px;
                        overflow: hidden;
                        text-overflow: ellipsis;
                        border: 1px solid #d2d6de;
                        text-align: left;
                        line-height: 30px;
                        background-color:#f9f9f9;
                        font-size: 13px;
                        padding-left: 40px;
                    }
                    #accordion  .item_dict:hover{
                        background-color:#f6f6f6;
                        cursor: pointer;
                        color:#00abfc;
                    }
                    #data_dict_tree .selected{ border-left: 4px solid #3c8dbc;}
                    #accordion   .tree_dict_box{
                        background-color: #00abfc;
                        color: #fff !important;
                    }
                    #accordion  .tree_dict_child{
                        color: #00abfc;
                    }
                    #accordion .pull-dict{
                        width: 20px;
                        height: 14px;
                        position: absolute;
                        top: 11px;
                        right: 15px;
                    }
                </style>
                <div class="box-body">
                    <div id="data_dict_tree" style="height: 400px;overflow-y :scroll;">
                        <div id="accordion">
                            <%--<div class="card">--%>
                                <%--<div class="dict_frist_warpper">--%>
                                    <%--<a class="collapsed dict_frist_text" data-toggle="collapse" href="#collapseOne">--%>
                                        <%--选项一--%>
                                    <%--</a>--%>
                                <%--</div>--%>
                                <%--<div id="collapseOne" class="collapse" data-parent="#accordion">--%>
                                    <%--<div class="card-body item_dict">--%>
                                        <%--#1 内容：--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="card">--%>
                                <%--<div class="dict_frist_warpper">--%>
                                    <%--<a class="collapsed dict_frist_text" data-toggle="collapse" href="#collapseTwo">--%>
                                        <%--选项二--%>
                                    <%--</a>--%>
                                <%--</div>--%>
                                <%--<div id="collapseTwo" class="collapse" data-parent="#accordion">--%>
                                    <%--<div class="card-body item_dict">--%>
                                        <%--#2 内容：--%>
                                    <%--</div>--%>
                                    <%--<div class="card-body item_dict">--%>
                                        <%--#2 内容：--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="card">--%>
                                <%--<div class="dict_frist_warpper">--%>
                                    <%--<a class="collapsed dict_frist_text" data-toggle="collapse" href="#collapseThree">--%>
                                        <%--选项三--%>
                                    <%--</a>--%>
                                <%--</div>--%>
                                <%--<div id="collapseThree" class="collapse" data-parent="#accordion">--%>
                                    <%--<div class="card-body item_dict">--%>
                                        <%--#3 内容：--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">配置列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <%--<div class="row">
                        <div class="col-md-4">
                            <div class="input-group margin">
                                <input type="text" class="form-control">
                                <span class="input-group-btn"><button type="button" class="btn btn-info btn-flat">查询</button></span>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="margin btn-group pull-right">
                                <button type="button" class="btn btn-info btn-flat" onclick="openEditPage('sys_dict','add');">添加字典
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
                            <table id="data_dict_table" class="table table-bordered table-striped text-nowrap">
                            </table>
                        </div>
                    </div>--%>

                    <div class="row">
                        <div class="table-responsive col-md-12">
                            <div id="toolbar">
                                <form class="form-inline" id="data_dict_search_form" style="margin-bottom: 10px;">
                                    <div class="form-group">
                                        <label class="sr-only">关键字</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">关键字</div>
                                            <input type="text" class="form-control" name="keyWord"
                                                   placeholder="请输入关键字...">
                                        </div>
                                    </div>
                                    <div class="pull-right">
                                        <button type="button" class="btn btn-primary queryButton" onclick="search();">
                                            查询
                                        </button>
                                        <button type="button" class="btn btn-primary" paoding-modal-size="600_600"
                                                onclick="addDictOpen(this);">添加
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <table id="data_dict_table" class="table table-bordered table-striped text-nowrap">
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
<script src="${ctx }/static/js/module/data/dict/list.js"></script>
