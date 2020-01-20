<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">编辑任务</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool paoding_btn_hide" data-widget="remove"
                    onclick="$('#commonModal').modal('hide')"><i class="fa fa-remove"></i></button>
        </div>
    </div>
    <!-- /.box-header -->
    <div class="box-body">
        <form id="data_emphasis_edit_form">
            <div class="row">
                <div class="col-md-6">
                    <input type="hidden" name="uuid">
                    <!-- text input -->
                    <div class="form-group">
                        <label>线路<span class="form-bt">(*必填)</span></label>
                        <select type="text" name="lineUuid" class="form-control" id="edit_lineUuid">
                            <option value="">--请选择--</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-6">
                    <!-- text input -->
                    <div class="form-group">
                        <label>上下行<span class="form-bt">(*必填)</span></label>
                        <select type="text" name="updown" class="form-control" id="edit_updown">
                            <option value="">请选择上下行</option>
                            <option value="0">上行</option>
                            <option value="1">下行</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <!-- text input -->
                    <div class="form-group">
                        <label>区间<span class="form-bt">(*必填)</span></label>
                        <select type="text" class="form-control" id="edit_segmentName">
                        </select>
                    </div>
                </div>
                <div class="col-md-6">
                    <!-- text input -->
                    <div class="form-group">
                        <label>小区间<span class="form-bt">(*必填)</span></label>
                        <input type="hidden" name="segmentName" id="edit_segment_name">
                        <input type="hidden" name="segmentUuid" id="edit_segment_uuid">
                        <select type="text" class="form-control" id="edit_group_segment">
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <!-- text input -->
                    <div class="form-group">
                        <label>大修工艺<span class="form-bt">(*必填)</span></label>
                        <select type="text" name="maintainTech" class="form-control ">
                            <option value='钢内衬加固'>钢内衬加固</option>
                        </select>

                    </div>
                </div>
                <div class="col-md-6">
                    <!-- text input -->
                    <div class="form-group">
                        <label>环号<span class="form-bt">(*必填)</span></label>
                        <input type="text" onchange="value=value.replace(/[^\d]/g,'')" name="ductCode" class="form-control ">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <!-- text input -->
                    <div class="form-group">
                        <label>实施时间<span class="form-bt"></span></label>
                        <input type="text" name="operateTime" class="form-control ">
                    </div>
                </div>
                <div class="col-md-6">
                    <!-- text input -->
                    <div class="form-group">
                        <label>实施公司<span class="form-bt"></span></label>
                        <input type="text" name="operateCompany" class="form-control "></input>
                    </div>
                </div>

            </div>
            <div class="row">

            </div>
        </form>
    </div>
    <!-- /.row -->
</div>
<!-- /.box-body -->
<div class="box-footer">
    <button type="reset" class="btn btn-default">重置</button>
    <button type="submit" class="btn btn-primary  paoding_btn_save pull-right">提交</button>
</div>
</div>
<script src="${ctx }/static/js/module/data/emphasis/edit.js"></script>


