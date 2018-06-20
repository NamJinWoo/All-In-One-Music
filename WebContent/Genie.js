var genieResult;
$(document).ready(function() {
	$.ajax({
		type : "GET",
		url : "./Crawling.jsp",
		data : {
			type : "Genie"
		},
		dataType : "JSON",
		success : function(result) {
			genieResult = JSON.parse(result);
			genieSort(genieResult);
			console.log(genieResult);
		}
	});
});

function genieSort(genieResult) {
	$(".loading").attr("style","display: none");
	var sortResult = [];
	for (var i = 0; i < genieResult.length; i++) {
		sortResult[i] = genieResult[i].split("\t");
	}
	console.log(sortResult);
	for (var i = 0; i < 50/*38*/; i++) {
		var rankTr = document.createElement("tr");
		var rankTd01 = document.createElement("td");
		var rankTd02 = document.createElement("td");
		var rankTd03 = document.createElement("td");
		var rankTd04 = document.createElement("td");
		var rankTd05 = document.createElement("td");
		var img = document.createElement("img");
		img.src = sortResult[i * 3][1];
		$("#t tbody").append(rankTr);
		rankTr.append(rankTd01);
		rankTr.append(rankTd02);
		rankTd02.append(img);
		rankTr.append(rankTd03);
		rankTr.append(rankTd04);
		rankTr.append(rankTd05);
		$("rankTr").attr("id", "songinfo");
		rankTd01.innerHTML = sortResult[i * 3][0];
		rankTd03.innerHTML = sortResult[i * 3][2];
		rankTd04.innerHTML = sortResult[i * 3][3];
		rankTd05.innerHTML = sortResult[i * 3][4];
	}
//	for (var i = 116; i < sortResult.length; i += 3) {
//		var rankTr = document.createElement("tr");
//		var rankTd01 = document.createElement("td");
//		var rankTd02 = document.createElement("td");
//		var rankTd03 = document.createElement("td");
//		var rankTd04 = document.createElement("td");
//		var rankTd05 = document.createElement("td");
//		var img = document.createElement("img");
//		img.src = sortResult[i][1];
//		$("#t tbody").append(rankTr);
//		rankTr.append(rankTd01);
//		rankTr.append(rankTd02);
//		rankTd02.append(img);
//		rankTr.append(rankTd03);
//		rankTr.append(rankTd04);
//		rankTr.append(rankTd05);
//		$("rankTr").attr("id", "songinfo");
//		rankTd01.innerHTML = sortResult[i][0];
//		rankTd03.innerHTML = sortResult[i][2];
//		rankTd04.innerHTML = sortResult[i][3];
//		rankTd05.innerHTML = sortResult[i][4];
//	}
}