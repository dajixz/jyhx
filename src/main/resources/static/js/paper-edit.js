layui.use(['layer', 'element', 'form'], function () {
    var layer = layui.layer;
    var element = layui.element;
    var form = layui.form;

    form.on('submit(add)', function (res) {
        console.log(res.field)
        console.log("++++")
        var data = $(res.form).serialize();
        $.ajax({
            type: 'POST',
            url: '/paper/editPaper',
            data: data,
            dataType: "json",
            async: true,
            success: function (res) {
                console.log("+++++")
                layer.alert("ssssssssssssssssssssssssssssss")
            }
        })
        //
        // return false;
    })

})

function span_onblur(obj) {
    var content = $(obj).html();
    $(obj).parent().children(".index").attr("value", content);
}
function getDivNum() {
    var num = $("#div .question").length + 1;
    return num;
}

function remove(obj,i) {
    if(i==2){
        $(obj).parent().parent().remove();
    }else if(i==3){
        var row = $(obj).parent().parent().parent();
        var input = row.parent().children(".input");
        $(obj).parent().parent().parent().remove();
        var valnum = parseInt(input.val()) - 1;
        input.attr("value", valnum);
        row.remove();
    }else {
        return;
    }
}
function addObjective() {
    var num = getDivNum()
    var body = "<div class=\"layui-card\">\n" +
        "                <label class=\"layui-form-label\"><a href=\"#\" onclick=\"remove(this,2)\"></button><i\n" +
        "                        class=\"layui-icon layui-icon-close-fill\"></i></a>&nbsp;&nbsp;\n" +
        "                    <span onblur=\"span_onblur(this)\" contenteditable=\"true\">"+num+"</span>.\n" +
        "                    <input class=\"index\" type=\"text\" name=\"oindex[]\" value=\""+num+"\" hidden/>\n" +
        "                </label>\n" +
        "                <div class=\"layui-input-block\">\n" +
        "                    <input type=\"text\" class=\"layui-input question\" name=\"objectiveName[]\" value=\"\"/>\n" +
        "                </div>\n" +
        "                <div class=\"layui-card-body\">\n" +
        "                    <div class=\"layui-row\">\n" +
        "                        <div class=\"layui-col-md8\">\n" +
        "                            <div class=\"layui-inline\">\n" +
        "                                <label class=\"layui-form-label\">A</label>\n" +
        "                                <div class=\"layui-input-inline\">\n" +
        "                                    <input type=\"text\" name=\"a[]\" autocomplete=\"off\" value=\"\" class=\"layui-input\"/>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                            <div class=\"layui-inline\">\n" +
        "                                <label class=\"layui-form-label\">B</label>\n" +
        "                                <div class=\"layui-input-inline\">\n" +
        "                                    <input type=\"text\" name=\"b[]\" autocomplete=\"off\" value=\"\" class=\"layui-input\"/>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                            <div class=\"layui-inline\">\n" +
        "                                <label class=\"layui-form-label\">C</label>\n" +
        "                                <div class=\"layui-input-inline\">\n" +
        "                                    <input type=\"text\" name=\"c[]\" autocomplete=\"off\" value=\"\" class=\"layui-input\"/>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                            <div class=\"layui-inline\">\n" +
        "                                <label class=\"layui-form-label\">D</label>\n" +
        "                                <div class=\"layui-input-inline\">\n" +
        "                                    <input type=\"text\" name=\"d[]\" autocomplete=\"off\" value=\"\" class=\"layui-input\"/>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                        <div class=\"layui-col-md4\">\n" +
        "                            <div class=\"layui-inline\">\n" +
        "                                <label class=\"layui-form-label\">分值</label>\n" +
        "                                <div class=\"layui-input-inline\">\n" +
        "                                    <input type=\"text\" name=\"oscore[]\" autocomplete=\"off\" value=\"\" class=\"layui-input\"/>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                            <div class=\"layui-inline\">\n" +
        "                                <label class=\"layui-form-label\">答案</label>\n" +
        "                                <div class=\"layui-input-inline\">\n" +
        "                                    <input type=\"text\" name=\"answer[]\" autocomplete=\"off\" value=\"\" class=\"layui-input\"/>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>";
    $("#div").append(body);
}
function addSubjective() {
    var num = getDivNum()
    var body = "<div class=\"layui-card\">\n" +
        "                <div class=\"div\">\n" +
        "                    <a href=\"javascript:;\" onclick=\"remove(this,2)\"></button><i\n" +
        "                            class=\"layui-icon layui-icon-close-fill\"></i></a>\n" +
        "                    <a href=\"javascript:;\" onclick=\"add_input(this)\"><i class=\"layui-icon   layui-icon-add-1\"></i></a>\n" +
        "                    <div class=\"layui-card-body\">\n" +
        "                        <label class=\"layui-form-label\"></label>\n" +
        "                        <div class=\"layui-input-block\">\n" +
        "                            <textarea name=\"content[]\" placeholder=\"请输入内容\" class=\"layui-textarea\"></textarea>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                    <input class=\"input\" hidden name=\"subjectiveNum[]\" value=\"1\"/>\n" +
        "                    <div class=\"layui-row\">\n" +
        "                        <div class=\"layui-col-md9\">\n" +
        "                            <label class=\"layui-form-label\">\n" +
        "                                <a href=\"javascript:;\" onclick=\"remove(this,3)\">\n" +
        "                                    <i class=\"layui-icon layui-icon-close-fill\"></i></a>&nbsp;&nbsp;\n" +
        "                                <span onblur=\"span_onblur(this)\" contenteditable=\"true\">"+num+"</span>.\n" +
        "                                <input class=\"index\" type=\"text\" name=\"sindex[]\" value=\""+num+"\" hidden/>\n" +
        "                            </label>\n" +
        "                            <div class=\"layui-input-block\">\n" +
        "                                <input type=\"text\" name=\"subjectiveName[]\" autocomplete=\"off\" value=\"\"\n" +
        "                                       class=\"layui-input question\">\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                        <div class=\"layui-col-md3\">\n" +
        "                            <div class=\"layui-inline\">\n" +
        "                                <label class=\"layui-form-label\">分值</label>\n" +
        "                                <div class=\"layui-input-inline\">\n" +
        "                                    <input type=\"text\" name=\"sscore[]\" autocomplete=\"off\" value=\"\" class=\"layui-input\">\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>"
    $("#div").append(body);
}

