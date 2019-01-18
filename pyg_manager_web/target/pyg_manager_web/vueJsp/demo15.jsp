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

1.VM和MVVM的区别<hr>
2.学习了Vue中最基本代码的结构
    不要在body里面加,要新建一个跟容器div<hr>
3.插值表达式  v-cloak  v-text  v-html  v-bind(缩写是:)  v-on(缩写是@)  v-model  v-for
    v - if  v-show<hr>
4.事件修饰符 : .stop  .prevent  .capture  .self  .once<hr>
5.el  指定要控制的区域     data  是个对象,制定了控制的区域内要用到的数据
    methods  虽然带了s,但是是个对象:这里自定义了一些方法<hr>
6.在VM实例中,如果要访问data上的数据,或者要访问methods中的方法,必须带this<hr>
7.在v-for中,要会使用key属性(只接受string/number)<hr>
8.v-model 只能运用于表单元素<hr>
9.在vue中绑定样式两种方式 v-bind:class    v-bind:style<hr>







<div id="app"></div>

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
