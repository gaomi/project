<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<ul id="myCustodyTab" class="nav nav-tabs" style="display: none">
    <li class="active">
        <a href="#custody_list" data-toggle="custody_tab">
        </a>
    </li>
    <li><a href="#custody_details" data-toggle="custody_tab"></a></li>
</ul>
<div class="row">
    <div class="col-xs-12">
        <!-- /数据表 -->
        <!-- /.box-header -->
        <div class="box-body">
            <div id="custodyTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="custody_list">
                    <!-- 数据表工具栏 -->
                    <div class="toolbar paoding-dg-toolbar">
                        <div class="easyui-panel toolbar-north-panel tab_title">
                            <div class="aqbhq_title"></div>
                            <div class="toolbar_box_bt">
                                <ul>
                                    <li>
                                        <label>名称</label>
                                        <input id="custody_name" type="text" dataType="trim"
                                               class="li_select check-input"/>
                                    </li>
                                    <li>
                                        <label>状态</label>
                                        <select id="custody_status" class="li_select">
                                            <option value=""></option>
                                            <option value="已完成">已完成</option>
                                            <option value="已归档">已归档</option>
                                        </select>
                                    </li>
                                    <li>
                                        <label>等级</label>
                                        <select id="custody_line" class="li_select">
                                            <option value=""></option>
                                            <option value="特级">特级</option>
                                            <option value="一级">一级</option>
                                            <option value="二级">二级</option>
                                            <option value="三级">三级</option>
                                        </select>
                                    </li>
                                    <li onclick="custodyRefresh()"
                                        class="tbale_aqbhq_bt remst_bt">
                                        查询
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <table id="custody_table" style="word-break:break-all">
                    </table>
                </div>
                <div class="tab-pane fade" id="custody_details">
                    <div class="tab_title margin_top">
                        <div onclick="toCustodyList()" class="com_bt aqbhq_select_bt select_main_bt">返回</div>
                    </div>
                    <div class="monitor_scroll margin_top">
                        <div class="panel panel-default">
                            <div class="custody_panel custody_panel_height">
                                <img src="${ctx }/static/images/home/aqbhq/image071.png" width="100%" height="100%"/>
                            </div>
                            <div class="custody_panel">
                                <img src="${ctx }/static/images/home/aqbhq/image073.jpg" width="100%" height="100%"/>
                            </div>
                            <div class="custody_panel custody_panel_height">
                                <img src="${ctx }/static/images/home/aqbhq/image075.jpg" width="100%" height="100%"/>

                            </div>
                            <div class="custody_panel custody_panel_height">
                                <div class="work_left">
                                    <div class="work_statue">工况列表<br/><span></span></div>
                                    <div class="work_scroll_box">
                                        <div class="work_scroll">
                                            <ul id="timeline" class="timeline">

                                            </ul>
                                        </div>

                                    </div>
                                </div>
                                <div class="work_right">

                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <span id="detail_status">世纪大道SN1地块</span>
                                            <span id="detail_date">2018/8/29 12:05:00</span>
                                        </div>
                                        <div class="panel-body">
                                            <div id="detail_detail" class="panel_title">
                                                本项目影响轨道交通9号线商城路站、商城路上下行联络通道及商城路站两端部分隧道区间，沿线地铁线路长度约为200m。主要影响范围为商城路站，商城路站1#、4#出入口，商城路站两个风井，以及商城路站两端部分隧道区间。
                                                ↵对应里程：上行线SK41+330.0～SK41+737.0；下行线XK41+335.0～XK41+742.0）。
                                            </div>
                                            <div class="panel_body">
                                                <div class="panel_img">
                                                    <img id="detail_url" src="" class="item_img"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <script src="${ctx }/static/js/module/segment/tabs/custody.js"></script>
</div>
