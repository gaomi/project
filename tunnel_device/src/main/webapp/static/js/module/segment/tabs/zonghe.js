var ductArr = {
    1: [1832, 1758.8],
    2: [1758.8, 1685.6],
    3: [1685.6, 1612.4],
    4: [1612.4, 1539.2],
    5: [1539.2, 1466],
    6: [1466, 1392.8],
    7: [1392.8, 1319.6],
    8: [1319.6, 1246.4],
    9: [1246.4, 1173.2],
    10: [1173.2, 1100],
    11: [1100, 1026.8],
    12: [1026.8, 953.6],
    13: [953.6, 880.4],
    14: [880.4, 807.2],
    15: [807.2, 734],
    16: [734, 660.8],
    17: [660.8, 587.6],
    18: [587.6, 514.4],
    19: [514.4, 441.2],
    20: [441.2, 368],
    21: [368, 294.8],
    22: [294.8, 221.6],
    23: [221.6, 148.4],
    24: [148.4, 75.2],
    25: [75.2, 2]
};
var upShen = {
    1: [9.253, 9.67],
    2: [9.67, 10.55],
    3: [10.55, 10.973],
    4: [10.973, 10.43],
    5: [10.43, 12.27],
    6: [12.27, 11.29],
    7: [11.29, 12.66],
    8: [12.66, 13.2],
    9: [13.2, 13.31],
    10: [13.31, 14.21],
    11: [14.21, 13.567],
    12: [13.567, 13.9],
    13: [13.9, 12.92],
    14: [12.92, 13.58],
    15: [13.58, 14.35],
    16: [14.35, 14.18],
    17: [14.18, 15.13],
    18: [15.13, 14.88],
    19: [14.88, 16.19],
    20: [16.19, 16.24],
    21: [16.24, 17.37],
    22: [17.37, 17.92],
    23: [17.92, 17.88],
    24: [17.88, 18.43],
    25: [18.43, 18.63]
};
var downShen = {
    1: [15.453, 15.87],
    2: [15.87, 16.75],
    3: [16.75, 17.173],
    4: [17.173, 16.63],
    5: [16.63, 18.47],
    6: [18.47, 17.49],
    7: [17.49, 18.86],
    8: [18.86, 19.4],
    9: [19.4, 19.51],
    10: [19.51, 20.41],
    11: [20.41, 19.767],
    12: [19.767, 20.1],
    13: [20.1, 19.12],
    14: [19.12, 19.78],
    15: [19.78, 20.55],
    16: [20.55, 20.38],
    17: [20.38, 21.33],
    18: [21.33, 21.08],
    19: [21.08, 22.39],
    20: [22.39, 22.44],
    21: [22.44, 23.57],
    22: [23.57, 24.12],
    23: [24.12, 24.08],
    24: [24.08, 24.63],
    25: [24.63, 24.83]
};
var res = {};

// 随机数函数
function radomNum(MaxNum, MinNum, decimalNUm) {
    var max = 0, min = 0;
    MinNum <= MaxNum ? (min = MinNum , max = MaxNum) : (min = MaxNum, max = MinNum);
    switch (arguments.length) {
        case 1:
            return Math.floor(Math.random() * (max + 1));
            break;
        case 2:
            return Math.floor(Math.random() * (max - min + 1) + min);
            break;
        case 3:
            return (Math.random() * (max - min) + min).toFixed(decimalNUm);
            break;
        default :
            return 16;
    }
}

function ductRandom(ductNum) {
    var index;
    $.each(ductArr, function (i, n) {
        if (ductNum <= n[0] & ductNum > n[1]) {
            index = i;
        }
    });
    $.each(upShen, function (i, n) {
        if (i == index) {
            res.upShen = radomNum(n[0], n[1], 1);
        }
    });
    // $.each(downShen, function (i, n) {
    //     if (i == index) {
    //         res.downShen = radomNum(n[0], n[1], 3);
    //     }
    // })
    $('.duct_desc_head1 span').html(ductNum);
    $('.duct_desc_head2 span').html(res.upShen);
    res.downShen = parseFloat(res.upShen);
    res.downShen = parseFloat(res.upShen);
    var res2Num = res.downShen + 6.2;
    $('.duct-down-ms span').html(res2Num.toFixed(1));
}

$('.map-mask').mouseenter(function () {
    $('#zonghe-box .mapdiv1').css({display: 'block'});
});
$('.map-mask').mouseleave(function () {
    $('#zonghe-box .mapdiv1').css({display: 'none'});
})

