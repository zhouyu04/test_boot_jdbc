(window.webpackJsonp=window.webpackJsonp||[]).push([[10],{"332":function(e,t,n){},"343":function(e,t,n){"use strict";n.r(t);var a,c=n(3),o=n(4),i=n(31),l=n(12),r=n(13),s=n(6),u=n(1),m=n.n(u),d=n(320),p=n(322),b=n(84),w=(n(332),Object(b.b)("store")(a=Object(b.c)(a=function(e){Object(l.a)(Index,e);var t=Object(r.a)(Index);function Index(e){var n;return Object(c.a)(this,Index),n=t.call(this,e),Object(s.a)(Object(i.a)(n),"useCoupon",(function(){Taro.navigateTo({"url":"/coupon/code"})})),n.state={"mobile":"","userName":"","tenantId":"","accountId":"","schemaId":"","loading":!1},n}return Object(o.a)(Index,[{"key":"componentDidMount","value":function componentDidMount(){}},{"key":"render","value":function render(){var e=this.props.store.couponStore.cpInfo,t=e.effectTime.slice(0,11),n=e.expireTime.slice(0,11);return m.a.createElement(d.View,{"className":"receive-coupon"},m.a.createElement(d.View,{"className":"header"}),m.a.createElement(d.View,{"className":"content"},m.a.createElement(p.AtButton,{"type":"primary","loading":this.state.loading,"onClick":this.useCoupon,"className":"use-coupon"},"使用"),m.a.createElement(d.View,{"className":"coupon-detail"},m.a.createElement(d.View,{"className":"amount"},m.a.createElement(d.Text,null,e.totalAmt),m.a.createElement(d.Text,null,"元")),m.a.createElement(d.View,{"className":"limit"},m.a.createElement(d.Text,null,t," - ",n)))))}}]),Index}(u.Component))||a)||a);t.default=w}}]);