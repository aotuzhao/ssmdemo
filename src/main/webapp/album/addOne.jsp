<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">

    function addAlbum() {
        $("#add_album").form("submit", {
            url: "${pageContext.request.contextPath}/album/addOne",
            onSubmit: function () {
                return $("#add_album").form("validate");
            },
            success: function (data) {
                $("#album_add").dialog("close");
                showMessage(data);
            }
        })
    }
</script>

<form id="add_album" enctype="multipart/form-data" method="post">
    <table>
        <tr>
            <td>名称：</td>
            <td>
                <input class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
            </td>
        </tr>
        <tr>
            <td>作者：</td>
            <td>
                <input class="easyui-validatebox" type="text" name="author" data-options="required:true"/>
            </td>
        </tr>
        <tr>
            <td>上传封面：</td>
            <td>
                <input class="easyui-validatebox" data-options="required:true" type="file" name="img"/>
            </td>
        </tr>
        <tr>
            <td>播音：</td>
            <td>
                <input class="easyui-validatebox" type="text" name="broadcast" data-options="required:true"/>
            </td>
        </tr>
        <tr>
            <td>简介：</td>
            <td>
                <input class="easyui-validatebox" type="text" name="brief" data-options="required:true"/>
            </td>
        </tr>

        <tr>
            <td><a href="javascript:void(0)" onclick="addAlbum()">提交</a></td>
            <td><input type="reset" value="重置"/></td>
        </tr>

    </table>


</form>

