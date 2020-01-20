<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<style>
    .has-error{
        color: red;
    }
</style>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">编辑数据</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool paoding_btn_hide paoding_btn_hide" data-widget="remove" onclick="$('#commonModal').modal('hide')"><i
                    class="fa fa-remove"></i></button>
        </div>
    </div>
    <!-- /.box-header -->
    <div class="box-body">
        <form id="fault_log_edit_form">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>线路<span class="form-bt">(*必填)</span></label>
                        <input type="text" class="form-control" name="lineName"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>上下行<span class="form-bt">(*必填)</span></label>
                        <select name="updown"  class="form-control ">
                            <option value="">请选择...</option>
                            <option value="0">上行</option>
                            <option value="1">下行</option>
                            <option value="2">上下行</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>区间<span class="form-bt">(*必填)</span></label>
                        <select name="updown"  class="form-control">
                            <option value="">--请选择--</option>
                            <option value="0">上行</option>
                            <option value="1">下行</option>
                            <option value="2">上下行</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>设备名称<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="deviceUuid" class="form-control " placeholder="请输入"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>开始管片号<span class="form-bt">(*必填)</span></label>
                        <input type="text" class="form-control" name="startDuctCode">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>结束管片号<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="endDuctCode" class="form-control" placeholder="请输入发现时间"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>开始里程标<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="startMileageFlag"  class="form-control " placeholder="请输入开始环号">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>开始里程<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="startKiMileage" class="form-control " placeholder="请输入结束环号">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>开始里程千米数<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="startHunMileage"  class="form-control " placeholder="请输入开始环号">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>结束里程标<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="endMileageFlag" class="form-control " placeholder="请输入结束环号">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>结束里程<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="endKiMileage"  class="form-control " placeholder="请输入开始环号">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>结束里程千米数<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="endHunMileage" class="form-control " placeholder="请输入结束环号">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>环数<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="ductCount"  class="form-control " placeholder="请输入开始环号">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>设备类型1<span class="form-bt">(*必填)</span></label>
                        <select name="level3"  class="form-control "></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>设备类型2<span class="form-bt">(*必填)</span></label>
                        <select name="level4"  class="form-control "></select>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>具体部位<span class="form-bt">(*必填)</span></label>
                        <select name="deviceType"  class="form-control "></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>病害类型1<span class="form-bt">(*必填)</span></label>
                        <select name="faultType1"  class="form-control "></select>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>病害类型2<span class="form-bt">(*必填)</span></label>
                        <select name="faultType2"  class="form-control "></select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>病害等级<span class="form-bt">(*必填)</span></label>
                        <input type="text" name="faultLevel"  class="form-control " placeholder="请输入">
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!-- /.box-body -->
    <div class="box-footer">
        <%--<button type="reset" class="btn btn-default resetLxsz_btn" name="resetLxsz">重置</button>--%>
        <button type="submit" class="btn btn-primary saveBhgl_btn pull-right" name="saveBhgl">提交</button>
    </div>
</div>
<script src="${ctx }/static/js/module/inspect/fault/log/edit.js"></script>

