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
                        console.log(res)
                        if (res.code == 403) {
                            layer.msg(res.msg)
                            return;
                        }
                        app.roleList = res;
                    }
                })
            }
        }
    })
})