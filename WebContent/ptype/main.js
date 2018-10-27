function bindLink(){
		$("a#firstPage").unbind();
		$("a#prevPage").unbind();
		$("a#nextPage").unbind();
		$("a#lastPage").unbind();
		var currentPage;
		var totalPage;
		$("a#firstPage").click(function(){
			$("input#currentPage").val(1);	
			queryPtypeList(1);
			});
		$("a#prevPage").click(function(){
			currentPage = $("input#currentPage").val();
			if(currentPage>1){
				currentPage = parseInt(currentPage)-1;
				$("input#currentPage").val(currentPage);
				queryPtypeList(currentPage);
			}
				
		});
		$("a#nextPage").click(function(){
			currentPage = parseInt($("input#currentPage").val());
			totalPage  = parseInt($("input#totalPage").val());
			if(currentPage<totalPage){
				currentPage = parseInt(currentPage)+1
				$("input#currentPage").val(currentPage);
				queryPtypeList(currentPage);
			}
				
		});
		$("a#lastPage").click(function(){
			totalPage  = $("input#totalPage").val();
			$("input#currentPage").val(totalPage);
			queryPtypeList(totalPage);
		});
		
	}
	function queryPtypeList(currentPage) {
		//var currentPage = $("input#currentPage").val();
		var totalPage=0;
	//	var ptypeid = $("select#ptypeTypeSelect").val().replace(/\\s*/g,"");
		var name = $("#queryForm input[name='name']").val().replace(/\\s*/g,"");
		var json ="'currentPage':"+currentPage+",'pageSize':8";
		if(name!="")
			json = json +",'name':'"+name+"'";
		json = "{"+json+"}";
		json = json.replace(/\'/g,'\"');
		json = JSON.parse(json);
		//alert(JSON.stringify(json));
		$.post("ptype/PtypeList",json,function(data){
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
				$("tbody#ptypeTable").empty();
				if(beanList.length < 1)
					$("tbody#ptypeTable").append("<tr><td colspan='3' style='text-align:center;'>无记录</td></tr>");
				
				for(var i = 0;i<beanList.length;i++){
					var tds="<td>"+((parseInt(currentPage)-1)*8+i+1)+"</td>";
					for(var key in beanList[i]){
						tds +="<td>"+beanList[i][key]+"</td>";
					}	 
					$("tbody#ptypeTable").append("<tr class='rowRecord'>"+tds+"</tr>");
					
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
        $("div#addPtype form").bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
            　feedbackIcons: {
                　　　　　　　　valid: 'glyphicon glyphicon-ok',
                　　　　　　　　invalid: 'glyphicon glyphicon-remove',
                　　　　　　　　validating: 'glyphicon glyphicon-refresh'
            　　　　　　　　   },
            fields: {
                name: {
                    message: '员工类型验证失败',
                    validators: {
                        notEmpty: {
                            message: '员工类型不能为空'
                        }
                    }
                }
            }
        });
    });
	$(function () {
        $("div#updatePtype form").bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
            　feedbackIcons: {
                　　　　　　　　valid: 'glyphicon glyphicon-ok',
                　　　　　　　　invalid: 'glyphicon glyphicon-remove',
                　　　　　　　　validating: 'glyphicon glyphicon-refresh'
            　　　　　　　　   },
            fields: {
                name: {
                    message: '员工类型验证失败',
                    validators: {
                        notEmpty: {
                            message: '员工类型不能为空'
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
        $("div#deletePtype form").bootstrapValidator({
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
		queryPtypeList(1);
		$("div#addPtype #addPtypeBtn").click(function(){
			var formdata = $("div#addPtype form").serialize();
			var name = $("div#addPtype form input[name='name']").val().replace(/\s*/g,"");
			if(name==""){
				alert("员工类型不能为空");
				return false;
			}
				
			$.post("ptype/save",formdata,function(data){
				if(data.result=="success"){
					alert("保存成功");
					$("div#addPtype form")[0].reset();
					$("#addPtype").modal("hide");
					queryPtypeList($("input#currentPage").val());
				}
					
			},"json");
		
		});
		$("div#updatePtype #updatePtypeBtn").click(function(){	
			var formdata = $("div#updatePtype form").serialize();
			var id = $("div#updatePtype form input[name='id']").val().replace(/\s*/g,"");
			var name = $("div#updatePtype form input[name='name']").val().replace(/\s*/g,"");
			if(name==""||id==""){
				alert("请点击表格中您要修改的员工类型");
				return false;
			}
				
			$.post("ptype/update",formdata,function(data){
				if(data.result=="success"){
					alert("保存成功");
					$("div#updatePtype form")[0].reset();
					$("input#currentClickRowId").val("");
					$("#updatePtype").modal("hide");
					queryPtypeList($("input#currentPage").val());
				}
					
			},"json");
			
		});
		$("div#deletePtype #deletePtypeBtn").click(function(){
			var id = $("div#deletePtype form input[name='id']").val().replace(/\s*/g,"");
			if(id==""){
				alert("请点击表格中您要删除的员工类型");
				return false;
			}
				
//			$.post("ptype/delete",{id:id},function(data){
//				if(data.result=="success"){
//					alert("删除成功");
//					$("div#deletePtype form")[0].reset();
//					$("#deletePtype").modal("hide");
//					$("input#currentClickRowId").val("");
//					queryPtypeList($("input#currentPage").val());
//				}
//					
//			});
			$.ajax({
				async:true,
				url:"ptype/delete",
				data:{id:id},
				dataType:"json",
				success:function(data){
					if(data.result=="success"){
						alert("删除成功");
						$("div#deletePtype form")[0].reset();
						$("#deletePtype").modal("hide");
						$("input#currentClickRowId").val("");
						queryPtypeList($("input#currentPage").val());
					}
				},
				error:function(data,status){
					alert("该员工类型已有员工关联，请先修改相关员工信息！");
				}
			});
			
		});
		$("a#ptypeViewLink").click(function(){
			$("input#isBindLink").val(0);
			$("input#currentPage").val(1);
			queryPtypeList(1);
		});
		
		$("a#ptypeModifyLink").click(function(){
			var id = $("input#currentClickRowId").val();
			$("div#updatePtype form input[name='id']").val(id);
			if(id==""){
				alert("请点击列表的员工类型进行修改");
				return false;
			}
			$.get("ptype/findPtypeById",{id:id},function(data){
				$("div#updatePtype form input[name='name']").val(data.name);
			});
		});
		
		$("a#ptypeDeleteLink").click(function(){
			var id = $("input#currentClickRowId").val();
			$("div#deletePtype form input[name='id']").val(id);
			if(id==""){
				alert("请点击列表的员工类型进行删除");
				return false;
			}
			$.get("ptype/findPtypeById",{id:id},function(data){
				$("div#deletePtype form input[name='name']").val(data.name);
			});
		});
		
	});