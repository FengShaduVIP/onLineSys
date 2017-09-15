var id = T.p("id");

var examItem = Vue.extend({
    name: 'exam-item',
    props: {
    	item:{},
	},
    template: [
			'<div class="layui-colla-item">',
			'<h2 class="layui-colla-title">',
				'题目标题：{{item.title}}' ,
				'<div style="float: right; width: 300px" >' ,
					'<div class="layui-col-xs4">',
        					'题目分数:{{item.score}}',
					'</div>',
					'<div class="layui-col-xs8">',
						'<span style="padding-left: 40px" v-if="item.status==1" class="glyphicon glyphicon-ok" aria-hidden="true">&nbsp;&nbsp;正确</span>',
						'<span style="padding-left: 40px" v-if="item.status==0" class="glyphicon glyphicon-remove" aria-hidden="true">&nbsp;&nbsp;错误</span>',
						'<span style="padding-left: 40px" v-if="item.status!=0&&item.status!=1" aria-hidden="true">&nbsp;&nbsp;未提交</span>',
        			'</div>',
				'</div>',
			'</h2>',
				'<div class="layui-colla-content">',
					'<h3>',
        				'<div v-html="item.context"></div>',
					'</h3>',
					'<div class="layui-form-item layui-form-text" style="padding-top: 20px">',
						'<div class="layui-input-block">',
							'<textarea :id="item.id"  :key="item.id" placeholder="请输入提交内容" class="layui-textarea"></textarea>',
							'<button @click="submitItemTest(item.id)">提交</button>',
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
		sendData:{},
        layIndex:-1
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		//获取考试题目列表
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