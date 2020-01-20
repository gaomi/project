/** 初始化*/
var faultTypeDict;
//  存储点击图片的下标
var imgIndex = 0;
// 存储病害数据
var bhNav;
// 线站数据
var faultCommon = {};
//  导航图片左右滑动
var pageList = 0;
var lock = true;
// 审核后的数据
var arrPhoto = [];
var checkDialog={};
// 关闭水印的变量
checkDialog.hideSY = true;
checkDialog.text = "";
checkDialog.html = "";
// 设置工单审核的标题
    checkDialog.html+=" <div class='row'> <div class='col-md-4'>公司：<span>"+editRow.COMPANY_WHDW+"</span></div><div class='col-md-3'> 线路：<span>"+editRow.LINENO+"</span></div><div class='col-md-3'>区间: <span>"+
        editRow.START_STATION_NAME+"-"+editRow.END_STATION_NAME+"</span></div><div class='col-md-2'> 上下行：<span>"+editRow.HIGHLOW+"</span></div></div>"+
        "<div class='row'><div class='col-md-4'>工单编号：<span>"+editRow.PLAN_NO+"</span></div><div class='col-md-3'> 状态：<span>"+editRow.STATUS_T+"</span></div><div class='col-md-3'> 类型：<span>"+
        editRow.PARTDESC+"</span></div><div class='col-md-2'> 检查类型：<span>"+editRow.PLANNATURE+"</span></div></div>";
$(".gdshHeader").html(checkDialog.html);
/**
 * 获取病害数据
 */
function getBhData() {
    var paramsData = {status: '10', orderId: editRow.UUID, lineCode: editRow.LINENO};
    $.ajax({
        url: ctx + '/module/fault/check/getFaultByWorkOrder',
        type: "post",
        dataType: "json",
        contentType: 'application/json',
        data: JSON.stringify({
            "pageNum": 0,
            "pageSize": 0,
            "params": paramsData
        }),
        success: function (data) {
            bhNav = data.data;
            if (data.data.length > 0) {
                navLunBo(bhNav);
            }
            $MB.removeOverlay('overlay');
        },
        error: function (err) {
            console.log(err)
        }
    })
}

getBhData();

function search() {
}


/**
 * 图片索引
 * @param data
 */
function navLunBo(data) {

    var topHtml = '';
    $.each(data, function (i, n) {
        var time = timestampToTime(n.insertDate);//时间转换

        topHtml += "<li data-imglist=" + n.uuid + "><p>环号: <span>" + n.startDuctCode + "</span></p><p><span>" + n.startMileageFlag +
            "</span><span>" + n.startKiMileage + "</span><span>" + n.startHunMileage + "</span></p><p><span>" +
            n.status + "</span></p><div class='linkage'style='display:none;'><p>设备名称：" + n.lineCode +
            "</p><p>设备编号：" + n.planUuid + "</p><p>病害类型：<span>" + n.fault_Type_1 + "</span>,<span>" + n.fault_Type_2 +
            "</span>,<span>" + n.device_type_1 + "</span>,<span>" + n.faultLevel +
            "</span></p><p>起始环号：<span>" + n.startDuctCode + "</span>-<span>" + n.endDuctCode + "</span> 环数：<span>" + n.ductCount +
            "</span></p><p>里程范围：<span>" + n.startMileageFlag + "</span> <span>" + n.startKiMileage + "</span><span>" + n.startHunMileage +
            "</span>-<span>" + n.endKiMileage + "</span><span>" + n.endHunMileage + "</span></p><p>病害状态：" + n.status +
            " </p><p>病害量：<span>" + n.faultQuantity + "</span><span>" + n.faultUnit + "</p><p class='porson'>操作人：" + n.operatorNo +
            "</p><p>时间：" + time + "</p></div></li>";

    });

    var _menuList = $('.menulist');

    _menuList.html(topHtml);
    _menuList.css({
        "width": ($('.menulist li').length + 1) * 114
    });
    if ($('.menulist li').length > 8) {
        $('.next').css({display: 'block'});
        $('.prve').css({display: 'block'});
    }
    // 第一张图片
    var src = $('.menulist li').eq(0).attr("data-imglist");
    checkDialog.text = $('.menulist li').eq(0).find('.linkage').html();
    $(".menulist li").eq(0).click();
}

/**
 * 展示大图
 * @param data
 * @param src
 * @param text
 */
