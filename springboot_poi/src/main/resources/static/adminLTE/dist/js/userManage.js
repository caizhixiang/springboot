
var index = {
    target:"#tr_information",
    url:"datatest.json",
    searchData:{},
    fn:function(data){
        var html = '';
        for (var i = 0; i < data.length; i++) {
            html += '<tr>';
            html += "<td>" + data[i].name + "</td>";
            html += "<td>" + data[i].name + "</td>";
            html += "<td>" + data[i].position + "</td>";
            html += "<td>232323@www.vom</td>";
            html += "<td>1</td>";
            html += "<td>2</td>";
            html += "<td>2</td>";
            html += "<td><a class='authorization' href='javascript:void(0)'>授权角色</a><a class='modifiy' id='1' href='javascript:void(0)' >修改</a><a class='delete' href='javascript:void(0)'>删除</a></td>";
            html += '</tr>'
        }
        return html;
    }
}

localStorage.removeItem("firstIn");
$(function () {
    $(".btn-search").click(function(){
        var arr = $("#edit_form").serializeArray();
        var data = utils.serializeJson(arr);
        index.searchData = data;
        new Page(index).tableList();
        return false
    })
    var a1 = new Page(index);
    a1.tableList();
    $(".btn-add").click(function(){
        $(".modalMain").html($(".templateDom").html());
        checkForm();
        $('#myModal').modal('show');

    })
    $("#tr_information").on("click",".modifiy",function(){
        $(".modalMain").html($(".templateDom").html());
        var aTdText = $(this).parents("tr").find("td");
        var arrText = [];
        $.each(aTdText,function(index,item){
            arrText.push($(this).text());
            if(index == aTdText.length-2){
                return false;
            }
        })

        /*
        *修改 赋值* */
        $("#a_userName").val(arrText[0]);
        $("#a_realName").val(arrText[1]);
        $("#a_phone").val(arrText[2]);
        $("#a_mail").val(arrText[3]);
        $("#a_status").val(arrText[4]);
        $("#a_depart").val(arrText[5]);
        $("#a_position").val(arrText[6]);
        checkForm();
        $('#myModal').modal('show');

    })
    $("#tr_information").on("click",".delete",function(){
        $(".templateDom2").find(".textShow").html("").html('<p stye="margin-bottom:15px">您确定要删除吗？</p>')
        $(".modalMain").html($(".templateDom2").html());
        confirm(function(){
            //todo .....
        })
    })
    $("#tr_information").on("click",".authorization",function(){
        var data = [{ a:true, text:"aaa" },{a:false, text:"aaa"}];
        var option = '';
        $.each(data,function(index,item){
            if(item.a){
                option +='<div class="vertical-middle"><input type="checkbox" checked="checked" />'+item.text+'</div>';
            }else{
                option +='<div class="vertical-middle"><input type="checkbox" />'+item.text+'</div>';
            }

        })
        var html  =  '<div class="form-group">\
                        <label for="a_userName" class="col-sm-3 control-label">角色列表：</label>\
                        <div class="col-sm-9 rolelist">'+option+' </div>\
                     </div>';
        $(".templateDom2").find(".textShow").html("").html(html)
        $(".modalMain").html($(".templateDom2").html());
        confirm(function(){
            //todo .....
        })


    })

    function checkForm(){
        utils.validator("#myModal .form-horizontal",{/*验证*/
            a_userName: {/*键名username和input name值对应*/
            message: '您输入的姓名无效',
                validators: {
                notEmpty: {/*非空提示*/
                    message: '请输入真实姓名'
                },
            }
        },
            a_realName: {/*键名username和input name值对应*/
                message: '您输入的姓名无效',
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '请输入真实姓名'
                    },
                    stringLength: {/*长度提示*/
                        min: 6,
                        max: 30,
                        message: '真实姓名长度必须在6到30之间'
                    }/*最后一个没有逗号*/
                }
            },
            a_phone: {
                message: '请输入手机号',
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '请输入手机号'
                    },
                    regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /^1[34578]\d{9}$/,
                        message: '请输入正确的手机号码'
                    }
                }
            },
            a_mail: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress: {
                        message: '请输入有效的邮箱地址'
                    }
                }
            },
            a_status: {
                validators: {
                    notEmpty: {
                        message: '请选择状态'
                    }
                }
            },
            a_depart: {
                validators: {
                    notEmpty: {
                        message: '请选择部门'
                    }
                }
            },
            a_position: {
                validators: {
                    notEmpty: {
                        message: '请选择职位'
                    }
                }
            }
        },function(e) {
            // 提交成功
            e.preventDefault();
            var arr = $("#myModal .form-horizontal").serializeArray();
            var data = utils.serializeJson(arr);
            new Page(index).tableList();
            $('#myModal').modal('hide');
        })
    }
    function confirm(fn){
        $('#myModal').modal('show');
        $(".confirm-btn").off("click").on("click",function(){
            fn&&fn();
            $('#myModal').modal('hide');
            return false;
        })
    }

})






