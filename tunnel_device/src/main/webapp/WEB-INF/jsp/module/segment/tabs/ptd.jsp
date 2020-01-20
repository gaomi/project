<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="row">
    <div class="col-xs-12">
        <div class="box">

            <!-- /.box-header -->
            <ul id="myPtdTab" class="nav nav-tabs" style="display: none">
                <li class="active">
                    <a href="#ptd_list" data-toggle="ptd_tab">
                    </a>
                </li>
                <li><a href="#ptd_details" data-toggle="ptd_tab"></a></li>
                <li><a href="#ptd_time" data-toggle="ptd_tab"></a></li>
            </ul>
            <div class="box-header">
            </div>
            <div class="box-body">
                <div class="tab-content">
                    <div class="tab-pane fade in active">
                        <!-- 数据表 ; word-wrap:break-all; -->
                        <table id="ptd_list" style="word-break:break-all">
                        </table>
                    </div>
                    <div class="tab-pane fade" id="ptd_details">
                        <div class="tab_title margin_top">
                            <div onclick="toPtdList()" class="com_bt aqbhq_select_bt select_main_bt">返回列表</div>
                            <!--<button type="button" class="btn btn-default"-->
                            <!--onclick="toPtdList()">返回列表-->
                            <!--</button>-->
                            <div id="select_ptd_complete" onclick="showPopover(this,'ptd_complete')"
                                 class="com_bt aqbhq_unselect_bt unmargin">数据设置
                            </div>
                            <div class="popover fade bottom in complete_pop" id="ptd_complete" style="width: 520px">
                                <div class="arrow"></div>
                                <div class="popover-title popover_title_padding">
                                    <div class="checkbox" style="margin-top:0;margin-bottom:0px;"></div>
                                    <button type="button" class="close" onclick="hide('ptd_complete')">
                                        <span aria-hidden="true">&times;</span>
                                        <span class="sr-only">Close</span>
                                    </button>
                                </div>
                                <div class="popover-content">
                                    <ul class="list-inline" id="ptd_echarts_complete">
                                        <table style="width: 100%">
                                            <tr style="display: none">
                                                <td class="first_title" rowspan="4">沉<br/>降</td>
                                                <td class="col_td">曲率<br/>坡度</td>
                                                <td class="second_td">
                                                    <div id="ptd_showQl" class="select_type" onclick="ptdShowQlEchar('QL')"><input type="checkbox" checked="checked"/>
                                                        <div>查看曲率</div>
                                                    </div>
                                                    <div id="ptd_showPd" class="select_type " onclick="ptdShowQlEchar('PD')"><input type="checkbox" checked="checked"/>
                                                        <div>查看坡度</div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="first_title" rowspan="3">沉<br/>降</td>
                                                <td class="first_td">初始</td>
                                                <td class="second_td">
                                                    <select class="table_select" id="ptd_cj_select">
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="first_td">本次<br/>年度</td>
                                                <td class="second_td">
                                                    <div class="tb_scroll" data-target="ptd_sink"></div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="first_td">累计</td>
                                                <td class="second_td unsingle">
                                                    <div class="tb_scroll" data-target="ptd_panel">
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="first_title" rowspan="4">收<br/>敛</td>
                                                <td class="first_td">初始</td>
                                                <td class="second_td">
                                                    <select class="table_select" id="ptd_sl_select">
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="first_td">设计<br/>差</td>
                                                <td class="second_td unsingle">
                                                    <div class="tb_scroll" data-target="ptd_diff_val"></div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="first_td">本次</td>
                                                <td class="second_td unsingle">
                                                    <div class="tb_scroll" data-target="ptd_now_astringe"></div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="first_td">累计</td>
                                                <td class="second_td unsingle">
                                                    <div class="tb_scroll" data-target="ptd_astringe"></div>
                                                </td>
                                            </tr>
                                        </table>
                                    </ul>
                                </div>
                            </div>

                            <div id="select_ptd_cjsl_box" onclick="showPopover(this,'ptd_cjsl_box')"
                                 class="com_bt aqbhq_unselect_bt unmargin">收敛/沉降
                            </div>
                            <div class="popover fade bottom in margin_b" id="ptd_cjsl_box"
                                 style="display:none;opacity:1;width:215px">
                                <div class="arrow"></div>
                                <div class="popover-title" style="height:35px;">
                                    <div class="checkbox" style="margin-top:0;margin-bottom:0px;"></div>
                                    <!--<label><input type="checkbox" onchange="CheckAll(this,'diff_val')"/>全选</label>-->
                                    <button type="button" class="close" onclick="hide('ptd_cjsl_box')">
                                        <span aria-hidden="true">&times;</span>
                                        <span class="sr-only">Close</span>
                                    </button>
                                </div>
                                <div class="popover-content">
                                    <div class="list-inline" data-target="ptd_cjsl_box">
                                        <div class="checkechar_box" onclick="ptdShowEchar(this,'CJ')"><input type="checkbox"/>
                                            <div>只看沉降</div>
                                        </div>
                                        <div class="checkechar_box" onclick="ptdShowEchar(this,'SL')"><input type="checkbox"/>
                                            <div>只看收敛</div>
                                        </div>
                                        <div class="checkechar_box" onclick="ptdShowEchar(this,'ALL')"><input type="checkbox" checked="checked"/>
                                            <div>沉降收敛</div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="monitor_scroll mrakPtdBox">
                            <div id="ptd_echarts2" class="echarts_tab1"></div>
                            <div id="ptd_echarts5" class="echarts_tab1" style="display:block"></div>
                            <div id="ptd_echarts3" class="echarts_tab1" style="display:none"></div>
                            <div id="ptd_echarts4" class="echarts_tab1" style="display:none"></div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="ptd_time">
                        <div class="tab_title margin_top">
                            <div onclick="toPtd()" class="com_bt aqbhq_select_bt select_main_bt">返回</div>
                            <select class="sele_input" id="ptd_detail_start">
                            </select>
                            <select class="sele_input" id="ptd_detail_end">
                            </select>
                            <div type="button" class="tbale_aqbhq_bt remst_bt" onclick="queryPtdDetails()"
                                 style="float: left;margin-left: 10px;font-size: 12px">搜索
                            </div>
                            <!--<input class="form-control sele_input" placeholder="结束时间"/>-->
                        </div>
                        <div class="detail_time">
                            <div class="panel detail_checkbox">
                                <div class="point_title">点号</div>
                                <div id="ptd_detail_checkbox" data-target="ptd_detail_checkbox"></div>
                            </div>
                            <div id="ptd_detail_echar"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.box-body -->
        </div>
        <!-- /.box -->
    </div>
    <!-- /.col -->
    <script src="${ctx }/static/js/module/segment/tabs/ptd.js"></script>
</div>