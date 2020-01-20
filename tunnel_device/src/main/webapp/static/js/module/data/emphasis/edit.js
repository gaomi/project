var $data_emphasis_edit_form = $('#data_emphasis_edit_form');
var validator;
;!$(function () {
    // validateRule();
    initFormInput();
    //编辑
    if (editType) {
        loadForm(editRow, 'data_emphasis');
        showSegment();
    } else if (editRow.objId === -1) {
        loadForm(editRow, 'data_emphasis');
    }
    $("input[name='status']").change(function () {
    });

    $("#commonModal .paoding_btn_save").click(function () {
        var name = $(this).attr("name");
        // var validator = $data_emphasis_edit_form.validate();
        // var flag = validator.form();
        // if (flag) {
        var url = '';
        if (!editType) {
            url = ctx + "/module/data/emphasis/add";
            editFormSubmit(url, "添加");
            initEmphasislineInfo();
        } else {
            url = ctx + "/module/data/emphasis/update";
            editFormSubmit(url, "修改");
        }
        // }

    });
});

function editFormSubmit(url, type) {
    $.ajax({
        url: url,
        data: $data_emphasis_edit_form.serialize(),
        type: 'POST',
        dataType: 'json',
        success: function (response) {
            if (response.code === 200) {
                closeModal();
                $MB.n_success(type + "成功");
                $MB.refreshTable("data_emphasis_table");
            } else $MB.n_danger(response.message);
        }
    });
}

function initFormInput() {
    if(emphasis.segmentData == null){
        $.ajax({
            type: "post",
            url: ctx + "/module/data/base/getSegmentInfo",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify({}),
            async: false,
            success: function (data) {
                if (data.code == '200') {
                    data.data.sort(sortLine);
                    emphasis.segmentData = data.data;
                    //加载线路下拉框
                    // loadingEmphasisLine("emphasisType_search_select", segmentData,"LINE_UUID","LINE_NAME",true);
                }
            }
        });
    }
    loadingEmphasisLine("edit_lineUuid", emphasis.segmentData, "LINE_UUID", "LINE_NAME", true);
}

//线路下拉框触发事件
$("#edit_lineUuid").change(function () {
    loadEmphasisSegment("edit_lineUuid", "edit_updown", "edit_segmentName",emphasis.segmentData);
});
//上下行下拉框触发事件
$("#edit_updown").change(function () {
    loadEmphasisSegment("edit_lineUuid", "edit_updown", "edit_segmentName",emphasis.segmentData);
});
//区间下拉框触发事件
$("#edit_segmentName").change(function () {
    var segment = $("#edit_segmentName").val();
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
                loadingEmphasisLine("edit_group_segment", data.data, "UUID", "SEGMENT_NAME", selected);
                $("#edit_segment_name").val(data.data[0].SEGMENT_NAME);
                $("#edit_segment_uuid").val(data.data[0].UUID);
            }
        }
    });
});
//上下行下拉框触发事件
$("#edit_group_segment").change(function () {
    var test = $("#edit_group_segment option:selected").text();
    var value = $("#edit_group_segment").val();
    $("#edit_segment_name").val(test);
    $("#edit_segment_uuid").val(value);
});

function showSegment(){
    loadEmphasisSegment("edit_lineUuid", "edit_updown", "edit_segmentName",emphasis.segmentData);
    var segment_uuid = $("#edit_segment_uuid").val();
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
        // async: false,
        success: function (data) {
            if (data.code == '200') {
                $("#edit_segmentName").val(data.data[0].NO_GROUP_UUID);
                var selected = false;
                if (data.data.length > 1) {
                    selected = true;
                }
                loadingEmphasisLine("edit_group_segment", data.data, "UUID", "SEGMENT_NAME", selected);
                $("#edit_group_segment").val($("#edit_segment_uuid").val());
            }
        }
    });
}

