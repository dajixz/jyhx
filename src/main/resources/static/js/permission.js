layui.use(['layer','form'], function () {
    var layer = layui.layer;
    var form =layui.form;
    var app = new Vue({
        el: '#layui',
        data: {
            permissionList: []
        },
        created: function () {
            this.getPermissionList()
        },
        methods: {
            getPermissionList: function () {
                $.ajax({
                    type: 'GET',
                    url: '/permission/getPermissions',
                    dataType: 'json',
                    async: true,
                    success: function (res) {
                        console.log(res)
                        app.permissionList = res;
                    }
                })
            }
        }
    })
})