<%@page pageEncoding="UTF-8" %>

<script type="text/javascript">


    var toolbar = [{
        text: "导出数据",
        iconCls: 'icon-print',
        handler: function () {
            location.href = "${pageContext.request.contextPath}/user/export";
        }
    }, '-', {
        text: "导入数据",
        iconCls: 'icon-print',
        handler: function () {
            $("#import").dialog({
                title: '导入用户',
                width: 400,
                height: 200,
                cache: false,
                href: "${pageContext.request.contextPath}/user/addOne.jsp",
                modal: true
            })

        }
    }];
    $(function () {
        $('#user_table').datagrid({
            url: '${pageContext.request.contextPath}/user/queryPage',
            idField: 'id',
            treeField: 'title',
            striped: true,
            fit: true,
            fitColumns: true,
            pagination: true,
            toolbar: toolbar,
            pageList: [3, 5, 7],
            pageSize: 3,
            columns: [[
                {field: 'name', title: '名字', width: 100},
                {field: 'sex', title: '性别', width: 80},
                {field: 'province', title: '省份', width: 100},
                {field: 'city', title: '城市', width: 80},
                {field: 'regDate', title: '注册时间', width: 80}
            ]]
        });


    });


</script>
<table id="user_table"></table>
<div id="import"></div>



