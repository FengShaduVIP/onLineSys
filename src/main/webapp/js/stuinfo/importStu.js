var id = T.p("classId");
function doUpload() {  
    var formData = new FormData($( "#uploadForm" )[0]);  
    $.ajax({
         url: '../sysitem/importExcel' ,
         type: 'POST',  
         data: formData,  
         async: false,  
         cache: false,  
         contentType: false,  
         processData: false,  
         success: function (returndata) {  
            alert('操作成功', function(index){
				//vm.back();
			});
         },  
         error: function (returndata) {  
             alert('操作失败', function(index){
				//vm.back();
			});
         }  
    });  
}  

var vm = new Vue({
	el:'#rrapp',
	data:{
		classId:id
	},
	created: function() {

    },
	methods: {
		back: function (event) {
			history.go(-1);
		}
	}
});

