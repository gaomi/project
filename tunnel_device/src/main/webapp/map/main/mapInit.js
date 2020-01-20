var g_Map;
if (void 0 === G_Dci_Info) var G_Dci_Info = {};
var gLyr;
var gLyrLbl;
/*
dojo.addOnLoad(function () {
    //setTimeout("CreateMap",5000);
    CreateMap()
});
dynamicLoadingFile.js(["widget/scalebar/scalebarToolBar.js"
    , "widget/navigation/navigationToolBar.js"
    , "widget/baseMap/baseMapToolBar.js"
    , "widget/measure/measureToolBar.js"
    , "widget/layerList/layerListToolBar.js"
    , "widget/legend/legendToolBar.js"
    , "widget/geoQuery/geoQueryToolBar.js"]);
*/
(function () {

    dynamicLoadingFile.js(["widget/scalebar/scalebarToolBar.js"
        , "widget/navigation/navigationToolBar.js"
        , "widget/baseMap/baseMapToolBar.js"
        , "widget/measure/measureToolBar.js"
        , "widget/layerList/layerListToolBar.js"
        , "widget/legend/legendToolBar.js"
        , "widget/geoQuery/geoQueryToolBar.js"], function () {
        dojo.addOnLoad(function () {
            CreateMap();

        });
    });
})();

function CreateMap() {
    var e = new esri.Map("map", {logo: !1, slider: !1});
    g_Map = e,
        require(["esri/urlUtils"], function (urlUtils) {
            urlUtils.addProxyRule({
                urlPrefix: td_LayerMapsUrls,
                // proxyUrl: "http://218.242.68.246/tunnel_device/proxy.jsp"
                //proxyUrl: td_ctx + "/gis/proxy"
                proxyUrl: proxy_ctx + "/proxy.jsp"
                // proxyUrl: proxy_ctx + "/proxy"
                //proxyUrl: "http://10.0.112.71/tunnel_device/proxy.jsp"
            });
        });

    G_Dci_Info.MapManager.initMapManager(e), G_Dci_Info.LayerListToolBar.initLayerList(e);
    //g_Map = e, MapConfig.httpProxy && MapConfig.httpProxy.useProxy && (esri.config.defaults.io.proxyUrl = MapConfig.httpProxy.url, esri.config.defaults.io.alwaysUseProxy = MapConfig.httpProxy.useProxy), G_Dci_Info.MapManager.initMapManager(e), G_Dci_Info.LayerListToolBar.initLayerList(e);
    var o = new esri.geometry.Extent({
        xmin: MapConfig.mapInitParams.extent.xmin,
        ymin: MapConfig.mapInitParams.extent.ymin,
        xmax: MapConfig.mapInitParams.extent.xmax,
        ymax: MapConfig.mapInitParams.extent.ymax,
        spatialReference: MapConfig.mapInitParams.spatialReference
    });
    e.setExtent(o), this.mapInitBasicTool(e, o), e.on("load", function () {
        $("#divMsg").hide(), $("#divMsg_shade").hide(), G_Dci_Info.legendToolBar.initLegend(e)
    })

    gLyr = new esri.layers.GraphicsLayer({"id": "gLyr"});
    e.addLayer(gLyr);
    gLyrLbl = new esri.layers.GraphicsLayer({"id": "gLyrLbl"});
    e.addLayer(gLyrLbl);
}

function mapInitBasicTool(e, o) {
    G_Dci_Info.scalebarToolBar.initScalebar(e), showSlider(o, MapConfig.sliderConfig, e);
    new BaseMapToolBar(MapConfig.baseMaps, e);
    G_Dci_Info.MapToolManager.InitTool(e), G_Dci_Info.GeoQueryToolBar.initGeoQuery(e)
}

