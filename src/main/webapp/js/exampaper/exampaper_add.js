var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		examPaper:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id);
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../exampaper/info/"+id, function(r){
                vm.examPaper = r.examPaper;
            });
		},
		saveOrUpdate: function (event) {
			var data = CKEDITOR.instances.examDetail.getData();
			var url = vm.examPaper.id == null ? "../exampaper/save" : "../exampaper/update";
			vm.examPaper.detail = data;
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.examPaper),
			    success: function(r){
			    	if(r.code === 0){
						addItem(r.examPaperId)
					}else{
						alert(r.msg);
					}
				}
			});
		},
		back: function (event) {
			history.go(-1);
		}
	}
});

function addItem (examPaperId) {
    var ids=$('#jqGrid').jqGrid('getGridParam','selarrrow');
    if(ids == null){
        return ;
    }
    var sendData = {
        examId:examPaperId+'',
        itemIds:ids
    }
    $.ajax({
        type: "POST",
        url: "../exampaperitem/saveExamItem",
        data: JSON.stringify(sendData),
        success: function(r){
            if(r.code == 0){
                alert('操作成功', function(index){
                    vm.back();
                });
            }else{
                alert(r.msg);
            }
        }
    });
}

function statusFmt(v) {
    if (v == 0) {
        v = '<i class="fa fa-times" aria-hidden="true"></i>';
        //v = '前台不显示';
    } else {
        v = '<i class="fa fa-check" aria-hidden="true"></i>';
    }
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

$(function () {
    $("#jqGrid").jqGrid({
        url: '../sysitem/listNoPage',
        datatype: "json",
        colModel: [
            { label: '题号', name: 'id', width: '50px', key: true },
            { label: '题目标题', name: 'title', width: '150px'},
            { label: '分数', name: 'score', width: '25px' }
        ],
        viewrecords: true,
        height: '350px',
        autowidth:true,
        rownumbers: true,
        rownumWidth: 25,
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
