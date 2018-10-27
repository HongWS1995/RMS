function bindLink(){
		$("a#firstPage").unbind();
		$("a#prevPage").unbind();
		$("a#nextPage").unbind();
		$("a#lastPage").unbind();
		var currentPage;
		var totalPage;
		$("a#firstPage").click(function(){
			$("input#currentPage").val(1);	
			queryTableRList(1);
			});
		$("a#prevPage").click(function(){
			currentPage = $("input#currentPage").val();
			if(currentPage>1){
				currentPage = parseInt(currentPage)-1;
				$("input#currentPage").val(currentPage);
				queryTableRList(currentPage);
			}
				
		});
		$("a#nextPage").click(function(){
			currentPage = parseInt($("input#currentPage").val());
			totalPage  = parseInt($("input#totalPage").val());
			if(currentPage<totalPage){
				currentPage = parseInt(currentPage)+1;
				$("input#currentPage").val(currentPage);
				queryTableRList(currentPage);
			}
				
		});
		$("a#lastPage").click(function(){
			totalPage  = $("input#totalPage").val();
			$("input#currentPage").val(totalPage);
			queryTableRList(totalPage);
		});
		
	}
	function queryTableRList(currentPage) {
		//var currentPage = $("input#currentPage").val();
		var totalPage=0;
	//	var ptypeid = $("select#tablerTypeSelect").val().replace(/\\s*/g,"");
		var tableName = $("#queryForm input[name='tableName']").val().replace(/\\s*/g,"");
		var status  = $("#queryForm select[name='status']").val();
		var json ="'currentPage':"+currentPage+",'pageSize':8";
		if(tableName!="")
			json = json +",'tableName':'"+tableName+"'";
		if(status!="")
			json = json +",'status':'"+status+"'";
		json = "{"+json+"}";
		json = json.replace(/\'/g,'\"');
		json = JSON.parse(json);
		//alert(JSON.stringify(json));
		$.post("tabler/TableRList",json,function(data){
			if(data.result=="success"){
				$("input#totalRecord").val(data.pageBean['totalRecord']);
				$("input#totalPage").val(data.pageBean['totalPage']);
				$("span#currentPage").text("第"+data.pageBean['currentPage']+"页");
				$("span#totalPage").text("总页数:"+data.pageBean['totalPage']);				
				$("span#totalRecord").text("总记录:"+data.pageBean['totalRecord']+"条");
				totalPage = $("input#totalPage").val();
				if(totalPage>=0)
					$("div#pageButton").show();
				if($("input#isBindLink").val()==0){
					 bindLink();
				}
				var beanList = data.pageBean['beanList'];
				$("tbody#tablerTable").empty();
				if(beanList.length < 1)
					$("tbody#tablerTable").append("<tr><td colspan='5' style='text-align:center;'>无记录</td></tr>");
				
				for(var i = 0;i<beanList.length;i++){
					var tds="<td>"+((parseInt(currentPage)-1)*8+i+1)+"</td>";
					for(var key in beanList[i]){
						if(key=="products")
							break;
						if(key=="isRemove")
							break;
						tds +="<td>"+beanList[i][key]+"</td>";
					}	 
					$("tbody#tablerTable").append("<tr class='rowRecord'>"+tds+"</tr>");
					
				}
				$(".rowRecord").click(function(){
					$(".rowRecord:odd").css("background-color","white");
					$(".rowRecord:even").css("background-color","#f9f9f9");
					$(this).css("background-color","#fbec88ad");
					$("input#currentClickRowId").val($(this).children("td:nth-child(2)").text());
				});
			}
			
		});
	}
$(function () {
        $("div#addTableR form").bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
            　feedbackIcons: {
                　　　　　　　　valid: 'glyphicon glyphicon-ok',
                　　　　　　　　invalid: 'glyphicon glyphicon-remove',
                　　　　　　　　validating: 'glyphicon glyphicon-refresh'
            　　　　　　　　   },
            fields: {
            	tableName: {
                    message: '餐桌名验证失败',
                    validators: {
                        notEmpty: {
                            message: '餐桌名不能为空'
                        }
                    }
                }
            }
        });
    });
	
	$(function () {
        $("div#updateTableR form").bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
     feedbackIcons: {
                　　　　　　　　valid: 'glyphicon glyphicon-ok',
                　　　　　　　　invalid: 'glyphicon glyphicon-remove',
                　　　　　　　　validating: 'glyphicon glyphicon-refresh'
            　　　　　　　　   },
            fields: {
            	tableName: {
                    message: '餐桌名验证失败',
                    validators: {
                        notEmpty: {
                            message: '餐桌名不能为空'
                        }
                    }
                },
                id: {
                    message: '请点击要修改的行',
                    validators: {
                        notEmpty: {
                            message: '请点击要修改的行'
                        }
                    }
                }
            }
        });
    });
	$(function () {
        $("div#deleteTableR form").bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
            　feedbackIcons: {
                　　　　　　　　valid: 'glyphicon glyphicon-ok',
                　　　　　　　　invalid: 'glyphicon glyphicon-remove',
                　　　　　　　　validating: 'glyphicon glyphicon-refresh'
            　　　　　　　　   },
            fields: {
                id: {
                    message: '请点击要删除的行',
                    validators: {
                        notEmpty: {
                            message: '请点击要删除的行'
                        }
                    }
                }    
            }
        });
    });
	$(document).ready(function(){
		$("div#pageButton").hide();
		queryTableRList(1);
		$("div#addTableR #addTableRBtn").click(function(){
			var formdata = $("div#addTableR form").serialize();
			var tableName = $("div#addTableR form input[name='tableName']").val().replace(/\s*/g,"");
			if(tableName==""){
				alert("餐桌不能为空");
				return;
			}
				
			$.post("tabler/save",formdata,function(data){
				if(data.result=="success"){
					alert("保存成功");
					$("div#addTableR form")[0].reset();
					$("#addTableR").modal("hide");
					queryTableRList($("input#currentPage").val());
				}
					
			},"json");
			
		});
		$("div#updateTableR #updateTableRBtn").click(function(){	
			var formdata = $("div#updateTableR form").serialize();
			var id = $("div#updateTableR form input[name='id']").val().replace(/\s*/g,"");
			var tableName = $("div#updateTableR form input[name='tableName']").val().replace(/\s*/g,"");
			if(tableName==""||id==""){
				alert("请点击表格中您要修改的餐桌");
				return false;
			}
				
			$.post("tabler/update",formdata,function(data){
				if(data.result=="success"){
					alert("保存成功");
					$("div#updateTableR form")[0].reset();
					$("input#currentClickRowId").val("");
					$("#updateTableR").modal("hide");
					queryTableRList($("input#currentPage").val());
				}
					
			},"json");
			
		});
		$("div#deleteTableR #deleteTableRBtn").click(function(){
			var id = $("div#deleteTableR form input[name='id']").val().replace(/\s*/g,"");
			if(id==""){
				alert("请点击表格中您要删除的餐桌");
				return false;
			}
				
			$.post("tabler/delete",{id:id},function(data){
				if(data.result=="success"){
					alert("删除成功");
					$("div#deleteTableR form")[0].reset();
					$("input#currentClickRowId").val("");
					$("#deleteTableR").modal("hide");
					queryTableRList($("input#currentPage").val());
					
				}
					
			});
			
		});
		$("a#tablerViewLink").click(function(){
			$("input#isBindLink").val(0);
			$("input#currentPage").val(1);
			queryTableRList(1);
		});
		
		$("a#tablerModifyLink").click(function(){
			var id = $("input#currentClickRowId").val();
			$("div#updateTableR form input[name='id']").val(id);
			if(id==""){
				alert("请点击列表的餐桌进行修改");
				return false;
			}
			$.get("tabler/findTableRById",{id:id},function(data){
				for(var key in data){
					if(key=="status")
						$("div#updateTableR form select[name='"+key+"']").find("option[value = '"+data[key]+"']").attr("selected","selected");
					else
						$("div#updateTableR form input[name='"+key+"']").val(data[key]);
				}
				
			});
		});
		
		$("a#tablerDeleteLink").click(function(){
			var id = $("input#currentClickRowId").val();
			$("div#deleteTableR form input[name='id']").val(id);
			if(id==""){
				alert("请点击列表的餐桌进行删除");
				return false;
			}
				
			$.get("tabler/findTableRById",{id:id},function(data){
				for(var key in data){
					if(key=="status")
						$("div#deleteTableR form select[name='"+key+"']").find("option[value = '"+data[key]+"']").attr("selected","selected");
					else
						$("div#deleteTableR form input[name='"+key+"']").val(data[key]);
				}
			});
		});
		
	});