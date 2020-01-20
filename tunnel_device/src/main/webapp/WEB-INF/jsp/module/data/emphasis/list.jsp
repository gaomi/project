<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<section class="content-header">
    <h1> 数据管理
        <small>大修信息列表</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 数据管理</a></li>
        <li class="active">大修信息列表</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">
    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">大修信息列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="table-responsive col-md-12">
                            <div>
                                <form id="data_emphasis_search_form" class="form-inline" style="margin-bottom: 10px;">
                                    <div class="form-group">
                                        <label class="sr-only" for="emphasisType_search_select">线路编号</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">线路编号</div>
                                            <select class="form-control" name="emphasis_line_uuid" id="emphasisType_search_select">
                                                <option value="">请选择线路...</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="updownType_search_select">上下行</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">上下行</div>
                                            <select class="form-control" id="updownType_search_select">
                                                <option value="">请选择上下行</option>
                                                <option value="0">上行</option>
                                                <option value="1">下行</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="segmentType_search_select">区间</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">区间</div>
                                            <select class="form-control" id="segmentType_search_select">
                                                <option value="" data-group="">请选择区间</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only">环号</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">环号</div>
                                            <input type="text" class="form-control" name="duct" placeholder="请输入环号..."/>
                                        </div>
                                    </div>
                                    <div class="pull-right">
                                        <button type="button" class="btn btn-primary" onclick="search();">查询</button>
                                        <button type="button" class="btn btn-primary" paoding-modal-size="600_600" onclick="openEditPage('data_emphasis','add',this);">添加
                                        </button>
                                        <button type="button" class="btn btn-primary" onclick="deleteStations();">删除
                                        </button>
                                    </div>
                                </form>
                            </div>

                            <table id="data_emphasis_table" class="table table-bordered table-striped text-nowrap">
                            </table>
                        </div>


                    </div>
                </div>
            </div>
            <!-- /.box-body -->
        </div>
    </div>
</section>
<script src="${ctx }/static/js/module/data/emphasis/list.js"></script>