// 土层
function soilLayer(data) {
    var _siblings = $('.tuceng-lis li');
    $.each(_siblings, function (i, n) {
        $(n).find('i').empty();
    });
    if (data != null && data != undefined) {
        $.each(data, function (i, n) {
            var sandy_tunnel = n.SANDY_TUNNEL_POSITION;
            if (sandy_tunnel == 4) {
                $('.tuceng-lis ul').css({display: "none"});
                $('.tuceng-whole i').css({display: "block"});
                $('.tuceng-lis .tuceng-whole i').html(n.SOIL_LEVEL_VALUE);
            } else if (sandy_tunnel != 4) {
                $('.tuceng-lis .tuceng-whole').css({display: "none"});
                $('.tuceng-lis ul').css({display: "block"});
            }
            if (sandy_tunnel == 1) {
                $('.tuceng-1 i').html(n.SOIL_LEVEL_VALUE);
            }
            if (sandy_tunnel == 7) {
                $('.tuceng-2 i').html(n.SOIL_LEVEL_VALUE);
            }
            if (sandy_tunnel == 6) {
                $('.tuceng-3 i').html(n.SOIL_LEVEL_VALUE);
            }
        })
    }

}


/***
 * 管片详情处理信息
 * @param index
 */
function detailXiangQing(index) {
    var ductItem = $("#zonghe-box .duct-warpper div");
    $.each(ductItem, function (i, n) {
        $(n).attr("class", "");
    });
    var param = {
        params: {
            segmentUuid: segmentId,
            group_segment: group_segment,
            lineCode: lineId,
            status: '20',
            duct: index
        }
    };
    $.ajax({
        type: "post",
        url: ctx + "/module/segment/getXiangQing",
        dataType: 'json',
        contentType: 'application/json',
        // async: false,
        data: JSON.stringify(param),
        success: function (data) {
            if (data.code == '200') {
                if(data.data.sl!==null){
                $("#duct-contNum span").css({display:'block'});
                $("#duct-contNum i").text(parseInt(data.data.sl));
                }else {
                    $("#duct-contNum span").css({display:'none'});
                    $("#duct-contNum i").text('');
                }
                changeDuctTab(data.data.fault);
                soilLayer(data.data.sandy);
            }
        }
    });
}

/***
 * 显示病害环位置
 * @param arr
 */
function changeDuctTab(data) {
    var arr = new Array();
    $.each(data, function (i, n) {
        if ($.trim(n.FAULT_LEVEL).length > 0 && $.trim(n.DETAIL_LOC).length > 0) {
            var obj = new Object();
            if (n.FAULT_LEVEL.substr(0, 1) == 'A') {
                obj.level = 'A';
            } else {
                obj.level = 'B';
            }
            obj.loc = n.DETAIL_LOC;
            arr.push(obj);
        }
    });
    if (arr.length > 0) {
        var ductItem = $("#zonghe-box .duct-warpper div");
        $.each(ductItem, function (i, n) {
            $.each(arr, function (j, m) {
                if ($(n).attr("data-type") == m.loc) {
                    var con = $(n).attr("data-content");
                    $(n).attr("class", "");
                    if (m.level == 'A') {
                        $(n).addClass(con + "-red");
                    } else {
                        $(n).addClass(con + "-yellow");
                    }
                }
            });

        });
    }

}


/***
 * 获取病害数
 * @param index
 */
function deatilXiangqingTab(index) {
    var ductItem = $("#zonghe-box .duct-warpper div");
    $.each(ductItem, function (i, n) {
        $(n).attr("class", "");
    });
    var param = {
        params: {
            segmentUuid: segmentId,
            group_segment: group_segment,
            status: '20',
            lineCode: lineId,
            duct: index
        }
    };
    $.ajax({
        type: "post",
        url: ctx + "/module/segment/getBhInfo",
        dataType: 'json',
        contentType: 'application/json',
        // async: false,
        data: JSON.stringify(param),
        success: function (data) {
            if (data.code == '200') {
                var arr = new Array();
                $.each(data.data.rows, function (i, n) {
                    if ($.trim(n.FAULT_LEVEL).length > 0 && $.trim(n.DETAIL_LOC).length > 0) {
                        var obj = new Object();
                        if (n.FAULT_LEVEL.substr(0, 1) == 'A') {
                            obj.level = 'A';
                        } else {
                            obj.level = 'B';
                        }
                        obj.loc = n.DETAIL_LOC;
                        arr.push(obj);
                    }
                });
                if (arr.length > 0) {
                    changeDuctTab(arr);
                }
            }
        }
    });
}

/***
 * 管片详情处理土层信息
 * @param index
 */
function getSandyInfoByDuct(index) {
    var param = {
        params: {
            segmentUuid: segmentId,
            group_segment: group_segment,
            lineCode: lineId,
            duct: index
        }
    };
    $.ajax({
        type: "post",
        url: ctx + "/module/data/sandy/list",
        dataType: 'json',
        contentType: 'application/json',
        // async: false,
        data: JSON.stringify(param),
        success: function (data) {
            if (data.code == '200') {
                soilLayer(data.data.rows);
            }
        }
    });
}