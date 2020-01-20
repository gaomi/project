var $faultTypeTable = $("#device_faultType_table");
var $faultTypeSearchForm = $("#device_faultType_search_form");
;!function () {
console.log(faultTypeData)
    var settings = {
        url: ctx + "/module/device/faultType/list",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: getFromParam("device_faultType_search_form"),
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'UUID',
            visible: false
        }, {
            field: 'LEVEL3',
            title: '设备中类型'
        }, {
            field: 'LEVEL4',
            title: '设备小类型'
        }, {
            field: 'DEVICE_TYPE',
            title: '设备'
        }, {
            field: 'FAULT_TYPE1',
            title: '病害类型'
        }, {
            field: 'FAULT_TYPE2',
            title: '病害描述'
        }, {
            field: 'FAULT_GRADE',
            title: '病害等级'
        }, {
            field: 'REMARK',
            title: '备注'
        },
            {
                field: '', title: '详情', align: 'center', width: 120,
                formatter: function (value, row, index) {
                    // var Location = '&nbsp;&nbsp;<button type="button"  class="btn btn-primary btn-sm updates" >' +
                    //     '<span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;区间定位</button>';
                    return '<button type="button" class="btn-primary btn-xs paoding_btn_detail" style="margin-right:15px;" paoding-modal-size="800_600"><i class="fa fa-pencil-square-o" ></i>&nbsp;详情</button>';
                }
            }],
        onLoadSuccess: function (result, res, data) {
        },
    };

    $MB.initTable('device_faultType_table', settings);

    //设备等级下拉框
    faultTypeSelectData(faultTypeData, 'level', 'level', 'search');


    function faultTypeSelectData(data, type, id, editForm) {
        var datum = data[type];
        var groupOpt = '<option value="">---请选择---</option>';
        $.each(data['level3']['0103'], function (i, r) {
            groupOpt += '<optgroup label="' + r.name + '">';
            $.each(datum, function (j, o) {
                if (o.id.substring(0, 6) === r.id) {
                    groupOpt += "<option value=" + o.id + " data-pid=" + type + ">" + o.name + "</option>"
                }

            });
            groupOpt += '</optgroup>';
        });
        $('select[id="' + id + 'Type_' + editForm + '_select"]').empty();
        $('select[id="' + id + 'Type_' + editForm + '_select"]').html(groupOpt);
        // selectExtracteda(datum, type, id, editForm);

    };

    /**
     * 填充select选项
     * @param datum
     * @param id
     */
    function selectExtracteda(datum, type, id, editForm) {
        var selecthtml = "<option value=''>---请选择---</option>";
        $.each(datum, function (i, r) {
            selecthtml += "<option value=" + r.id + " data-pid=" + type + ">" + r.name + "</option>"
        });
        $('select[id="' + id + 'Type_' + editForm + '_select"]').empty();
        $('select[id="' + id + 'Type_' + editForm + '_select"]').html(selecthtml);
    };
    //下拉框联动
    $('select[id="levelType_search_select"]').change(function () {
        selectOnChange(faultTypeData.device, 'device', this, 'search');
        selectOnChange(faultTypeData.fault1, 'fault1', this, 'search');
        //selectOnChange(faultTypeData.fault2, 'fault2', this, 'search');
    });
    $('select[id="deviceType_search_select"]').change(function () {
        selectOnChange(faultTypeData.fault1, 'fault1', this, 'search');
        //selectOnChange(faultTypeData.fault2, 'fault2', this, 'search');
    });

}();

function search() {
    $MB.refreshTable('device_faultType_table');
}

function refresh() {
    $faultTypeSearchForm[0].reset();
    $MB.refreshTable('device_faultType_table');
}

