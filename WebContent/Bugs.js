var bugsResult;
$(document).ready(function() {
	$.ajax({
		type : "GET",
		url : "./Crawling.jsp",
		data : {
			type : "Bugs"
		},
		dataType : "JSON",
		success : function(result) {
			bugsResult = JSON.parse(result);
			bugsSort(bugsResult);
			console.log(JSON.parse(result));
		}
	});
});


function bugsSort(bugsResult) {
	$(".loading").attr("style","display: none");
	var sortResult = [];

	for (var i = 0; i < bugsResult.length; i++) {
		sortResult[i] = bugsResult[i].split("\t");
	}
	for (var i = 0; i < 100; i++) {
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
		rankTd05.innerHTML = sortResult[i][4];
	}
}