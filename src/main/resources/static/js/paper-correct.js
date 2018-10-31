layui.use(['form','layer','laypage'], function() {
    var form = layui.form;
    var layer = layui.layer;
    var laypage = layui.laypage;

    var app = new Vue({
        el:'#correct',
        data: {
            questionName:"",
            subjectiveQuestionContent:"",
            questionScore:"",
            answer:null
        },
        created:function () {
            this.getAnswerInfo()
            this.getAnswers(1)
        },
        methods:{
            getAnswerInfo:function () {
                var paperId =$("#paperId").attr("value")
                $.ajax({
                    type:'GET',
                    url:'/answer/getAnswerInfo',
                    dataType:'json',
                    data:{
                        paperId:paperId,
                        questionId:3
                    },
                    async:true,
                    success:function (res) {
                        app.questionName = res.questionName;
                        app.subjectiveQuestionContent = res.subjectiveQuestionContent;
                        app.questionScore = res.questionScore
                    }
                })
            },
            getAnswers:function (page) {
                var paperId =$("#paperId").attr("value")
                $.ajax({
                    type:'GET',
                    url:'/answer/getAnswers',
                    dataType:'json',
                    data:{
                        paperId:paperId,
                        questionId:3,
                        page:page
                    },
                    async:true,
                    success:function (res) {
                        app.answer = res.content[0]
                        laypage.render({
                            elem: 'laypage',
                            count: res.totalElements,//数据总数，从服务端得到
                            curr:res.number+1,
                            limit:res.size,
                            jump: function(obj,first){
                                if(!first){
                                    app.getAnswers(obj.curr)

                                }

                            }
                        });
                    }
                })
            }
        }
    })
    form.on('submit(put)', function (res) {
        var data = app.answer.answerPk
        data.goalScore = res.field.goalScore
        data.questionScore =  app.questionScore;

        return false;
    })

    form.verify({
        required:function (value) {
            var i = app.questionScore;
            var reg = /^[0-9]+.?[0-9]*$/;
            if(!reg.test(value)){
                return "请输入数字！"
            }else {
                if(value>i){
                    return "请输入"+"0~"+i+"之间的分数！"
                }
            }

        }
    })
})