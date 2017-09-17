var examTestId = T.p("examTestId");

function loadData() {
    var classId = document.getElementById("classSelected").value;
    vm.selectedClassId = classId;
    var param = {
        classId :classId,
        examTestId:vm.examTestId
    }
    $("#jqGrid").jqGrid("setGridParam", { postData: param }).trigger("reloadGrid");
}

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
                    var options = [];
                    var classID = 0;
                    if(classList.length>0){
                        for (var i=0;i<classList.length;i++){
                            var value = classList[i];
                            var option = document.createElement("option");
                            if(i==0){
                                option.setAttribute("value",value.class_id);
                                option.text=value.class_name;
                                option.setAttribute("selected","selected");
                                options.push(option);
                                document.getElementById("classSelected").append(option)
                            }else{
                                option.setAttribute("value",value.class_id);
                                option.text=value.class_name;
                                options.push(option);
                                document.getElementById("classSelected").append(option)
                            }
                        };
                       // document.getElementById("classSelected").innerHTML = options;
                        vm.selectedClassId = classList[0].class_id;
                        classID = classList[0].class_id;
                    }
                    vm.loadDataGrid(classID);
                }else{
                    alert("获取班级列表失败")
                }
            });
        },
        loadDataGrid:function (value) {
            $("#jqGrid").jqGrid({
                postData:{
                    classId:value,
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
        },
        outPutGrade :function () {
            var dataGrid = $("#jqGrid").jqGrid("getRowData");
           // var ret=$("#jqGridPager").jqGridPager('getGridParam', 'total');
            /*if(dataGrid.length==0){
                alert("该班级数据为空，不能导出");
                return
            }*/
            var classId = document.getElementById("classSelected").value;
            var url = '../stugrade/outPutGrade?examTestId='+vm.examTestId+'&classId='+classId;
            var fileName = "testAjaxDownload.txt";
            var form = $("<form></form>").attr("action", url).attr("method", "post");
            form.append($("<input></input>").attr("type", "hidden").attr("name", "fileName").attr("value", fileName));
            form.appendTo('body').submit().remove();
        }
	}
});