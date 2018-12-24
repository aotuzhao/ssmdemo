<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">


    $(function () {

        var toolbar = [{
            iconCls: 'icon-add',
            text: "添加",
            handler: function () {
                $("#banner_dialog").dialog({
                    title: '添加轮播图',
                    width: 400,
                    height: 200,
                    closed: false,
                    cache: false,
                    href: '${pageContext.request.contextPath}/banner/addOne.jsp',
                    modal: true
                })
            }
        }, '-', {
            text: "修改",
            iconCls: 'icon-edit',
            handler: function () {
                //获取选中行
                $("#banner_tab").edatagrid("cancelRow");
                var row = $("#banner_tab").edatagrid("getSelected");
                if (row != null) {
                    //编辑指定行
                    var index = $("#banner_tab").edatagrid("getRowIndex", row);
                    $("#banner_tab").edatagrid("editRow", index);
                } else {
                    showMessage("请先选中行！");
                }


            }
        }, '-', {
            text: "删除",
            iconCls: 'icon-remove',
            handler: function () {
                var row = $("#banner_tab").edatagrid("getSelected");
                if (row != null) {
                    var s = $("#banner_tab").edatagrid("destroyRow");
                    console.log(s);
                } else {
                    showMessage("请先选中行！");
                }

            }
        }, '-', {
            text: "保存",
            iconCls: 'icon-save',
            handler: function () {
                $("#banner_tab").edatagrid("saveRow");
                $("#banner_tab").edatagrid("load", "${pageContext.request.contextPath}/banner/queryAll");
                showMessage("更新成功！");

            }
        }, '-', {
            text: "导出数据",
            iconCls: 'icon-print',
            handler: function () {
                location.href = "${pageContext.request.contextPath}/banner/export";

            }
        }]

        $("#banner_tab").edatagrid({
            title: "轮播图管理",
            /*fit: true,*/
            striped: true,
            fitColumns: true,
            pagination: true,
            singleSelect: false,
            toolbar: toolbar,
            pageList: [3, 5, 7],
            pageSize: 3,
            url: "${pageContext.request.contextPath}/banner/queryAll",
            updateUrl: "${pageContext.request.contextPath}/banner/updateOne",
            destroyUrl: "${pageContext.request.contextPath}/banner/deleteOne",
            columns: [[
                {field: "id", title: "ID", width: 100},
                {
                    field: "title", title: "名称", width: 100,
                    align: "left"
                },
                {field: "img_path", title: "路径", width: 100},
                {
                    field: "status", title: "状态", width: 100, editor: {
                        type: "text",
                        options: {required: true}
                    }
                },

            ]],
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/img/' + rowData.img_path + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>描述: ' + rowData.description + '</p>' +
                    '<p>日期: ' + rowData.pub_date + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        })
    })

    function showMessage(msg) {
        $.messager.show({
            showType: "fade", //slide,fade,show
            showSpeed: 600,//窗口显示速度
            title: "系统提示",
            msg: msg
        });
    }
</script>


<table id="banner_tab"></table>
<div id="banner_dialog"></div>

