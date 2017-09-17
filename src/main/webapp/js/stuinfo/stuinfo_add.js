var id = T.p("id");
var classId = T.p("classId");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		stuInfo:{
			classId:classId,
		}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../stuinfo/info/"+id, function(r){
                vm.stuInfo = r.stuInfo;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.stuInfo.id == null ? "../stuinfo/save" : "../stuinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.stuInfo),
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
            console.log(classId);
			history.go(-1);
		},
        getStuInfo : function () {
            layer.confirm('确定要重置密码吗？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                $.get("../stuinfo/getStuInfo?stuNo="+vm.stuInfo.stuNo+"&classId="+vm.stuInfo.classId, function(r){
                    layer.msg("重置密码成功",{time:1500},function () {
                        vm.back();
                    });
                });
            });
        }
	}
});