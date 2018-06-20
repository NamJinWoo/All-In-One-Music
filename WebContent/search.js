var bugsResult;

function search() {
	var title = $('#searchItem').val();
		$.ajax({
			type : "GET",
			url : "./Crawling.jsp",
			data : {
				type : "Search",
				"name" : title,
			},
			success : function(result) {
				console.log(result);
			}
		});
}
