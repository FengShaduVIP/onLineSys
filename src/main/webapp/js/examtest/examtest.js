$(function () {
    $.getJSON("../stuinfo/queryStuClass?_"+$.now(), function(r){
        var classId = r.classId;
        $("#jqGrid").jqGrid({
            url: '../examtest/isGoingList?classId='+classId,
            datatype: "json",
            colModel: [
                { label: 'id', name: 'id', width: 50, key: true,hidden:true },
                { label: '考试名称', name: 'exam_title', width: 100, formatter:statusFmt},
                { label: '考试开始时间', name: 'start_time', width: 50 },
                { label: '考试结束时间', name: 'end_time', width: 50 },
                { label: '创建人', name: 'real_name', width: 30 }
            ],
            viewrecords: true,
            height: '430px',
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

});

function statusFmt(cellvalue, options, rowObject) {
    var v = '<a href="../examtest/exam_test_detail.html?id='+rowObject.id+'" >'+rowObject.exam_title+'</a>';
    return v;
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		
	},
	methods: {

	}
});