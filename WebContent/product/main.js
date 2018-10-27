function bindLink(){
		$("a#firstPage").unbind();
		$("a#prevPage").unbind();
		$("a#nextPage").unbind();
		$("a#lastPage").unbind();
		var currentPage;
		var totalPage;
		$("a#firstPage").click(function(){
			$("input#currentPage").val(1);	
			queryProductList(1);
			});
		$("a#prevPage").click(function(){
			currentPage = parseInt($("input#currentPage").val());
			if(currentPage>1){
				currentPage = parseInt(currentPage)-1;
				$("input#currentPage").val(currentPage);
				queryProductList(currentPage);
			}
				
		});
		$("a#nextPage").click(function(){
			currentPage = parseInt($("input#currentPage").val());
			totalPage  = parseInt($("input#totalPage").val());
			if(currentPage<totalPage){
				currentPage = parseInt(currentPage)+1;
				$("input#currentPage").val(currentPage);
				queryProductList(currentPage);
			}
				
		});
		$("a#lastPage").click(function(){
			totalPage  = $("input#totalPage").val();
			$("input#currentPage").val(totalPage);
			queryProductList(totalPage);
		});
		
	}
	function queryProductList(currentPage) {
		//var currentPage = $("input#currentPage").val();
		var totalPage=0;
		var cid = $("select#productTypeSelect").val();
		var name = $("input[name='name']").val();
		if(cid!=null&&name!=null){
			cid =cid.replace(/\s*/g,"");
			name = name.replace(/\s*/g,"");
		}
		var json ="'currentPage':"+currentPage+",'pageSize':5";
		if(cid!=""&&cid!=null)
			json = json +",'cid':'"+cid+"'";
		if(name!=""&&name!=null)
			json = json +",'name':'"+name+"'";
		json = "{"+json+"}";
		json = json.replace(/\'/g,'\"');
		json = JSON.parse(json);
		//alert(JSON.stringify(json));
		$.post("product/ProductList",json,function(data){
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
				$("tbody#productTable").empty();
				if(beanList.length < 1)
					$("tbody#productTable").append("<tr><td colspan='6' style='text-align:center;'>无记录</td></tr>");
				for(var i = 0;i<beanList.length;i++){
					
					var tds="<td class='textCenter'>"+((parseInt(currentPage)-1)*5+i+1)+"</td>";
					for(var key in beanList[i]){
						var value;		
						if(key!="cid"){
							if(key=='isRemove'){
								continue;
							}
							value=beanList[i][key];
							if(value!=null){
								if(key=="category"){
									tds +="<td class='textCenter'>"+value['name']+"</td>";
								}else if(key=="imagepath"){
										tds +="<td class='textCenter'><img src='"+value+"' style='width:50px;height:32px;'></img></td>";
								}else{
											tds +="<td class='textCenter'>"+value+"</td>";
								}
							}	
							else
								tds +="<td></td>";
						}
							
					}	 
					$("tbody#productTable").append("<tr class='rowRecord'>"+tds+"</tr>");
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
        $("div#addProduct form").bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
            　feedbackIcons: {
                　　　　　　　　valid: 'glyphicon glyphicon-ok',
                　　　　　　　　invalid: 'glyphicon glyphicon-remove',
                　　　　　　　　validating: 'glyphicon glyphicon-refresh'
            　　　　　　　　   },
            fields: {
                name: {
                    message: '商品名验证失败',
                    validators: {
                        notEmpty: {
                            message: '商品名不能为空'
                        }
                    }
                }
            }
        });
    });
	$(function () {
        $("div#updateProduct form").bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
            　feedbackIcons: {
                　　　　　　　　valid: 'glyphicon glyphicon-ok',
                　　　　　　　　invalid: 'glyphicon glyphicon-remove',
                　　　　　　　　validating: 'glyphicon glyphicon-refresh'
            　　　　　　　　   },
            fields: {
                name: {
                    message: '商品名验证失败',
                    validators: {
                        notEmpty: {
                            message: '商品名不能为空'
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
        $("div#deleteProduct form").bootstrapValidator({
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
	
	function getPtypeList(){
		$.get("category/CategoryList",{currentPage:1,pageSize:100},function(data){
			if(data.result=="success"){
				var beanList = data.pageBean['beanList'];
				var ops;
				for(var i=0;i<beanList.length;i++){
					var bean = beanList[i];
					if(i==0)
						ops +="<option value="+bean['id']+">"+bean['name']+"</option>";
					else
						ops +="<option value="+bean['id']+">"+bean['name']+"</option>";
				}
				$("select.productSelect").append(ops);
			//	queryProductList(1);
			}
		});
	}
	$(document).ready(function(){
		$("div#pageButton").hide();
		getPtypeList();
		$("a#productViewLink").click(function(){
			$("input#isBindLink").val(0);
			$("input#currentPage").val(1);
			queryProductList(1);
		});
		//保存商品
		$("div#addProduct #addProductBtn").click(function(){
			var name = $("div#addProduct form input[name='name']").val().replace(/\s*/g,"");
			var cid = $("div#addProduct form select[name='cid']").val();
			if(name==""||cid==""){
				alert("商品名和商品类型不能为空");
				return false;
			}
				
// 			$.post("product/save",formdata,function(data){
// 				if(data.result=="success")
// 					alert("保存成功");
// 				$("#addProduct").modal("hide");
// 			},"json",false);
			 var formData = new FormData($("div#addProduct form")[0]);
			    $.ajax({
			        async : false,
			        cache : false,
			        type : "post",
			        data : formData,
			        url : 'product/save',
			        dataType : 'json',
			        contentType:false, //必须
			        processData: false, //必须
			        success : function(data) {
			        	if(data.result=="success"){
			        		alert("保存成功");
			        		$("div#addProduct form")[0].reset();
							$("#addProduct").modal("hide");
							queryProductList($("input#currentPage").val());
			        	}
							
			        },
			        error : function(arg1, arg2, arg3) {
			        	alert("增加异常！");
			            console.log(arg1 + "--" + arg2 + "--" + arg3);
			        }
			    });
			
		});
		
		$("div#updateProduct #updateProductBtn").click(function(){	
			//var formdata = $("div#updateProduct form").serialize();
			var id = $("div#updateProduct form input[name='id']").val().replace(/\s*/g,"");
			var name = $("div#updateProduct form input[name='name']").val().replace(/\s*/g,"");
			var cid = $("div#updateProduct form select[name='cid']").val();
			if(id==""){
				alert("请点击表格中您要删除的商品");
				return false;
			}
			if(name==""||cid==""){
				alert("商品名和商品类型不能为空");
				return false;
			}		
// 			$.post("product/update",formdata,function(data){
// 				if(data.result=="success")
// 					alert("保存成功");
// 					$("#updateProduct").modal("hide");
// 			},"json");
			var formData = new FormData($("div#updateProduct form")[0]);
		    $.ajax({
		        async : false,
		        cache : false,
		        type : "post",
		        data : formData,
		        url : 'product/update',
		        dataType : 'json',
		        contentType:false, //必须
		        processData: false, //必须
		        success : function(data) {
		        	if(data.result=="success"){
		        		alert("保存成功");
		        		$("div#updateProduct form")[0].reset();
		        		$("input#currentClickRowId").val("");
						$("#updateProduct").modal("hide");
						queryProductList($("input#currentPage").val());	
		        	}
						
		        },
		        error : function(arg1, arg2, arg3) {
		        	alert("修改异常！");
		            console.log(arg1 + "--" + arg2 + "--" + arg3);
		        }
		    });
			
		});
		$("div#deleteProduct #deleteProductBtn").click(function(){
			var id = $("div#deleteProduct form input[name='id']").val().replace(/\s*/g,"");
			if(id==""){
				alert("请点击表格中您要删除的商品");
				return false;
			}
				
			$.post("product/delete",{id:id},function(data){
				if(data.result=="success")
					alert("删除成功");
					$("div#deleteProduct form")[0].reset();
					$("input#currentClickRowId").val("");
					$("#deleteProduct").modal("hide");
					queryProductList($("input#currentPage").val());
			});
			
		});
		$("a#productModifyLink").click(function(){
			var id = $("input#currentClickRowId").val();
			$("div#updateProduct form input[name='id']").val(id);
			if(id==""){
				alert("请点击表格中您要修改的商品");
				return false;
			}
			$.get("product/findProductById",{id:id},function(data){
				for(var key in data){
					if(key=="imagepath")
						break;
					if(key=="cid")
						$("div#updateProduct form select[name='"+key+"']").find("option[value = '"+data[key]+"']").attr("selected","selected");
					else
						$("div#updateProduct form input[name='"+key+"']").val(data[key]);
				}
			});
		});
		
		$("a#productDeleteLink").click(function(){
			var id = $("input#currentClickRowId").val();
			$("div#deleteProduct form input[name='id']").val(id);
			if(id==""){
				alert("请点击表格中您要删除的商品");
				return false;
			}
			$.get("product/findProductById",{id:id},function(data){
				for(var key in data){
					if(key=="cid")
						$("div#deleteProduct form select[name='"+key+"']").find("option[value = '"+data[key]+"']").attr("selected","selected");
					else
						$("div#deleteProduct form input[name='"+key+"']").val(data[key]);
				}
			});
		});
		queryProductList(1);
	});