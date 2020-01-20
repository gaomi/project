<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>

<div class="box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">设备审核</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool paoding_btn_hide paoding_btn_hide" data-widget="remove" onclick="$('#commonModal').modal('hide')"><i
                    class="fa fa-remove"></i></button>
        </div>
    </div>
    <!-- /.box-header -->
            <div class="box">
                <div class="box-body  box-max-height">
                    <div class=" sbshHeader"> </div>
                    <div class="sbshBtnCha">
                        <form class="form-inline">
                            <div class="form-group form_timeGd">
                                <label class="sr-only">时间</label>
                                <div class="input-group">
                                    <div class="input-group-addon">上传时间</div>
                                    <select class="form-control" id="check_query_date">
                                    </select>
                                </div>
                            </div>
                                <button type="button" class="btn btn-primary pull-right query_checkTable">查询</button>
                        </form>

                    </div>
                    <div class="xqMiddleAll">
                        <table  id="device_edit_table" class="table table-bordered table-striped text-nowrap">
                        </table>
                    </div>
                    <div class="xqBottomAll">
                        <div class="col-md-5 xqMenuLeft" style="padding-left: 0;">
                            <p class="MenuChildHead"><span>部位</span><span>附件数</span></p>
                            <ul >
                            </ul>
                        </div>
                        <div class="col-md-7">
                            <div class=" device_check_swiper swiper-container swiper-no-swiping swiper-container-initialized swiper-container-horizontal swiper-container-css-mode">
                                <div class="swiper-wrapper check_imageList"></div>
                                <div class="swiper-button-next" tabindex="0" role="button" aria-label="Next slide" aria-disabled="false"></div>
                                <div class="swiper-button-prev swiper-button-disabled" tabindex="0" role="button" aria-label="Previous slide" aria-disabled="true"></div>
                                <div class="swiper-pagination swiper-pagination-bullets"></div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
    <div class="box-footer">
        <button type="reset" class="btn btn-default paoding_btn_reset" name="reset">重置</button>
        <button type="submit" class="btn btn-primary  pull-right sub_examine" name="save">提交</button>
    </div>
</div>

    <script src="${ctx }/static/js/module/inspect/device/edit.js"></script>