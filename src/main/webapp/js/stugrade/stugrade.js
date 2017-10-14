var examTestId = T.p("examTestId");

function loadData(event, treeId, treeNode, clickFlag) {
    vm.selectedClassId = treeNode.id;
    var param = {
        classId :treeNode.id,
        examTestId:vm.examTestId
    }
    $("#jqGrid").jqGrid("setGridParam", { postData: param }).trigger("reloadGrid");
}


function loadDataGrid(treeId) {
    $("#jqGrid").jqGrid({
        postData:{
            classId:treeId,
            examTestId :vm.examTestId
        },
        url: '../stugrade/StuGradeLists',
        datatype: "json",
        colModel: [
            { label: '学号', name: 'stuNo',key: true, width: 80 },
            { label: '姓名', name: 'realName', width: 80 },
            { label: '分数', name: 'score', width: 80 },
            { label: '班级名称', name: 'className', width: 80 }
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
};


var setting = {
    callback: {
        onClick: loadData
    }
};

var classTree;

var vm = new Vue({
	el:'#rrapp',
	data:{
		selectedClassId:0,
        examTestId:examTestId,
	},
    created:function(){
	    this.initDataGrid();
    },
	methods: {
        initDataGrid:function () {
            $.get("../classInfo/listOnAdmin", function(r){
                if(r.code===0){
                    var classList = r.list;
                    var classID = 0;

                    if(classList.length>0){
                        var list = [];
                        for (var i=0;i<classList.length;i++){
                            var classObj = classList[i];
                            if(i==0){
                                var node ={
                                    id:classObj.class_id,
                                    name :classObj.class_name,
                                }
                                list.push(node)
                            }else{
                                var node ={
                                    id:classObj.class_id,
                                    name :classObj.class_name,
                                }
                                list.push(node)
                            }
                        };
                       // document.getElementById("classSelected").innerHTML = options;
                        vm.selectedClassId = classList[0].class_id;
                        loadDataGrid(vm.selectedClassId);
                    }
                    var nodes = [
                        {
                            id:'-1', name: "我的班级",chkDisabled:true, children:list
                        }
                    ]
                    classTree = $.fn.zTree.init($("#classTree"), setting, nodes);
                    classTree.expandAll(true);
                }else{
                    alert("获取班级列表失败")
                }
            });
        },

        outPutGrade :function () {
            var dataGrid = $("#jqGrid").jqGrid("getRowData");
           // var ret=$("#jqGridPager").jqGridPager('getGridParam', 'total');
            /*if(dataGrid.length==0){
                alert("该班级数据为空，不能导出");
                return
            }*/
            var classId =vm.selectedClassId;
            var url = '../stugrade/outPutGrade?examTestId='+vm.examTestId+'&classId='+classId;
            var fileName = "testAjaxDownload.txt";
            var form = $("<form></form>").attr("action", url).attr("method", "post");
            form.append($("<input></input>").attr("type", "hidden").attr("name", "fileName").attr("value", fileName));
            form.appendTo('body').submit().remove();
        }
	}
});