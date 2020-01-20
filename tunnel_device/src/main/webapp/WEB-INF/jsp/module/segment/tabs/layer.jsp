<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div style="width: 100%;height: 100%;">
    <img id="tab_pmt_img" src="${ctx}/static/images/segment/image069.png" isLoad="false" onclick="showPriceture(this)" width="100%"/>
    <!--模态框-->
    <div class="modal fade" id="layer_ImgModal" tabindex="-1" role="dialog"
         aria-labelledby="diseImgModalLabel"
         aria-hidden="true"
    >
        <div class="modal-dialog" style="width: 80%;height: 80%; ">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                </div>
                <div class="modal-body diserBodyBox">
                    <style>
                        .diserBodyBox {
                            overflow: auto;
                        }

                        .diserModelPag {
                            width: 1366px;
                            height: 768px;
                            overflow: hidden;
                            position: relative;
                            /*border: 1px solid #000;*/
                        }

                        .diserModelPag img {
                            position: absolute;
                            height: 100%;
                            width: auto;
                            cursor: move;
                        }
                    </style>
                    <div class="diserModelPag" onmousewheel="return bbimg(this)">
                        <img id="diserModelImg" class="item_img"/>
                    </div>
                    <script>
                        var pmtParams = {
                            zoomVal: 1,
                            left: 0,
                            top: 0,
                            currentX: 0,
                            currentY: 0,
                            flag: false
                        };

                        //图片缩放
                        function bbimg(o) {
                            var o = o.getElementsByTagName("img")[0];
                            pmtParams.zoomVal += event.wheelDelta / 1200;
                            if (pmtParams.zoomVal >= 0.2) {
                                o.style.transform = "scale(" + pmtParams.zoomVal + ")";
                            } else {
                                pmtParams.zoomVal = 0.2;
                                o.style.transform = "scale(" + pmtParams.zoomVal + ")";
                                return false;
                            }
                        }

                        //获取相关CSS属性
                        var getCss = function (o, key) {
                            return o.currentStyle ? o.currentStyle[key] : document.defaultView.getComputedStyle(o, false)[key];
                        };
                        //拖拽的实现
                        var startDrag = function (bar, target, callback) {
                            if (getCss(target, "left") !== "auto") {
                                pmtParams.left = getCss(target, "left");
                            }
                            if (getCss(target, "top") !== "auto") {
                                pmtParams.top = getCss(target, "top");
                            }
                            //o是移动对象
                            bar.onmousedown = function (event) {
                                pmtParams.flag = true;
                                if (!event) {
                                    event = window.event;
                                    //防止IE文字选中
                                    bar.onselectstart = function () {
                                        return false;
                                    }
                                }
                                var e = event;
                                pmtParams.currentX = e.clientX;
                                pmtParams.currentY = e.clientY;
                            };
                            document.onmouseup = function () {
                                pmtParams.flag = false;
                                if (getCss(target, "left") !== "auto") {
                                    pmtParams.left = getCss(target, "left");
                                }
                                if (getCss(target, "top") !== "auto") {
                                    pmtParams.top = getCss(target, "top");
                                }
                            };
                            document.onmousemove = function (event) {
                                var e = event ? event : window.event;

                                if (pmtParams.flag) {
                                    var nowX = e.clientX,
                                        nowY = e.clientY;
                                    var disX = nowX - pmtParams.currentX,
                                        disY = nowY - pmtParams.currentY;
                                    target.style.left = parseInt(pmtParams.left) + disX + "px";
                                    target.style.top = parseInt(pmtParams.top) + disY + "px";

                                    if (typeof callback == "function") {
                                        callback((parseInt(pmtParams.left) || 0) + disX, (parseInt(pmtParams.top) || 0) + disY);
                                    }

                                    if (event.preventDefault) {
                                        event.preventDefault();
                                    }
                                    return false;
                                }


                            }
                        };
                        startDrag(document.getElementById("diserModelImg"), document.getElementById("diserModelImg"));
                    </script>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                </div>
            </div>
        </div><!-- /.modal -->
    </div>
    <script>
        setPicUrl('tab_pmt_img');

        function setPicUrl(id) {
            var resurl = $("#segment_select option:selected").attr("station");
            if (resurl === '05-06') {
                $("#" + id).attr("src", ctx + "/static/images/segment/07_sl/" + resurl + ".gif");
            } else {
                $("#" + id).attr("src", ctx + "/static/images/segment/07_sl/" + resurl + ".png");
            }
        }

        // 显示大图片
        function showPriceture(url) {
            setPicUrl('diserModelImg');
            var resVal = 1;
            $("#diserModelImg").css({transform: "scale(" + resVal + ")"});
            $("#diserModelImg").css({left: 0, top: 0})
            $('#layer_ImgModal').modal('show');
        }
    </script>
</div>