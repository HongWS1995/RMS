function orderdetail(id,flag){
		this.id = id;
		this.flag = flag;
	}
	
	function bindLink(){
		$("a#firstPage").unbind();
		$("a#prevPage").unbind();
		$("a#nextPage").unbind();
		$("a#lastPage").unbind();
		var currentPage;
		var totalPage;
		$("a#firstPage").click(function(){
			$("input#currentPage").val(1);	
			queryOrderList(1);
			});
		$("a#prevPage").click(function(){
			currentPage = $("input#currentPage").val();
			if(currentPage>1){
				currentPage = parseInt(currentPage)-1;
				$("input#currentPage").val(currentPage);
				queryOrderList(currentPage);
			}
				
		});
		$("a#nextPage").click(function(){
			currentPage = parseInt($("input#currentPage").val());
			totalPage  = parseInt($("input#totalPage").val());
			if(currentPage<totalPage){
				currentPage = parseInt(currentPage)+1;
				$("input#currentPage").val(currentPage);
				queryOrderList(currentPage);
			}
				
		});
		$("a#lastPage").click(function(){
			totalPage  = $("input#totalPage").val();
			$("input#currentPage").val(totalPage);
			queryOrderList(totalPage);
		});
		
	}
	function queryOrderList(currentPage) {
		var totalPage=0;
		var id = $("input[name='id']").val();
		var tableId = $("select#tableSelect").val();
		var status = $("select#statusSelect").val();
		var json ="'currentPage':"+currentPage+",'pageSize':5";
		if(id!=null&&id!=undefined){
			id =id.replace(/\s*/g,"");
		}
		if(tableId!=null&&tableId!=undefined){
			tableId =tableId.replace(/\s*/g,"");
		}
		if(status!=null&&status!=undefined){
			status =status.replace(/\s*/g,"");
		}
		if(id!="")
			json = json +",'id':'"+id+"'";
		if(tableId!="")
			json = json +",'tableId':'"+tableId+"'";
		if(status!="")
			json = json +",'status':'"+status+"'";
		json = "{"+json+"}";
		json = json.replace(/\'/g,'\"');
		json = JSON.parse(json);
		//alert(JSON.stringify(json));
		$.post("order/OrderList",json,function(data){
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
				$("tbody#orderTable").empty();
				if(beanList.length < 1)
					$("tbody#orderTable").append("<tr><td colspan='8' style='text-align:center;'>无记录</td></tr>");
				for(var i = 0;i<beanList.length;i++){
					
					var tds="<td>"+((parseInt(currentPage)-1)*5+i+1)+"</td>";
					for(var key in beanList[i]){
						var value;
						if(beanList[i][key]==null&&key=="tableR"){
							tds +="<td></td>";
							continue;
						}
							
						if(key=="tableId")
							continue;
						if(key=="tableR"){
							tds +="<td>"+beanList[i][key]['tableName']+"</td>";
							continue;
						}
						if(key=="orderdetails"){
							tds +="<td><a href='#' class='btn btn-default ShowOrderDetailLink'>订单详情</a></td>";
							continue;
						}
						if(key!="cid"){
							value=beanList[i][key];
							if(value!=null){
								tds +="<td>"+value+"</td>";
							}else
								tds +="<td></td>";
						}
							
					}	 
					$("tbody#orderTable").append("<tr class='rowRecord'>"+tds+"</tr>");
				}
				$(".ShowOrderDetailLink").click(function(){
					$("#showOrderDetail").modal("show");
					orderID = $(this).parent("td").parent("tr").children("td:nth-child(2)").text();
// 					alert(orderID);
					$.get("order/getOrderDetailById",{id:orderID},function(order){
						console.log(JSON.stringify(order));
						$("div#showOrderDetail span#orderId").text(order.id);
						$("div#showOrderDetail tfoot td[name='total']").text("共计:"+order.total+"元");
						$("div#showOrderDetail form textarea[name='remark']").val(order.remark);
						var orderDetails = order.orderdetails;
						$("div#showOrderDetail tbody").empty();
						for(var i = 0;i<orderDetails.length;i++){
							var tds= "<td>"+(i+1)+"</td>";
							tds += "<input type='hidden' id='"+orderDetails[i]['id']+"'></td>";
							tds += "<td>"+orderDetails[i]['product']['name']+"</td>";
							tds += "<td>"+orderDetails[i]['product']['price']+"</td>";
							tds += "<td>"+orderDetails[i]['count']+"</td>";
							if(orderDetails[i]['flag']=="1")
								tds += "<td class='no-print'><input type='checkbox' id='"+orderDetails[i]['id']+"' name='flag' value='"+orderDetails[i]['flag']+"' checked> 已上菜</td>";
							else
								tds += "<td class='no-print'><input type='checkbox' id='"+orderDetails[i]['id']+"' name='flag' value='"+orderDetails[i]['flag']+"'> 已上菜</td>";
							$("div#showOrderDetail tbody").append("<tr class='detailRecord'>"+tds+"</tr>");
						}
					});
				});
				$(".rowRecord").click(function(){
					$(".rowRecord:odd").css("background-color","white");
					$(".rowRecord:even").css("background-color","#f9f9f9");
					$(this).css("background-color","#fbec88ad");
					$("input#currentClickRowId").val($(this).children("td:nth-child(2)").text());
					$("input#currentClickTableName").val($(this).children("td:nth-child(7)").text());
					$("input#currentClickStatus").val($(this).children("td:nth-child(4)").text());
				});
			}
			
		});
	}
	$(document).ready(function(){
		$("div#pageButton").hide();
		//显示座位
			$.get("tabler/getAllTableR",function(data){
				if(data.result=="success"){
					var beanList = data['beanList'];
					var ops="";
					for(var i = 0;i<beanList.length;i++){
						var bean = beanList[i];
						ops +="<option value="+bean['id']+">"+bean['tableName']+"</option>";
					}
					$("select#tableSelect").append(ops);
				}
				
			});
		$("a#orderViewLink").click(function(){
			$("input#isBindLink").val(0);
			$("input#currentPage").val(1);
			queryOrderList(1);
		});
		$("a#orderModifyLink").click(function(){
			var id = $("input#currentClickRowId").val();
			var tableName = $("input#currentClickTableName").val();
			var status = $("input#currentClickStatus").val();
			$("div#updateOrder form input[name='id']").val(id);
			$("div#updateOrder form input[name='TableName']").val(tableName);
			if(id==""){
				alert("请点击表格中您要修改的订单状态");
				return false;
			}
			$.get("order/findOrderById",{id:id},function(data){
				$("div#updateOrder form select[name='orderstatus']").find("option[value='"+data.status+"']").attr("selected","selected");
			});
		});
		
		$("div#updateOrder #updateOrderBtn").click(function(){	
			var formdata = $("div#updateOrder form").serialize();
			var id = $("div#updateOrder form input[name='id']").val().replace(/\s*/g,"");
			var status = $("div#updateOrder form select[name='orderstatus']").val().replace(/\s*/g,"");
			if(id==""){
				alert("请点击表格中您要修改的订单");
				return false;
			}
			if(status==""){
				alert("请修改订单状态！");
				return false;
			}		
			$.post("order/update",{id:id,status:status},function(data){
				doSend(id,status);
				if(data.result=="success"){
					alert("保存成功");
					$("div#updateOrder form")[0].reset();
					$("input#currentClickRowId").val("");
					$("#updateOrder").modal("hide");
					queryOrderList($("input#currentPage").val());
				}
					
			},"json");
			
		});
		
		$("#updateOrderDetail").click(function(){
			var checkedItems = $("div#showOrderDetail input[type='checkbox']");
			var orderDetails = new Array();
			for(var  i = 0;i < checkedItems.length;i++){
				var id = checkedItems[i].id;
				var flag;
				if(checkedItems[i].checked==true)
					flag = '1';
				else
					flag = '0';
				var detail = new orderdetail(id,flag);
				orderDetails.push(detail);
			}
			 $.ajax({  
                 type:"POST",  
                 url: "orderdetail/updateBatch",  
                 dataType:"json",  
                 contentType:"application/json", // 指定这个协议很重要  
                 data:JSON.stringify(orderDetails),  
                 success:function(data){  
                	if(data.result="success"){
                		alert("已修改状态！");
                		$("div#showOrderDetail").modal("hide");
                	}    					
     				else
     					alert("修改失败，请重新修改");
     				
                 }  
             });  
		});
		$("button#printOrder").on("click",function(){
			jQuery('div#printArea').print();
		});
		//加载完成默认查询
		queryOrderList(1);
	});