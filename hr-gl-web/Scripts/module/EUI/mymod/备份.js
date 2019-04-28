/* 
 * Copyright 2017, by zhangyihao
 * Date: 2018-02-06
 */
//--------------
;eui.define('jquery',function(exports){ //提示：模块也可以依赖其它模块，如：layui.define('layer', callback);
    var $ = eui.$;
    var obj = {
        hello: function(str){
            alert('Heddllo '+ (str||'mymod'));
        }
    };


    //输出test接口
    exports('rightKey', obj);
});


//}).addcss('css相对于这个js的路径');   如果有依赖CSS