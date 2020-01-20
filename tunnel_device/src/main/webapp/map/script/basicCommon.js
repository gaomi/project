/*
*加载文件
 */
/*
var dynamicLoadingFile = {
    css: function (pathList) {
        if (pathList && pathList.length > 0) {
            pathList.forEach(function (path, i) {
                if (path.length > 0) {
                    var head = document.getElementsByTagName('head')[0];
                    var link = document.createElement('link');
                    link.href = path;
                    link.rel = 'stylesheet';
                    link.type = 'text/css';
                    head.appendChild(link);
                }
            });
        }
    },
    js: function (pathList) {
        if (pathList && pathList.length > 0) {
            pathList.forEach(function (path, i) {
                if (path.length > 0) {
                    var head = document.getElementsByTagName('head')[0];
                    var script = document.createElement('script');
                    script.src = path;
                    script.type = 'text/javascript';
                    head.appendChild(script);
                }
            });
        }
    }
}
*/
var dynamicLoadingFile = {
    css: function (pathList) {
        if (pathList && pathList.length > 0) {
            pathList.forEach(function (path, i) {
                if (path.length > 0) {
                    var head = document.getElementsByTagName('head')[0];
                    var link = document.createElement('link');
                    link.href = path;
                    link.rel = 'stylesheet';
                    link.type = 'text/css';
                    head.appendChild(link);
                }
            });
        }
    },
    js: function (pathList, callback) {
        if (pathList && pathList.length > 0) {
            var loadJsCount = 0;
            pathList.forEach(function (path, i) {
                if (path.length > 0) {
                    // var head = document.getElementsByTagName('head')[0];
                    // var script = document.createElement('script');
                    // script.src = path;
                    // script.type = 'text/javascript';
                    // head.appendChild(script);
                    // if (typeof (callback) != "undefined") {
                    //     script.onload = function () {
                    //         loadJsCount = loadJsCount + 1;
                    //         if (loadJsCount === pathList.length) {
                    //             callback();
                    //         }
                    //     }
                    // }
                    $.getScript(path, function () {
                        loadJsCount = loadJsCount + 1;
                        console.log(path + '... load complete!');
                        if (loadJsCount === pathList.length) {
                            callback();
                        }
                    });
                }
            });
        }
    }
}

/**
 * 短暂提示
 * msg: 显示消息
 * type：类型 0：警告，1：成功，2：失败，3：问号
 **/
function tipDialog(msg, type, isShade) {
    if (isShade) {
        layer.msg(msg, {
            icon: type, time: 2000, shade: [0.5, '#fff']
        });
    } else {
        layer.msg(msg, {icon: type, time: 2000});
    }
}

/**
 *显示操作的进度条
 * msg: 显示消息
 **/
function showOperProDiv (msg) {
    $("#divMsg span").html(msg);
    $("#divMsg").show();
    $("#divMsg_shade").show();
}

function hideOperProDiv () {
    $("#divMsg").hide();
    $("#divMsg_shade").hide();
}

$('#divMsg').click(function() {
    $("#divMsg").hide();
    $("#divMsg_shade").hide();
})

/**
 *存储共享数据
 * name: 共享名称
 * value: 共享数据
 **/
function saveShareData(name, value) {
    sessionStorage.setItem(name, value);
}

/**
 *获取共享数据
 * name: 共享名称
 **/
function getShareData(name) {
    return sessionStorage.getItem(name);
}

function disntinctArr(arr) {
    var res = arr.filter(function (item, index, array) {
        return arr.indexOf(item) === index;
    });
    return res;
}

/**
 * js获取项目根路径，如： http://localhost/GGFW/
 */
function getRootPath() {
    //获取当前网址，如： http://localhost/GGFW/
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名
    var projectName = pathName.substring(0, pathName.substr(1).lastIndexOf('/') + 1);

    return (localhostPaht + projectName + "/");
}

DUtil = {};
DUtil.extend = function (destination, source) {
    destination = destination || {};
    if (source) {
        for (var property in source) {
            var value = source[property];
            if (value !== undefined) {
                destination[property] = value;
            }
        }
        var sourceIsEvt = typeof window.Event == "function" && source instanceof window.Event;
        if (!sourceIsEvt && source.hasOwnProperty && source.hasOwnProperty('toString')) {
            destination.toString = source.toString;
        }
    }
    return destination;
};

DObject = function () {
    var Class = function () {
        if (arguments && arguments[0] != null) {
            this.construct.apply(this, arguments);
        }
    };
    var extended = {};
    var parent;
    for (var i = 0, len = arguments.length; i < len; ++i) {
        if (typeof arguments[i] == "function") {
            parent = arguments[i].prototype;
        } else {
            parent = arguments[i];
        }
        DUtil.extend(extended, parent);
    }
    Class.prototype = extended;
    return Class;
};

function StringBuffer() {
    this.__strings__ = new Array();
}

StringBuffer.prototype.append = function (str) {
    this.__strings__.push(str);
    return this;
}

StringBuffer.prototype.toString = function () {
    return this.__strings__.join("");
}

/**
 *获取数据
 **/
function getAjax(url, dataType, postData, callBack) {
    $.ajax({
        type: 'post',
        dataType: dataType,
        url: url,
        data: postData,
        cache: false,
        async: false,
        success: function (data) {
            callBack(data);
        },
        error: function (data) {
            console.log("error:" + JSON.stringify(data));
        }
    });
}

/**
 *获取数据
 **/
function AjaxJson(url, dataType, postData, callBack) {
    $.ajax({
        url: url,
        type: "post",
        data: postData,
        dataType: dataType,
        async: false,
        success: function (data) {
            if (data.Code == "-1") {

            } else {
                callBack(data);
            }
        },
        error: function (data) {
            console.log("error:" + JSON.stringify(data));
        }
    });
}