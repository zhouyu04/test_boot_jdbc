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
<%--大爷进来玩啊！！！--%>

<br/>

<%--<a href="/user/toChat">确认进入</a>--%>

<%--<form name="Form2" action="/user/upload" method="post"  enctype="multipart/form-data">
    <h1>使用spring mvc提供的类的方法上传文件</h1>
    <input type="file" name="file">
    <input type="submit" value="upload"/>
</form>--%>

<%--appID:<input id="appid" value="${appId}" name="" hidden="true"><br>--%>
<%--username:<input id="username" value="${username}" name="" hidden="true"><br>--%>
<%--dbid:<input id="dbid" value="${dbid}" name="" hidden="true"><br>--%>
<%--tenantid:<input id="tenantid" value="${tenantid}" name="" hidden="true"><br>--%>
<a hidden="true" href="/wx/getPreCode?appid=${appId}&username=${username}&dbid=${dbid}&tenantid=${tenantid}" id="a_url">授权</a>
</body>

<script>

    var aLinkDom = document.getElementById('a_url').getAttribute('href')
    if (aLinkDom != null) {
        window.location.href = (aLinkDom)
    }

</script>
</html>