function showSlider(e, o, n) {
    o.targetId = "map";
    var i = new MapNavigationToolbar(o), a = n;
    return i.onMoveUp = function () {
        a.panUp()
    }, i.onMoveDown = function () {
        a.panDown()
    }, i.onMoveLeft = function () {
        a.panLeft()
    }, i.onMoveRight = function () {
        a.panRight()
    }, i.onFullMap = function () {
        a.setExtent(e)
    }, i.onZoomIn = function () {
        a.setLevel(i.getValue())
    }, i.onZoomOut = function () {
        a.setLevel(i.getValue())
    }, i.onSliderEnd = function () {
        a.setLevel(i.getValue())
    }, i.onMark_Street = function () {
        a.setLevel(o.marksShow.streetLevel)
    }, i.onMark_City = function () {
        a.setLevel(o.marksShow.cityLevel)
    }, i.onMark_Province = function () {
        a.setLevel(o.marksShow.provinceLevel)
    }, i.onMark_Country = function () {
        a.setLevel(o.marksShow.countryLevel)
    }, i.create(), dojo.connect(a, "onZoomEnd", function (e, o, n, a) {
        i.setValue(a)
    }), i
}

/**
 * 撒点
 * @param data
 */

function markPoi(data) {
    gLyr.clear();
    gLyrLbl.clear();
    var _data = data;
    for (var i = 0; i < _data.length; i++) {
        var pt = new esri.geometry.Point(_data[i].x, _data[i].y, map.spatialReference);
        var pms;
        var imgurl = ctx + '/static/images/marker/mark';
        if (_data[i].LEVEL == '特级' || _data[i].PROJECTLEVEL == '特级') {
            pms = new esri.symbol.PictureMarkerSymbol(imgurl + "-te.png", 30, 30);
        } else if (_data[i].LEVEL == '一级' || _data[i].PROJECTLEVEL == '一级') {
            pms = new esri.symbol.PictureMarkerSymbol(imgurl + "-01.png", 30, 30);
        } else if (_data[i].LEVEL == '二级' || _data[i].PROJECTLEVEL == '二级') {
            pms = new esri.symbol.PictureMarkerSymbol(imgurl + "-02.png", 30, 30);
        } else if (_data[i].LEVEL == '三级' || _data[i].PROJECTLEVEL == '三级') {
            pms = new esri.symbol.PictureMarkerSymbol(imgurl + "-03.png", 30, 30);
        } else {
            pms = new esri.symbol.PictureMarkerSymbol(imgurl + "_red.png", 30, 30);
        }
        var gImg = new esri.Graphic(pt, pms, _data[i]);
        gLyr.add(gImg);
        var gLbl = new esri.Graphic(pt, _data[i]);
        gLyrLbl.add(gLbl);
    }
    gLyr.on("click", function (e) {
        var attr = e.graphic.attributes;
        showInfo(attr);
    });

}

/**
 * 弹窗
 * @param attr
 */
function showInfo(attr) {
    var pt = new esri.geometry.Point(attr.x, attr.y, map.spatialReference);
    var _content = "";
    if (attr.SUMMARY) {
        _content += "状态：" + attr.STATE + "<br/>建筑公司：" + attr.COMPANY + "<br/>等级：" + attr.LEVEL + "<br/>内容：" + attr.SUMMARY;
    } else {
        _content += "地址：" + attr.PROJECTADDRESS + "<br/>状态：" + attr.PROJTASKLASTRESULTSTATUS + "<br/>公司：" + (attr.PROJECTBUILDUNIT ? attr.PROJECTBUILDUNIT : '') + "<br/>等级：" + attr.PROJECTLEVEL + "<br/>建筑单位：" + (attr.PROJECTCONSTRUCTIONUNIT ? attr.PROJECTCONSTRUCTIONUNIT : '') + "<br/>距离范围：" + attr.PROJECTLOCATIONWITHMETRO;
    }
    g_Map.infoWindow.setTitle(attr.NAME ? attr.NAME : attr.PROJECTNAME);
    g_Map.infoWindow.setContent(_content);
    g_Map.infoWindow.show(pt);
}
