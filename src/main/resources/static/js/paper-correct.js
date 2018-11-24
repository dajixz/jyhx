layui.use(['form', 'layer', 'laypage'], function () {
    var form = layui.form;
    var layer = layui.layer;

    var app = new Vue({
        el: '#correct',
        data: {
            answer:null
        },
        created: function () {
            this.getAnswers()
        },
        methods: {
            getAnswers: function () {
                var paperId = $("#paperId").attr("value")
                $.ajax({
                    type: 'GET',
                    url: '/score/getScores',
                    dataType: 'json',
                    data: {
                        paperId: paperId,
                    },
                    async: true,
                    success: function (res) {
                        console.log(res)
                        if (res.code == 200) {
                            app.answer = res.data
                        }
                        if (res.code == 403) {
                            layer.msg(res.msg);
                        }
                        if (res.code == 201) {
                            layer.msg("没有需要批改的了~ 2秒后自动退出蛤", {
                                time: 2000
                            }, function () {
                                window.close();
                            })
                            return;
                        }
                    }
                })
            }
        }
    })
    form.on('submit(put)', function (res) {
        var data = app.answer.scorePk
        data.goalScore = res.field.goalScore
        data.questionScore = app.answer.scoreInfo.questionScore;
        console.log(data)
        $.ajax({
            type: 'PUT',
            url: '/score/updateScore',
            dataType: 'json',
            data: data,
            async: true,
            success: function (res) {
                console.log(res)
                if(res.code!=0){
                    app.getAnswers();
                    $("#score").val("")
                }
            }
        })

        return false;
    })

    form.verify({
        required: function (value) {
            var i = parseInt(app.questionScore);
            var reg = /^[0-9]+.?[0-9]*$/;
            if (!reg.test(value)) {
                return "请输入数字！"
            } else {
                if (value > i) {
                    return "请输入" + "0~" + i + "之间的分数！"
                }
            }

        }
    })
})