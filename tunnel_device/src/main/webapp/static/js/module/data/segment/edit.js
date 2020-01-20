
;!function () {
    // 添加或删除变量
    var segment={};
    // 表单输入数据
    var paramsQj;
    // 设置管片宽度
    var gpSetData;
    // 编辑数据提交按钮
     segment.qjFlag = true;
    if (editType) {
        loadForm(editRow, 'data_segment');
        segment.url = "update";
        gpSetData = editRow;
        if(editRow.UPDOWN=="0"){
            $("input[name=updown]").val("上行")
        }else if(editRow.UPDOWN=="1"){
            $("input[name=updown]").val("下行")
        }
        gpSetWidth(gpSetData);
    } else if (editRow.objId === -1) {
        loadForm(editRow, 'data_segment');
    }else{
        segment.url = "add";
    }
// 添加的表单验证
    var $data_segment_form = $('#data_segment_edit_form');
    function valiform() {
        // 表单验证
        return $data_segment_form.validate({
            debug: true,
            rules: {
                startMileageCode: {
                    required: true
                },
                endMileageCode: {
                    required: true
                },
                startMileageFlag:{
                    required: true
                },
                endMileageFlag:{
                    required: true
                }
            },
            messages: {
                startMileageCode: {
                    required: "起始里程不能为空"
                },
                endMileageCode: {
                    required: "结束里程不能为空"
                },
                startMileageFlag:{
                    required: "起始里程标不能为空"
                },
                endMileageFlag:{
                    required: "结束里程标不能为空"
                }
            },
            errorClass: "has-error",
            errorElement: "em",
            success: function (label) {
                label.text(" ") //清空错误提示消息
                    .addClass("success");
            }
        })
    };
    $("#commonModal .saveQj_btn").click(function () {
        if(segment.qjFlag ){
            segment.qjFlag  = false;
            if (!valiform().form()) {
            }else {
                paramsQj = {
                    startMileageCode:$("input[name=startMileageCode]").val().trim(),
                    endMileageCode:$("input[name=endMileageCode]").val().trim(),
                    startMileageFlag:$("input[name=startMileageFlag]").val().trim(),
                    endMileageFlag:$("input[name=endMileageFlag]").val().trim(),
                    uuid:editRow.uuid
                };
                funDict(segment.url)
            }
        }
    });
    function funDict(url) {
        $.ajax({
            type:"post",
            url:ctx+"/module/data/segment/"+url,
            dataType:"json",
            data:paramsQj,
            success:function (data) {
                if(data.code ==200){
                    $("#commonModal").modal("hide");
                    $MB.refreshTable("data_segment_table");
                    segment.qjFlag  = true;
                }
            }
        })
    }
    // 根据里程计算管片宽度
    function gpSetWidth(data) {
        var duct_codeNum =Math.abs(data.startDuctCode-data.endDuctCode);
        var mileage_codeNum =Math.abs(data.startMileageCode-data.endMileageCode);
        var codeNumTofixed = mileage_codeNum/duct_codeNum;
        if(codeNumTofixed){
            $("input[name=gpWidth]").val(codeNumTofixed.toFixed(2)+"米");
        }else {
            $("input[name=gpWidth]").val("");
        }
    }
    $("input[name=startMileageCode],input[name=endMileageCode]").change(function (e) {
       if(e.target.name=="endMileageCode"){
           gpSetData.END_MILEAGE_CODE = $(this).val();
           gpSetWidth(gpSetData);
       }else if(e.target.name=="startMileageCode"){
           gpSetData.START_MILEAGE_CODE = $(this).val();
           gpSetWidth(gpSetData);
       }
    })
}();




