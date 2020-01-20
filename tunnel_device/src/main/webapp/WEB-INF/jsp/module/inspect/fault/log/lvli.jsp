<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<style>
    .logLvliHeade{
        margin-left: 20px;
        font-size: 14px;
    }
    .logLvliHeade span{
        margin-right: 10px;
    }
</style>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">病害履历</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool paoding_btn_hide paoding_btn_hide" data-widget="remove" onclick="$('#commonModal').modal('hide')"><i
                    class="fa fa-remove"></i></button>
        </div>
    </div>
    <!-- /.box-header -->
    <div class="box-body">
        <div class="logLvliHeade" ></div>
        <table id="fault_lvli_table" class="table table-bordered table-striped text-nowrap">
        </table>
    </div>
    <!-- /.box-body -->
</div>
<script src="${ctx }/static/js/module/inspect/fault/log/lvli.js"></script>