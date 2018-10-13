layui.use(['laydate', 'laypage'], function () {

    var laydate = layui.laydate;
    var laypage = layui.laypage;
    //执行一个laydate实例
    laydate.render({
        elem: '#start' //指定元素
    });
    //执行一个laydate实例
    laydate.render({
        elem: '#end' //指定元素
    });
    var app = new Vue({   //创建一个Vue的实例
        el: "#layui_table_id", //挂载点是id="app"的地方
        data: {     //数据
            studentList: []
        },
        mounted: function () {
            this.getStudentList(1);
        },
        methods: {
            getStudentList: function (page) {
                var page = page
                $.ajax({
                    type: 'GET',
                    url: '/getStudentList',
                    dataType: 'json',
                    data: {
                        page: page
                    },
                    async: true,
                    success: function (res) {
                        app.studentList = res.content
                        laypage.render({
                            elem: 'laypage',
                            count: res.totalElements,//数据总数，从服务端得到
                            curr: res.number + 1,
                            limit: res.size,
                            jump: function (obj, first) {
                                if (!first) {
                                    app.getStudentList(obj.curr)
                                }
                            }
                        });
                    }
                })
            }
        }
    })
});


/*用户-停用*/
function member_stop(obj, id) {
    layer.confirm('确认要停用吗？', function (index) {

        if ($(obj).attr('title') == '启用') {

            //发异步把用户状态进行更改
            $(obj).attr('title', '停用')
            $(obj).find('i').html('&#xe62f;');

            $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
            layer.msg('已停用!', {icon: 5, time: 1000});

        } else {
            $(obj).attr('title', '启用')
            $(obj).find('i').html('&#xe601;');

            $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
            layer.msg('已启用!', {icon: 5, time: 1000});
        }

    });
}

/*用户-删除*/
function member_del(obj, id) {
    layer.confirm('确认要删除吗？', function (index) {
        //发异步删除数据
        $(obj).parents("tr").remove();
        layer.msg('已删除!', {icon: 1, time: 1000});
    });
}


function delAll(argument) {

    var data = tableCheck.getData();

    layer.confirm('确认要删除吗？' + data, function (index) {
        //捉到所有被选中的，发异步进行删除
        layer.msg('删除成功', {icon: 1});
        $(".layui-form-checked").not('.header').parents('tr').remove();
    });
}