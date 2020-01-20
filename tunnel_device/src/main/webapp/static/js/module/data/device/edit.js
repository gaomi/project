;(function () {
    // 添加或删除变量
    var url;
    // 表单输入数据
    var paramsSheb;
    // 编辑数据提交按钮
    var SbFlag = true;
    debugger
    if (editType) {
        loadForm(editRow, 'data_device');
        url = "update";
        console.log(editRow)
    } else if (editRow.objId === -1) {
        loadForm(editRow, 'data_device');
    }else{
        url = "add";
    };
    // 添加的表单验证
    var $data_device_form = $('#data_device_edit_form');
    function valiform() {
        // 表单验证
        return $data_device_form.validate({
            debug: true,
            rules: {
                lineCode: {
                    required: true
                },
                lineName: {
                    required: true
                }
            },
            messages: {
                lineCode: {
                    required: "线路编号不能为空"
                },
                lineName: {
                    required: "线路名称不能为空"
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
    $("#commonModal .saveLine_btn").click(function(){
        if(SbFlag){
            qjFlag = SbFlag;
            if(!valiform().form()){
            }else{
                paramsSheb = {
                    LINE_ID:$("select[name=LINE_ID]").val().trim(),
                    lineName:$("input[name=lineName]").val().trim(),
                    remark:$("textarea[name=remark]").val().trim(),
                };
                if(url=="update"){
                    paramsSheb.uuid = editRow.uuid;
                }
                funDict(url);
            }
        }
    });
    function funDict(url) {
        $.ajax({
            type:"post",
            url:ctx+"/module/data/device/"+url,
            dataType:"json",
            data:paramsSheb,
            success:function (data) {
                if(data.code ==200){
                    $("#commonModal").modal("hide");
                    $MB.refreshTable("data_device_table");
                    SbFlag = true;
                }
            }
        })
    }
})();