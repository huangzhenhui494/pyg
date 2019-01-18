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
    <style>
        .inner{
            height: 150px;
            background-color: darkcyan;
        }

        .outer{
            padding: 40px;
            background-color: red;
        }
    </style>
</head>
<body>
    <div id="app">
            <%--stop,点击btn时,不触发外层div--%>
        <%--<div class="inner" @click.capture="div1Handler"> </div>--%>
        <%--<input type="button" value="点我" @click.stop="btnHandler">--%>

           <%--使用.prevent组织默认行为--%>
        <%--<a href="http://www.baidu.com" @click.prevent="linkClick">百度一下</a>--%>

            <%--使用 .capture实现捕获触发,从外到里--%>
        <%--<div class="inner" @click.capture="div1Handler">
            <input type="button" value="点我" @click.stop="btnHandler">
        </div>--%>

            <%--使用 .self实现只有点击当前元素时候才会触发事件处理函数--%>
        <%--<div class="inner" @click.self="div1Handler">
            <input type="button" value="点我" @click="btnHandler">
        </div>--%>

            <%--使用 .once 只触发一次事件处理函数--%>
        <%--<a href="http://www.baidu.com" @click.prevent.once="linkClick">百度一下</a>--%>

            <%--演示： stop和.self的区别--%>

            <%--stop只执行自己的--%>
       <%--<div class="outer" @click="div2Handler">--%>
           <%--<div class="inner" @click="div1Handler">--%>
               <%--<input type="button" value="点我" @click.stop="btnHandler">--%>
           <%--</div>--%>
       <%--</div>--%>

            <%--只负责自己--%>
       <div class="outer" @click="div2Handler">
           <div class="inner" @click.self="div1Handler">
               <input type="button" value="点我" @click="btnHandler">
           </div>
       </div>
        <%--.self只会阻止自己身上冒泡行为的触发,并不会真正阻止冒泡的行为--%>

    </div>

    <script>
        //创建Vue实例,得到viewModel
        var VM = new Vue({
            el:'#app',
            data:{},
            methods:{
                div1Handler(){
                    alert("触发inner div的点击")
                },
                btnHandler(){
                    alert("btn")
                },
                linkClick(){
                    alert('触发连接的点击')
                },
                div2Handler(){
                    alert('触发outerdiv的点击')
                }
            }
        })


    </script>

</body>
</html>
