<!DOCTYPE html>
<html>
<head>
<style>
#contentTable, th, td{
border : solid 1px black;
}
</style>
</head>
<body>
<h2>Ajax Example with Servlet</h2>
 
<button onclick="callAjax();">Get Data</button>
<br/><br/>
 
 
<table id="contentTable">
<tr>
<th>Name</th><th>Age</th>
</tr> 
</table>
<div id="loading"></div>
 
<script type="text/javascript">
function callAjax() {
 
	document.getElementById('loading').innerHTML = "Loading Data...";
	 
	httpRequest = new XMLHttpRequest();
	 
	if (!httpRequest) {
	console.log('Unable to create XMLHTTP instance');
	return false;
	}
	httpRequest.open('POST', 'AjaxHandler');
	httpRequest.responseType = 'json';
	httpRequest.send();
	httpRequest.onreadystatechange = function() {
		if (httpRequest.readyState === XMLHttpRequest.DONE) {
		 
			document.getElementById('loading').innerHTML = "";
			 
			if (httpRequest.status === 200) {
				 
				var array = httpRequest.response;
				for (var i=0; i< array.length; i++) {
				 
				var table = document.getElementById('contentTable');
				var row = table.insertRow(table.rows.length);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var name = document.createTextNode(array[i].name);
				var age = document.createTextNode(array[i].age);
				cell1.appendChild(name);
				cell2.appendChild(age);
			}
			} else {
				console.log('Something went wrong..!!');
			}
		}
	}
}
</script>
</body>
</html>