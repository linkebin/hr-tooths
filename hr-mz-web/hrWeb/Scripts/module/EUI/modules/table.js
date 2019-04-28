/** 2.2.5 */
;
eui.define(["laytpl", "laypage", "layer", "form"],
    function(e) {
        "use strict";
        var t = eui.$,
            i = eui.laytpl,
            a = eui.laypage,
            l = eui.layer,
            n = eui.form,
            o = eui.hint(),
            r = eui.device(),
            d = {
                config: {
                    checkName: "LAY_CHECKED",
                    indexName: "LAY_TABLE_INDEX"
                },
                cache: {},
                index: eui.table ? eui.table.index + 1e4: 0,
                set: function(e) {
                    var i = this;
                    return i.config = t.extend({},
                        i.config, e),
                        i
                },
                on: function(e, t) {
                    return eui.onevent.call(this, s, e, t)
                }
            },
            c = function() {
                var e = this,
                    t = e.config,
                    i = t.id;
                return i && (c.config[i] = t),
                {
                    reload: function(t) {
                        e.reload.call(e, t)
                    },
                    config: t
                }
            },
            s = "table",
            u = ".eui-table",
            h = "eui-hide",
            f = "eui-none",
            y = "eui-table-view",
            p = ".eui-table-header",
            m = ".eui-table-body",
            v = ".eui-table-main",
            g = ".eui-table-fixed",
            x = ".eui-table-fixed-l",
            b = ".eui-table-fixed-r",
            k = ".eui-table-tool",
            C = ".eui-table-page",
            w = ".eui-table-sort",
            N = "eui-table-edit",
            F = "eui-table-hover",
            W = function(e) {
                var t = '{{#if(item2.colspan){}} colspan="{{item2.colspan}}"{{#} if(item2.rowspan){}} rowspan="{{item2.rowspan}}"{{#}}}';
                return e = e || {},
                    ['<table cellspacing="0" cellpadding="0" border="0" class="eui-table" ',
                        '{{# if(d.data.skin){ }}e-skin="{{d.data.skin}}"{{# } }} {{# if(d.data.size){ }}e-size="{{d.data.size}}"{{# } }} {{# if(d.data.even){ }}e-even{{# } }}>',
                        "<thead>",
                            "{{# eui.each(d.data.cols, function(i1, item1){ }}",
                            "<tr>",
                            "{{# eui.each(item1, function(i2, item2){ }}",
                            '{{# if(item2.fixed && item2.fixed !== "right"){ left = true; } }}',
                            '{{# if(item2.fixed === "right"){ right = true; } }}',


                        function() {
                            return e.fixed && "right" !== e.fixed ? '{{# if(item2.fixed && item2.fixed !== "right"){ }}': "right" === e.fixed ? '{{# if(item2.fixed === "right"){ }}': ""
                        } (),

                        '<th data-field="{{ item2.field||i2 }}" {{# if(item2.minWidth){ }}data-minwidth="{{item2.minWidth}}"{{# } }} ' + t + ' {{# if(item2.unresize){ }}data-unresize="true"{{# } }}>',
                        '<div class="eui-table-cell laytable-cell-', "{{# if(item2.colspan > 1){ }}",
                        "group",
                        "{{# } else { }}",
                        "{{d.index}}-{{item2.field || i2}}",
                        '{{# if(item2.type !== "normal"){ }}',
                        " laytable-cell-{{ item2.type }}",
                        "{{# } }}",
                        "{{# } }}",
                        '" {{#if(item2.align){}}align="{{item2.align}}"{{#}}}>', //检查一下是不是有问题

                        '{{# if(item2.type === "checkbox"){ }}',
                        '<input type="checkbox" name="layTableCheckbox" e-skin="primary" e-filter="layTableAllChoose" {{# if(item2[d.data.checkName]){ }}checked{{# }; }}>',
                        "{{# } else { }}",
                        '<span>{{item2.title||""}}</span>',
                        "{{# if(!(item2.colspan > 1) && item2.sort){ }}",
                        '<span class="eui-table-sort eui-inline"><i class="eui-edge eui-table-sort-asc"></i><i class="eui-edge eui-table-sort-desc"></i></span>',
                        "{{# } }}",
                        "{{# } }}",
                        "</div>",
                        "</th>",
                        e.fixed ? "{{# }; }}": "",
                        "{{# }); }}",
                        "</tr>",
                        "{{# }); }}",
                        "</thead>",
                        "</table>"].join("")
            },




            z = ['<table cellspacing="0" cellpadding="0" border="0" class="eui-table" ', '{{# if(d.data.skin){ }}e-skin="{{d.data.skin}}"{{# } }} {{# if(d.data.size){ }}e-size="{{d.data.size}}"{{# } }} {{# if(d.data.even){ }}e-even{{# } }}>', "<tbody></tbody>", "</table>"].join(""),



            A = [
                '<div class="eui-form eui-border-box {{d.VIEW_CLASS}}" e-filter="LAY-table-{{d.index}}" style="{{# if(d.data.width){ }}width:{{d.data.width}}px;{{# } }} {{# if(d.data.height){ }}height:{{d.data.height}}px;{{# } }}">', "{{# if(d.data.toolbar){ }}",
                '<div class="eui-table-tool"></div>',
                "{{# } }}",

                '<div class="eui-table-box">',
                   "{{# var left, right; }}",
                    '<div class="eui-table-header">',
                       W(),
                    "</div>",
                    '<div class="eui-table-body eui-table-main">',
                       z,
                    "</div>",
                   "{{# if(left){ }}",
                    '<div class="eui-table-fixed eui-table-fixed-l">',
                        '<div class="eui-table-header">',
                            W({
                            fixed: !0
                            }),

                        "</div>",
                        '<div class="eui-table-body">',
                            z,
                        "</div>",
                    "</div>",
                    "{{# }; }}", "{{# if(right){ }}",
                    '<div class="eui-table-fixed eui-table-fixed-r">',
                        '<div class="eui-table-header">',
                            W({
                                fixed: "right"
                            }),
                            '<div class="eui-table-mend"></div>',
                        "</div>",
                        '<div class="eui-table-body">',
                            z,
                        "</div>",
                    "</div>",
                  "{{# }; }}",
                   "</div>",

                "{{# if(d.data.page){ }}",
                '<div class="eui-form eui-table-page">',
                '<div e-filter="table-page-{{d.index}}" id="eui-table-page{{d.index}}"></div>',
                "</div>",
                "{{# } }}",


                "<style>",
                "{{# eui.each(d.data.cols, function(i1, item1){",
                "eui.each(item1, function(i2, item2){ }}",
                ".laytable-cell-{{d.index}}-{{item2.field||i2}}{ ",
                "{{# if(item2.width){ }}", "width: {{item2.width}}px;",
                "{{# } }}", " }", "{{# });", "}); }}",
                "</style>",



                "</div>",



            ].join(""),




            T = t(window),
            M = t(document),
            S = function(e) {
                var i = this;
                i.index = ++d.index,
                    i.config = t.extend({},
                        i.config, d.config, e),
                    i.render()
            };
        S.prototype.config = {
            onLoaded:function(t){return false},
            limit: 10,
            loading: !0,
            cellMinWidth: 60,
            text: {
                none: "无数据"
            }
        },
            S.prototype.render = function() {
                var e = this,
                    a = e.config;
                if (a.elem = t(a.elem), a.where = a.where || {},
                        a.id = a.id || a.elem.attr("id"), a.request = t.extend({
                            pageName: "page",
                            limitName: "size"
                        },
                        a.request), a.response = t.extend({
                            statusName: "code",
                            statusCode: 200,
                            msgName: "message",
                            dataName: "data",
                            countName: "total"
                        },
                        a.response), "object" == typeof a.page && (a.limit = a.page.limit || a.limit, a.limits = a.page.limits || a.limits, e.page = a.page.curr = a.page.curr || 1, delete a.page.elem, delete a.page.jump), !a.elem[0]) return e;
                e.setArea();
                var l = a.elem,
                    n = l.next("." + y),
                    o = e.elem = t(i(A).render({
                        VIEW_CLASS: y,
                        data: a,
                        index: e.index
                    }));
                if (a.index = e.index, n[0] && n.remove(), l.after(o), e.layHeader = o.find(p), e.layMain = o.find(v), e.layBody = o.find(m), e.layFixed = o.find(g), e.layFixLeft = o.find(x), e.layFixRight = o.find(b), e.layTool = o.find(k), e.layPage = o.find(C), e.layTool.html(i(t(a.toolbar).html() || "").render(a)), a.height && e.fullSize(), a.cols.length > 1) {
                    var r = e.layFixed.find(p).find("th");
                    r.height(e.layHeader.height() - 1 - parseFloat(r.css("padding-top")) - parseFloat(r.css("padding-bottom")))
                }
                e.pullData(e.page),
                    e.events()
            },
            S.prototype.initOpts = function(e) {
                var t = this,
                    i = (t.config, {
                        checkbox: 48,
                        space: 15,
                        numbers: 40
                    });
                e.checkbox && (e.type = "checkbox"),
                e.space && (e.type = "space"),
                e.type || (e.type = "normal"),
                "normal" !== e.type && (e.unresize = !0, e.width = e.width || i[e.type])
            },
            S.prototype.setArea = function() {
                var e = this,
                    t = e.config,
                    i = 0,
                    a = 0,
                    l = 0,
                    n = 0,
                    o = t.width ||
                        function() {
                            var e = function(i) {
                                var a, l;
                                i = i || t.elem.parent(),
                                    a = i.width();
                                try {
                                    l = "none" === i.css("display")
                                } catch(n) {}
                                return ! i[0] || a && !l ? a: e(i.parent())
                            };
                            return e()
                        } ();
                e.eachCols(function() {
                    i++
                }),
                    o -=
                        function() {
                            return "line" === t.skin || "nob" === t.skin ? 2 : i + 1
                        } (),
                    eui.each(t.cols,
                        function(t, i) {
                            eui.each(i,
                                function(t, l) {
                                    var r;
                                    return l ? (e.initOpts(l), r = l.width || 0, void(l.colspan > 1 || (/\d+%$/.test(r) ? l.width = r = Math.floor(parseFloat(r) / 100 * o) : r || (l.width = r = 0, a++), n += r))) : void i.splice(t, 1)
                                })
                        }),
                    e.autoColNums = a,
                o > n && a && (l = (o - n) / a),
                    eui.each(t.cols,
                        function(e, i) {
                            eui.each(i,
                                function(e, i) {
                                    var a = i.minWidth || t.cellMinWidth;
                                    i.colspan > 1 || 0 === i.width && (i.width = Math.floor(l >= a ? l: a))
                                })
                        }),
                t.height && /^full-\d+$/.test(t.height) && (e.fullHeightGap = t.height.split("-")[1], t.height = T.height() - e.fullHeightGap)
            },
            S.prototype.reload = function(e) {
                var i = this;
                i.config.data && i.config.data.constructor === Array && delete i.config.data,
                    i.config = t.extend({},
                        i.config, e),
                    i.render()
            },
            S.prototype.page = 1,
            S.prototype.pullData = function(e, i) {
                var a = this,
                    n = a.config,
                    o = n.request,
                    r = n.response,
                    d = function() {
                        "object" == typeof n.initSort && a.sort(n.initSort.field, n.initSort.type)
                    };
                if (a.startTime = (new Date).getTime(), n.url) {
                    var c = {};
                    c[o.pageName] = e,
                        c[o.limitName] = n.limit,
                        t.ajax({
                            type: n.method || "get",
                            url: n.url,
                            data: t.extend(c, n.where),
                            dataType: "json",
                            success: function(t) {
                                t[r.statusName] != r.statusCode ? (a.renderForm(), a.layMain.html('<div class="' + f + '">' + (t[r.msgName] || "返回的数据状态异常") + "</div>")) : (a.renderData(t, e, t[r.countName]), d(), n.time = (new Date).getTime() - a.startTime + " ms"),
                                i && l.close(i),
                                "function" == typeof n.done && n.done(t, e, t[r.countName]);
                                a.config.onLoaded(t.data);
                            },
                            error: function(e, t) {
                                a.layMain.html('<div class="' + f + '">数据接口请求异常</div>'),
                                    a.renderForm(),
                                i && l.close(i)
                            }
                        })
                } else if (n.data && n.data.constructor === Array) {
                    var s = {},
                        u = e * n.limit - n.limit;
                    s[r.dataName] = n.data.concat().splice(u, n.limit),
                        s[r.countName] = n.data.length,
                        a.renderData(s, e, n.data.length),
                        d(),
                    "function" == typeof n.done && n.done(s, e, s[r.countName])
                }
            },
            S.prototype.eachCols = function(e) {
                var i = t.extend(!0, [], this.config.cols),
                    a = [],
                    l = 0;
                eui.each(i,
                    function(e, t) {
                        eui.each(t,
                            function(t, n) {
                                if (n.colspan > 1) {
                                    var o = 0;
                                    l++,
                                        n.CHILD_COLS = [],
                                        eui.each(i[e + 1],
                                            function(e, t) {
                                                t.PARENT_COL || o == n.colspan || (t.PARENT_COL = l, n.CHILD_COLS.push(t), o += t.colspan > 1 ? t.colspan: 1)
                                            })
                                }
                                n.PARENT_COL || a.push(n)
                            })
                    });
                var n = function(t) {
                    eui.each(t || a,
                        function(t, i) {
                            return i.CHILD_COLS ? n(i.CHILD_COLS) : void e(t, i)
                        })
                };
                n()
            },
            S.prototype.renderData = function(e, n, o, r) {
                var c = this,
                    s = c.config,
                    u = e[s.response.dataName] || [],
                    y = [],
                    p = [],
                    m = [],
                    v = function() {
                        return ! r && c.sortKey ? c.sort(c.sortKey.field, c.sortKey.sort, !0) : (eui.each(u,
                            function(e, a) {
                                var l = [],
                                    o = [],
                                    u = [],
                                    h = e + s.limit * (n - 1) + 1;
                                0 !== a.length && (r || (a[d.config.indexName] = e), c.eachCols(function(e, n) {
                                    var r = n.field || e,
                                        f = a[r];
                                    c.getColElem(c.layHeader, r);
                                    if (void 0 !== f && null !== f || (f = ""), !(n.colspan > 1)) {
                                        var y = ['<td data-field="' + r + '" ' +
                                        function() {
                                            var e = [];
                                            return n.edit && e.push('data-edit="' + n.edit + '"'),
                                            n.align && e.push('align="' + n.align + '"'),
                                            n.templet && e.push('data-content="' + f + '"'),
                                            n.toolbar && e.push('data-off="true"'),
                                            n.event && e.push('e-event="' + n.event + '"'),
                                            n.style && e.push('style="' + n.style + '"'),
                                            n.minWidth && e.push('data-minwidth="' + n.minWidth + '"'),
                                                e.join(" ")
                                        } () + ">", '<div class="eui-table-cell laytable-cell-' +
                                        function() {
                                            var e = s.index + "-" + r;
                                            return "normal" === n.type ? e: e + " laytable-cell-" + n.type
                                        } () + '">' +
                                        function() {
                                            var e = t.extend(!0, {
                                                    LAY_INDEX: h
                                                },
                                                a);
                                            return "checkbox" === n.type ? '<input type="checkbox" name="layTableCheckbox" e-skin="primary" ' +
                                            function() {
                                                var t = d.config.checkName;
                                                return n[t] ? (a[t] = n[t], n[t] ? "checked": "") : e[t] ? "checked": ""
                                            } () + ">": "numbers" === n.type ? h: n.toolbar ? i(t(n.toolbar).html() || "").render(e) : n.templet ?
                                            function() {
                                                return "function" == typeof n.templet ? n.templet(e) : i(t(n.templet).html() || String(f)).render(e)
                                            } () : f} (),
                                            "</div></td>"].join("");
                                        l.push(y),
                                        n.fixed && "right" !== n.fixed && o.push(y),
                                        "right" === n.fixed && u.push(y)
                                    }
                                }),
                                    y.push('<tr data-index="' + e + '">' + l.join("") + "</tr>"),
                                    p.push('<tr title="d" data-index="' + e + '">' + o.join("") + "</tr>"),
                                    m.push('<tr data-index="' + e + '">' + u.join("") + "</tr>"))
                            }),
                            c.layBody.scrollTop(0),
                            c.layMain.find("." + f).remove(),
                            c.layMain.find("tbody").html(y.join("")),
                            c.layFixLeft.find("tbody").html(p.join("")),
                            c.layFixRight.find("tbody").html(m.join("")),
                            c.renderForm(),
                            c.syncCheckAll(),
                            c.haveInit ? c.scrollPatch() : setTimeout(function() {
                                c.scrollPatch()
                            },
                            50),
                            c.haveInit = !0, void l.close(c.tipsIndex))
                    };
                return c.key = s.id || s.index,
                    d.cache[c.key] = u,
                    c.layPage[0 === u.length && 1 == n ? "addClass": "removeClass"](h),
                    r ? v() : 0 === u.length ? (c.renderForm(), c.layFixed.remove(), c.layMain.find("tbody").html(""), c.layMain.find("." + f).remove(), c.layMain.append('<div class="' + f + '">' + s.text.none + "</div>")) : (v(), void(s.page && (s.page = t.extend({
                            elem: "eui-table-page" + s.index,
                            count: o,
                            limit: s.limit,
                            limits: s.limits || [10, 20, 30, 40, 50, 60, 70, 80, 90],
                            groups: 3,
                            layout: ["prev", "page", "next", "skip", "count", "limit"],
                            prev: '<i class="eui-icon">&#xe603;</i>',
                            next: '<i class="eui-icon">&#xe602;</i>',
                            jump: function(e, t) {
                                t || (c.page = e.curr, s.limit = e.limit, c.pullData(e.curr, c.loading()))
                            }
                        },
                        s.page), s.page.count = o, a.render(s.page))))
            },
            S.prototype.getColElem = function(e, t) {
                var i = this,
                    a = i.config;
                return e.eq(0).find(".laytable-cell-" + (a.index + "-" + t) + ":eq(0)")
            },
            S.prototype.renderForm = function(e) {
                n.render(e, "LAY-table-" + this.index);
                n.render(e, "table-page-"+ this.index);
            },
            S.prototype.sort = function(e, i, a, l) {
                var n, r, c = this,
                    u = {},
                    h = c.config,
                    f = h.elem.attr("e-filter"),
                    y = d.cache[c.key];
                "string" == typeof e && c.layHeader.find("th").each(function(i, a) {
                    var l = t(this),
                        o = l.data("field");
                    if (o === e) return e = l,
                        n = o,
                        !1
                });
                try {
                    var n = n || e.data("field");
                    if (c.sortKey && !a && n === c.sortKey.field && i === c.sortKey.sort) return;
                    var p = c.layHeader.find("th .laytable-cell-" + h.index + "-" + n).find(w);
                    c.layHeader.find("th").find(w).removeAttr("e-sort"),
                        p.attr("e-sort", i || null),
                        c.layFixed.find("th")
                } catch(m) {
                    return o.error("Table modules: Did not match to field")
                }
                c.sortKey = {
                    field: n,
                    sort: i
                },
                    "asc" === i ? r = eui.sort(y, n) : "desc" === i ? r = eui.sort(y, n, !0) : (r = eui.sort(y, d.config.indexName), delete c.sortKey),
                    u[h.response.dataName] = r,
                    c.renderData(u, c.page, c.count, !0),
                l && eui.event.call(e, s, "sort(" + f + ")", {
                    field: n,
                    type: i
                })
            },
            S.prototype.loading = function() {
                var e = this,
                    t = e.config;
                if (t.loading && t.url) return l.msg("数据请求中", {
                    icon: 16,
                    offset: [e.elem.offset().top + e.elem.height() / 2 - 35 - T.scrollTop() + "px", e.elem.offset().left + e.elem.width() / 2 - 90 - T.scrollLeft() + "px"],
                    time: -1,
                    anim: -1,
                    fixed: !1
                })
            },
            S.prototype.setCheckData = function(e, t) {
                var i = this,
                    a = i.config,
                    l = d.cache[i.key];
                l[e] && l[e].constructor !== Array && (l[e][a.checkName] = t)
            },
            S.prototype.syncCheckAll = function() {
                var e = this,
                    t = e.config,
                    i = e.layHeader.find('input[name="layTableCheckbox"]'),
                    a = function(i) {
                        return e.eachCols(function(e, a) {
                            "checkbox" === a.type && (a[t.checkName] = i)
                        }),
                            i
                    };
                i[0] && (d.checkStatus(e.key).isAll ? (i[0].checked || (i.prop("checked", !0), e.renderForm("checkbox")), a(!0)) : (i[0].checked && (i.prop("checked", !1), e.renderForm("checkbox")), a(!1)))
            },
            S.prototype.getCssRule = function(e, t) {
                var i = this,
                    a = i.elem.find("style")[0],
                    l = a.sheet || a.styleSheet || {},
                    n = l.cssRules || l.rules;
                eui.each(n,
                    function(a, l) {
                        if (l.selectorText === ".laytable-cell-" + i.index + "-" + e) return t(l),
                            !0
                    })
            },
            S.prototype.fullSize = function() {
                var e, t = this,
                    i = t.config,
                    a = i.height;
                t.fullHeightGap && (a = T.height() - t.fullHeightGap, a < 135 && (a = 135), t.elem.css("height", a)),
                    e = parseFloat(a) - parseFloat(t.layHeader.height()) - 1,
                i.toolbar && (e -= t.layTool.outerHeight()),
                i.page && (e = e - t.layPage.outerHeight() - 1),
                    t.layMain.css("height", e)
            },
            S.prototype.getScrollWidth = function(e) {
                var t = 0;
                return e ? t = e.offsetWidth - e.clientWidth: (
                    e = document.createElement("div"),
                    e.style.width = "100px",
                    e.style.height = "100px",
                    e.style.overflowY = "scroll",
                    document.body.appendChild(e),
                    t = e.offsetWidth - e.clientWidth,
                    document.body.removeChild(e)),
                    t

            },
            S.prototype.scrollPatch = function() {
                var e = this,
                    i = e.layMain.children("table"),
                    a = e.layMain.width() - e.layMain.prop("clientWidth"),
                    l = e.layMain.height() - e.layMain.prop("clientHeight"),
                    n = e.getScrollWidth(e.layMain[0]),
                    o = i.outerWidth() - e.layMain.width();


            //    console.log("e.layMain.height()："+e.layMain.height());
            //    console.log("i.outerWidth()："+i.outerWidth());
            //    console.log("e.layMain.height()："+e.layMain.height());
            //    console.log("i.outerWidth()："+i.outerWidth());
            //    console.log("e.layMain.width()："+e.layMain.width());
            //    console.log(o);


                if (e.autoColNums && o < 5 && !e.scrollPatchWStatus) {
                    var r = e.layHeader.eq(0).find("thead th:last-child"),
                        d = r.data("field");


                    e.getCssRule(d,
                        function(t) {
                            var i = t.style.width || r.outerWidth();

                    //        console.log("前："+i);
                     //       console.log("N："+n);
                            console.log(parseFloat(i) - n - o);

                            t.style.width = parseFloat(i) - n - o + "px",


                     //       console.log("后："+t.style.width);

                            e.layMain.height() - e.layMain.prop("clientHeight") > 0 && (t.style.width = parseFloat(t.style.width) - 1 + "px"),
                                e.scrollPatchWStatus = !0
                        })
                }
                if (a && l) {
                    if (!e.elem.find(".eui-table-patch")[0]) {
                        var c = t('<th class="eui-table-patch"><div class="eui-table-cell"></div></th>');
                        c.find("div").css({
                            width: a
                        }),
                            e.layHeader.eq(0).find("thead tr").append(c)
                    }
                } else e.layHeader.eq(0).find(".eui-table-patch").remove();
                var s = e.layMain.height(),
                    u = s - l;
                e.layFixed.find(m).css("height", i.height() > u ? u: "auto"),
                    e.layFixRight[o > 0 ? "removeClass": "addClass"](h),
                    e.layFixRight.css("right", a - 1)
            },
            S.prototype.events = function() {
                var e, a = this,
                    n = a.config,
                    o = t("body"),
                    c = {},
                    u = a.layHeader.find("th"),
                    h = ".eui-table-cell",
                    f = n.elem.attr("e-filter");
                u.on("mousemove",
                    function(e) {
                        var i = t(this),
                            a = i.offset().left,
                            l = e.clientX - a;
                        i.attr("colspan") > 1 || i.data("unresize") || c.resizeStart || (c.allowResize = i.width() - l <= 10, o.css("cursor", c.allowResize ? "col-resize": ""))
                    }).on("mouseleave",
                    function() {
                        t(this);
                        c.resizeStart || o.css("cursor", "")
                    }).on("mousedown",
                    function(e) {
                        var i = t(this);
                        if (c.allowResize) {
                            var l = i.data("field");
                            e.preventDefault(),
                                c.resizeStart = !0,
                                c.offset = [e.clientX, e.clientY],
                                a.getCssRule(l,
                                    function(e) {
                                        var t = e.style.width || i.outerWidth();
                                        c.rule = e,
                                            c.ruleWidth = parseFloat(t),
                                            c.minWidth = i.data("minwidth") || n.cellMinWidth
                                    })
                        }
                    }),
                    M.on("mousemove",
                        function(t) {
                            if (c.resizeStart) {
                                if (t.preventDefault(), c.rule) {
                                    var i = c.ruleWidth + t.clientX - c.offset[0];
                                    i < c.minWidth && (i = c.minWidth),
                                        c.rule.style.width = i + "px",
                                        l.close(a.tipsIndex)
                                }
                                e = 1
                            }
                        }).on("mouseup",
                        function(t) {
                            c.resizeStart && (c = {},
                                o.css("cursor", ""), a.scrollPatch()),
                            2 === e && (e = null)
                        }),
                    u.on("click",
                        function() {
                            var i, l = t(this),
                                n = l.find(w),
                                o = n.attr("e-sort");
                            return n[0] && 1 !== e ? (i = "asc" === o ? "desc": "desc" === o ? null: "asc", void a.sort(l, i, null, !0)) : e = 2
                        }).find(w + " .eui-edge ").on("click",
                        function(e) {
                            var i = t(this),
                                l = i.index(),
                                n = i.parents("th").eq(0).data("field");
                            eui.stope(e),
                                0 === l ? a.sort(n, "asc", null, !0) : a.sort(n, "desc", null, !0)
                        }),
                    a.elem.on("click", 'input[name="layTableCheckbox"]+',
                        function() {
                            var e = t(this).prev(),
                                i = a.layBody.find('input[name="layTableCheckbox"]'),
                                l = e.parents("tr").eq(0).data("index"),
                                n = e[0].checked,
                                o = "layTableAllChoose" === e.attr("e-filter");
                            o ? (i.each(function(e, t) {
                                t.checked = n,
                                    a.setCheckData(e, n)
                            }), a.syncCheckAll(), a.renderForm("checkbox")) : (a.setCheckData(l, n), a.syncCheckAll()),
                                eui.event.call(this, s, "checkbox(" + f + ")", {
                                    checked: n,
                                    data: d.cache[a.key] ? d.cache[a.key][l] || {}: {},
                                    type: o ? "all": "one"
                                })
                        }),
                    a.layBody.on("mouseenter", "tr",
                        function() {
                            var e = t(this),
                                i = e.index();
                            a.layBody.find("tr:eq(" + i + ")").addClass(F)
                        }).on("mouseleave", "tr",
                        function() {
                            var e = t(this),
                                i = e.index();
                            a.layBody.find("tr:eq(" + i + ")").removeClass(F)
                        }),
                    a.layBody.on("change", "." + N,
                        function() {
                            var e = t(this),
                                i = this.value,
                                l = e.parent().data("field"),
                                n = e.parents("tr").eq(0).data("index"),
                                o = d.cache[a.key][n];
                            o[l] = i,
                                eui.event.call(this, s, "edit(" + f + ")", {
                                    value: i,
                                    data: o,
                                    field: l
                                })
                        }).on("blur", "." + N,
                        function() {
                            var e, l = t(this),
                                n = l.parent().data("field"),
                                o = l.parents("tr").eq(0).data("index"),
                                r = d.cache[a.key][o];
                            a.eachCols(function(t, i) {
                                i.field == n && i.templet && (e = i.templet)
                            }),
                                l.siblings(h).html(e ? i(t(e).html() || this.value).render(r) : this.value),
                                l.parent().data("content", this.value),
                                l.remove()
                        }),
                    a.layBody.on("click", "td",
                        function() {
                            var e = t(this),
                                i = (e.data("field"), e.data("edit")),
                                o = e.children(h);
                            if (l.close(a.tipsIndex), !e.data("off")) if (i) if ("select" === i);
                            else {
                                var d = t('<input class="eui-input ' + N + '">');
                                d[0].value = e.data("content") || o.text(),
                                e.find("." + N)[0] || e.append(d),
                                    d.focus()
                            } else o.find(".eui-form-switch,.eui-form-checkbox")[0] || Math.round(o.prop("scrollWidth")) > Math.round(o.outerWidth()) && (a.tipsIndex = l.tips(['<div class="eui-table-tips-main" style="margin-top: -' + (o.height() + 16) + "px;" +
                            function() {
                                return "sm" === n.size ? "padding: 4px 15px; font-size: 12px;": "lg" === n.size ? "padding: 14px 15px;": ""
                            } () + '">', o.html(), "</div>", '<i class="eui-icon eui-table-tips-c">&#x1006;</i>'].join(""), o[0], {
                                tips: [3, ""],
                                time: -1,
                                anim: -1,
                                maxWidth: r.ios || r.android ? 300 : 600,
                                isOutAnim: !1,
                                skin: "eui-table-tips",
                                success: function(e, t) {
                                    e.find(".eui-table-tips-c").on("click",
                                        function() {
                                            l.close(t)
                                        })
                                }
                            }))
                        }),
                    a.layBody.on("click", "*[e-event]",
                        function() {
                            var e = t(this),
                                l = e.parents("tr").eq(0).data("index"),
                                n = a.layBody.find('tr[data-index="' + l + '"]'),
                                o = "eui-table-click",
                                r = d.cache[a.key][l];
                            eui.event.call(this, s, "tool(" + f + ")", {
                                data: d.clearCacheKey(r),
                                event: e.attr("e-event"),
                                tr: n,
                                del: function() {
                                    d.cache[a.key][l] = [],
                                        n.remove(),
                                        a.scrollPatch()
                                },
                                update: function(e) {
                                    e = e || {},
                                        eui.each(e,
                                            function(e, l) {
                                                if (e in r) {
                                                    var o, d = n.children('td[data-field="' + e + '"]');
                                                    r[e] = l,
                                                        a.eachCols(function(t, i) {
                                                            i.field == e && i.templet && (o = i.templet)
                                                        }),
                                                        d.children(h).html(o ? i(t(o).html() || l).render(r) : l),
                                                        d.data("content", l)
                                                }
                                            })
                                }
                            }),
                                n.addClass(o).siblings("tr").removeClass(o)
                        }),
                    a.layMain.on("scroll",
                        function() {
                            var e = t(this),
                                i = e.scrollLeft(),
                                n = e.scrollTop();
                            a.layHeader.scrollLeft(i),
                                a.layFixed.find(m).scrollTop(n),
                                l.close(a.tipsIndex)
                        }),
                    T.on("resize",
                        function() {
                            a.fullSize(),
                                a.scrollPatch()
                        })
            },
            d.init = function(e, i) {
                i = i || {};
                var a = this,
                    l = t(e ? 'table[e-filter="' + e + '"]': u + "[e-data]"),
                    n = "Table element property e-data configuration item has a syntax error: ";
                return l.each(function() {
                    var a = t(this),
                        l = a.attr("e-data");
                    try {
                        l = new Function("return " + l)()
                    } catch(r) {
                        o.error(n + l)
                    }
                    var c = [],
                        s = t.extend({
                                elem: this,
                                cols: [],
                                data: [],
                                skin: a.attr("e-skin"),
                                size: a.attr("e-size"),
                                even: "string" == typeof a.attr("e-even")
                            },
                            d.config, i, l);
                    e && a.hide(),
                        a.find("thead>tr").each(function(e) {
                            s.cols[e] = [],
                                t(this).children().each(function(i) {
                                    var a = t(this),
                                        l = a.attr("e-data");
                                    try {
                                        l = new Function("return " + l)()
                                    } catch(r) {
                                        return o.error(n + l)
                                    }
                                    var d = t.extend({
                                            title: a.text(),
                                            colspan: a.attr("colspan") || 0,
                                            rowspan: a.attr("rowspan") || 0
                                        },
                                        l);
                                    d.colspan < 2 && c.push(d),
                                        s.cols[e].push(d)
                                })
                        }),
                        a.find("tbody>tr").each(function(e) {
                            var i = t(this),
                                a = {};
                            i.children("td").each(function(e, i) {
                                var l = t(this),
                                    n = l.data("field");
                                if (n) return a[n] = l.html()
                            }),
                                eui.each(c,
                                    function(e, t) {
                                        var l = i.children("td").eq(e);
                                        a[t.field] = l.html()
                                    }),
                                s.data[e] = a
                        }),
                        d.render(s)
                }),
                    a
            },
            d.checkStatus = function(e) {
                var t = 0,
                    i = 0,
                    a = [],
                    l = d.cache[e] || [];
                return eui.each(l,
                    function(e, l) {
                        return l.constructor === Array ? void i++:void(l[d.config.checkName] && (t++, a.push(d.clearCacheKey(l))))
                    }),
                {
                    data: a,
                    isAll: !!l.length && t === l.length - i
                }
            },
            c.config = {},
            d.reload = function(e, i) {
                var a = c.config[e];
                return i = i || {},
                    a ? (i.data && i.data.constructor === Array && delete a.data, d.render(t.extend(!0, {},
                        a, i))) : o.error("The ID option was not found in the table instance")
            },
            d.render = function(e) {
                var t = new S(e);
                return c.call(t)
            },
            d.onLoaded = function(e) {
                return false;
            },
            d.clearCacheKey = function(e) {
                return e = t.extend({},
                    e),
                    delete e[d.config.checkName],
                    delete e[d.config.indexName],
                    e
            },
            d.init(),
            e(s, d)
    });