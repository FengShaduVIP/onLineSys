var classId = T.p("classId");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		classInfo:{
            status:1,
		}
	},
	created: function() {
		if(classId != null){
			this.title = "修改";
			this.getInfo(classId)
		}
    },
	methods: {
		getInfo: function(classId){
			$.get("../classInfo/info/"+classId, function(r){
                vm.classInfo = r.classInfo;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.classInfo.classId == null ? "../classInfo/save" : "../classInfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.classInfo),
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