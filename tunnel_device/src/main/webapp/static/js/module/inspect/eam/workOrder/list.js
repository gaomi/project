var $eamWorkOrderSearchForm = $('#eam_workOrder_search_form');
var searchEam = true;
;!function () {

    $MB.initDatepicker('time-select');

    function addFunctionAlty(i, r) {
        var statusBtn = '<正常button type="button"  style="margin-right:15px;" paoding-modal-size="1000_600" paoding_opt="WorkOrderCheck"';
        if (r.ORDER_STATUS !== '2') {
            statusBtn += 'class="btn-dark btn-xs paoding_btn_check" disabled="disabled"'
        } else {
            statusBtn += 'class="btn-primary btn-xs paoding_btn_check"'
        }
        statusBtn += ' ><i class="fa fa-pencil-square-o" ></i>&nbsp;任务审核</button>';
        return [
            '<button type="button" class="btn-primary btn-xs paoding_btn_detail" style="margin-right:15px;" paoding-modal-size="800_600"><i class="fa fa-pencil-square-o" ></i>&nbsp;查看</button>',
            statusBtn,
            '<button type="button" class="btn-default btn-xs paoding_btn_update" style="margin-right:15px;"><i class="fa fa-pencil-square-o" ></i>&nbsp;驳回</button>',
        ].join('');

    }

    var operateEvents = {
        'click .paoding_btn_edit': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.UUID;
            editRow = row;
            openEditPage('eam_workOrder', editRow, _clickBtn);
        }, 'click .paoding_btn_check': function (e, value, row, index) {
            var _clickBtn = e.currentTarget;
            row['objId'] = row.UUID;
            editRow = row;
            //触发check页面菜单点击事件。
            // var checkMenu = $('ul.sidebar-menu').children('li.active').find('li').get(1);
            // checkMenu.click();
            openEditPage('eam_workOrder', editRow, _clickBtn);
            //审核页面加载
            //  $('#my-box-widget').boxWidget('toggle');

        }
    };
    var settings = {
        url: ctx + "/module/eam/workOrder/list",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: {
                    keyWord: $eamWorkOrderSearchForm.find("input[name='keyWord']").val().trim(),
                    status: $("#statusType_search_select").val()
                }
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'UUID',
            visible: false
        }, {
            field: 'PLAN_NO',
            title: '工单编号'
        }, {
            field: 'LINENO',
            title: '线路编号'
        }, {
            field: 'START_STATION_NAME',
            title: '开始站'
        }, {
            field: 'END_STATION_NAME',
            title: '结束站'
        }, {
            field: 'HIGHLOW',
            title: '上下行'
        }, {
            field: 'COMPANY_WHDW',
            title: '部门'
        }, {
            field: 'REGISTER_STATION_NAME',
            title: '登记车站'
        }, {
            field: 'PLANSTARTTIME',
            title: '任务开始时间',
            formatter: function (value, row, index) {
                return timestampToTime(value);
            }
        }, {
            field: 'PLANENDTIME',
            title: '任务结束时间',
            formatter: function (value, row, index) {
                return timestampToTime(value);
            }
        }, {
            field: 'STATUS_T',
            title: '状态'
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

    $MB.initTable('eam_workOrder_table', settings);

}();

function search() {
    $MB.refreshTable('eam_workOrder_table');
}

function refresh() {
    $eamWorkOrderSearchForm[0].reset();
    $MB.refreshTable('eam_workOrder_table');
}

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
    eachdata(baseData.stations,lineChange)
    $(this).find('option').each(function(){
        if($(this).text() == lineChange){
            $(this).attr("selected",true);
        }
    })

});
function eachdata(data,line){
    var htmlLine = '';
    var htmlStation =  "<option value=''>---请选择---</option>";
    $.each(data,function(i,n){
        htmlLine+="<option value=" + n.LINE_CODE+" data-id="+n.LINE_UUID+">" + n.LINE_NAME + "</option>"
        if(n.LINE_NAME==line){
            $.each(n.STATIONS,function(i,m){
                htmlStation+="<option value=" + m.STATION_CODE + " data-id="+m.STATION_UUID+"> "+ m.STATION_NAME + "</option>"
            })
        }
    })
    $('select[id="startStaType_search_select"]').html(htmlStation)
    $('select[id="endStaType_search_select"]').html(htmlStation)
}
// // 监听浏览器窗口变化
// var deviceWidth = $(document .body).width();
// $(window).resize(function () {
//     deviceWidth = $(document .body).width();
//     $("#eam_workOrder_search_form").css({height:"43px"});
//
// });
// $(".autoHeightEam").click(function () {
//     if(searchEam){
//         if(deviceWidth>1484){
//             $("#eam_workOrder_search_form").animate({height:"98px"},200);
//         }else if(deviceWidth<=1484&&deviceWidth>1223){
//             $("#eam_workOrder_search_form").animate({height:"147px"},200);
//         }else if(deviceWidth<=1223&&deviceWidth>1000){
//             $("#eam_workOrder_search_form").animate({height:"196px"},200);
//         }else if(deviceWidth<=1000&&deviceWidth>799){
//             $("#eam_workOrder_search_form").animate({height:"245px"},200);
//         }else if(deviceWidth<=799&&deviceWidth>792){
//             $("#eam_workOrder_search_form").animate({height:"294px"},200);
//         }else if(deviceWidth<=792&&deviceWidth>768){
//             $("#eam_workOrder_search_form").animate({height:"392px"},200);
//         }else if(deviceWidth<=768){
//             $("#eam_workOrder_search_form").animate({height:"524px"},200);
//         }
//         searchEam = false;
//         $(this).text("关闭全部")
//     }else{
//         $("#eam_workOrder_search_form").animate({height:"43px"},200);
//         searchEam = true;
//         $(this).text("展开全部")
//     }
//
// });
