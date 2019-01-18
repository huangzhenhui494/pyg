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

        <p v-for="(user,i) in list">索引:{{i}}----id:{{user.id}},name:{{user.name}}</p>

    </div>

    <script>
        //创建Vue实例,得到viewModel
        var VM = new Vue({
            el:'#app',
            data:{
                list:[
                    {id:1,name:"zs1"},
                    {id:2,name:"zs2"},
                    {id:3,name:"zs3"},
                    {id:4,name:"zs4"}
                ]
            },
            methods:{}
        })


    </script>

</body>
</html>
