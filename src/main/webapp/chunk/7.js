(window.webpackJsonp=window.webpackJsonp||[]).push([[7],{"319":function(r,e,t){"use strict";t.r(e);var n=t(0),o=t.n(n);o.a.createElement;var reactify_wc=r=>{class Index extends o.a.Component{"constructor"(r){super(r),this.eventHandlers=[],this.ref=Object(n.createRef)()}"update"(e){this.clearEventHandlers(),Object.entries(this.props).forEach(([t,n])=>{if(this.ref.current&&"children"!==t)if("classname"!==t.toLowerCase())if("style"!==t){if("taro-scroll-view-core"===r){if("scrollTop"===t)return void(this.ref.current.mpScrollTop=n);if("scrollLeft"===t)return void(this.ref.current.mpScrollLeft=n);if("scrollIntoView"===t)return void(this.ref.current.mpScrollIntoView=n)}if("function"==typeof n&&t.match(/^on[A-Z]/)){const e=t.substr(2).toLowerCase();let o=n;return"taro-scroll-view-core"===r&&"scroll"===e&&(o=function(r){r instanceof CustomEvent&&n.apply(null,Array.from(arguments))}),this.eventHandlers.push([e,o]),this.ref.current.addEventListener(e,o)}if("string"!=typeof n&&"number"!=typeof n)return"boolean"==typeof n?n?(this.ref.current[t]=!0,this.ref.current.setAttribute(t,n)):(this.ref.current[t]=!1,this.ref.current.removeAttribute(t)):void(this.ref.current[t]=n);this.ref.current[t]=n}else{if("string"==typeof n)return this.ref.current.setAttribute(t,n);if(n&&"object"==typeof n){for(const r in n)this.ref.current.style[r]=n[r];return}}else this.ref.current.className=e?function getClassName(r,e,t){const n=Array.from(r.classList),o=(e.className||e.class||"").split(" ");let c=(t.className||t.class||"").split(" "),i=[];return n.forEach(r=>{c.indexOf(r)>-1?(i.push(r),c=c.filter(e=>e!==r)):-1===o.indexOf(r)&&i.push(r)}),i=[...i,...c],i.join(" ")}(this.ref.current,e,this.props):n})}"componentDidUpdate"(r){this.update(r)}"componentDidMount"(){const{"forwardRef":r}=this.props;"function"==typeof r?r(this.ref.current):r&&"object"==typeof r&&r.hasOwnProperty("current")?r.current=this.ref.current:"string"==typeof r&&console.warn("内置组件不支持字符串 ref"),this.update()}"componentWillUnmount"(){this.clearEventHandlers()}"clearEventHandlers"(){this.eventHandlers.forEach(([r,e])=>{this.ref.current&&this.ref.current.removeEventListener(r,e)}),this.eventHandlers=[]}"render"(){const{"children":e,"dangerouslySetInnerHTML":t}=this.props,o={"ref":this.ref};return t&&(o.dangerouslySetInnerHTML=t),Object(n.createElement)(r,o,e)}}return o.a.forwardRef((r,e)=>o.a.createElement(Index,{...r,"forwardRef":e}))};const c=reactify_wc("taro-input-core");o.a.createElement;var i=o.a.forwardRef((r,e)=>{const t={...r};return t.hasOwnProperty("focus")&&(t.autoFocus=Boolean(t.focus),delete t.focus),o.a.createElement(c,{...t,"ref":e})});t.d(e,"View",(function(){return a})),t.d(e,"Icon",(function(){return u})),t.d(e,"Progress",(function(){return f})),t.d(e,"RichText",(function(){return s})),t.d(e,"Text",(function(){return d})),t.d(e,"Button",(function(){return l})),t.d(e,"Checkbox",(function(){return p})),t.d(e,"CheckboxGroup",(function(){return h})),t.d(e,"Editor",(function(){return v})),t.d(e,"Form",(function(){return m})),t.d(e,"Input",(function(){return w})),t.d(e,"Label",(function(){return b})),t.d(e,"Picker",(function(){return g})),t.d(e,"PickerView",(function(){return y})),t.d(e,"PickerViewColumn",(function(){return x})),t.d(e,"Radio",(function(){return I})),t.d(e,"RadioGroup",(function(){return M})),t.d(e,"Slider",(function(){return E})),t.d(e,"Switch",(function(){return O})),t.d(e,"CoverImage",(function(){return S})),t.d(e,"Textarea",(function(){return k})),t.d(e,"CoverView",(function(){return C})),t.d(e,"MoveableArea",(function(){return N})),t.d(e,"MoveableView",(function(){return j})),t.d(e,"ScrollView",(function(){return A})),t.d(e,"Swiper",(function(){return P})),t.d(e,"SwiperItem",(function(){return L})),t.d(e,"FunctionalPageNavigator",(function(){return V})),t.d(e,"Navigator",(function(){return H})),t.d(e,"Audio",(function(){return R})),t.d(e,"Camera",(function(){return T})),t.d(e,"Image",(function(){return B})),t.d(e,"LivePlayer",(function(){return D})),t.d(e,"Video",(function(){return U})),t.d(e,"Map",(function(){return F})),t.d(e,"Canvas",(function(){return G})),t.d(e,"Ad",(function(){return J})),t.d(e,"OfficialAccount",(function(){return W})),t.d(e,"OpenData",(function(){return _})),t.d(e,"WebView",(function(){return Z})),t.d(e,"NavigationBar",(function(){return q})),t.d(e,"Block",(function(){return z}));const a=reactify_wc("taro-view-core"),u=reactify_wc("taro-icon-core"),f=reactify_wc("taro-progress-core"),s=reactify_wc("taro-rich-text-core"),d=reactify_wc("taro-text-core"),l=reactify_wc("taro-button-core"),p=reactify_wc("taro-checkbox-core"),h=reactify_wc("taro-checkbox-group-core"),v=reactify_wc("taro-editor-core"),m=reactify_wc("taro-form-core"),w=i,b=reactify_wc("taro-label-core"),g=reactify_wc("taro-picker-core"),y=reactify_wc("taro-picker-view-core"),x=reactify_wc("taro-picker-view-column-core"),I=reactify_wc("taro-radio-core"),M=reactify_wc("taro-radio-group-core"),E=reactify_wc("taro-slider-core"),O=reactify_wc("taro-switch-core"),S=reactify_wc("taro-cover-image-core"),k=reactify_wc("taro-textarea-core"),C=reactify_wc("taro-cover-view-core"),N=reactify_wc("taro-moveable-area-core"),j=reactify_wc("taro-moveable-view-core"),A=reactify_wc("taro-scroll-view-core"),P=reactify_wc("taro-swiper-core"),L=reactify_wc("taro-swiper-item-core"),V=reactify_wc("taro-functional-page-navigator-core"),H=reactify_wc("taro-navigator-core"),R=reactify_wc("taro-audio-core"),T=reactify_wc("taro-camera-core"),B=reactify_wc("taro-image-core"),D=reactify_wc("taro-live-player-core"),U=reactify_wc("taro-video-core"),F=reactify_wc("taro-map-core"),G=reactify_wc("taro-canvas-core"),J=reactify_wc("taro-ad-core"),W=reactify_wc("taro-official-account-core"),_=reactify_wc("taro-open-data-core"),Z=reactify_wc("taro-web-view-core"),q=reactify_wc("taro-navigation-bar-core"),z=reactify_wc("taro-block-core")},"320":function(r,e,t){"use strict";function parseUrl(r){var e,t,n=/\+/g,o=/([^&=]+)=?([^&]*)/g,c=r||window.location.href||"",i=function decode(r){return decodeURIComponent(r.replace(n," "))},a=c.indexOf("?"),u={};if(-1===a)return{};for(t=c.slice(a+1,c.length);e=o.exec(t);)u[i(e[1])]=i(e[2]);return u}function formatMoneyPoint(r,e){if(e=e||2,isNaN(r))return"0";if(0===r||0===Number(r))return"0.00";if(-1===(r=r.toString()).indexOf("."))return r+"."+"".padEnd(e,"0");var t=r.split(".");return t[1].length>e?r=function roundNum(r,e){return _.round(r,e)}(Number(r),e).toString():t[0]+"."+t[1].padEnd(e,"0")}function calcAdd(r,e){var t,n,o;try{t=r.toString().split(".")[1].length}catch(r){t=0}try{n=e.toString().split(".")[1].length}catch(r){n=0}return(calcMul(r,o=Math.pow(10,Math.max(t,n)))+calcMul(e,o))/o}function calcMul(r,e){e=void 0===e?0:e;var t=0,n=(r=void 0===r?0:r).toString(),o=e.toString();try{t+=n.split(".")[1].length}catch(r){}try{t+=o.split(".")[1].length}catch(r){}return Number(n.replace(".",""))*Number(o.replace(".",""))/Math.pow(10,t)}function formatMoney(r,e){var t=2;return e&&(t=e),function _formatMoney(r){var e=r.toString().split(".");return e[0]=e[0].replace(/\B(?=(\d{3})+(?!\d))/g,","),e.join(".")}(formatMoneyPoint(r,t))}t.d(e,"d",(function(){return parseUrl})),t.d(e,"c",(function(){return formatMoneyPoint})),t.d(e,"a",(function(){return calcAdd})),t.d(e,"b",(function(){return formatMoney}))},"336":function(r,e,t){"use strict";t.r(e);var n=t(2),o=t(4),c=t(11),i=t(12),a=t(0),u=t.n(a),f=t(319),s=t(320),d=function(r){Object(c.a)(Index,r);var e=Object(i.a)(Index);function Index(){return Object(n.a)(this,Index),e.apply(this,arguments)}return Object(o.a)(Index,[{"key":"componentDidMount","value":function componentDidMount(){var r="/wx/getPreCode?",e=Object.assign({"appId":"","username":"","dbid":"","tenantid":"","appid":""},Object(s.d)());for(var t in e.appid=e.appId,delete e.appId,e)r+="".concat(t,"=").concat(e[t],"&");window.location.href=r}},{"key":"render","value":function render(){return u.a.createElement(f.View,{"className":"index"})}}]),Index}(a.Component);e.default=d}}]);