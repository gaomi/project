<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="wait-load" style="display: block">
    正在加载请稍等......
</div>
<div class="monitor-content" style="display: none">
    <ul id="monitor_tab" class="nav nav-tabs" style="display: none">
        <li class="active">
            <a href="#monitor_echarts" data-toggle="tab">
            </a>
        </li>
        <li><a href="#monitor_details" data-toggle="tab"></a></li>
    </ul>
    <div class="row">
        <div class="col-xs-12">
            <div class="tab-content">
                <div class="tab-pane fade in active" id="monitor_echarts">
                    <div class="tab_title margin_top">
                        <div id="select_complete" onclick="showPopover(this,'complete')"
                             class="com_bt aqbhq_unselect_bt bt_change unmargin">数据设置
                        </div>
                        <div class="popover fade bottom in complete_pop" id="complete" style="width: 520px">
                            <div class="arrow"></div>
                            <div class="popover-title popover_title_padding">
                                <div class="checkbox" style="margin-top:0;margin-bottom:0px;"></div>
                                <button type="button" class="close" onclick="hide('complete')">
                                    <span aria-hidden="true">&times;</span>
                                    <span class="sr-only">Close</span>
                                </button>
                            </div>
                            <div class="popover-content">
                                <ul class="list-inline" id="echarts_complete">
                                    <table style="width: 100%">
                                        <tr style="display: none">
                                            <td class="col_td" colspan="2">沉降收敛</td>
                                        </tr>
                                        <tr>
                                            <td class="first_title" rowspan="4">沉<br/>降</td>
                                            <td class="first_td">初始</td>
                                            <td class="second_td">
                                                <select class="table_select" id="cj_select">
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="first_td">本次<br/>年度</td>
                                            <td class="second_td">
                                                <div class="tb_scroll" data-target="sink"></div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="first_td">累计</td>
                                            <td class="second_td unsingle">
                                                <div class="tb_scroll" data-target="panel">
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="col_td">曲率<br/>坡度</td>
                                            <td class="second_td">
                                                <div id="showQl" class="select_type"
                                                     onclick="showQlEchar('QL',true)">
                                                    <input type="checkbox" checked="checked"/>
                                                    <div>查看曲率</div>
                                                </div>
                                                <div id="showPd" class="select_type "
                                                     onclick="showQlEchar('PD',true)">
                                                    <input type="checkbox" checked="checked"/>
                                                    <div>查看坡度</div>
                                                </div>
                                                <div id="showSL" class="select_type "
                                                     onclick="showQlEchar('SL',true)">
                                                    <input type="checkbox" checked="checked"/>
                                                    <div>查看速率</div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="first_title" rowspan="4">收<br/>敛</td>
                                            <td class="first_td">初始</td>
                                            <td class="second_td">
                                                <select class="table_select" id="sl_select">
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="first_td">设计<br/>差</td>
                                            <td class="second_td unsingle">
                                                <div class="tb_scroll" data-target="diff_val"></div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="first_td">本次</td>
                                            <td class="second_td unsingle">
                                                <div class="tb_scroll" data-target="now_astringe"></div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="first_td">累计</td>
                                            <td class="second_td unsingle">
                                                <div class="tb_scroll" data-target="astringe"></div>
                                            </td>
                                        </tr>
                                    </table>
                                </ul>
                            </div>
                        </div>

                        <div id="select_error" onclick="showPopover(this,'error')"
                             class="com_bt aqbhq_unselect_bt unmargin">异常值设置
                        </div>
                        <div class="popover fade bottom in" id="error" style="display:none;opacity:1;width:300px">
                            <div class="arrow"></div>
                            <div class="popover-title popover_title_padding">
                                <div class="checkbox" style="margin-top:0;margin-bottom:0px;"></div>
                                <button type="button" class="close" onclick="hide('error')">
                                    <span aria-hidden="true">&times;</span>
                                    <span class="sr-only">Close</span>
                                </button>
                            </div>
                            <div class="popover-content">
                                <ul class="list-inline" id="echarts_error">
                                    <table style="width: 100%">
                                        <tr>
                                            <td class="td_with " rowspan="2">累计沉降：</td>
                                            <td class="second_td">
                                                <input type="text" class="table_input check-input" dataType="number"
                                                       data-target="[沉][累]" data-type="MIN" placeholder="最小值，默认为0.00"/>
                                                <label>最小值(mm)</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="second_td">
                                                <input type="text" class="table_input check-input" dataType="number"
                                                       data-target="[沉][累]" data-type="MAX" placeholder="最大值，默认为0.00"/>
                                                <label>最大值(mm)</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td_with " rowspan="2">本次沉降：</td>
                                            <td class="second_td">
                                                <input type="text" class="table_input check-input" dataType="number"
                                                       data-target="[沉][本]" data-type="MIN" placeholder="最小值，默认为0.00"/>
                                                <label>最小值(mm)</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="second_td">
                                                <input type="text" class="table_input check-input" dataType="number"
                                                       data-target="[沉][本]" data-type="MAX" placeholder="最大值，默认为0.00"/>
                                                <label>最大值(mm)</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td_with " rowspan="2">年度沉降：</td>
                                            <td class="second_td">
                                                <input type="text" class="table_input check-input" dataType="number"
                                                       data-target="[沉][年]" data-type="MIN" placeholder="最小值，默认为0.00"/>
                                                <label>最小值(mm)</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="second_td">
                                                <input type="text" class="table_input check-input" dataType="number"
                                                       data-target="[沉][年]" data-type="MAX" placeholder="最大值，默认为0.00"/>
                                                <label>最大值(mm)</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td_with ">曲率半径：</td>
                                            <td class="second_td">
                                                <input type="text" class="table_input check-input" dataType="number"
                                                       data-target="QL" data-type="MIN" placeholder="请输入小数，默认为0.00"/>
                                                <label>最小值(m)</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td_with" rowspan="2">沉降坡度：</td>
                                            <td class="second_td">
                                                <input type="text" class="table_input check-input" dataType="number"
                                                       data-target="PD" data-type="MIN" placeholder="最小值，默认为0.00"/>
                                                <label>最小值(mm)</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="second_td">
                                                <input type="text" class="table_input check-input" dataType="number"
                                                       data-target="PD" data-type="MAX" placeholder="最大值，默认为0.00"/>
                                                <label>最大值(mm)</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td_with" rowspan="2">与设计差：</td>
                                            <td class="second_td">
                                                <input type="text" class="table_input check-input" dataType="number"
                                                       data-target="[收][差]" data-type="MIN" placeholder="最小值，默认为0.00"/>
                                                <label>最小值(cm)</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="second_td">
                                                <input type="text" class="table_input check-input" dataType="number"
                                                       data-target="[收][差]" data-type="MAX" placeholder="最大值，默认为0.00"/>
                                                <label>最大值(cm)</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td_with" rowspan="2"> 累计收敛：</td>
                                            <td class="second_td">
                                                <input type="text" class="table_input check-input" dataType="number"
                                                       data-target="[收][累]" data-type="MIN" placeholder="最小值，默认为0.00"/>
                                                <label>最小值(mm)</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="second_td">
                                                <input type="text" class="table_input check-input" dataType="number"
                                                       data-target="[收][累]" data-type="MAX" placeholder="最大值，默认为0.00"/>
                                                <label>最大值(mm)</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="td_with" rowspan="2"> 本次收敛：</td>
                                            <td class="second_td">
                                                <input type="text" class="table_input check-input" dataType="number"
                                                       data-target="[收][本]" data-type="MIN" placeholder="最小值，默认为0.00"/>
                                                <label>最小值(mm)</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="second_td">
                                                <input type="text" class="table_input check-input" dataType="number"
                                                       data-target="[收][本]" data-type="MAX" placeholder="最大值，默认为0.00"/>
                                                <label>最大值(mm)</label>
                                            </td>
                                        </tr>
                                    </table>
                                    <div class="error_option">
                                        <button onclick="initError()">保存</button>
                                        <button onclick="hide('error')">取消</button>
                                    </div>

                                </ul>
                            </div>
                        </div>

                        <div id="select_cjsl_box" onclick="showPopover(this,'cjsl_box')"
                             class="com_bt aqbhq_unselect_bt unmargin">收敛/沉降
                        </div>
                        <div class="popover fade bottom in margin_b" id="cjsl_box"
                             style="display:none;opacity:1;width:215px">
                            <div class="arrow"></div>
                            <div class="popover-title" style="height:35px;">
                                <div class="checkbox" style="margin-top:0;margin-bottom:0px;"></div>
                                <button type="button" class="close" onclick="hide('cjsl_box')">
                                    <span aria-hidden="true">&times;</span>
                                    <span class="sr-only">Close</span>
                                </button>
                            </div>
                            <div class="popover-content">
                                <div class="list-inline" data-target="cjsl_box">
                                    <div class="checkechar_box" onclick="showEchar(this,'CJ')"><input
                                        type="checkbox"/>
                                        <div>只看沉降</div>
                                    </div>
                                    <div class="checkechar_box" onclick="showEchar(this,'SL')"><input
                                        type="checkbox"/>
                                        <div>只看收敛</div>
                                    </div>
                                    <div class="checkechar_box" onclick="showEchar(this,'ALL')"><input
                                        type="checkbox"
                                        checked="checked"/>
                                        <div>沉降收敛</div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="aqbhq_type" style="display: none">
                            <div id="select_diff_val" onclick="showPopover(this,'diff_val')"
                                 class="com_bt aqbhq_unselect_bt bt_change unmargin">与设计差值
                            </div>
                            <div class="popover fade bottom in" id="diff_val"
                                 style="display:none;opacity:1;width:175px">
                                <div class="arrow"></div>
                                <div class="popover-title" style="height:35px;">
                                    <div class="checkbox" style="margin-top:0;margin-bottom:0px;"></div>
                                    <button type="button" class="close" onclick="hide('diff_val')">
                                        <span aria-hidden="true">&times;</span>
                                        <span class="sr-only">Close</span>
                                    </button>
                                </div>
                                <div class="popover-content">
                                    <div class="list-inline" data-target="diff_val">
                                    </div>
                                </div>
                            </div>

                            <div id="select_now_astringe" onclick="showPopover(this,'now_astringe')"
                                 class="com_bt aqbhq_unselect_bt bt_change unmargin">本次收敛
                            </div>
                            <div class="popover fade bottom in" id="now_astringe"
                                 style="display:none;opacity:1;width:175px">
                                <div class="arrow"></div>
                                <div class="popover-title" style="height:35px;">
                                    <div class="checkbox" style="margin-top:0;margin-bottom:0px;"></div>
                                    <button type="button" class="close" onclick="hide('now_astringe')">
                                        <span aria-hidden="true">&times;</span>
                                        <span class="sr-only">Close</span>
                                    </button>
                                </div>
                                <div class="popover-content">
                                    <div class="list-inline" data-target="now_astringe">
                                    </div>
                                </div>
                            </div>

                            <div id="select_astringe" onclick="showPopover(this,'astringe')"
                                 class="com_bt bt_change aqbhq_unselect_bt">累计收敛
                            </div>
                            <div class="popover fade bottom in" id="astringe"
                                 style="display:none;opacity:1;width:175px">
                                <div class="arrow"></div>
                                <div class="popover-title" style="height:35px;">
                                    <div class="checkbox" style="margin-top:0;margin-bottom:0px;"></div>
                                    <button type="button" class="close" onclick="hide('astringe')">
                                        <span aria-hidden="true">&times;</span>
                                        <span class="sr-only">Close</span>
                                    </button>
                                </div>
                                <div class="popover-content">
                                    <div class="list-inline" data-target="astringe">
                                    </div>
                                </div>
                            </div>
                            <div id="select_sink" onclick="showPopover(this,'sink')"
                                 class="com_bt bt_change aqbhq_unselect_bt">
                                本次沉降
                            </div>
                            <div class="popover fade bottom in" id="sink"
                                 style="display:none;opacity:1;width:175px">
                                <div class="arrow"></div>
                                <div class="popover-title" style="height:35px;">
                                    <div class="checkbox" style="margin-top:0;margin-bottom:0px;"></div>
                                    <button type="button" class="close" onclick="hide('sink')">
                                        <span aria-hidden="true">&times;</span>
                                        <span class="sr-only">Close</span>
                                    </button>
                                </div>
                                <div class="popover-content">
                                    <div class="list-inline" data-target="sink">
                                    </div>
                                </div>
                            </div>

                            <div id="select_panel" onclick="showPopover(this,'panel')"
                                 class="com_bt bt_change aqbhq_unselect_bt">
                                累计沉降
                            </div>
                            <div class="popover fade bottom in" id="panel"
                                 style="display:none;opacity:1;width:175px">
                                <div class="arrow"></div>
                                <div class="popover-title" style="height:35px;">
                                    <div class="checkbox" style="margin-top:0;margin-bottom:0px;"></div>
                                    <button type="button" class="close" onclick="hide('panel')">
                                        <span aria-hidden="true">&times;</span>
                                        <span class="sr-only">Close</span>
                                    </button>
                                </div>
                                <div class="popover-content">
                                    <div class="list-inline" data-target="panel">
                                        <p style="font-size: 10px">2019-06</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="monitor_scroll margin_top mrakMonitorBox">
                        <div id="echarts2" class="echarts_tab1"></div>
                        <div id="echarts5" class="echarts_tab1" style="display:block"></div>
                        <div id="echarts3" class="echarts_tab1" style="display:block"></div>
                        <div id="echarts4" class="echarts_tab1" style="display:block"></div>
                        <div id="echarts6" class="echarts_tab1" style="display:block"></div>
                    </div>
                </div>
                <div class="tab-pane fade" id="monitor_details">

                    <div class="tab_title margin_top">
                        <div onclick="blackMonitor()" class="com_bt aqbhq_select_bt select_main_bt">返回</div>
                        <select class="sele_input" id="detail_start">
                        </select>
                        <select class="sele_input" id="detail_end">
                        </select>
                        <div type="button" class="tbale_aqbhq_bt remst_bt" onclick="queryDetails()"
                             style="float: left;margin-left: 10px;font-size: 12px">搜索
                        </div>
                    </div>
                    <div class="detail_time">
                        <div class="panel detail_checkbox">
                            <div class="point_title">点号</div>
                            <div id="detail_checkbox" data-target="detail_checkbox"></div>
                        </div>
                        <div id="detail_echar"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${ctx }/static/js/module/segment/tabs/monitor.js"></script>
</div>