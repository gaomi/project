;!function () {
    $.fn.modal.Constructor.prototype.enforceFocus = function () {
    };
    $.fn.select2.defaults.set('width', '100%');

    $('.select2').select2({
        minimumResultsForSearch: -1
    });

    EmploreName_select2();

    loadDict(faultDict);
    /* $('#begin_time').daterangepicker({timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A'})
     $('#end_time').daterangepicker({timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A'})*/
    $('.time-select').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii:ss',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });

    $('#d_keepRenderingSort').multiselect({
        keepRenderingSort: true
    });
    $('#p_keepRenderingSort').multiselect({
        keepRenderingSort: true
    });
}();

function colse1Modal() {
    debugger;
    $('#commonModal').modal('hide');
}

/**
 * 装载字典数据
 */
function loadDict(dictData) {
    // id="plan_type" , id="major_type" , id="plan_type"
    $('#edit_major_type').select2({
        data: dictData.major,
        placeholder: '请选择',
        allowClear: true
    });
    $('#edit_plan_type').select2({
        data: dictData.plan_type,
        placeholder: '请选择',
        allowClear: true
    });
    $('#edit_work_type').select2({
        data: dictData.work_type,
        placeholder: '请选择',
        allowClear: true
    });

}

/*施工单位*/
function EmploreName_select2() {
    var EmploreId;
    // if(comcodeC==03){
    //     EmploreId = usercodeC;
    // }else{
    //     EmploreId = $('#CustomName').combobox('getValue');
    // }
    /*$.ajax({
        type: "post",
        dataType: "json",
        url: ctx + 'module/inspect/plan/getCompany',
        data: {
            "pageNum": 0,
            "pageSize": 0,
            "params": {
                /!* funcId: "EmploreNameQ",
                 EmploreId: EmploreId,
                 perName: params.term   *!/ //接收搜索框输入的姓名
            }
        },
        success: function (data) {// result为返回的数据
            if (data.code == 200) {

                companySelect(data.data);
            } else {
                $("#login_errorMsg").html(data.message);
                $("#login_errorMsg").css("display", "block");
            }
        },
        error: function (data) {
            $("#login_errorMsg").html(data.responseJSON.message);
            $("#login_errorMsg").css("display", "block");
        }
    });*/

    function companySelect() {
        $('#company').select2({
            //data: data.rows,
            placeholder: "输入姓名搜索...",
            allowClear: true,
            ajax: {
                type: "post",
                dataType: "json",
                url: ctx + '/module/fault/plan/getCompany',
                processResults: function (data, params) {
                    params.page = params.page || 1;

                    return {
                        results: data.data.rows,
                        pagination: {
                            more: (params.page * 10) < data.total
                        }
                    };

                }
            },
            cache: true
            /*escapeMarkup: function (markup) {
                return markup;
            }, // 字符转义处理自定义格式化防止xss注入
            formatResult: function formatRepo(repo) {
                return repo;
            }, // 函数用来渲染结果
            formatSelection: function formatRepoSelection(repo) {
                return repo.text;
            } // 函数用于呈现当前的选择*/

        }).on('change', function () {
            var id = $("#company option:selected").attr("value");
            debugger;
        });
    }

    /*$("#company").on('change', function () {
        //alert($("#mySelect option:selected").attr("value"))


        $.ajax({
            url: "/web/a/logistics/channelarea/logisticsChannelArea/areaIds2?channelId=" + id,
            type: 'post',
            data: {},
            async: true,   //如果不加，无法实现数据传值 false就是同步请求,true 是异步请求
            dataType: 'json',
            success: function (data) {
                //清空下拉框原来options
                $("#mySelect2").empty()
                $("#mySelect2").select2("data", null)
                $('#mySelect2').append("<option value='' selected='selected' style='display: none'></option>");
                // alert($("#mySelect2 option:selected").attr("value"))
                //alert("后端接受"+id)
                //console.log(data)
                $(data).each(function (key) {  //循环读取后台传来的Json数据
                    var id = data[key].id;   //公司id
                    var channelAreaName = data[key].channelAreaName; //公司名称
                    $('#mySelect2').append("<option value='" + id + "'>" + channelAreaName + "</option>");
                });

            }
        })
    })*/
    companySelect();

}

var arr = [
    {
        "text": "Group1",
        "id": "001",
        "children": [
            {
                "id": "001-1",
                "text": "member1-1",
            }
        ]
    },
    {
        "text": "Group2",
        "id": "002",
        "children": [
            {
                "id": "002-1",
                "text": "member2-1",
            },
            {
                "id": "002-2",
                "text": "member2-2",
            }
        ]
    },
    {
        "text": "Group3",
        "id": "003",
        "children": [
            {
                "id": "003-1",
                "text": "member3-1",
            },
            {
                "id": "003-2",
                "text": "member3-2",
            },
            {
                "id": "003-3",
                "text": "member3-3",
            }
        ]
    }
];