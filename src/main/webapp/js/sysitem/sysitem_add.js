var id = T.p("id");
function doUpload() {  
    var formData = new FormData($( "#uploadForm" )[0]);  
    var data = CKEDITOR.instances.context.getData();
    formData.set("context",data);
    $.ajax({  
         url: '../sysitem/save' ,  
         type: 'POST',  
         data: formData,  
         async: false,  
         cache: false,  
         contentType: false,  
         processData: false,  
         success: function (returndata) {  
            alert('操作成功', function(index){
				vm.back();
			});
         },  
         error: function (returndata) {  
             alert('操作失败', function(index){
				vm.back();
			});
         }  
    });  
}  

var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		sysItem:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id);
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../sysitem/info/"+id, function(r){
                vm.sysItem = r.sysItem;
            });
		},
		
		saveOrUpdate: function (event) {
			var data = CKEDITOR.instances.context.getData();
			var url = "../sysitem/update";
			vm.sysItem.context = data;
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.sysItem),
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

