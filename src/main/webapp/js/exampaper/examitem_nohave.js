$(function () {
    $("#jqGridItem_nohave").jqGrid({
        url: '../exampaper/list',
        datatype: "json",
        colModel: [
            { label: '题目编号', name: 'id', width: 10, key: true },
            { label: '题目标题', name: 'title', width: 50 },
            { label: '题目分值', name: 'score', width: 50 },
            //{ label: '试卷要求', name: 'detail', width: 80 },
            { label: '创建时间', name: 'createTime', width: '20%' },
            { label: '作者', name: 'authorId', width: '15%' }
        ],
        viewrecords: true,
        height: 'auto',
        //autowidth:true,
        //width:'auto',
        //shrinkToFit: true,
        rowNum: 25,
        rowList : [25,50],
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
			$.ajax({
				type: "POST",
			    url: "../sysitem/delete", 
			    data: JSON.stringify(ids),
			    success: function(r){
					if(r.code == 0){
						alert('操作成功', function(index){
							$("#jqGrid").trigger("reloadGrid");
						});
					}else{
						alert(r.msg);
					}
				}
			});
		}
	}
});