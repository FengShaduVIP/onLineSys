$(function () {
    $("#jqGrid").jqGrid({
        url: '../classInfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'classId', name: 'classId',hidden:true, width: 50, key: true },
			//{ label: '班级编号', name: 'classNo', width: 80 },
			{ label: '班级名称', name: 'className', width: 80 },
            { label: '创建者', name: 'realName', width: 50 },
            { label: '创建时间', name: 'createTime', width: 50 },
            { label: '班级届', name: 'createYear', width: 80 },
            { label: '状态', name: 'status', width: 80,
                formatter: function(value, options, row){
                    return value === 0 ?
                        '<span class="label label-danger">禁用</span>' :
                        '<span class="label label-success">正常</span>';
                }
            }
        ],
		viewrecords: true,
        height: '400px',
        rowNum: 50,
        rowList : [25,50],
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
function getLocalTime(nS) {
    return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');
}
var vm = new Vue({
	el:'#rrapp',
	data:{
	},
	methods: {
		update: function (event) {
			var classId = getSelectedRow();
			if(classId == null){
				return ;
			}
			location.href = "classinfo_add.html?classId="+classId;
		},
		del: function (event) {
			var classIds = getSelectedRows();
			if(classIds == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../classInfo/delete",
				    data: JSON.stringify(classIds),
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
        lookClassStu:function(){
            var classId = getSelectedRow();
            if(classId == null){
                return ;
            }
            location.href = "../stuinfo/stuinfo.html?classId="+classId;
        },
        downloadFile:function () {
            var url = '../stuinfo/fileDownload';
            var fileName = "学生导入模板.xls";
            var form = $("<form></form>").attr("action", url).attr("method", "post");
            form.append($("<input></input>").attr("type", "hidden").attr("name", "fileName").attr("value", fileName));
            form.appendTo('body').submit().remove();
        }
	},
});

