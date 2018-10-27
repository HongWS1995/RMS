function bindLink(){
		$("a#firstPage").unbind();
		$("a#prevPage").unbind();
		$("a#nextPage").unbind();
		$("a#lastPage").unbind();
		var currentPage;
		var totalPage;
		$("a#firstPage").click(function(){
			$("input#currentPage").val(1);	
			queryCategoryList(1);
			});
		$("a#prevPage").click(function(){
			currentPage = $("input#currentPage").val();
			if(currentPage>1){
				currentPage = parseInt(currentPage)-1;
				$("input#currentPage").val(currentPage);
				queryCategoryList(currentPage);
			}
				
		});
		$("a#nextPage").click(function(){
			currentPage = parseInt($("input#currentPage").val());
			totalPage  = parseInt($("input#totalPage").val());
			if(currentPage<totalPage){
				currentPage = parseInt(currentPage)+1;
				$("input#currentPage").val(currentPage);
				queryCategoryList(currentPage);
			}
				
		});
		$("a#lastPage").click(function(){
			totalPage  = $("input#totalPage").val();
			$("input#currentPage").val(totalPage);
			queryCategoryList(totalPage);
		});
		
	}
	function queryCategoryList(currentPage) {
		// var currentPage = $("input#currentPage").val();
		var totalPage=0;
	// var ptypeid = $("select#userTypeSelect").val().replace(/\\s*/g,"");
		var name = $("#queryForm input[name='name']").val().replace(/\\s*/g,"");
		var json ="'currentPage':"+currentPage+",'pageSize':8";
		if(name!="")
			json = json +",'name':'"+name+"'";
		json = "{"+json+"}";
		json = json.replace(/\'/g,'\"');
		json = JSON.parse(json);
		// alert(JSON.stringify(json));
		$.post("category/CategoryList",json,function(data){
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
				$("tbody#categoryTable").empty();
				if(beanList.length < 1)
					$("tbody#categoryTable").append("<tr><td colspan='3' style='text-align:center;'>无记录</td></tr>");
				
				for(var i = 0;i<beanList.length;i++){
					var tds="<td>"+((parseInt(currentPage)-1)*8+i+1)+"</td>";
					for(var key in beanList[i]){
						if(key=="products")
							break;
						tds +="<td>"+beanList[i][key]+"</td>";
					}	 
					$("tbody#categoryTable").append("<tr class='rowRecord'>"+tds+"</tr>");
					
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
        $("div#addCategory form").bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
            　feedbackIcons: {
                　　　　　　　　valid: 'glyphicon glyphicon-ok',
                　　　　　　　　invalid: 'glyphicon glyphicon-remove',
                　　　　　　　　validating: 'glyphicon glyphicon-refresh'
            　　　　　　　　   },
            fields: {
                name: {
                    message: '商品类名验证失败',
                    validators: {
                        notEmpty: {
                            message: '商品类名不能为空'
                        }
                    }
                }
            }
        });
    });
	
	$(function () {
        $("div#updateCategory form").bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
     feedbackIcons: {
                　　　　　　　　valid: 'glyphicon glyphicon-ok',
                　　　　　　　　invalid: 'glyphicon glyphicon-remove',
                　　　　　　　　validating: 'glyphicon glyphicon-refresh'
            　　　　　　　　   },
            fields: {
                name: {
                    message: '商品类名验证失败',
                    validators: {
                        notEmpty: {
                            message: '商品类名不能为空'
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
        $("div#deleteCategory form").bootstrapValidator({
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
		queryCategoryList(1);
		$("div#addCategory #addCategoryBtn").click(function(){
			var formdata = $("div#addCategory form").serialize();
			var name = $("div#addCategory form input[name='name']").val().replace(/\s*/g,"");
			if(name==""){
				alert("商品类别不能为空");
				return;
			}
				
			$.post("category/save",formdata,function(data){
				if(data.result=="success"){
					alert("保存成功");
					$("div#addCategory form")[0].reset();
					$("#addCategory").modal("hide");
					queryCategoryList($("input#currentPage").val());
				}else{
					alert("保存失败,参数非法");
				}
					
			},"json");
			
		});
		$("div#updateCategory #updateCategoryBtn").click(function(){	
			var formdata = $("div#updateCategory form").serialize();
			var id = $("div#updateCategory form input[name='id']").val().replace(/\s*/g,"");
			var name = $("div#updateCategory form input[name='name']").val().replace(/\s*/g,"");
			if(name==""||id==""){
				alert("请点击表格中您要修改的商品类别");
				return false;
			}
				
			$.post("category/update",formdata,function(data){
				if(data.result=="success"){
					alert("保存成功");
					$("div#updateCategory form")[0].reset();
					$("#updateCategory").modal("hide");
					$("input#currentClickRowId").val("");
					queryCategoryList($("input#currentPage").val());
				}else{
					alert("修改失败,参数非法");
				}
					
			},"json");
			
		});
		$("div#deleteCategory #deleteCategoryBtn").click(function(){
			var id = $("div#deleteCategory form input[name='id']").val().replace(/\s*/g,"");
			if(id==""){
				alert("请点击表格中您要删除的商品类别");
				return false;
			}
				
//			$.post("category/delete",{id:id},function(data){
//				if(data.result=="success"){
//					alert("删除成功");
//					 $("div#deleteCategory form")[0].reset();
//					$("#deleteCategory").modal("hide");
//					$("input#currentClickRowId").val("");
//					queryCategoryList($("input#currentPage").val());
//				}else{
//					alert("删除失败,该类别已有商品关联");
//				}
//					
//			});
			$.ajax({
				async:true,
				url:"category/delete",
				type:"post",
				data:{id:id},
				dataType:"json",
				success:function(data){
					if(data.result=="success"){
						alert("删除成功");
						 $("div#deleteCategory form")[0].reset();
						$("#deleteCategory").modal("hide");
						$("input#currentClickRowId").val("");
						queryCategoryList($("input#currentPage").val());
					}else{
						alert("删除失败,参数非法");
					}
				},
				error:function(xhr){
					alert("删除失败,该类别已有商品关联，请先修改相关商品在进行删除");
					//alert("错误信息："+xhr.status);
				}
			});
			
		});
		$("a#userViewLink").click(function(){
			$("input#isBindLink").val(0);
			$("input#currentPage").val(1);
			queryCategoryList(1);
		});
		
		$("a#userModifyLink").click(function(){
			var id = $("input#currentClickRowId").val();
			$("div#updateCategory form input[name='id']").val(id);
			if(id==""){
				alert("请点击表格中您要修改的商品类别");
				return false;
			}
			$.get("category/findCategoryById",{id:id},function(data){
				$("div#updateCategory form input[name='name']").val(data.name);
			});
		});
		
		$("a#userDeleteLink").click(function(){
			var id = $("input#currentClickRowId").val();
			$("div#deleteCategory form input[name='id']").val(id);
			if(id==""){
				alert("请点击表格中您要删除的商品类别");
				return false;
			}
			$.get("category/findCategoryById",{id:id},function(data){
				$("div#deleteCategory form input[name='name']").val(data.name);
			});
		});
		
	});