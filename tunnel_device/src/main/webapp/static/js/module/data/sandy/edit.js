;(function () {
    // 添加或删除变量
    var sandy = {};
    // var sandy.url;
    // 编辑数据提交按钮
    sandy.TcFlag = true;
    // 线路
    var htmlStation="<option value=''>---请选择---</option>";
    $.each(stationData.stations,function(i,n){
        htmlStation+= "<option value=" + n.LINE_CODE+ " data-code="+n.LINE_UUID+">" + n.LINE_NAME + "</option>"
    });
    $("#sandy_line_code").html(htmlStation);
    // 上下行联动
    $("#updown_sandy_change").change(function(){
        loadEmphasisSandy("sandy_line_code","updown_sandy_change","edit_sandyName");
    });
    // 线路联动
    $("#sandy_line_code").change(function(){
        var text = $(this).find("option:selected").attr("data-code");
        $("#sandy_line_uuid").val(text);
        loadEmphasisSandy("sandy_line_code","updown_sandy_change","edit_sandyName");
    });
    //区间下拉框触发事件
    $("#edit_sandyName").change(function () {
        var segment = $("#edit_sandyName").val();
        $.ajax({
            type: "post",
            url: ctx + "/module/data/emphasis/getGroupSegment",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify({
                params: {
                    segment: segment
                }
            }),
            // async: false,
            success: function (data) {
                if (data.code == '200') {
                    var selected = false;
                    if (data.data.length > 1) {
                        selected = true;
                    }
                    loadingSandyLine("edit_sandySmillName", data.data, "UUID", "SEGMENT_NAME", selected);
                    $("#edit_segmentSy_name").val(data.data[0].SEGMENT_NAME);
                    $("#edit_segmentSy_uuid").val(data.data[0].UUID);
                }
            }
        });
    });
    //小区间下拉框触发事件
    $("#edit_sandySmillName").change(function () {
        var test = $("#edit_sandySmillName option:selected").text();
        var value = $("#edit_sandySmillName").val();
        $("#edit_segmentSy_name").val(test);
        $("#edit_segmentSy_name").val(value);
    });
// 土层与与隧道位置select

                var htmlLocal = "<option>--请选择--</option>";
                var htmlcode = "<option>--请选择--</option>";
                $.each(sandyEditData.local,function (i,n) {
                    htmlLocal+="<option value='"+n.dictKey+"'>"+n.dictValue+"</option>"
                });
                $("select[name=sandyTunnelPosition]").html(htmlLocal);
                $.each(sandyEditData.code,function (i,n) {
                    htmlcode+="<option value='"+n.dictKey+"'>"+n.dictValue+"</option>"
                });
                $("select[name=soilLevel]").html(htmlcode);

    if (editType){
        loadForm(editRow, 'data_sandy');
        sandy.url = "update";
        // 设置区间select框
        showSegmentSy()
    } else if (editRow.objId === -1) {
        loadForm(editRow, 'data_sandy');
    }else{
        sandy.url = "add";
    }
    // 添加的表单验证
    var $data_sandy_form = $('#data_sandy_edit_form');
    function valiform() {
        // 表单验证
        return $data_sandy_form.validate({
            debug: true,
            rules: {
                LINE_CODE: {
                    required: true
                },
                SEG_UPDOWN: {
                    required: true
                },
                SEG_SEGMENT_NAME: {
                    required: true
                },
                SANDY_TUNNEL_POSITION_VALUE: {
                    required: true
                },
                SOIL_LEVEL_VALUE: {
                    required: true
                },
                START_MILEAGE: {
                    required: true
                },
                END_MILEAGE: {
                    required: true
                },
            },
            messages: {
                LINE_CODE: {
                    required: "线路编号不能为空"
                },
                SEG_UPDOWN: {
                    required: "上下行不能为空"
                },
                SEG_SEGMENT_NAME: {
                    required: "站区间不能为空"
                },
                SANDY_TUNNEL_POSITION_VALUE: {
                    required: "与隧道位置不能为空"
                },
                SOIL_LEVEL_VALUE: {
                    required: "土层不能为空"
                },
                START_MILEAGE: {
                    required: "起始环号不能为空"
                },
                END_MILEAGE: {
                    required: "结束环号不能为空"
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
    $("#commonModal .saveTc_btn").click(function(){
        if(sandy.TcFlag){
            sandy.TcFlag = false;
            if(!valiform().form()){
            }else{
                funDict(sandy.url);
            }
        }
    });
    function funDict(url) {
        $.ajax({
            type:"post",
            url:ctx+"/module/data/sandy/"+url,
            dataType:"json",
            data: $data_sandy_form.serialize(),
            success:function (data) {
                if(data.code ==200){
                    if(url=="add"){
                        $MB.n_success("添加成功");
                    }else if(url=="update"){
                        $MB.n_success("修改成功");
                    }
                    $("#commonModal").modal("hide");
                    $MB.refreshTable("data_sandy_table");
                    sandy.TcFlag = true;
                }
            }
        })
    };
    function showSegmentSy(){
        loadEmphasisSandy("sandy_line_code","updown_sandy_change","edit_sandyName");
        var segment_uuid = $("#edit_segmentSy_uuid").val();
        $.ajax({
            type: "post",
            url: ctx + "/module/data/emphasis/getSegmentByGroup",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify({
                params: {
                    group_segment: segment_uuid
                }
            }),
            success: function (data) {
                if (data.code == '200') {
                    $("#edit_sandyName").val(data.data[0].NO_GROUP_UUID);
                    var selected = false;
                    if (data.data.length > 1) {
                        selected = true;
                    }
                    loadingSandyLine("edit_sandySmillName", data.data, "UUID", "SEGMENT_NAME", selected);
                    $("#edit_sandySmillName").val($("#edit_segmentSy_uuid").val());
                }
            }
        });
    }
})();