/*
* 地图初始信息配置
*/
function MapConfig() {
}

var td_baseMapUrls = "http://map.smi.sh.cegn.cn";
var td_LayerMapsUrls = "http://10.0.112.72:6080";
//var td_ctx = "http://localhost/tunnel_device";
//var proxy_ctx = "http://218.242.68.246/tunnel_api";
var proxy_ctx = ctx;
//var proxy_ctx = "http://10.0.112.71/tunnel_api";
//var proxy_ctx = "http://metro.sigs.cn/tunnel_api";

MapConfig.mapInitParams = {
    fullExtent: {
        xmin: -58645.319129811105,
        ymin: -61351.654921693116,
        xmax: 48416.20621789899,
        ymax: 69950.04657659109
    },
    extent: {
        xmin: -100251.300935,
        ymin: -91459.1682,
        xmax: 115350.862235,
        ymax: 90581.752199
    },
    spatialReference: {
        wkt: "PROJCS[\"shanghaicity\",GEOGCS[\"GCS_Beijing_1954\",DATUM[\"D_Beijing_1954\",SPHEROID[\"Krasovsky_1940\",6378245.0,298.3]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",-3457147.81],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",121.2751921],PARAMETER[\"Scale_Factor\",1.0],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Meter\",1.0]]"
    },
}

/*导航条配置参数*/
MapConfig.sliderConfig = {
    targetId: "mapDiv",
    minValue: 0,
    maxValue: 9,
    startValue: 0,
    toolbarCss: ["toolBar", "toolBar_button", "toolBar_slider", "toolBar_mark"],
    marksShow: {
        countryLevel: null,
        provinceLevel: null,
        cityLevel: null,
        streetLevel: null
    }
};

/*地图配置*/
/*MapConfig.baseMaps = [
    {
        id: "shMapDC",
        label: "base",
        // url: td_baseMapUrls+"/OneMapServer/rest/services/BaseMap/MapServer",
        url: "http://mape.shanghai-map.net/ArcGIS/rest/services/SHMAP_DC/MapServer",
        type: "tiled",
        icon: "images/widget/basemaps/baidumap.png",
        visible: true,
        layerFlag: ''
    },
    {
        id: "shMapImagery",
        label: "image",
        // url: td_baseMapUrls+"/OneMapServer/rest/services/Air2018/MapServer",
        url: "http://mape.shanghai-map.net/ArcGIS/rest/services/SHMAP_DC/MapServer",
        type: "tiled",
        icon: "images/widget/basemaps/googleimage.png",
        visible: false,
        layerFlag: ''
    },
    {
        id: "shMapDcSs",
        label: "bases",
        url: "http://mape.shanghai-map.net/ArcGIS/rest/services/SHMAP_DC/MapServer",
        // url: td_baseMapUrls+"/OneMapServer/rest/services/shmap_ss/MapServer",
        type: "tiled",
        icon: "images/widget/basemaps/sogoumap.png",
        visible: false,
        layerFlag: ''
    }
];*/
var tokenInfo = '';
getAjax(td_baseMapUrls + "/RemoteTokenServer", 'text', {
    request: "getToken",
    username: "smiuser",
    password: "11111111",
    clientid: "10.2.128.246",
    expiration: "1440"
}, function (data) {
    tokenInfo = $.trim(data)
});
/*地图配置*/
MapConfig.baseMaps = [
    {
        id: "shMapDC",
        label: "base",
        url: td_baseMapUrls + "/OneMapServer/rest/services/BaseMap/MapServer?token=" + tokenInfo,
        type: "tiled",
        icon: "images/widget/basemaps/baidumap.png",
        visible: true,
        layerFlag: ''
    },
    {
        id: "shMapImagery",
        label: "image",
        url: td_baseMapUrls + "/OneMapServer/rest/services/Air2018/MapServer?token=" + tokenInfo,
        type: "tiled",
        icon: "images/widget/basemaps/googleimage.png",
        visible: false,
        layerFlag: ''
    },
    {
        id: "shMapDcSs",
        label: "bases",
        url: td_baseMapUrls + "/OneMapServer/rest/services/shmap_ss/MapServer?token=" + tokenInfo,
        type: "tiled",
        icon: "images/widget/basemaps/sogoumap.png",
        visible: false,
        layerFlag: ''
    }
];
MapConfig.geometryService = "http://www.sigs.com.cn/arcgis/rest/services/Utilities/Geometry/GeometryServer";

MapConfig.divShowToolList = ['divLayerList', 'divLegend'];

