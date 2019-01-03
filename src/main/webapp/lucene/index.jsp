<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="always" name="referrer">
    <title>百知一下，你就知道</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
        $(function () {

            $("#su").click(function () {

                $('#lu').datagrid({
                    url: '${pageContext.request.contextPath}/lucene/search',
                    idField: 'id',
                    treeField: 'title',
                    striped: true,
                    fit: true,
                    fitColumns: true,
                    pagination: true,
                    pageList: [3, 5, 7],
                    pageSize: 3,
                    columns: [[
                        {field: 'name', title: '名字', width: 100},
                        {field: 'price', title: '价格', width: 80},
                        {field: 'description', title: '描述', width: 100},
                        {
                            field: 'imgPath', title: '图片', formatter: function (value, row, index) {
                                var url = "http://192.168.110.138/" + row.imgPath;
                                return "<img src='" + url + "' width='100'/>";
                            }, width: 80
                        },
                    ]],
                    queryParams: {keywords: $("#keywords").val()}
                });

            });


        })
    </script>
<body>
<div id="head_wrapper">
    <div id="s_fm">
        <div id="s_form_wrapper">
            <div id="lg">
                <img id="s_lg_img" src="./index_files/logo.png">
            </div>
            <form id="form" method="post" class="fm">
                <input type="text" id="keywords" class="s_ipt" name="keywords" id="kw" maxlength="100"
                       autocomplete="off">
                <input type="button" value="搜索一下" id="su" class="btn self-btn bg s_btn">
            </form>

        </div>
    </div>
</div>


<hr>


<table id="lu"></table>


</body>
</html>