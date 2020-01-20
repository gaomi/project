var faultDict;
var faultLog = {};
faultLog.flag = false;
faultLog.searchGd = true;
;!function () {
    $MB.initDatepicker('time-select');
    getDictData();
    /*页面内容更换*/
    $(document).on('click', '.more_search', function () {
        $('#more_search_form').addClass('more_search_form');
        $('#more_search_form').removeClass('more_search_form');
        debugger;
    });

    function addFunctionAlty() {
        return [
            '<button type="button" class="btn-primary btn-xs paoding_btn_detail" style="margin-right:15px;" paoding-modal-size="800_600"paoding_opt="Edit"><i class="fa fa-pencil-square-o" ></i>&nbsp;查看</button>',
            '<button type="button" class="btn-primary btn-xs paoding_btn_edit" style="margin-right:15px;"  paoding-modal-size="800_600" paoding_opt="Edit" ><i class="fa fa-pencil-square-o" ></i>&nbsp;编辑</button>',
            '<button type="button" class="btn-primary btn-xs paoding_btn_lvli" style="margin-right:15px;"  ><i class="fa fa-pencil-square-o" ></i>&nbsp;履历</button>',
        ].join('');

    }

    var operateEvents = {
        'click .paoding_btn_edit': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.UUID;
            editRow = row;
            faultLog.flag = false;
            openEditPage('fault_log', editRow, _clickBtn);
        },
        'click .paoding_btn_detail': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.UUID;
            editRow = row;
            openEditPage('fault_log', editRow, _clickBtn);
            faultLog.flag = true;
        },
        'click .paoding_btn_lvli': function (e, value, row, index) {
            faultLog.lvliUuid = row.INTERNAL_CODE;
            $("#commonModal").find(".modal-dialog").css("width",  '900px', "height",  '600px');
            $("#commonModal").modal({
                remote: ctx + '/module/fault/log/tolvli',
                keyboard: true//当按下esc键时，modal框消失
            });
        }
    }
    var settings = {
        url: ctx + "/module/fault/log/list",

        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params:getFromParam("fault_log_search_form")
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'UUID',
            visible: false
        },
            {
            field: 'LINE_NAME',
            title: '线路'
        },  {
                title: '区间',
                formatter:function (index,value) {
                    return value.START_STA_NAME_T+" - "+value.END_STA_NAME_T
                }
            },
            {
            field: 'UPDOWN',
            title: '上下行',
            // formatter:function (index,row) {
            //     if(row.UPDOWN ==""){
            //         return "上行"
            //     }else  if(row.UPDOWN =="1"){
            //         return "下行"
            //     }
            // }
        },
            {
            field: 'LEVEL3_NAME',
            title: '设备名称'
        }, {
            field: 'LEVEL4_NAME',
            title: '设备类型'
        }, {
            field: 'DEVICE_TYPE_NAME',
            title: '设备部位'
        },
            {
            title: '病害分类',
             formatter:function (index,value) {
                return value.FAULT_TYPE1_NAME+" - "+value.FAULT_TYPE2_NAME
             }
        }, {
            field: 'FAULT_LEVEL',
            title: '病害等级'
        }, {
            field: 'INSERT_DATE',
            title: '处理时间',
            formatter: function (value, row, index) {
                return timestampToTime(value);
                console.log(value)
            }
        },
            // {
            //     field: 'START_DUCT_CODE',
            //     title: '开始环号'
            // },{
            //     field: 'END_DUCT_CODE',
            //     title: '结束环号'
            // },
            {
                field: 'operate',
                title: '操作',
                events: operateEvents,//给按钮注册事件
                formatter: addFunctionAlty,//表格中增加按钮
                align: 'center', width: 180
            }],
        onLoadSuccess: function (result, res, data) {
            $MB.removeOverlay('overlay');
            // if (result.code == 20000) {
            //
            //
            // }

        },
    };

    $MB.initTable('fault_log_table', settings);
}();

function openPlanEdit(str, flag, btn) {
    var row = $("#fault_log_table").bootstrapTable('getSelections');
    if (row != null && row != undefined) {
        row['objId'] = row.UUID;
        editRow = row;
        debugger;
        openEditPage('fault_log', editRow, btn);
    } else {
        $MB.n_warning('请勾选需要编辑的任务！');
        return;
    }


}

function search() {
    $MB.refreshTable('fault_log_table');
}

/***
 * 数据字典
 */
function getDictData() {
    $.ajax({
        url: ctx + '/module/fault/log/getFaultStatus',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            if (data.code === 200) {
                faultDict = data.data;
                initSelectOption("statusType_search_select",faultDict);
            } else {
                console.log(data.message)
            }
        }
    })
}
//线路下拉框
loadSelectData(stationData, 'stations', 'line', 'search');
$('select[id="lineType_search_select"]').change(function () {
    var lineChange = $(this).find(':selected').text();
    eachdata(baseData.stations,lineChange);
    $(this).find('option').each(function(){
        if($(this).text() == lineChange){
            $(this).attr("selected",true);
        }
    })

});

function initSelectOption(id,faultDict){
    $("#"+id).empty();
    var htmlText = "<option value=''>---请选择---</option>";
    $.each(faultDict,function(i,n){
        htmlText+="<option value='" + n.dictKey+"'>" + n.dictValue + "</option>";
    });
    $("#"+id).append(htmlText);
}

function eachdata(data,line){
    var htmlLine = '';
    var htmlStation =  "<option value=''>---请选择---</option>";
    $.each(data,function(i,n){
        htmlLine+="<option value=" + n.LINE_CODE+" data-id="+n.LINE_UUID+">" + n.LINE_NAME + "</option>"
        if(n.LINE_NAME==line){
            $.each(n.STATIONS,function(i,m){
                htmlStation+="<option value=" + m.STATION_UUID + " data-id="+m.STATION_CODE+"> "+ m.STATION_NAME + "</option>"
            })
        }
    });
    $('select[id="startStaType_search_select"]').html(htmlStation);
    $('select[id="endStaType_search_select"]').html(htmlStation);
}
