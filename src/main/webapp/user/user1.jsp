<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<script type="text/javascript">
    $(function () {
        var goEasy = new GoEasy({
            appkey: "BC-6672cd4d416c4980b3921e3f24d341cd"
        });
        $("#send").click(function () {
            var send = $("#input").val();
            var time = new Date();
            var date = time.getFullYear() + "-" + (time.getMonth() + 1) + "-" + time.getDate();
            var day = time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
            goEasy.publish({
                channel: "chat",
                message: "user" + date + " " + day + "<br / >" + send + "<br />"
            });
            $("#input").val("");
        });
        goEasy.subscribe({
            channel: "chat",
            onMessage: function (message) {
                var h = $("#msg").html();
                $("#msg").html(h + "<br/>" + message.content);
            }
        });
    })

</script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="msg" class="divclass5-a" style="background: #6b9cde ;width: 100%;height:90%;margin: 10px auto;overflow:auto">


</div>
<div style="background: #74debc ;width: 50%;margin: 5px auto;">
    <input type="text" id="input"/><input type="submit" id="send"/>
</div>

</body>
</html>
