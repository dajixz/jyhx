layui.use(['form', 'layer'], function () {
    $ = layui.jquery;
    var form = layui.form,
        layer = layui.layer;

    //自定义验证规则
    form.verify({
        nikename: function (value) {
            if (value.length < 5) {
                return '昵称至少得5个字符啊';
            }
        },
        pass: [/(.+){6,12}$/, '密码必须6到12位'],
        repass: function (value) {
            if ($('#teacherPassword').val() != $('#L_repass').val()) {
                return '两次密码不一致';
            }
        }
    });

    //监听提交
    form.on('submit(add)', function (data) {
        var teacher = data.field
        console.log(teacher)
        $.ajax({
            type: 'POST',
            url: '/admin/addTeacher',
            dataType: 'json',
            data: teacher,
            async: true,
            success: function (res) {
                if(res.code == 200){
                    layer.alert(res.msg, {icon: 6}, function () {
                        // 获得frame索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                    });
                }else if(res.code==403){
                  layer.msg(res.msg);
                  return;
                }
            }
        })

        return false;
    });


});