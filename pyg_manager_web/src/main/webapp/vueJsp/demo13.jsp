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

        <div>
            <label>Id:
                <input type="text" v-model="id">
            </label>
            <label>Name:
                <input type="text" v-model="name">
            </label>

            <input type="button" value="添加" @click="add">
        </div>

        <%--注意:v-for 循环的时候,key属性只能使用number或string--%>
        <%--注意:key 在使用的时候必须使用v-bind 属性绑定的形式指定key值--%>
        <%--在组件中,使用v-for循环的时候,或者在一些特殊情况中,如果v-for
        有问题,必须 在使用v-for的同时指定唯一的字符串或数字类型的key值: key 值
        --%>
        <p v-for="item in list" :key="item.id">
            <input type="checkbox">{{item.id}} --- {{item.name}}
        </p>

    </div>

    <script>
        //创建Vue实例,得到viewModel
        var VM = new Vue({
            el:'#app',
            data:{

                id:'',
                name:'',
                list:[
                    {id:1,name:'李斯'},
                    {id:2,name:'嬴政'},
                    {id:3,name:'赵高'},
                    {id:4,name:'韩非'},
                    {id:5,name:'荀子'}
                ]
            },
            methods:{
                add(){
                    this.list.push({id:this.id,name:this.name})
                    /*或者unshift*/
                }
            }
        })


    </script>

</body>
</html>
