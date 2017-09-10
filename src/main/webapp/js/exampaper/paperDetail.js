var examPaperId = T.p("examPaperId");
$(function () {
    $("#jqGridItem_have").jqGrid({
        url: '../exampaperitem/examItemList?exmaId='+examPaperId,
        datatype: "json",
        colModel: [
            { label: '试卷编号', name: 'id', width: 10, key: true },
            { label: '试卷标题', name: 'title', width: 50 },
            { label: '创建时间', name: 'createTime', width: 20 },
            { label: '作者', name: 'authorId', width: 15 }
        ],
        viewrecords: true,
        height: '400px',
        autowidth:true,
        shrinkToFit: true,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        multiselect: true,
        pager: "#jqGridPagerItem_have",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });

    $("#jqGridItem_nohave").jqGrid({
        url:'../exampaperitem/unExamItemList?exmaId='+examPaperId,
        datatype: "json",
        colModel: [
            { label: '试卷编号', name: 'id', width: '10%', key: true },
            { label: '试卷标题', name: 'title', width: '50%' },
            //{ label: '试卷要求', name: 'detail', width: 80 },
            { label: '创建时间', name: 'createTime', width: '20%' },
            { label: '作者', name: 'authorId', width: '15%' }
        ],
        viewrecords: true,
        height: '350px',
        autowidth:true,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        multiselect: true,
        pager: "#jqGridPagerItem_nohave",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vm_have = new Vue({
	el:'#exampaperItem_have',
	data:{
		
	},
	methods: {
		delItem: function (event) {
			var ids=$('#jqGridItem_have').jqGrid('getGridParam','selarrrow');
			if(ids == null){
				return ;
			}
            var sendData = {
                exmaId:examPaperId,
                itemIds:ids
            }
			confirm('确定要删除选中的题目？', function(){
				$.ajax({
					type: "POST",
				    url: "../exampaperitem/deleteExamItem",
				    data: JSON.stringify(sendData),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
                                $("#jqGridItem_have").trigger("reloadGrid");
                                $("#jqGridItem_nohave").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		}
	}
});


var vm_nohave = new Vue({
    el:'#exampaperItem_nohave',
    data:{

    },
    methods: {
        addItem: function (event) {
            var ids=$('#jqGridItem_nohave').jqGrid('getGridParam','selarrrow');
            if(ids == null){
                return ;
            }
            var sendData = {
                exmaId:examPaperId,
                itemIds:ids
            }
            $.ajax({
                type: "POST",
                url: "../exampaperitem/saveExamItem",
                data: JSON.stringify(sendData),
                success: function(r){
                    if(r.code == 0){
                        alert('操作成功', function(index){
                            $("#jqGridItem_have").trigger("reloadGrid");
                            $("#jqGridItem_nohave").trigger("reloadGrid");
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        }
    }
});