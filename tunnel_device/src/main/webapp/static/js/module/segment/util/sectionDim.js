/***
 * 便利xAxis轴
 * @param data
 * @returns {Object}
 */
function dealxAxis(data) {
    var xAxis = new Array();
    $.each(data, function (i, n) {
        var temp = new Array();
        temp.push(n.DUCT_CODE);
        temp.push(n.MILEAGE_CODE);
        xAxis.push(temp);
    });
    return xAxis;
}

/***
 * 处理各项数据
 * @param date
 */
function dealMainDate(date) {
    var rever = false;
    var m = new Array();
    var xAxis = Object.assign([], date.xAxis.data)
    if (parseInt(xAxis[0].DUCT_CODE) > parseInt(xAxis[(xAxis.length - 1)].DUCT_CODE)) {
        xAxis.reverse();
        rever = true;
    }
    $.each(xAxis, function (i, n) {
        m.push({
            BH: 0,
            BH_LEVEL: "D",
            BJ: 0,
            DUCT_CODE: n.DUCT_CODE,
            JH: 0,
            MILEAGE_CODE: n.MILEAGE_CODE,
            TC: 0,
            WG: 0,
            WX: 0
        });
    });
    dealDuct(date.jhjcDate, m, 'JH');
    dealDuct(date.sandyDate, m, 'TC');
    dealDuct(date.aqbhqDate, m, 'WG');
    dealEmphasisDateDuct(date.emphasisDate, m, 'WX');
    dealDuct(date.baoJing, m, 'BJ');
    dealFaultDuct(date.faultDate, m);
    if (rever) {
        m.reverse();
    }
    return m;
}

function dealEmphasisDateDuct(data, arr, key) {
    $.each(data, function (j, arrStr) {
        try {
            var arrString = arrStr.EMPHASIS_DUCT.split(",");
            $.each(arrString, function (i, n) {
                try {
                    var index = parseInt(n) - 1;
                    if (index <= arr.length) {
                        arr[index][key] += 1;
                    }
                } catch (e) {
                }
            });
        } catch (e) {
        }
    });
}

function dealDuct(data, arr, key) {
    $.each(data, function (i, n) {
        try {
            var a = parseInt(n.START_DUCT_CODE);
            var b = parseInt(n.END_DUCT_CODE);
            var start = a < b ? a : b;
            var end = a < b ? b : a;
            for (start; start <= end; start++) {
                if (start > arr.length) {
                    continue;
                }
                var index = start - 1;
                arr[index][key] += 1;
            }
        } catch (e) {
        }
    });

    return arr;
}

function dealFaultDuct(data, arr) {
    $.each(data, function (i, n) {
        try {
            var a = parseInt(n.START_DUCT_CODE);
            var b = parseInt(n.END_DUCT_CODE);
            var start = a < b ? a : b;
            var end = a < b ? b : a;
            for (start; start <= end; start++) {
                if (start > arr.length) {
                    continue;
                }
                var index = start - 1;
                arr[index]['BH'] += 1;
                if ($.trim(n.FAULT_LEVLE).length > 0 && n.FAULT_LEVLE < arr[index]['BH_LEVEL']) {
                    arr[index]['BH_LEVEL'] = $.trim(n.FAULT_LEVLE);
                }
            }
        } catch (e) {
        }
    });
    return arr;
}

