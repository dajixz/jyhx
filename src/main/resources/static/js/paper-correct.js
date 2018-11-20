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
                    url:'/score/getScoreInfo',
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
                    url:'/score/getScores',
                    dataType:'json',
                    data:{
                        paperId:paperId,
                        questionId:3,
                        page:page
                    },
                    async:true,
                    success:function (res) {
                        if(res.content.length == 0){
                            layer.msg("没有需要批改的了~ 2秒后自动退出蛤",{
                                time:2000
                            },function () {
                                window.close();
                            })
                            return;
                        }
                        app.answer = res.content[0]
                        laypage.render({
                            elem: 'laypage',
                            count: res.totalElements,//数据总数，从服务端得到
                            curr:res.number+1,
                            limit:res.size,
                            layout: ['prev', 'next'],
                            jump: function(obj,first){
                                if(!first){
                                    app.getAnswers(1)
                                }

                            }
                        });
                    }
                })
            }
        }
    })
    form.on('submit(put)', function (res) {
        var data = app.answer.scorePk
        data.goalScore = res.field.goalScore
        data.questionScore =  app.questionScore;
        console.log(data)
        $.ajax({
            type:'PUT',
            url:'/score/updateScore',
            dataType:'json',
            data:data,
            async:true,
            success:function (res) {
                console.log(res)
            }
        })

        return false;
    })

    form.verify({
        required:function (value) {
            var i = parseInt(app.questionScore) ;
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