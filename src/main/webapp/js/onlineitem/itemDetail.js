var itemId = T.p("itemId");

var vm = new Vue({
	el:'#rrapp',
	data:{
        layIndex:-1,
		sysItem:{},
        sendData:{},
	},
	created: function() {
		if(itemId != null){
			this.getInfo(itemId);
		}
    },
	methods: {
		getInfo: function(itemId){
			$.get("../sysitem/info/"+itemId, function(r){
                vm.sysItem = r.sysItem;
            });
		},
		back: function (event) {
			history.go(-1);
		},
        submitItem : function () {
            var submitContext =  document.getElementById("submitContext").value;
            vm.sendData.submitContext=submitContext;
            vm.sendData.itemId=itemId;
            $.ajax({
                type: "POST",
                url: "../sysitem/sumbitItem",
                data: JSON.stringify(vm.sendData),
                dataType: "json",
                beforeSend:function () {
                    vm.layIndex = 	layer.msg('加载中。。。', {icon: 4,time:100000});
                },
                success: function(r){
                    layer.close(vm.layIndex);
                    if(r.code === 0){
                        alert(r.result);
                    }else{
                        alert(r.msg);
                    }
                }
            });
        }
	}
});

