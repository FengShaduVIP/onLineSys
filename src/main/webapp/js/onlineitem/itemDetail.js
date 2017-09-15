var itemId = T.p("itemId");

var vm = new Vue({
	el:'#rrapp',
	data:{
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
                url: "../sysitem/sumbitItemTest",
                data: JSON.stringify(vm.sendData),
                dataType: "json",
                success: function(r){
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

