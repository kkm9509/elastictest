<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Elastic Search Demo</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	function changeType() {
		var radioType = document.getElementsByName('radioType');
		var btn = document.getElementById('btn');
		
		var chkValue;
		for(var i = 0; i < radioType.length; i++){
		    if(radioType[i].checked){
		    		chkValue = radioType[i].value;
		    }
		}

		if(chkValue == "search") {
			btn.value = "검색";
		} else if (chkValue == "delete") {
			btn.value = "삭제";
		} else if (chkValue == "put") {
			btn.value = "저장";
		}
		
		$(".all").css("display", "none");
		$("."+chkValue).css("display", "");
	}
	
	function esSearch() {
		var txtIndices = document.getElementById("txtIndices").value;
		var txtFrom = document.getElementById("txtFrom").value;
		var txtSize = document.getElementById("txtSize").value;

		var txtType = document.getElementById("txtType").value;
		var txtSearchField = document.getElementById("txtSearchField").value;
		var txtSearchPrefix = document.getElementById("txtSearchPrefix").value;

		var txtSortField = document.getElementById("txtSortField").value;
		var txtOrder = document.getElementById("txtOrder").value;
		
		if (txtIndices === "") {
			alert("필수값이 없습니다.");
			return;
		}
		
		var url = "es/search?indices=" + txtIndices + "&from="+ txtFrom + "&size=" + txtSize;
		url += "&type=" + txtType + "&searchField=" + txtSearchField + "&searchPrefix=" + txtSearchPrefix;
		url += "&sortField=" + txtSortField + "&order=" + txtOrder;
		
		$.get(url, function(data) {
				var jobj = JSON.parse(data);
				if (jobj.code != "0000") 
					alert(data);
				else {
					var result = "<table border='1' cellpadding='0' cellspacing='0'>";
					
					result +="<tr>";
					result += "<td width='100px' align='center'>id</td>";
					result += "<td width='100px' align='center'>type</td>";
					result += "<td width='100px' align='center'>user</td>";
					result += "<td width='100px' align='center'>post_date</td>";
					result += "<td width='100px' align='center'>message</td>";
					result +="</tr>";
					
					for (i in jobj.list) {
						item = jobj.list[i];
						result += "<tr>";
						result += "<td align='center'>" + item.id + "</td>"
						result += "<td align='center'>" + item.type + "</td>"
						result += "<td align='center'>" + item.user + "</td>"
						result += "<td align='center'>" + item.post_date + "</td>"
						result += "<td align='center'>" + item.message + "</td>"
						result += "</tr>";
					}
					
					result += "</table>";
					
					$("#divResult").html(result);
				}
			});
	}

	function esDelete() {
		var txtIndices = document.getElementById("txtIndices").value;
		var txtType = document.getElementById("txtType").value;
		var txtId = document.getElementById("txtId").value;
		
		if (txtIndices === "" || txtType === "" || txtId === "") {
			alert("필수값이 없습니다.");
			return;
		}
		
		var url = "es/delete?indices=" + txtIndices + "&type="+ txtType + "&id=" + txtId;
		
		if (confirm('삭제하시겠습니까?')) {
			$.get(url, function(data) {
					var jobj = JSON.parse(data);
					if (jobj.code != "0000") 
						alert(data);
					else {
						alert("삭제하였습니다.");
						esSearch();
					}
				});
		}
	}
	
	function esPut() {
		var txtIndices = document.getElementById("txtIndices").value;
		var txtType = document.getElementById("txtType").value;
		var txtId = document.getElementById("txtId").value;
		
		var txtUser = document.getElementById("txtUser").value;
		var txtMessage = document.getElementById("txtMessage").value;
		var txtPostDate = document.getElementById("txtPostDate").value;
		
		if (txtIndices === "" || txtType === "" || txtId === "") {
			alert("필수값이 없습니다.");
			return;
		}
		
		var url = "es/put?indices=" + txtIndices + "&type="+ txtType + "&id=" + txtId;
		url += "&user=" + txtUser + "&message=" + txtMessage + "&post_date=" + txtPostDate;
		
		$.get(url, function(data) {
				var jobj = JSON.parse(data);
				if (jobj.code != "0000") 
					alert(data);
				else {
					alert("저장되었습니다.");
					esSearch();
				}
			});
	}
	
	function btnClick() {
		var radioType = document.getElementsByName('radioType');
		var chkValue;
		for(var i = 0; i < radioType.length; i++){
		    if(radioType[i].checked){
		    		chkValue = radioType[i].value;
		    }
		}
		
		if(chkValue == "search") {
			esSearch();
		} else if (chkValue == "delete") {
			esDelete();
		} else if (chkValue == "put") {
			esPut();
		}
	}
	
</script>
</head>
<body>
	<div>
		검색 <input name="radioType" type="radio" onchange="javascript:changeType()" checked="checked" value="search">&emsp;
		등록/수정 <input name="radioType" type="radio" onchange="javascript:changeType()" value="put">&emsp;
		삭제 <input name="radioType" type="radio" onchange="javascript:changeType()" value="delete">&emsp;
		<input id="btn" type="button" value="검색" style="width:70px" onClick="javascript:btnClick()">
		<table>
			<tr class="all search delete put">
				<td style="width:100px">
					indices
				</td>
				<td>
					: <input type="text" id="txtIndices" size="10" value="twitter">
				</td>
			</tr>
			<tr class="all search">
				<td>
					From
				</td>
				<td>
					: <input type="text" id="txtFrom" size="10">
				</td>
			</tr>
			<tr class="all search">
				<td>
					size
				</td>
				<td>
					: <input type="text" id="txtSize" size="10">
				</td>
			</tr>
			<tr class="all search delete put">
				<td>
					type
				</td>
				<td>
					: <input type="text" id="txtType" size="10" value="view">
				</td>
			</tr>
			<tr class="all search">
				<td>
					search field
				</td>
				<td>
					: <input type="text" id="txtSearchField" size="10">
				</td>
			</tr>
			<tr class="all search">
				<td>
					search prefix
				</td>
				<td>
					: <input type="text" id="txtSearchPrefix" size="10">
				</td>
			</tr>
			<tr class="all search">
				<td>
					sort field
				</td>
				<td>
					: <input type="text" id="txtSortField" size="10">
				</td>
			</tr>
			<tr class="all search">
				<td>
					sort order
				</td>
				<td>
					: <input type="text" id="txtOrder" size="10">
				</td>
			</tr>
			<tr class="all delete put">
				<td>
					id
				</td>
				<td>
					: <input type="text" id="txtId" size="10">
				</td>
			</tr>
			<tr class="all put">
				<td>
					user
				</td>
				<td>
					: <input type="text" id="txtUser" size="10">
				</td>
			</tr>
			<tr class="all put">
				<td>
					post_data
				</td>
				<td>
					: <input type="text" id="txtPostDate" size="10">
				</td>
			</tr>
			<tr class="all put">
				<td>
					message
				</td>
				<td>
					: <input type="text" id="txtMessage" size="10">
				</td>
			</tr>
		</table>
	</div>
	<div id="divResult" style="margin-top:30px"></div>
	<script type="text/javascript">changeType();</script>
</body>
</html>