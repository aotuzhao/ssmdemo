<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/echarts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/china.js"></script>

<div id="user_map" style="width: 95%;height:90%;margin: 10px auto">
</div>
<script type="text/javascript">
    var userChart = echarts.init(document.getElementById("user_map"));
    var time = new Date();
    var date = time.getFullYear() + "-" + (time.getMonth() + 1) + "-" + time.getDate();
    option = {
        title: {
            text: '持明法州APP用户分布图',
            subtext: "截止 " + date,
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            top: 50,
            data: ['男', '女']
        },
        visualMap: {
            min: 0,
            max: 20,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: [
            {
                name: '男',
                type: 'map',
                mapType: 'china',
                roam: true,
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data: []
            },
            {
                name: '女',
                type: 'map',
                mapType: 'china',
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data: []
            }
        ]
    };

    userChart.setOption(option);
    $(function () {

        $.post("${pageContext.request.contextPath}/user/countSex", "sex=男", function (data) {
            userChart.setOption({
                series: [{
                    name: '男',
                    type: 'map',
                    mapType: 'china',
                    roam: true,
                    label: {
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: data
                }]
            });
        }, "json");

        $.post("${pageContext.request.contextPath}/user/countSex", "sex=女", function (data) {
            userChart.setOption({
                series: [{
                    name: '女',
                    type: 'map',
                    mapType: 'china',
                    roam: true,
                    label: {
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: data
                }]
            });
        }, "json");
    });

</script>