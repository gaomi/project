var editType = false;//false表示modal是添加，true表示编辑
var editRow = '';//被编辑的对象。
var baseInfo = '';//线路，站、设备。
var stationData = '';
/*头部菜单点击*/
$(document).on('click', '.paoding_menu_button', function () {
    var url = $(this).data("url");
    if (url === "/visual" || url === "/map/index" || url === "/gis") {
        window.open(ctx + url, '_blank');
        $(this).css("background","#3c8dbc");
        $(this).hover(function(){
            $(this).css("background","rgba(0, 0, 0, 0.1)");
        },function(){
            $(this).css("background","#3c8dbc");
        });
    } else {
        window.location.href = ctx + url;
        //重新加载左侧菜单
        //loadSideMenu(url);
        //loadContent(module, url)
    }
    return false;
});


function getStationInspect() {
    $.ajax({
        type: "post",
        url: ctx + "/module/data/base/getStationInfo",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({}),
        async: false,
        success: function (data) {
            if (data.code === 200) {
                baseData = data.data;
                baseData.stations.sort(sortLine);
                $.each(baseData.stations, function (i, r) {
                    r['id'] = r.LINE_UUID;
                    r['name'] = r.LINE_NAME;
                });
                stationData = baseData;
            }
        }
    });
}
//对象排序
function sortLine(a, b) {
    return parseInt(a.LINE_NAME.substr(a.length - 1, 2)) - parseInt(b.LINE_NAME.substr(a.length - 1, 2));
}


/***
 * 去除头部菜单选中状态
 */
function cancelNav(){
    $("#main_head_menu .header-menu.headActive").removeClass("headActive");
}
/***
 * 初始化头部菜单
 */
function initHeadMenu(index){
    $.ajax({
        type: "GET",
        url: ctx + "/module/sys/menus/getHeadMenu",
        dataType:'json',
        success: function (data) {
            if (data.code == '200') {
                // $("#main_head_menu").empty();
                var html = "";
                $.each(data.data,function(i,n){
                    if(index == n.URL){
                        html+='<li class="header-menu headActive">';
                    }else{
                        html+='<li class="header-menu">';
                    }
                    html+='<a class="dropdown-toggle paoding_menu_button" href="javascript:void(0);" '+
                        ' data-url="'+n.URL+'">' +n.NAME+'</a></li>';
                });
                $("#main_head_menu").prepend(html);
            }else{
                $MB.n_warning("获取头部菜单失败");
            }
        }
    });
}

/***
 * 初始化左侧菜单
 */
function initAsideMenu(index){
    $.ajax({
        type: "POST",
        url: ctx + "/module/sys/menus/getAsideMenu",
        data:JSON.stringify({params:{path:index}}),
        dataType:'json',
        contentType:'application/json',
        success: function (data) {
            if (data.code == '200') {
                if(data.data.length<1){
                    $MB.n_info("无可以访问的菜单！");
                }
                $("ul.sidebar-menu").empty();
                var html ='<li class="header">'+data.data[0].par_name+'</li>';
                $.each(data.data,function(i,n){
                    try{
                        if(n.item.length>0){
                            html+='<li class="treeview"><a href="javascript:void(0);" '+
                                'data-module="'+n.url+'"><i class="fa fa-link"></i> <span>'+n.name+'</span>' +
                                '<span class="pull-right-container">' +
                                '<i class="fa fa-angle-left pull-right"></i>' +
                                '</span></a><ul class="treeview-menu">';
                            $.each(n.item,function(j,m){
                                html+='<li><a href="javascript:void(0);" data-module="'+m.url.substring(0,m.url.lastIndexOf('/'))+'" data-url="'+m.url.substring(m.url.lastIndexOf('/'))+'">'+m.name+'</a></li>';
                            });
                            html+='</ul></li>';
                        }else{
                            html+='<li><a href="javascript:void(0);" data-module="'+n.url.substring(0,n.url.lastIndexOf('/'))+'" data-url="'+n.url.substring(n.url.lastIndexOf('/'))+'"><i class="fa fa-link"></i> <span>'+n.name+'</span></a></li>';
                        }
                    }catch (e) {
                        console.log("遍历左侧菜单异常");
                    }

                });
                $("ul.sidebar-menu").append(html);
                moduleInit();
            }else{
                $MB.n_warning("获取左侧子菜单失败");
            }
        }
    });
}

