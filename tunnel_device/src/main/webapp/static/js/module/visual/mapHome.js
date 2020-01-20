var home_Map;

require(["esri/map",
        "esri/layers/GraphicsLayer",
        "esri/layers/ArcGISDynamicMapServiceLayer",
        "esri/layers/LayerDrawingOptions",
        "esri/renderers/ClassBreaksRenderer",
        "esri/geometry/Extent",
        "esri/graphic",
        "esri/Color",
        "esri/symbols/SimpleLineSymbol",
        "esri/tasks/query",
        "esri/tasks/QueryTask",
        "esri/config",
        "dojo/domReady!"],
    function (Map, GraphicsLayer, ArcGISDynamicMapServiceLayer, LayerDrawingOptions, ClassBreaksRenderer, Extent, Graphic, Color, SimpleLineSymbol, Query, QueryTask, esriConfig) {
        try {

            var idxMapConfig = {};
            idxMapConfig.httpProxy = {
                useProxy: true,
                // url: "http://localhost:8080/gis-proxy/proxy.jsp"
                //url: "http://localhost:8080/proxy.jsp"
                //url: "http://localhost:8080/gis-proxy/proxy"
                //url: "http://localhost:8080/proxy-java/proxy.jsp"
                //url: "http://localhost:8080/p-java/proxy.jsp"
                //url: "http://192.168.12.61/gisProxy/proxy.ashx"
                //url: ctx + "gis/proxy"
                url: ctx + "/proxy.jsp"
                //url: "http://metro.sigs.cn/tunnel_api/proxy.jsp"
                //url: "http://218.242.68.246/tunnel_api/proxy.jsp"
                //url: "http://10.0.112.71/tunnel_api/proxy.jsp"
                //url: "http://localhost:81/tunnel_api_test/proxy"
                // url: "http://192.168.12.155/tunnel_device/proxy"
            };

            if (idxMapConfig.httpProxy.useProxy) {
                esriConfig.defaults.io.proxyUrl = idxMapConfig.httpProxy.url;
                esriConfig.defaults.io.alwaysUseProxy = idxMapConfig.httpProxy.useProxy;
            }

            home_Map = new Map("mapDiv", {
                logo: false, slider: false, maxScale: 245,
                extent: new Extent({
                    xmin: 666, ymin: -1635, xmax: 1118, ymax: -1180,
                    spatialReference: {wkt: "PROJCS[\"shanghaicity\",GEOGCS[\"GCS_Beijing_1954\",DATUM[\"D_Beijing_1954\",SPHEROID[\"Krasovsky_1940\",6378245.0,298.3]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",-3457147.81],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",121.2751921],PARAMETER[\"Scale_Factor\",1.0],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Meter\",1.0]]"}
                })
            });

            // var lineMapLayer_Url = "http://192.168.12.55:6080/arcgis/rest/services/Metro/lineMap/MapServer";
            var lineMapLayer_Url = "http://10.0.112.72:6080/arcgis/rest/services/Metro/MetroLineFig/MapServer";

            var lineMapLayer = new ArcGISDynamicMapServiceLayer(lineMapLayer_Url, {
                id: "lineMapLayer",
                visible: true
            });
            lineMapLayer.setVisibleLayers([0, 1, 2]);
            home_Map.addLayer(lineMapLayer);

            var drawingOptions = new LayerDrawingOptions();

            var symbol = new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([0, 0, 0, 0]), 4);

            var renderer = new ClassBreaksRenderer(symbol, "Shape_Length");
            renderer.addBreak(0, 15, new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([255, 255, 0, 0.9]), 4));
            renderer.addBreak(15, 35, new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([0, 191, 255, 0.9]), 4));
            renderer.addBreak(35, 50, new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([148, 0, 211, 0.9]), 4));
            renderer.addBreak(50, 500, new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([255, 0, 0, 0.9]), 4));

            drawingOptions.renderer = renderer;
            var options = [];
            options[2] = drawingOptions;
            home_Map.getLayer("lineMapLayer").setLayerDrawingOptions(options);

            var query = new Query();
            query.spatialRelationship = Query.SPATIAL_REL_INTERSECTS;
            query.outSpatialReference = home_Map.spatialReference;
            query.returnGeometry = true;
            query.where = "1 = 1";
            query.outFields = ["*"];

            var queryTask = new QueryTask(lineMapLayer_Url + "/" + "2");
            queryTask.execute(query, showSuccResults, showErrorResults);

            function showSuccResults(results) {
                $("#content_id .chart-loader").css("display", "none");
                $("#content_id .content_body").css("display", "block");
                // debugger;
                // for (var i = 0; i < results.features.length; i++) {
                //     var geoTemp = results.features[i].geometry;
                //     var featureAttributes = results.features[i].attributes;
                //     // featureAttributes['attindex'] = 50;
                //     var geoAttr = {
                //         "lineNo": featureAttributes["lineNo"],
                //         "segmentNo": featureAttributes["segmentNo"],
                //         "name": featureAttributes["name"]
                //     };
                //     var _simpleLineSymbol = new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([0, 0, 0, 0]), 4);
                //     var graTemp = new Graphic(geoTemp, _simpleLineSymbol, geoAttr);
                //
                //     home_Map.graphics.add(graTemp);
                // }

                home_Map.graphics.on("click", function (evt) {
                    // alert("lineNo : " + evt.graphic.attributes.lineNo + " segmentNo : " + evt.graphic.attributes.segmentNo);
                    window.open(ctx + "/segment", '_blank');
                });

                home_Map.graphics.on("mouse-over", function (evt) {
                    home_Map.setMapCursor('pointer');
                });

                home_Map.graphics.on("mouse-out", function (evt) {
                    home_Map.setMapCursor('default');
                });
            }

            function showErrorResults(results) {
                console.log("查询异常!");
            }
        } catch (exp) {
            console.log(exp);
        }
    });

