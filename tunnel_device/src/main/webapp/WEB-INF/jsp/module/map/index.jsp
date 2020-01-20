<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <meta charset="utf-8"/>
    <title>隧道设备智能化管理平台</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
    <link rel="stylesheet" href="${ctx }/webjars/bootstrap/3.4.1/css/bootstrap.min.css"/>
    <link rel="shortcut icon" href="${ctx }/static/images/favicon.ico"/>
    <link rel="bookmark" href="${ctx }/static/images/favicon.ico"/>
    <style>
        html, body {
            padding: 0;
            margin: 0;
            height: 100% !important;
        }

        ul, li {
            padding-left: 0;
            list-style: none;
        }

        #myModal .modal-content {
            background: #f1f2f6;
        }

        #myModal {
            width: 100%;
            height: 100%;
            min-width: 1000px;
            overflow: auto
        }

        .wgxmMenu, .jhjcsin {
            position: absolute;
            bottom: 40px;
            width: 152px;
            height: 212px;
            background: #fff;
            border: 1px solid #000;
            z-index: 99999;
        }

        .show-title {
            height: 30px;
            text-align: center;
            line-height: 30px;
            margin-bottom: 0;
            background: #3033e5;
            color: #fff;
            position: relative;
        }

        .show-title button {
            display: block;
            width: 20px;
            height: 20px;
            padding: 0;
            position: absolute;
            right: 10px;
            top: 5px;
            background-color: #fff;
            color: #000;
            border-radius: 50%;
            border: 0;
            outline: none;
            text-align: center;
            line-height: 20px;
            font-size: 20px;
        }

        .jhjcsin {
            right: 0;
        }

        .wgxmMenu {
            left: 0;
        }

        .wgxmMenu ul, .jhjcsin ul {
            width: 150px;
            height: 182px;
        }

        .wgxmMenu li, .jhjcsin li {
            width: 50px;
            height: 30px;
            float: left;
            text-align: center;
            line-height: 30px;
            cursor: pointer;
        }

        /*,body,html*/
        .complete_pop, .ptd_complete_pop {
            display: none;
            opacity: 1
        }
    </style>

</head>
<body>
<iframe id="gisView" width="100%" height="100%" scrolling="no" frameborder="0"></iframe>
<button onclick="showModal()" style="position: absolute;bottom: 10px;left: 10px;z-index: 9999;">区间详情</button>
<div id="proBtns">
    <div style="position: absolute;bottom: 10px;left: 90px;z-index: 9999;">
        <button id="select_jhShow" onclick="jsWgShow(this,'jhShow')">监护监测</button>
        <div id="jhShow" class=" jhjcsin fade bottom in complete_pop">
            <div class="show-title"><p>监护监测</p>
                <button onclick="jsWgShow(this,'jhShow')">×</button>
            </div>
            <ul></ul>
        </div>
    </div>
    <div style="position: absolute;bottom: 10px;left: 170px;z-index: 9999;">
        <button id="select_wgShow" onclick="jsWgShow(this,'wgShow')">违规项目</button>
        <div id="wgShow" class=" wgxmMenu fade bottom in complete_pop">
            <div class="show-title"><p>违规项目</p>
                <button onclick="jsWgShow(this,'wgShow')">×</button>
            </div>
            <ul></ul>
        </div>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog mains_width">
        <div class="modal-content">
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->

</div>
</body>
<script>var ctx = "${ctx }"</script>
<script type="text/javascript" src="${ctx}/webjars/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/webjars/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/lib/coordinateTransformation.js"></script>
<script type="text/javascript" src="${ctx}/static/js/lib/wgs84Tosh.js"></script>
<script type="text/javascript" src="${ctx}/static/js/module/map/index.js"></script>


</html>
