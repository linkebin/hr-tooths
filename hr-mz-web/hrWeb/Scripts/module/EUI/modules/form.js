/** eui-v2.0.0  */
;
eui.define("layer",
    function(e) {
     "use strict";
     var i = eui.$,
         t = eui.layer,
         a = eui.hint(),
         n = eui.device(),
         l = "form",
         s = ".eui-form",
         r = "eui-this",
         u = "eui-hide",
         c = "eui-disabled",
         o = function() {
          this.config = {
           verify: {
            required: [/[\S]+/, "必填项不能为空"],
            phone: [/^1\d{10}$/, "请输入正确的手机号"],
            email: [/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/, "邮箱格式不正确"],
            url: [/(^#)|(^http(s*):\/\/[^\s]+\.[^\s]+)/, "链接格式不正确"],
            number: [/^\d+$/, "只能填写数字"],
            date: [/^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/, "日期格式不正确"],
            identity: [/(^\d{15}$)|(^\d{17}(x|X|\d)$)/, "请输入正确的身份证号"]
           }
          }
         };
     o.prototype.set = function(e) {
      var t = this;
      return i.extend(!0, t.config, e),
          t
     },
         o.prototype.verify = function(e) {
          var t = this;
          return i.extend(!0, t.config.verify, e),
              t
         },
         o.prototype.on = function(e, i) {
          return eui.onevent.call(this, l, e, i)
         },
         o.prototype.render = function(e, t) {
          var n = this,
              o = i(s +
                  function() {
                   return t ? '[e-filter="' + t + '"]': ""
                  } ()),
              d = {
               select: function() {
                var e, t = "请选择",
                    a = "eui-form-select",
                    n = "eui-select-title",
                    s = "eui-select-none",
                    d = "",
                    f = o.find("select"),
                    y = function(t, l) {
                     i(t.target).parent().hasClass(n) && !l || (i("." + a).removeClass(a + "ed " + a + "up"), e && d && e.val(d)),
                         e = null
                    },
                    h = function(t, o, f) {
                     var h = i(this),
                         p = t.find("." + n),
                         m = p.find("input"),
                         k = t.find("dl"),
                         g = k.children("dd");
                     if (!o) {
                      var b = function() {
                           var e = t.offset().top + t.outerHeight() + 5 - v.scrollTop(),
                               i = k.outerHeight();
                           t.addClass(a + "ed"),
                               g.removeClass(u),
                           e + i > v.height() && e >= i && t.addClass(a + "up")
                          },
                          x = function(e) {
                           t.removeClass(a + "ed " + a + "up"),
                               m.blur(),
                           e || C(m.val(),
                               function(e) {
                                e && (d = k.find("." + r).html(), m && m.val(d))
                               })
                          };
                      p.on("click",
                          function(e) {
                           t.hasClass(a + "ed") ? x() : (y(e, !0), b()),
                               k.find("." + s).remove()
                          }),
                          p.find(".eui-edge").on("click",
                              function() {
                               m.focus()
                              }),
                          m.on("keyup",
                              function(e) {
                               var i = e.keyCode;
                               9 === i && b()
                              }).on("keydown",
                              function(e) {
                               var i = e.keyCode;
                               9 === i ? x() : 13 === i && e.preventDefault()
                              });
                      var C = function(e, t, a) {
                           var n = 0;
                           eui.each(g,
                               function() {
                                var t = i(this),
                                    l = t.text(),
                                    s = l.indexOf(e) === -1; ("" === e || "blur" === a ? e !== l: s) && n++,
                                "keyup" === a && t[s ? "addClass": "removeClass"](u)
                               });
                           var l = n === g.length;
                           return t(l),
                               l
                          },
                          w = function(e) {
                           var i = this.value,
                               t = e.keyCode;
                           return 9 !== t && 13 !== t && 37 !== t && 38 !== t && 39 !== t && 40 !== t && (C(i,
                                   function(e) {
                                    e ? k.find("." + s)[0] || k.append('<p class="' + s + '">无匹配项</p>') : k.find("." + s).remove()
                                   },
                                   "keyup"), void("" === i && k.find("." + s).remove()))
                          };
                      f && m.on("keyup", w).on("blur",
                          function(i) {
                           e = m,
                               d = k.find("." + r).html(),
                               setTimeout(function() {
                                    C(m.val(),
                                        function(e) {
                                         e && !d && m.val("")
                                        },
                                        "blur")
                                   },
                                   200)
                          }),
                          g.on("click",
                              function() {
                               var e = i(this),
                                   a = e.attr("e-value"),
                                   n = h.attr("e-filter");
                               return ! e.hasClass(c) && (e.hasClass("eui-select-tips") ? m.val("") : (m.val(e.text()), e.addClass(r)), e.siblings().removeClass(r), h.val(a).removeClass("eui-form-danger"), eui.event.call(this, l, "select(" + n + ")", {
                                    elem: h[0],
                                    value: a,
                                    othis: t
                                   }), x(!0), !1)
                              }),
                          t.find("dl>dt").on("click",
                              function(e) {
                               return ! 1
                              }),
                          i(document).off("click", y).on("click", y)
                     }
                    };
                f.each(function(e, l) {
                 var s = i(this),
                     u = s.next("." + a),
                     o = this.disabled,
                     d = l.value,
                     f = i(l.options[l.selectedIndex]),
                     y = l.options[0];
                 if ("string" == typeof s.attr("e-ignore")) return s.show();
                 var v = "string" == typeof s.attr("e-search"),
                     p = y ? y.value ? t: y.innerHTML || t: t,
                     m = i(['<div class="eui-unselect ' + a + (o ? " eui-select-disabled": "") + '">', '<div class="' + n + '"><input type="text" placeholder="' + p + '" value="' + (d ? f.html() : "") + '" ' + (v ? "": "readonly") + ' class="eui-input eui-unselect' + (o ? " " + c: "") + '">', '<i class="eui-edge"></i></div>', '<dl class="eui-anim eui-anim-upbit' + (s.find("optgroup")[0] ? " eui-select-group": "") + '">' +
                     function(e) {
                      var i = [];
                      return eui.each(e,
                          function(e, a) {
                           0 !== e || a.value ? "optgroup" === a.tagName.toLowerCase() ? i.push("<dt>" + a.label + "</dt>") : i.push('<dd e-value="' + a.value + '" class="' + (d === a.value ? r: "") + (a.disabled ? " " + c: "") + '">' + a.innerHTML + "</dd>") : i.push('<dd e-value="" class="eui-select-tips">' + (a.innerHTML || t) + "</dd>")
                          }),
                      0 === i.length && i.push('<dd e-value="" class="' + c + '">没有选项</dd>'),
                          i.join("")
                     } (s.find("*")) + "</dl>", "</div>"].join(""));
                 u[0] && u.remove(),
                     s.after(m),
                     h.call(this, m, o, v)
                })
               },
               checkbox: function() {
                var e = {
                     checkbox: ["eui-form-checkbox", "eui-form-checked", "checkbox"],
                     _switch: ["eui-form-switch", "eui-form-onswitch", "switch"]
                    },
                    t = o.find("input[type=checkbox]"),
                    a = function(e, t) {
                     var a = i(this);
                     e.on("click",
                         function() {
                          var i = a.attr("e-filter"),
                              n = (a.attr("e-text") || "").split("|");
                          a[0].disabled || (a[0].checked ? (a[0].checked = !1, e.removeClass(t[1]).find("em").text(n[1])) : (a[0].checked = !0, e.addClass(t[1]).find("em").text(n[0])), eui.event.call(a[0], l, t[2] + "(" + i + ")", {
                           elem: a[0],
                           value: a[0].value,
                           othis: e
                          }))
                         })
                    };
                t.each(function(t, n) {
                 var l = i(this),
                     s = l.attr("e-skin"),
                     r = (l.attr("e-text") || "").split("|"),
                     u = this.disabled;
                 "switch" === s && (s = "_" + s);
                 var o = e[s] || e.checkbox;
                 if ("string" == typeof l.attr("e-ignore")) return l.show();
                 var d = l.next("." + o[0]),
                     f = i(['<span class="eui-unselect ' + o[0] + (n.checked ? " " + o[1] : "") + (u ? " eui-checkbox-disbaled " + c: "") + '" e-skin="' + (s || "") + '">', {
                      _switch: "<em>" + ((n.checked ? r[0] : r[1]) || "") + "</em><i></i>"
                     } [s] || (n.title.replace(/\s/g, "") ? "<span>" + n.title + "</span>": "") + '<i class="eui-icon">' + (s ? "&#xe605;": "&#xe618;") + "</i>", "</span>"].join(""));
                    /*    f = i(['<div class="eui-unselect ' + o[0] + (n.checked ? " " + o[1] : "") + (u ? " eui-checkbox-disbaled " + c: "") + '" e-skin="' + (s || "") + '">', {
                      _switch: "<em>" + ((n.checked ? r[0] : r[1]) || "") + "</em><i></i>"
                     } [s] || (n.title.replace(/\s/g, "") ? "<span>" + n.title + "</span>": "") + '<i class="eui-icon">' + (s ? "&#xe605;": "&#xe618;") + "</i>", "</div>"].join(""));*/
                 d[0] && d.remove(),
                     l.after(f),
                     a.call(this, f, o)
                })
               },
               radio: function() {
                var e = "eui-form-radio",
                    t = ["&#xe643;", "&#xe63f;"],
                    a = o.find("input[type=radio]"),
                    n = function(a) {
                     var n = i(this),
                         r = "eui-anim-scaleSpring";
                     a.on("click",
                         function() {
                          var u = n[0].name,
                              c = n.parents(s),
                              o = n.attr("e-filter"),
                              d = c.find("input[name=" + u.replace(/(\.|#|\[|\])/g, "\\$1") + "]");
                          n[0].disabled || (eui.each(d,
                              function() {
                               var a = i(this).next("." + e);
                               this.checked = !1,
                                   a.removeClass(e + "ed"),
                                   a.find(".eui-icon").removeClass(r).html(t[1])
                              }), n[0].checked = !0, a.addClass(e + "ed"), a.find(".eui-icon").addClass(r).html(t[0]), eui.event.call(n[0], l, "radio(" + o + ")", {
                           elem: n[0],
                           value: n[0].value,
                           othis: a
                          }))
                         })
                    };
                a.each(function(a, l) {
                 var s = i(this),
                     r = s.next("." + e),
                     u = this.disabled;
                 if ("string" == typeof s.attr("e-ignore")) return s.show();
                 var o = i(['<span class="eui-unselect ' + e + (l.checked ? " " + e + "ed": "") + (u ? " eui-radio-disbaled " + c: "") + '">', '<i class="eui-anim eui-icon">' + t[l.checked ? 0 : 1] + "</i>", "<span>" + (l.title || "未命名") + "</span>", "</span>"].join(""));
                 r[0] && r.remove(),
                     s.after(o),
                     n.call(this, o)
                })
               }
              };
          return e ? d[e] ? d[e]() : a.error("不支持的" + e + "表单渲染") : eui.each(d,
              function(e, i) {
               i()
              }),
              n
         };
     var d = function() {
          var e = i(this),
              a = f.config.verify,
              r = null,
              u = "eui-form-danger",
              c = {},
              o = e.parents(s),
              d = o.find("*[e-verify]"),
              y = e.parents("form")[0],
              v = o.find("input,select,textarea"),
              h = e.attr("e-filter");
          return eui.each(d,
              function(e, l) {
               var s = i(this),
                   c = s.attr("e-verify").split("|"),
                   o = "",
                   d = s.val();
               if (s.removeClass(u), eui.each(c,
                       function(e, i) {
                        var c = "function" == typeof a[i];
                        if (a[i] && (c ? o = a[i](d, l) : !a[i][0].test(d))) return t.msg(o || a[i][1], {
                         icon: 5,
                         shift: 6
                        }),
                        n.android || n.ios || l.focus(),
                            s.addClass(u),
                            r = !0
                       }), r) return r
              }),
          !r && (eui.each(v,
              function(e, i) {
               i.name && (/^checkbox|radio$/.test(i.type) && !i.checked || (c[i.name] = i.value))
              }), eui.event.call(this, l, "submit(" + h + ")", {
           elem: this,
           form: y,
           field: c
          }))
         },
         f = new o,
         y = i(document),
         v = i(window);
     f.render(),
         y.on("reset", s,
             function() {
              var e = i(this).attr("e-filter");
              setTimeout(function() {
                   f.render(null, e)
                  },
                  50)
             }),
         y.on("submit", s, d).on("click", "*[e-submit]", d),
         e(l, f)
    });