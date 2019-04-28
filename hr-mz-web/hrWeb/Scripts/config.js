//统一ajax返回封装
(function ($) {
    //允许跨域携带cookie
    $.ajaxSetup({
        type: "POST",
        xhrFields: {withCredentials: true},
        crossDomain: true
    });
    //统一返回封装
    var ajax = $.ajax;
    $.ajax = function (s) {
        var old = s.success;
        s.success = function (data, textStatus, XMLHttpRequest) {
            if (data.code == 401) {//未登录，跳转到登录页面
                layer.alert(data.message);
                setTimeout(function(){
                    window.location.replace("/hrWeb");
                },3000)
            } else if (data.code == 403) {
                layer.alert("您没有权限");
            }
            if(old!=null){
            	 old(data, textStatus, XMLHttpRequest);
            }
           
        }
        ajax(s);
    }


})(jQuery);

