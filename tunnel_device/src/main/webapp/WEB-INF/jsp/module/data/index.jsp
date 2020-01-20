<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/WEB-INF/jsp/commons/common_head.jsp" %>
    <link href="${ctx }/webjars/bootstrap-table/1.14.2/dist/bootstrap-table.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx }/webjars/sweetalert2/7.33.1/dist/sweetalert2.min.css">
    <%@ include file="/WEB-INF/jsp/commons/common_head2.jsp" %>
</head>
<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="wrapper">
    <!-- Main Header -->
    <%@ include file="/WEB-INF/jsp/commons/layout_header.jsp" %>
    <%@ include file="/WEB-INF/jsp/commons/layout_aside.jsp" %>
    <div class="content-wrapper">
        数据处理
    </div>
    <%@ include file="/WEB-INF/jsp/commons/common_footer.jsp" %>
</div>
<%@ include file="/WEB-INF/jsp/commons/common_script.jsp" %>
<script src="${ctx }/webjars/sweetalert2/7.33.1/dist/sweetalert2.min.js"></script>
<script src="${ctx }/webjars/bootstrap-table/1.14.2/dist/bootstrap-table.min.js"></script>
<script src="${ctx }/webjars/bootstrap-table/1.14.2/dist/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${ctx }/static/js/module/data/sideMenu.js"></script>
<%@ include file="/WEB-INF/jsp/commons/common_script2.jsp" %>
<script>
    var module = 'data'
    var dictData = '';
    // loadSideMenu('/' + module);
    initHeadMenu('/' + module);
    initAsideMenu('/' + module);
    getStationInspect();
    //activeHeadMenu('/' + module);
    ;!$(function () {
        // /*左侧菜单收缩*/
        // $("#header-sidebar-toggle").click();
        getDictData('DATA');
        modalControl();
    });

    function getDictData(type) {
        $.ajax({
            type: "post",
            url: ctx + "/module/data/base/getDataDict",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify({params: {module: type}}),
            async: false,
            success: function (data) {
                if (data.code === 200) {
                    $.each(data.data['98'], function (i, r) {
                        r['id'] = r.dictKey;
                        r['name'] = r.dictValue;
                    });
                    $.each(data.data['113'], function (i, r) {
                        r['id'] = r.dictKey;
                        r['name'] = r.dictValue;
                    });
                    dictData = data.data;

                }
            }
        });
    }
</script>
<!-- 模态框（Modal） -->
<div class="modal fade" id="commonModal" tabindex="-1" role="dialog" aria-labelledby="commonModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" id="commonModalBody">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="myModalLabel">添加数据</h4>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default paoding_btn_reset" name="resetLx">重置</button>
                <button type="button" class="btn btn-primary">添加</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</body>
</html>