(window.webpackJsonp=window.webpackJsonp||[]).push([[43],{"311":function(e,t,i){"use strict";i.r(t),i.d(t,"taro_switch_core",(function(){return n}));var c=i(54),n=function(){function Switch(e){var t=this;Object(c.g)(this,e),this.type="switch",this.checked=!1,this.color="#04BE02",this.switchChange=function(e){e.stopPropagation();var i=e.target.checked;t.isChecked=i,t.onChange.emit({"value":i})},this.onChange=Object(c.d)(this,"change",7)}return Switch.prototype.function=function(e,t){e!==t&&(this.isChecked=e)},Switch.prototype.componentWillLoad=function(){this.isChecked=this.checked},Switch.prototype.componentDidLoad=function(){var e=this;Object.defineProperty(this.el,"value",{"get":function(){return e.isChecked},"configurable":!0})},Switch.prototype.render=function(){var e=this.type,t=this.color,i=this.isChecked,n=this.name,o=i?{"borderColor":t||"04BE02","backgroundColor":t||"04BE02"}:{};return Object(c.f)("input",{"type":"checkbox","class":"weui-"+e,"style":o,"checked":i,"name":n,"onChange":this.switchChange})},Object.defineProperty(Switch.prototype,"el",{"get":function(){return Object(c.e)(this)},"enumerable":!0,"configurable":!0}),Object.defineProperty(Switch,"watchers",{"get":function(){return{"checked":["function"]}},"enumerable":!0,"configurable":!0}),Object.defineProperty(Switch,"style",{"get":function(){return"taro-switch-core{display:inline-block;width:52px;height:32px}taro-switch-core .weui-switch{display:block;width:100%;height:100%}"},"enumerable":!0,"configurable":!0}),Switch}()}}]);