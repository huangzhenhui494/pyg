<%--
  Created by IntelliJ IDEA.
  User: Hzh-win10
  Date: 2019/1/9
  Time: 8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Vue</title>
    <script src="js/vue.js"></script>
  </head>
  <body>

  <a href="vueJsp/demo1.jsp">  01 入门-MVVM</a><br>
  <a href="vueJsp/demo2.jsp">  02 v-cloak,v-text,v-html,v-on:click,v-on:mouseover,methods</a><br>
  <a href="vueJsp/demo3.jsp">  03 跑马灯</a><br>
  <a href="vueJsp/demo4.jsp">  04 修饰符</a><br>
  <a href="vueJsp/demo5.jsp">  05 v-model双向绑定</a><br>
  <a href="vueJsp/demo6.jsp">  06 简易计算器</a><br>
  <a href="vueJsp/demo7.jsp">  07 Vue中样式-class</a><br>
  <a href="vueJsp/demo8.jsp">  08 Vue中样式-style</a><br>
  <a href="vueJsp/demo9.jsp">  09 v-for循环普通数组(相当于aj的ng-repeat)</a><br>
  <a href="vueJsp/demo10.jsp"> 10 v-for循环对象数组</a><br>
  <a href="vueJsp/demo11.jsp"> 11 v-for循环对象</a><br>
  <a href="vueJsp/demo12.jsp"> 12 v-for迭代数字</a><br>
  <a href="vueJsp/demo13.jsp"> 13 v-for循环中,key属性的使用</a><br>
  <a href="vueJsp/demo14.jsp"> 14 v-if和v-show的使用</a><br>
  <a href="vueJsp/demo15.jsp"> 15 第一天内容小结</a><br>

  <a href="vueJsp/demo16.html">16 品牌列表案例</a><br>
  <a href="vueJsp/demo17.html">17 过滤器</a><br>
  <a href="vueJsp/demo18.html">18 过滤器,时间格式,keyup事件,自定义键盘码,获取焦点和颜色设置等指令</a><br>
  <a href="vueJsp/demo19.html">19 Vue实例的生命周期函数,创建挂在更新销毁8个</a><br>
  <a href="vueJsp/demo20.html">20 vue-resource基本使用get,post,jsonp</a><br>
  <a href="vueJsp/demo21.html">21 jsonp的原理,需要再百度看看</a><br>

  <a href="vueJsp/demo22.html">22 增删查数据库</a>    **************<br>
  <a href="vueJsp/demo23.html">23 动画,不使用动画</a><br>
  <a href="vueJsp/demo24.html">24 全场动画,使用过渡类名动画,transition元素 enter,leave</a><br>
  <a href="vueJsp/demo25.html">25 全场动画,修改v-前缀 enter,leave</a><br>
  <a href="vueJsp/demo26.html">26 全场动画,使用第三方类实现动画animate,入场离场时间时长等</a><br>
  <a href="vueJsp/demo27.html">27 动画-使用钩子函数模拟小球半场动画</a><br>
  <a href="vueJsp/demo28.html">28 动画-列表动画</a><br>
  <%--组件化和模块化--%>
  <%--组件的出现,就是为了拆分Vue实例的代码量的,能够让我们以不同组件,来划分不同的功能模块,将来
  我们需要什么样的功能,就可以去调用对应的组件即可
  组件化和模块化的不同:
    模块化:是从代码逻辑的角度进行划分的 : 方便戴安分层开发,保证每个功能模块的只能单一
    组件化:是从UI界面的角度进行划分的 : 前端的组件化,方便UI组件的重用
  --%>
  <a href="vueJsp/demo29.html">29 创建组件的方式1</a><br>
  <a href="vueJsp/demo30.html">30 创建组件的方式2</a><br>
  <a href="vueJsp/demo31.html">31 创建组件的方式3</a><br>
  <a href="vueJsp/demo32.html">32 组件中的data和methods</a><br>
  <a href="vueJsp/demo33.html">33 Why components data must be a function,成员和局部变量</a><br>
  <a href="vueJsp/demo34.html">34 组件切换-方式1</a><br>
  <a href="vueJsp/demo35.html">35 组件切换-方式2  <component :is=""></component></a><br>
  <a href="vueJsp/demo36.html">36 组件切换-切换动画</a><br>

  <a href="vueJsp/demo37.html">37 复习-实现小球动画,加入购物车</a><br>
  <a href="vueJsp/demo38.html">38 复习-定义组件的方式</a><br>
  <a href="vueJsp/demo39.html">39 组件-父组件向子组件传值</a><br>
  <a href="vueJsp/demo40.html">40 组件-父组件向子组件传方法</a><br>
  <a href="vueJsp/demo41.html">41 组件案例-评论列表</a><br>
  <a href="vueJsp/demo42.html">42 ref 获取DOM元素和组件</a><br>
  <%--路由--%>
  <a href="vueJsp/demo43.html">43 路由的基本使用(注册登录和选中高亮),修改默认类名</a><br>
  <a href="vueJsp/demo44.html">44 路由规则中定义参数</a><br>
  <a href="vueJsp/demo45.html">45 路由规则传参方式2</a><br>
  <a href="vueJsp/demo46.html">46 路由的嵌套</a><br>
  <a href="vueJsp/demo47.html">47 路由-命名视图实现经典布局</a><br>


  <a href="vueJsp/demo48.html">48 名称案例1 使用@keyup  methods</a><br>
  <a href="vueJsp/demo49.html">49 名称案例2 watch</a><br>
  <a href="vueJsp/demo50.html">50 watch 监视路由地址的改变</a><br>
  <a href="vueJsp/demo51.html">51 名称案例3 computed</a><br>
  <a href="vueJsp/demo52.html">52 watch,computed,methods对比(三种名称案例)</a><br>
  <a href="vueJsp/demo53.html">53 nrm 工具的使用(npm,cnpm,taobao)</a><br>
  <a href="vueJsp/demo54.html">54 webpack安装,案例</a><br>

  <a href="vueJsp/demo55.html">55 Ajax库,axios</a><br>

  <a href="vueJsp/demo56.html">56 在页面中使用render函数渲染组件</a><br>


</body>
</html>
