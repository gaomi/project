;!function () {
    // 添加或删除变量
    var url;
    // 表单输入数据
    var paramsDict;
    if (editType) {
        loadForm(editRow, 'data_dict');
        url = "update";
    } else if (editRow.objId === -1) {
        loadForm(editRow, 'data_dict');
    } else {
        url = "add";
    }
// 添加的表单验证
    var $data_dict_edit_form = $('#data_dict_edit_form');

    function valiform() {
        // 表单验证
        return $data_dict_edit_form.validate({
            debug: true,
            rules: {
                dictKey: {
                    required: true
                },
                dictValue: {
                    required: true
                },
                forModlue: {
                    required: true
                },
                remark: {
                    required: true
                }
            },
            messages: {
                dictKey: {
                    required: "键不能为空"
                },
                dictValue: {
                    required: "值不能为空"
                },
                forModlue: {
                    required: "归属模块不能为空"
                },
                remark: {
                    required: "备注不能为空"
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
    $("#commonModal .saveLxsz_btn").click(function () {
        if (!valiform().form()) {
            // $("input.has-error").parent('div').addClass('has-error');
            // $("textarea.error").prev().css({"color": "red"})
        } else {
            paramsDict = {
                dictKey: $("input[name=dictKey]").val().trim(),
                dictValue: $("input[name=dictValue]").val().trim(),
                forModlue: $("input[name=forModlue]").val().trim(),
                remark: $("textarea[name=remark]").val().trim(),
                status: $("input[name=status]").val().trim(),
                pid: dataNum,
                uuid: dictUuid

            };
            var dictSeq = $("input[name=seq]").val().trim();
            if (dictSeq != "") {
                paramsDict.seq = dictSeq;
            }
            funDict(url);
        }
    });

    function funDict(urlKey) {
        $.ajax({
            type: "post",
            url: ctx + "/module/data/dict/" + urlKey,
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