function smileImgList(data, src, text) {
    $(".big").fadeOut(400, function () {
        var bigSrc = '';
        // $.each(data, function (i, n) {
        //     if (n.uuid == src) {
        //         if (n.imageList.length == 0) {
        if (data.length == 0) {
            bigSrc += " <div class=\"swiper-slide\"><img src='" + ctx + "/static/images/zwtp.jpg' style=\"margin-top:130px\" alt=''/></div>";
        } else {
            // $.each(n.imageList, function (j, m) {
            $.each(data, function (j, m) {
                bigSrc += "<div class=\"swiper-slide\"><img src=" + m + " onload = 'getImage(this)' alt='' class='' /><div class='outer-ceng' style='padding-top:20px;'></div></div>";
            })
        }
        // if (n.imageList.length <= 1) {
        if (data.length <= 1) {
            $('.big>div:not([ delay="false"])').css({display: 'none'});
        } else {
            $('.big>div:not([ delay="false"])').css({display: 'block'});
        }
        // }
        // });
        $(".big_imgList").html(bigSrc);
        var itemCheck = new Swiper({
            el: '.item_check_swiper',
            paginationClickable: true,
            slideToClickedSlide: true,
            mousewheel: {
                enabled: false,
            },
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            }
        });
        $(".big").fadeIn(400);
    });
}
function getImage(thas){
    $(thas).next().css({
        "width": $(thas).width(),
        "height": $(thas).height(),
        "left": (450 - $(thas).outerWidth()) / 2,
        "color": "#fff"
    });
    $(thas).next().html(checkDialog.text)
}

/**
 * 点击通过和不通过提交
 *
 * @param thas
 */
function slideNav(thas) {
    // if(lock){
    var objPhoto = {}, bhTableID;
    bhTableID = $('.menulist li').eq(imgIndex).attr('data-imglist');
    if (imgIndex == $('.menulist li').length - 1) {
        imgIndex = imgIndex;
    } else {
        imgIndex++;
    }
    $.each(bhNav, function (i, n) {
        if (bhTableID == n.uuid) {
            objPhoto = n;
            changeStatus(n,thas);
        }
    });
    objPhoto.faultPingYv = thas;
    if (arrPhoto.indexOf(objPhoto) < 0) {
        arrPhoto.push(objPhoto);
    }
    $('#fault_check_table').bootstrapTable("load", arrPhoto);
    // 改变图片展示左右滑动
    if (imgIndex > 4) {
        var length = $('.menulist li').length;
        if (pageList >= 0 & pageList < length - 8) {
            pageList++;
            $('.menulist').animate({marginLeft: -pageList * 114 + 'px'}, 400, function () {
                // lock = true
            })
            // lock = false;
        }
    }
    // 顶部样式
    $('.menulist li').eq(imgIndex).css({boxShadow: "0 0 4px #000"}).siblings().css({boxShadow: "0 0 1px gray"});
    var src = $('.menulist li').eq(imgIndex).attr('data-imglist');
    checkDialog.text  = $('.menulist li').eq(imgIndex).find('.linkage').html();
    // smileImgList(bhNav,src,text);
    $(".menulist li").each(function (i, n) {
        if (i === imgIndex) {
            $(this).click()
        }
    });
    // }
}

