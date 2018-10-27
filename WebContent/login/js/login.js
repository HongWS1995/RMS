$(document).ready(function(){
  			$("#login_btn").click(function(){
  				var username = $("#inputUsername").val();
  				var password = $("#inputPassword").val();
  				if(username!=undefined&&password!=undefined&&username!=""&&password!=""){
  					$.post("user/validateUser",{username:username,password:password},function(data){
  	  					if(data.result=="fail"){
  	  						$("#inputPassword").val("");
  	  						alert(data.message);
  	  						$("#alert").append("<p style='color:red'>"+data.message+"</p>");
  	  						return;
  	  					}else{
  	  						window.location.href = "user/index";
  	  					}
  	  				});
  				}else{
  					return;
  				}
  				
  			});
  		});