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
			var data = CKEDITOR.instances.context.getData();
			var url = vm.examPaper.id == null ? "../exampaper/save" : "../exampaper/update";
			vm.examPaper.detail = data;
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.examPaper),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.back();
						});
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