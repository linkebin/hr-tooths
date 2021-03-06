/*--主要定义以下模块--*/
/*--1、导航/面包屑  2、TAB页---*/

;eui.define("jquery",function(i) {
     "use strict";


    //右侧IFRAME点击关闭TAB标签页右键内容
    //  document.addEventListener('click', function (event) {if(window.parent.document.getElementById("tabContextmenu"))window.parent.closeRC();}, false);




//-------------------------------------------------------------
     var a = eui.$,
         t = (eui.hint(), eui.device()),
         l = "element",
         e = "eui-this",
         n = "eui-show",
         s = function() {
          this.config = {}
         };
     s.prototype.set = function(i) {
      var t = this;
      return a.extend(!0, t.config, i),
          t
     },
     s.prototype.on = function(i, a) {
          return eui.onevent.call(this, l, i, a)
     },
     s.prototype.tabAdd = function(i, t) {
          var l = ".eui-tab-title",
              e = a(".eui-tab[e-filter=" + i + "]"),
              n = e.children(l),
              s = e.children(".eui-tab-content");
          return n.append('<li e-id="' + (t.id || "") + '">' + (t.title || "unnaming") + "</li>"),
              s.append('<div class="eui-tab-item">' + (t.content || "") + "</div>"),
              y.hideTabMore(!0),
              y.tabAuto(),
              this
     },
     s.prototype.tabDelete = function(i, t) {
          var l = ".eui-tab-title",
              e = a(".eui-tab[e-filter=" + i + "]"),
              n = e.children(l),
              s = n.find('>li[e-id="' + t + '"]');
          return y.tabDelete(null, s),
              this
     },
     s.prototype.tabChange = function(i, t) {
          var l = ".eui-tab-title",
              e = a(".eui-tab[e-filter=" + i + "]"),
              n = e.children(l),
              s = n.find('>li[e-id="' + t + '"]');
          return y.tabClick(null, null, s),
              this
     },
     s.prototype.progress = function(i, t) {
          var l = "eui-progress",
              e = a("." + l + "[e-filter=" + i + "]"),
              n = e.find("." + l + "-bar"),
              s = n.find("." + l + "-text");
          return n.css("width", t),
              s.text(t),
              this
     };

     var o = ".eui-nav",
         c = "eui-nav-item",
         r = "eui-nav-bar",
         u = "eui-nav-tree",
         d = "eui-nav-child",
         h = "eui-nav-more",
         f = "eui-anim eui-anim-upbit",
         y = {
          tabClick: function(i, t, s) {
           var o = s || a(this),
               t = t || o.parent().children("li").index(o),
               c = o.parents(".eui-tab").eq(0),
               r = c.children(".eui-tab-content").children(".eui-tab-item"),
               u = o.find("a"),
               d = c.attr("e-filter");
           "javascript:;" !== u.attr("href") && "_blank" === u.attr("target") || (o.addClass(e).siblings().removeClass(e), r.eq(t).addClass(n).siblings().removeClass(n)),
               eui.event.call(this, l, "tab(" + d + ")", {
                elem: c,
                index: t
               })
          },
          tabDelete: function(i, t) {
           var l = t || a(this).parent(),
               n = l.index(),
               s = l.parents(".eui-tab").eq(0),
               o = s.children(".eui-tab-content").children(".eui-tab-item");
           l.hasClass(e) && (l.next()[0] ? y.tabClick.call(l.next()[0], null, n + 1) : l.prev()[0] && y.tabClick.call(l.prev()[0], null, n - 1)),
               l.remove(),
               o.eq(n).remove(),
               setTimeout(function() {
                    y.tabAuto()
                   },
                   50)
          },
          tabAuto: function() {
           var i = "eui-tab-more",
               l = "eui-tab-bar",
               e = "eui-tab-close",
               n = this;
           a(".eui-tab").each(function() {
            var s = a(this),
                o = s.children(".eui-tab-title"),
                c = (s.children(".eui-tab-content").children(".eui-tab-item"), 'e-stope="tabmore"'),
                r = a('<span class="eui-unselect eui-tab-bar" ' + c + "><i " + c + ' class="eui-icon">&#xe61a;</i></span>');
            if (n === window && 8 != t.ie && y.hideTabMore(!0), s.attr("e-allowClose") && o.find("li").each(function() {
                 var i = a(this);
                 if (!i.find("." + e)[0]) {
                  var t = a('<i class="eui-icon eui-unselect ' + e + '">&#x1006;</i>');
                  t.on("click", y.tabDelete),
                      i.append(t)
                 }
                }), o.prop("scrollWidth") > o.outerWidth() + 1) {
             if (o.find("." + l)[0]) return;
             o.append(r),
                 s.attr("overflow", ""),
                 r.on("click",
                     function(a) {
                      o[this.title ? "removeClass": "addClass"](i),
                          this.title = this.title ? "": "收缩"
                     })
            } else o.find("." + l).remove(),
                s.removeAttr("overflow")
           })
          },
          hideTabMore: function(i) {
           var t = a(".eui-tab-title");
           i !== !0 && "tabmore" === a(i.target).attr("e-stope") || (t.removeClass("eui-tab-more"), t.find(".eui-tab-bar").attr("title", ""))
          },
          clickThis: function() {
           var i = a(this),
               t = i.parents(o),
               n = t.attr("e-filter"),
               s = i.find("a");
           i.find("." + d)[0] || ("javascript:;" !== s.attr("href") && "_blank" === s.attr("target") || (t.find("." + e).removeClass(e), i.addClass(e)), eui.event.call(this, l, "nav(" + n + ")", i))
          },
          clickChild: function() {
           var i = a(this),
               t = i.parents(o),
               n = t.attr("e-filter");
           t.find("." + e).removeClass(e),
               i.addClass(e),
               eui.event.call(this, l, "nav(" + n + ")", i)
          },
          showChild: function() {
           var i = a(this),
               t = i.parents(o),
               l = i.parent(),
               e = i.siblings("." + d);
           t.hasClass(u) && (e.removeClass(f), l["none" === e.css("display") ? "addClass": "removeClass"](c + "ed"))
          },
          collapse: function() {
           var i = a(this),
               t = i.find(".eui-colla-icon"),
               e = i.siblings(".eui-colla-content"),
               s = i.parents(".eui-collapse").eq(0),
               o = s.attr("e-filter"),
               c = "none" === e.css("display");
           if ("string" == typeof s.attr("e-accordion")) {
            var r = s.children(".eui-colla-item").children("." + n);
            r.siblings(".eui-colla-title").children(".eui-colla-icon").html("&#xe602;"),
                r.removeClass(n)
           }
           e[c ? "addClass": "removeClass"](n),
               t.html(c ? "&#xe61a;": "&#xe602;"),
               eui.event.call(this, l, "collapse(" + o + ")", {
                title: i,
                content: e,
                show: c
               })
          }
         };
     s.prototype.init = function(i) {
      var l = {
       tab: function() {
        y.tabAuto.call({})
       },
       nav: function() {
        var i = 200,
            l = {},
            e = {},
            s = {},
            p = function(o, c, r) {
             var y = a(this),
                 p = y.find("." + d);
             c.hasClass(u) ? o.css({
              top: y.position().top,
              height: y.children("a").height(),
              opacity: 1
             }) : (p.addClass(f), o.css({
              left: y.position().left + parseFloat(y.css("marginLeft")),
              top: y.position().top + y.height() - 5
             }), l[r] = setTimeout(function() {
                  o.css({
                   width: y.width(),
                   opacity: 1
                  })
                 },
                 t.ie && t.ie < 10 ? 0 : i), clearTimeout(s[r]), "block" === p.css("display") && clearTimeout(e[r]), e[r] = setTimeout(function() {
                  p.addClass(n),
                      y.find("." + h).addClass(h + "d")
                 },
                 300))
            };
        a(o).each(function(t) {
         var o = a(this),
             f = a('<span class="' + r + '"></span>'),
             v = o.find("." + c);
         o.find("." + r)[0] || (o.append(f), v.on("mouseenter",
             function() {
              p.call(this, f, o, t)
             }).on("mouseleave",
             function() {
              o.hasClass(u) || (clearTimeout(e[t]), e[t] = setTimeout(function() {
                   o.find("." + d).removeClass(n),
                       o.find("." + h).removeClass(h + "d")
                  },
                  300))
             }), o.on("mouseleave",
             function() {
              clearTimeout(l[t]),
                  s[t] = setTimeout(function() {
                       o.hasClass(u) ? f.css({
                        height: 0,
                        top: f.position().top + f.height() / 2,
                        opacity: 0
                       }) : f.css({
                        width: 0,
                        left: f.position().left + f.width() / 2,
                        opacity: 0
                       })
                      },
                      i)
             })),
             v.each(function() {
              var i = a(this),
                  t = i.find("." + d);
              if (t[0] && !i.find("." + h)[0]) {
               var l = i.children("a");
               l.append('<span class="' + h + '"></span>')
              }
              i.off("click", y.clickThis).on("click", y.clickThis),
                  i.children("a").off("click", y.showChild).on("click", y.showChild),
                  t.children("dd").off("click", y.clickChild).on("click", y.clickChild)
             })
        })
       },
       breadcrumb: function() {
        var i = ".eui-breadcrumb";
        a(i).each(function() {
         var i = a(this),
             t = i.attr("e-separator") || ">",
             l = i.find("a");
         l.find(".eui-box")[0] || (l.each(function(i) {
          i !== l.length - 1 && a(this).append('<span class="eui-box">' + t + "</span>")
         }), i.css("visibility", "visible"))
        })
       },
       progress: function() {
        var i = "eui-progress";
        a("." + i).each(function() {
         var t = a(this),
             l = t.find(".eui-progress-bar"),
             e = l.attr("e-percent");
         l.css("width", e),
         t.attr("e-showPercent") && setTimeout(function() {
              var a = Math.round(l.width() / t.width() * 100);
              a > 100 && (a = 100),
                  l.html('<span class="' + i + '-text">' + a + "%</span>")
             },
             350)
        })
       },
       collapse: function() {
        var i = "eui-collapse";
        a("." + i).each(function() {
         var i = a(this).find(".eui-colla-item");
         i.each(function() {
          var i = a(this),
              t = i.find(".eui-colla-title"),
              l = i.find(".eui-colla-content"),
              e = "none" === l.css("display");
          t.find(".eui-colla-icon").remove(),
              t.append('<i class="eui-icon eui-colla-icon">' + (e ? "&#xe602;": "&#xe61a;") + "</i>"),
              t.off("click", y.collapse).on("click", y.collapse)
         })
        })
       }
      };
      return eui.each(l,
          function(i, a) {
           a()
          })
     };
     var p = new s,
         v = a(document);
     p.init();
     var b = ".eui-tab-title li";
         v.on("click", b, y.tabClick),
         v.on("click", y.hideTabMore),
         a(window).on("resize", y.tabAuto),
         i(l, p)
   //--------------------------------------



    });