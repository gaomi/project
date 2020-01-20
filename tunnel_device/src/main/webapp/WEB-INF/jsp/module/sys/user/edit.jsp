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

    #deptTreeDiv {
        z-index: 1000;
    }

    #deptTreeDiv ul.ztree {
        margin-top: 10px;
        border: 1px solid #617775;
        background: #f0f6e4;
        width: 220px;
        height: 360px;
        overflow-y: scroll;
        overflow-x: auto;
    }

    input.error {
        border-color: red;
    }

    textarea.error {
        border-color: red;
    }


</style>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">编辑用户</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool paoding_btn_hide paoding_btn_hide" data-widget="remove" onclick="$('#commonModal').modal('hide')"><i
                class="fa fa-remove"></i></button>
        </div>
    </div>
    <!-- /.box-header -->
    <div class="box-body">
        <form id="sys_user_edit_form">
            <div class="row">
                <div class="col-md-6">
                    <input type="hidden" name="id">
                    <!-- text input -->
                    <div class="form-group">
                        <label>用户名<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="userName" class="form-control " placeholder="请输入">
                    </div>
                    <div class="form-group">
                        <label>姓名<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="trueName" class="form-control " placeholder="请输入">
                    </div>
                    <div class="form-group">
                        <label>手机号</label>
                        <input type="text" name="telephone" class="form-control " placeholder="请输入">
                    </div>
                    <div class="form-group">
                        <label>账号状态</label>
                        <div class="row">
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

                </div>
                <!-- /.col -->
                <div class="col-md-6">
                    <div class="form-group">
                        <label>密码<span class="form-bt">(*必填)</span></label>
                        <input type="password" name="password" class="form-control" value="" placeholder="默认111111">
                    </div>
                    <div class="form-group">
                        <label>性别</label>
                        <div class="row">
                            <div class="col-xs-4">
                                <div class="edit_radio_div">
                                    <input type="radio" name="sex" value="1" checked="">
                                    男
                                </div>
                            </div>
                            <div class="col-xs-4">
                                <div class="edit_radio_div">
                                    <input type="radio" name="sex" value="0">
                                    女
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <label>邮箱</label>
                        <input type="text" name="mail" class="form-control" placeholder="请输入">
                    </div>
                    <div class="form-group">
                        <label>页码数</label>
                        <input type="text" name="pageSize" class="form-control" placeholder="默认10" value="10">
                    </div>

                </div>
                <!-- /.col -->
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label>备注</label>
                        <textarea class="form-control" name="remark" rows="3" placeholder="请输入"></textarea>
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>部门</label>
                        <%--  <select class="form-control select2" name="deptName">
                              <option>option 1</option>
                              <option>option 2</option>
                              <option>option 3</option>
                              <option>option 4</option>
                              <option>option 5</option>
                          </select>--%>
                        <input type="text" name="deptName" id="dept_name" class="form-control" placeholder="点击选择部门" title="部门选择" readonly="readonly" onclick="showDeptTree()"/>
                        <!-- 模拟select点击框 以及option的text值显示-->
                        <i class="trg" style="position: absolute;"></i>
                        <!-- 存储 模拟select的value值 -->
                        <input type="hidden" name="deptId" id="deptId" value=""/>
                        <div id="deptTreeDiv" style="display:none;position: absolute;">
                            <ul id="editFormDeptTree" class="ztree" style="margin-top:0; width:375px;"></ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>角色<span class="form-bt">(*必填)</span></label>
                        <select name="rolesSelect" multiple="multiple">
                            <%-- <label>角色</label><%--https://developer.snapappointments.com/bootstrap-select/
                                <select name="rolesSelect" class="selectpicker" multiple>
                                    --%></select>
                        <input name="roles" hidden/>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!-- /.box-body -->
    <div class="box-footer">
        <button type="reset" class="btn btn-default paoding_btn_reset" name="reset">重置</button>
        <button type="submit" class="btn btn-primary paoding_btn_save pull-right" name="save">提交</button>
    </div>
</div>
<%--<script src="${ctx }/static/js/lib/utils/treeDiv.js"></script>--%>
<script src="${ctx }/static/js/module/sys/user/edit.js"></script>


