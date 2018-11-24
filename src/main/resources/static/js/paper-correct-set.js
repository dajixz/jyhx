layui.use(['form','layer'], function() {
    var form = layui.form;

    var layer = layui.layer;

    var app = new Vue({
        el:'#layer',
        data: {
            teacherList:[],
            questionIdList:[]

        },
        created:function () {
            this.getTeachersBySubjectAndGradeId();
            this.getSubjectiveQuestionId();
        },
        methods: {
            getTeachersBySubjectAndGradeId:function () {
                var subject =$("#subject").attr("value")
                $.ajax({
                    type:'GET',
                    url:'/score/getTeachersToCorrect',
                    dataType:'json',
                    data:{
                        subject:subject
                    },
                    async:true,
                    success:function (res) {
                        if(res.code == 200){
                            app.teacherList = res.data

                        }
                    }
                })
            },
            getSubjectiveQuestionId:function () {
                var paperId =$("#paperId").attr("value")
                $.ajax({
                    type:'GET',
                    url:'/score/getSubjectiveQuestionId',
                    dataType:'json',
                    data:{
                        paperId:paperId
                    },
                    async:true,
                    success:function (res) {

                        if(res.code == 200){
                            app.questionIdList = res.data
                        }
                    }
                })
            }
        }
    })
    form.on('submit(on)',function (res) {
        console.log(res.field)
        console.log(res.field)
        console.log("++++")
        $.ajax({
            type: 'PUT',
            url: '/score/setTeachersToCorrect',
            data: res.field,
            dataType: "json",
            async: true,
            success: function (res) {
                if(res.code==200){
                    layer.alert("添加成功！", {icon: 6}, function () {
                        // 获得frame索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                    });
                }
            }
        })
        return false;
    })
})
