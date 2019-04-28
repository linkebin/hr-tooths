var Index = {
	isRenderTop: false,

	shortcut: {
		render: function(shortcuts) {
			var container = $("#shortcut_template").attr("container");

			shortcuts = formatShortcut(shortcuts);

			$(container).html(
				$("#shortcut_template").render(shortcuts)
			);

			$(shortcuts).each(function() {
				var me = this;
				if(me.url) {
					me.url = $.utils.parseUrl(me.url);
					$("[shortcut-id='" + this.menuId + "']").click(function() {
						window.location.href = me.url;
						return false;
					});
				} else if(me.description) {
					me.description = $.utils.parseUrl(me.description);
					$("[shortcut-id='" + this.menuId + "']").click(function() {
						if(me.description.indexOf("function()") != -1) {
							var action = function() {};
							eval("action = " + me.description);
							action();
						}
						return false;
					});
				}
			});

			function formatShortcut(shortcuts) {
				$(shortcuts).each(function() {
					this.url = jQuery.utils.parseUrl(this.url);
					this.menuId = this.menuId || this.id;
					this.thumbnail = jQuery.utils.parseUrl(this.thumbnail || this.iconMiddle || this.icon);
				});
				return shortcuts;
			}
		}
	},
	menu: {
		mapCacheMenu: null,
		_cacheMenu: null,
		mapEvent: {},
		toRenderMenu: null,
		init: function(menus) {

			var cacheMenu = {};
			menus = formatMenu(menus);
			Index.menu.mapCacheMenu = cacheMenu;
			Index.menu._cacheMenu = menus;
			Index.menu.render();

			function formatMenu(menus) {
				var _roots = [];
				var map = {};

				$(menus).each(function() {
					//	this.thumbnailSmall = this.thumbnailSmall=='null'?"":this.thumbnailSmall ;
					if(!this.pid) {
						_roots.push(this); //root
					} else {
						map[this.pid] = map[this.pid] || [];
						map[this.pid].push(this);
					}
					cacheMenu[this.id] = this;
				});

				formatChild(_roots, map);

				return _roots;

			}

			function formatChild(menus, map) {

				$(menus).each(function() {
					if(map[this.id]) {
						this.childs = map[this.id];
						formatChild(this.childs, map);
					}
				});

			};
		},
		addEvent: function(type, func) {
			Index.menu.mapEvent[type] = Index.menu.mapEvent[type] || [];
			Index.menu.mapEvent[type].push(func);
		},
		render: function() { /*渲染菜单*/
			Index.menu.hasTopMenu = Index.menu.hasTopMenu === false ? false : true;
			//自动发现

			if($("#topmenu_template")[0] && Index.menu.hasTopMenu) {
				Index.menu._renderTop(); //有顶部菜单
			} else {
				Index.menu._renderLeft(); //无顶部菜单，全部居左
			}

		},

		_findMenu: function(menus, id) {
			for(var i = 0; i < menus.length; i++) {
				if(menus[i].id == id) {
					return menus[i];
				}

				if(menus[i].childs) {
					var _ = Index.menu._findMenu(menus[i].childs, id);
					if(_) return _;
				}
			}
			return null;
		},
		_renderTop: function() {
			Index.menu.toRenderMenu = null;
			Index.menu.getReqMenuId = Index.menu.getReqMenuId || function() {
				return "";
			};
			var initMenuid = Index.menu.getReqMenuId();
			var menus = Index.menu._cacheMenu;
			var container = $("#topmenu_template").attr("container");

			//$(container).width( containerWidth ) ;
			$(container).html(
				$("#topmenu_template").render(Index.menu._cacheMenu)
			).find("a").click(function(e) {
				Index.menu.toRenderMenu = $(this).attr("toRenderMenu");
				$(container).find("li").removeClass("current");
				$(this).parents("li").addClass("current");
				var menuid = $(this).attr('menuid');
				var url = $(this).attr('href');

				$.ajax({
					type: "POST",
					url: basePath + "/sec/menu/getUrl",
					data:{
						id:menuid
					},
					async: false,
					dataType: "json",
					success: function(data) {
						if(data.openType==2){
							$("#content iframe").attr("src", data.url);
						}

					},
					error: function() {
						layer.msg("数据读取失败");
					}
				});

				//renderLeftMenu
				var menu = Index.menu._findMenu(menus, menuid);

				if(menu && menu.childs && menu.childs.length > 0) {
					$("#sidebar").show();
					//	$(".master-page").removeClass("nosidebar");
					Index.menu._renderLeft(menu.childs);

					//navigation
					//	var url = $(this).attr('href');
					Index.menu._navigate(menuid, url, this); //关系到点击第一导航时会不会触发左边第一个

					return false;
				} else {

					Index.menu._navigate(menuid, url, this);

					$("#sidebar").hide();
					$(this).addClass("noChilds");
					Index.layout.init();

					return false;
				}

				//	return false;
			});

			if(initMenuid) {
				var rootMenu = getMenuRoot(initMenuid);
				if(!rootMenu) {
					$(container).find("a").first().click();
				} else {
					if(rootMenu.id != initMenuid) {
						Index.menu.toRenderMenu = initMenuid;
					}
					$(container).find("a[menuid='" + rootMenu.id + "']").attr("toRenderMenu", Index.menu.toRenderMenu || "").click();
				}
			} else {
				$(container).find("a").first().click();
			}

			var containerWidth = 0;
			if(Index.menu.menuWidthCallback) {
				//	containerWidth = Index.menu.menuWidthCallback() ;
			} else {
				//	containerWidth = Index.menu._menuWidthCallback() ;
			}

			Index.isRenderTop = true;

			$(Index.menu.mapEvent['renderTopAfter'] || []).each(function(index, func) {
				func();
			});

			function getMenuRoot(id) {
				var menu = Index.menu.mapCacheMenu[id];
				if(!menu) return null;
				var pid = menu.pid;
				if(Index.menu.mapCacheMenu[pid]) {
					return getMenuRoot(pid);
				} else {
					return Index.menu.mapCacheMenu[id];
				}
			}
		},
		_renderLeft: function(menus) {
			menus = menus || Index.menu._cacheMenu;
			$(".rootmenu-div").empty().html('<ul class="rootmenu"></ul>');

			var container = $("#leftmenu_template").attr("container");
			$(container).html(
				$("#leftmenu_template").render(menus)
			);

			$(container).find("a").click(function(e) {
				Index.menu.toRenderMenu = null;
				$(container).find("li").removeClass("current");
				$(this).parents("li").addClass("current");
				var menuid = $(this).attr('menuid');

				//navigation
				var url = $(this).attr('href');
				Index.menu._navigate(menuid, url, this);

				return false;
			});

			$('#sidebar .rootmenu>li').each(function() {
				if($(this).find('li').length != 0) {
					$(this).addClass('submenuLi');
				} else {
					//	alert("无子菜单")
				}
			});

			/*菜单点击*/
			/* 没有子菜单的点击 */
			$('#sidebar  .rootmenu>li:not([class="submenuLi"])>a').unbind("click.submenuLi").bind("click.submenuLi", function() {
				$(this).parent().siblings().removeClass('minus').find('ul:first').hide();
			});

			/* 有子菜单的点击 */
			$('#sidebar .rootmenu>li[class="submenuLi"]>a').unbind("click.submenuLi").bind("click.submenuLi", function() {

				var tf = $("#sidebar").hasClass("collapsed");
				if(!tf) {
					//开启
					$(this).parent().addClass('minus').find('ul:first').show().end().siblings().removeClass('minus').find('ul:first').hide();
				}
				return false;
			});

			setTimeout(function() {
				Index.layout.init();
			}, 0);

			$(Index.menu.mapEvent['renderLeftAfter'] || []).each(function(index, func) {
				func();
			});

			$(".rootmenu li:first").addClass("current");

		},
		_navigate: function(id, url, el, childMenu) {
			if(!(url && url != "#" && url != "javascript:void(0)")) {
				url = "";
			}

			Index.menu.setReqMenuId = Index.menu.setReqMenuId || function() {
				return "";
			};

			try {
				if(Index.menu.setReqMenuId(id, Index.menu.toRenderMenu || '')) {
					return;
				}
			} catch(e) {}

			//currentMenuId targetMenuId
			if(Index.menu.toRenderMenu && Index.menu.toRenderMenu != id) { //调整到子菜单
				$("[menuid='" + Index.menu.toRenderMenu + "']").click();
				return;
			}

			if(url != '###' && url) {
				var menu = Index.menu._findMenu(Index.menu._cacheMenu, id);
				if(menu.target == "_blank" || jQuery.query.get("target") == "_blank") {

					window.open($.utils.parseUrl(url));
				} else {
					//	alert(url);
					//设置点击左侧菜单，右侧加载
					$("#content iframe").attr("src", $.utils.parseUrl(url));
				}

			} else { //查找子菜单

				var selfMenu = Index.menu._findMenu(Index.menu._cacheMenu, id);
				//alert( $.json.encode(selfMenu) ) ;
				if(selfMenu && selfMenu.childs && selfMenu.childs.length > 0) {
					var child = selfMenu.childs[0];
					$("[menuid='" + child.id + "']").click();
					console.log(child.id);

				}

				setTimeout(function() {
					var sideBarHeight = $("#sidebar").height();

					var height = 0;
					$(".rootmenu >li").each(function() {
						height = height + $(this).outerHeight(true);
					});

				}, 500);
			}
		}
	},
	layout: {
		init: function() {

			//JSON数据加载，平台名称、LOGO路径
			$.ajax({
				"type": "GET",
				"url": "Scripts/data/frameInfo.json",
				async: false, //同步方式，先加载完数据再进行
				"dataType": "json",
				"success": function(data) {

					// $("#webName").text(data.webInfo.系统名称);
					$(document).attr("title", data.webInfo.系统标题);
					$("#webLogo").attr("src", data.webInfo.系统LOGO);
					$("#copyRight").text(data.webInfo.版权信息);
				},
				"error": function() {
					layer.msg("好伤心，数据读取失败了");

				}
			});

			navSroll(); //一级导航是否滚动
			//	leftNavSroll(); //左侧导航是否滚动
			leftNavH();
			heightAuto(); //外框架高度自适应

			if(!$('#sidebar').length) return;

			/**
			 * Sidebar menus
			 * */
			var currentMenu = null;
			var winHei = $(window).height(),
				headHei = $('#header').height(),
				$li = $('#sidebar .rootmenu>li:first'),
				sidebarTitle = $('#sidebar .sidebar-title').height(),
				liHei = $li.outerHeight(true);

			if(liHei > $li.find(">ul").outerHeight(true)) {
				liHei = liHei - $li.find(">ul").outerHeight(true);
			}

			var sideHei = $('#sidebar>.rootmenu-div>.rootmenu>li').length * liHei,
				contentHei = $("#content").height();
			var submenuHei = contentHei - sideHei - sidebarTitle;

			/*左侧收缩效果 --start--*/
			//全局定义
			var $sidebar = $('#sidebar'),
				$menucollapse = $('#menucollapse'),
				$sidebarSubmenu = $("#sidebar .rootmenu>li[class='submenuLi']");

			var widthCol = 45,
				widthIscol = 200;

			//collapsed 是关着
			if($.cookie('isCollapsed') === 'true') {
				$sidebar.addClass('collapsed');
			} else {
				$sidebar.removeClass('collapsed');
			}

			//点击收缩
			var sideBarWidth = $sidebar.width();
			$menucollapse.unbind("click.menucollapse").bind("click.menucollapse", function() {
				//	var $sidebar = $sidebar;
				$sidebar.toggleClass('collapsed');
				isCollapsed = $sidebar.hasClass('collapsed');

				if(isCollapsed) {

					$("ul.submenu").removeAttr("style");

					$(this).find("i").html("&#xe65b;"); //切换向右箭头
					$(".content").css("left", widthCol); //设置右边内容是否有左边距
					$(".rootmenu li > a > span").hide();
					$("#sidebar").width(widthCol);

					$sidebarSubmenu.bind("mouseenter", showSubmenu).bind("mouseleave", hideSubmenu);
					//	$sidebarSubmenu.find("ul.submenu").hide();
					$(this).find("ul.submenu").show();

					console.log("关闭");

				} else {
					$(this).find("i").html("&#xe65a;"); //切换向左箭头
					$(".content").css("left", widthIscol);
					$("#sidebar").width(widthIscol);
					$(".rootmenu li > a > span").delay(100).fadeIn();
					console.log("开启");

					$sidebarSubmenu.unbind("mouseenter", showSubmenu).unbind("mouseleave", hideSubmenu);
					$(".rootmenu > li.current").find("ul.submenu").show();

				}
				$.cookie('isCollapsed', isCollapsed);
				return false;
			});

			//未点击收缩时
			if($sidebar.is(":hidden")) {
				$(".content").css("left", 0);
				//	$(this).find("i").html("&#xe65a;");
			} else {
				isCollapsed = $sidebar.hasClass('collapsed');
				if(isCollapsed) {
					$menucollapse.find("i").html("&#xe65b;");
					$(".content").css("left", widthCol);
					$sidebar.width(widthCol);

					$sidebarSubmenu.bind("mouseenter", showSubmenu).bind("mouseleave", hideSubmenu); //绑定经过事件

				} else {
					$menucollapse.find("i").html("&#xe65a;");
					$(".content").css("left", widthIscol);
					$sidebar.width(widthIscol);

					$sidebarSubmenu.unbind("mouseenter", showSubmenu).unbind("mouseleave", hideSubmenu); //解绑经过事件

				}
			}

			//收缩时的菜单操作
			//移入，出现子菜单
			function showSubmenu() {
				$(this).find("ul.submenu").show();
				console.log("移入，出现");

			}
			//移出，隐藏子菜单
			function hideSubmenu() {
				$(this).find("ul.submenu").hide();
				console.log("移出，隐藏");
			}

			/*左侧收缩效果 --end--*/

			//窗口改变后动态计算
			$(window).resize(function() {
				navSroll();
				//	leftNavSroll();
				leftNavH();
				heightAuto();
			});

			//一级导航左右滚动
			function navSroll() {
				var windowWidth = $(window).width(),
					navLogoOWidth = $(".nav-logo").outerWidth(),
					navSreachOWidth = $(".nav-sreach").outerWidth(),
					euiNavOWidth = $(".header .eui-nav").outerWidth() + 1,
					headerPadVal = $(".header").css("padding-left").replace("px", "");

				var navliLength = $(".navul li").length;
				var navliWidth = 0;
				$(".navul li").each(function() {
					navliWidth = navliWidth + $(this).outerWidth();
				});

				var navulW = $(".navul").width();
				var liWidth = 105;
				var navulBodyMar = $(".navBody").css("margin-right").replace("px", "");

				var navulBodyMinW = windowWidth - navLogoOWidth - navSreachOWidth - euiNavOWidth - headerPadVal * 2 - navulBodyMar; //导航最小宽度

				//	console.log("没读JSON之前我就计算好宽度了："+windowWidth+"-"+navLogoOWidth+"-"+navSreachOWidth+"-"+euiNavOWidth+"-"+headerPadVal*2+"-"+navulBodyMar+"="+navulBodyMinW);

				if(navliWidth >= navulBodyMinW) {

					$(".navulBody").width(navulBodyMinW);
					$(".nav-Arrow").show();
					$(".navul").width(navliWidth);

					if($(".navul").css("margin-left").replace("px", "") == 0) {
						$(".nav-leftArrow").addClass("nav-Arrow-no eui-color-gray3")
					} else {}
				} else {
					//	$(".navulBody").width(navulW);
					$(".nav-Arrow").hide();

				}

				var navulBW = $(".navulBody").width();
				//绑定点击左箭头 向右滑动
				$(".nav-leftArrow").unbind("click").click(function() {
					$(".nav-rightArrow").removeClass("nav-Arrow-no eui-color-gray3");
					var magrinVal = parseInt($(".navul").css("margin-left").replace("px", ""));
					if(magrinVal < 0) {
						if(-magrinVal > liWidth) {
							$(".navul").animate({
								"margin-left": "+=" + liWidth + "px"
							});
						} else {
							$(".navul").animate({
								"margin-left": 0 + "px"
							});
							$(".nav-leftArrow").addClass("nav-Arrow-no eui-color-gray3");
						}
					}
				})

				//绑定点击右箭头 向左滑动
				$(".nav-rightArrow").unbind("click").click(function() {
					$(".nav-leftArrow").removeClass("nav-Arrow-no eui-color-gray3");
					var magrinVal = parseInt($(".navul").css("margin-left").replace("px", ""));
					if(navliWidth + magrinVal > navulBW) {
						if(navliWidth + magrinVal - navulBW > liWidth) {
							$(".navul").animate({
								"margin-left": "-=" + liWidth + "px"
							});
						} else {
							$(".navul").animate({
								"margin-left": "-=" + (navliWidth + magrinVal - navulBW) + "px"

							});
							$(".nav-rightArrow").addClass("nav-Arrow-no eui-color-gray3");
						}
					}
				})
			}

			//  左侧高度，滚动-临时方案
			function leftNavH() {
				var sidebarH = $(".sidebar").height(),
					sidebarTitleH = $(".sidebar-title").outerHeight();
				$(".rootmenu-div").height(sidebarH - sidebarTitleH).css({
					"overflow-y": "auto",
					"overflow-x": "hidden"
				});
			}

			//外框架高度自适应
			function heightAuto() {
				// 动态设置右边内容高度
				var windowHeight = $(window).height(),
					footerOuterHeight = $("#footer").outerHeight(true),
					headerOuterHeight = $("#header").outerHeight(true),
					contentOuterHeight = $(".content").outerHeight(true),
					//	tabTitleHeight  = $(".eui-tab-title").height(),
					contentHeight = windowHeight - headerOuterHeight - footerOuterHeight;
				//	euiTabHeight =$(".content .eui-tab").css("padding-top").replace("px","");

				$("#sidebar").height(contentHeight);
				//	$("#content .eui-tab-content").height(contentHeight-tabTitleHeight-euiTabHeight);

			}
			/*兼容性调整*/
			//$('#sidebar>ul>li:first').find('ul:first').css("display","block");
			//小于ie7版本，样式调整
			/*              if($.browser.version<7){
			 $("#sidebar>ul>li>a").css("text-indent","10px");
			 }
			 //google,ie8
			 if($.browser.webkit||$.browser.version==8.0){
			 $("#sidebar>ul>li>a").css({"height":"25px","ine-height":"25px"});
			 }
			 //ie7
			 if($.browser.version==7.0){
			 $("#sidebar>ul>li>a>span").css("padding-right","4px");
			 }
			 //ie7
			 if($.browser.version==9.0||$.browser.version==7.0){
			 $("#sidebar>ul>li>a").css("line-height","33px");
			 } */
		}
	}
}