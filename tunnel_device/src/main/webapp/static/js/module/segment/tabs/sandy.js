$(function () {
    renderSandy();
});

function arr_unique(arr) {
    for (var i = 0; i < arr.length; i++) {
        for (var j = i + 1; j < arr.length; j++) {
            if (arr[i] == arr[j]) {
                arr.splice(j, 1);
                j--;
            }
        }
    }
    return arr;
}

function renderSandy() {
    var settings = {
        url: ctx + "/module/data/sandy/list",

        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: {lineCode: lineId, segment: segmentId, group_segment: group_segment}
            };
        },
        columns: [{
            field: 'UUID',
            visible: false
        }, {
            field: "SEG_SEGMENT_NAME", title: "区间"
        }, {
            field: 'SEG_UPDOWN',
            title: '上下行'
        }, {
            field: 'SANDY_TUNNEL_POSITION_VALUE',
            title: '与隧道位置'
        }, {
            field: 'SANDY_ID',
            title: '土层编号'
        }, {
            field: 'SOIL_LEVEL_VALUE',
            title: '土层'
        }, {
            field: 'startrange',
            title: '起始范围', formatter: function (value, row, index) {
                return row.START_FLAG + row.START_MILEAGE;
            }
        }, {
            field: 'endrange',
            title: '结束范围', formatter: function (value, row, index) {
                return row.END_FLAG + row.END_MILEAGE;
            }
        }/*,
            {
                field: '', title: '详情', align: 'center', width: 120,
                formatter: function (value, row, index) {
                    var details = '<div  class="updates_bt"  onclick=detailsData(\'' + row.projectid + '\')>' +
                        '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;详情</div>';
                    // var Location = '&nbsp;&nbsp;<button type="button"  class="btn btn-primary btn-sm updates" >' +
                    //     '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;区间定位</button>';
                    return details;
                }
            }*/],
        onLoadSuccess: function (result, res, data) {
            if($(".bootstrap-table tbody .no-records-found").length > 0){
                $(".bootstrap-table tbody .no-records-found td").text("无");
            }
            //debugger;
            // if (result.code == 20000) {
            //
            //
            // mergeTable(result, "td_sandy");
            // }

        }, responseHandler: function (result) {
            if (result.code == 200) {
                common.status.tab_sandy = segmentId;
                return result.data;
            } else {
                return result.message;
            }
        }
    };

    $MB.initTable('td_sandy', settings);
    /*   var param = {pageSize: "10", pageNum: "1", params: {"lineCode": lineId, "segment": segmentId}};
       $('#td_sandy').bootstrapTable({
           ajax: function (request) {
               $.ajax({
                   url: ctx + "module/data/sandy/list",
                   type: "post",
                   dataType: "json",
                   contentType: 'application/json',
                   data: JSON.stringify(param),
                   success: function (res) {
                       if (res.data.rows){
                           var results = res.data.rows;
                           var tableTitle = [];
                           var row1 = [{
                               field: 'UUID',
                               visible: false,
                               colspan: 1,
                               rowspan: 2
                           }, {
                               field: "SEG_SEGMENT_NAME", title: "区间",
                               colspan: 1,
                               rowspan: 2
                           }, {
                               field: 'SEG_UPDOWN',
                               title: '上下行',
                               colspan: 1,
                               rowspan: 2
                           }, {
                               field: 'SANDY_TUNNEL_POSITION_VALUE',
                               title: '与隧道位置',
                               colspan: 1,
                               rowspan: 2
                           }, {
                               field: 'SANDY_ID',
                               title: '土层编号',
                               colspan: 1,
                               rowspan: 2
                           }, {
                               field: "",
                               title: '土层',
                               colspan: 1,
                               rowspan: 1
                           }];

                           var SOIL_LEVEL = [];
                           for (var i = 0; i < results.length; i++) {
                               var property = results[i]["SOIL_LEVEL_VALUE"];
                               var newPro = "SOIL_LEVEL_VALUE_" + property;
                               SOIL_LEVEL.push(newPro);

                           }
                           arr_unique(SOIL_LEVEL);
                           row1[5].colspan = SOIL_LEVEL.length;
                           var row2 = [];
                           for (var i = 0; i < SOIL_LEVEL.length; i++) {
                               var str = "SOIL_LEVEL_VALUE"
                               // var index = SOIL_LEVEL[i].indexOf("SOIL_LEVEL_VALUE");
                               var substr = SOIL_LEVEL[i].substr(str.length + 1, SOIL_LEVEL[i].length);
                               row2.push({
                                   field: SOIL_LEVEL[i],
                                   title: substr,
                                   colspan: 1,
                                   rowspan: 1
                               });
                               for (var j = 0; j < results.length; j++) {
                                   var newStr = SOIL_LEVEL[i];
                                   results[j][newStr] = null;
                                   if (results[j].SOIL_LEVEL_VALUE == substr) {
                                       results[j][newStr] = results[j].START_FLAG + results[j].START_MILEAGE + ' ~ ' + results[j].END_FLAG + results[j].END_MILEAGE;
                                   }
                               }
                           }
                           row1.push(
                               {
                                   field: '', title: '详情', align: 'center', width: 120,
                                   formatter: function (value, row, index) {
                                       var details = '<div  class="updates_bt"  onclick=detailsData(\'' + row.projectid + '\')>' +
                                           '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;详情</div>';
                                       // var Location = '&nbsp;&nbsp;<button type="button"  class="btn btn-primary btn-sm updates" >' +
                                       //     '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;区间定位</button>';
                                       return details;
                                   },
                                   colspan: 1,
                                   rowspan: 2
                               });
                           tableTitle.push(row1);
                           tableTitle.push(row2);
                           //console.log(Object.keys(json[0]));

                           $('#td_sandy').bootstrapTable('destroy').bootstrapTable({
                               data: results,
                               //classes: 'table table-hover',
                               //height: 400,
                               //url: queryUrl,                      //请求后台的URL（*）
                               //method: 'GET',                      //请求方式（*）
                               //toolbar: '#toolbar',              //工具按钮用哪个容器
                               striped: true,                      //是否显示行间隔色
                               //cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                               //pagination: true,                   //是否显示分页（*）
                               //sortable: true,                     //是否启用排序
                               //sortOrder: "asc",                   //排序方式
                               //sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                               //pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
                               //pageSize: rows,                     //每页的记录行数（*）
                               //pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                               search: false,                      //是否显示表格搜索
                               strictSearch: false,
                               showColumns: false,                  //是否显示所有的列（选择显示的列）
                               //showRefresh: true,                  //是否显示刷新按钮
                               //minimumCountColumns: 2,             //最少允许的列数
                               clickToSelect: true,                //是否启用点击选中行
                               uniqueId: "fid",                     //每一行的唯一标识，一般为主键列
                               showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
                               cardView: false,                    //是否显示详细视图
                               detailView: false,                  //是否显示父子表
                               //得到查询的参数
                               //queryParams: function (params) {
                               //    //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
                               //    var temp = {
                               //        rows: params.limit,                         //页面大小
                               //        page: (params.offset / params.limit) + 1,   //页码
                               //        sort: params.sort,      //排序列名
                               //        sortOrder: params.order //排位命令（desc，asc）
                               //    };
                               //    return temp;
                               //},
                               columns: tableTitle,
                               onLoadSuccess: function () {
                                   //debugger;
                                   mergeTable(result, "td_sandy");
                               },
                               onLoadError: function () {
                                   //debugger;
                                   showTips("数据加载失败！");
                               },
                               onClickRow: function (row, $element) {
                                   return;
                                   var featureid = row.fid;
                                   var realval = row.realval;
                                   if (realval == 1) {
                                       realval = 0;
                                   } else {
                                       realval = 1;
                                   }
                                   updatePlanVal(featureid, realval);
                               },
                               onDblClickRow: function (row, $element) {
                                   var id = row.ID;
                                   EditViewById(id, 'view');
                               }
                           });
                           //var tableop = $('#td_sandy').bootstrapTable('getOptions');
                           mergeTable(results, "td_sandy");
                       }
                   },
                   error: function () {
                      // alert("SQL查询错误，请输入正确的SQL语句！");
                       //location.reload();
                   }
               });
           }
       });*/

}

