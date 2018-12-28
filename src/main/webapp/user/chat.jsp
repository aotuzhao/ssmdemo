<%@ page contentType="text/html;charset=UTF-8" %>


<script type="text/javascript">

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
            message: "尊敬的管理员" + date + " " + day + "<br / >" + send + "<br />"
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
</script>


<div id="msg" style="background: #6b9cde ;width: 100%;height:90%;margin: 10px auto;">


</div>
<div style="background: #74debc ;width: 50%;margin: 5px auto;">
    <input type="text" id="input"/><input type="submit" id="send"/>
</div>


