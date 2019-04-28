/* 
 * Copyright 2017, by zhangyihao
 * Date: 2018-02-06
 */
//--------------
;eui.define(['jquery','layer'],function(exports){
    var layer = eui.layer;

    var obj={


        //页面加载
        loading:function (data) {

            //监听加载状态改变
            document.onreadystatechange = completeLoading;

            //加载状态为complete时移除loading效果
            function completeLoading() {
                if (document.readyState == "complete") {
                //    console.log("加载完成");
                    $("#loading").remove();
                }
            }

        },

        //左右选择
        LRchoose:function (data) {
            var active = {
                //全部向右
                removeRightALL: function(othis){
                    $("#selectLeft option").clone().appendTo("#selectRight");
                    $("#selectLeft option").remove();
                },
                //向右
                removeRight: function(othis){
                    $("#selectLeft option:selected").clone().appendTo("#selectRight");
                    $("#selectLeft option:selected").remove();
                },
                //向左
                addLeft: function(othis){
                    $("#selectRight option:selected").clone().appendTo("#selectLeft");
                    $("#selectRight option:selected").remove();
                },
                //全部向左
                addLeftAll: function(othis){
                    $("#selectRight option").clone().appendTo("#selectLeft");
                    $("#selectRight option").remove();
                }
            };

            $('.lrCenter .eui-btn').on('click', function(){
                var othis = $(this), method = othis.data('method');
                active[method] ? active[method].call(this, othis) : '';
            });
        },

        //犹取当前的系统时间
        setTime:function (data) {
            function addZero(e) {
            //    e<0 ? return "0"+ e : return e;
                  if(e<10) return "0"+ e;else return e;
            }
            function currentTime(){
                var d = new Date(),str = '';
                str += d.getFullYear()+'年';
                str  +=  addZero(d.getMonth() + 1)+'月';
                str  +=  addZero(d.getDate())+'日';
                str +=  addZero(d.getHours())+':';
                str  += addZero(d.getMinutes())+':';
                str+= addZero(d.getSeconds())+'';
                return str;
            }
            setInterval(function(){
                $('.'+data).text(currentTime());
            },1000);
        }


    };



    exports("elementExtend",obj)
});