function getSetionInfo(colerLevel, setioninfo) {
    var BH = {name: common.emun.BHXX, weight: 3, data: [], color: [], LEVEL: [], PID: null};
    var WG = {name: common.emun.WGSG, weight: 1, data: [], color: [], PID: '168'};
    var WX = {name: common.emun.WXXX, weight: 1, data: [], color: [], PID: '171'};
    var TC = {name: common.emun.TCXX, weight: 1, data: [], color: [], PID: '170'};
    var BJ = {name: common.emun.BJXM, weight: 1, data: [], color: [], PID: '169'};
    var JH = {name: common.emun.JHXM, weight: 1, data: [], color: [], PID: '167'};
    // var BH_STATIS={A:0,B:0,C:0,COUNT:0};
    var BH_LEVEL = {data: [], PID: '172'};
    var resu = {BJ: BJ, BH: BH, WG: WG, TC: TC, WX: WX, JH: JH, BH_LEVEL: BH_LEVEL};
    $.each(setioninfo, function (i, n) {
        // if(n.BH > 0){
        //     BH_STATIS.COUNT+=1;
        //     if(n.BH_LEVEL=='A'){
        //         BH_STATIS.A +=1;
        //     }else if(n.BH_LEVEL=='B'){
        //         BH_STATIS.B +=1;
        //     }else{
        //         BH_STATIS.C +=1;
        //     }
        // }
        for (key in n) {
            if (resu[key] == undefined) {
                continue;
            }
            resu[String(key)].data.push(n[String(key)]);
            if (key == 'BH_LEVEL') {
                resu.BH.LEVEL.push(n[String(key)]);
            }
            for (keys in colerLevel) {
                if (String(key).indexOf("BH") < 0 && keys == resu[String(key)].PID) {
                    for (var j = 0; j < colerLevel[keys].length; j++) {
                        if (j == 0 && n[key] > colerLevel[keys][j].DICT_KEY) {
                            resu[String(key)].color.push(colerLevel[keys][j].DICT_VALUE);
                        } else if (j == colerLevel[keys].length - 1 && n[key] <= colerLevel[keys][j].DICT_KEY) {
                            resu[String(key)].color.push(colerLevel[keys][j].DICT_VALUE);
                        } else if (n[key] > colerLevel[keys][j].DICT_KEY && n[key] <= colerLevel[keys][j - 1].DICT_KEY) {
                            resu[String(key)].color.push(colerLevel[keys][j].DICT_VALUE);
                        }
                    }
                } else if (String(key).indexOf("LEVEL") >= 0 && keys == resu[String(key)].PID) {
                    $.each(colerLevel[keys], function (s, m) {
                        if (n[key] == m.DICT_KEY) {
                            resu.BH.color.push(m.DICT_VALUE);
                        }
                    });
                }
            }
        }
    });
    // common.BH_STATIS=BH_STATIS;
    delete resu.BH_LEVEL;
    return resu;
}

