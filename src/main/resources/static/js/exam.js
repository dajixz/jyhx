layui.use(['laydate','laypage'], function(){
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
    var app = new Vue({
        el:'#layui_table_id',
        data:{
            examList:[]
        },
        created:function () {
            this.getExamList(1)
        },
        methods:{
            getExamList:function (page) {
                var page = page
                var info = $.parseJSON($.cookie("current"));
                var gradeId = info.teacherGradeId
                $.ajax({
                    type:'GET',
                    url:'/getExamList',
                    dataType:'json',
                    data:{
                        page:page,
                        gradeId:gradeId
                    },
                    async:true,
                    success:function (res) {
                        for(var index in res.content){
                            res.content[index].examTime = res.content[index].examTime.substring(0,10)
                        }
                        app.examList = res.content
                        laypage.render({
                            elem: 'laypage',
                            count: res.totalElements,//数据总数，从服务端得到
                            curr:res.number+1,
                            limit:res.size,
                            jump: function(obj,first){
                                if(!first){
                                    app.getExamList(obj.curr)
                                }

                            }
                        });
                    }
                })
            }
        }
    })
});

function parent_add_tab(url,obj){
    title=$(obj).attr("name");
    id= $(obj).attr("lay-id");
    var tab_li = $("li[lay-id="+id+"]",parent.document)
    if(tab_li.length==1){
        parent.element.tabChange('xbs_tab', id)
    }else {
        parent.element.tabAdd('xbs_tab', {
            title: title,
            content: '<iframe tab-id="'+id+'" frameborder="0" src="'+url+'" scrolling="yes" class="x-iframe"></iframe>',
            id: id
        })
        parent.element.tabChange('xbs_tab', id)
    }
}
/*用户-停用*/
function member_stop(obj,id){
    layer.confirm('确认要停用吗？',function(index){

        if($(obj).attr('title')=='启用'){

            //发异步把用户状态进行更改
            $(obj).attr('title','停用')
            $(obj).find('i').html('&#xe62f;');

            $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
            layer.msg('已停用!',{icon: 5,time:1000});

        }else{
            $(obj).attr('title','启用')
            $(obj).find('i').html('&#xe601;');

            $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
            layer.msg('已启用!',{icon: 5,time:1000});
        }

    });
}

/*用户-删除*/
function member_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
        //发异步删除数据
        $(obj).parents("tr").remove();
        layer.msg('已删除!',{icon:1,time:1000});
    });
}



function delAll (argument) {

    var data = tableCheck.getData();

    layer.confirm('确认要删除吗？'+data,function(index){
        //捉到所有被选中的，发异步进行删除
        layer.msg('删除成功', {icon: 1});
        $(".layui-form-checked").not('.header').parents('tr').remove();
    });
}