<%@ page contentType="text/html;charset=UTF-8" %>


<div id="user_count" style="width: 80%;height:90%;margin: 10px auto">
</div>
<script type="text/javascript">
    var userChart = echarts.init(document.getElementById("user_count"));
    var time = new Date();
    var date = time.getFullYear() + "-" + (time.getMonth() + 1) + "-" + time.getDate();
    option = {
        title: {
            text: '持明法州APP用户注册量统计分析',
            subtext: "截止 " + date
        },
        tooltip: {},
        legend: {
            data: ['男', "女", "不限"]
        },
        xAxis: {
            data: ["7天", "15天", "1月", "3月", "半年", "一年"]
        },
        yAxis: {},
        series: []
    };

    userChart.setOption(option);
    $(function () {
        $.post("${pageContext.request.contextPath}/user/countReg", function (data) {
            userChart.setOption({
                series: [{
                    name: '男',
                    type: 'bar',
                    data: data.man
                }, {
                    name: '女',
                    type: 'bar',
                    data: data.female
                }, {
                    name: '不限',
                    type: 'bar',
                    data: data.allUser
                }]
            });
        }, "json");
        var goEasy = new GoEasy({
            appkey: "BC-6672cd4d416c4980b3921e3f24d341cd"
        });
        goEasy.subscribe({
            channel: "countReg",
            onMessage: function (message) {
                //console.log( message.content);
                var data = eval("(" + message.content + ")");
                //console.log(data.man);
                userChart.setOption({
                    series: [{
                        name: '男',
                        type: 'bar',
                        data: data.man
                    }, {
                        name: '女',
                        type: 'bar',
                        data: data.female
                    }, {
                        name: '不限',
                        type: 'bar',
                        data: data.allUser
                    }]
                });
            }
        });

    })

</script>