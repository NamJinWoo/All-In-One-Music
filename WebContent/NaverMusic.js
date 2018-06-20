var NMResult;
$(document).ready(function() {
	$.ajax({
		type : "GET",
		url : "./Crawling.jsp",
		data : {
			type : "NM"
		},
		dataType : "JSON",
		success : function(result) {
			NMResult = JSON.parse(result);
			NMSort(NMResult);
			console.log(JSON.parse(result));
		}
	});
});


function NMSort(NMResult) {
	$(".loading").attr("style","display: none");
	var sortResult = [];

	for (var i = 0; i < NMResult.length; i++) {
		sortResult[i] = NMResult[i].split("\t");
	}
	console.log(sortResult);
	for (var i = 1; i < sortResult.length; i++) {
		var rankTr = document.createElement("tr");
		var rankTd01 = document.createElement("td");
		var rankTd02 = document.createElement("td");
		var rankTd03 = document.createElement("td");
		var rankTd04 = document.createElement("td");
		var rankTd05 = document.createElement("td");
		var img = document.createElement("img");
		img.src=sortResult[i][1];
		$("#t tbody").append(rankTr);
		rankTr.append(rankTd01);
		rankTr.append(rankTd02);
		rankTd02.append(img);
		rankTr.append(rankTd03);
		rankTr.append(rankTd04);
		rankTr.append(rankTd05);
		$("rankTr").attr("id", "songinfo");
		rankTd01.innerHTML = sortResult[i][0];
		rankTd03.innerHTML = sortResult[i][2];
		rankTd04.innerHTML = sortResult[i][3];
		
		//rankTd05.innerHTML = NMResult[i][4];
	}
}