/*页面内容更换*/
$(document).on('click', '.sidebar-menu li', function () {
    if (!$(this).hasClass('treeview')) {
        $('.sidebar-menu li').removeClass('active');
        $(this).addClass('active');
        var a = $(this).find("a:first");
        var content_url = a.data('url');
        var content_module = a.data('module');
        loadContent(content_module, content_url);
        if ($(this).parent().hasClass('treeview-menu')) {
            $(this).parent().parent().addClass('active');
            return false;
        } else {
            $(this).siblings().each(function (i, r) {
                    if ($(r).hasClass('menu-open')) {
                        $(r).find('ul.treeview-menu').hide();
                        $(r).removeClass('menu-open');

                    }
                }
            )
        }
    }
    return false;
});

/*头部菜单选中*/
function activeHeadMenu(type) {
    var key = type;
    $('.paoding_menu_button').each(function (i, r) {
        var $r = $(r);
        if ($r.data('url') === key) {
            $r.parent().siblings().removeClass('headActive');
            $r.parent().addClass('headActive');
        }
    });
}

function modalControl() {
    $('#commonModal').on('shown.bs.modal', function (e) {
        // //获取modal的宽度
        // var modalWidth = $("#commonModal").width(window.innerWidth);
        // //计算偏移量
        // debugger;
        // var left = "-" + parseInt(modalWidth) / 2 + "px";
        // //modal居中
        // $("#commonModal").css({"margin-left": left});
        // $(this).css('display', 'block');
        // var modalWidth = $(window).width() / 2 - $('#commonModal .modal-dialog').width() / 2;
        // $(this).find('.modal-dialog').css({
        //     'width': modalWidth
        // });


    });
    $('#commonModal').on('show.bs.modal', function (e) {

    });
    $('#commonModal').on('hide.bs.modal', function (e) {
        $(e.target).removeData("bs.modal").find(".modal-content").empty();
        //TODO 把变量置空
        editType = false;
        editRow = '';
    });
    $('#commonModal').on('hidden.bs.modal', function (e) {
        $(e.target).removeData("bs.modal").find(".modal-content").empty();
        editType = false;
        editRow = '';
    });
}

/**
 * 关闭modal
 */
function closeModal() {
    $MB.closeAndRestModal("commonModal");
}

/*左侧菜单内容渲染*/
function loadSideMenu(url) {
    $.ajax({
        type: "GET",
        url: ctx + "/module" + url + "/sideMenu",
        success: function (data) {
            if (data.code) {
                //TODO 返回400是提示错误。
                // $("#errorMsg").html(data.message);
                // $("#errorMsg").css("display", "block");
            } else {
                $('ul.sidebar-menu').empty();
                $('ul.sidebar-menu').html(data);
            }
        }
    });
}
function getFromParam(id){
    var param = new Object();
    var fromData = $("#"+id+" [name]");
    $.each(fromData,function(i,n){
        var value = $.trim($(n).val());
        if(value.length > 0){
            var key = $(n).attr("name");
            param[key] = value;
        }
    });
    return param;
}

/*页面内容更换*/
function moduleInit() {
    /*左侧菜单展开,如果小于50就展开*/
    if ($(".main-sidebar").width() <= 50) {
        $("#header-sidebar-toggle").click();
    }
    //找到菜单的一个链接，触发点击事件
    var firstMenu = $('ul.sidebar-menu').children('li').get(1);
    firstMenu.click();
}

/*加载页面*/
function loadContent(module, url) {
    $.ajax({
        type: "GET",
        url: ctx + "/module" + module + url,
        success: function (data) {
            if (data.code) {
                //TODO 返回400是提示错误。
                // $("#errorMsg").html(data.message);
                // $("#errorMsg").css("display", "block");
            } else {
                $(".content-wrapper").empty();
                $(".content-wrapper").html(data);
            }
        },
        error: function (data) {
            console.log("404", data);
            $MB.n_danger("页面加载错误");
        }
    });
}

/**
 * 加载表格
 * @param row
 * @param module
 */
function loadForm(row, module) {
    var split = module.split("_");
    if (row.objId !== -1) {
        $.ajax({
            url: ctx + '/module/' + split[0] + '/' + split[1] + '/detail',
            data: {id: row.objId},
            type: 'POST',
            dataType: 'json',
            async:false,
            success: function (response) {
                if (response.code === 200) {
                    $('#' + split[0] + '_' + split[1] + '_edit_form').setForm(response.data);
                    editRow = response.data;
                } else {
                    $MB.n_danger("数据加载失败");
                }
            }
        });
    } else {
        $('#' + split[0] + '_' + split[1] + '_edit_form').setForm(row);
    }

}

