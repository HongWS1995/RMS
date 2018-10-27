function bindLink(){
		$("a#firstPage").unbind();
		$("a#prevPage").unbind();
		$("a#nextPage").unbind();
		$("a#lastPage").unbind();
		var currentPage;
		var totalPage;
		$("a#firstPage").click(function(){
			$("input#currentPage").val(1);	
			queryUserList(1);
			});
		$("a#prevPage").click(function(){
			currentPage = $("input#currentPage").val();
			if(currentPage>1){
				currentPage = parseInt(currentPage)-1;
				$("input#currentPage").val(currentPage);
				queryUserList(currentPage);
			}
				
		});
		$("a#nextPage").click(function(){
			currentPage = parseInt($("input#currentPage").val());
			totalPage  = parseInt($("input#totalPage").val());
			if(currentPage<totalPage){
				currentPage = parseInt(currentPage)+1
				$("input#currentPage").val(currentPage);
				queryUserList(currentPage);
			}
				
		});
		$("a#lastPage").click(function(){
			totalPage  = $("input#totalPage").val();
			$("input#currentPage").val(totalPage);
			queryUserList(totalPage);
		});
		
	}
	function queryUserList(currentPage) {
		//var currentPage = $("input#currentPage").val();
		var totalPage=0;
		var ptypeId = $("select#userTypeSelect").val();
		var username = $("input[name='username']").val();
		if(ptypeId!=null&&username!=null){
			ptypeId =ptypeId.replace(/\s*/g,"");
			username = username.replace(/\s*/g,"");
		}
		var json ="'currentPage':"+currentPage+",'pageSize':8";
		if(ptypeId!=""&&ptypeId!=null)
			json = json +",'ptypeId':'"+ptypeId+"'";
		if(username!=""&&username!=null)
			json = json +",'username':'"+username+"'";
		json = "{"+json+"}";
		json = json.replace(/\'/g,'\"');
		json = JSON.parse(json);
		//alert(JSON.stringify(json));
		$.post("user/UserList",json,function(data){
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
				$("tbody#userTable").empty();
				if(beanList.length < 1)
					$("tbody#userTable").append("<tr><td colspan='10' style='text-align:center;'>无记录</td></tr>");
				
				for(var i = 0;i<beanList.length;i++){
					
					var tds="<td>"+((parseInt(currentPage)-1)*8+i+1)+"</td>";
					for(var key in beanList[i]){
						var value;
						if(key!="password"&&key!="isstaff"&&key!="ptypeId"){
							value=beanList[i][key];
							if(value!=null){
								if(key=="islogin"){
									if(value=="Y")
										tds +="<td>在线</td>";
									else
										tds +="<td>离线</td>";
									continue;
								}
								if(key=="canlogin"){
									if(value=="0")
										tds +="<td>禁止登陆</td>";
									else
										tds +="<td>授权登陆</td>";
									continue;
								}
								if(key=="ptype"){
									tds +="<td>"+value['name']+"</td>";
								}else{
									tds +="<td>"+value+"</td>";
								}
							}	
							else
								tds +="<td></td>";
						}
							
					}	 
					$("tbody#userTable").append("<tr class='rowRecord'>"+tds+"</tr>");
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
        $("div#addUser form").bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
            　feedbackIcons: {
                　　　　　　　　valid: 'glyphicon glyphicon-ok',
                　　　　　　　　invalid: 'glyphicon glyphicon-remove',
                　　　　　　　　validating: 'glyphicon glyphicon-refresh'
            　　　　　　　　   },
            fields: {
                username: {
                    message: '员工名验证失败',
                    validators: {
                        notEmpty: {
                            message: '员工名不能为空'
                        }
                    }
                }
            }
        });
    });
	$(function () {
        $("div#updateUser form").bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
            　feedbackIcons: {
                　　　　　　　　valid: 'glyphicon glyphicon-ok',
                　　　　　　　　invalid: 'glyphicon glyphicon-remove',
                　　　　　　　　validating: 'glyphicon glyphicon-refresh'
            　　　　　　　　   },
            fields: {
                username: {
                    message: '员工名验证失败',
                    validators: {
                        notEmpty: {
                            message: '员工名不能为空'
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
        $("div#deleteUser form").bootstrapValidator({
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
		$.get("ptype/PtypeList",{currentPage:1,pageSize:100},function(data){
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
				$("select.userSelect").append(ops);
			//	queryUserList(1);
			}
		});
	}
	$(document).ready(function(){
		$("div#pageButton").hide();
		getPtypeList();
		$("a#userViewLink").click(function(){
			$("input#isBindLink").val(0);
			$("input#currentPage").val(1);
			queryUserList(1);
		});
		
		$("div#addUser #addUserBtn").click(function(){
			var formdata = $("div#addUser form").serialize();
			var username = $("div#addUser form input[name='username']").val().replace(/\s*/g,"");
			var password = $("div#addUser form input[name='password']").val();
			if(username==""||password==""){
				alert("员工名和密码不能为空");
				return false;
			}
			
			$.post("user/save",formdata,function(data){
				if(data.result=="success"){
					alert("保存成功");
					$("div#addUser form")[0].reset();
					$("#addUser").modal("hide");
					queryUserList($("input#currentPage").val());
				}	
				else
					alert("用户名与系统原有用户重复！");
				
			},"json",false);
			
		});
		$("div#updateUser #updateUserBtn").click(function(){	
			var formdata = $("div#updateUser form").serialize();
			var id = $("div#updateUser form input[name='id']").val().replace(/\s*/g,"");
			var username = $("div#updateUser form input[name='username']").val().replace(/\s*/g,"");
			//var password = $("div#updateUser form input[name='password']").val().replace(/\s*/g,"");
			if(id==""){
				alert("请点击表格中您要删除的员工");
				return false;
			}
			if(username==""){
				alert("员工名不能为空");
				return false;
			}		
			$.post("user/update",formdata,function(data){
				if(data.result=="success"){
					alert("保存成功");
					 $("div#updateUser form")[0].reset();
					$("input#currentClickRowId").val("");
					$("#updateUser").modal("hide");
					queryUserList($("input#currentPage").val());
				}
					
			},"json");
			
		});
		$("div#deleteUser #deleteUserBtn").click(function(){
			var id = $("div#deleteUser form input[name='id']").val().replace(/\s*/g,"");
			var username = $("div#deleteUser form input[name='username']").val();
			if(id==""){
				alert("请点击表格中您要删除的用户");
				return false;
			}
			if(username=='admin'){
				alert("管理员账号无法删除");
				return false;
			}
					
			$.post("user/delete",{id:id},function(data){
				if(data.result=="success"){
					alert("删除成功");
					 $("div#deleteUser form")[0].reset();
					$("input#currentClickRowId").val("");
					$("#deleteUser").modal("hide");
					queryUserList($("input#currentPage").val());
				}
					
			});
			
		});
		$("a#userModifyLink").click(function(){
			var id = $("input#currentClickRowId").val();
			$("div#updateUser form input[name='id']").val(id);
			if(id==""){
				alert("请点击表格中您要修改的员工");
				return false;
			}
			$.get("user/findUserById",{id:id},function(data){
				for(var key in data){
					if(key=="canlogin"||key=="ptypeId")
						$("div#updateUser form select[name='"+key+"']").find("option[value = '"+data[key]+"']").attr("selected","selected");
					else
						$("div#updateUser form input[name='"+key+"']").val(data[key]);
				}
			});
		});
		
		$("a#userDeleteLink").click(function(){
			var id = $("input#currentClickRowId").val();
			$("div#deleteUser form input[name='id']").val(id);
			if(id==""){
				alert("请点击表格中您要删除的员工");
				return false;
			}
			if(id=="10000"){
				alert("您没有权限删除超级管理员账号！");
				return false;
			}
			$.get("user/findUserById",{id:id},function(data){
				for(var key in data){
					if(key=="canlogin"||key=="ptypeId")
						$("div#deleteUser form select[name='"+key+"']").find("option[value = '"+data[key]+"']").attr("selected","selected");
					else
						$("div#deleteUser form input[name='"+key+"']").val(data[key]);
				}
			});
		});
		$("a#uploadPayImageLink").click(function(){
			$("div#uploadPayImageModal").modal("show");
		});
		$("button#uploadPayImageBtn").click(function(){
			var formData = new FormData($("div#uploadPayImageModal form")[0]);
		    $.ajax({
		        async : false,
		        cache : false,
		        type : "post",
		        data : formData,
		        url : 'user/updatePayImage',
		        dataType : 'json',
		        contentType:false, //必须
		        processData: false, //必须
		        success : function(data) {
		        	if(data.result=="success"){
		        		alert("保存成功");
		        		$("div#uploadPayImageModal form")[0].reset();
						$("#uploadPayImageModal").modal("hide");	
		        	}else{
		        		alert("上传图片失败");
		        	}
						
		        },
		        error : function(arg1, arg2, arg3) {
		        	alert("服务器异常！");
		            console.log(arg1 + "--" + arg2 + "--" + arg3);
		        }
		    });
		});
		//加载进行查询
		queryUserList(1);
	});