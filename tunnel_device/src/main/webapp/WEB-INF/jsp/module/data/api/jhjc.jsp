<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<section class="content-header">
    <h1> 数据管理
        <small>接口数据</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 数据管理</a></li>
        <li class="active">接口数据</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">
    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">监护监测列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="table-responsive col-md-12">
                            <div id="toolbar">
                                <form class="form-inline" id="api_jhjc_search_form" style="margin-bottom: 10px;">
                                    <div class="form-group">
                                        <label class="sr-only">关键字</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">关键字</div>
                                            <input type="text" class="form-control" name="projectname" placeholder="请输入关键字...">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="status_search_select">状态</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">状态</div>
                                            <select class="form-control" id="status_search_select">
                                                <option value="">请选择站类型...</option>
                                                <%--<option value="停工">停工</option>--%>
                                                <%--<option value="施工">施工</option>--%>
                                                <%--<option value="已完工">已完工</option>--%>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="projectlevel_search_select">等级</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">等级</div>
                                            <select class="form-control" id="projectlevel_search_select">
                                                <option value="">请选择项目等级...</option>
                                                <%--<option value="一级">一级</option>--%>
                                                <%--<option value="二级">二级</option>--%>
                                                <%--<option value="三级">三级</option>--%>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="pull-right">
                                        <button type="button" class="btn btn-primary queryButton" onclick="search();">查询</button>
                                        <button type="button" class="btn btn-primary" paoding-modal-size="600_600" onclick="updataAllPorject();">更新全部项目
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <table id="api_jhjc_table" class="table table-bordered table-striped text-nowrap">
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.box-body -->
        </div>
    </div>
</section>
<script src="${ctx }/static/js/module/data/api/jhjc.js"></script>