var $deviceLevelTable = $("#device_level_table");
var $deviceLevelSearchForm = $("#device_level_search_form");
;!function () {
var operateFormatter = {};
    $deviceLevelTable.bootstrapTable({
        method: 'post',
        url: ctx + '/module/device/level/treelist',
        //data: data,
        dataType: 'json',
        idField: 'nid',
        //rootParentId: "0",
        columns: [
            /*{
                field: 'check', checkbox: true, formatter: function (value, row, index) {
                    if (row.check == true) {
                        // console.log(row.serverName);
                        //设置选中
                        return {checked: true};
                    }
                }
            },
            {field: 'name', title: '名称'},
            // {field: 'id', title: '编号', sortable: true, align: 'center'},
            // {field: 'pid', title: '所属上级'},
            {field: 'status', title: '状态', sortable: true, align: 'center', formatter: 'statusFormatter'},
            {field: 'permission', title: '权限值'},
            {field: 'operate', title: '操作', align: 'center', events: operateEvents, formatter: 'operateFormatter'},*/
            {
                field: 'selectItem',
                checkbox: true
            }, /* {
                title: '编号',
                field: 'id'
            },*/ {
                field: 'LEVEL_NAME',
                title: '分类名称'
            }, {
                field: 'STATUS',
                title: '状态',
                formatter: function (value, row, index) {
                    if (value === "1") return '<span class="badge bg-green">有效</span>';
                    if (value === "0") return '<span class="badge badge-success">无效</span>';
                }
            }/*, {
                field: 'permission',
                title: '权限标识',
                formatter: function (index, item) {
                    return item.permission;
                }
            }*/, {field: 'operate', title: '操作', align: 'center', formatter: function (value, row, index) {
                    return '<button type="button" class="btn-primary btn-xs paoding_btn_detail" style="margin-right:15px;" paoding-modal-size="800_600"><i class="fa fa-pencil-square-o" ></i>&nbsp;详情</button>';
                },events: operateFormatter}
        ],

        // bootstrap-table-treegrid.js 插件配置 -- start

        //在哪一列展开树形
        treeShowField: 'LEVEL_NAME',
        //指定父id列
        parentIdField: 'pid',
        onLoadSuccess: function (result, res, data) {
        },
        queryParams: function (params) {
            return {
                // pageSize: params.limit,
                // pageNum: params.offset / params.limit + 1,
                "params": {
                    "keyWord": $deviceLevelSearchForm.find("input[name='keyWord']").val().trim()
                }
            };
        },
        responseHandler: function (result) {
            if (result.code == 200) {
                $.each(result.data, function (i, r) {
                    r.pid = parseInt(r.PID);
                    r.nid = parseInt(r.UUID);
                });
                return result.data;
            } else {
                return result.message;
            }
        },
        onResetView: function (data) {
            //console.log('load');
            // $deviceLevelTable.treegrid({
            //     initialState: 'collapsed',// 所有节点都折叠
            //     // initialState: 'expanded',// 所有节点都展开，默认展开
            //     treeColumn: 1,
            //     // expanderExpandedClass: 'glyphicon glyphicon-minus',  //图标样式
            //     // expanderCollapsedClass: 'glyphicon glyphicon-plus',
            //     onChange: function () {
            //         $deviceLevelTable.bootstrapTable('resetWidth');
            //     }
            // });

            //只展开树形的第一级节点
            // $deviceLevelTable.treegrid('getRootNodes').treegrid('expand');

        },
        onCheck: function (row) {
            var datas = $deviceLevelTable.bootstrapTable('getData');
            // 勾选子类
            selectChilds(datas, row, "id", "pid", true);

            // 勾选父类
            selectParentChecked(datas, row, "id", "pid")

            // 刷新数据
            $deviceLevelTable.bootstrapTable('load', datas);
        },

        onUncheck: function (row) {
            var datas = $deviceLevelTable.bootstrapTable('getData');
            selectChilds(datas, row, "id", "pid", false);
            $deviceLevelTable.bootstrapTable('load', datas);
        },
        // bootstrap-table-treetreegrid.js 插件配置 -- end
    });

    //设备等级下拉框
    loadSelectData(faultTypeData.level3, '0103', 'level3', 'search');
    //下拉框联动
    $('select[id="level3Type_search_select"]').change(function () {
        selectOnChange(faultTypeData.level4, 'level4', this, 'search');
    });

}();

function search() {
    $MB.refreshTable('device_level_table');
}

function refresh() {
    $typedeviceLevelSearchForm[0].reset();
    $MB.refreshTable('device_level_table');
}


