<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">工单审核</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool paoding_btn_hide paoding_btn_hide" data-widget="remove" onclick="$('#commonModal').modal('hide')"><i
                class="fa fa-remove"></i></button>
        </div>
    </div>
    <!-- /.box-header -->
    <div class="box-body box-max-height">
        <div class=" gdshHeader"> </div>
        <div class="col-md-12 table-responsive" id="faultCheckDialog">
            <div class="checkBlock">
                <div class="smallimage">
                    <ul class="menulist">
                    </ul>
                    <div class="next">
                        <a href="javascript:void(0);" class="right carousel-control">
                            <span class="fa fa-angle-right"></span>
                        </a>
                    </div>
                    <div class="prve">
                        <a href="javascript:void(0);" class="left carousel-control">
                            <span class="fa fa-angle-left"></span>
                        </a>
                    </div>
                </div>
            </div>
            <div class="row">
            <div class="col-md-6">
            <div class=" item_check_swiper swiper-container swiper-no-swiping swiper-container-initialized swiper-container-horizontal swiper-container-css-mode big">
                <div class="swiper-wrapper big_imgList" delay="false"></div>
                <div class="swiper-button-next" tabindex="0" role="button" aria-label="Next slide" aria-disabled="false"></div>
                <div class="swiper-button-prev swiper-button-disabled" tabindex="0" role="button" aria-label="Previous slide" aria-disabled="true"></div>
                <div class="swiper-pagination swiper-pagination-bullets"></div>
            </div>
                <div class="groupBtnAll ">
                    <button type="button" class="btn btn-primary offshuyin">关闭水印</button>
                    <button type="button" class="btn btn-primary adopt">通过</button>
                    <button type="button" class="btn btn-primary notPass">驳回</button>
                    <select name="bhBoHui" class="form-control reason" id="">
                        <option value="">原因一</option>
                        <option value="">原因二</option>
                        <option value="">原因三</option>
                    </select>
                </div>
            </div>
            <div class="col-md-6">
                <table id="fault_check_table" class="table table-bordered ">
                </table>
            </div>
            </div>
        </div>
    </div>
    <div class="overlay">
        <i class="fa fa-refresh fa-spin"></i>
    </div>
    <!-- /.box-body -->
    <div class="box-footer">
        <button type="reset" class="btn btn-default paoding_btn_reset" name="reset">重置</button>
        <button type="submit" class="btn btn-primary  pull-right sub_examine" name="save">提交</button>
    </div>

</div>
<script src="${ctx }/static/js/module/inspect/eam/workOrder/checkDialog.js"></script>
