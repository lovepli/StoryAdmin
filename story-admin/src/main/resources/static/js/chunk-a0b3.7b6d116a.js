(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-a0b3"],{YXOD:function(t,o,a){t.exports=a.p+"static/img/rotate1.c718fc3.jpg"},g8rT:function(t,o,a){"use strict";var e=a("itPt");a.n(e).a},itPt:function(t,o,a){},zNDm:function(t,o,a){"use strict";a.r(o);var e=[function(){var t=this,o=t.$createElement,e=t._self._c||o;return e("div",[e("div",{staticClass:"header"},[e("h2",[t._v("图形编辑(平移,旋转,缩放,翻转)")]),t._v(" "),e("p",[t._v("示例博客链接"),e("a",{attrs:{href:"https://www.cnblogs.com/steamed-twisted-roll/p/13408245.html",target:"_blank"}},[t._v("https://www.cnblogs.com/steamed-twisted-roll/p/13408245.html")])])]),t._v(" "),e("div",{staticClass:"container"},[e("div",{staticClass:"box"},[e("div",{staticClass:"img-box"},[e("div",{staticClass:"flat"},[t._v("翻转")]),t._v(" "),e("div",{staticClass:"rotate"},[t._v("旋转")]),t._v(" "),e("div",{staticClass:"rotate1"},[t._v("旋转")]),t._v(" "),e("img",{staticClass:"img",attrs:{src:a("YXOD"),alt:""}})])])])])}],n=a("ozZp"),s=a.n(n),i={mounted:function(){this.initImage()},methods:{initImage:function(){var t=-1;s()(".flat").click(function(){s()(".img").css("transform","scaleX("+t+")"),t=-t});var o={X:s()(".box").width()/2+s()(".box").offset().left,Y:s()(".box").height()/2+s()(".box").offset().top};console.log(o,s()(".box").position());var a={},e={},n=!1,i=!1,r=0,c=0,l={},p={count:0},u={target:null,angle:0};s()(".rotate, .rotate1").on("mousedown",function(t){t.preventDefault(),t.stopPropagation();var i=s()(".box").width()/s()(".box").height(),g=Math.round(180*Math.atan(i)/Math.PI);u.target&&u.target!==t.currentTarget?t.currentTarget===s()(".rotate")[0]?u.angle=2*g:u.angle=-2*g:u.angle=0,n=!0,c<1&&(a.X=t.pageX,a.Y=t.pageY,p.count=0,u.target=t.currentTarget,c++),l.flag&&(a.X+=l.X,a.Y+=l.Y,l.flag=!1,p.count=0),console.log(5,o,a),s()(document).on("mousemove",function(t){if(t.preventDefault(),n){e.X=t.pageX,e.Y=t.pageY;var i=a.X-o.X,c=a.Y-o.Y,l=e.X-o.X,p=e.Y-o.Y,g={},f={};g.X=a.X-o.X,g.Y=a.Y-o.Y,f.X=e.X-o.X,f.Y=e.Y-o.Y;var h=g.X*f.Y-g.Y*f.X,v=Math.sqrt(Math.pow(o.X-a.X,2)+Math.pow(o.Y-a.Y,2)),X=Math.sqrt(Math.pow(o.X-e.X,2)+Math.pow(o.Y-e.Y,2)),Y=Math.sqrt(Math.pow(a.X-e.X,2)+Math.pow(a.Y-e.Y,2)),m=(Math.pow(v,2)+Math.pow(X,2)-Math.pow(Y,2))/(2*v*X),d=Math.round(180*Math.acos(m)/Math.PI);r=h<0?-d:d,r+=u.angle;var w=Math.sqrt(i*i+c*c),b=Math.sqrt(l*l+p*p)/w;s()(".img-box").css("transform","rotate("+r+"deg) scale("+b+")")}})}),s()(".img").mousedown(function(t){t.preventDefault(),t.stopPropagation(),i=!0,p.count<1&&(p={X:o.X,Y:o.Y,count:1});var a=t.pageX-s()(".box").position().left,e=t.pageY-s()(".box").position().top;s()(document).on("mousemove",function(t){if(t.preventDefault(),t.stopPropagation(),i){var n={};n.X=t.pageX-a,n.Y=t.pageY-e,s()(".box").css({left:n.X,top:n.Y}),o={X:s()(".box").width()/2+s()(".box").offset().left,Y:s()(".box").height()/2+s()(".box").offset().top},c>0&&(l.X=o.X-p.X,l.Y=o.Y-p.Y,l.flag=!0)}})}),s()(document).on("mouseup",function(t){n=!1,i=!1})}}},r=(a("g8rT"),a("ZrdR")),c=Object(r.a)(i,function(){this.$createElement;this._self._c;return this._m(0)},e,!1,null,"d57e0b48",null);c.options.__file="editImage.vue";o.default=c.exports}}]);