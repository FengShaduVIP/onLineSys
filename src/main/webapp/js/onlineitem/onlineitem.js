$(function () {
    $("#jqGrid").jqGrid({
        url: '../sysitem/stuList',
        datatype: "json",
        colModel: [
            { label: '题号', name: 'id', width: 50, key: true },
            { label: '题目标题', name: 'title', width: 200 ,formatter:statusFmt},
            { label: '难度', name: 'level', width: 50 ,formatter:levelFmt},
            { label: '作者', name: 'author', width: 50 },
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
    var v = '<a href="../sysitem/itemDetail.html?itemId='+rowObject.id+'" >'+rowObject.title+'</a>';
    return v;
}

function levelFmt(level) {
    var levelStr = '难';
    if(level==1){
        levelStr='简单';
    }else if(level=2){
        levelStr='一般';
    }else if(level=3){
        levelStr='难';
    }else if(level=4){
        levelStr='困难';
    }
    return levelStr;
}

var vm = new Vue({
    el:'#rrapp',
    data:{

    },
    methods: {


    }
});