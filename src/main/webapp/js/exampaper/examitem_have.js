$(window).resize(function(){
    $(window).unbind("onresize");
    $(window).bind("onresize", this);
});
$(function () {
    $("#jqGridItem_have").jqGrid({
        url: '../exampaper/list',
        datatype: "json",
        colModel: [
            { label: '试卷编号', name: 'id', width: 10, key: true },
            { label: '试卷标题', name: 'title', width: 50 },
            //{ label: '试卷要求', name: 'detail', width: 80 },
            { label: '创建时间', name: 'createTime', width: 20 },
            { label: '作者', name: 'authorId', width: 15 }
        ],
        viewrecords: true,
        height: 'auto',
        //autowidth:true,
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
			confirm('确定要删除选中的记录？', function(){
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
			});
		}
	}
});