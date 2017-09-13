var id = T.p("id");

var examItem = Vue.extend({
    name: 'exam-item',
    props: {
    	item:{},
	},
    template: [
			'<div class="layui-colla-item">',
				'<h2 class="layui-colla-title">题目标题：{{item.title}}</h2>',
				'<div class="layui-colla-content">',
					'<div v-html="item.context"></div>',
					'<div class="layui-form-item layui-form-text" style="padding-top: 20px">',
						'<div class="layui-input-block">',
							'<textarea :id="item.id"  :key="item.id" placeholder="请输入提交内容" class="layui-textarea"></textarea>',
							'<button @click="sumbitItem(item.id)">提交</button>',
						'</div>',
					'</div>',
				'</div>',
			'</div>',
	].join('')
});

//注册试卷题目组件
Vue.component('examItem',examItem);

var vm = new Vue({
	el:'#rrapp',
	data:{
		title:'',
		examList:[],
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../examtest/examTestList?id="+id, function(r){
                vm.examList = r.list;
            });
		},
		back: function (event) {
			history.go(-1);
		}
	}
});