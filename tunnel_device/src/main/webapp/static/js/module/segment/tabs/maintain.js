$(function () {
    var mainTainTable = new mainTainTableInit();
    mainTainTable.Init('#maintain_list');
});
var mainTainTableInit = function () {
    var mainTainTable = new Object();
    mainTainTable.Init = function (id) {
        var queryUrl = ctx + "/module/segment/emphasis/list";
        var tableOption = segmentCommon.tableOptions({
            url: queryUrl,
            queryParams: function (params) {
                var param = new Object();
                param.segment = segmentId;
                param.group_segment = group_segment;
                var maintainType = $.trim($("#maintainType").val());
                if (maintainType.length > 0) {
                    param.faultDesc = maintainType;
                }
                /***
                 * 处理异步加载后的参数
                 */
                debugger;
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
                {field: 'ductCode', title: '对应环号', width: 180, align: 'center'},
                {field: 'maintainTech', title: '大修工艺', width: 350, align: 'center'},
                {field: 'operateCompany', title: '实施单位', align: 'center'},
                {
                    field: 'operateTime', title: '实施时间', width: 280, align: 'center'
                }
                // ,
                // ,
                // {
                //     field: '', title: '履历', align: 'center',
                //     formatter: function (value, row, index) {
                //         var details = '<div  class="updates_bt dise-lvli" onclick="showlvli(\''+row.FAULT_ID+'\',event)" >' +
                //             '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;履历</div>';
                //         return details;
                //     }
                // }
            ],
            responseHandler: function (result) {
                if (result.code == 200) {
                    segmentCommon.status.tab_maintain = segmentId;
                    return result.data;
                } else {
                    return result.message;
                }
            }
        });
        $(id).bootstrapTable(tableOption);
    };
    return mainTainTable;
};

function queryMainTain() {
    $("#maintain_list").bootstrapTable('refresh');
}

