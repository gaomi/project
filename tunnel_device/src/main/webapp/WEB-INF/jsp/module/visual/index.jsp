<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta content="webkit" name="renderer"/>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>隧道设备智能化管理平台</title>
    <link rel="stylesheet" href="${ctx }/static/css/visual.css"/>
    <link rel="shortcut icon" href="${ctx }/static/images/favicon.ico"/>
    <link rel="bookmark" href="${ctx }/static/images/favicon.ico"/>
    <link rel="stylesheet" href="${ctx }/webjars/swiper/4.5.0/dist/css/swiper.min.css"/>
</head>

<body>
<header id="header">
    <h3 class="header-title"></h3>
    <div class="header-info header-info-l"></div>
    <div class="header-info header-info-r"></div>
    <!--<div class="header-btn"><a href="javascript:void(0);" th:href="@{/}" data-url="">返回</a></div>-->
</header>
<div id="container">
    <div id="flexCon">
        <div class="flex-row">
            <div class="flex-cell flex-cell-l roll-info">
                <div class="chart-wrapper">
                    <div id="dynamic-info" class="">
                        <div class="dynamic-title"><p class="dynamic-icon-bg">动态信息</p></div>
                        <div class="chart-loader">
                            <div class="loader"></div>
                        </div>
                        <div class="flex-one-box" style="display:none">
                            <div class="text_scroller">
                                <div class="flex-one-title">新增病害信息（滚动信息）
                                    <!--<span class="befor-time">周期：一年</span>-->
                                </div>
                                <div class="flex-line_left"></div>
                                <div class="flex-line_right">
                                    <div class="dise_heard">
                                        <div class="AA_dise">
                                            <div class="AA_level">等级AA</div>
                                            <div class="AA_count"><span id="aa_count"></span>&nbsp;&nbsp;(个)</div>
                                        </div>
                                        <div class="A_dise">
                                            <div class="A_level">等级A</div>
                                            <div class="A_count"><span id="a_count"></span>&nbsp;&nbsp;(个)</div>
                                        </div>
                                    </div>
                                    <div class="swiper-container dise_info_cont">

                                        <div class="swiper-button-prev"></div>
                                        <div class="swiper-button-next swiper_button_next"></div>
                                        <div id="dise_info_cont" class="swiper-wrapper">
                                            <!--<div class="swiper-slide">-->
                                            <!--<div class="img_div">-->
                                            <!--<img src='http://10.0.19.125:9080/upload/QC/60f998b9-1b22-40ce-8641-833d08b0e1e6_1_20180928001300_WBGW1809A001458_0111100103000000000001_F.png' style="width: 100%"/>-->
                                            <!--&lt;!&ndash;<img  data-src='http://10.0.19.125:9080/upload/QC/60f998b9-1b22-40ce-8641-833d08b0e1e6_1_20180928001300_WBGW1809A001458_0111100103000000000001_F.png' class="swiper-lazy item_img"/>&ndash;&gt;-->
                                            <!--</div>-->
                                            <!--<div class="desc_div">-->
                                            <!--<div  class="dise_item_info">线路:&emsp;<span id="slide_line">7号线</span></div>-->
                                            <!--<div  class="dise_item_info">区间:&emsp;<span id="slide_qj">7号线</span></div>-->
                                            <!--<div  class="dise_item_info">类型:&emsp;<span id="slide_type">7号线</span></div>-->
                                            <!--<div  class="dise_item_info">等级:&emsp;<span id="slide_level">7号线</span></div>-->
                                            <!--</div>-->
                                            <!--</div>-->
                                        </div>
                                        <div class="swiper-pagination"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="text_scroller">
                                <div class="flex-one-title">新增维修信息（滚动信息）
                                </div>
                                <div class="flex-line_left"></div>
                                <div class="flex-line_right">
                                    <div class="header_li">
                                        <div>序号</div>
                                        <div>线路</div>
                                        <div>序号设备</div>
                                        <div>病害</div>
                                        <div>操作</div>
                                    </div>
                                    <div id="repair_scroll" class="scrollDiv">
                                        <ul id="ul_repair" class="big_scteen_tab">
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="flex-cell flex-cell-c map-info">
                <div class="chart-wrapper">
                    <div class="img_left"></div>
                    <div id="" class="chart-div">
                        <div class="content_body">
                            <div style="width: 100%;height: 100%;position: relative">
                                <div class="gis_heard_bt">
                                    <div class="change_gis_box">
                                        <div class="gis_title_bt gis_select" data-id="gis-bt1">健康度</div>
                                        <div class="gis_title_bt gis_unselect" data-id="gis-bt2">致命风险项</div>
                                    </div>
                                    <!--<div onclick="showDialog()"-->
                                    <!--style="width: 30px;height: 25px;border: 1px solid #045698;position: absolute;right: 30px;bottom: 0px"></div>-->
                                    <div onclick="showLintSta()"
                                         style="width:0.3rem;height: 0.25rem;border: 1px solid #045698;position: absolute;right:0.4rem;bottom: 0.1rem"></div>
                                </div>

                                <div class="content_id" data-cta-target=".js-dialog" id="content_id">
                                    <div class="chart-loader">
                                        <div class="loader"></div>
                                    </div>
                                    <div class="content_body mapDiv_StaCount" id="mapDiv" style="display:none">
                                        <!--<span id="modal-close-bt" class=" modal-close-btn" style="display: none"></span>-->
                                        <span id="modal-close-Station" class=" modal-close-btn" style="display: none"></span>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="img_right"></div>
                </div>
            </div>
            <div class="flex-cell flex-cell-r warn-info">
                <div class="chart-wrapper">
                    <div class="warning_title_bg">
                        <h3 class="chart-title">超标预警值（重点区段） </h3>
                    </div>

                    <!--<span class="warning_title"></span>-->

                    <div id="warning-echarts" class="chart-div">
                        <div class="chart-loader">
                            <div class="loader"></div>
                        </div>
                        <div class="e_warning" style="display: none">
                            <div class="check_item">
                                <div class="check_title">收敛</div>
                                <div class="echart_box">
                                    <!-- Swiper -->
                                    <div class="swiper_box swiper-container swiper-no-swiping">
                                        <div id="swiper_box" class="swiper-wrapper">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="check-item-bg"></div>
                            <div class="check_item check_item-cj">
                                <div class="check_title">沉降</div>
                                <div class="echart_box">
                                    <!-- Swiper -->
                                    <div class="swiper_box_2 swiper-container swiper-no-swiping">
                                        <div id="swiper_box_2" class="swiper-wrapper">
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="down-cell">
            <div class=" flex-cell-l monitor-info">
                <div class="chart-wrapper wrapper_padding">
                    <div class="monitor-title-bg">
                        <h3 class="div_title">&nbsp;监&nbsp;控</h3>
                    </div>

                    <div class="chart-div">
                        <div class="div_video">
                            <div class="video_show"></div>
                            <div class="video_bt">
                                <div class="item_video_bt video_up_bt"></div>
                                <div class="item_video_bt video_down_bt"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class=" flex-cell-c change-data-info">
                <div class="chart-wrapper">
                    <div id="dise_div">
                        <div class="chart-loader">
                            <div class="loader"></div>
                        </div>
                        <div class="dise_div" style="display: none">
                            <div class="dise_border">
                                <div class="change_bt_box">
                                    <div class="change_item_box">
                                        <div class="echar_title_bt echar_title_dise">累计值统计</div>
                                        <div class="echar_title_bt echar_rep_none">变化值统计</div>
                                    </div>
                                    <!--<span class="dise_zhouqi">周期：一年</span>-->
                                </div>
                                <div class="echar_cont">
                                    <div id="disease_echar" style="width: 100%;height: 100%;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class=" flex-cell-r">
                <div class="chart-wrapper line-padding">
                    <div id="line-echarts" class="top-data-info">
                        <!--雷达图-->
                        <div class="chart-loader">
                            <div class="loader"></div>
                        </div>
                        <div class="content_body" style="display: none">
                            <div class="line_title">
                                <div class="line-bt-left"></div>
                                <div class="line_item">
                                    <div class="line_box">
                                    </div>
                                </div>
                                <div class="line-bt-right"></div>
                            </div>
                            <div id="echar_line" class="line_echar"></div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<!--<div id="this_con" class="js-dialog  modal  dialog" style="text-align: center;">-->
