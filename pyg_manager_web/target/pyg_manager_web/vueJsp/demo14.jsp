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

        <%--<input type="button" value="changeFlag" @click="changeFlag">--%>

        <input type="button" value="changeFlag" @click="flag=!flag">

        <%--v-if的特点是每次都会重新删除或创建元素--%>
        <%--v-show的特点:每次不会重新进行DOM的删除和创建操作,只是切换了元素的display:none--%>
        <%--v-if 有较高的切换性能消耗--%>
        <%--v-show 有较高的厨师渲染消耗--%>
        <%--如果元素设计到频繁的切换,最好不要用v-if,推荐用v-show --%>
        <%--如果元素可能永远也不会被显示出来被用户看到,则推荐使用v-if--%>
        <h3 v-if="flag">这是用v-if控制的元素</h3>
        <h3 v-show="flag">这是用v-show控制的元素</h3>

    </div>

    <script>
        //创建Vue实例,得到viewModel
        var VM = new Vue({
            el:'#app',
            data:{
                flag:true
            },
          /*  methods:{
                changeFlag(){
                    this.flag = !this.flag
                }
            }*/
        })


    </script>

</body>
</html>
