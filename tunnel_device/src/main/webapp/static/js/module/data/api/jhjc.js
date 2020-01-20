var $jhjcTable = $("#api_jhjc_table");
var $jhjcSearchForm = $("#api_jhjc_search_form");
;!function () {
    var settings = {
        url: ctx + "/module/segment/jhjc/list",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: {
                    name: $jhjcSearchForm.find("input[name='projectname']").val().trim(),
                    stats: $("#status_search_select").val() == null ? '' : $("#status_search_select").val(),
                    level: $("#projectlevel_search_select").val() == null ? '' : $("#projectlevel_search_select").val()
                }
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            visible: false
        }, {
            field: 'line',
            title: '线路'
        }, {
            field: 'name',
            title: '项目名称'
        }, {
            field: 'level',
            title: '项目等级'
        }, {
            field: 'company',
            title: '公司单位'
        }, {
            field: 'state',
            title: '项目状态'
        // }, {
        //     field: 'summary',
        //     width:200,
        //     title: '项目描述'
        }, {
            field: 'company',
            title: '公司单位'
        }],
        onLoadSuccess: function (result, res, data) {

        }
    };

    $MB.initTable('api_jhjc_table', settings);
    initDictCheck();
}();

function search() {
    $MB.refreshTable('api_jhjc_table');
}

function refresh() {
    $jhjcSearchForm[0].reset();
    $MB.refreshTable('api_jhjc_table');
}

var toUpdatePorject = true;

function updataAllPorject() {
    if (toUpdatePorject) {
        toUpdatePorject = false;
        $.ajax({
            type: "POST",
            url: ctx + "/module/api/jhjc/GetProjectInfo",
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
        url: ctx + "/module/api/jhjc/initDictCheck",
        dataType: 'json',
        success: function (data) {
            if (data.code == 200) {
                debugger;
                initDictSelect("status_search_select",data.data.status);
                initDictSelect("projectlevel_search_select",data.data.level);
            } else {
                $MB.n_danger("数据字典加载失败");
            }
        }
    });
}

function initDictSelect(id,data) {
    var obj = $jhjcSearchForm.find("#"+id);
    var str ="";
    $.each(data,function(i,n){
        str+="<option value='"+n.dictValue+"'>"+n.dictValue+"</option>";
    });
    obj.append(str);
}


