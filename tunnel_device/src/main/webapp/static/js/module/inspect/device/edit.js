var deviceImageSy = {};
deviceImageSy.headText = "";
deviceImageSy.headText +="<div class='row'> <div class='col-md-3'>线路：<span>"+editRow.LINE_CODE+"</span></div><div class='col-md-3'>区间: <span>"+editRow.START_STATION_NAME+"-"+editRow.END_STATION_NAME+"</span></div><div class='col-md-3'> 部位：<span>"+editRow.EQUIPNAME+"</span></div><div class='col-md-3'> 环号：<span>"+editRow.DUCT_CODE_START+"</span></div></div>"
$(".sbshHeader").html(deviceImageSy.headText);
function addIngBtnAll() {
    return  '<button type="button" class="btn-primary btn-xs paoding_btn_xq" style="margin-right:15px;" paoding-modal-size="800_600"><i class="fa fa-pencil-square-o" ></i>&nbsp;详情</button>'
}
var operateEvents = {
    "click .paoding_btn_xq":function (e,value,row,index) {
      editCheckXq(row.UUID)
    }
};
// 工单审核表格
$(".query_checkTable").click(function () {
    $MB.refreshTable('device_edit_table');
    firstRow()
});

function firstRow() {
    var res = $('#device_edit_table').bootstrapTable("getData");
    if(res==""){
        $(".xqBottomAll").css({display:'none'});
    }else{
        editCheckXq(res[0].UUID)
    }
}

/***
 * 获取选框年份
 */
function initSelectYear(){
    $.ajax({
        type: "POST",
        url: ctx + "/module/device/check/getDict",
        data:JSON.stringify({
            params:{device_uuid:'0730300103050102000008'}
        }),
        dataType:'json',
        contentType:'application/json',
        success: function (data) {
            if (data.code == '200') {
                $("#check_query_date").empty();
                var html = "<option value=''>--请选择--</option>";
                $.each(data.data.year,function(i,n){
                    html+="<option value="+n+">"+n+"年</option>"
                });
                $("#check_query_date").append(html);
            }else{
                $MB.n_warning("获取搜索年份失败");
            }
        }
    });
}
var settings = {
    url: ctx + "/module/device/check/list",
    queryParams: function (params) {
        return {
            pageSize: params.limit,
            pageNum: params.offset / params.limit + 1,
            // params:{deviceUuid:editRow.objId}
            params:{deviceUuid:'0730300103050102000008',date:getDateYeat()},
        };
},
    onLoadSuccess: function (result,res,data) {
        firstRow()
    },
    columns: [{
        field: 'UUID',
        visible: false
    },{
        field:'CREATE_TIME',
        title:'创建时间'
    },{
        field:"WORK_T",
        title:'检查类型'
    },{
        field:'SGDW',
        title:"部门"
    },{
        field:'NAME',
        title:"人员"
    },{
        field:'INSERT_DATE',
        title:'上传时间'
    },{
        field:'RES_COUNT',
        title:'附件数'
    },{
        title:"操作",
        align: 'center',
        formatter:addIngBtnAll,
        events:operateEvents
    }

    ]
};

function getDateYeat(){
    if($("#check_query_date").val() != '' && $("#check_query_date").val() != null){
        return $("#check_query_date").val();
    }else{
        return new Date().getFullYear();
    }
}

function editCheckXq(id) {
    $(".xqBottomAll").css({display:'block'});
    var obj = {
        pageSize:0,
        pageNum:0,
        params:{deviceUuid:'0730300103050102000008',date:getDateYeat(),plan_uuid:id}
    };
    $.ajax({
        type:"post",
        url: ctx + "/module/device/check/checkItem",
        dataType:'json',
        data:JSON.stringify(obj),
        contentType: "application/json",
        success:function (data) {
            if(data.data!=''){
                $(".xqMenuLeft").css({display:'block'});
                var html = "";
                for(var i in data.data){
                    var length = data.data[i].length;
                    var imageList = JSON.stringify(data.data[i]);
                    html+="<li data-imageList = "+imageList+"><span class='MenuBuWei'>"+i+" </span><span>"+length+"</span>"+
                        "</li>"
                }
                $(".xqMenuLeft ul").html(html);
                $(".xqMenuLeft li").eq(0).click();
            }
        },
        error:function (err) {
            console.log(err)
        }
    });

}
// 点击部位显示对应的图片
$(document).on("click",".xqMenuLeft li",function () {
    $(this).css({background:'rgba(94,176,215,0.7)'}).siblings().css({background:'rgba(94,176,215,0.1)'})
var imgList = $(this).attr("data-imageList");
var parseImage = JSON.parse(imgList);
var bigImage='';
  if(parseImage.length>0){
      $.each(parseImage,function (i,n) {
          bigImage += "<div class=\"swiper-slide\"><img src="+n+" alt='' class='' /><div class='gdshShuYin'style='padding-top:10px;'></div></div>";
      });
  }else{
      bigImage += " <div class=\"swiper-slide\"><img src='" + ctx + "/static/images/zwtp.jpg'  style=\"margin-top:130px\" alt=''/></div>";
  }

$(".check_imageList").html(bigImage);
    // swiper轮播样式
    var itemCheck = new Swiper({
        el: '.device_check_swiper',
        paginationClickable: true,
        slideToClickedSlide: true,
        mousewheel: {
            enabled: false,
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        }
    });
});

$MB.initTable('device_edit_table', settings);
initSelectYear();


