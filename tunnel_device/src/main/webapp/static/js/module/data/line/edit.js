;(function () {
    // 添加或删除变量
    var line = {};
    // 编辑数据提交按钮
    line.qjFlag = true;
    // 表单输入数据
    var paramsLine;
    if (editType) {
        loadForm(editRow, 'data_line');
        line.url = "update";
    } else if (editRow.objId === -1) {
        loadForm(editRow, 'data_line');
    }else{
        line.url = "add";
    }
    // 添加的表单验证
    var $data_line_form = $('#data_line_edit_form');
    function valiform() {
        // 表单验证
        return $data_line_form.validate({
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
        if(line.qjFlag){
            line.qjFlag = false;
            if(!valiform().form()){
            }else{
                paramsLine = {
                    lineCode:$("input[name=lineCode]").val().trim(),
                    lineName:$("input[name=lineName]").val().trim(),
                    remark:$("textarea[name=remark]").val().trim(),
                };
                if(line.url=="update"){
                    paramsLine.uuid = editRow.uuid;
                }
                funDict(line.url);
            }
        }
    });
    function funDict(url) {
        $.ajax({
            type:"post",
            url:ctx+"/module/data/line/"+url,
            dataType:"json",
            data:paramsLine,
            success:function (data) {
                if(data.code ==200){
                    $("#commonModal").modal("hide");
                    $MB.refreshTable("data_line_table");
                    line.qjFlag = true;
                }
            }
        })
    }
})();