/*

function renderSandyTable(dynamicColums, results) {
    //土层信息选项卡
    var $sandyTableForm = $(".td-sandy-table-form");


    /!*var settings = {
        data: results,
        /!*
        striped: true,
        cache: false,
        pagination: true,
        sortable: false,
        sidePagination: "client",
        pageNumber: 1,

        pageList: [5, 10, 25, 50, 100],
        strictSearch: true,
        showColumns: false,
        minimumCountColumns: 2,
        clickToSelect: true,
        uniqueId: "ID",
        cardView: false,
        detailView: false,
        smartDisplay: false,*!/
        columns: dynamicColums,
        onLoadSuccess: function (result, res, data) {
            var tableop = $('#td_sandy').bootstrapTable('getOptions');
            ////debugger;
            // if (result.code == 20000) {
            //
            //
            mergeTable(result, "td_sandy");
            // }
        }
    };
*!/

    $('#td_sandy').bootstrapTable('destroy');
    $('#td_sandy').bootstrapTable({
        data: results,
        //classes: 'table table-hover',
        //height: 400,
        //url: queryUrl,                      //请求后台的URL（*）
        //method: 'GET',                      //请求方式（*）
        //toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        //cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        //pagination: true,                   //是否显示分页（*）
        //sortable: true,                     //是否启用排序
        //sortOrder: "asc",                   //排序方式
        //sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        //pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        //pageSize: rows,                     //每页的记录行数（*）
        //pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: false,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
        //showRefresh: true,                  //是否显示刷新按钮
        //minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: "fid",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        //得到查询的参数
        //queryParams: function (params) {
        //    //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
        //    var temp = {
        //        rows: params.limit,                         //页面大小
        //        page: (params.offset / params.limit) + 1,   //页码
        //        sort: params.sort,      //排序列名
        //        sortOrder: params.order //排位命令（desc，asc）
        //    };
        //    return temp;
        //},
        columns: dynamicColums,
        onLoadSuccess: function () {
            //debugger;
            mergeTable(result, "td_sandy");
        },
        onLoadError: function () {
            //debugger;
            showTips("数据加载失败！");
        },
        onClickRow: function (row, $element) {
            return;
            var featureid = row.fid;
            var realval = row.realval;
            if (realval == 1) {
                realval = 0;
            } else {
                realval = 1;
            }
            updatePlanVal(featureid, realval);
        },
        onDblClickRow: function (row, $element) {
            var id = row.ID;
            EditViewById(id, 'view');
        }
    });

    //$('#td_sandy').bootstrapTable(settings);
    $('#td_sandy').on('post-body.bs.table', function (e) {//渲染完成，重新设置高度
        var h = $('#td_sandy').height();
        if (h > 500) {
            $('#td_sandy').bootstrapTable('resetView', {'height': 300});
        }
    });
    //$MB.initTable('td_sandy', settings);
}
*/

