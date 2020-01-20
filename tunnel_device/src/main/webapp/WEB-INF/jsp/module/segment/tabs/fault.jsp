<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<link rel="stylesheet" href="${ctx}/webjars/swiper/4.5.0/dist/css/swiper.min.css"/>
<ul id="myFaultTab" class="nav nav-tabs" style="display: none">
    <li class="active">
        <a href="#fault_list" data-toggle="fault_tab">
        </a>
    </li>
    <li><a href="#fault_details" data-toggle="fault_tab"></a></li>
</ul>
<div class="row">
    <div class="col-xs-12">
        <!-- /数据表 -->
        <!-- /.box-header -->
        <div class="box-body">
            <div class="toolbar paoding-dg-toolbar">
                <div class="easyui-panel toolbar-north-panel tab_title">
                    <div class="toolbar_box_bt">
                        <ul id="dise_param">
                            <li id="dise_button" onclick="changTable('20')" data-id="20"
                                class="tbale_dise_bt dise_select_bt">
                                入库病害
                            </li>
                            <li id="maintain_button" onclick="changTable('60')" data-id="60"
                                class="tbale_dise_bt dise_unselect_bt">
                                已维修
                            </li>
                            <li>
                                <label>关键字</label>
                                <input id="diseKey" type="text" datatype="trim" class="li_select check-input"/>
                            </li>
                            <!--<li>-->
                            <!--<label>设备</label>-->
                            <!--<input type="deviceName" datatype="trim" class="li_select check-input"/>-->
                            <!--</li>-->
                            <li onclick="queryDise()" class="tbale_aqbhq_bt remst_bt">
                                查询
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div id="faultTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="fault_list">
                    <div class="monitor_scroll">
                        <!-- 数据表工具栏 -->
                        <!--<div  id="fault_bt" class="toolbar paoding-dg-toolbar" style="display: none">-->
                        <!--<div class="easyui-panel toolbar-north-panel tab_title">-->
                        <!--<button type="button" class="btn btn-default"-->
                        <!--onclick="toLvLiList()">查看病害履历-->
                        <!--</button>-->
                        <!--</div>-->
                        <!--</div>-->
                        <!-- /数据表工具栏 -->
                        <!-- 数据表 ; word-wrap:break-all; -->
                        <div class="dise_box">
                            <div class="dise_swiper_tab">
                                <table id="dise_list" style="word-break:break-all"></table>
                            </div>
                        </div>
                        <div id="dise-desc" class="panel panel-default" style="display: none">
                            <div class="panel-heading" style="text-align: center">
                                <label>病害详细信息</label>
                            </div>

                            <div id="dise_panel_body" class="panel-body col-md-12"><!--dise_panel_body-->
                                <div class="row">
                                    <div class="col-xs-12 col-md-3 item-test ">
                                        <div id="dise_img" class="swiper-container ">
                                            <div class="swiper-button-prev"></div>
                                            <div class="swiper-button-next"></div>
                                            <div id="dise_cont" class="swiper-wrapper">
                                            </div>
                                            <div class="swiper-pagination"></div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-3 item-test contest-one fault-contest">
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">线路：</div>
                                            <div name="LINE_ID" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">上下行：</div>
                                            <div name="DIRECTION" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">病害等级：</div>
                                            <div name="FAULT_LEVEL" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">病害状态：</div>
                                            <div name="STATUS" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">病害类型：</div>
                                            <div name="FAULT_TYPE" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">大类名称：</div>
                                            <div name="BIG_TYPE_NAME" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">小类名称：</div>
                                            <div name="SMALL_TYPE_NAME" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">详细位置：</div>
                                            <div name="DETAIL_LOC" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">病害位置：</div>
                                            <div name="FAULT_UNIT" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">是否修复：</div>
                                            <div name="IS_FINISH" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">病害内码：</div>
                                            <div name="INTERNAL_CODE" class="col-xs-8 col-desc"></div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-3 item-test  contest-two fault-contest">
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">病害描述：</div>
                                            <div name="FAULT_DESC" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">病害类型码：</div>
                                            <div name="FAULT_NO" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">病害类型码：</div>
                                            <div name="FAULT_ID" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label" style="padding: 0;">计划编制人姓名：</div>
                                            <div name="PLANNER" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">处理人：</div>
                                            <div name="DEAL_NAME" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">设备名称：</div>
                                            <div name="DEVICE_NAME" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">开始环号：</div>
                                            <div name="DUCT_CODE" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">影响环号：</div>
                                            <div name="DUCT_NUM" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">结束环号：</div>
                                            <div name="DUCT_CODE2" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">创建人：</div>
                                            <div name="REC_CREATE_NAME" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">创建时间：</div>
                                            <div name="REC_CREATE_TIME" class="col-xs-8 col-desc"></div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-md-3 item-test contest-three fault-contest">
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">图片数：</div>
                                            <div name="IMAGE_COUNT" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">操作人：</div>
                                            <div name="OPERATOR_NAME" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">开始站：</div>
                                            <div name="START_STATIONNAME" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">结束站：</div>
                                            <div name="END_STATIONNAME" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">开始里程标：</div>
                                            <div name="STATION_FLAG" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">结束里程标：</div>
                                            <div name="END_STATIONNAME" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">开始百米标：</div>
                                            <div name="STATION_HUN_NUM" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">结束百米标：</div>
                                            <div name="STATION_HUN_NUM_END" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">修改人：</div>
                                            <div name="REC_REVISE_NAME" class="col-xs-8 col-desc"></div>
                                        </div>
                                        <div class="row row-style">
                                            <div class="col-xs-4 col-label">修改时间：</div>
                                            <div name="REC_REVISE_TIME" class="col-xs-8 col-desc"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="fault_details">
                    <div class="tab_title margin_top">
                        <div onclick="toFaultList()"
                             class="com_bt aqbhq_select_bt select_main_bt">返回
                        </div>
                    </div>
                    <div class="custody_panel custody_panel_height">
                        <div class="work_left">
                            <div class="work_statue">病害履历列表<br/><span></span></div>
                            <div class="work_scroll_box">
                                <div class="work_scroll">
                                    <ul id="fault_time_line" class="timeline">

                                    </ul>
                                </div>

                            </div>
                        </div>
                        <div class="work_right">

                            <div class="panel panel-default panle-unpadding">
                                <div class="panel-heading">
                                    <span>履历详情</span>
                                    <span id="create_date" class="panle-date">2018/8/29 12:05:00</span>
                                </div>
                                <div class="panel-body">
                                    <div id="dise_Lvli_body"
                                         class="panel-body dise_panel_body">
                                        <div class="dise-flex contest-one">
                                            <label>线路：</label>
                                            <label name="lineId"></label>
                                            <br/>
                                            <label>上下行：</label>
                                            <label name="direction"></label>
                                            <br/>
                                            <label>病害等级：</label>
                                            <label name="faultLevel"></label>
                                            <br/>
                                            <label>病害状态：</label>
                                            <label name="status"></label>
                                            <br/>
                                            <label>病害类型：</label>
                                            <label name="faultType"></label>
                                            <br/>
                                            <label>大类名称：</label>
                                            <label name="bigTypeName"></label>
                                            <br/>
                                            <label>小类名称：</label>
                                            <label name="smallTypeName"></label>
                                            <br/>
                                            <label>详细位置：</label>
                                            <label name="deviceLoc"></label>
                                            <br/>
                                            <label>病害位置：</label>
                                            <label name="faultUnit"></label>
                                            <br/>
                                            <label>是否修复：</label>
                                            <label name="isFinish"></label>
                                            <br/>
                                            <label>病害内码：</label>
                                            <label name="internalCode"></label>
                                            <br/>
                                        </div>
                                        <div class="dise-flex contest-one">
                                            <label>病害描述：</label>
                                            <label name="faultDesc"></label>
                                            <br/>
                                            <label>病害类型码：</label>
                                            <label name="faultNo"></label>
                                            <br/>
                                            <label>病害类型码：</label>
                                            <label name="faultId"></label>
                                            <br/>
                                            <label>处理人：</label>
                                            <label name="dealName"></label>
                                            <br/>
                                            <label>设备名称：</label>
                                            <label name="deviceName"></label>
                                            <br/>
                                            <label>开始环号：</label>
                                            <label name="ductCode"></label>
                                            <br/>
                                            <label>影响环号：</label>
                                            <label name="ductNum"></label>
                                            <br/>
                                            <label>结束环号：</label>
                                            <label name="ductCode2"></label>
                                            <br/>
                                            <label>创建人：</label>
                                            <label name="recCreateName"></label>
                                            <br/>
                                            <label>创建时间：</label>
                                            <label name="recCreateTime"></label>
                                            <br/>
                                        </div>
                                        <div class="dise-flex contest-one">
                                            <label>类型名称：</label>
                                            <label name="exceptionTypeName"></label>
                                            <br/>
                                            <label>图片编码：</label>
                                            <label name="imgCode"></label>
                                            <br/>
                                            <label>操作人：</label>
                                            <label name="operatorName"></label>
                                            <br/>
                                            <label>开始站：</label>
                                            <label name="startStationname"></label>
                                            <br/>
                                            <label>结束站：</label>
                                            <label name="endStationname"></label>
                                            <br/>
                                            <label>开始里程标：</label>
                                            <label name="stationFlag"></label>
                                            <br/>
                                            <label>开始百米标：</label>
                                            <label name="stationHunNum"></label>
                                            <br/>
                                            <label>开始千米标：</label>
                                            <label name="stationKiNum"></label>
                                            <br/>
                                            <label>修改人：</label>
                                            <label name="recReviseName"></label>
                                            <br/>
                                            <label>修改时间：</label>
                                            <label name="recReviseTime"></label>
                                            <br/>
                                        </div>
                                        <!--<div>lng：</div>-->
                                        <!--<div name="lng">121.428806</div>-->
                                        <!--<br/>-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <!-- /.col -->
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="diseImgModal" tabindex="-1" role="dialog"
     aria-labelledby="diseImgModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                </h4>
            </div>
            <div class="modal-body">
                <img id="diseModelImg" class="item_img" src=""/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->

</div>
<script src="${ctx }/static/js/module/segment/tabs/fault.js"></script>
<script src="${ctx}/webjars/swiper/4.5.0/dist/js/swiper.min.js"></script>