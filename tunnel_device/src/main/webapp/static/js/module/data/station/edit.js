;(function () {
    // 添加或删除变量
    var station={};
    // 编辑数据提交按钮
    station.StationFlag = true;
    // 表单输入数据
    var paramsStation;
    //线路下拉框
    var htmlStation="<option value=''>---请选择---</option>";
  $.each(stationData.stations,function(i,n){
      htmlStation+= "<option value=" + n.LINE_CODE+ " data-code="+n.LINE_UUID+">" + n.LINE_NAME + "</option>"
    });
  $("#line2Type_search_select").html(htmlStation);
    //站类型
    loadSelectData(dictData, '98', 'class2', 'search');
    if (editType) {
        loadForm(editRow, 'data_station');
        station.url = "update";
    } else if (editRow.objId === -1) {
        loadForm(editRow, 'data_station');
    }else{
        station.url = "add";
    };
    // 添加的表单验证
    var $data_station_form = $('#data_station_edit_form');
    function valiform() {
        // 表单验证
        return $data_station_form.validate({
            debug: true,
            rules: {
                lineCode: {
                    required: true
                },
                stationCode: {
                    required: true
                } ,
                stationName: {
                    required: true
                },
                isTunnel: {
                    required: true
                },
                classType: {
                    required: true
                }
            },
            messages: {
                lineCode: {
                    required: "线路编号不能为空"
                },
                stationCode: {
                    required: "站编号不能为空"
                },
                stationName: {
                    required: "站名称不能为空"
                },
                isTunnel: {
                    required: "位置不能为空"
                },
                classType: {
                    required: "站类型不能为空"
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
    $("#commonModal .saveStation_btn").click(function(){
        if(station.StationFlag ){
            station.StationFlag  = false;
            if(!valiform().form()){
            }else{
                paramsStation = {
                    lineCode:$("select[name=lineCode] option:selected").val().trim(),
                    lineUuid:$("select[name=lineCode] option:selected").attr("data-code").trim(),
                    stationCode:$("input[name=stationCode]").val().trim(),
                    stationName:$("input[name=stationName]").val().trim(),
                    isTunnel:$("select[name=isTunnel] option:selected").val().trim(),
                    classType:$("select[name=classType] option:selected").val().trim()
                };
                if(url=="update"){
                    paramsStation.uuid = editRow.uuid;
                }
                funDict(station.url);
            }
        }
    });
    function funDict(url) {
        $.ajax({
            type:"post",
            url:ctx+"/module/data/station/"+url,
            dataType:"json",
            data:paramsStation,
            success:function (data) {
                if(data.code ==200){
                    $("#commonModal").modal("hide");
                    $MB.refreshTable("data_station_table");
                }
                station.StationFlag  = true;
            }
        })
    }
})();