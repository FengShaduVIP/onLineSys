var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		stuGrade:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../stugrade/info/"+id, function(r){
                vm.stuGrade = r.stuGrade;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.stuGrade.id == null ? "../stugrade/save" : "../stugrade/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.stuGrade),
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