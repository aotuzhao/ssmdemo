<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">

    function addUser() {
        $("#importUser").form("submit", {
            url: "${pageContext.request.contextPath}/user/import",
            onSubmit: function () {
                return $("#importUser").form("validate");
            },
            success: function (data) {
                $("#import").dialog("close");
                $.messager.show({
                    showType: "fade", //slide,fade,show
                    showSpeed: 600,//窗口显示速度
                    title: "系统提示",
                    msg: data
                });
            }

        })
    }
</script>

<form id="importUser" enctype="multipart/form-data" method="post">
    <table>


        <tr>
            <td>上传表格：</td>
            <td>
                <input class="easyui-validatebox" data-options="required:true" type="file" name="user"/>
            </td>
        </tr>


        <tr>
            <td><a href="javascript:void(0)" onclick="addUser()">提交</a></td>
            <td><input type="reset" value="重置"/></td>
        </tr>

    </table>


</form>

