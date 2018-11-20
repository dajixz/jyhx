layui.use(['layer', 'upload', 'laypage'], function () {
    var upload = layui.upload;
    var laypage = layui.laypage;
    var layer = layui.layer;
    var app = new Vue({
        el: '#layui',
        data: {
            teacherList: [],
            tatolNumber:""
        },
        created: function () {
            this.getTeacherList(1)
        },
        methods: {
            getTeacherByName: function () {
                var teacherName = $("input[name=teacherName]").val().trim()
                if (teacherName == "") {
                    return;
                }
                $.ajax({
                    type: 'GET',
                    url: '/teacher/getTeachers',
                    dataType: 'json',
                    data: {
                        page: 1,
                        teacherName: teacherName
                    },
                    async: true,
                    success: function (res) {
                        if (res.code == 403) {
                            layer.msg(res.msg)
                            return;
                        }
                        app.teacherList = res.content;
                        laypage.render({
                            elem: 'laypage',
                            count: res.totalElements,//数据总数，从服务端得到
                            curr: res.number + 1,
                            limit: res.size,
                            jump: function (obj, first) {
                                if (!first) {
                                    app.getTeacherList(obj.curr)
                                }
                            }
                        })
                    }
                })
            },
            getTeacherList: function (page) {
                $.ajax({
                    type: 'GET',
                    url: '/teacher/getTeacherList',
                    dataType: 'json',
                    data: {
                        page: page
                    },
                    async: true,
                    success: function (res) {
                        if (res.code == 403) {
                            layer.msg(res.msg)
                            return;
                        }
                        app.teacherList = res.content;
                        app.tatolNumber = res.totalElements;
                        laypage.render({
                            elem: 'laypage',
                            count: res.totalElements,//数据总数，从服务端得到
                            curr: res.number + 1,
                            limit: res.size,
                            jump: function (obj, first) {
                                if (!first) {
                                    app.getTeacherList(obj.curr)
                                }

                            }
                        })
                    }
                })
            }
        }
    })
    var uploadInst = upload.render({
        elem:'#uploadButton',
        url:"/admin/uploadBathTeachers",
        accept:"file",
        exts:"xls|xlsx",
        done:function (res) {
            layer.msg(res.msg)
        },
        error:function (res) {
            layer.msg(res.msg)
        }
    })
});
function toAdminEdit (title, url, obj) {
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