define(["dojo/_base/declare","esri/layers/TiledMapServiceLayer","esri/SpatialReference","esri/geometry/Extent","esri/layers/TileInfo"],function(e,t){return e("TdtTiledMapServiceLayer",t,{layertype:"vec",constructor:function(e){this.layertype=e.esriLayerType,this.spatialReference=new esri.SpatialReference(MapConfig.mapInitParams.spatialReference),this.fullExtent=new esri.geometry.Extent({xmin:MapConfig.params_tdt.fullExtent.xmin,ymin:MapConfig.params_tdt.fullExtent.ymin,xmax:MapConfig.params_tdt.fullExtent.xmax,ymax:MapConfig.params_tdt.fullExtent.ymax,spatialReference:this.spatialReference}),this.initialExtent=this.fullExtent,this.tileInfo=new esri.layers.TileInfo(MapConfig.tianDiTu_Par),this.loaded=!0,this.onLoad(this)},getTileUrl:function(e,t,i){var a="";switch(this.layertype){case"vec":a="http://t"+i%8+".tianditu.cn/vec_c/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=vec&STYLE=default&TILEMATRIXSET=c&TILEMATRIX="+e+"&TILEROW="+t+"&TILECOL="+i+"&FORMAT=tiles";break;case"vec_lab":a="http://t"+t%8+".tianditu.cn/cva_c/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=cva&STYLE=default&TILEMATRIXSET=c&TILEMATRIX="+e+"&TILEROW="+t+"&TILECOL="+i+"&FORMAT=tiles";break;case"img":a="http://t"+t%8+".tianditu.cn/img_c/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=img&STYLE=default&TILEMATRIXSET=c&TILEMATRIX="+e+"&TILEROW="+t+"&TILECOL="+i+"&FORMAT=tiles";break;case"img_lab":a="http://t"+t%8+".tianditu.cn/cia_c/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=cia&STYLE=default&TILEMATRIXSET=c&TILEMATRIX="+e+"&TILEROW="+t+"&TILECOL="+i+"&FORMAT=tiles";break;default:a="http://t"+i%8+".tianditu.cn/vec_c/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=vec&STYLE=default&TILEMATRIXSET=c&TILEMATRIX="+e+"&TILEROW="+t+"&TILECOL="+i+"&FORMAT=tiles"}return a}})});