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

        <%--in 后面我们放过 普通数组,对象数组,对象,还可以放数字--%>
        <%--注意:如果使用 v-for 迭代数字的话,前面的count值从1开始--%>
        <p v-for="count in 10">这是第 {{ count }} 次循环</p>

    </div>

    <script>
        //创建Vue实例,得到viewModel
        var VM = new Vue({
            el:'#app',
            data:{},
            methods:{}
        })


    </script>

</body>
</html>
