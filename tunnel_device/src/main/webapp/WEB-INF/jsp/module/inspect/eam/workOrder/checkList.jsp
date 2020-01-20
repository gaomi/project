<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<link rel="stylesheet" href="${ctx}/webjars/swiper/4.5.0/dist/css/swiper.min.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/inspect.css"/>
<section class="content-header">
    <h1> 结构巡检工单审核
        <small>待审核工单列表</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 结构巡检</a></li>
        <li class="active">工单审核</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">


    <%--<div class="box">
        <div class="box-header with-border">
            <h3 class="box-title">Collapsible Box Example</h3>
            <div class="box-tools pull-right">
                <!-- Collapse Button -->
                <button type="button" class="btn btn-box-tool" data-widget="collapse">
                    <i class="fa fa-minus"></i>
                </button>
            </div>
            <!-- /.box-tools -->
        </div>
        <!-- /.box-header -->
        <div class="box-body">
            The body of the box
        </div>
        <!-- /.box-body -->
        <div class="box-footer">
            The footer of the box
        </div>
        <!-- box-footer -->
    </div>--%>
    <div class="box">
        <div class="box-header">
            <h3 class="box-title">待工单列表</h3>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
            <div class="row">
                <div class="table-responsive col-md-12">
                    <div id="toolbar">
                        <form class="form-inline"  id="eam_workOrder_search_form" style="margin-bottom: 10px;">
                            <div class="form-group">
                                <label class="sr-only">工单号</label>
                                <div class="input-group">
                                    <div class="input-group-addon">工单号</div>
                                    <input type="text" class="form-control" name="orderNo" placeholder="请输入工单号...">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only">操作部门</label>
                                <div class="input-group">
                                    <div class="input-group-addon">操作部门</div>
                                    <input type="text" class="form-control" name="workOrder" placeholder="请输入操作部门...">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only">操作人</label>
                                <div class="input-group">
                                    <div class="input-group-addon">操作人</div>
                                    <input type="text" class="form-control" name="workOrder" placeholder="请输入操作人编号...">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only">关键字</label>
                                <div class="input-group">
                                    <div class="input-group-addon">关键字</div>
                                    <input type="text" class="form-control" name="keyWord" placeholder="请输入关键字...">
                                </div>
                            </div>
                            <div class="pull-right">
                                <button type="button" class="btn btn-primary queryButton" onclick="search();">查询</button>
                                <%--<button type="button" class="btn btn-primary" paoding-modal-size="600_600" onclick="openEditPage('eam_workOrder','add',this);">重置密码--%>
                                </button>
                            </div>
                        </form>
                    </div>
                    <table id="eam_workOrder_table" class="table table-bordered table-striped text-nowrap">
                    </table>
                </div>
            </div>
        </div>
        <div class="overlay">
            <i class="fa fa-refresh fa-spin"></i>
        </div>
    </div>
    <%--   <!-- /.box-body -->
       <div class="box">
           <div class="box-header with-border">
               <h3 class="box-title">工单审核</h3>
               <div class="box-tools pull-right">
                   <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                   </button>
               </div>
           </div>
           <!-- /.box-header -->
           <div class="box-body">
               <div class="row">
                   <div class="table-responsive col-md-12">


                       aaaaaaaaaaaaa
                   </div>
               </div>
           </div>

       </div>
       <!-- /.box-body -->


       <div class="box">
           <div class="box-header with-border">
               <h3 class="box-title">Collapsible Box Example</h3>
               <div class="box-tools pull-right">
                   <!-- Collapse Button -->
                   <button type="button" class="btn btn-box-tool" data-widget="collapse">
                       <i class="fa fa-minus"></i>
                   </button>
               </div>
               <!-- /.box-tools -->
           </div>
           <!-- /.box-header -->
           <div class="box-body">
               The body of the box
           </div>
           <!-- /.box-body -->
           <div class="box-footer">
               The footer of the box
           </div>
           <!-- box-footer -->
       </div>
       <!-- /.box -->--%>
</section>
<script src="${ctx}/webjars/swiper/4.5.0/dist/js/swiper.min.js"></script>
<script src="${ctx }/static/js/module/inspect/eam/workOrder/checkList.js"></script>
