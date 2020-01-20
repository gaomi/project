$(function () {
    $("#gisView")[0].src = ctx + "/map/map";
});

function getProData(o) {
    var acolor = {"background": "#fff", "color": "#000"};
    $("#proBtns li").css({"background": "#fff", "color": "#000"})
    var text = $(o).attr('data_uuid');
    var color = $(o).attr("data-color");
    var type = $(o).attr("data-type");
    $(o).css({"background": color, "color": "#fff"}).siblings().css(acolor);
    $.ajax({
        type: 'post',
        url: ctx + '/map/get' + type + 'DataForMap',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "pageNum": 0,
            "pageSize": 0,
            "params": {
                "LINE_UUID": text
            }
        }),
        success: function (data) {
            loadXY(type, data.data);
        }, error: function (e) {
            console.log(e);
        }
    })
}

function showModal() {
    //debugger;
    var modalLeft = 0;
    var modalWidth = 1100;
    if (document.body.clientWidth * 0.9 > modalWidth) {
        modalLeft = document.body.clientWidth * 0.1 / 2 - 10;
        modalWidth = document.body.clientWidth * 0.9;
    } else if (document.body.clientWidth > modalWidth) {
        modalLeft = (document.body.clientWidth - modalWidth) / 2 - 10;
    }

    $('#myModal').find('.modal-dialog').css({
        'margin-left': modalLeft,
        'width': modalWidth
    });

    $('#myModal').modal({
        backdrop: 'static',
        remote: ctx + 'aqbhq',
    });
    var modalLeft = 0;
}

// 监护监测
var popoverTc = false;
var popoverwg = false;
// // //点击外侧关闭多选

// $.each(["jhShow", "jhShow"], function (i, n) {
//     $("#select_" + n).on("click", function (e) {
//         e.stopPropagation();
//         $(".jhjcsin").on("click", function (e) {
//             e.stopPropagation();
//         });
//         $(".wgxmMenu").on("click", function (e) {
//             e.stopPropagation();
//         });
//     });
// });

//显示多选框
function jsWgShow(t, id) {
    switch (id) {
        case "jhShow":
            if (popoverTc) {
                $("#" + id).css("display", "none");
                popoverTc = false;
                return "";
            }
            popoverTc = true;
            break;
        case "wgShow":
            if (popoverwg) {
                $("#" + id).css("display", "none");
                popoverwg = false;
                return "";
            }
            popoverwg = true;
            break;
    }
    $("#" + id).css("display", "block");

}


//隐藏多选框
function hide(id) {
    switch (id) {
        case "jhShow":
            popoverTc = false;
            break;
        default:
            return "";
    }

    $("#" + id).css("display", "none");
}


// 渲染彈窗ajax
var baseInfo;

function lineSort(a, b) {
    var asub = a.LINE_NAME;
    var bsub = b.LINE_NAME;
    return parseInt(asub.substring(0, asub.length - 2)) - parseInt(bsub.substring(0, bsub.length - 2));
}

function getBaseInfo() {
    $.ajax({
        url: ctx + '/module/data/base/getStationInfo',
        type: "post",
        dataType: "json",
        contentType: 'application/json',
        data: JSON.stringify({
            "pageNum": 0,
            "pageSize": 0,
            "params": {}
        }),
        success: function (data) {

            if (data.code === 200) {
                baseInfo = data.data.stations.sort(lineSort);
                $('#jhShow ul').html(createLiHtml('Jhjc', baseInfo));
                $('#wgShow ul').html(createLiHtml('Aqbhq', baseInfo));
            } else {
                console.log(data.message)
            }
        }
    })
}

getBaseInfo();


var pro_data = [];

function loadXY(type, res) {
    pro_data = [];
    $(res).each(function (i, r) {
        pro_data.push(r);
        var shzb = [];
        if (type == 'Jhjc') {
            var gcj02_ll = JSON.parse(r.CENTERJSON);
            //火星坐标大地坐标
            var wgs84_ll = gcj02towgs84(gcj02_ll.lng, gcj02_ll.lat);
            //大地坐标转上海坐标
            shzb = wgs84Tosh(wgs84_ll[1], wgs84_ll[0]);
        } else {
            var bd09togcj = bd09togcj02(r.LNG, r.LAT);
            //火星坐标大地坐标
            var wgs84_ll = gcj02towgs84(bd09togcj[0], bd09togcj[1]);
            //大地坐标转上海坐标
            shzb = wgs84Tosh(wgs84_ll[1], wgs84_ll[0]);
        }
        pro_data[i]["x"] = shzb[0];
        pro_data[i]["y"] = shzb[1];
    });
    //var resMark = markId(pro_data);
    var contentWindow = $("#gisView")[0].contentWindow;
    contentWindow.markPoi(pro_data);
}

function createLiHtml(type, baseInfo) {
    var html = '';
    $.each(baseInfo, function (i, n) {
        html += "<li onclick='getProData(this)' data_uuid=" + n.LINE_UUID + " data-color=" + n.LINE_COLOR + " data-type='" + type + "'>" + n.LINE_NAME + "</li>"
    });
    return html;
}
