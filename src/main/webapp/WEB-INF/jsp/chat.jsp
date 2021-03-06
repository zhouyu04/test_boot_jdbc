<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/7
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>聊天室</title>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/sockjs.min.js"></script>
    <script type="text/javascript" src="/js/stomp.min.js"></script>
</head>
<body>
<button id="connect">连接</button>
<input type="text" id="connectInput" placeholder="请输入编号1-100">
<br/>
<button id="disconnect">断开连接</button>

<div id="inputDiv">
    <textarea name="" id="message" cols="30" rows="10"></textarea>
    <br/>

    <input type="text" id="send">
    <br>
    <button id="sendButton">发送</button>
</div>

<input id="user-name-label" type="text" readonly="readonly">
</body>

<script type="application/javascript">

    var socket;
    if (typeof(WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    } else {
        console.log("您的浏览器支持WebSocket");
        //实现化WebSocket对象，指定要连接的服务器地址与端口 建立连接
        //等同于socket = new WebSocket("ws://localhost:8083/checkcentersys/websocket/20");
        //socket = new WebSocket("http://localhost:8080/websocket/${cid}".replace("http","ws"));
    }

    //连接
    $("#connect").click(function(){
        var number = $(connectInput).val();
        if (number < 1 || number > 100){
            alert("请输入1-100");
            return;
        }
        socket = new WebSocket("ws://47.107.171.112:8081/websocket/"+number);

        //打开事件
        socket.onopen = function() {
            console.log("已连接");
            $("#connectInput").val("已连接-编号【"+number+"】")
        };

        //获得消息事件
        socket.onmessage = function(msg) {
            console.log(msg.data);
            var exist = $("#message").val() + "\n";
            //发现消息进入 开始处理前端触发逻辑
            var data = msg.data;
            var split = data.split("*当前用户*");
            var message = split[0];
            var username = split[1];
            $("#message").val(exist + message);
            $("#user-name-label").val(username);
        };

        //发生了错误事件
        socket.onerror = function() {
            alert("Socket发生了错误");
            //此时可以尝试刷新页面
        }

        //发送消息事件
        // socket.send = function (msg){
        //     console.log(msg.data);
        // }

        //关闭事件
        socket.onclose = function(socket) {
            console.log("Socket已关闭");
            $("#close").val("Socket已关闭")
        };

        //离开页面时，关闭socket
        $(window).unload(function() {
            socket.close();
        });
    });

    //断开连接
    $("#disconnect").click(function () {
        //关闭事件
        onclose();
    });

    //发送消息
    $("#sendButton").click(function () {
        var message = $("#send").val();
        console.log("message-->"+message);
        if (message == null ){
            alert("发送内容不能为空!");
        }
        //发送消息事件
        socket.send(message);
        $("#send").val("");
    });

    //离开页面时，关闭socket
    $(window).unload(function() {
        socket.close();
    });
</script>
</html>
