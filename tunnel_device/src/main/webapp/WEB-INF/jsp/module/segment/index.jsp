<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/WEB-INF/jsp/commons/common_head.jsp" %>
    <%@ include file="/WEB-INF/jsp/commons/common_head2.jsp" %>

    <link rel="stylesheet" href="${ctx }/static/css/segment.css"/>
</head>
<style>
    .wrapper .content-wrapper, .main-footer {
        margin-left: 0px;
    }
</style>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="wrapper">
    <!-- Main Header -->
    <%@ include file="/WEB-INF/jsp/commons/layout_header1.jsp" %>
    <%@ include file="/WEB-INF/jsp/commons/layout_header2.jsp" %>
    <div class="content-wrapper">
        <section class="content container-fluid" id="content_wrapper_content">
            <div class="model_max_with">
                <div id="segment_header" class="modal-header">
                    <div class="hearder_titlt_box">
                        <select class="sele_input" id="lineList">
                        </select>
                        <select class="sele_input" id="direction_select">
                            <option value="上行">上行</option>
                            <option value="下行">下行</option>
                        </select>
                        <select class="sele_input" style="width: 200px" id="segment_select">
                        </select>
                        <!--onkeyup="checkTest(this)"-->
                        <input id="start_licheng" class="sele_input check-input" dataType="number" type="text" value=""
                               placeholder="开始里程"/>
                        <div class="betw_lable">——</div>
                        <input id="end_licheng" class="sele_input check-input unmarginleft" dataType="number"
                               type="text" value=""
                               placeholder="结束里程"/>


                        <div class="select_main_bt" onclick="selectlic()">查询</div>
                    </div>
                </div>
                <div class="comp_echar">
                    <div class="main_echar_box">
                        <div class="white_background"></div>
                        <div class="show_map_main">
                            <div class="show_map_input">
                                <h4>显示环数</h4>
                                <select class="sele_input input_small_with" id="step_echar">
                                    <option value="50">50</option>
                                    <option value="100">100</option>
                                    <option value="-1" selected="selected">全部</option>
                                </select>
                            </div>
                            <div class="show_map_box"></div>
                        </div>
                        <div id="echarts1" class="main_echar"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="box">
                            <div class="nav-tabs-custom">
                                <ul id="main_ul" class="nav nav-tabs">
                                    <li class="active" data-type="gen"><a href="#tab_gen" data-toggle="tab">区间概况</a>
                                    </li>
                                    <li data-type="monitor"><a href="#tab_monitor" data-toggle="tab">监测数据</a></li>
                                    <li data-content="监护项目" data-type="custody" data-table="custody_table"><a
                                            href="#tab_custody" data-toggle="tab">监护项目</a></li>
                                    <li data-content="违规施工" data-type="violation" data-table="aqbhq_list"><a
                                            href="#tab_violation" data-toggle="tab">违规施工</a></li>
                                    <li data-content="土层信息" data-type="sandy" data-table="td_sandy"><a href="#tab_sandy"
                                                                                                       data-toggle="tab">土层信息</a>
                                    </li>
                                    <li data-content="大修信息" data-type="emphasis" data-table="emphasis_list"><a
                                            href="#tab_emphasis" data-toggle="tab">大修信息</a></li>
                                    <li data-content="病害信息" data-type="fault" data-table="dise_list"><a
                                            href="#tab_fault"
                                            data-toggle="tab">病害与维修</a>
                                    </li>
                                    <li data-type="ptd" data-table="ptd_list"><a href="#tab_ptd"
                                                                                 data-toggle="tab">旁通道</a></li>
                                    <li data-type="layer"><a href="#tab_layer" data-toggle="tab">地质剖面图</a></li>
                                    <li data-content="综合详情" data-type="zonghe"><a href="#tab_zonghe"
                                                                                  data-toggle="tab">综合详情</a></li>
                                </ul>
                                <div id="main_content" class="tab-content">
                                    <div class="tab-pane in active" id="tab_gen">
                                        <div class="survey-heard">
                                            <div class="survey-item">
                                                <h4>监护项目</h4>
                                                <span target-name="JHXM">0</span>
                                                <div class="detail-item">
                                                    <div class="item-flex">
                                                        <p>特级</p>
                                                        <span target-data="A" style="color:#e74c3c">3</span>
                                                    </div>
                                                    <div class="item-flex">
                                                        <p>一级</p>
                                                        <span target-data="B" style="color:#e7933c">1</span>
                                                    </div>
                                                    <div class="item-flex">
                                                        <p>其他</p>
                                                        <span target-data="C" style="color:#2d9a2a">0</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="survey-item">
                                                <h4>违规项目</h4>
                                                <span target-name="WGSG">0</span>
                                                <div class="detail-item">
                                                    <div class="item-flex">
                                                        <p>一级</p>
                                                        <span target-data="A" style="color:#e74c3c">3</span>
                                                    </div>
                                                    <div class="item-flex">
                                                        <p>二级</p>
                                                        <span target-data="B" style="color:#e7933c">1</span>
                                                    </div>
                                                    <div class="item-flex">
                                                        <p>其他</p>
                                                        <span target-data="C" style="color:#2d9a2a">0</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="survey-item">
                                                <h4>病害信息</h4>
                                                <span target-name="BHXX">0</span>
                                                <div class="detail-item">
                                                    <div class="item-flex">
                                                        <p>AA</p>
                                                        <span target-data="A" style="color:#e74c3c">3</span>
                                                    </div>
                                                    <div class="item-flex">
                                                        <p>A</p>
                                                        <span target-data="B" style="color:#e7933c">1</span>
                                                    </div>
                                                    <div class="item-flex">
                                                        <p>其他</p>
                                                        <span target-data="C" style="color:#2d9a2a">0</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="survey-body row">

                                            <div id="survey_info" class="col-xs-12 col-md-4">
                                            </div>
                                            <div id="survey_left" class="col-xs-12 col-md-4"></div>
                                            <div id="survey_right" class="col-xs-12 col-md-4"></div>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="tab_monitor"></div>
                                    <div class="tab-pane" id="tab_maintain"></div>
                                    <div class="tab-pane" id="tab_custody"></div>
                                    <div class="tab-pane" id="tab_violation"></div>
                                    <div class="tab-pane" id="tab_ptd"></div>
                                    <div class="tab-pane" id="tab_sandy"></div>
                                    <div class="tab-pane" id="tab_zonghe"></div>
                                    <div class="tab-pane" id="tab_layer"></div>
                                    <div class="tab-pane" id="tab_emphasis"></div>
                                    <div class="tab-pane" id="tab_dev"></div>
                                    <div class="tab-pane" id="tab_fault"></div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/commons/common_script.jsp" %>
<script src="${ctx }/webjars/bootstrap-table/1.14.2/dist/bootstrap-table.min.js"></script>
<!-- put your locale files after bootstrap-table.js -->
<script src="${ctx }/webjars/bootstrap-table/1.14.2/dist/locale/bootstrap-table-zh-CN.min.js"></script>
<%@ include file="/WEB-INF/jsp/commons/common_script2.jsp" %>
<script>
    //  loadSideMenu('/device');
    var module = 'segment';
    // activeHeadMenu('/' + module);
    initHeadMenu('/' + module);
    ;!$(function () {
        // /*左侧菜单收缩*/
        // $("#header-sidebar-toggle").click();
        modalControl();
    });
</script>
<script src="${ctx }/webjars/echarts/4.2.1/dist/echarts.min.js"></script>
<script src="${ctx }/static/js/module/segment/util/sectionDim.js"></script>
<script src="${ctx }/static/js/module/segment/index.js"></script>


<!-- 模态框（Modal） -->
<div class="modal fade" id="commonModal" tabindex="-1" role="dialog" aria-labelledby="commonModalLabel"
     aria-hidden="true">
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