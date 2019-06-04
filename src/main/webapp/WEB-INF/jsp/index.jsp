<%--
  Created by IntelliJ IDEA.
  User: zzyy
  Date: 2018/11/9
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
    大爷进来玩啊！！！

    <br/>

    <a href="/user/toChat">确认进入</a>

    <form name="Form2" action="/user/upload" method="post"  enctype="multipart/form-data">
        <h1>使用spring mvc提供的类的方法上传文件</h1>
        <input type="file" name="file">
        <input type="submit" value="upload"/>
    </form>

</body>
</html>
