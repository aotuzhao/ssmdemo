<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">

    function addChapter() {
        $("#add_chapter").form("submit", {
            url: "${pageContext.request.contextPath}/chapter/addOne",
            onSubmit: function () {
                return $("#add_chapter").form("validate");
            },
            success: function (data) {
                $("#chapter_add").dialog("close");
                showMessage(data);
            },
            queryParams: {albumId:${param.albumId}}
        })
    }
</script>

<form id="add_chapter" enctype="multipart/form-data" method="post">
    <table>
        <tr>
            <td>名称：</td>
            <td>
                <input class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
            </td>
        </tr>

        <tr>
            <td>上传封面：</td>
            <td>
                <input class="easyui-validatebox" data-options="required:true" type="file" name="audio"/>
            </td>
        </tr>


        <tr>
            <td><a href="javascript:void(0)" onclick="addChapter()">提交</a></td>
            <td><input type="reset" value="重置"/></td>
        </tr>

    </table>


</form>

