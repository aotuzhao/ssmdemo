<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript">
        var goEasy = new GoEasy({
            appkey: "BC-6672cd4d416c4980b3921e3f24d341cd"
        });
        goEasy.subscribe({
            channel: "test1",
            onMessage: function (message) {
                alert("Channel:" + message.channel + " content:" + message.content);
            }
        });
    </script>
</head>
<body>

</body>
</html>
