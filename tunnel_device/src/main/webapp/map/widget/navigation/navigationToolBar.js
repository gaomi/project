function getElCoordinate(e){for(var t=e.offsetTop,l=e.offsetLeft,s=e.offsetWidth,o=e.offsetHeight;e=e.offsetParent;)t+=e.offsetTop,l+=e.offsetLeft;return{top:t,left:l,width:s,height:o,bottom:t+o,right:l+s}}function disableSelection(e){void 0!==e.onselectstart?e.onselectstart=function(){return!1}:void 0!==e.style.MozUserSelect?e.style.MozUserSelect="none":e.onmousedown=function(){return!1},e.style.cursor="default"}MapNavigationToolbar=function(e){"object"==typeof e?(this.targetId=e.targetId,this.minValue=e.minValue?e.minValue:0,this.maxValue=e.maxValue?e.maxValue:12,this.startValue=e.startValue?e.startValue:0,this.toolbarCss=e.toolbarCss?e.toolbarCss:["toolBar","toolBar_button","toolBar_slider","toolBar_mark"],this.marksShow=e.marksShow?e.marksShow:{countryLevel:null,provinceLevel:null,cityLevel:null,streetLevel:null},this.onMoveUp=null,this.onMoveDown=null,this.onMoveLeft=null,this.onMoveRight=null,this.onFullMap=null,this.onZoomIn=null,this.onZoomOut=null,this.onSliderEnd=null,this.onMark_Street=null,this.onMark_City=null,this.onMark_Province=null,this.onMark_Country=null,this._initializer.apply(this)):console.log("配置参数错误，请重新配置参数！")},MapNavigationToolbar.prototype={_initializer:function(){if(this._button={},this._slider={},this._mark={},this._target=document.createElement("DIV"),document.getElementById(this.targetId).appendChild(this._target),this.minValue>this.maxValue){var e=this.minValue;this.minValue=this.maxValue,this.maxValue=e}this.minValue>this.startValue&&(this.startValue=this.minValue),this._value=this.startValue},create:function(){this._createToolbar()},show:function(){this._target.style.display="block"},hide:function(){this._target.style.display="none"},dispose:function(){},_createToolbar:function(){with(this)_target.className=toolbarCss[0],disableSelection(_target),_createButton(),_createSlider(),_createMark()},_createButton:function(){with(this){var _self=this._button;_self._container=document.createElement("DIV"),_target.appendChild(_self._container),_self._container.className=toolbarCss[1],_self._north=document.createElement("DIV"),_self._container.appendChild(_self._north),_self._north.id=targetId+"_button_north",_self._north.title="向上平移",_self._north.className=toolbarCss[1]+"_north",_self._north.onclick=function(e){onMoveUp.call(this)},_self._north.onmouseover=function(e){_self._container.className=toolbarCss[1]+"_Nover"},_self._north.onmouseout=function(e){_self._container.className=toolbarCss[1]},_self._west=document.createElement("DIV"),_self._container.appendChild(_self._west),_self._west.id=targetId+"_button_west",_self._west.title="向左平移",_self._west.className=toolbarCss[1]+"_west",_self._west.onclick=function(e){onMoveLeft.call(this)},_self._west.onmouseover=function(e){_self._container.className=toolbarCss[1]+"_Wover"},_self._west.onmouseout=function(e){_self._container.className=toolbarCss[1]},_self._center=document.createElement("DIV"),_self._container.appendChild(_self._center),_self._center.id=targetId+"_button_center",_self._center.title="查看全图",_self._center.className=toolbarCss[1]+"_center",_self._center.onclick=function(e){onFullMap.call(this)},_self._east=document.createElement("DIV"),_self._container.appendChild(_self._east),_self._east.id=targetId+"_button_east",_self._east.title="向右平移",_self._east.className=toolbarCss[1]+"_east",_self._east.onclick=function(e){onMoveRight.call(this)},_self._east.onmouseover=function(e){_self._container.className=toolbarCss[1]+"_Eover"},_self._east.onmouseout=function(e){_self._container.className=toolbarCss[1]},_self._south=document.createElement("DIV"),_self._container.appendChild(_self._south),_self._south.id=targetId+"_button_south",_self._south.title="向下平移",_self._south.className=toolbarCss[1]+"_south",_self._south.onclick=function(e){onMoveDown.call(this)},_self._south.onmouseover=function(e){_self._container.className=toolbarCss[1]+"_Sover"},_self._south.onmouseout=function(e){_self._container.className=toolbarCss[1]}}},_createSlider:function(){with(this){var _self=this._slider;_self._container=document.createElement("DIV"),_target.appendChild(_self._container),_self._container.onmouseover=function(e){_mark._container.style.display="block"},_self._container.onmouseout=function(e){setTimeout(function(){_mark._container.style.display="none"},2e3)},_self._container.className=toolbarCss[2],_self._zoomIn=document.createElement("DIV"),_self._container.appendChild(_self._zoomIn),_self._zoomIn.id=targetId+"_slider_zoomIn",_self._zoomIn.title="放大一级",_self._zoomIn.className=toolbarCss[2]+"_zoomIn",_self._zoomIn.onclick=function(e){_zoomIn(e)},_self._ticks=document.createElement("DIV"),_self._container.appendChild(_self._ticks),_self._ticks.id=targetId+"_slider_ticks",_self._ticks.title="缩放到此级别",_self._ticks.style.height=6*(maxValue-minValue+1)+"px",_self._ticks.className=toolbarCss[2]+"_ticks",_self._ticks.onclick=function(e){_moveTo(e),_moveEnd(e)},_self._ticksSel=document.createElement("DIV"),_self._ticks.appendChild(_self._ticksSel),_self._ticksSel.id=targetId+"_slider_ticksSel",_self._ticksSel.title="缩放到此级别",_self._ticksSel.style.height=0==startValue?0:6*(startValue-1)+"px",_self._ticksSel.className=toolbarCss[2]+"_ticksSel",_self._ticksSel.onclick=function(e){_moveTo(e),_moveEnd(e)},_self._float=document.createElement("DIV"),_self._ticks.appendChild(_self._float),_self._float.id=targetId+"_slider_float",_self._float.title="拖动缩放",_self._float.className=toolbarCss[2]+"_float_nonactivated",_self._float.style.bottom=6*(startValue-(0==minValue?1:minValue))+"px",_self._float.onmouseover=function(e){_self._float.className=toolbarCss[2]+"_float_activated"},_self._float.onmouseout=function(e){_self._float.className=toolbarCss[2]+"_float_nonactivated"},_self._float.onmousedown=function(e){e=e||window.event,document.onmousemove=function(e){_moveTo(e)},document.onmouseup=function(e){_moveEnd(e)}},_self._zoomOut=document.createElement("DIV"),_self._container.appendChild(_self._zoomOut),_self._zoomOut.id=targetId+"_slider_zoomOut",_self._zoomOut.title="缩小一级",_self._zoomOut.className=toolbarCss[2]+"_zoomOut",_self._zoomOut.onclick=function(e){_zoomOut(e)}}},_createMark:function(){with(this){var _self=this._mark;_self._container=document.createElement("DIV"),_target.appendChild(_self._container),_self._container.id=targetId+"_mark",_self._container.style.display="none",_self._container.className=toolbarCss[3],null!=marksShow.streetLevel&&marksShow.streetLevel>=minValue&&marksShow.streetLevel<=maxValue&&(_self._street=document.createElement("DIV"),_self._container.appendChild(_self._street),_self._street.id=targetId+"_mark_street",_self._street.title="缩放到街道",_self._street.className=toolbarCss[3]+"_street",_self._street.style.top=6*(maxValue-marksShow.streetLevel)+"px",_self._street.onclick=function(e){onMark_Street.call(this)}),null!=marksShow.cityLevel&&marksShow.cityLevel>=minValue&&marksShow.cityLevel<=maxValue&&(_self._city=document.createElement("DIV"),_self._container.appendChild(_self._city),_self._city.id=targetId+"_mark_city",_self._city.title="缩放到城市",_self._city.className=toolbarCss[3]+"_city",_self._city.style.top="47px",_self._city.onclick=function(e){onMark_City.call(this)}),null!=marksShow.provinceLevel&&marksShow.provinceLevel>=minValue&&marksShow.provinceLevel<=maxValue&&(_self._province=document.createElement("DIV"),_self._container.appendChild(_self._province),_self._province.id=targetId+"_mark_province",_self._province.title="缩放到省",_self._province.className=toolbarCss[3]+"_province",_self._province.style.top="64px",_self._province.onclick=function(e){onMark_Province.call(this)}),null!=marksShow.countryLevel&&marksShow.countryLevel>=minValue&&marksShow.countryLevel<=maxValue&&(_self._country=document.createElement("DIV"),_self._container.appendChild(_self._country),_self._country.id=targetId+"_mark_country",_self._country.title="缩放到国家",_self._country.className=toolbarCss[3]+"_country",_self._country.style.top="87px",_self._country.onclick=function(e){onMark_Country.call(this)})}},_moveTo:function(event){var _self=this;with(_self._slider){event=event||window.event;var ticks_Top=getElCoordinate(_ticks).top,ticks_Height=_ticks.offsetHeight-_float.offsetHeight,ticks_Bottom=ticks_Top+_ticks.offsetHeight,ticks_ValuePx=ticks_Height/(_self.maxValue-_self.minValue),x=ticks_Bottom-event.clientY-Math.round(_float.offsetHeight/2);x=0==x?0:Math.round(x/ticks_ValuePx)*ticks_ValuePx,x=x<=0?0:ticks_Height<=x?ticks_Height:x,_float.style.bottom=x+"px",_self._slider._ticksSel.style.height=x+"px",_self._value=x/ticks_ValuePx+_self.minValue}},_moveEnd:function(e){document.onmousemove=null,document.onmouseup=null,this.onSliderEnd.call(this)},_zoomIn:function(event){with(this){var v=getValue();++v,setValue(v),onZoomIn.call(this)}},_zoomOut:function(event){with(this){var v=getValue();--v,setValue(v),onZoomOut.call(this)}},setValue:function(value){with(this){if(!_slider._float)return;value=Number(value),value=value>maxValue?maxValue:value<minValue?minValue:value;var ticks_Height=_slider._ticks.offsetHeight-_slider._float.offsetHeight,ticks_ValuePx=ticks_Height/(maxValue-minValue),x=(value-minValue)*ticks_ValuePx;x=x<=0?0:ticks_Height<=x?ticks_Height:x,_slider._float.style.bottom=parseInt(x)+"px",_slider._ticksSel.style.height=parseInt(x)+"px",_value=value}},getValue:function(){return this._value}};