function add_input(obj) {
    var num = getDivNum();
    var body = "<div class=\"layui-row\">\n" +
        "                        <div class=\"layui-col-md9\">\n" +
        "                            <label class=\"layui-form-label\">\n" +
        "                                <a href=\"javascript:;\" onclick=\"remove(this,3)\">\n" +
        "                                    <i class=\"layui-icon layui-icon-close-fill\"></i></a>&nbsp;&nbsp;\n" +
        "                                <span onblur=\"span_onblur(this)\" contenteditable=\"true\">"+num+"</span>.\n" +
        "                                <input class=\"index\" type=\"text\" name=\"sindex[]\" value=\""+num+"\" hidden/>\n" +
        "                            </label>\n" +
        "                            <div class=\"layui-input-block\">\n" +
        "                                <input type=\"text\" name=\"subjectiveName[]\" autocomplete=\"off\" value=\"\"\n" +
        "                                       class=\"layui-input question\">\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                        <div class=\"layui-col-md3\">\n" +
        "                            <div class=\"layui-inline\">\n" +
        "                                <label class=\"layui-form-label\">分值</label>\n" +
        "                                <div class=\"layui-input-inline\">\n" +
        "                                    <input type=\"text\" name=\"sscore[]\" autocomplete=\"off\" value=\"\" class=\"layui-input\">\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>";
    var div = $(obj).parent();
    var valnum = parseInt(div.children(".input").val()) + 1
    div.children(".input").attr("value", valnum);
    div.append(body);
}