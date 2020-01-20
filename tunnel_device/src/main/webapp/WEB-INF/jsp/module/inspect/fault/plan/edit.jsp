<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">施工计划信息</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool paoding_btn_hide paoding_btn_hide" data-widget="remove" onclick=" $('#commonModal').modal('hide');"><i
                class="fa fa-remove"></i></button>
        </div>
    </div>
    <!-- /.box-header -->
    <div class="box-body">

        <form id="fault_plan_edit_form">
            <div class="post">
                <div class="row">
                    <input type="hidden" name="id">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>施工单号（自动生成）</label>
                            <input type="text" name="PLAN_NO" class="form-control input-sm" placeholder="无">
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>*站点类型（必选）</label>
                            <div class="row">
                                <div class="col-sm-5">
                                    <div class="edit_radio_div">
                                        <input type="radio" name="STATION_TYPE" value="1" checked="">
                                        线路
                                    </div>
                                </div>
                                <div class="col-sm-5">
                                    <div class="edit_radio_div">
                                        <input type="radio" name="STATION_TYPE" value="0">
                                        基地
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>*线路</label>
                            <select class="form-control input-sm select2" name="LINE_CODE">
                                <option selected="selected">1号线</option>
                                <option>2号线</option>
                                <option>option 3</option>
                                <option>option 4</option>
                                <option>option 5</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>*专业</label>
                            <select class="form-control input-sm select2" id="edit_major_type" name="MAJOR">
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <input type="hidden" name="id">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>*上下行</label>
                            <select class="form-control input-sm select2" name="UPDOWN">
                                <option>上行</option>
                                <option>下行</option>
                                <option>无</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>*登记站</label>
                            <select class="form-control input-sm select2" name="REGIST_STATION">
                                <option>大华三路站</option>
                                <option>大华三路站</option>
                            </select>

                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>*开始站</label>
                            <select class="form-control input-sm select2" name="START_STATION_NAME">
                                <option>大华三路站</option>
                                <option>大华三路站</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>*结束站</label>
                            <select class="form-control input-sm select2" name="END_STATION_NAME">
                                <option>大华三路站</option>
                                <option>大华三路站</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>*计划性质</label>
                            <select class="form-control input-sm select2" id="edit_plan_type" name="PLAN_TYPE">
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>*工作类型</label>
                            <select class="form-control input-sm select2" id="edit_work_type" name="WORK_TYPE">
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>*计划开始时间</label>
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" name="START_TIME" class="form-control input-sm time-select" placeholder="无" id="begin_time">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>*计划结束时间</label>
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" name="END_TIME" class="form-control input-sm time-select" placeholder="无" id="end_time">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <input type="hidden" name="id">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>施工单位名称</label>
                            <select name="COMPANY" id="company" class="form-control input-sm"></select>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>*负责人姓名</label>
                            <input type="password" name="LEADER_UUID" class="form-control input-sm select2" value="123456" placeholder="无">
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>电话</label>
                            <input type="text" name="telephone" class="form-control input-sm" placeholder="无">
                        </div>
                    </div>
                    <div class="col-md-3">
                    </div>
                </div>
                <div class="row">
                    <input type="hidden" name="id">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>是否停电</label>
                            <input type="text" name="telephone" class="form-control input-sm" placeholder="无">
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>停电开始范围</label>
                            <input type="password" name="password" class="form-control input-sm" value="123456" placeholder="无">
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>停电结束范围</label>
                            <input type="text" name="telephone" class="form-control input-sm" placeholder="无">
                        </div>
                    </div>
                    <div class="col-md-3">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>*施工影响说明（限300字）</label>
                            <textarea class="form-control input-sm" name="INFLUENCE" rows="3" placeholder="无"></textarea>
                        </div>
                        <div class="form-group">
                            <label>*安全措施（限300字）</label>
                            <textarea class="form-control input-sm" name="PREVENTION" rows="3" placeholder="无"></textarea>
                        </div>
                        <div class="form-group">
                            <label>*详细描述（限300字）</label>
                            <textarea class="form-control input-sm" name="DETAIL_DESC" rows="3" placeholder="无"></textarea>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>*执行人员选择</label>
                            <select class="form-control input-sm select2" multiple="multiple" name="EXECUTIVES">
                                <option>option 1</option>
                                <option>option 2</option>
                                <option>option 3</option>
                                <option>option 4</option>
                                <option>option 5</option>
                            </select>
                        </div>


                    </div>
                </div>
            </div>
            <div class="post">
                <div class="row">
                    <%--http://www.jiaoben123.com/uploadfiles/demo/d19cb85fac5b4c74bb4e387852f7d23b/#home--%>
                    <div class="col-md-5">
                        <select name="from" id="p_keepRenderingSort" class="form-control" size="8" multiple="multiple">
                            <option value="1">Item 1</option>
                            <option value="2">Item 5</option>
                            <option value="2">Item 2</option>
                            <option value="2">Item 4</option>
                            <option value="3">Item 3</option>
                            <option value="1">Item 1</option>
                            <option value="2">Item 5</option>
                            <option value="2">Item 2</option>
                            <option value="2">Item 4</option>
                            <option value="3">Item 3</option>
                            <option value="1">Item 1</option>
                            <option value="2">Item 5</option>
                            <option value="2">Item 2</option>
                            <option value="2">Item 4</option>
                            <option value="3">Item 3</option>
                            <option value="1">Item 1</option>
                            <option value="2">Item 5</option>
                            <option value="2">Item 2</option>
                            <option value="2">Item 4</option>
                            <option value="3">Item 3</option>
                        </select>
                    </div>

                    <div class="col-md-2">
                        <button type="button" id="p_keepRenderingSort_rightAll" class="btn btn-block"><i class="glyphicon glyphicon-forward"></i></button>
                        <button type="button" id="p_keepRenderingSort_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
                        <button type="button" id="p_keepRenderingSort_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
                        <button type="button" id="p_keepRenderingSort_leftAll" class="btn btn-block"><i class="glyphicon glyphicon-backward"></i></button>
                    </div>

                    <div class="col-md-5">
                        <select name="to" id="p_keepRenderingSort_to" class="form-control" size="8" multiple="multiple"></select>
                    </div>
                </div>
            </div>
            <div class="post">
                <div class="row">
                    <%--http://www.jiaoben123.com/uploadfiles/demo/d19cb85fac5b4c74bb4e387852f7d23b/#home--%>
                    <%--https://blog.csdn.net/u012796085/article/details/79970339--%>
                    <div class="col-md-5">
                        <select name="from" id="d_keepRenderingSort" class="form-control" size="8" multiple="multiple">
                            <option value="1">Item 1</option>
                            <option value="2">Item 5</option>
                            <option value="2">Item 2</option>
                            <option value="2">Item 4</option>
                            <option value="3">Item 3</option>
                            <option value="1">Item 1</option>
                            <option value="2">Item 5</option>
                            <option value="2">Item 2</option>
                            <option value="2">Item 4</option>
                            <option value="3">Item 3</option>
                            <option value="1">Item 1</option>
                            <option value="2">Item 5</option>
                            <option value="2">Item 2</option>
                            <option value="2">Item 4</option>
                            <option value="3">Item 3</option>
                        </select>
                    </div>

                    <div class="col-md-2">
                        <button type="button" id="d_keepRenderingSort_rightAll" class="btn btn-block"><i class="glyphicon glyphicon-forward"></i></button>
                        <button type="button" id="d_keepRenderingSort_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
                        <button type="button" id="d_keepRenderingSort_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
                        <button type="button" id="d_keepRenderingSort_leftAll" class="btn btn-block"><i class="glyphicon glyphicon-backward"></i></button>
                    </div>

                    <div class="col-md-5">
                        <select name="to" id="d_keepRenderingSort_to" class="form-control" size="8" multiple="multiple"></select>
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
<script src="${ctx }/static/js/module/inspect/fault/plan/edit.js"></script>


