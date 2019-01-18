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

    <%--将来new的Vue实例,会控制这个元素中的所有内容--%>
    <%--Vue 实例所控制的这个元素区域,就是我们的v--%>
    <div id="app">
        <p>{{msg}}</p>
    </div>

    <script>
        //创建一个vue实例
        //当我们导入包之后,在浏览器的内存中就多了一个Vue构造函数
        //注意:我们 new 出来的这个vm 对象,就是我们MVVM中的 VM调度者
        var vm = new Vue({
            el: '#app',                 //表示当前我们new的这个Vue实例,要控制页面上的哪个区域
            //这里的 data 就是 MVVM中的 M,专门保存的是每个页面的数据
            data:{                      //data属性中,存放的是el中要用到的属性
                msg:'欢迎学习Vue'        //通过Vue提供的指令,很方便的就能把数据渲染到页面上,程序员不再手动
                                        //操作DOM元素了[前端Vue之类的框架,不提倡我们去手动操作DOM元素了]

            }
        })
    </script>

</body>
</html>
