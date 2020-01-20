<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="row">
    <div class="col-xs-12">
        <div class="box">
           <%-- <div class="box-header">
            </div>--%>
            <!-- /.box-header -->
            <div class="box-body">
                <div id="zonghe-box">
                    <div class="col-md-12 col-lg-5 zonghe-pading">
                        <div class="tuceng-duct-warpper">
                            <div class="duct-desc">
                                <div class="duct_desc_head1"><p>环号：<span></span></p></div>
                                <div class="duct_desc_head2"><p>隧道顶埋深：<span></span>米</p></div>
                            </div>
                            <div class="mainimg">
                                <div class="tuceng">
                                    <div class="tuceng-lis">
                                        <div class="title">
                                            <p>土层</p>
                                        </div>
                                        <ul>
                                            <li class="tuceng-1"><img src="${ctx}/static/images/segment/zonghe/tcwk_on.png" alt=""/><i></i></li>
                                            <li class="tuceng-2"><img src="${ctx}/static/images/segment/zonghe/tcwk_middle.png" alt=""/><i></i></li>
                                            <li class="tuceng-3"><img src="${ctx}/static/images/segment/zonghe/tcwk_down.png" alt=""/><i></i></li>
                                        </ul>
                                        <div class="tuceng-whole">
                                            <img src="${ctx}/static/images/segment/zonghe/tcwk_soil.png" alt=""/>
                                            <i></i>
                                        </div>
                                    </div>
                                </div>
                                <div class="duct-warpper">
                                    <div data-type="封顶块" data-content="duct-fdk" class=""></div>
                                    <div data-type="邻接块" data-content="duct-left-ljk" class=""></div>
                                    <div data-type="邻接块" data-content="duct-right-ljk" class=""></div>
                                    <div data-type="标准块" data-content="duct-left-bzk" class=""></div>
                                    <div data-type="标准块" data-content="duct-right-bzk" class=""></div>
                                    <div data-type="拱底块" data-content="duct-gdk" class=""></div>
                                    <div id="duct-contNum"><i></i><span>(mm)</span></div>
                                </div>
                            </div>
                            <div class="duct-down-ms1">

                            </div>
                            <div class="duct-down-ms">
                                <p>隧道底埋深：<span></span>米</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12 col-lg-7 zonghe-pading">
                        <div class="map-earpper">
                            <img src="${ctx}/static/images/segment/zonghe/dt.png" class="zonghe-img"/>
                            <div class="map-mask">
                                <div class="mapdiv1">
                                    <img src="${ctx}/static/images/segment/zonghe/w1.png" alt=""/>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.box-body -->
        </div>
        <!-- /.box -->
    </div>
    <!-- /.col -->
    <script src="${ctx }/static/js/module/segment/tabs/zonghe.js"></script>
</div>