;!function () {
    function addFunctionAlty() {
        return [
            '<button type="button" class="btn-primary btn-xs paoding_btn_detail" style="margin-right:15px;" ><i class="fa fa-pencil-square-o" ></i>&nbsp;查看</button>',
            '<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;" paoding-modal-size="800_600" paoding_opt="Check"><i class="fa fa-pencil-square-o" ></i>&nbsp;任务审核</button>',
            '<button type="button" class="btn-default btn-xs paoding_btn_update" style="margin-right:15px;"><i class="fa fa-pencil-square-o" ></i>&nbsp;驳回</button>',
        ].join('');

    }

    var operateEvents = {
        'click .paoding_btn_edit': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.uuid;
            editRow = row;
            openEditPage('eam_workOrder', editRow, _clickBtn);
        }
    };


    $('.next').click(function () {
        var length = $('.menulist li').length;
        if (pageList >= 0 && pageList < length - 8) {
            pageList++;
            $('.menulist').animate({marginLeft: -pageList * 114 + 'px'}, 400, function () {
                // lock = true;
            })
        }
    });
    $('.prve').click(function () {
        if (pageList > 0) {
            pageList--;
            $('.menulist').animate({marginLeft: -pageList * 114 + 'px'}, 400, function () {
                // lock = true;
            })
        }
    });
    $(document).on('click', '.menulist li', function () {
        imgIndex = $(this).index();
        $(this).css({boxShadow: "0 0 4px #000"}).siblings().css({boxShadow: "0 0 1px gray"});
        var src = $(this).attr("data-imglist");
        if(checkDialog.hideSY){
            checkDialog.text = $('.menulist li').eq(imgIndex).find('.linkage').html();
        }else{
            checkDialog.text = "";
        }

        // smileImgList(bhNav,src,text)
        $.each(bhNav, function (i, n) {
            if (src == n.uuid) {
                smileImgList(n.imageList, src, checkDialog.text)
            }
        });
        return false;
    });

    // 点击通过和不通过提交
    $('.adopt').click(function () {
        $('.menulist li').eq(imgIndex).css({"backgroundColor": "rgb(0, 128, 0,0.2)"});
        slideNav("正常");
    });
    $('.notPass').click(function () {
        $('.menulist li').eq(imgIndex).css({"backgroundColor": "rgb(255, 0, 0,0.2)"});
        var bhSelectOn = $("select[name=bhBoHui] option:selected").text();
        slideNav(bhSelectOn);
    });
    $('.sub_examine').click(function () {
        if(arrPhoto.length < bhNav.length){
            $MB.n_warning("请审核完毕再提交！");
            return;
        }
        var newArrphoto = [];
        $.ajax({
            type: 'post',
            url: ctx + '/module/fault/check/commitFaultByWorkOrder',
            data: JSON.stringify({
                params: {
                    faultList: arrPhoto,
                    orderId: editRow.UUID
                }
            }),
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                if (data.code === 200) {
                    $MB.n_success("提交成功");
                    $MB.refreshTable("eam_workOrder_table");
                    closeModal();
                } else {
                    $MB.n_danger(data.message);
                }
            }
        })
    });
    $('#fault_check_table').bootstrapTable({
        cache: false,
        // height:400,
        striped: true,
        pagination: true,
        pageSize: 7,
        pageNumber: 1,
        pageList: [7,15,20],
        sidepagination: 'client',
        search: false,
        showRefresh: false,
        showExport: false,
        exportTypes: ['csv', 'txt', 'xml'],
        clickToselect: true,
        columns: [
            {field: "startDuctCode", title: "环号"},
            {
                field: "faultPingYv",
                title: "是否通过", formatter: function (value) {
                    if (value == "正常") {
                        return "通过"
                    } else{
                        return "不通过"
                    }
                }
            },
            {field: "faultPingYv", title: "评语"},
            {field: "status", title: "状态"},

            {
                field: "uuid", title: "id", visible: false, formatter: function (value, row) {
                    $(row).attr("data-tag", value)
                }
            }
        ],
        onClickRow: function (row, el) {
            for (var i = 0; i < $(".menulist li").length; i++) {
                if ($(".menulist li").eq(i).attr("data-imglist") === row.uuid) {
                    imgIndex = i;
                    $(".menulist li").eq(i).click();
                    // $(".menulist li").eq(imgIndex).css({boxShadow: "0 0 4px #000"}).siblings().css({boxShadow: "0 0 1px gray"})
                    // var src = $(".menulist li").eq(imgIndex).attr("data-imglist");
                    // var text = $('.menulist li').eq(imgIndex).find('.linkage').html();
                    // smileImgList(bhNav, src, text);
                    var length = $('.menulist li').length;
                    if (imgIndex >= 0 && imgIndex <= 4) {
                        $('.menulist').css({marginLeft: "0"});
                    } else if (imgIndex > 4 && imgIndex <= length - 8) {
                        pageList = (imgIndex - 4);
                        $('.menulist').animate({marginLeft: -pageList * 114 + 'px'}, 400);
                    } else if (imgIndex >= length - 8) {
                        pageList = length - 8
                        $('.menulist').animate({marginLeft: -(length - 8) * 114 + 'px'}, 400);
                    }
                }
            }
        },
        data: arrPhoto
    });
}();

function changeStatus(data,thas){
    if("正常" === thas){
        switch (data.faultStatus) {
            case '10':
                data.faultStatus='20';
                break;
            case '35':
                if(data.isFinish =='0'){
                    data.faultStatus='20';
                    data.isFinish='0';
                }else{
                    data.faultStatus='60';
                }
                break;
            case '20':
                data.faultStatus='35';
                break;
            default:

        }

    }else{
        switch (data.faultStatus) {
            case '10':
                data.faultStatus='01';
                break;
            case '20':
                data.faultStatus='20';
                data.isFinish='0';
                break;
            default:

        }

    }
}

// 关闭水印按钮
$(".offshuyin").click(function () {
    if(checkDialog.hideSY){
        checkDialog.hideSY=false;
        $(this).text("打开水印")
    }else{
        checkDialog.hideSY=true;
        $(this).text("关闭水印")
    }
    $(".menulist li").eq(imgIndex).click()
});