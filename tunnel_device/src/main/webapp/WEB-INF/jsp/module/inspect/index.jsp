<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<!-- 后台页面布局，自动包装head、menu、NAV和foot -->
<html lang="en">
<head>
    <%@ include file="/WEB-INF/jsp/commons/common_head.jsp" %>
    <link rel="stylesheet" href="${ctx }/webjars/sweetalert2/7.33.1/dist/sweetalert2.min.css">
    <%--<link rel="stylesheet"  href="${ctx }/webjars/swiper/4.5.0/dist/css/swiper.css"/>--%>
    <link rel="stylesheet" href="${ctx }/webjars/select2/4.0.10/css/select2.min.css"/>
    <link href="${ctx }/webjars/bootstrap-table/1.14.2/dist/bootstrap-table.min.css" rel="stylesheet">
    <!-- daterange picker -->
    <%--<link rel="stylesheet" href="${ctx }/webjars/bootstrap-datetimepicker/2.4.4/css/bootstrap-datetimepicker.min.css"/>--%>
    <!-- daterange picker -->
    <%--<link rel="stylesheet" href="${ctx }/webjars/bootstrap-daterangepicker/2.1.27/css/bootstrap-daterangepicker.css"/>--%>
    <!--bootstrap-datepicker-->
    <%--<link rel="stylesheet" href="${ctx }/webjars/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.css"/>--%>
    <%--<link href="${ctx }/webjars/jquery-treegrid/0.2.0/css/jquery.treegrid.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="${ctx }/webjars/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css"/>
    <%@ include file="/WEB-INF/jsp/commons/common_head2.jsp" %>
</head>
<!-- fixed -->
<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="wrapper">
    <!-- Main Header -->
    <%@ include file="/WEB-INF/jsp/commons/layout_header.jsp" %>
    <%@ include file="/WEB-INF/jsp/commons/layout_aside.jsp" %>
    <!-- <div th:replace="common/layout :: layout-home"/>
 -->

    <div class="content-wrapper">
        巡检
    </div>
    <%@ include file="/WEB-INF/jsp/commons/common_footer.jsp" %>
</div>

<%@ include file="/WEB-INF/jsp/commons/common_script.jsp" %>
<script src="${ctx }/webjars/sweetalert2/7.33.1/dist/sweetalert2.min.js"></script>
<%--<script src="${ctx }/webjars/select2/4.0.10/js/select2.full.min.js"></script>--%>
<script src="${ctx }/webjars/bootstrap-table/1.14.2/dist/bootstrap-table.min.js"></script>
<!-- put your locale files after bootstrap-table.js -->
<script src="${ctx }/webjars/bootstrap-table/1.14.2/dist/locale/bootstrap-table-zh-CN.min.js"></script>
<!-- date-range-picker -->
<%--<script src="${ctx }/webjars/momentjs/2.24.0/min/moment.min.js"></script>--%>
<%--<script src="${ctx }/webjars/momentjs/2.24.0/min/moment-with-locales.min.js"></script>--%>
<%--<script src="${ctx }/webjars/bootstrap-daterangepicker/2.1.27/js/bootstrap-daterangepicker.js"></script>--%>
<%--<script src="${ctx }/webjars/bootstrap-datetimepicker/2.4.4/js/bootstrap-datetimepicker.min.js"></script>--%>
<%--<script src="${ctx }/webjars/bootstrap-datetimepicker/2.4.4/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>--%>
<!--bootstrap-datepicker-->
<%--<script src="${ctx }/webjars/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.js"></script>--%>
<%--<script src="${ctx }/webjars/bootstrap-datepicker/1.8.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>--%>
<%--<script src="${ctx }/webjars/github-com-crlcu-multiselect/2.4.0/dist/js/multiselect.min.js"></script>--%>
<%--<script src="${ctx }/webjars/bootstrap-table/1.14.2/dist/extensions/treegrid/bootstrap-table-treegrid.js"></script>--%>
<%--<script src="${ctx }/webjars/jquery-treegrid/0.2.0/js/jquery.treegrid.js"></script>--%>
<!--bootstrap-datepicker-->
<script src="${ctx }/webjars/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx }/webjars/bootstrap-datepicker/1.8.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<%@ include file="/WEB-INF/jsp/commons/common_script2.jsp" %>
<%--<script src="${ctx }/static/js/module/inspect/sideMenu.js"></script>--%>
<script>
    var module = 'inspect'
    var faultTypeData = '';
    // activeHeadMenu('/' + module);
    initHeadMenu('/' + module);
    // loadSideMenu('/' + module);
    initAsideMenu('/' + module);
    getStationInspect();
    ;!$(function () {
        // /*左侧菜单收缩*/
        // $("#header-sidebar-toggle").click();
        getDictData('a');
        modalControl();
    });
    function getDictData(type) {
        $.ajax({
            type: "post",
            url: ctx + "/module/device/dict",
            dataType: 'json',
            contentType: 'application/json',
            // data: JSON.stringify({params: {module: type}}),
            async: false,
            success: function (data) {
                if (data.code === 200) {
                    faultTypeData = data.data;
                }
            }
        });
    }


</script>
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