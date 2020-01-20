<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<section class="content-header">
    <h1> 结构巡检
        <small>故障分类列表</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 结构巡检</a></li>
        <li class="active">故障分类列表</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">
    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">故障分类列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="table-responsive col-md-12">
                            <div id="toolbar">
                                <form class="form-inline" id="device_faultType_search_form" style="margin-bottom: 10px;">
                                    <div class="form-group">
                                        <label class="sr-only" for="levelType_search_select">大类型</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">大类型</div>
                                            <select class="form-control" name="big_type" id="levelType_search_select">
                                                <option value="">请选择...</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="deviceType_search_select">小类型</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">小类型</div>
                                            <select class="form-control" name="small_type" id="deviceType_search_select">
                                                <option value="">请选择...</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="fault1Type_search_select">病害类型</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">病害类型</div>
                                            <select class="form-control" name="fault_type" id="fault1Type_search_select">
                                                <option value="">请选择...</option>
                                            </select>
                                        </div>
                                    </div>
                                    <%--<div class="form-group">
                                        <label class="sr-only" for="fault2Type_search_select">病害类型2</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">病害类型2</div>
                                            <select class="form-control" id="fault2Type_search_select">
                                                <option value="">请选择...</option>
                                            </select>
                                        </div>
                                    </div>--%>
                                    <div class="form-group">
                                        <label class="sr-only">关键字</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">关键字</div>
                                            <input type="text" class="form-control" name="keyWord" placeholder="请输入关键字...">
                                        </div>
                                    </div>
                                    <%--<button type="button" class="btn btn-primary queryButton" onclick="search();">查询</button>--%>
                                    <div class="pull-right">
                                        <button type="button" class="btn btn-primary queryButton" onclick="search();">查询</button>
                                        <button type="button" class="btn btn-primary" paoding-modal-size="600_600" onclick="openEditPage('device_faultType','add',this);">添加
                                        </button>
                                        <button type="button" class="btn btn-primary" onclick="deleteStations();">删除
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <table id="device_faultType_table" class="table table-bordered table-striped text-nowrap">
                            </table>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.box-body -->
        </div>
    </div>
</section>
<script src="${ctx }/static/js/module/inspect/device/faultType/list.js"></script>
