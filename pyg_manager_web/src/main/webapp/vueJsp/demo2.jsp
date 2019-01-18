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
    <style>
        [v-cloak]{
            display: none;
        }
    </style>
    <script src="../js/vueJs/vue.js"></script>
</head>
<body>

    <div id="app">
        <%--使用 v-cloak 能够解决 插值表达式闪烁问题--%>
        <p v-cloak>{{msg}}</p>
        <%-- 默认v-text 是没有闪烁问题的--%>
        <%--v-text 会覆盖元素中原本的内容,但是,插值表达式 只会替换自己的这个占位符--%>
        <h4 v-text="msg"></h4>

        <div>{{msg2}}</div>
        <div v-text="msg2"></div>
        <%--这两个都会把东西当成文本格式--%>
        <div v-html="msg2"></div>

        <%-- v-bind: 是Vue中,提供的用于绑定属性的指令--%>
        <%--还可以加上表达式--%>
        <%--<input type="button" value="按钮" v-bind:title="mytitle + '123'">--%>
            <%--注意:v-bind: 指令可以被简写为 :要绑定的属性--%>
            <%-- v-bind 中,可以写合法的js表达式--%>

            <%--Vue 中提供了 v-on:事件绑定机制--%>
        <%--<input type="button" value="按钮" :title="mytitle + '12322'">--%>
        <input type="button" value="按钮" v-on:click="show" @mouseover="show">

    </div>

    <script>

        var vm = new Vue({
            el:'#app',
            data:{
                msg:'123',
                msg2:'<h1>哈哈</h1>',
                mytitle:'这是一个自己定义的title'
            },
            methods:{  //这个methods属性中定义了当前Vue实例所有可用的方法
                show:function(){
                    alert('hello')
                }
            }
        })

    </script>

</body>
</html>
