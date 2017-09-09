var itemId = T.p("itemId");

var vm = new Vue({
	el:'#rrapp',
	data:{
		sysItem:{}
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
		}
	}
});

