$(function () {
    $("#jqGrid").jqGrid({
        url: '../stugrade/StuGradeList',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true,hidden:true },
			{ label: '考试名称', name: 'exam_title', width: 80, formatter:statusFmt},
			{ label: '分数', name: 'score', width: 80 },
			{ label: '考试时间', name: 'create_time', width: 80 }
        ],
		viewrecords: true,
        height: '400px',
        rowNum: 10,
		rowList : [10,30,50],
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

//点击题目标题跳转题目详情 做练习
function statusFmt(cellvalue, options, rowObject) {
    var v = '<a href="../stugrade/examTestMy.html?examTestId='+rowObject.examTestId+'" >'+rowObject.exam_title+'</a>';
    return v;
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		
	},
	methods: {

	}
});