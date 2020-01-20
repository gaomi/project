var $aqbhqTable = $("#api_aqbhq_table");
var $aqbhqSearchForm = $("#api_aqbhq_search_form");
;!function () {

    var settings = {
        url: ctx + "/module/segment/aqbhq/list",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: {
                    projectname : $aqbhqSearchForm.find("input[name='projectname']").val().trim(),
                    status: $("#status_search_select").val() == null ? '' : $("#status_search_select").val(),
                    projectlevel: $("#projectlevel_search_select").val() == null ? '' : $("#projectlevel_search_select").val()
                }
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'PROJECTID',
            visible: false
        }, {
            field: 'LINE_NAME',
            title: '线路'
        }, {
            field: 'PROJECTNAME',
            title: '项目名称'
        }, {
            field: 'PROJECTLEVEL',
            title: '项目等级'
        // }, {
        //     field: 'PROJECTDESCRIPTION',
        //     title: '项目描述'
        }, {
            field: 'PROJTASKLASTRESULTSTATUS',
            title: '项目状态'
        }, {
            field: 'PROJECTBUILDUNIT',
            title: '施工单位'
        }, {
        //     field: 'PROJECTLOCATIONWITHMETRO',
        //     title: '项目与铁路间距'
        // }, {
                field: 'PROJECTADDRESS',
                title: '项目地址'
            }, {
            field: 'PROJECTAREA',
            title: '施工面积/平方米'
        }, {
                field: 'INSERT_DATE',
                title: '更新时间',
                formatter: function (value, row, index) {
                    return timestampToTime(value);
                }
            }
            /*,
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

        }
    };

    $MB.initTable('api_aqbhq_table', settings);
    initDictCheck();

}();

function search() {
    $MB.refreshTable('api_aqbhq_table');
}

function refresh() {
    $aqbhqSearchForm[0].reset();
    $MB.refreshTable('api_aqbhq_table');
}

var toUpdatePorject = true;

function updataAllPorject() {
    if (toUpdatePorject) {
        toUpdatePorject = false;
        $.ajax({
            type: "POST",
            url: ctx + "/module/api/aqbhq/GetProjectInfo",
            dataType: 'json',
            success: function (data) {
                toUpdatePorject = true;
                if (data.code == 200) {
                    $MB.n_success("更新成功");
                } else {
                    $MB.n_danger("更新失败");
                }
            }
        });
    } else {
        $MB.n_info("努力更新中,请稍等。。。");
    }
}

function initDictCheck(){
    $.ajax({
        type: "POST",
        url: ctx + "/module/api/aqbhq/initDictCheck",
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                initDictSelect("status_search_select",data.data.status);
                initDictSelect("projectlevel_search_select",data.data.level);
            } else {
                $MB.n_danger("数据字典加载失败");
            }
        }
    });
}

function initDictSelect(id,data) {
    var obj = $aqbhqSearchForm.find("#"+id);
    var str ="";
    $.each(data,function(i,n){
        str+="<option value='"+n.dictValue+"'>"+n.dictValue+"</option>";
    });
    obj.append(str);
}


