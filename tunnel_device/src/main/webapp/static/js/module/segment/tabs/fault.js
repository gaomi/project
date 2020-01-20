var diseSwiper;
$(function () {
    //     //病害项目，初始化表格
    var diseTable = new disePorjectTable();
    diseTable.Init('#dise_list');
    $(".contest-two .row:last").css({borderRight: "2px solid #c2d1e8"})
});

function toLvLiList(id) {
    var param = {id: id};
    var params = {params: param};
    $.ajax({
        type: "post",
        url: ctx + "/module/segment/getBhLvli",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(params),
        success: function (data) {
            if (data.code == '200') {
                if (data.data.length < 1) {
                    alert("暂无履历");
                } else {
                    $('#myFaultTab li:eq(1) a').tab('show');
                }
                initBhTimeLine(data.data);
            }
        }
    });

}

function initBhTimeLine(data) {
    $("#fault_time_line").empty();
    var str_heart = "<li><i class=\"fa fa-camera bg-purple\"></i><div class=\"timeline-item \"><div class=\"panel-default\">" +
        "<div class=\"timline_title\" data-toggle=\"collapse\" data-parent=\"#accordion\"";
    var str_end = "</div></div></div></li>";
    $.each(data, function (i, n) {
        var str = " data-content='" + JSON.stringify(n) + "' >小类名称：" + n.smallTypeName + "<br/><span><i class=\"fa fa-clock-o\"></i>" + n.recCreateTime + "</span></div><div id=\"collapse_" + i + "\" class=\"panel-collapse collapse\">";
        $("#fault_time_line").append(str_heart + str + str_end);
    });
    $(".timline_title").click(function () {
        var obj_text = $(this).attr("data-content");
        var obj = JSON.parse(obj_text);
        var item = $("#dise_Lvli_body label[name]");
        $.each(item, function (i, n) {
            var attr = $(n).attr("name");
            for (key in obj) {
                if (attr == key) {
                    $(n).text(obj[key]);
                }
            }
        });
        $("#create_date").text(obj.recCreateTime);
    });
}

function toFaultList() {
    $('#myFaultTab li:eq(0) a').tab('show');
}

//初始化病害swiper图
function initDiseSilder(id, arr) {
    $("#" + id).empty();
    for (var i = 0; i < arr.length; i++) {
        var str = "<div class=\"swiper-slide\" onclick=\"showPicture('" + arr[i].IMAGE_URL + "')\">" +
            // "<div class='img-desc'><p>病害等级："+arr[i].dise.FAULT_LEVEL+"</p><p>大类名称："+arr[i].dise.BIG_TYPE_NAME+"</p><p>详细位置："+arr[i].dise.DETAIL_LOC+"</p><p>设备名称："+arr[i].dise.DEVICE_NAME+"</p></div>"+
            "<img data-src='" + arr[i].IMAGE_URL + "' src='" + arr[i].IMAGE_URL + "' class=\"swiper-lazy item_img\"/>" +
            "</div>";
        $("#" + id).append(str);
    }
    // let falge=false;
    // if(arr.length>1){
    //     falge=true
    // };
    if ($("#tab_dise").hasClass("active")) {
        diseSwiper = new Swiper({
            el: '#dise_img',
            preventClicksPropagation: true,
            lazy: true,
            mousewheel: {
                enabled: true,
            },
            loop: true,
            pagination: {
                el: '.swiper-pagination',
            },
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
        });
        diseSwiper.lazy.load();
    }
}

//显示图片
function showPicture(url) {
    var consont = common.diseClickRow;
    var str = "等级:" + consont.FAULT_LEVEL + "&nbsp;&nbsp;&nbsp;&nbsp;" + consont.LINE_ID + "号线&nbsp;&nbsp;&nbsp;&nbsp;" + (consont.DEVICE_NAME ? consont.DEVICE_NAME : '无') + "&nbsp;&nbsp;&nbsp;&nbsp;环号:" + consont.DUCT_CODE + "~" + consont.DUCT_CODE2 + "&nbsp;&nbsp;&nbsp;&nbsp;" + consont.FAULT_TYPE;
    $("#myModalLabel").html(str);
    $("#diseModelImg").attr("src", url);
    $('#diseImgModal').modal('show');
}

