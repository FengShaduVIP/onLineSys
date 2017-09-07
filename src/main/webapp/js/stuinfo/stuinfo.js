$(function () {
    $("#jqGrid").jqGrid({
        url: '../stuinfo/list?classId='+classId,
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true,hidden:true },
			{ label: '姓名', name: 'userId', width: 80 }, 			
			{ label: '学号', name: 'stuNo', width: 80 }, 			
			{ label: '班级', name: 'classId', width: 80 }, 			
			{ label: '教师姓名', name: 'teachId', width: 80 }			
        ],
		viewrecords: true,
        height: '85%',
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
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
var classId = T.p("classId");
var vm = new Vue({
	el:'#rrapp',
	data:{
		
	},
	methods: {
        goBack:function () {
            window.history.back();
        },
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			
			location.href = "stuinfo_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../stuinfo/delete",
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