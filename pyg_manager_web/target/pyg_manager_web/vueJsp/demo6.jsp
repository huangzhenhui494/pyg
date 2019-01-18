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
        <input type="text" v-model="n1">

        <select v-model="opt">
            <option value="+">+</option>
            <option value="-">-</option>
            <option value="*">*</option>
            <option value="/">/</option>
        </select>

        <input type="text" v-model="n2">

        <input type="button" value="=" @click="calc">

        <input type="text" v-model="result">
    </div>



    <script>
        //创建Vue实例,得到viewModel
        var VM = new Vue({
            el:'#app',
            data:{
                n1:0,
                n2:0,
                result:0,
                opt:'+'
            },
            methods:{
                calc(){   //计算器算数的方法
   /*                 // 逻辑:判断操作符
                    switch (this.opt) {
                        case '+' :
                            this.result = parseInt(this.n1) + parseInt(this.n2)
                            break;
                        case '-' :
                            this.result = parseInt(this.n1) - parseInt(this.n2)
                            break;
                        case '*' :
                            this.result = parseInt(this.n1) * parseInt(this.n2)
                            break;
                        case '/' :
                            this.result = parseInt(this.n1) / parseInt(this.n2)
                            break;
                    }*/
                }
            }
        })


    </script>

</body>
</html>