//病害项目查询
var disePorjectTable = function () {
    var diseTable = new Object();
    diseTable.Init = function (id) {
        //记录页面bootstrap-table全局变量$table，方便应用
        var queryUrl = ctx + "/module/segment/getBhInfo";
        var tableOption = common.tableOptions({
            url: queryUrl,
            queryParams: function (params) {
                var param = new Object();
                param.segmentUuid = segmentId;
                param.group_segment = group_segment;
                param.lineCode = lineId;
                var diseKey = $.trim($("#diseKey").val());
                if (diseKey.length > 0) {
                    param.diseKey = diseKey;
                }
                param.status = "20";
                /***
                 * 处理异步加载后的参数
                 */
                param = addTableParam(params.search,param);
                param = addTableParam(common.resetSearch,param);
                if(common.resetSearch != null) common.resetSearch=null;
                return {
                    pageSize: params.limit,                         //页面大小
                    pageNum: (params.offset / params.limit) + 1, //页码
                    params: param
                };
            },
            columns: [
                {field: 'BIG_TYPE_NAME', title: '大类名称'},
                {field: 'SMALL_TYPE_NAME', title: '小类名称'},
                {field: 'FAULT_TYPE', title: '病害类型'},
                {field: 'FAULT_LEVEL', title: '病害等级'},
                {field: 'DETAIL_LOC', title: '详细位置'},
                {
                    field: 'ductcodes', title: '环号范围', formatter: function (value, row, index) {
                        return (row.DUCT_CODE ? row.DUCT_CODE : '无') + " - " + (row.DUCT_CODE2 ? row.DUCT_CODE2 : '无');
                    }
                },
                {
                    field: 'DUCT_NUM', title: '影响环数', formatter: function (value, row, index) {
                        return row.DUCT_NUM;
                    }
                },
                // {field: 'DEVICE_NAME', title: '设备名称'},
                {
                    field: '', title: '履历', align: 'center',
                    formatter: function (value, row, index) {
                        var details = '<div  class="updates_bt dise-lvli" onclick="showlvli(\'' + row.FAULT_ID + '\',event)" >' +
                            '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;履历</div>';
                        return details;
                    }
                }
            ],
            responseHandler: function (result) {
                if (result.code == 200) {
                    common.status.tab_dise = segmentId;
                    for (var i = 0; i < result.data.image.length; i++) {
                        $.each(result.data.rows, function (j, n) {
                            if (result.data.image[i].INTERNAL_CODE == n.INTERNAL_CODE) {
                                result.data.image[i].dise = n;
                            }
                        });
                    }
                    // initDiseSilder(options.siwper, result.data.image);
                    common.diseImg = result.data.image;

                    return result.data;
                } else {
                    return result.message;
                }
            },
            onClickRow: function (row, el) {
                var row_duct_code = parseInt(row.DUCT_CODE)
                ductHuanDy(row_duct_code);
                common.diseClickRow = row;
                $('#dise-desc').css('display', 'block');
                var rowlist = el.parent().find('.row-barck');
                if (rowlist.length > 0) {
                    rowlist.removeClass('row-barck');
                }
                el.addClass('row-barck');
                if (common.diseImg != undefined) {
                    var selectImg = new Array();
                    $.each(common.diseImg, function (i, n) {
                        if (n.INTERNAL_CODE == row.INTERNAL_CODE) {
                            selectImg.push(n);
                        }
                    });
                    if (selectImg.length > 0) {
                        initDiseSilder("dise_cont", selectImg);
                    } else {
                        initDiseSilder("dise_cont", [{"IMAGE_URL": ctx + "/static/images/zwtp.jpg"}]);
                    }
                }
                var lableText = $(".contest-one div[name]");
                $.each(lableText, function (i, n) {
                    var attr = $(n).attr("name");
                    $(n).text(row[attr]);
                });
            }
        });
        $(id).bootstrapTable(tableOption);
        $(id).bootstrapTable("hideLoading");
    };
    return diseTable;
};

function changTable(status) {
    if (status == '20') {
        changeDise(status);
        var obj = {queryTime: new Date().getTime()};
        obj.status = '20';
        $("#dise_list").bootstrapTable('resetSearch', JSON.stringify(obj));
    } else {
        changeDise(status);
        var obj = {queryTime: new Date().getTime()};
        obj.status = '60';
        $("#dise_list").bootstrapTable('resetSearch', JSON.stringify(obj));
    }
}

function showlvli(id, e) {
    // alert(id);
    toLvLiList(id);
    e.stopPropagation();
}

function queryDise() {
    var obj = new Object();
    obj.time = new Date().getTime();
    $("#dise_list").bootstrapTable('resetSearch', JSON.stringify(obj));
}