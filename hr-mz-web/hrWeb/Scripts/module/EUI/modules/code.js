/** eui-v2.0.0  */
 ;eui.define("jquery",function(e){"use strict";var a=eui.$,l="http://www.layui.com/doc/modules/code.html";e("code",function(e){var t=[];e=e||{},e.elem=a(e.elem||".eui-code"),e.about=!("about"in e)||e.about,e.elem.each(function(){t.push(this)}),eui.each(t.reverse(),function(t,i){var c=a(i),o=c.html();(c.attr("e-encode")||e.encode)&&(o=o.replace(/&(?!#?[a-zA-Z0-9]+;)/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/'/g,"&#39;").replace(/"/g,"&quot;")),c.html('<ol class="eui-code-ol"><li>'+o.replace(/[\r\t\n]+/g,"</li><li>")+"</li></ol>"),c.find(">.eui-code-h3")[0]||c.prepend('<h3 class="eui-code-h3">'+(c.attr("e-title")||e.title||"code")+(e.about?'<a href="'+l+'" target="_blank">eui.code</a>':"")+"</h3>");var d=c.find(">.eui-code-ol");c.addClass("eui-box eui-code-view"),(c.attr("e-skin")||e.skin)&&c.addClass("eui-code-"+(c.attr("e-skin")||e.skin)),(d.find("li").length/100|0)>0&&d.css("margin-left",(d.find("li").length/100|0)+"px"),(c.attr("e-height")||e.height)&&d.css("max-height",c.attr("e-height")||e.height)})})}).addcss("modules/code.css","skincodecss");