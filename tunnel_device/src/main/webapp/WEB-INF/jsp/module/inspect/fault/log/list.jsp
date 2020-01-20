<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/inspect.css"/>
<section class="content-header">
    <h1> 病害记录查询
        <small>病害记录</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 结构巡检</a></li>
        <li class="active">病害查询</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">
    <div class="box">
        <div class="box-header">
            <h3 class="box-title">病害记录列表</h3>
        </div>
        <div class="box-body">
            <div class="row">
                <div class="table-responsive col-md-12">
                    <div id="toolbar">
                        <form class="form-inline device_change_height" id="fault_log_search_form" style="margin-bottom: 10px;">
                            <div class="form-group">
                                <label class="sr-only">线路</label>
                                <div class="input-group">
                                    <div class="input-group-addon">线路</div>
                                    <select class="form-control" name="lineId" id="lineType_search_select">
                                        <option value="">请选择...</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only">上下行</label>
                                <div class="input-group">
                                    <div class="input-group-addon">上下行</div>
                                    <select class="form-control" name="updown" id="updownType_search_select">
                                        <option value="">请选择...</option>
                                        <option value="0">上行</option>
                                        <option value="1">下行</option>
                                        <option value="2">上下行</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only">起始站</label>
                                <div class="input-group">
                                    <div class="input-group-addon">起始站</div>
                                    <select class="form-control" name="sStationName" id="startStaType_search_select">
                                        <option value="">请选择...</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only">结束站</label>
                                <div class="input-group">
                                    <div class="input-group-addon">结束站</div>
                                    <select class="form-control" name="eStationName" id="endStaType_search_select">
                                        <option value="">请选择...</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon">起始时间</div>
                                    <input type="text" class="form-control  time-select" placeholder="起始时间" name="sCreateTime"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon">结束时间</div>
                                    <input type="text" class="form-control  time-select" placeholder="结束时间" name="eCreateTime"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only">工单号</label>
                                <div class="input-group">
                                    <div class="input-group-addon">工单号</div>
                                    <input type="text" class="form-control" name="planId" placeholder="请输入工单号...">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only">操作部门</label>
                                <div class="input-group">
                                    <div class="input-group-addon">操作部门</div>
                                    <input type="text" class="form-control" name="deptId" placeholder="请输入操作部门...">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only">操作人</label>
                                <div class="input-group">
                                    <div class="input-group-addon">操作人</div>
                                    <input type="text" class="form-control" name="operator" placeholder="请输入操作人编号...">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only">工单状态</label>
                                <div class="input-group">
                                    <div class="input-group-addon">工单状态</div>
                                    <select class="form-control" name="plan_status" id="statusType_search_select">
                                        <option value="">请选择...</option>
                                        <option value="0">下发</option>
                                        <option value="1">已接收</option>
                                        <option value="2" selected>待审核</option>
                                        <option value="3">已关闭</option>
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

                        </form>
                        <div class="pull-right">
                            <button type="button" class="btn autoHeightFau" onclick="packUpMenu(this,'fault_log_search_form')">展开菜单</button>
                            <button type="button" class="btn btn-primary queryButton" onclick="search();">查询</button>
                            <button type="button" class="btn btn-primary queryButton"  paoding-modal-size="800_600" onclick="openEditPage('fault_log','add',this);">添加</button>
                            <%--<button type="button" class="btn btn-primary" paoding-modal-size="600_600" onclick="openEditPage('eam_workOrder','add',this);">重置密码--%>
                            </button>
                        </div>
                    </div>
                    <table id="fault_log_table" class="table table-bordered table-striped text-nowrap">
                    </table>
                </div>
            </div>
        </div>
        <!-- /.box-body -->
        <div class="overlay">
            <i class="fa fa-refresh fa-spin"></i>
        </div>
    </div>
</section>
<script src="${ctx }/static/js/module/inspect/fault/log/list.js"></script>