function DimXData(xdata, ptd, setioninfo) {
    var BH = {name: common.emun.BHXX, weight: 3, data: [], color: []};
    var WG = {name: common.emun.WGSG, weight: 1, data: [], color: []};
    var WX = {name: common.emun.WXXX, weight: 1, data: [], color: []};
    var TC = {name: common.emun.TCXX, weight: 1, data: [], color: []};
    var BJ = {name: common.emun.BJXM, weight: 1, data: [], color: []};
    var JH = {name: common.emun.JHXM, weight: 1, data: [], color: []};
    var resu = {BJ: BJ, BH: BH, WG: WG, TC: TC, WX: WX, JH: JH};
    var retDim = new Object();
    var DimPtd = new Array();
    var dimX = new Array();
    var s = xdata.length % 100;
    var size = s == 0 ? parseInt(xdata.length / 100) : parseInt(xdata.length / 100 + 1);
    var offset = 0;
    while (offset * size < xdata.length) {
        dimX.push(xdata[offset * size]);
        for (var i = 0; i < ptd.length; i++) {
            if (offset != 0 && xdata[(offset - 1) * size][0] < xdata[offset * size][0] && ptd[i][0] > xdata[(offset - 1) * size][0] && ptd[i][0] <= xdata[offset * size][0]) {
                DimPtd.push(xdata[offset * size]);
                break;
            } else if (offset != 0 && xdata[(offset - 1) * size][0] > xdata[offset * size][0] && ptd[i][0] <= xdata[(offset - 1) * size][0] && ptd[i][0] > xdata[offset * size][0]) {
                DimPtd.push(xdata[offset * size]);
                break;
            }
        }
        ;
        var max = 0;
        if (offset == 0) {
            for (key in  resu) {
                resu[key].data.push(setioninfo[key].data[offset * size]);
                resu[key].color.push(setioninfo[key].color[offset * size]);
            }
        } else if (offset >= 1) {
            for (keys in  resu) {
                if (keys.indexOf("BH") < 0) {
                    max = (offset - 1) * size + 1;
                    for (var e = (offset - 1) * size + 2; e <= offset * size; e++) {
                        if (setioninfo[keys].data[e] > setioninfo[keys].data[e - 1]) {
                            max = e;
                        }
                    }
                    resu[keys].data.push(setioninfo[keys].data[max]);
                    resu[keys].color.push(setioninfo[keys].color[max]);
                } else {
                    max = 0;
                    var color = (offset - 1) * size + 1;
                    if (setioninfo[keys].data[(offset - 1) * size + 1] > 0) {
                        max += setioninfo[keys].data[(offset - 1) * size + 1];
                    }
                    for (var e = (offset - 1) * size + 2; e <= offset * size; e++) {
                        if (setioninfo[keys].data[e] > 0) {
                            max += setioninfo[keys].data[e];
                        }
                        if ((offset - 1) * size + 1 < e) {
                            if (setioninfo[keys].LEVEL[e] < setioninfo[keys].LEVEL[e - 1]) {
                                color = e;
                            }
                        }
                    }
                    resu[keys].data.push(max);
                    resu[keys].color.push(setioninfo[keys].color[color]);
                }
            }
        }
        offset++;
    }
    if ((offset - 1) * size != xdata.length - 1) {
        dimX.push(xdata[xdata.length - 1]);
        // for(key in  resu){
        //     resu[key].data.push(setioninfo[key].data[xdata.length-1]);
        //     resu[key].color.push(setioninfo[key].color[xdata.length-1]);
        // }
        var max = 0;
        if (offset >= 2) {
            for (keys in  resu) {
                if (keys.indexOf("BH") < 0) {
                    max = (offset - 1) * size + 1;
                    for (var e = (offset - 1) * size + 2; e <= xdata.length - 1; e++) {
                        if (setioninfo[keys].data[e] > setioninfo[keys].data[e - 1]) {
                            max = e;
                        }
                    }
                    resu[keys].data.push(setioninfo[keys].data[max]);
                    resu[keys].color.push(setioninfo[keys].color[max]);
                } else {
                    max = 0;
                    var color = (offset - 1) * size + 1;
                    if (setioninfo[keys].data[(offset - 1) * size + 1] > 0) {
                        max += setioninfo[keys].data[(offset - 1) * size + 1];
                    }
                    for (var e = (offset - 1) * size + 2; e <= xdata.length - 1; e++) {
                        if (setioninfo[keys].data[e] > 0) {
                            max += setioninfo[keys].data[e];
                        }
                        if ((offset - 1) * size + 1 < e) {
                            if (setioninfo[keys].LEVEL[e] < setioninfo[keys].LEVEL[e - 1]) {
                                color = e;
                            }
                        }
                    }
                    resu[keys].data.push(max);
                    resu[keys].color.push(setioninfo[keys].color[color]);
                }
            }
        }

        for (var i = 0; i < ptd.length; i++) {
            if (offset != 0 && xdata[(offset - 1) * size][0] < xdata[xdata.length - 1][0] && ptd[i][0] > xdata[(offset - 1) * size][0] && ptd[i][0] <= xdata[xdata.length - 1][0]) {
                DimPtd.push(xdata[offset * size]);
                break;
            } else if (offset != 0 && xdata[(offset - 1) * size][0] > xdata[xdata.length - 1][0] && ptd[i][0] <= xdata[(offset - 1) * size][0] && ptd[i][0] > xdata[xdata.length - 1][0]) {
                DimPtd.push(xdata[offset * size]);
                break;
            }
        }
        ;
    }

    retDim.xAxis = {name: '环号\n里程', data: dimX};
    retDim.data = resu;
    retDim.xPtd = DimPtd;
    return retDim;
}

function mapLevel(colerLevel) {
    var ret = new Object();
    $.each(colerLevel, function (i, n) {
        if (ret[String(n.PID)] == undefined || ret[String(n.PID)] == null) {
            ret[String(n.PID)] = [n];
        } else {
            ret[String(n.PID)].push(n);
        }
    });
    for (key in ret) {
        ret[key].sort(sortId);
    }
    return ret;
}

//对象排序
function sortId(a, b) {
    return parseInt(b.SEQ) - parseInt(a.SEQ);
}