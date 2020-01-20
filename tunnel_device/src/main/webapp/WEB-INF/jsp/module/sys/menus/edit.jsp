<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<style>
    /*.trg {*/
    /*width: 0;*/
    /*height: 0;*/
    /*border-left: 3px solid transparent;*/
    /*border-right: 3px solid transparent;*/
    /*border-top: 6px solid black;;*/
    /*position: absolute;*/
    /*left: 181px;*/
    /*top: 8px;*/

    /*}*/

    /*#dept_name {*/
    /*cursor: default;*/
    /*z-index: -1;*/
    /*width: 200px;*/
    /*}*/
    .ms-choice {
        height: 34px;
        line-height: 34px;
        border-radius: 0px;
    }

    #menusTreeDiv {
        z-index: 1000;
    }

    #menusTreeDiv ul.ztree {
        margin-top: 10px;
        border: 1px solid #617775;
        background: #f0f6e4;
        width: 220px;
        height: 360px;
        overflow-y: scroll;
        overflow-x: auto;
    }

</style>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">编辑菜单</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool paoding_btn_hide" data-widget="remove" onclick="$('#commonModal').modal('hide')"><i class="fa fa-remove"></i></button>
        </div>
    </div>
    <form id="sys_menus_edit_form" class="form-horizontal">
        <div class="box-body">
            <input type="hidden" name="id"/>
            <div class="form-group">
                <label for="menuName" class="col-sm-3 control-label">菜单名称<span class="form-bt">(*必填)</span></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="menuName" name="name" placeholder="请输入">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">类型<span class="form-bt">(*必填)</span></label>
                <div class="col-sm-9">
                    <div class="col-xs-4">
                        <div class="edit_radio_div">
                            <input type="radio" name="type" value="1" checked="checked"/>
                            菜单
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="edit_radio_div">
                            <input type="radio" name="type" value="0">
                            按钮
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">地址<span class="form-bt">(*必填)</span></label>
                <div class="col-sm-9">
                    <input type="text" name="url" class="form-control" placeholder="请输入">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">菜单层级<span class="form-bt">(*必填)</span></label>
                <div class="col-sm-9">
                    <select class="form-control"  name="type" id="levelType_edit_select">
                        <option value="">请选择...</option>
                        <option value="1">一级</option>
                        <option value="2">二级</option>
                        <option value="3">三级</option>
                    </select>
                    <%--<input type="hidden" name="type"/>--%>
                </div>
            </div>
            <div class="form-group">
                <label for="parentMenuTree" class="col-sm-3 control-label">上级菜单</label>

                <div class="col-sm-9">
                    <input type="text" name="parentMenu" id="parentMenuTree" class="form-control" placeholder="上级菜单" title="父级选择" onclick="showMenusTree()"/>
                    <!-- 模拟select点击框 以及option的text值显示-->
                    <i class="trg" style="position: absolute;"></i>
                    <!-- 存储 模拟select的value值 -->
                    <input type="hidden" name="parentId" id="parentId"/>
                    <div id="menusTreeDiv" style="display:none;position: absolute;">
                        <ul id="editFormMenusTree" class="ztree" style="margin-top:0; width:375px;"></ul>
                    </div>

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
                <label class="col-sm-3 control-label">权限标识</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="permission" placeholder="格式：user:query">
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
<script src="${ctx }/static/js/module/sys/menus/edit.js"></script>

