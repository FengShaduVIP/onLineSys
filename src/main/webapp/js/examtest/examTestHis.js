$(function () {
    $("#jqGrid").jqGrid({
        url: '../examtest/list',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'id', width: 50, key: true,hidden:true },
            { label: '考试名称', name: 'exam_title', width: 100,formatter:lookDetail},
            { label: '考试开始时间', name: 'start_time', width: 50 },
            { label: '考试结束时间', name: 'end_time', width: 50 },
            { label: '考试状态', name: 'status', width: 50,formatter:statusFmt },
            { label: '创建人', name: 'real_name', width: 30 },
        ],
		viewrecords: true,
        height: '85%',
        rowNum: 25,
        multiselect: true,
		rowList : [25,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
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

function lookDetail(cellvalue, options, rowObject) {
    var v = '<a href="../stugrade/stugrade.html?examTestId='+rowObject.id+'" >'+rowObject.exam_title+'</a>';
    return v;
}


function statusFmt(v) {
	if(v==1){
		return '进行中';
	}else{
		return '已结束';
	}
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		
	},
	methods: {
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../examtest/delete",
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