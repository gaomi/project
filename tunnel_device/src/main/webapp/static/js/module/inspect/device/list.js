// 展开或关闭搜索项
var searchGd = true;
var $inspectDeviceSearchForm = $('#inspect_device_search_form');
;!function () {
    getDictData();
    $MB.initDatepicker('time-select');

    function addFunctionAlty(i, r) {
        var statusBtn = '<button type="button"  style="margin-right:15px;" paoding-modal-size="1000_600" paoding_opt="Edit"';
        if (r.IS_DOOR === '0') {
            // statusBtn += 'class="btn-dark btn-xs paoding_btn_check" disabled="disabled"'
            statusBtn += 'class="btn-dark btn-xs paoding_btn_check" "'
        } else {
            statusBtn += 'class="btn-primary btn-xs paoding_btn_check"'
        }
        statusBtn += ' ><i class="fa fa-pencil-square-o" ></i>&nbsp;设备审核</button>';
        return [
            '<button type="button" class="btn-primary btn-xs paoding_btn_detail" style="margin-right:15px;" paoding-modal-size="800_600"><i class="fa fa-pencil-square-o" ></i>&nbsp;查看</button>',
            statusBtn
        ].join('');

    }

    var operateEvents = {
        'click .paoding_btn_detail': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            openEditPage('_device', "add", _clickBtn);
            editRow=row.EQUIPNO;
        }, 'click .paoding_btn_check': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.EQUIPNO;
            editRow = row;
            openEditPage('device_check', editRow, _clickBtn);

        }
    };
    var settings = {
        url: ctx + "/module/device/list",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: getFromParam("inspect_device_search_form"),
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'UUID',
            visible: false
        },
        //     {
        //     field: 'PLAN_NO',
        //     title: '工单编号'
        // },
            {
            field: 'LINE_CODE',
            title: '线路编号'
        }, {
            field: 'START_STATION_NAME',
            title: '开始站'
        }, {
            field: 'END_STATION_NAME',
            title: '结束站'
        },  {
            field: 'EQUIPNAME',
            title: '设备类型1'
        }, {
            field: 'LEVEL4_NAME',
            title: '设备类型2'
        }, {
            field: 'UPDOWN_V',
            title: '上下行'
        // },   {
        //     field: 'INSERT_DATE',
        //     title: '处理时间',
        //     formatter: function (value, row, index) {
        //         return timestampToTime(value);
        //     }
        // },{
        //     field: 'STATUS',
        //     title: '状态'
        },
            {
                field: '', title: '操作', align: 'center',
                formatter: addFunctionAlty,
                events: operateEvents
            }],
        onLoadSuccess: function (result, res, data) {
            $MB.removeOverlay('overlay');
        }
    };

//设备类型下拉框
    loadGroupSelectData(faultTypeData, 'level', 'device', 'search');
    $MB.initTable('inspect_device_table', settings);
}();

function search() {
    $MB.refreshTable('inspect_device_table');
}

function refresh() {
    $inspectDeviceSearchForm[0].reset();
    $MB.refreshTable('inspect_device_table');
}
function loadGroupSelectData(data, type, id, editForm) {
    var datum = data[type];
    var groupOpt = '<option value="">---请选择---</option>';
    $.each(data['level3']['0103'], function (i, r) {
        groupOpt += '<optgroup label="' + r.name + '">';
        $.each(datum, function (j, o) {
            if (o.id.substring(0, 6) === r.id) {
                if(o.id==='01030502'){
                    groupOpt += "<option value=" + o.id + " data-pid=" + type + " selected='selected'>" + o.name + "</option>"
                }else{
                    groupOpt += "<option value=" + o.id + " data-pid=" + type + ">" + o.name + "</option>"
                }
            }

        });
        groupOpt += '</optgroup>';
    });
    $('select[id="' + id + 'Type_' + editForm + '_select"]').empty();
    $('select[id="' + id + 'Type_' + editForm + '_select"]').html(groupOpt);
    // selectExtracteda(datum, type, id, editForm);

};

/**
 * 关闭modal
 */
function closeModal() {
    $MB.closeAndRestModal("commonModal");
}

//线路下拉框
loadSelectData(stationData, 'stations', 'line', 'search');
$('select[id="lineType_search_select"]').change(function () {
    var lineChange = $(this).find(':selected').text();
    eachdata(baseData.stations, lineChange)
    $(this).find('option').each(function () {
        if ($(this).text() == lineChange) {
            $(this).attr("selected", true);
        }
    })

});

function eachdata(data, line) {
    var htmlLine = '';
    var htmlStation = "<option value=''>---请选择---</option>";
    $.each(data, function (i, n) {
        htmlLine += "<option value=" + n.LINE_CODE + " data-id=" + n.LINE_UUID + ">" + n.LINE_NAME + "</option>"
        if (n.LINE_NAME == line) {
            $.each(n.STATIONS, function (i, m) {
                htmlStation += "<option value=" + m.STATION_CODE + " data-id=" + m.STATION_UUID + "> " + m.STATION_NAME + "</option>"
            })
        }
    })
    $('select[id="startStaType_search_select"]').html(htmlStation)
    $('select[id="endStaType_search_select"]').html(htmlStation)
}
 // 监听浏览器窗口变化
var deviceWidth = $(document .body).width();
if(deviceWidth>1551){
    $(".autoHeightDev").css({display:'none'});
}else{
    $(".autoHeightDev").css({display:'inline-block'});
}
$(window).resize(function () {
    deviceWidth = $(document .body).width();
    if(deviceWidth>1551){
        $(".autoHeightDev").css({display:'none'});
    }else{
        $(".autoHeightDev").css({display:'inline-block'});
    }
});
