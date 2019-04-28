/*--首页JS--*/
eui.use(['element','layer','elementExtend'],function(){
    var $ = eui.jquery
        ,layer = eui.layer
        ,element = eui.element      //Tab的切换功能，切换事件监听等，需要依赖element模块
        ,elementExtend = eui.elementExtend;

    elementExtend.loading();


/*    $(function () {

    });*/

    //初始化首页
  /*  var indexVal = $("ul.navul >li:first-child a span").text(),
        indexUrl = $("ul.navul >li:first-child a").attr("href"),
        indexId=$("ul.navul >li:first-child a").attr("menuid");
*/
    /*   element.tabAdd("nav-tab", {
           title:indexVal
           ,content: "<iframe frameborder=0 class='contentIframe' src='"+ indexUrl +"'></iframe>"
           ,id: indexId
       });*/

  /*  if (indexUrl!=undefined){
        element.tabAdd("nav-tab", {
            title:indexVal
            ,content: "<iframe frameborder=0 name='iframeName' class='contentIframe' src="+indexUrl+"></iframe>"
            ,id: indexId
        });
        element.tabChange("nav-tab", indexId);
    };

    $("#content .eui-tab-title li").trigger("click").addClass("firstNoClose").prepend("<i class='eui-icon'>&#xe622;</i>");

    //点击菜单生成TAB页
    $(document).on("click","#sidebar .rootmenu>li:not([class*='submenuLi'])>a,#sidebar .rootmenu>li[class*='submenuLi'] .submenu li>a,ul.navul >li a.noChilds",function(){
        closeRightContent();
        var othis = $(this);
        var navName = othis.text(),
            aMenuid = othis.attr("menuid"),
            aHref = othis.attr("href");

        if($(".eui-tab-title li[e-id="+ aMenuid+"]").length <= 0){//如果菜单没点过
            //新增一个Tab项
            element.tabAdd("nav-tab", {
                title: navName
                ,content: "<iframe frameborder=0 name='iframeName' class='contentIframe' src='"+ aHref +"'></iframe>"
                ,id: aMenuid
            });

        }else{
        }
        element.tabChange("nav-tab", aMenuid); //切换到当前菜单
        return false;
    });


    //一些事件监听
    element.on('tab(nav-tab)', function(data){

        var eid=$(this).attr("e-id");

        console.log( eid );
        console.log($(".rootmenu li a[menuid="+ eid +"]").html());


        return false;


    });


    //TAB页右键弹出关闭图层
    $(document).on("contextmenu","#content .eui-tab .eui-tab-title li",function(){
        $(".tabContextmenu").remove();
        if($(this).attr('e-id') != 1){   //首页右键不弹出
            $(this).append("" +
                "<dl id='tabContextmenu' class='tabContextmenu eui-nav-child eui-anim eui-anim-upbit'>" +
                "<dd><a href='###' id='closeCur'>关闭此标签页</a></dd>" +
                "<dd><a href='###' id='closeOther'>关闭其它页面</a></dd>" +
                "<dd><a href='###' id='closeAll'>关闭所有页面</a></dd>" +
                "<dd><a href='###' id='refresh'>刷新当前页面</a></dd>" +
                "</dl>");
            $(".tabContextmenu").addClass("eui-show");
        }
        return false;
    });

    //点击关闭当前TAB页
    $(document).on("click","#closeCur",function(){
        var eid = $(this).closest("li").attr("e-id");
        element.tabDelete("nav-tab",eid);
    });

    //点击关闭其它TAB页面
    $(document).on("click","#closeOther",function(){
        var eid = $(this).closest("li").attr("e-id");
        $("#content .eui-tab .eui-tab-title li").each(function(){
            if($(this).attr("e-id") != '1'){
                if($(this).attr("e-id")!=eid){
                    element.tabDelete("nav-tab",$(this).attr("e-id")).init();
                }
            }
        })
    });

    //点击关闭除了首页的其它TAB页面
    $(document).on("click","#closeAll",function(){
        $("#content .eui-tab .eui-tab-title li").each(function(){
            if($(this).attr("e-id") != 1){
                element.tabDelete("nav-tab",$(this).attr("e-id")).init();
            }
        })
    });

    //点击刷新当前页面
    $(document).on("click","#refresh",function(){
        var liIndex = $(this).closest("li").index();
        var refreshIframe= $("#content .eui-tab-content > div").eq(liIndex).children('iframe');
        refreshIframe.attr('src', refreshIframe.attr('src'));
    });

    //点击其它区域进行关闭
    $(document).bind("click",function(){
        closeRightContent();
    });

    //点击其它区域进行关闭
    function closeRightContent(){
        var target = $(target);
        if(target.closest(".tabContextmenu").length == 0){
            $(".tabContextmenu").remove();
        }
    };

    closeRC = function(){
        closeRightContent();
    };

    //点击右侧页面的按钮可新建TAB页标签，接收返回信息
    active = function (a, b, c) {
        if($(".eui-tab-title li[e-id="+ c +"]").length <= 0){//如果菜单不存在
            //新增一个Tab项
            element.tabAdd('nav-tab', {
                title: a //用于演示
                ,content: "<iframe frameborder=0 class='contentIframe' src='" + b + "'></iframe>"
                ,id: c //实际使用一般是规定好的id，这里以时间戳模拟下
            });
        }else{
        }
        element.tabChange("nav-tab", c); //切换到当前菜单
        return false;
    };

*/

});



