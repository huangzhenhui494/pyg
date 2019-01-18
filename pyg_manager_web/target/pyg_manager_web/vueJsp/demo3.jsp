<%--
  Created by IntelliJ IDEA.
  User: Hzh-win10
  Date: 2019/1/9
  Time: 8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>demo1</title>
    <script src="../js/vueJs/vue.js"></script>
</head>
<body>

    <div id="app">
        <input type="button" value="浪起来" @click="lang">
        <input type="button" value="低调" @click="stop">
        <h4>{{msg}}</h4>
    </div>

    <script>
        //注意:在VM实例中,如果想要调用data上的数据,或者想要调用methods中的方法,必须通过
        //this.数据属性名  或 this.方法名
        var vm = new Vue({
            el:'#app',
            data:{
                msg:'猥琐发育,别浪!!',
                intervalId:null //在data上定义 定时器id
            },
            methods:{
                lang () {
                    if (this.intervalId != null)return;
                    this.intervalId = setInterval( () => {
                        //获取到头的第一个字符
                        var start = this.msg.substring(0,1)
                        //获取到后面的所有字符
                        var end = this.msg.substring(1)
                        //重新拼接得到新的字符串,并赋值给this.msg
                        this.msg = end + start
                    },400)

                    //注意: VM实例,会监听自己身上data中所有数据的改变,
                    //只要数据一发生变化,就会自动把最新的数据,从data上同步到页面中去
                    //好处:程序员只需要数据,不需要考虑如何重新渲染到页面
                },
                stop(){ //停止定时器
                    clearInterval(this.intervalId)
                    //清楚定时器重新为null
                    this.intervalId = null;
                }
            }
        })


        //分析:
        //1.给[浪起来]绑定一个点击事件
        //2.在按钮的事件处理函数中,写相关的业务逻辑代码:拿到msg字符串,然后调用字符串的
        //substring来进行字符串的截取操作,把第一个字符截取放到最后一个位置即可:
        //.3为了实现点击一下按钮,自动截取的功能,需要把2步骤中的代码放到定时器里
    </script>

</body>
</html>
