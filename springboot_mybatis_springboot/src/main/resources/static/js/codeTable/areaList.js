var index = {
    target:"#tr_information",
    url:"findPage",
    searchData:{},
    pageSize:10,
    fn:function(data){
    	
        var html = '';
        if(data == undefined||data == null||data.length==0){
            $(".d_pagination").html("");
            var ss = "暂时无数据"
            html += '<tr>';
              html += "<td colspan='13'>"+ ss +"</td>";
              html += '</tr>'
              return html;
            }
        	for (var i = 0; i < data.length; i++) {
        		html += '<tr>';
        		html += "<td>" + checkNull(data[i].areaCode) + "</td>";
        		html += "<td>" + checkNull(data[i].areaName) + "</td>";
        		html += "<td>" + checkNull(data[i].areaLevel) + "</td>";
        		html += "<td>" + checkNull(data[i].parentAreaCode) + "</td>";
        		html += "<td>" + checkNull(data[i].parentAreaName) + "</td>";
        		html += "<td>" + checkNull(data[i].postalCodePrefix) + "</td>";
        		html += "<td>" + checkNull(data[i].cityPinyin) + "</td>";
        		html += "<td>" + checkNull(data[i].pinyinShort) + "</td>";
        		html += "<td>" + checkNull(data[i].cityInitials) + "</td>";
        		html += "<td>" + checkNull(data[i].nameAbbreviation) + "</td>";
        		
        		html += "<td>" + checkNull(data[i].cityLevel) + "</td>";
        		html += "<td>" + checkNull(provincial(data[i].isProvincial)) + "</td>";
        		html += "<td>" + checkNull(municipality(data[i].isMunicipality)) + "</td>";
        		html += "<td>" + checkNull(data[i].displayOrder) + "</td>";
        		html += "<td>" + checkNull(statu(data[i].statu)) + "</td>";
        		html += "<td>" + checkNull(checkBool(data[i].isCustom)) + "</td>";
        		html += "<td>" + checkNull(checkBool(data[i].noDistrictsOrCountries)) + "</td>";
        		
        		html += "<td><a href='javaScript:;' onclick='update("+data[i].id+")' class='detail btn btn-default btn-xg'  >" +
        		"<i class='fa  fa-file-text-o'></i> 修改</a></td>";
        		html += '</tr>'
        	}
        	
        return html;
    }
}

function checkBool(data){
	if(data=='Y'){
		return '是';
	}else if(data=='N'){
		return '否';
	}
	
}
function provincial(data){
	if(data==0){
		return "否";
	}
	if(data==1){
		return "是";
	}
}
function statu(data){
	if(data==0){
		return "作废";
	}
	if(data==1){
		return "启用";
	}
}
 function municipality(data){
	if(data==0){
		return "否";
	}
	if(data==1){
		return "是";
	}
	
}

localStorage.removeItem("firstIn");
$(function () {
	 new Page(index).tableList();
//	 $("#btn").click(function(){
//		 localStorage.removeItem("firstIn");
//			var arr = $("#ff").serializeArray();
//			 var data = utils.serializeJson(arr);
//			 index.searchData = data;
//			 new Page(index).tableList();
//			return false;
//			
//		});
 
});









