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
        <%--注意:在遍历对象身上的键值对的时候,除了 有  val,key之外,
            第三个位置还有一个索引
        --%>
        <p v-for="(val,key,index) in user">{{val}} --- {{key}} ---- index:{{index}}</p>

    </div>

    <script>
        //创建Vue实例,得到viewModel
        var VM = new Vue({
            el:'#app',
            data:{
                user:{
                    id:1,
                    name:'黄振辉',
                    gender:'男'
                }
            },
            methods:{}
        })


    </script>

</body>
</html>
