(window.webpackJsonp=window.webpackJsonp||[]).push([[2],{"318":function(e,r,t){"use strict";t.r(r);var n=t(0),o=t.n(n);o.a.createElement;var reactify_wc=e=>{class Index extends o.a.Component{"constructor"(e){super(e),this.eventHandlers=[],this.ref=Object(n.createRef)()}"update"(r){this.clearEventHandlers(),Object.entries(this.props).forEach(([t,n])=>{if(this.ref.current&&"children"!==t)if("classname"!==t.toLowerCase())if("style"!==t){if("taro-scroll-view-core"===e){if("scrollTop"===t)return void(this.ref.current.mpScrollTop=n);if("scrollLeft"===t)return void(this.ref.current.mpScrollLeft=n);if("scrollIntoView"===t)return void(this.ref.current.mpScrollIntoView=n)}if("function"==typeof n&&t.match(/^on[A-Z]/)){const r=t.substr(2).toLowerCase();let o=n;return"taro-scroll-view-core"===e&&"scroll"===r&&(o=function(e){e instanceof CustomEvent&&n.apply(null,Array.from(arguments))}),this.eventHandlers.push([r,o]),this.ref.current.addEventListener(r,o)}if("string"!=typeof n&&"number"!=typeof n)return"boolean"==typeof n?n?(this.ref.current[t]=!0,this.ref.current.setAttribute(t,n)):(this.ref.current[t]=!1,this.ref.current.removeAttribute(t)):void(this.ref.current[t]=n);this.ref.current[t]=n}else{if("string"==typeof n)return this.ref.current.setAttribute(t,n);if(n&&"object"==typeof n){for(const e in n)this.ref.current.style[e]=n[e];return}}else this.ref.current.className=r?function getClassName(e,r,t){const n=Array.from(e.classList),o=(r.className||r.class||"").split(" ");let c=(t.className||t.class||"").split(" "),i=[];return n.forEach(e=>{c.indexOf(e)>-1?(i.push(e),c=c.filter(r=>r!==e)):-1===o.indexOf(e)&&i.push(e)}),i=[...i,...c],i.join(" ")}(this.ref.current,r,this.props):n})}"componentDidUpdate"(e){this.update(e)}"componentDidMount"(){const{"forwardRef":e}=this.props;"function"==typeof e?e(this.ref.current):e&&"object"==typeof e&&e.hasOwnProperty("current")?e.current=this.ref.current:"string"==typeof e&&console.warn("内置组件不支持字符串 ref"),this.update()}"componentWillUnmount"(){this.clearEventHandlers()}"clearEventHandlers"(){this.eventHandlers.forEach(([e,r])=>{this.ref.current&&this.ref.current.removeEventListener(e,r)}),this.eventHandlers=[]}"render"(){const{"children":r,"dangerouslySetInnerHTML":t}=this.props,o={"ref":this.ref};return t&&(o.dangerouslySetInnerHTML=t),Object(n.createElement)(e,o,r)}}return o.a.forwardRef((e,r)=>o.a.createElement(Index,{...e,"forwardRef":r}))};const c=reactify_wc("taro-input-core");o.a.createElement;var i=o.a.forwardRef((e,r)=>{const t={...e};return t.hasOwnProperty("focus")&&(t.autoFocus=Boolean(t.focus),delete t.focus),o.a.createElement(c,{...t,"ref":r})});t.d(r,"View",(function(){return a})),t.d(r,"Icon",(function(){return u})),t.d(r,"Progress",(function(){return s})),t.d(r,"RichText",(function(){return f})),t.d(r,"Text",(function(){return l})),t.d(r,"Button",(function(){return d})),t.d(r,"Checkbox",(function(){return p})),t.d(r,"CheckboxGroup",(function(){return m})),t.d(r,"Editor",(function(){return h})),t.d(r,"Form",(function(){return v})),t.d(r,"Input",(function(){return w})),t.d(r,"Label",(function(){return b})),t.d(r,"Picker",(function(){return y})),t.d(r,"PickerView",(function(){return g})),t.d(r,"PickerViewColumn",(function(){return k})),t.d(r,"Radio",(function(){return x})),t.d(r,"RadioGroup",(function(){return O})),t.d(r,"Slider",(function(){return E})),t.d(r,"Switch",(function(){return j})),t.d(r,"CoverImage",(function(){return I})),t.d(r,"Textarea",(function(){return A})),t.d(r,"CoverView",(function(){return C})),t.d(r,"MoveableArea",(function(){return S})),t.d(r,"MoveableView",(function(){return H})),t.d(r,"ScrollView",(function(){return L})),t.d(r,"Swiper",(function(){return V})),t.d(r,"SwiperItem",(function(){return M})),t.d(r,"FunctionalPageNavigator",(function(){return D})),t.d(r,"Navigator",(function(){return N})),t.d(r,"Audio",(function(){return P})),t.d(r,"Camera",(function(){return R})),t.d(r,"Image",(function(){return T})),t.d(r,"LivePlayer",(function(){return B})),t.d(r,"Video",(function(){return W})),t.d(r,"Map",(function(){return U})),t.d(r,"Canvas",(function(){return F})),t.d(r,"Ad",(function(){return G})),t.d(r,"OfficialAccount",(function(){return J})),t.d(r,"OpenData",(function(){return Z})),t.d(r,"WebView",(function(){return q})),t.d(r,"NavigationBar",(function(){return z})),t.d(r,"Block",(function(){return K}));const a=reactify_wc("taro-view-core"),u=reactify_wc("taro-icon-core"),s=reactify_wc("taro-progress-core"),f=reactify_wc("taro-rich-text-core"),l=reactify_wc("taro-text-core"),d=reactify_wc("taro-button-core"),p=reactify_wc("taro-checkbox-core"),m=reactify_wc("taro-checkbox-group-core"),h=reactify_wc("taro-editor-core"),v=reactify_wc("taro-form-core"),w=i,b=reactify_wc("taro-label-core"),y=reactify_wc("taro-picker-core"),g=reactify_wc("taro-picker-view-core"),k=reactify_wc("taro-picker-view-column-core"),x=reactify_wc("taro-radio-core"),O=reactify_wc("taro-radio-group-core"),E=reactify_wc("taro-slider-core"),j=reactify_wc("taro-switch-core"),I=reactify_wc("taro-cover-image-core"),A=reactify_wc("taro-textarea-core"),C=reactify_wc("taro-cover-view-core"),S=reactify_wc("taro-moveable-area-core"),H=reactify_wc("taro-moveable-view-core"),L=reactify_wc("taro-scroll-view-core"),V=reactify_wc("taro-swiper-core"),M=reactify_wc("taro-swiper-item-core"),D=reactify_wc("taro-functional-page-navigator-core"),N=reactify_wc("taro-navigator-core"),P=reactify_wc("taro-audio-core"),R=reactify_wc("taro-camera-core"),T=reactify_wc("taro-image-core"),B=reactify_wc("taro-live-player-core"),W=reactify_wc("taro-video-core"),U=reactify_wc("taro-map-core"),F=reactify_wc("taro-canvas-core"),G=reactify_wc("taro-ad-core"),J=reactify_wc("taro-official-account-core"),Z=reactify_wc("taro-open-data-core"),q=reactify_wc("taro-web-view-core"),z=reactify_wc("taro-navigation-bar-core"),K=reactify_wc("taro-block-core")},"321":function(e,r,t){},"326":function(e,r,t){"use strict";t.r(r);var n,o=t(2),c=t(4),i=t(30),a=t(11),u=t(12),s=t(5),f=t(0),l=t.n(f),d=t(318),p=t(83),m=(t(321),Object(p.b)("store")(n=Object(p.c)(n=function(e){Object(a.a)(Index,e);var r=Object(u.a)(Index);function Index(){var e;Object(o.a)(this,Index);for(var t=arguments.length,n=new Array(t),c=0;c<t;c++)n[c]=arguments[c];return e=r.call.apply(r,[this].concat(n)),Object(s.a)(Object(i.a)(e),"increment",(function(){e.props.store.counterStore.increment()})),Object(s.a)(Object(i.a)(e),"decrement",(function(){e.props.store.counterStore.decrement()})),Object(s.a)(Object(i.a)(e),"incrementAsync",(function(){e.props.store.counterStore.incrementAsync()})),e}return Object(c.a)(Index,[{"key":"componentWillMount","value":function componentWillMount(){}},{"key":"componentDidMount","value":function componentDidMount(){}},{"key":"componentWillUnmount","value":function componentWillUnmount(){}},{"key":"componentDidShow","value":function componentDidShow(){}},{"key":"componentDidHide","value":function componentDidHide(){}},{"key":"render","value":function render(){var e=this.props.store.counterStore.counter;return l.a.createElement(d.View,{"className":"index"},l.a.createElement(d.Button,{"onClick":this.increment},"+"),l.a.createElement(d.Button,{"onClick":this.decrement},"-"),l.a.createElement(d.Button,{"onClick":this.incrementAsync},"Add Async"),l.a.createElement(d.Text,null,e))}}]),Index}(f.Component))||n)||n);r.default=m}}]);