/**
 * 参考
 * https://www.cnblogs.com/eleven258/p/9686731.html
 * @type {string}
 */
//全局变量 ***如果每次只是发送ajax请求对table进行局部更新，则每次要合并前前都应该清空这三个变量 不然全局变量会一值追加结果
var projNameCount = "";
var subProjNameCount = "";
var phaseCount = "";

//合并表格
function mergeTable(data, tableId) {
    //每次合并表格前 都要将全局变量清空
    projNameCount = "";
    subProjNameCount = "";
    phaseCount = "";
    mergeCells(data, 0, data.length, "SEG_SEGMENT_NAME", $('#' + tableId));
    //对projName,subProjName,phase的次数进行分割
    //去掉末尾的逗号 有时候也可以不用去掉 还是去掉了我这里
    projNameCount = projNameCount.substring(0, projNameCount.length - 1);
    subProjNameCount = subProjNameCount.substring(0, subProjNameCount.length - 1);
    phaseCount = phaseCount.substring(0, phaseCount.length - 1);
    //console.log(projNameCount+"+"+subProjNameCount+"+"+phaseCount);
    var strArr1 = projNameCount.split(",");
    var strArr2 = subProjNameCount.split(",");
    var strArr3 = phaseCount.split(",");
    //根据次数进行表格合并
    //合并projName
    var index = 0;
    for (var i = 0; i < strArr1.length; i++) {
        var count = strArr1[i] * 1;
        $('#' + tableId).bootstrapTable('mergeCells', {index: index, field: "SEG_SEGMENT_NAME", colspan: 1, rowspan: count});
        index += count;
    }
    //合并subProjName
    var index = 0;
    for (var i = 0; i < strArr2.length; i++) {
        var count = strArr2[i] * 1;
        $('#' + tableId).bootstrapTable('mergeCells', {index: index, field: "SEG_UPDOWN", colspan: 1, rowspan: count});
        index += count;
    }
    //合并subProjName
    var index = 0;
    for (var i = 0; i < strArr2.length; i++) {
        var count = strArr2[i] * 1;
        $('#' + tableId).bootstrapTable('mergeCells', {index: index, field: "endStationCode", colspan: 1, rowspan: count});
        index += count;
    }
    //合并phaseName
    var index = 0;
    for (var i = 0; i < strArr3.length; i++) {
        var count = strArr3[i] * 1;
        $('#' + tableId).bootstrapTable('mergeCells', {index: index, field: "updown", colspan: 1, rowspan: count});
        index += count;
    }
    //合并phaseName
    var index = 0;
    for (var i = 0; i < strArr3.length; i++) {
        var count = strArr3[i] * 1;
        $('#' + tableId).bootstrapTable('mergeCells', {index: index, field: "SANDY_TUNNEL_POSITION_VALUE", colspan: 1, rowspan: count});
        index += count;
    }
}


