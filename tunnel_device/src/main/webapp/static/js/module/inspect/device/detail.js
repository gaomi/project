
;!$(function () {
    $.ajax({
        type: "post",
        url: ctx + "/module/device/detail",
        dataType:'json',
        data:{id:editRow},
        success: function (data) {
            if (data.code) {
                var params = $("#device_detail_form [name]");
               $.each(params,function(i,n){
                   var key = $(n).attr("name");
                   $(n).text(data.data[key]);
               });
            }else{
                $MB.n_warning("详情查询失败");
            }
        }
    });
});