function openEditPage(module, type, clickBtn) {
    var sizeSplit = $(clickBtn).attr("paoding-modal-size").split("_");//宽高
    var split = module.split("_");//模块
    var urlKey = '';
    if ('add' === type) {
        editType = false;
        editRow = '';
        urlKey = 'Edit';//如果是添加，就用edit页面。
    } else {
        editType = true;
        urlKey = $(clickBtn).attr("paoding_opt");
    }
    //设置大小。
    $("#commonModal").find(".modal-dialog").css({"width": sizeSplit[0] + 'px',"height": sizeSplit[1] + 'px'});

    $("#commonModal").modal({
        remote: ctx + '/module/' + split[0] + '/' + split[1] + '/to' + urlKey,
        keyboard: true//当按下esc键时，modal框消失
    });
    /*$('#commonModalBody').modal().css({
        'margin-top': function () {
            debugger;
            return ($(this).height() * 0.1);
        },
        'margin-left': function () {
            debugger;
            return ($(this).width() * 0.1);
        }
    });*/


}

//日期格式化显示
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    var D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' ';
    var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
    var m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
    var s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
    return Y + M + D + h + m + s;
}

/*注销*/
function logout() {
    $.ajax({
        type: "get",
        url: ctx + "/logout",
        success: function (data) {
            window.location.href = ctx + "/login";
        }
    });
}


/**
 * 表单自动填充
 * @param jsonValue
 */
$.fn.setForm = function (jsonValue) {
    var obj = this;
    $.each(jsonValue, function (name, ival) {
        var $oinput = obj.find("input[name='" + name + "']");
        if ($oinput.attr("type") == "radio" || $oinput.attr("type") == "checkbox") {
            $oinput.each(function () {
                if (Object.prototype.toString.apply(ival) == '[object Array]') {//是复选框，并且是数组
                    for (var i = 0; i < ival.length; i++) {
                        if ($(this).val() == ival[i])
                            $(this).attr("checked", "checked");
                    }
                } else {
                    if ($(this).val() == ival)
                        $(this).attr("checked", "checked");
                }
            });
        } else if ($oinput.attr("type") == "textarea") {//多行文本框
            obj.find("[name='" + name + "']").html(ival);
        } else {
            obj.find("[name='" + name + "']").val(ival);
        }
    });
};
/**
 * 转为json数据格式
 * @param formData
 * @param id
 * @returns {*}
 */
$.fn.transformToJson = function () {
    var formData = this.serializeArray();
    //将json数组转为json 对象
    var obj = {};
    for (var i in formData) {
        //下标为的i的name做为json对象的key，下标为的i的value做为json对象的value
        obj[formData[i].name] = formData[i]['value'] !== "" ? formData[i]['value'] : null;
    }
    return obj;
};

/**
 * 选择框change
 * @param data
 * @param id
 * @param obj
 */
function selectOnChange(data, id, obj, editForm) {
    var sChange = $(obj).find(':selected').val();
    loadSelectData(data, sChange, id, editForm);
    $(this).find('option').each(function () {
        if ($(this).text() == sChange) {
            $(this).attr("selected", true);
        }
    })
};

/**
 * 当select被改变时，填充select选项
 * @param datum
 * @param id
 */
function loadSelectData(data, type, id, editForm) {
    var datum = data[type];
    selectExtracted(datum, type, id, editForm);
};

/**
 * 填充select选项
 * @param datum
 * @param id
 */
function selectExtracted(datum, type, id, editForm) {
    var selecthtml = "<option value=''>---请选择---</option>";
    $.each(datum, function (i, r) {
        selecthtml += "<option value=" + r.id + " data-pid=" + type + ">" + r.name + "</option>"
    });
    $('select[id="' + id + 'Type_' + editForm + '_select"]').empty();
    $('select[id="' + id + 'Type_' + editForm + '_select"]').html(selecthtml);
};


function postJsonAjax(settings) {
    $.ajax({
        url: settings.url,
        data: settings.data,
        type: settings.type ? settings.type : 'POST',
        dataType: 'json',
        contentType: settings.contentType ? settings.contentType : "application/x-www-form-urlencoded;charset=UTF-8",
        async: settings.async ? settings.async : true,
        success: settings.successFun,
        error: function (res) {
            $MB.n_danger(res.message);
        }
    });
}
$(document).on("keydown","form",function (e) {
    if(e.keyCode==13){
       return false
    }
});
//收起菜单
function packUpMenu(thas,id) {
    if($("#"+id).hasClass("device_change_height")){
        $("#"+id).removeClass("device_change_height");
        $(thas).text("收起菜单");
    }else {
        $("#"+id).addClass("device_change_height");
        $(thas).text("展开菜单")
    }
}