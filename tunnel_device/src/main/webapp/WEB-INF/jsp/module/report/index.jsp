<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/WEB-INF/jsp/commons/common_head.jsp" %>
    <link rel="stylesheet" href="${ctx }/static/css/report.css"/>
    <%@ include file="/WEB-INF/jsp/commons/common_head2.jsp" %>
</head>
<body class="hold-transition skin-blue fixed sidebar-mini">
<style>
    .wrapper .content-wrapper, .main-footer {
        margin-left: 0px;
    }

</style>
<div class="wrapper">
    <%@ include file="/WEB-INF/jsp/commons/layout_header1.jsp" %>
    <%@ include file="/WEB-INF/jsp/commons/layout_header2.jsp" %>
    <input value='${lineCode}' type="hidden" id="lineCode"/>
    <div class="content-wrapper">
        <section class="content-header">
            <h1> 报表统计
                <small>周、月、年报统计</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 报表统计</a></li>
                <li class="active">条线统计</li>
            </ol>
        </section>
        <section class="content container-fluid" id="content_wrapper_content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">区间健康度</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div class="row">
                                <div class="title col-md-12">
                                    <div class="tit-left col-md-2 col-xs-12">
                                        <div class="jiao1">
                                            <p class="jiao-top">
                                                <img src="${ctx }/static/images/report/sj_s.png" alt=""/>
                                            </p>
                                        </div>
                                        <div class="page_line">
                                            <div class="page_chLine">

                                            </div>
                                        </div>
                                        <div class="jiao2">
                                            <p class="jiao-bottom">
                                                <img src="${ctx }/static/images/report/sj_x.png" alt=""/>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="col-md-10 col-xs-12 tit-right-parent">
                                        <div class="tit-right text-center">
                                            <div class="position-p position-p1">
                                                <p>上行</p>
                                                <p>下行</p>
                                            </div>
                                            <div class="line-across1 ">
                                            </div>
                                            <div class="position-p position-p2">
                                                <p>下行</p>
                                                <p>上行</p>
                                            </div>
                                            <div class="line-across2 ">
                                            </div>
                                            <div class="position-p position-p3">
                                                <p>上行</p>
                                                <p>下行</p>
                                            </div>
                                            <div class="line-across3 ">
                                            </div>
                                            <div class="position-right">
                                                <div class=" grade"><p>健康度 </p></div>
                                                <div class="grade grade1" style="background-color: #e72529"><p>4-5</p></div>
                                                <div class="grade grade2" style="background-color: #f6cd19"><p>2-4</p></div>
                                                <div class="grade grade3" style="background-color: #46a218"><p>0-2</p></div>
                                                <div class="grade grade4" style="background-color: #7c7b7c"><p>桥梁/高架</p></div>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                            </div>
                            <div class="row">
                                <div class="count col-md-12" id="myTab">
                                    <div class="bao-wrapper">
                                        <div class="count-tit">
                                            <div class="bao-btn bao-t" data-type="week">
                                                <p class="bao-bgweek bao-bgweek-t"></p>
                                                <p class="bao-font">周报</p>
                                            </div>
                                            <div class="bao-btn" data-type="month">
                                                <p class="bao-bgmonth"></p>
                                                <p class="bao-font">月报</p>
                                            </div>
                                            <div class="bao-btn" data-type="year">
                                                <p class="bao-bgyear"></p>
                                                <p class="bao-font">年报</p>
                                            </div>
                                        </div>
                                        <div class="export">导出</div>
                                    </div>
                                </div>

                            </div>
                            <div class="row count-bane">
                                <div class="week">
                                    <div class="bane-left col-md-6">
                                        <div class="bane-left-z bane-left-bao bane-left-active">
                                            <div class="bane-ti1">条线病害分析</div>
                                            <div class="bane-menu">

                                                <table class="table table-bordered table-responsive no-padding  table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>线路</th>
                                                        <th>等级</th>
                                                        <th>本周新增数</th>
                                                        <th>本周消除数</th>
                                                        <th>本周结存数</th>
                                                    </tr>

                                                    </thead>
                                                    <tbody class="blfenix">
                                                    <tr>
                                                        <td rowspan="7">网络剩余</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>


                                            <div class="bane-ti1">本周新增数</div>
                                            <div class="bane-menu2 bane-menu">
                                                <table class="table table-bordered">
                                                    <thead>
                                                    <tr>
                                                        <th>线路</th>
                                                        <th>区间/上下行</th>
                                                        <th>病害环号/里程</th>
                                                        <th>病害类型</th>
                                                        <th>病害等级</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody class="addBH">

                                                    </tbody>
                                                </table>


                                            </div>
                                            <div class="bane-ti1">本周消除数</div>
                                            <div class="bane-menu2 bane-menu">
                                                <table class="table table-bordered">
                                                    <thead>
                                                    <tr>
                                                        <th>线路</th>
                                                        <th>区间/上下行</th>
                                                        <th>病害环号/里程</th>
                                                        <th>病害类型</th>
                                                        <th>病害等级</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody class="weekXC">

                                                    </tbody>
                                                </table>


                                            </div>
                                            <div class="bane-ti1">本周结存数</div>
                                            <div class="bane-menu2 bane-menu">
                                                <table class="table table-bordered">
                                                    <thead>
                                                    <tr>
                                                        <th>线路</th>
                                                        <th>区间/上下行</th>
                                                        <th>病害环号/里程</th>
                                                        <th>病害类型</th>
                                                        <th>病害等级</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody class="weekAll">

                                                    </tbody>
                                                </table>


                                            </div>
                                        </div>
                                    </div>

                                    <div class="bane-rig col-md-6">
                                        <div id="echarts-pie-week"></div>
                                        <div id="echarts-bar-week"></div>
                                    </div>
                                </div>
                                <div class="month" style="display: none;">
                                    <div class="bane-left col-md-6">
                                        <div class="bane-left-y bane-left-bao">
                                            <div class="bane-ti1">条线病害分析</div>
                                            <div class="bane-menu">
                                                <table class="table table-bordered table-responsive no-padding  table-hover">

                                                <thead>
                                                    <tr>
                                                        <th>线路</th>
                                                        <th>等级</th>
                                                        <th>本月新增数</th>
                                                        <th>本月消除数</th>
                                                        <th>本月结存数</th>
                                                    </tr>


                                                    </thead>
                                                    <tbody class="yueTl">
                                                    <tr>
                                                        <td rowspan="7">网络剩余</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="bane-ti1">本月新增数</div>
                                            <div class="bane-menu2 bane-menu">
                                                <table class="table table-bordered">
                                                    <thead>
                                                    <tr>
                                                        <th>线路</th>
                                                        <th>区间/上下行</th>
                                                        <th>病害环号/里程</th>
                                                        <th>病害类型</th>
                                                        <th>病害等级</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody class="yueXZ">

                                                    </tbody>
                                                </table>


                                            </div>
                                            <div class="bane-ti1">本月消除数</div>
                                            <div class="bane-menu2 bane-menu">
                                                <table class="table table-bordered">
                                                    <thead>
                                                    <tr>
                                                        <th>线路</th>
                                                        <th>区间/上下行</th>
                                                        <th>病害环号/里程</th>
                                                        <th>病害类型</th>
                                                        <th>病害等级</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody class="monthDel">

                                                    </tbody>
                                                </table>


                                            </div>
                                            <div class="bane-ti1">本月结存数</div>
                                            <div class="bane-menu2 bane-menu">
                                                <table class="table table-bordered">
                                                    <thead>
                                                    <tr>
                                                        <th>线路</th>
                                                        <th>区间/上下行</th>
                                                        <th>病害环号/里程</th>
                                                        <th>病害类型</th>
                                                        <th>病害等级</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody class="monthJc">

                                                    </tbody>
                                                </table>


                                            </div>
                                        </div>
                                    </div>
                                    <div class="bane-rig col-md-6">
                                        <div id="echarts-pie-month"></div>
                                        <div id="echarts-bar-month"></div>
                                    </div>
                                </div>
                                <div class="year" style="display: none;">
                                    <div class="bane-left col-md-6">
                                        <div class="bane-left-n bane-left-bao">
                                            <div class="bane-ti1">条线病害分析</div>
                                            <div class="bane-menu">
                                                <table class="table table-bordered table-responsive no-padding  table-hover">

                                                <thead>
                                                    <tr>
                                                        <th>线路</th>
                                                        <th>等级</th>
                                                        <th>本年新增数</th>
                                                        <th>本年消除数</th>
                                                        <th>本年结存数</th>
                                                    </tr>


                                                    </thead>
                                                    <tbody class="yearTl">
                                                    <tr>
                                                        <td rowspan="7">网络剩余</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="bane-ti1">本年新增数</div>
                                            <div class="bane-menu2 bane-menu">
                                                <table class="table table-bordered">
                                                    <thead>
                                                    <tr>
                                                        <th>线路</th>
                                                        <th>区间/上下行</th>
                                                        <th>病害环号/里程</th>
                                                        <th>病害类型</th>
                                                        <th>病害等级</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody class="yearXZ">

                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="bane-ti1">本年消除数</div>
                                            <div class="bane-menu2 bane-menu">
                                                <table class="table table-bordered">
                                                    <thead>
                                                    <tr>
                                                        <th>线路</th>
                                                        <th>区间/上下行</th>
                                                        <th>病害环号/里程</th>
                                                        <th>病害类型</th>
                                                        <th>病害等级</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody class="yearDel">

                                                    </tbody>
                                                </table>


                                            </div>
                                            <div class="bane-ti1">本年结存数</div>
                                            <div class="bane-menu2 bane-menu">
                                                <table class="table table-bordered">
                                                    <thead>
                                                    <tr>
                                                        <th>线路</th>
                                                        <th>区间/上下行</th>
                                                        <th>病害环号/里程</th>
                                                        <th>病害类型</th>
                                                        <th>病害等级</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody class="yearJc">

                                                    </tbody>
                                                </table>


                                            </div>
                                        </div>
                                    </div>

                                    <div class="bane-rig col-md-6">
                                        <div id="echarts-pie-year"></div>
                                        <div id="echarts-bar-year"></div>
                                    </div>
                                </div>

                            </div>

                        </div>

                    </div>
                    <!-- /.box-body -->
                </div>
            </div>
        </section>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/commons/common_script.jsp" %>
<%--<script src="${ctx }/static/js/lib/jqTreeGrid/jquery.treegrid.extension.js"></script>--%>
<%@ include file="/WEB-INF/jsp/commons/common_script2.jsp" %>
<script src="${ctx }/webjars/echarts/4.2.1/dist/echarts.min.js"></script>
<script src="${ctx }/static/js/module/report/index.js"></script>
<script>

    var module = 'report';
    // activeHeadMenu('/' + module);
    initHeadMenu("/"+module);
    ;!$(function () {
        modalControl();
    });
</script>
<!-- 模态框（Modal） -->
<div class="modal fade" id="commonModal" tabindex="-1" role="dialog" aria-labelledby="commonModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" id="commonModalBody">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>