
var index = {
    target:"#tr_information",
    url:"datatest.json",
    searchData:{},
    fn:function(data){
        var html = '';
        for (var i = 0; i < data.length; i++) {
            html += '<tr>';
            html += "<td>" + data[i].name + "</td>";
            html += "<td>2</td>";
            html += "<td><a class='authority' href='javascript:void(0)'>授权角色</a><a class='modifiy' id='1' href='javascript:void(0)' >修改</a><a class='delete' href='javascript:void(0)'>删除</a></td>";
            html += '</tr>'
        }
        return html;
    }
}

localStorage.removeItem("firstIn");

//授权树
var setting = {
    check: {
        enable: true
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    chkboxType: {
        "Y": "ps",
        "N": "ps"
    }
};

//数据
var zNodes = [
    {id:1, name:"右键菜单 1", open:true,checked:true,
        children:[
            {id:11, name:"节点 1-1",checked:true},
            {id:12, name:"节点 1-2",checked:true}

        ]},
    {id:2, name:"右键操作 2", open:true,
        children:[
            {id:21, name:"节点 2-1"},
            {id:22, name:"节点 2-2"},
            {id:23, name:"节点 2-3"},
            {id:24, name:"节点 2-4"}
        ]},
    {id:3, name:"右键操作 3", open:true,
        children:[
            {id:31, name:"节点 3-1"},
            {id:32, name:"节点 3-2"},
            {id:33, name:"节点 3-3"},
            {id:34, name:"节点 3-4"}
        ]},
    {id:4, name:"右键操作 3", open:true,
        children:[
            {id:41, name:"节点 3-1",checked:true},
            {id:42, name:"节点 3-2"},
            {id:43, name:"节点 3-3"},
            {id:44, name:"节点 3-4"}
        ]},
    {id:5, name:"右键操作 3", open:true,
        children:[
            {id:51, name:"节点 3-1"},
            {id:52, name:"节点 3-2"},
            {id:53, name:"节点 3-3"},
            {id:54, name:"节点 3-4"}
        ]}
];



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
        $("#a_authorityName").val(arrText[0]);
        $("#a_authorityType").val(arrText[1]);
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
    $("#tr_information").on("click",".authority",function(){
        $(".templateDom2").find(".textShow").html("").html('<ul id="treeDemo" class="ztree"></ul>');
        $(".modalMain").html($(".templateDom2").html());
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        confirm(function(){
            var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
                nodes=treeObj.getCheckedNodes(true),
                v="";
            for(var i=0;i<nodes.length;i++){
                v+=nodes[i].name + ",";
                alert(nodes[i].id); //获取选中节点的值
            }


        })


    })

    function checkForm(){
        utils.validator("#myModal .form-horizontal",{/*验证*/
            a_authorityName: {/*键名username和input name值对应*/
                message: '您输入的姓名无效',
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '请输入权限名称'
                    },
                }
            },
            a_authorityType: {/*键名username和input name值对应*/
                message: '您输入的姓名无效',
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '请输入真实姓名'
                    }
                }
            },
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






