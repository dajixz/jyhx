layui.use('layer', function(){
    var layer = layui.layer;
    var app = new Vue({
        el: '#paperid',
        data: {
            paperList: []
        },
        created: function () {
            this.getPaperList()
        },
        methods: {
            getPaperList: function () {
                var examid = window.frameElement.getAttribute("tab-id")
                $.ajax({
                    type: 'GET',
                    url: '/paper/getPaperList',
                    dataType: 'json',
                    data:{
                        examId:examid
                    },
                    async: true,
                    success: function (res) {
                        if(res.code ==403){
                            layer.msg(res.msg,{
                                time:2000,
                                shade:0.3,
                                end:function () {
                                    parent.element.tabDelete("xbs_tab",examid)
                                }
                            })

                        }else{
                            app.paperList = res
                        }

                    }
                })
            }
        }
    })

})
function toSetView(url,obj) {
    var title = $(obj).attr("name");
    var paperId = $(obj).attr("data-id");
    var subject = $(obj).attr("data-subject");
    url=url+"?paperId="+paperId+"&subject="+subject;
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
function toPaperView(url,obj){
    var title = $(obj).attr("name");
    var paperId = $(obj).attr("data-id");
    // url=url+"?title="+title+"&paperId="+paperId;
    var subject = $(obj).attr("data-subject");
    var tempForm = document.createElement("form");
    tempForm.id="tempForm";
    tempForm.method="post";
    tempForm.action=url;
    tempForm.target='_blank';
    var hideInput1 = document.createElement("input");
    hideInput1.type="hidden";
    hideInput1.name= "title"
    hideInput1.value= title;
    tempForm.appendChild(hideInput1);
    var hideInput2 = document.createElement("input");
    hideInput2.type="hidden";
    hideInput2.name= "paperId"
    hideInput2.value= paperId;
    tempForm.appendChild(hideInput2);
    var hideInput3 = document.createElement("input");
    hideInput3.type="hidden";
    hideInput3.name= "subject"
    hideInput3.value= subject;
    tempForm.appendChild(hideInput3);
    document.body.appendChild(tempForm);
    tempForm.submit();
    document.body.removeChild(tempForm);
    return true;
}





