<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>隧道设备智能化管理平台</title>
    <link rel="stylesheet" href="style/default.css" type="text/css"/>
    <%--<link rel="stylesheet" href="http://192.168.12.55:8000/tunnel_device/static/js/lib/arcgis_js_api/library/3.22/esri/css/esri.css"/>--%>
    <%--<link rel="stylesheet" href="http://218.242.68.246/arcgis_js_api/library/3.22/3.22/esri/css/esri.css"/>--%>
    <link rel="stylesheet" href="http://10.0.112.71/tunnel_api/library/3.22/3.22/esri/css/esri.css"/>
    <%--<link rel="stylesheet" href="http://localhost:8080/arcgis_js_api/library/3.22/3.22/esri/css/esri.css"/>--%>
    <%--<link rel="stylesheet" href="http://172.17.200.198/arcgis_js_api/library/3.22/esri/css/esri.css"/>--%>
    <link rel="stylesheet" href="style/mapMain.css" type="text/css"/>
    <link rel="stylesheet" href="style/bootstrap.css" type="text/css"/>

    <script type="text/javascript" src="script/jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="script/basicCommon.js"></script>

    <!--Ztree-->
    <script type='text/javascript' src='script/plugins/ztree/jquery.ztree.all.min.js'></script>
    <script type='text/javascript' src='script/plugins/ztree/jquery.ztree.core.min.js'></script>
    <script type='text/javascript' src='script/plugins/ztree/jquery.ztree.excheck.min.js'></script>
    <script type='text/javascript' src='script/plugins/ztree/jquery.ztree.exedit.min.js'></script>
    <script type='text/javascript' src='script/plugins/ztree/jquery.ztree.exhide.min.js'></script>

    <!--jqueryDialog-->
    <script type='text/javascript' src='script/plugins/layer/layer.js'></script>

    <!--SelectMenu-->
    <script type='text/javascript' src='script/plugins/selectMenu/selectmenu.min.js'></script>
    <script type='text/javascript' src='script/plugins/jquery.fixedheadertable.js'></script>


    <!---->
    <script type="text/javascript" src="http://10.0.112.71/tunnel_api/library/3.22/3.22/init.js"></script>
    <!--<script type="text/javascript" src="http://218.242.68.246/arcgis_js_api/library/3.22/3.22/init.js"></script>-->
    <!--<script type="text/javascript" src="http://192.168.12.55:8000/tunnel_device/static/js/lib/arcgis_js_api/library/3.22/init.js"></script>-->
    <!--<script type="text/javascript" src="http://localhost:8080/arcgis_js_api/library/3.22/3.22/init.js"></script>-->
    <!--<script type="text/javascript" src="http://172.17.200.198/arcgis_js_api/library/3.22/init.js"></script>-->
    <script>var ctx = "${ctx }"</script>
    <script type="text/javascript" src="main/config/map.config.js"></script>
    <!--加载-->
    <!--<script type="text/javascript" src="widget/scalebar/scalebarToolBar.js"></script>
    <script type="text/javascript" src="widget/navigation/navigationToolBar.js"></script>
    <script type="text/javascript" src="widget/baseMap/baseMapToolBar.js"></script>
    <script type="text/javascript" src="widget/measure/measureToolBar.js"></script>
    <script type="text/javascript" src="widget/layerList/layerListToolBar.js"></script>
    <script type="text/javascript" src="widget/legend/legendToolBar.js"></script>
    <script type="text/javascript" src="widget/geoQuery/geoQueryToolBar.js"></script>-->
    <!---->
    <script type="text/javascript" src="main/mapInit.js"></script>
    <script type="text/javascript" src="main/mapManager.js"></script>
    <script type="text/javascript" src="main/mapToolManager.js"></script>
    <script type="text/javascript" src="main/mapMessageDeal.js"></script>
    <%--<script type="text/javascript" src="authUtils.js" th:src="@{/static/js/authUtils.js}"></script>--%>

</head>
<body>

<div class="main-container" id="main-container">
    <div class="allMapTool" id="tool_container"></div>
    <div id="centerPanel">
        <div id="map" class="mapOne"></div>
        <div id="map_Two" class="mapTwo"></div>
    </div>
</div>
<div id="divMsg_shade"></div>
<div id="divMsg">
    <img style="vertical-align: middle;" src="images/loading_pro.gif"/>
    <span>地图加载中...</span>
</div>
<script>
    //toModule(window.location.pathname);
    $(document).ready(function () {
        $('#centerPanel').css({
            'height': $(window).height() + 'px',
            'width': $(window).width() + 'px'
        });
    });
</script>
</body>
</html>
