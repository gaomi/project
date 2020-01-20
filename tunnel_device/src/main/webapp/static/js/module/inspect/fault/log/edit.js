;!function () {
    // 病害数据菜单
    console.log(faultTypeData)
    // 设备类型1
   faultLog.shebei1 = "<option value=''>--请选择--</option>";
    $.each(faultTypeData["level3"]["0103"],function(i,n){
        faultLog.shebei1+="<option value="+n.id+">"+n.name+"</option>";
    });
    $("select[name=level3]").html( faultLog.shebei1);
    // 回显数据
    if (editType) {
        loadForm(editRow, 'fault_log');
    } else if (editRow.objId === -1) {
        loadForm(editRow, 'fault_log');
    }
    function selectChange(data,type,id){
        faultLog.shebei2 = "<option value=''>--请选择--</option>";
        $.each(data,function(i,n){
            faultLog.shebei2+="<option value="+n.id+">"+n.name+"</option>";
        });
        $("select[name="+id+"]").html( faultLog.shebei2);
        $("select[name="+id+"]").val(type);
    }
    // 设备类型2
    selectChange(faultTypeData.level4[editRow.level3],editRow.level4,'level4');
    //具体部位
    selectChange(faultTypeData.device[editRow.level4],editRow.deviceType,'deviceType');

    //病害类型1
    selectChange(faultTypeData.fault1[editRow.deviceType],editRow.faultType1,'faultType1');
    // faultLog.bhlx1 = "<option value=''>--请选择--</option>";
    // $.each(faultTypeData.fault1[editRow.deviceType],function(i,n){
    //     faultLog.bhlx1+="<option value="+n.id+">"+n.name+"</option>";
    // });
    // $("select[name=faultType1]").html( faultLog.bhlx1);
    // $("select[name=faultType1]").val(editRow.faultType1);
    //病害类型2
    selectChange(faultTypeData.fault2[editRow.faultType1],editRow.faultType2,'faultType2');
    // faultLog.bhlx2 = "<option value=''>--请选择--</option>";
    // $.each(faultTypeData.fault2[editRow.faultType1],function(i,n){
    //     faultLog.bhlx2+="<option value="+n.id+">"+n.name+"</option>";
    //     console.log(faultLog.bhlx2)
    // });
    // $("select[name=faultType2]").html( faultLog.bhlx2);
    // $("select[name=faultType2]").val("");
    // 设备类型1下拉框联动
    $("select[name=level3]").change(function () {
        var text = $(this).val();
        selectChange(faultTypeData.level4[text],"",'level4');
        $("select[name=deviceType]").html("<option value=''>--请选择--</option>");
        $("select[name=faultType1]").html("<option value=''>--请选择--</option>");
        $("select[name=faultType2]").html("<option value=''>--请选择--</option>");
    });
    // 设备类型2下拉框联动
    $("select[name=level4]").change(function () {
        var text = $(this).val();
        selectChange(faultTypeData.device[text],"",'deviceType');
        $("select[name=faultType1]").html("<option value=''>--请选择--</option>");
        $("select[name=faultType2]").html("<option value=''>--请选择--</option>");
    });
    // 具体部位下拉框联动
    $("select[name=deviceType]").change(function () {
        var text = $(this).val();
        selectChange(faultTypeData.fault1[text],"",'faultType1');
        $("select[name=faultType2]").html("<option value=''>--请选择--</option>");
    });
    // 病害类型1下拉框联动
    $("select[name=faultType1]").change(function () {
        var text = $(this).val();
        selectChange(faultTypeData.fault2[text],"",'faultType2');
    });
    // 判断打开页面是查看还是编辑
    if(faultLog.flag==false){
}else{
        $("#fault_log_edit_form").find("input").prop("readonly",true);
        $("#fault_log_edit_form").find("select").prop("disabled",true);
        $(".box-footer").css({display:'none'});
        $(".box-title").text('查看数据')
    }
// 添加的表单验证
    var $fault_log_edit_form = $('#fault_log_edit_form');
    function valiform() {
        // 表单验证
        return $fault_log_edit_form.validate({
            debug: true,
            rules: {
                updown: {
                    required: true
                },
                deviceUuid: {
                    required: true
                },
                startDuctCode: {
                    required: true
                },
                endDuctCode: {
                    required: true
                },
                startMileageFlag: {
                    required: true
                },
                startKiMileage: {
                    required: true
                },
                startHunMileage: {
                    required: true
                },
                endMileageFlag: {
                    required: true
                },
                endKiMileage: {
                    required: true
                },
                endHunMileage: {
                    required: true
                } ,
                ductCount: {
                    required: true
                },
                level3: {
                    required: true
                },
                level4: {
                    required: true
                },
                deviceType: {
                    required: true
                },
                faultType1: {
                    required: true
                },
                faultType2: {
                    required: true
                },
                faultLevel: {
                    required: true
                },

            },
            messages: {
                updown: {
                    required: "上下行不能为空"
                },
                deviceUuid: {
                    required: "设备名称不能为空"
                },
                startDuctCode: {
                    required: "开始管片号不能为空"
                },
                endDuctCode: {
                    required: "结束管片号不能为空"
                },
                startMileageFlag: {
                    required: "开始里程标不能为空"
                },
                startKiMileage: {
                    required: "开始里程不能为空"
                },
                startHunMileage: {
                    required: "开始里程千米数不能为空"
                } ,
                endMileageFlag: {
                    required: "结束里程标不能为空"
                },
                endKiMileage: {
                    required: "结束里程不能为空"
                },
                endHunMileage: {
                    required: "结束里程千米数不能为空"
                },
                ductCount: {
                    required: "环数不能为空"
                },
                level3: {
                    required: "设备类型1不能为空"
                },
                level4: {
                    required: "设备类型2不能为空"
                },
                deviceType: {
                    required: "具体部位不能为空"
                },
                faultType1: {
                    required: "病害类型1不能为空"
                },
                faultType2: {
                    required: "病害类型2不能为空"
                } ,
                faultLevel: {
                    required: "病害等级不能为空"
                },

            },
            errorClass: "has-error",
            errorElement: "em",
            success: function (label) {
                label.text(" ") //清空错误提示消息
                    .addClass("success");
            }
        })
    };
    $("#commonModal .saveBhgl_btn").click(function () {
        if (!valiform().form()) {
            // $("input.has-error").parent('div').addClass('has-error');
            // $("textarea.error").prev().css({"color": "red"})
        } else {
            funDict();
        }
    });
    function funDict() {
        $.ajax({
            type: "post",
            url: ctx + "/module/inspect/fault/update",
            dataType: "json",
            data: paramsDict,
            success: function (data) {
                if (data.code == 200) {
                    $("#commonModal").modal("hide");
                    $MB.refreshTable("data_dict_table");
                }
            }
        })
    }
}();




