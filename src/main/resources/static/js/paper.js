layui.use('laypage', function(){
    layer = layui.layer;
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
                        app.paperList = res
                    }
                })
            }
        }
    })

})
function toPaperEdit(url,obj){
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





