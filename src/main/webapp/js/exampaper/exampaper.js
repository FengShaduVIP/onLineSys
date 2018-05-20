$(function () {
    $("#jqGrid").jqGrid({
        url: '../exampaper/list',
        datatype: "json",
        colModel: [			
			{ label: '试卷编号', name: 'id', width: 20, key: true },
			{ label: '试卷标题', name: 'title', width: 100 },
			{ label: '创建时间', name: 'createTime', width: 30 ,formatter:getLocalTime },
			{ label: '创建者', name: 'authorId', width: 20 }
        ],
        viewrecords: true,
        height: '400px',
        rowNum: 25,
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

function CurentTime()
{
    var now = new Date();

    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日

    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分

    var clock = year + "-";

    if(month < 10)
        clock += "0";

    clock += month + "-";

    if(day < 10)
        clock += "0";

    clock += day + " ";

    if(hh < 10)
        clock += "0";

    clock += hh + ":";
    if (mm < 10) clock += '0';
    clock += mm;
    return(clock);
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		
	},
	methods: {
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			location.href = "exampaper_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../exampaper/delete",
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
        examList :function(event) {
            var examPaperId = getSelectedRow();
            if (examPaperId == null) {
                return;
            }
            location.href = "paper_detail.html?examPaperId="+examPaperId;
        },
        examStart :function (event) {
            var examPaperId = getSelectedRow();
            var rowData = $("#jqGrid").jqGrid('getRowData',examPaperId);
            var examPaperName = rowData.title+ "_" +CurentTime();
            if (examPaperId == null) {
                return;
            }
            layer.open({
                type: 2,
                offset: '50px',
                shade: 0,
                title: '开始考试',
                area: ['700px', '400px'],
                closeBtn: 2,
                shadeClose: false,
                skin: 'layui-layer-molv',
                content: 'start_exam.html?examPaperId='+examPaperId+"&examPaperName="+examPaperName,
            });
        }

	}
});

