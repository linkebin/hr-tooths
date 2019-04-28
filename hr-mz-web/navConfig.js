// 说明：
// 1、如果不需要图标，将  icon:"图标路径",  删除即可
// 2、当前标题下如有下级标题的，则当前不需要url: "页面路径"，而是使用childs:[]再进行包裹。

// var menus = [
// 	// '系统管理员': [
// 		 { name: "首页", icon: "", id: "01", url: "HtmlPage/module/01.index/index3.html" },
//          { name: "客户中心", icon: "",  id: "0205", url: "HtmlPage/module/cust/cust-manage.html" },
//         // { name: "客户预约", icon: "", id: "02",childs: [
//             // { name: "客户管理", icon: "&#xe857;",  id: "0201", url: "HtmlPage/module/cust/custManage.html" },
//             // { name: "预约管理", icon: "&#xe857;",  id: "0202", url: "HtmlPage/module/cust/yyManage.html" },
//         // ]},
//         // { name: "订单管理", icon: "", id: "04",childs: [
//         //     { name: "客户订单", icon: "&#xe857;",  id: "0401", url: "HtmlPage/module/order/orderManage.html" },
//         //
//         // ]},
//         { name: "库存管理", icon: "", id: "05",childs: [
//             { name: "库存汇总", icon: "&#xe857;",  id: "0501", url: "HtmlPage/module/stock/stockManage.html" },
//             { name: "入库管理", icon: "&#xe857;",  id: "0502", url: "HtmlPage/module/stock/inStockManage.html" },
//             { name: "出库管理", icon: "&#xe857;",  id: "0503", url: "HtmlPage/module/stock/outStockManage.html" },
//             { name: "内部订单", icon: "&#xe857;",  id: "0504", url: "HtmlPage/module/order/productOrderManage.html" },
//             { name: "制牙订单", icon: "&#xe857;",  id: "0505", url: "HtmlPage/module/order/create-order.html" }
//         ]},
//          { name: "财务管理", icon: "", id: "06",childs: [
//             { name: "结算管理", icon: "&#xe857;",  id: "0601", url: "HtmlPage/module/finance/account-manage.html" },
//             // { name: "营收管理", icon: "&#xe857;",  id: "0602", url: "HtmlPage/module/finance/revenue-manage.html" },
//         ]},
//         { name: "基础管理", icon: "", id: "08",childs: [
//             { name: "潜在客户管理", icon: "&#xe857;",  id: "0203", url: "HtmlPage/module/cust/potentialManage.html" },
//             { name: "客户等级管理", icon: "&#xe857;",  id: "0204", url: "HtmlPage/module/cust/custClass.html" },
//             { name: "医生排班", icon: "&#xe857;",  id: "0701", url: "HtmlPage/module/custServicce/doctors-scheduling.html" },
//             { name: "用户管理", icon: "&#xe857;",  id: "0803", url: "HtmlPage/module/system/userManagement/userManage.html" },
//             { name: "就诊项目管理", icon: "&#xe857;",  id: "08016", url: "HtmlPage/module/system/projectManage.html" },
//             { name: "系统日志管理", icon: "&#xe857;",  id: "0808", url: "HtmlPage/module/system/common/log.html" }
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
	{ id: "2", name: "退出系统", thumbnail: "Styles/images/iconStyle2/stop.png",  url:"javascript:loginOut()"}
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

function loginOut() {
    $.ajax({
        type:"POST",
        url:basePath+"/login/loginOut",
        success:function(result){

        },
        error:function(){
            layer.msg("失败");
        }
    });
}

/* 图标：http://www.iconfont.cn/
蓝色设备系列
银行logo汇总
天气图标
天气图标组件
 sport
 在线学习图标

 ---------------
 所有官方的图标

*/





