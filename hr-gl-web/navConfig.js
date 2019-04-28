// 说明：
// 1、如果不需要图标，将  icon:"图标路径",  删除即可
// 2、当前标题下如有下级标题的，则当前不需要url: "页面路径"，而是使用childs:[]再进行包裹。

// var menus = [
// 	// '系统管理员': [
// 		{ name: "首页", icon: "", id: "01", url: "HtmlPage/module/01.index/index3.html" },
// 		{ name: "供应商", icon: "", id: "11", url: "HtmlPage/module/01.index/supplier-index.html" },
//         { name: "客户管理", icon: "", id: "02",childs: [
//             { name: "客户管理", icon: "&#xe857;",  id: "0201", url: "HtmlPage/module/cust/custManage.html" },
//             { name: "预约管理", icon: "&#xe857;",  id: "0202", url: "HtmlPage/module/cust/yyManage.html" },
//             { name: "潜在客户管理", icon: "&#xe857;",  id: "0203", url: "HtmlPage/module/cust/potentialManage.html" },
//             // { name: "客户等级管理", icon: "&#xe857;",  id: "0204", url: "HtmlPage/module/cust/custClass.html" }
//         ]},
//         { name: "库存管理", icon: "", id: "04",childs: [
//             { name: "库存汇总", icon: "&#xe857;",  id: "0501", url: "HtmlPage/module/stock/stockManage.html" },
//             { name: "库存预警", icon: "&#xe857;",  id: "0501", url: "HtmlPage/module/stock/stockManage.html" },
//             // { name: "客户订单", icon: "&#xe857;",  id: "0401", url: "HtmlPage/module/order/orderManage.html" },
//             { name: "内部订单", icon: "&#xe857;",  id: "0202", url: "HtmlPage/module/order/productOrderManage.html" },
//             { name: "制牙订单", icon: "&#xe857;",  id: "0203", url: "HtmlPage/module/order/create-order.html" },
//             { name: "产品库管理", icon: "&#xe857;",  id: "0504", url: "HtmlPage/module/stock/productManage.html" },
//             { name: "产品类型管理", icon: "&#xe857;",  id: "0505", url: "HtmlPage/module/stock/productType.html" },
//             { name: "进/退货申请处理", icon: "&#xe857;",  id: "0506", url: "HtmlPage/module/stock/stockApply.html" }
//         ]},
// //         { name: "后端页面", icon: "", id: "03",childs: [
// //             { name: "信息列表", icon: "&#xe857;",  id: "0301",childs:[
// //                 { name: "常规列表", icon: "&#xe857;",  id: "030101", url: "HtmlPage/module/03.backManage/常规列表.html" },
// //                 { name: "左树右列", icon: "&#xe857;",  id: "030102", url: "HtmlPage/module/03.backManage/左树右列.html"},
// //                 { name: "详情页面", icon: "&#xe857;",  id: "030103", url: "HtmlPage/module/00.default/noPage.html" }
// //             ] },
// // 			{ name: "常用表单表单", icon: "&#xe857;",  id: "0302", childs:[
// //             	{ name: "申请表单一", icon: "&#xe875;", id: "030201", url: "HtmlPage/module/forms/表单页面.html"},
// //             	{ name: "申请表单二", icon: "&#xe875;", id: "030202", url: "HtmlPage/module/forms/表单页面二.html"},
// //             	{ name: "申请表单三", icon: "&#xe875;", id: "030203", url: "HtmlPage/module/forms/表单页面三（表格布局）.html"},
// //             	{ name: "条件查询", icon: "&#xe875;", id: "030203", url: "HtmlPage/module/forms/信息列表查询.html"}
// // //          	{ name: "信息列表", icon: "&#xe875;", id: "030203", url: "HtmlPage/module/forms/信息列表.html"},
// //             ]},
// //             { name: "信息表单", icon: "&#xe857;",  id: "0303", childs:[
// //             	{ name: "信息表单-单列", icon: "&#xe875;", id: "030301", url: "HtmlPage/module/forms/formMenus1.html"},
// //             	{ name: "信息表单-两列", icon: "&#xe875;", id: "030302", url: "HtmlPage/module/forms/formMenus2.html"}
// // //          	{ name: "信息表单-三列", icon: "&#xe875;", id: "030303", url: "HtmlPage/module/forms/formMenus3.html"},
// //             ]},
// //
// //             { name: "评论中心", icon: "&#xe857;",  id: "0304",  childs:[
// //                 { name: "评论样式一", icon: "&#xe857;",  id: "030401", url: "HtmlPage/module/03.backManage/评论样式一.html" },
// //                 { name: "评论样式二", icon: "&#xe857;",  id: "030402", url: "HtmlPage/module/03.backManage/评论样式二.html"}
// //             ]},
// //             { name: "登录注册", icon: "&#xe857;",  id: "0305",  childs:[
// //                 { name: "登录界面", icon: "&#xe857;",  id: "030501", url: "login.html" },
// //                 { name: "注册界面", icon: "&#xe857;",  id: "030502", url: "HtmlPage/module/00.default/noPage.html"}
// //             ]},
// //             { name: "消息中心", icon: "&#xe857;",  id: "0306", url: "HtmlPage/module/00.default/noPage.html" },
// //             { name: "个人中心", icon: "&#xe857;",  id: "0307", childs:[
// //             	{ name: "个人中心-单列", icon: "&#xe857;", id: "030701", url: "HtmlPage/module/forms/personalCenter.html"}
// //             ] },
// //             { name: "ECHARTS官方网站", icon: "&#xe857;",  id: "0402", url: "http://echarts.baidu.com/index.html" }
// //         ]},
// //          { name: "财务管理", icon: "", id: "06",childs: [
// //             { name: "结算管理", icon: "&#xe857;",  id: "0601", url: "HtmlPage/module/finance/account-manage.html" },
// //             { name: "营收管理", icon: "&#xe857;",  id: "0602", url: "HtmlPage/module/finance/revenue-manage.html" },
//             // { name: "折线图", icon: "&#xe857;",  id: "0401", url: "HtmlPage/module/00.default/noPage.html" },
//             // { name: "柱状图", icon: "&#xe857;",  id: "0401", url: "HtmlPage/module/00.default/noPage.html" },
//             // { name: "圆环图", icon: "&#xe857;",  id: "0401", url: "HtmlPage/module/00.default/noPage.html" },
//             // { name: "雷达图", icon: "&#xe857;",  id: "0401", url: "HtmlPage/module/00.default/noPage.html" },
//             // { name: "漏斗图", icon: "&#xe857;",  id: "0401", url: "HtmlPage/module/00.default/noPage.html" },
//             // { name: "地图", icon: "&#xe857;",  id: "0401", url: "HtmlPage/module/00.default/noPage.html" },
//
//         // ]},
//         { name: "系统管理", icon: "", id: "08",childs: [
//             { name: "在线咨询", icon: "&#xe857;",  id: "0702", url: "HtmlPage/module/custServicce/service-im.html" },
//             { name: "菜单管理", icon: "&#xe857;", id: "0801", url: "HtmlPage/module/system/menuManage.html" },
//             { name: "组织管理", icon: "&#xe857;",  id: "0802", url: "HtmlPage/module/system/departmentManage.html" },
//             { name: "用户管理", icon: "&#xe857;",  id: "0803", url: "HtmlPage/module/system/userManage.html" },
//             { name: "角色权限管理", icon: "&#xe857;",  id: "0804", url: "HtmlPage/module/system/roleManage.html"},
//             // { name: "权限模板管理", icon: "&#xe857;",  id: "0805", url: "HtmlPage/module/system/permissionManage.html" },
//             { name: "诊所管理", icon: "&#xe857;",  id: "0806", url: "HtmlPage/module/system/clinic-manage.html" },
//             // { name: "就诊项目管理", icon: "&#xe857;",  id: "08016", url: "HtmlPage/module/system/projectManage.html" },
//             { name: "系统日志管理", icon: "&#xe857;",  id: "0808", url: "HtmlPage/module/system/common/log.html" },
//             // { name: "系统配置管理", icon: "&#xe857;",  id: "08010", url: "HtmlPage/module/system/systemSet.html" }
//         ]}
// ];
var menus =new Array;
//查询用户所拥有的菜单
$.ajax({
    type:"GET",
    url:basePath+"/sec/menu/findMenuByUser",
    async: false,
    dataType:"json",
    success:function(data){
        menus=JSON.parse( data );

    },
    error :function(){
        layer.msg("数据读取失败");
    }
});

var shortcuts = [
    { id: "3", name: "个人中心", thumbnail: "Styles/images/iconStyle2/stop.png",  url:"javascript:personalCenter()"},
    { id: "2", name: "退出系统", thumbnail: "Styles/images/iconStyle2/stop.png", url: "javascript:loginOut()" }
];

$.pageLoad.register("before", function () {
    if(window.sessionStorage){
        var str =window.sessionStorage.getItem("user");
        var user = JSON.parse(str);
        if(user==undefined||user ==""||user==null){
            alert("未登录或超时，请先登录！！");
            window.location.href = "/hrWeb";
        }
        $('#userinfoName').html(user.userName);

    }else {
        var userName =$.cookie('userName');
        if(userName==undefined||userName ==""||userName==null){
            alert("未登录或超时，请先登录！！");
            window.location.href = "/hrWeb";
        }
        $('#userinfoName').html(userName);
    }
    // Index.theme.render(themes) ;
    console.info(menus);
    var _menus = menus;
    Index.shortcut.render(shortcuts) ;
    Index.menu.init(_menus);

});