//排序后紧挨在一起 进行同名合并
/**
 * 对于表格合并，首先要进行排序，即将同名的属性的记录排序紧挨在一起，这样才能最好的显示出合并想要的效果。
 * 因为此方法是拿第一个数据与后面的数据依次比较，
 * 例如，第一条记录的projName与第二条记录的projName来进行比较，两者相同，则继续第一条记录的projName与第三条记录的projName来进行比较，
 * 当不相同时，记录下此projName对应的值出现的次数，然后再开始从第三条记录的projName与第四条记录的projName来进行比较，依次循环下去，记
 * 录下相同内容的值出现的次数，到时候，再根据这些次数来进行合并
 *
 * 此方法主要是先拿到每个同名属性的值的相等次数，把次数利用全局变量存下来
 *
 * @param datas --表格数据,一般为表格的rows数据
 * @param startIndex --开始下标
 * @param size --从开始下标起，到size结束，遍历合并多少个
 * @param fieldName --计算算哪个列
 * @param target --table表格对象
 */
function mergeCells(datas, startIndex, size, fieldName, target) {
    //console.log("startIndex:"+startIndex+"size:"+size+"---合并列:"+fieldName)
    //声明一个数组计算相同属性值在data对象出现的次数和
    //这里不能使用map，因为如果涉及到排序后，相同的属性并不是紧挨在一起,那么后面的次数会覆盖前面的次数，故这里用数组
    var sortArr = new Array();
    for (var i = startIndex; i < size; i++) {
        for (var j = i + 1; j < size; j++) {
            if (datas[i][fieldName] != datas[j][fieldName]) {
                //相同属性值不同
                if (j - i > 1) {
                    sortArr.push(j - i);
                    i = j - 1;
                    //如果是最后一个元素 把最后一个元素的次数也装进去
                    if (i == size - 1 - 1) {
                        sortArr.push(1);
                    }
                } else {
                    sortArr.push(j - i);
                    //如果j是最后一个元素 把最后一个元素的次数装进去
                    if (j == size - 1) {
                        sortArr.push(1);
                    }
                }
                break;

            } else {
                //相同属性值相同 直到最后一次的时候才会装 否则在他们的值不同时再装进去
                if (j == size - 1) {
                    sortArr.push(j - i + 1);
                    //这里的赋值感觉有点多余 算了现就这个样子吧 不影响功能
                    i = j;
                }
            }
        }
    }

    //遍历数组，将值装追加到对应的字符串后面
    for (var prop in sortArr) {
        /*这里在ie8上运行的时候 出现坑 最好遍历数组不要用for in 这里我用了就懒得换了
        下面加上如果prop是indexOf就停止 就解决了ie8出现的问题*/
        if (prop == "indexOf") {
            continue;
        }
        if (fieldName == "SEG_SEGMENT_NAME") {
            var count = sortArr[prop] * 1;
            projNameCount += count + ",";
        }

        if (fieldName == "SEG_UPDOWN") {
            var count = sortArr[prop] * 1;
            subProjNameCount += count + ",";
        }

        if (fieldName == "endStationCode") {
            var count = sortArr[prop] * 1;
            phaseCount += count + ",";
        }
    }

    for (var prop in sortArr) {
        if (prop == "indexOf") {
            continue;
        }
        if (fieldName == "SEG_SEGMENT_NAME") {
            //console.log("进入projName--此时开始index-"+startIndex+"--结束index--"+(startIndex+sortArr[prop])*1);
            startIndex = 0;
            //subProjName每次进去的startIndex为前面次数的和
            if (subProjNameCount.length > 0) {
                //console.log("subProjNameCount-"+subProjNameCount);
                var temp = subProjNameCount.substring(0, subProjNameCount.length - 1);
                var strArr1 = temp.split(",");
                for (var i = 0; i < strArr1.length; i++) {
                    var count = strArr1[i] * 1;
                    startIndex += count;
                }
            }

            if (sortArr[prop] > 1) {
                mergeCells(datas, startIndex, startIndex + sortArr[prop], "SEG_UPDOWN", target);
            } else {
                //当projName的次数为1就不进入循环
                subProjNameCount += 1 + ",";
                phaseCount += 1 + ",";
            }
        }
        if (fieldName == "SEG_UPDOWN") {
            startIndex = 0;
            if (phaseCount.length > 0) {
                //console.log("phaseCount-"+phaseCount);
                var temp = phaseCount.substring(0, phaseCount.length - 1);
                //phaseCount = phaseCount + ",";
                var strArr1 = temp.split(",");
                for (var i = 0; i < strArr1.length; i++) {
                    var count = strArr1[i] * 1;
                    startIndex += count;
                }
            }
            if (sortArr[prop] > 1) {
                //console.log("进入subProj--此时开始index-"+startIndex+"--结束index--"+(startIndex+sortArr[prop])*1);
                mergeCells(datas, startIndex, startIndex + sortArr[prop], "endStationCode", target)
            } else {
                phaseCount += 1 + ",";
            }
        }
    }
}

function renderSandy1() {
    var param = {pageSize: "10", pageNum: "1", params: {"lineCode": lineId, "segment": segmentId}};
    $.ajax({
        url: ctx + "/module/data/sandy/list",
        type: "post",
        dataType: "json",
        contentType: 'application/json',
        data: JSON.stringify(param),
        //async: false,
        success: function (res) {

            // renderSandyTable(tableTitle, results);
        },
        error: function () {
        }
    });


}