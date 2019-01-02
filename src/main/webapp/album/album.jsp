<%@page pageEncoding="UTF-8" %>
<style type="text/css">
    .datagrid-row {
        height: 42px;
    }
</style>
<script type="text/javascript">

    var toolbar = [{
        iconCls: 'icon-add',
        text: "专辑详情",
        handler: function () {
            var row = $("#album").treegrid("getSelected")
            if (row != null) {
                if (row.duration == null) {
                    //打开dialog
                    $("#album_dialog").dialog("open")
                    $("#album_from").form("load", row)
                    $("#coverImg").prop("src", "http://192.168.110.138/" + row.coverImg)
                }
            }
            /* var node = $("#album").treegrid("getSelected");
             if (node != null) {
                 $("#album_detail").dialog({
                     title: '专辑详情',
                     width: 400,
                     height: 200,
                     closed: false,
                     cache: false,
                     href: '/album/detail.jsp?album=' + node,
                     modal: true
                 })

             } else {
                 showMessage("请选择专辑!");
             }*/
        }
    }, '-', {
        text: "添加专辑",
        iconCls: 'icon-edit',
        handler: function () {
            $("#album_add").dialog({
                title: '添加专辑',
                width: 400,
                height: 300,
                closed: false,
                cache: false,
                href: '${pageContext.request.contextPath}/album/addOne.jsp',
                modal: true
            })

        }
    }, '-', {
        text: "添加音频",
        iconCls: 'icon-remove',
        handler: function () {
            var node = $("#album").treegrid("getSelected");
            if (node != null) {
                $("#chapter_add").dialog({
                    title: '添加章节',
                    width: 400,
                    height: 200,
                    closed: false,
                    cache: false,
                    href: '${pageContext.request.contextPath}/chapter/addOne.jsp?albumId=' + node.id,
                    modal: true
                })

            } else {
                showMessage("请选择专辑!");
            }
        }
    }, '-', {
        text: "音频下载",
        iconCls: 'icon-save',
        handler: function () {
            var node = $("#album").treegrid("getSelected");
            if (node != null) {
                location.href = "${pageContext.request.contextPath}/chapter/downLoad?url=" + node.url + "&title=" + node.title;


            } else {
                showMessage("请选择音频!");
            }

        }
    }, '-', {
        text: "导出数据",
        iconCls: 'icon-print',
        handler: function () {
            location.href = "${pageContext.request.contextPath}/album/export";
        }
    }];
    $(function () {
        $('#album').treegrid({
            url: '${pageContext.request.contextPath}/album/queryPage',
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
                {
                    field: 'title', title: '名字', width: 100, formatter: function (value, row, index) {
                        if (row.url != null) {
                            return "<a href='${pageContext.request.contextPath}/chapter/downLoad?url=" + row.url + "'>" + row.title + "</a>"
                        } else {
                            return row.title;
                        }
                    }
                },
                {field: 'duration', title: '时长', width: 80},
                {
                    field: 'url', title: '播放', width: 100, formatter: function (value, row, index) {

                        if (row.url != null) {
                            var path = "${pageContext.request.contextPath}/chapter/audio/" + value;
                            return "<audio style='height:40px;width:220px' controls='controls' src='" + path + "'/>";
                        } else {
                            return "";
                        }
                    }
                },
                {field: 'size', title: '大小', width: 80}
            ]],
            onDblClickRow: function (row) {
                if (row.url != null) {

                    $("#audio_dialog").dialog("open")
                    $("#audio_url").prop("src", "${pageContext.request.contextPath}/chapter/audio/" + row.url)
                }

            }
        });


    });

    function showMessage(msg) {
        $.messager.show({
            showType: "fade", //slide,fade,show
            showSpeed: 600,//窗口显示速度
            title: "系统提示",
            msg: msg
        });
    }
</script>

<table id="album"></table>
<div id="album_detail"></div>
<div id="album_add"></div>
<div id="chapter_add"></div>
<div id="audio_dialog" class="easyui-dialog" title="播放" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <audio id="audio_url" src="" controls="controls" autoplay="autoplay">

    </audio>
</div>
<div id="album_dialog" class="easyui-dialog" title="专辑详情" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">


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

</div>