<!--<span class="modal-close-btn" onclick="closeShowingModal(true);"></span>-->
<!--<h3>Do you think this is Awesome?</h3>-->
<!--<br/>-->
<!--<a onclick="closeShowingModal(true); return;" class="btn  btn&#45;&#45;blue">Yes</a>-->
<!--<a onclick="closeShowingModal(false); return;" class="btn  btn&#45;&#45;blue">No</a>-->
<!--</div>-->

<div class="" id="layer_StationModal" style="">
    <div class="layer_background"></div>
    <div class="layer_StaCount" id="mapDiv_big">
        <button class="layer_StaHide">X</button>
    </div>
</div>


</body>
<script src="${ctx }/webjars/echarts/4.2.1/dist/echarts.min.js"></script>
<script src="${ctx }/webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="${ctx }/webjars/swiper/4.5.0/dist/js/swiper.min.js"></script>
<script>var ctx = "${ctx }";</script>
<script src="${ctx }/static/js/module/visual/index.js"></script>
<%--<link rel="stylesheet" href="http://172.17.200.198/arcgis_js_api/library/3.22/esri/css/esri.css"/>--%>
<%--<script type="text/javascript" src="http://172.17.200.198/arcgis_js_api/library/3.22/init.js"></script>--%>
<link rel="stylesheet" href="http://10.0.112.71/tunnel_api/library/3.22/3.22/esri/css/esri.css"/>
<script type="text/javascript" src="http://10.0.112.71/tunnel_api/library/3.22/3.22/init.js"></script>
<%--<link rel="stylesheet" href="http://218.242.68.246/arcgis_js_api/library/3.22/3.22/esri/css/esri.css"/>--%>
<%--<script type="text/javascript" src="http://218.242.68.246/arcgis_js_api/library/3.22/3.22/init.js"></script>--%>
<%--<script type="text/javascript" src="http://localhost:8000/tunnel_device/static/js/lib/arcgis_js_api/library/3.22/init.js"></script>--%>
<script type="text/javascript" src="${ctx }/static/js/module/visual/mapHome.js"></script>
</html>