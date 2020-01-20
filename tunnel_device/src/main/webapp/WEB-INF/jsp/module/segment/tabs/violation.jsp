<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<link rel="stylesheet" href="${ctx}/webjars/swiper/4.5.0/dist/css/swiper.min.css"/>
<style>
    /*违规施工表格css*/
    #small-font .col-label {
        padding: 0;
    }

    #small-font .row-style div {
        font-size: 12px;
    }
</style>
<ul id="violation_tab" class="nav nav-tabs" style="display: none">
    <li class="active">
        <a id="tab_list" href="#list" data-toggle="tab">
        </a>
    </li>
    <li><a id="tab_details" href="#details" data-toggle="tab"></a></li>
</ul>
<div class="row">
    <div class="col-xs-12">
        <div class="box-body">
            <div id="violationTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="list">
                    <div class="toolbar paoding-dg-toolbar">
                        <div class="easyui-panel toolbar-north-panel tab_title">
                            <div class="aqbhq_title"></div>
                            <div class="toolbar_box_bt">
                                <ul>
                                    <li>
                                        <label>名称</label>
                                        <input id="project_name" type="text"
                                               class="li_select"/>
                                    </li>
                                    <li>
                                        <label>状态</label>
                                        <select id="aqbhq_status" class="li_select">
                                            <option value=""></option>
                                            <option value="停工">停工</option>
                                            <option value="施工">施工</option>
                                            <option value="已完成">已完成</option>
                                        </select>
                                    </li>
                                    <li>
                                        <label>等级</label>
                                        <select id="metroline_name" class="li_select">
                                            <option value=""></option>
                                            <option value="一级">一级</option>
                                            <option value="二级">二级</option>
                                            <option value="三级">三级</option>
                                        </select>
                                    </li>
                                    <li onclick="refresh()"
                                        class="tbale_aqbhq_bt remst_bt">
                                        查询
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <table id="aqbhq_list" style="word-break:break-all">
                    </table>
                </div>
                <div class="tab-pane fade " id="details">
                    <div class="tab_title margin_top">
                        <div onclick="toList()" class="com_bt aqbhq_select_bt select_main_bt">返回</div>
                    </div>
                    <div class="violation_detail">
                        <div class="panel panel-default half_div">
                            <div class="panel-heading"
                                 style="text-align: center">
                                <label>违规项目详细信息</label>
                            </div>
                            <div id="small-font">
                                <div class="col-xs-12 col-md-6 item-test contest-one">
                                    <div class="row row-style">
                                        <div class="col-xs-3 col-label">项目名称：</div>
                                        <div name="projectname" class="col-xs-9 col-desc"></div>
                                    </div>
                                    <div class="row row-style">
                                        <div class="col-xs-3 col-label">项目描述：</div>
                                        <div name="projectdescription" class="col-xs-9 col-desc"></div>
                                    </div>
                                    <div class="row row-style">
                                        <div class="col-xs-3 col-label">项目地址：</div>
                                        <div name="projectaddress" class="col-xs-9 col-desc"></div>
                                    </div>
                                    <div class="row row-style">
                                        <div class="col-xs-3 col-label">建设单位：</div>
                                        <div name="projectbuildunit" class="col-xs-9 col-desc"></div>
                                    </div>
                                    <div class="row row-style">
                                        <div class="col-xs-3 col-label">施工单位：</div>
                                        <div name="projectconstructionunit" class="col-xs-9 col-desc"></div>
                                    </div>
                                    <div class="row row-style">
                                        <div class="col-xs-3 col-label">状态：</div>
                                        <div name="projtasklastresultstatus" class="col-xs-9 col-desc"></div>
                                    </div>
                                    <div class="row row-style">
                                        <div class="col-xs-3 col-label">联系人：</div>
                                        <div name="projectbuildunitcontact" class="col-xs-9 col-desc"></div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-6 item-test contest-one">
                                    <div class="row row-style">
                                        <div class="col-xs-3 col-label">线路名称：</div>
                                        <div name="metrolinename" class="col-xs-9 col-desc"></div>
                                    </div>
                                    <div class="row row-style">
                                        <div class="col-xs-3 col-label">区间：</div>
                                        <div name="qvjian" class="col-xs-9 col-desc"></div>
                                    </div>
                                    <div class="row row-style">
                                        <div class="col-xs-3 col-label">项目面积：</div>
                                        <div name="projectarea" class="col-xs-9 col-desc"></div>
                                    </div>
                                    <div class="row row-style">
                                        <div class="col-xs-3 col-label">项目类型：</div>
                                        <div name="projecttype" class="col-xs-9 col-desc"></div>
                                    </div>
                                    <div class="row row-style">
                                        <div class="col-xs-3 col-label">项目等级：</div>
                                        <div name="projectlevel" class="col-xs-9 col-desc"></div>
                                    </div>

                                    <div class="row row-style">
                                        <div class="col-xs-3 col-label">位置关系：</div>
                                        <div name="projectlocationwithmetro" class="col-xs-9 col-desc"></div>
                                    </div>
                                    <div class="row row-style">
                                        <div class="col-xs-3 col-label">坐标信息：</div>
                                        <div name="col" class="col-xs-9 col-desc"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="half_div violat_swiper">
                            <div class="main-swiper">
                                <div class="swiper-container gallery-top">
                                    <div id="top_cont" class="swiper-wrapper">
                                    </div>
                                    <div class="swiper-button-next"></div>
                                    <div class="swiper-button-prev"></div>
                                </div>
                            </div>
                            <div class="tool-swiper">
                                <div class="swiper-container gallery-thumbs">
                                    <div id="thumbs_cont" class="swiper-wrapper">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${ctx}/webjars/swiper/4.5.0/dist/js/swiper.min.js"></script>
    <script src="${ctx}/static/js/module/segment/tabs/violation.js"></script>
</div>
