var $personTable = $("#api_person_table");
var $personSearchForm = $("#api_person_search_form");
;!function () {

    var settings = {
        url: ctx + "/module/api/aqbhq/getAllPersonInfo",
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                params: {
                    personname: $personSearchForm.find("input[name='personname']").val().trim()
                        }
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'PERSONID',
            visible: false
        }, {
            field: 'PERSONNAME',
            title: '姓名'
        }, {
            field: 'PERSONSEX',
            title: '性别'
        }, {
            field: 'PERSONDESCRIPTION',
            title: '职位'
        }, {
            field: 'PERSONCONTACT',
            title: '联系电话'
        // }, {
        //     field: 'METROLINES',
        //     title: '线路'
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

    $MB.initTable('api_person_table', settings);
}();

function search() {
    $MB.refreshTable('api_person_table');
}

function refresh() {
    $personSearchForm[0].reset();
    $MB.refreshTable('api_person_table');
}

var toUpdatePerson = true;
function updataAllPerson(){
    if(toUpdatePerson){
        toUpdatePerson=false;
        $.ajax({
            type: "POST",
            url: ctx + "/module/api/aqbhq/updateAllPersonInfo",
            dataType:'json',
            success: function (data) {
                toUpdatePerson=true;
                if (data.code == 200) {
                    $MB.n_success("更新成功");
                } else {
                    $MB.n_danger("更新失败");
                }
            }
        });
    }else{
        $MB.n_info("努力更新中,请稍等。。。");
    }
}


