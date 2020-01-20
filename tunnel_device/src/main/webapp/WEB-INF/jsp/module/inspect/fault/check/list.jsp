<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<section class="content-header">
    <h1> 病害审核
        <small>病害审核列表</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 结构巡检</a></li>
        <li class="active">病害审核维护</li>
    </ol>
</section>
<section class="content container-fluid" id="content_wrapper_content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">病害审核列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="input-group margin">
                                <input type="text" class="form-control">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-info btn-flat">查询</button>
                                    <button type="button" class="btn btn-info btn-flat more_search">更多查询</button>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="margin btn-group pull-right">
                                <%-- <button type="button" class="btn btn-info btn-flat" paoding-modal-size="1000_600" onclick="openEditPage('fault_plan','add',this);"
                                         data-target="#commonModal">添加
                                 </button>
                                 <button type="button" class="btn btn-info btn-flat" paoding-modal-size="1000_600" paoding_opt="PalnEdit" onclick="openPlanEdit('fault_plan','edit',this);"
                                         data-target="#commonModal">修改
                                 </button>--%>
                                <button type="button" class="btn btn-info btn-flat ">批量删除
                                </button>
                                <button type="button" class="btn btn-info btn-flat ">批量复制
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="row more_search_form" id="more_search_form">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Minimal</label>
                                <select class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true">
                                    <option selected="selected">Alabama</option>
                                    <option>Alaska</option>
                                    <option>California</option>
                                    <option>Delaware</option>
                                    <option>Tennessee</option>
                                    <option>Texas</option>
                                    <option>Washington</option>
                                </select><span class="select2 select2-container select2-container--default" dir="ltr" style="width: 100%;"><span class="selection"><span
                                class="select2-selection select2-selection--single" role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="0"
                                aria-labelledby="select2-fxz1-container"><span class="select2-selection__rendered" id="select2-fxz1-container"
                                                                               title="Alabama">Alabama</span><span class="select2-selection__arrow" role="presentation"><b
                                role="presentation"></b></span></span></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
                            </div>
                            <!-- /.form-group -->
                            <div class="form-group">
                                <label>Disabled</label>
                                <select class="form-control select2 select2-hidden-accessible" disabled="" style="width: 100%;" tabindex="-1" aria-hidden="true">
                                    <option selected="selected">Alabama</option>
                                    <option>Alaska</option>
                                    <option>California</option>
                                    <option>Delaware</option>
                                    <option>Tennessee</option>
                                    <option>Texas</option>
                                    <option>Washington</option>
                                </select><span class="select2 select2-container select2-container--default select2-container--disabled" dir="ltr" style="width: 100%;"><span
                                class="selection"><span class="select2-selection select2-selection--single" role="combobox" aria-haspopup="true" aria-expanded="false"
                                                        tabindex="-1" aria-labelledby="select2-6eop-container"><span class="select2-selection__rendered"
                                                                                                                     id="select2-6eop-container"
                                                                                                                     title="Alabama">Alabama</span><span
                                class="select2-selection__arrow" role="presentation"><b role="presentation"></b></span></span></span><span class="dropdown-wrapper"
                                                                                                                                           aria-hidden="true"></span></span>
                            </div>
                            <!-- /.form-group -->
                        </div>
                        <!-- /.col -->
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Multiple</label>
                                <select class="form-control select2 select2-hidden-accessible" multiple="" data-placeholder="Select a State" style="width: 100%;" tabindex="-1"
                                        aria-hidden="true">
                                    <option>Alabama</option>
                                    <option>Alaska</option>
                                    <option>California</option>
                                    <option>Delaware</option>
                                    <option>Tennessee</option>
                                    <option>Texas</option>
                                    <option>Washington</option>
                                </select><span class="select2 select2-container select2-container--default" dir="ltr" style="width: 100%;"><span class="selection"><span
                                class="select2-selection select2-selection--multiple" role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="-1"><ul
                                class="select2-selection__rendered"><li class="select2-search select2-search--inline"><input class="select2-search__field" type="search"
                                                                                                                             tabindex="0" autocomplete="off" autocorrect="off"
                                                                                                                             autocapitalize="none" spellcheck="false"
                                                                                                                             role="textbox" aria-autocomplete="list"
                                                                                                                             placeholder="Select a State"
                                                                                                                             style="width: 634.5px;"></li></ul></span></span><span
                                class="dropdown-wrapper" aria-hidden="true"></span></span>
                            </div>
                            <!-- /.form-group -->
                            <div class="form-group">
                                <label>Disabled Result</label>
                                <select class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true">
                                    <option selected="selected">Alabama</option>
                                    <option>Alaska</option>
                                    <option disabled="disabled">California (disabled)</option>
                                    <option>Delaware</option>
                                    <option>Tennessee</option>
                                    <option>Texas</option>
                                    <option>Washington</option>
                                </select><span class="select2 select2-container select2-container--default" dir="ltr" style="width: 100%;"><span class="selection"><span
                                class="select2-selection select2-selection--single" role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="0"
                                aria-labelledby="select2-vjof-container"><span class="select2-selection__rendered" id="select2-vjof-container"
                                                                               title="Alabama">Alabama</span><span class="select2-selection__arrow" role="presentation"><b
                                role="presentation"></b></span></span></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
                            </div>
                            <!-- /.form-group -->
                        </div>
                        <!-- /.col -->
                    </div>

                    <div class="row">
                        <div class="col-md-12 table-responsive">
                            <table id="fault_plan_table" class="table table-bordered table-striped text-nowrap">
                            </table>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>

        </div>
    </div>

</section>
<script src="${ctx }/static/js/module/inspect/fault/plan/list.js"></script>