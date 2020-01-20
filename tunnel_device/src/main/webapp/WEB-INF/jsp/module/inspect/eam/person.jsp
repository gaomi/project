<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<section class="content-header">
    <h1> 结构巡检人员
        <small>人员列表</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 结构巡检</a></li>
        <li class="active">人员列表</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">
    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">人员列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="table-responsive col-md-12">
                            <div id="toolbar">
                                <form class="form-inline" id="eam_person_search_form" style="margin-bottom: 10px;">
                                    <div class="form-group">
                                        <label class="sr-only">关键字</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">关键字</div>
                                            <input type="text" class="form-control" name="keyWord" placeholder="请输入关键字..."/>
                                        </div>
                                    </div>
                                    <%--<button type="button" class="btn btn-primary queryButton" onclick="search();">查询</button>--%>
                                    <div class="pull-right">
                                        <button type="button" class="btn btn-primary queryButton" onclick="search();">查询</button>
                                        <button type="button" class="btn btn-primary" onclick="getAllFromEam();">更新EAM数据
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <table id="eam_person_table" class="table table-bordered table-striped text-nowrap">
                            </table>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.box-body -->
        </div>
    </div>
</section>
<script src="${ctx }/static/js/module/inspect/eam/person.js"></script>
