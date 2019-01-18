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

        <p v-for="(item,i) in list">索引值:{{i}} --- 每一项:{{item}}</p>

    </div>

    <script>
        //创建Vue实例,得到viewModel
        var VM = new Vue({
            el:'#app',
            data:{
                list:[1,2,3,4,5,6]
            },
            methods:{}
        })


    </script>

</body>
</html>
