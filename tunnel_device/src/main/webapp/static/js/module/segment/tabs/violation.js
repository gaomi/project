var loadImg = false;
$(function () {
    //违规项目
    var ViolationTable = new ViolationTableInit();
    ViolationTable.Init('#aqbhq_list');
    $('#violation_tab a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        if ($(this).attr("href") == "#details") {
            if (loadImg) {
                initAqbhqSwiper();
            }
        }
    });
    $('#searchBtn').click(function () {
        $('#dmcjkz_table').bootstrapTable('refresh', {url: ctxPath + 'dmcjkz/queryDmcjkzTable', silent: true});//url为后台action
    });

});

function initAqbhqSwiper() {
    var galleryThumbs = new Swiper('.gallery-thumbs', {
        spaceBetween: 10,
        slidesPerView: 4,
        lazy: true,
        freeMode: true,
        watchSlidesVisibility: true,
        watchSlidesProgress: true,
    });
    var galleryTop = new Swiper('.gallery-top', {
        spaceBetween: 10,
        mousewheel: {
            enabled: true,
        },
        lazy: true,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        thumbs: {
            swiper: galleryThumbs
        }
    });
}


//违规项目查询
var ViolationTableInit = function () {
    var oTableInit = new Object();
    oTableInit.Init = function (id) {
        var queryUrl = ctx + "/module/segment/aqbhq/list";
        var tableOption = common.tableOptions({
            url: queryUrl,
            queryParams: function (params) {
                var param = new Object();
                var project_name = $.trim($("#project_name").val());
                var metroline_name = $.trim($("#metroline_name").val());
                var aqbhq_status = $.trim($("#aqbhq_status").val());
                if (metroline_name.length > 0) {
                    param.projectlevel = metroline_name;
                }
                if (aqbhq_status.length > 0) {
                    param.status = aqbhq_status;
                }
                if (project_name.length > 0) {
                    param.projectname = project_name;
                }
                /***
                 * 处理异步加载后的参数
                 */
                param = addTableParam(params.search,param);
                param = addTableParam(common.resetSearch,param);
                if(common.resetSearch != null) common.resetSearch=null;

                param.metrolinename = getline(lineId) + "号线";
                //param.noUdUuid = segmentId;
                param.sgement = segmentName;
                param.updown = getDirectionCode();
                param.group_segment = group_segment;
                return {
                    pageSize: params.limit,                         //页面大小
                    pageNum: (params.offset / params.limit) + 1, //页码
                    params: param
                };
            },
            columns: [
                // {checkbox: true, visible: true,  align: 'center', cellStyle: {css: {"text-align": "center"}}},                 //是否显示复选框
                {field: 'PROJECTNAME', title: '项目名称'},
                /*{
                    field: "qvjian", title: "区间", align: 'center',
                    formatter: function (value, row, index) {
                        return row.METROSTATIONNAMESTART + "—" + row.METROSTATIONNAMEEND;
                    }
                },
                {field: 'METROLINENAME', title: '线路', align: 'center'},*/
                {field: 'PROJECTBUILDUNIT', title: '建设单位', align: 'center'},
                {field: 'PROJECTCONSTRUCTIONUNIT', title: '施工单位', align: 'center'},
                // {field: 'PROJECTTYPE', title: '项目类型', align: 'center',width:30},
                {field: 'PROJTASKLASTRESULTSTATUS', title: '项目状态', align: 'center', width: 80},
                {field: 'PROJECTLEVEL', title: '项目等级', width: 80, align: 'center'},
                {field: 'PROJECTADDRESS', title: '地址'},
                {
                    field: 'uStartDuct', title: '影响范围（环）', align: 'center', width: 130,
                    formatter: function (value, row, index) {
                        return row.U_START_DUCT + "~" + row.U_END_DUCT;
                    }
                },
                {
                    field: '', title: '操作', align: 'center', width: 120,
                    formatter: function (value, row, index) {
                        debugger
                        var details = '<div  class="updates_bt"  onclick=devtailsData(\'' + row.PROJECTID + '\')>' +
                            '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;详情</div>';
                        // var Location = '&nbsp;&nbsp;<button type="button"  class="btn btn-primary btn-sm updates" >' +
                        //     '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;区间定位</button>';
                        return details;
                    }
                }],
            onClickRow: function (row, el) {
                ductHuanDy(row.U_START_DUCT);
            },
            responseHandler: function (result) {
                if (result.code == 200) {
                    common.status.tab_violation = segmentId;
                    return result.data;
                } else {
                    return result.message;
                }
            }
        });
        $(id).bootstrapTable(tableOption);
    };
    return oTableInit;
};

function refresh() {
    var obj = {queryTime: new Date().getTime()};
    $("#aqbhq_list").bootstrapTable('resetSearch', JSON.stringify(obj));
}


function devtailsData(id) {
    var array = $("#small-font div[name]");
    $.ajax({
        type: "post",
        url: ctx + "/module/segment/getProjectInfoById",
        data: {id: id},
        dataType: 'json',
        success: function (data) {
            if (data.code == '200') {
                $.each(array, function (i, n) {
                    var t_name = array.get(i).getAttribute("name");
                    $(n).text(data.data[t_name]);
                    $(n).attr("title", data.data[t_name])
                    if (t_name == 'qvjian') {
                        $(n).text(data.data.metrostationnamestart + "—" + data.data.metrostationnameend)
                        $(n).attr("title", data.data.metrostationnamestart + "—" + data.data.metrostationnameend)
                    }

                });
            }
        }
    });
    $('#tab_details').tab('show');
    $.ajax({
        type: "get",
        url: ctx + "/static/js/mock/aqbhqDetail.json",
        // type: "post",
        // url: ctx + "module/segment/aqbhq/detail",
        data: {id: id},
        success: function (data) {
            if (data.code == '200') {
                initAqbhqImg(data.data.ImagePath);
                loadImg = true;
                initAqbhqSwiper();
            }
        }
    });
}


function toList() {
    $('#tab_list').tab('show');
}

function initAqbhqImg(date) {
    initImage(date, 'top');
    initImage(date, 'thumbs');
}

function initImage(date, id) {
    $("#" + id + "_cont").empty();
    $.each(date, function (i, n) {
        // if(id == 'top'){
        //     var tempStr ="";
        //     $("#"+id+"_cont").append(tempStr);
        // }else if(id == 'thumbs'){
        var tempStr = " <div  class=\"swiper-slide\"><img src=\"" + n + "\" class=\"swiper-lazy item_img\"/></div>";
        $("#" + id + "_cont").append(tempStr);
        // }
    });
}