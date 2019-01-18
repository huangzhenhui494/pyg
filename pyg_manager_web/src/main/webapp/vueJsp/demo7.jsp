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
    <script src="../js/vueJs/.js"></script>
    <style>
        .red{
            color: red;
        }

        .thin{
            font-weight: 200;
        }

        .italic{
            font-style:italic;
        }

        .active{
            letter-spacing: 0.5em;
        }
    </style>
</head>
<body>

    <div id="app">

        <%--在数组中使用三元表达式--%>
        <h1 :class="['thin','red',flag?'active':'']">黄振辉!!!</h1>

        <%--在数组中使用 对象来代替三元表达式,提高代码的可读性--%>
        <h1 :class="['thin','red',{'active':flag}]">黄振辉!!!</h1>

        <%--
            在为 class 使用v-bind 绑定对象的时候,对象的属性是类名,
            由于对象的属性可带引号,也可不带引号,所以这里没引号,属性的值是标识符
        --%>
        <h1 :class="{red:true,thin:true,italic:true,active:true}">黄振辉!!!</h1>

        <h1 :class="classObj">黄振辉!!!</h1>




    </div>

    <script>
        //创建Vue实例,得到viewModel
        var VM = new Vue({
            el:'#app',
            data:{
                flag:false,
                classObj:{red:true,thin:true,italic:true,active:true}
            },
            methods:{}
        })


    </script>


</body>
</html>
