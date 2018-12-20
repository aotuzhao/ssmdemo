<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
    $(function () {

    })

    function add() {
        $("#add_banner").form("submit", {
            url: "${pageContext.request.contextPath}/banner/addOne",
            onSubmit: function () {
                return $("#updateF").form("validate");
            },
            success: function (data) {
                $("#banner_tab").edatagrid("load", "${pageContext.request.contextPath}/banner/queryAll");
                $("#banner_dialog").dialog("close");
                showMessage(data);
            }
        })
    }
</script>

<form id="add_banner" enctype="multipart/form-data" method="post">
    <table>
        <tr>
            <td>名称：</td>
            <td>
                <input class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
            </td>
        </tr>
        <tr>
            <td>描述：</td>
            <td>
                <input class="easyui-validatebox" type="text" name="description" data-options="required:true"/>
            </td>
        </tr>
        <tr>
            <td>上传文件：</td>
            <td>
                <input type="file" name="img"/>
            </td>
        </tr>
        <tr>
            <td><a href="javascript:void(0)" onclick="add()">提交</a></td>
            <td><input type="reset" value="重置"/></td>
        </tr>

    </table>


</form>

