<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
    $(function () {

        $.get("${pageContext.request.contextPath}/album/queryOne", "id=" +${param.id}, function (album) {
            $("#album_from").form("load", album);
            $("#coverImg").prop("src", "${pageContext.request.contextPath}/album/image/" + album.coverImg);

        })
    })


</script>

<form id="album_from">
    <table>
        <tr>
            <td>名称：</td>
            <td>
                <input type="text" name="title" disabled="disabled"/>
            </td>
        </tr>
        <tr>
            <td>封面：</td>
            <td>
                <input type="text" name="coverImg" disabled="disabled"/>
                <img id="coverImg" style="height:50px;"/>
            </td>
        </tr>
        <tr>
            <td>作者：</td>
            <td>
                <input type="text" name="author" disabled="disabled"/>
            </td>
        </tr>
        <tr>
            <td>播音：</td>
            <td>
                <input type="text" name="broadcast" disabled="disabled"/>
            </td>
        </tr>
        <tr>
            <td>集数：</td>
            <td>
                <input type="text" name="count" disabled="disabled"/>
            </td>
        </tr>
        <tr>
            <td>评分：</td>
            <td>
                <input type="text" name="score" disabled="disabled"/>
            </td>
        </tr>
        <tr>
            <td>简介：</td>
            <td>
                <input type="text" name="brief" disabled="disabled"/>
            </td>
        </tr>
        <tr>
            <td>创建时间：</td>
            <td>
                <input type="text" name="pubDate" disabled="disabled"/>
            </td>
        </tr>

    </table>


</form>

