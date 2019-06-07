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


    <div style="margin-left:20px;margin-top: 20px">
        <form name="Form2" action="/express/upload" method="post" enctype="multipart/form-data">
            <input type="file" name="file" value="请选择文件"><input type="submit" id="upload" value="上传"/>
        </form>
    </div>

    <br/>
    <a href="/express/toTest">快递结果查询</a>

    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

    <a href="/user/index">请勿点击</a>


</body>
</html>