MapConfig.httpProxy = {
    useProxy: true,
    // url: "http://localhost:8080/gis-proxy/proxy.jsp"
    //url: "http://localhost:8080/proxy"
    //url: "http://localhost:8080/proxy.jsp"
    //url: "http://localhost:8080/gis-proxy/proxy.jsp"
    //url: "http://localhost:8080/proxy-java/proxy.jsp"
    //url: "http://localhost:8080/p-java/proxy.jsp"
    //url: "http://192.168.12.61/gisProxy/proxy.ashx"
    //url: "http://218.242.68.246/tunnel_device/proxy.jsp"
    // url: "http://218.242.68.246/tunnel_device/gis/proxy"
    //url: td_ctx + "/gis/proxy"
    url: proxy_ctx + "/proxy.jsp"
    //url: proxy_ctx + "/proxy.jsp"
    //url: "http://10.0.112.71/tunnel_device/proxy.jsp"
};

/*图层目录*/
MapConfig.layerListCol = [
    {
        id: "01",
        parentId: "0",
        lable: "业务数据",
        visible: false,
        url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/YWData/MapServer",
        type: "dynamic",
        iconType: "Server",
        layerFlag: "",
        childNodeCol: [
            {
                id: "01_01",
                parentId: "01",
                lable: "水准点",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/YWData/MapServer/0",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "point"
            },
            {
                id: "01_02",
                parentId: "01",
                lable: "深标点",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/YWData/MapServer/1",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "point"
            },
            {
                id: "01_03",
                parentId: "01",
                lable: "分层标",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/YWData/MapServer/2",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "point"
            },
            {
                id: "01_04",
                parentId: "01",
                lable: "地质钻孔",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/YWData/MapServer/3",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "point"
            },
            {
                id: "01_05",
                parentId: "01",
                lable: "结构检修",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/YWData/MapServer/4",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "point"
            },
            {
                id: "01_06",
                parentId: "01",
                lable: "基岩标",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/YWData/MapServer/5",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "point"
            },
            {
                id: "01_07",
                parentId: "01",
                lable: "地铁一号线管片",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/YWData/MapServer/6",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "point"
            },
            {
                id: "01_07",
                parentId: "01",
                lable: "地铁二号线管片",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/YWData/MapServer/7",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "point"
            }
        ]
    },
    {
        id: "02",
        parentId: "0",
        lable: "沉降监测点",
        visible: false,
        url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/BTLinesPoint/MapServer",
        type: "dynamic",
        iconType: "Server",
        layerFlag: "",
        childNodeCol: [
            {
                id: "02_01",
                parentId: "02",
                lable: "地铁沉降监测点",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/BTLinesPoint/MapServer/0",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "point"
            }
        ]
    },
    {
        id: "03",
        parentId: "0",
        lable: "隧道收敛环",
        visible: false,
        url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/SDSLH/MapServer",
        type: "dynamic",
        iconType: "Server",
        layerFlag: "",
        childNodeCol: [
            {
                id: "03_01",
                parentId: "03",
                lable: "新闸路-人民广场(上行)",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/BTLinesPoint/MapServer/0",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "point"
            },
            {
                id: "03_02",
                parentId: "03",
                lable: "新闸路-人民广场(下行)",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/BTLinesPoint/MapServer/1",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "point"
            },
            {
                id: "03_03",
                parentId: "03",
                lable: "人民广场-黄陂南路(下行)",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/BTLinesPoint/MapServer/2",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "point"
            },
            {
                id: "03_04",
                parentId: "03",
                lable: "人民广场-黄陂南路(上行)",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/BTLinesPoint/MapServer/3",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "point"
            }
        ]
    },
    {
        id: "04",
        parentId: "0",
        lable: "地铁线路",
        visible: false,
        url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/BTLines/MapServer",
        type: "dynamic",
        iconType: "Server",
        layerFlag: "",
        childNodeCol: [
            {
                id: "04_01",
                parentId: "04",
                lable: "地铁中心线",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/BTLines/MapServer/0",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "line"
            },
            {
                id: "04_01",
                parentId: "04",
                lable: "车站内轮廓线",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/BTLines/MapServer/1",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "line"
            },
            {
                id: "04_02",
                parentId: "04",
                lable: "矩形内轮廓线",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/BTLines/MapServer/2",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "line"
            },
            {
                id: "04_03",
                parentId: "04",
                lable: "矩形外轮廓线",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/BTLines/MapServer/3",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "line"
            },
            {
                id: "04_04",
                parentId: "04",
                lable: "保护区线",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/BTLines/MapServer/4",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "line"
            },
            {
                id: "04_05",
                parentId: "04",
                lable: "外轮廓线",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/BTLines/MapServer/5",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "line"
            },
            {
                id: "04_06",
                parentId: "04",
                lable: "旁通道",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/BTLines/MapServer/6",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "04_07",
                parentId: "04",
                lable: "车站",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/BTLines/MapServer/7",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            }
        ]
    },
    {
        id: "05",
        parentId: "0",
        lable: "地下空间",
        visible: false,
        url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DXKJ/MapServer",
        type: "dynamic",
        iconType: "Server",
        layerFlag: "",
        childNodeCol: [
            {
                id: "05_01",
                parentId: "05",
                lable: "内部立柱",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DXKJ/MapServer/0",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "point"
            },
            {
                id: "05_02",
                parentId: "05",
                lable: "内部楼梯",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DXKJ/MapServer/1",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "line"
            },
            {
                id: "05_03",
                parentId: "05",
                lable: "内部墙体",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DXKJ/MapServer/2",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "line"
            },
            {
                id: "05_04",
                parentId: "05",
                lable: "内部斜面",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DXKJ/MapServer/3",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "line"
            },
            {
                id: "05_05",
                parentId: "05",
                lable: "行车道",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DXKJ/MapServer/4",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "line"
            },
            {
                id: "05_06",
                parentId: "05",
                lable: "桩基范围",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DXKJ/MapServer/5",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "05_07",
                parentId: "05",
                lable: "出入口",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DXKJ/MapServer/6",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "05_08",
                parentId: "05",
                lable: "集水坑",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DXKJ/MapServer/7",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "05_09",
                parentId: "05",
                lable: "内部电梯井",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DXKJ/MapServer/8",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "05_10",
                parentId: "05",
                lable: "采光口",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DXKJ/MapServer/9",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "05_11",
                parentId: "05",
                lable: "外墙体",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DXKJ/MapServer/10",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "05_12",
                parentId: "05",
                lable: "通风口",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DXKJ/MapServer/11",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "05_13",
                parentId: "05",
                lable: "地下宗地",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DXKJ/MapServer/12",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            }
        ]
    },
    {
        id: "06",
        parentId: "0",
        lable: "地面沉降等值线图",
        visible: false,
        url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer",
        type: "dynamic",
        iconType: "Server",
        layerFlag: "",
        childNodeCol: [
            {
                id: "06_01",
                parentId: "06",
                lable: "2017中心城区沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/0",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_02",
                parentId: "06",
                lable: "2012中心城区沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/1",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_03",
                parentId: "06",
                lable: "2011中心城区沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/2",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_04",
                parentId: "06",
                lable: "2010中心城区沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/3",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_05",
                parentId: "06",
                lable: "2012~2016全市累计沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/4",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_06",
                parentId: "06",
                lable: "2006~2011全市累计沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/5",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_07",
                parentId: "06",
                lable: "2001~2006全市累计沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/6",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_18",
                parentId: "06",
                lable: "2001~2005全市累计沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/17",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_08",
                parentId: "06",
                lable: "1995~2001全市累计沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/7",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_20",
                parentId: "06",
                lable: "1996~2000全市累计沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/19",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_21",
                parentId: "06",
                lable: "1991~1995全市累计沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/20",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_09",
                parentId: "06",
                lable: "1980~1995全市累计沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/8",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_19",
                parentId: "06",
                lable: "1986~1990全市累计沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/18",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_10",
                parentId: "06",
                lable: "1977~1985全市累计沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/9",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_11",
                parentId: "06",
                lable: "1972~1976全市累计沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/10",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_12",
                parentId: "06",
                lable: "1966~1976全市累计沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/11",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_13",
                parentId: "06",
                lable: "1966~1971全市累计沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/12",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_14",
                parentId: "06",
                lable: "1962~1965全市累计沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/13",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_15",
                parentId: "06",
                lable: "1957~1961全市累计沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/14",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_16",
                parentId: "06",
                lable: "1949~1956全市累计沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/15",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            },
            {
                id: "06_17",
                parentId: "06",
                lable: "1921~1948 全市累计沉降量",
                visible: false,
                url: td_LayerMapsUrls + "/arcgis/rest/services/Metro/DMCJ/MapServer/16",
                type: "dynamic",
                iconType: "Layer",
                layerFlag: "polygon"
            }
        ]
    }
];




