<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<section class="content-header">
    <h1> 数据管理
        <small>设备列表</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 数据管理</a></li>
        <li class="active">设备列表</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">
    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">设备列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="table-responsive col-md-12">
                            <div id="toolbar">
                                <form id="data_device_search_form"  class="form-inline" style="margin-bottom: 10px;">
                                    <div class="form-group">
                                        <label class="sr-only" for="lineType_search_select">线路编号</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">线路编号</div>
                                            <select class="form-control" name="product_line" id="lineType_search_select">
                                                <option value="">请选择线路...</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="updownType_search_select">上下行</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">上下行</div>
                                            <select class="form-control" id="updownType_search_select">
                                                <option value="">上下行</option>
                                                <option value="0">上行</option>
                                                <option value="1">下行</option>
                                                <option value="2">上下行</option>
                                            </select>
                                        </div>
                                    </div>
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
                                        <button type="button" class="btn btn-primary" paoding-modal-size="600_600" onclick="openEditPage('data_device','add',this);">添加
                                        </button>
                                        <button type="button" class="btn btn-primary" onclick="deleteStations();">删除
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <table id="data_device_table" class="table table-bordered table-striped text-nowrap">
                            </table>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.box-body -->
        </div>
    </div>
</section>
<script src="${ctx }/static/js/module/data/device/list.js"></script>
