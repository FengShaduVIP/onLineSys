$(function () {
    $("#jqGrid").jqGrid({
        url: '../stuinfo/list?classId='+classId,
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true,hidden:true },
			{ label: '姓名', name: 'stuName', width: 80 },
			{ label: '学号', name: 'stuNo', width: 80 }, 			
			{ label: '班级', name: 'classId', width: 80 }, 			
			{ label: '教师姓名', name: 'teachId', width: 80 }			
        ],
		viewrecords: true,
        height: '400px',
        rowNum: 60,
        rowList : [25,100],
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
        addStu:function (event) {
            location.href = "stuinfo_add.html?classId="+classId;
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
		},
        importStu:function () {
            layer.open({
                type: 2,
                offset: '50px',
                shade: 0,
                title: '导入学生信息',
                area: ['600px', '260px'],
                closeBtn: 2,
                shadeClose: false,
                skin: 'layui-layer-molv',
                content: 'importStu.html?classId='+classId,
            });
        }
	}
});