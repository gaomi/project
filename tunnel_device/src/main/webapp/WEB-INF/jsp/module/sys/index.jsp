<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/WEB-INF/jsp/commons/common_head.jsp" %>
    <%--<link rel="stylesheet" href="${ctx }/webjars/select2/4.0.10/css/select2.min.css"/>--%>

    <!-- sweetalert2 -->
    <link rel="stylesheet" href="${ctx }/webjars/sweetalert2/7.33.1/dist/sweetalert2.min.css">
    <!-- multiple-select -->
    <link rel="stylesheet" href="${ctx }/webjars/multiple-select/1.2.1/multiple-select.css">
    <%--<script src="${ctx }/webjars/bootstrap-select/1.13.11/js/i18n/defaults-zh_CN.min.js"></script>--%>
    <link href="${ctx }/webjars/bootstrap-table/1.14.2/dist/bootstrap-table.min.css" rel="stylesheet">
    <link href="${ctx }/webjars/jquery-treegrid/0.2.0/css/jquery.treegrid.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx }/webjars/zTree/3.5.37/css/metroStyle/metroStyle.css"/>
    <!--bootstrap-datepicker-->
    <link rel="stylesheet" href="${ctx }/webjars/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css"/>
    <%@ include file="/WEB-INF/jsp/commons/common_head2.jsp" %>
</head>
<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="wrapper">
    <!-- Main Header -->
    <%@ include file="/WEB-INF/jsp/commons/layout_header.jsp" %>
    <%@ include file="/WEB-INF/jsp/commons/layout_aside.jsp" %>
    <div class="content-wrapper">
        系统管理
    </div>
    <%@ include file="/WEB-INF/jsp/commons/common_footer.jsp" %>
</div>
<%@ include file="/WEB-INF/jsp/commons/common_script.jsp" %>
<script src="${ctx }/webjars/sweetalert2/7.33.1/dist/sweetalert2.min.js"></script>
<%--<script src="${ctx }/webjars/select2/4.0.10/js/select2.full.min.js"></script>--%>
<script src="${ctx }/webjars/multiple-select/1.2.1/multiple-select.js"></script>
<%--<script src="${ctx }/webjars/bootstrap-select/1.13.11/js/bootstrap-select.min.js"></script>--%>
<%--<script src="${ctx }/webjars/bootstrap-select/1.13.11/js/i18n/defaults-zh_CN.js"></script>--%>
<script src="${ctx }/webjars/zTree/3.5.37/js/jquery.ztree.all.min.js"></script>
<script src="${ctx }/webjars/bootstrap-table/1.14.2/dist/bootstrap-table.min.js"></script>
<!--bootstrap-datepicker-->
<script src="${ctx }/webjars/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx }/webjars/bootstrap-datepicker/1.8.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${ctx }/webjars/bootstrap-table/1.14.2/dist/extensions/treegrid/bootstrap-table-treegrid.js"></script>
<script src="${ctx }/webjars/bootstrap-table/1.14.2/dist/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${ctx }/webjars/jquery-treegrid/0.2.0/js/jquery.treegrid.js"></script>
<%--<script src="${ctx }/static/js/lib/jqTreeGrid/jquery.treegrid.extension.js"></script>--%>
<%@ include file="/WEB-INF/jsp/commons/common_script2.jsp" %>
<script>
    var module = 'sys';
    initHeadMenu('/' + module);
    // loadSideMenu('/' + module);
    initAsideMenu('/' + module);
    getStationInspect();

   // activeHeadMenu('/' + module);
    ;!$(function () {
        // /*左侧菜单收缩*/
        // $("#header-sidebar-toggle").click();

        modalControl();
    });
</script>
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