var home_Map_big;
require(["esri/map",
        "esri/layers/GraphicsLayer",
        "esri/layers/ArcGISDynamicMapServiceLayer",
        "esri/layers/LayerDrawingOptions",
        "esri/renderers/ClassBreaksRenderer",
        "esri/geometry/Extent",
        "esri/graphic",
        "esri/Color",
        "esri/symbols/SimpleLineSymbol",
        "esri/tasks/query",
        "esri/tasks/QueryTask",
        "esri/config",
        "dojo/domReady!"],
    function (Map, GraphicsLayer, ArcGISDynamicMapServiceLayer, LayerDrawingOptions, ClassBreaksRenderer, Extent, Graphic, Color, SimpleLineSymbol, Query, QueryTask, esriConfig) {
        try {
            var idxMapConfig = {};
            idxMapConfig.httpProxy = {
                useProxy: true,
                // url: "http://localhost:8080/gis-proxy/proxy.jsp"
                //url: "http://localhost:8080/proxy.jsp"
                //url: "http://localhost:8080/gis-proxy/proxy"
                //url: "http://localhost:8080/proxy-java/proxy.jsp"
                //url: "http://localhost:8080/p-java/proxy.jsp"
                //url: "http://192.168.12.61/gisProxy/proxy.ashx"
                //url: ctx + "gis/proxy"
                url: ctx + "/proxy.jsp"
                //url: "http://metro.sigs.cn/tunnel_api/proxy.jsp"
                //url: "http://218.242.68.246/tunnel_api/proxy.jsp"
                //url: "http://10.0.112.71/tunnel_api/proxy.jsp"
                //url: "http://localhost:81/tunnel_api_test/proxy"
                // url: "http://192.168.12.155/tunnel_device/proxy"
            };

            if (idxMapConfig.httpProxy.useProxy) {
                esriConfig.defaults.io.proxyUrl = idxMapConfig.httpProxy.url;
                esriConfig.defaults.io.alwaysUseProxy = idxMapConfig.httpProxy.useProxy;
            }

            home_Map_big = new Map("mapDiv_big", {
                logo: false, slider: false, maxScale: 245,
                extent: new Extent({
                    xmin: 266, ymin: -1535, xmax: 718, ymax: -1180,
                    spatialReference: {wkt: "PROJCS[\"shanghaicity\",GEOGCS[\"GCS_Beijing_1954\",DATUM[\"D_Beijing_1954\",SPHEROID[\"Krasovsky_1940\",6378245.0,298.3]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",-3457147.81],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",121.2751921],PARAMETER[\"Scale_Factor\",1.0],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Meter\",1.0]]"}
                })
            });
            // var lineMapLayer_Url = "http://192.168.12.55:6080/arcgis/rest/services/Metro/lineMap/MapServer";
            var lineMapLayer_Url = "http://10.0.112.72:6080/arcgis/rest/services/Metro/MetroLineFig/MapServer";

            var lineMapLayer = new ArcGISDynamicMapServiceLayer(lineMapLayer_Url, {
                id: "lineMapLayer",
                visible: true
            });
            lineMapLayer.setVisibleLayers([0, 1, 2]);
            home_Map_big.addLayer(lineMapLayer);

            var drawingOptions = new LayerDrawingOptions();

            var symbol = new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([0, 0, 0, 0]), 4);

            var renderer = new ClassBreaksRenderer(symbol, "Shape_Length");
            renderer.addBreak(0, 15, new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([255, 255, 0, 0.9]), 4));
            renderer.addBreak(15, 35, new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([0, 191, 255, 0.9]), 4));
            renderer.addBreak(35, 50, new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([148, 0, 211, 0.9]), 4));
            renderer.addBreak(50, 500, new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([255, 0, 0, 0.9]), 4));

            drawingOptions.renderer = renderer;
            var options = [];
            options[2] = drawingOptions;
            home_Map_big.getLayer("lineMapLayer").setLayerDrawingOptions(options);

            var query = new Query();
            query.spatialRelationship = Query.SPATIAL_REL_INTERSECTS;
            query.outSpatialReference = home_Map_big.spatialReference;
            query.returnGeometry = true;
            query.where = "1 = 1";
            query.outFields = ["*"];

            var queryTask = new QueryTask(lineMapLayer_Url + "/" + "2");
            queryTask.execute(query, showSuccResults, showErrorResults);

            function showSuccResults(results) {
                for (var i = 0; i < results.features.length; i++) {
                    var geoTemp = results.features[i].geometry;
                    var featureAttributes = results.features[i].attributes;
                    var geoAttr = {
                        "lineNo": featureAttributes["lineNo"],
                        "segmentNo": featureAttributes["segmentNo"],
                        "name": featureAttributes["name"]
                    };

                    var _simpleLineSymbol = new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([0, 0, 0, 0]), 4);
                    var graTemp = new Graphic(geoTemp, _simpleLineSymbol, geoAttr);

                    home_Map_big.graphics.add(graTemp);
                }

                home_Map_big.graphics.on("click", function (evt) {
                    // alert("lineNo : " + evt.graphic.attributes.lineNo + " segmentNo : " + evt.graphic.attributes.segmentNo);
                    window.open(ctx + "/segment", '_blank');
                });

                home_Map_big.graphics.on("mouse-over", function (evt) {
                    home_Map_big.setMapCursor('pointer');
                });

                home_Map_big.graphics.on("mouse-out", function (evt) {
                    home_Map_big.setMapCursor('default');
                });
            }

            function showErrorResults(results) {
                console.log("查询异常!");
            }
        } catch (exp) {
            console.log(exp);
        }
    });