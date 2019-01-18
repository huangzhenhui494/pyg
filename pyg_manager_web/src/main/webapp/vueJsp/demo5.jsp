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
        <h4>{{msg}}</h4>

        <%-- v-bind: 只能实现数据的单向绑定,从M自动绑定到V--%>
        <%--<input type="text" :value="msg" style="width: 50%;">--%>

        <%--使用v-model指令 : 可以实现,表单元素和model找那个数据的双向绑定--%>
        <%--注意:v-model 只能运用在表单元素中--%>
        <%--input(radio,text,address,email...),select,checkbox,textarea--%>
        <input type="text" style="width: 50%;" v-model="msg">

    </div>

    <script>
        //创建Vue实例,得到viewModel
        var VM = new Vue({
            el:'#app',
            data:{
                msg:'大家都是好学生大家都是好学生大家都是好学生大家都是好学生'
            },
            methods:{}
        })


    </script>

</body>
</html>
