<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/WEB-INF/jsp/commons/common_head.jsp" %>
</head>
<!-- fixed -->
<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="wrapper">
    <!-- Main Header -->
    <%@ include file="/WEB-INF/jsp/commons/layout_header.jsp" %>
    <%@ include file="/WEB-INF/jsp/commons/layout_aside.jsp" %>
    <!-- <div th:replace="common/layout :: layout-home"/>
 -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        欢迎页
    </div>
    <!-- /.content-wrapper -->


    <!-- Left side column. contains the logo and sidebar -->
    <!--<div class="main-sidebar" th:include="~{commons/bar :: sidebar(activeUri='main.html')}"></div>-->
    <!-- Content Wrapper. Contains page content -->
    <!--<div class="content-wrapper" id="content-wrapper" layout:fragment="content"></div>-->
    <!-- /.content-wrapper -->
    <!-- Main Footer -->
    <!--<div th:replace="~{commons/main::footer(activeUri='main.html')}"></div>-->

    <%-- <div th:replace="common/layout :: main-footer"/>--%>
    <%@ include file="/WEB-INF/jsp/commons/common_footer.jsp" %>
    <!-- Control Sidebar -->
    <!--<div th:replace="~{commons/main::controlSiderbar(activeUri='main.html')}"></div>-->
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
 immediately after the control sidebar -->
    <!--<div class="control-sidebar-bg"></div>-->
</div>

<%@ include file="/WEB-INF/jsp/commons/common_script.jsp" %>
<%--<script th:src="@{/static/js/module/home/aqbhq/aqbhq.js}"></script>
<script th:src="@{/static/js/module/home/aqbhq/template/monitor.js}"></script>
<script th:src="@{/static/js/module/home/aqbhq/template/violation.js}"></script>
<script th:src="@{/static/js/module/home/aqbhq/template/custody.js}"></script>
<script th:src="@{/static/js/module/home/aqbhq/template/fault.js}"></script>
<script th:src="@{/static/js/module/map/result.js}"></script>
<script th:src="@{/static/js/lib/wgs84Tosh.js}"></script>
<script th:src="@{/static/js/module/home/aqbhq/template/ageway.js}"></script>--%>
<!-- Optionally, you can add Slimscroll and FastClick plugins.
Both of these plugins are recommended to enhance the
user experience. -->
<script src="${ctx }/webjars/bootstrap-table/1.14.2/dist/bootstrap-table.min.js"></script>
<!-- put your locale files after bootstrap-table.js -->
<script src="${ctx }/webjars/bootstrap-table/1.14.2/dist/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="${ctx }/static/js/common.js"></script>
<script src="${ctx }/static/js/index.js"></script>

<!-- 模态框（Modal） -->
<div class="modal fade" id="commonModal" tabindex="-1" role="dialog" aria-labelledby="commonModalLabel" aria-hidden="true">
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