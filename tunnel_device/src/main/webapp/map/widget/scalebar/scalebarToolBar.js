if(void 0===G_Dci_Info)var G_Dci_Info={};require(["dojo/_base/declare","esri/dijit/Scalebar"],function(o,e){G_Dci_Info.scalebarToolBar={map:null,legend:null,initScalebar:function(o){this.map=o,this.creatElement()},creatElement:function(){var o=new StringBuffer;o.append("<div id='map_scalebar_coordinates' class='map_scalebar_coordinates'><div id='div_scalebar' class='scalebarInfo'></div><div id='div_coordsInfo' class='coordsInfo'></div></div>"),$("#map").append(o.toString()),dojo.connect(this.map,"onMouseMove",a),dojo.connect(this.map,"onMouseDrag",a);e({map:this.map,attachTo:"bottom-left",scalebarStyle:"ruler",scalebarUnit:"metric"},dojo.byId("div_scalebar"));function a(o){var a=(o=o||(window.event?window.event:null)).mapPoint;$("#div_coordsInfo").html("<span style='color: red;font-weight: bold;'>X</span>:<span style='color: #1825d7;'>"+a.x.toFixed(3)+"</span>&nbsp;<span style='color: red;font-weight: bold;'>Y</span>:<span style='color: #1825d7;'>"+a.y.toFixed(3)+"</span>")}}}});