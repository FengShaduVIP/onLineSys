var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		examTest:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../examtest/info/"+id, function(r){
                vm.examTest = r.examTest;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.examTest.id == null ? "../examtest/save" : "../examtest/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.examTest),
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