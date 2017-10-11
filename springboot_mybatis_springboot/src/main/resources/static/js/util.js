var utils = {
	alertMessage:function(errorMessage){
    	
    	var errorHtml = '<div id="main-error-model" style="top:25%;" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">'
        +'<div class="modal-dialog modal-sm" role="document">'
        +'<div class="modal-content">'
          +'<div class="modal-header">'
            +'<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>'
            +'<h4 class="modal-title" id="mySmallModalLabel">消息提示</h4>'
          +'</div>'
          +'<div class="modal-body" id="main-error">'
          +errorMessage+'</div>'
        +'</div>'
      +'</div> </div>';
	   //$('#main-error-model').modal("show");
    	$(errorHtml).modal("show");
    },
  serializeJson:function(obj){
        var objJson = {}
        if(obj){
            $.each(obj,function(index,item){
                objJson[item.name] = item.value;
            })
        }
        return objJson;
    },
    validator:function(obj,opt,successFn){
        /*
        * obj 验证表单对象
        * opt 校验方法
        * successFn：校验成功之后回调
        * */
        $(obj).bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: opt
        }).on('success.form.bv', successFn);
    },
    pagination:function(obj,num,pageSize,callback){
        /*
        * obj目标元素
        * num总条数
        * 分页回调函数
        *pageSize 每页显示条数
        * */
      $(obj).pagination(num, {
            num_edge_entries: 1, //边缘页数
            num_display_entries: 4, //主体页数
            callback: callback,
            items_per_page: pageSize, //每页显示1项
            prev_text: "<",
            next_text: ">"
        });
    },
    ajax:function(opts){
        /*
        *opts: {'可选参数'}
        *method: 请求方式:GET/POST,默认值:'POST';
        *url:    发送请求的地址, 默认值: 当前页地址;
        * dataType 请求数据类型 默认json
        * async: 是否异步:true/false,默认值:true;
        *contentType: HTTP头信息
        *success: 请求成功后的回调函数;
        *error: 请求失败后的回调函数;
        * */

        var defaults = {
            "method": 'POST',
            "url":'',
            "data": {},
            "dataType":"json",
            "timeout":10000,
            "async": true,
            "cache": false, //关闭AJAX相应的缓存
           // "contentType":"application/json",
            "success":function(){},
            "error":function(e){
            	if(e.status && e.status!=200)
            		utils.alertMessage("系统异常");
            	else if(e.status && e.status==200)
            		logout();
            	else
            		utils.alertMessage("系统异常");
            	$("#loading").remove();
            }

        }
        $.extend(true,defaults,opts);
        $.ajax({
            type : defaults.method,
            dataType : defaults.dataType,
            url : defaults.url, // 提交到一般处理程序请求数据
            //contentType:defaults.contentType,
            async:defaults.async,
            data : defaults.data,
            success : defaults.success,
            timeout : defaults.timeout,
            error:defaults.error
        })
    },
    serializeJson:function(obj){
        var objJson = {}
        if(obj){
            $.each(obj,function(index,item){
                objJson[item.name] = item.value;
            })
        }
        return objJson;
    }
}

function Page(opt){
    this.target = opt ? opt.target: "#tr_information"; //插入到哪里
    this.url = opt? opt.url: "";    //请求地址
    this.dataSou = {pageSize:opt.pageSize ? opt.pageSize : 10,pageNum:1}; //pageSize每页显示条数 pageNum页码 默认值
    this.searchData = opt.searchData ?  opt.searchData : {}; //搜索条件
    this.fn = opt.fn ? opt.fn : function(){} //渲染html代码
    this.pageSize = opt.pageSize ? opt.pageSize : 10 //每页显示条数
    this.pageCount = opt.pageCount ? opt.pageCount : '.d_pagination';
}
Page.prototype.dataSource = {}; //请求返回数据

Page.prototype.tableList = function(){  //初始化函数
    var _this = this;
    if(this.searchData){
        this.dataSou = $.extend(this.dataSou,this.searchData);
    }
    //加上数据load效果
//    $(_this.target).html('');
//    var load = $('<img id="loading" src="'+root+'/img/timg_5.gif" alt="loading" style="padding-left: 40%;padding-top: 10%;padding-bottom: 10%;"></img>');
//    $(_this.target).parent().after(load);
//    
    utils.ajax({
        url:_this.url,
        data:_this.dataSou,
        success: function (data) {
            // 后台服务返回数据，重新加载数据
        	if(data.data!= null){
        		Page.prototype.dataSource = data.data ? data.data :data.list ;
  	            utils.pagination(_this.pageCount, data.totalCount,_this.pageSize, pageselectCallback);
        	}else{
        		_this.fn && $(_this.target).html(_this.fn());
        	}
        	$("#loading").remove();
        	
        }
    })

    var pageselectCallback = function(page_index, jq){
        _this.dataSou.pageNum = page_index+1;
        var flag = localStorage.getItem("firstIn");
        if(page_index>0 || flag === "true"){
            utils.ajax({
                url:_this.url,
                async:false,
                data: _this.dataSou,
                success : function(data){
                    localStorage.setItem("firstIn",true);
                    $("#loading").remove();
                    // 后台服务返回数据，重新加载数据
                    _this.dataSource = data.data;
                    
                }
            })
        }
        $(_this.target).html('');
        var data = _this.dataSource;
        var html = _this.fn&&_this.fn(data)
        $(_this.target).html(html);
        return false;
    }
    
}
function checkNull(obj){
	return (obj==null || obj== "") ? '----' : obj;
}
