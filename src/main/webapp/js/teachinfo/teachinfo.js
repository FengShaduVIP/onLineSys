$(function () {
    $("#jqGrid").jqGrid({
        url: '../sys/user/teachList',
        datatype: "json",
        colModel: [			
			{ label: 'userId', name: 'userId', width: 50,hidden:true, key: true },
			{ label: '用户名', name: 'username', width: 80 },
			{ label: '姓名', name: 'realName', width: 80 },
            { label: '手机号', name: 'mobile', width: 80 },
            { label: '邮箱', name: 'email', width: 80 },
            { label: '状态', name: 'status', width: 80,
				formatter: function(value, options, row){
					return value === 0 ?
						'<span class="label label-danger">禁用</span>' :
						'<span class="label label-success">正常</span>';
            	}
            },
            { label: '创建时间', name: 'createTime', width: 80}
        ],
		viewrecords: true,
        height: 400,
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		
	},
	methods: {
		update: function (event) {
			var teachId = getSelectedRow();
			if(teachId == null){
				return ;
			}
			
			location.href = "teachInfo_add.html?teachId="+teachId;
		},
		del: function (event) {
			var teachIds = getSelectedRows();
			if(teachIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sys/user/delete",
				    data: JSON.stringify(teachIds),
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