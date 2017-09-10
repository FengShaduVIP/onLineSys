var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		classTest:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../classtest/info/"+id, function(r){
                vm.classTest = r.classTest;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.classTest.id == null ? "../classtest/save" : "../classtest/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.classTest),
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