//用户ID
var userId = T.p("userId");
var vm = new Vue({
    el:'#rrapp',
    data:{
        title:"新增教师账号",
        roleList:{},
        user:{
            status:1,
            roleIdList:[1],
			level:1,
        }
    },
    created: function() {
        if(userId != null){
            this.title = "修改教师信息";
            this.getUser(userId)
        }
        //获取角色信息
        this.getRoleList();
    },
    methods: {
        getUser: function(userId){
            $.get("../sys/user/info/"+userId, function(r){
                vm.user = r.user;
            });
        },
        getRoleList: function(){
            $.get("../sys/role/select", function(r){
                vm.roleList = r.list;
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.user.userId == null ? "../sys/user/save" : "../sys/user/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.user),
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