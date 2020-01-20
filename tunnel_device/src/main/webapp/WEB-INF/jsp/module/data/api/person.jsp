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
                    <h3 class="box-title">巡检人员列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="table-responsive col-md-12">
                            <div id="toolbar">
                                <form class="form-inline"  id="api_person_search_form" style="margin-bottom: 10px;">
                                    <div class="form-group">
                                        <label class="sr-only">关键字</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">关键字</div>
                                            <input type="text" class="form-control" name="personname" placeholder="请输入关键字...">
                                        </div>
                                    </div>
                                    <div class="pull-right">
                                        <button type="button" class="btn btn-primary queryButton" onclick="search();">查询</button>
                                        <button type="button" class="btn btn-primary" paoding-modal-size="600_600" onclick="updataAllPerson();">更新全部人员
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <table id="api_person_table" class="table table-bordered table-striped text-nowrap">
                            </table>
                        </div>


                    </div>
                </div>
            </div>
            <!-- /.box-body -->
        </div>
    </div>
</section>
<script src="${ctx }/static/js/module/data/api/person.js"></script>