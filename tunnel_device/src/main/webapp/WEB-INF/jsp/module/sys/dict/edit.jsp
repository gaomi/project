<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">编辑字典</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool paoding_btn_hide" data-widget="remove" onclick="$('#commonModal').modal('hide')"><i class="fa fa-remove"></i></button>
        </div>
    </div>
    <form id="sys_dict_edit_form" class="form-horizontal">
        <div class="box-body">
            <input type="hidden" name="id"/>
            <div class="form-group">
                <label for="tableName" class="col-sm-3 control-label">表名<span class="form-bt">(*必填)</span></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="tableName" name="tableName" placeholder="请输入">
                </div>
            </div>
            <div class="form-group">
                <label for="fieldName" class="col-sm-3 control-label">字段名<span class="form-bt">(*必填)</span></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="fieldName" name="fieldName" placeholder="请输入">
                </div>
            </div>
            <div class="form-group">
                <label for="key" class="col-sm-3 control-label">键<span class="form-bt">(*必填)</span></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="key" name="key" placeholder="请输入">
                </div>
            </div>
            <div class="form-group">
                <label for="value" class="col-sm-3 control-label">值<span class="form-bt">(*必填)</span></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="value" name="value" placeholder="请输入">
                </div>
            </div>
            <div class="form-group">
                <label for="pidType_edit_select" class="col-sm-3 control-label">类型<span class="form-bt">(*必填)</span></label>
                <div class="col-sm-9">
                    <select class="form-control" name="pid" id="pidType_edit_select">
                        <option value="">请选择...</option>
                        <option value="0">字典分类</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">排序</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="seq" name="seq" placeholder="请输入">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">状态</label>
                <div class="col-sm-9">
                    <div class="col-xs-4">
                        <div class="edit_radio_div">
                            <input type="radio" name="status" value="1" checked="">
                            有效
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="edit_radio_div">
                            <input type="radio" name="status" value="0">
                            冻结
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">备注</label>
                <div class="col-sm-9">
                    <textarea class="form-control" name="remark" rows="3" placeholder="备注 ..."></textarea>
                </div>
            </div>

        </div>
    </form>
    <!-- /.box-body -->
    <div class="box-footer">
        <button type="reset" class="btn btn-default">重置</button>
        <button type="submit" class="btn btn-primary pull-right paoding_btn_save">提交</button>
    </div>
</div>


<script src="${ctx }/static/js/module/sys/dict/edit.js"></script>
