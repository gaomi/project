// $(function () {
//     debugger;
//     var deviceTable = new deviceTable();
//     deviceTable.Init('#device_list');
// });


//病害项目查询
var deviceTableInit = function () {
    var deviceTable = new Object();
    deviceTable.Init = function (id) {
        //记录页面bootstrap-table全局变量$table，方便应用
        var queryUrl = ctx + "module/home/tab/getDeviceInfo";
        var tableOption = segmentCommon.tableOptions({
            url: queryUrl,
            queryParams: function (params) {
                var param = new Object();
                param.segment = segmentId;
                param.group_segment = group_segment;
                var deviceName = $.trim($("#deviceName").val());
                if (deviceName.length > 0) {
                    param.deviceName = deviceName;
                }
                if (params.search != "") {
                    var obj_param = JSON.parse(params.search);
                    for (key in obj_param) {
                        param[String(key)] = obj_param[String(key)];
                    }
                }
                return {
                    pageSize: params.limit,                         //页面大小
                    pageNum: (params.offset / params.limit) + 1, //页码
                    params: param
                };
            },
            columns: [
                {field: 'deviceId', title: '设备编号'},
                {
                    field: 'deviceName', title: '设备名称'
                },
                {
                    field: 'deptId', title: '部门编号'
                },
                {field: 'highLow', title: '上行'},
                {field: 'major', title: '专业'},
                {field: 'startSta', title: '开始站编号'},
                {field: 'endSta', title: '结束站编号'},
                {field: 'deviceType', title: '设备分类'},
                {field: 'factoryTime', title: '日期'},
                {
                    field: '', title: '维修', align: 'center',
                    formatter: function (value, row, index) {
                        var details = '<div  class="updates_bt dise-lvli" onclick="toMainTain(\'' + row.deviceId + '\')" >' +
                            '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;病害信息</div>';
                        return details;
                    }
                }
            ]
        });
        $(id).bootstrapTable(tableOption);
    }
    return deviceTable;
};

function queryDevice() {
    $("#device_list").bootstrapTable('refresh');
}

function toDeviceList() {
    $('#myDeviceTab li:eq(0) a').tab('show');

}

function toMainTain(id) {
    // $('#myDeviceTab li:eq(1) a').tab('show');
    // $("#deviceMaintainTab").bootstrapTable('destroy');
    $(".nav-tabs-custom li[data-content='" + segmentCommon.emun.BHXX + "'] a").tab("show");
    var obj = new Object();
    obj.status = 20;
    obj.time = new Date().getTime();
    $("#dise_list").bootstrapTable('resetSearch', JSON.stringify(obj));
    $("#dise_statue option[value='20']").prop("selected", "selected");
    $('#myFaultTab li:eq(0) a').tab('show');
    $('#dise-desc').css('display', 'none');
    // var queryUrl = ctx + "module/home/tab/getMainTain";
    // var tableOption = segmentCommon.tableOptions({
    //     url: queryUrl,
    //     queryParams:  function (params) {
    //         var param = new Object();
    //         param.deviceId = id;
    //         return {
    //             pageSize: params.limit,                         //页面大小
    //             pageNum: (params.offset / params.limit) + 1, //页码
    //             params: param
    //         };
    //     },
    //     columns: [
    //         {
    //             field: 'direction', title: '上下行'
    //         },
    //         {
    //             field: 'segmentRange', title: '区间'
    //         },
    //         {field: 'dealCondition', title: '处理内容'},
    //         {field: 'diseDesc', title: '病害描述'},
    //         {field: 'dealType', title: '处理方式'},
    //         {field: 'followWork', title: '后续计划安排'},
    //         {field: 'ductLoc', title: '位置'},
    //         {field: 'workOrderId', title: '工单号'},
    //         {field: 'materialConsumption', title: '消耗材料'},
    //         {field: 'participant', title: '维修参与者'},
    //         {field: 'dataTime', title: '日期'}
    //     ]
    // });
    // $("#deviceMaintainTab").bootstrapTable(tableOption);
}