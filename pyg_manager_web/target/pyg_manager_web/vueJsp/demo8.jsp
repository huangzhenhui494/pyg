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

        <%--对象就是无序键值对的集合--%>
        <h1 :style="styleObj1">这是一个h111</h1>

        <h1 :style="[styleObj1, styleObj2]">这是h1</h1>

    </div>

    <script>
        //创建Vue实例,得到viewModel
        var VM = new Vue({
            el:'#app',
            data:{
                styleObj1:{ color:'red', 'font-weight':200 },
                styleObj2:{'font-style':'italic'}
            },
            methods:{}
        })


    </script>

</body>
</html>
