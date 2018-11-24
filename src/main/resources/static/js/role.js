layui.use(['layer'], function () {
    var layer = layui.layer;
    var app = new Vue({
        el: '#layui',
        data: {
            roleList: []
        },
        created: function () {
            this.getRoleList()
        },
        methods: {
            getRoleList: function () {
                $.ajax({
                    type: 'GET',
                    url: '/role/getRoles',
                    dataType: 'json',
                    async: true,
                    success: function (res) {
                        if (res.code == 403) {
                            layer.msg(res.msg)
                            return;
                        }
                        app.roleList = res;
                    }
                })
            },
            toRoleAddView:function(title,url){
                layer.open({
                    type: 2,
                    area: [($(window).width() * 0.9) + 'px',($(window).height() - 50) + 'px'],
                    fix: false, //不固定
                    maxmin: true,
                    shadeClose: true,
                    shade: 0.4,
                    title: title,
                    content: url
                });
            }
        }
    })
});
function toRoleEditView(title,url,obj){
    if (title == null || title == '') {
        title = false;
    };
    if (url == null || url == '') {
        url = "404.html";
    };
    if (obj != null) {
        var id=$(obj).attr("data");
        url = url+"/"+id
    };
    layer.open({
        type: 2,
        area: [($(window).width() * 0.9) + 'px',($(window).height() - 50) + 'px'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: true,
        shade: 0.4,
        title: title,
        content: url
    });
}
