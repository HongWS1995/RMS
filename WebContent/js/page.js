function bindLink() {
	$("a#firstPage").unbind();
	$("a#prevPage").unbind();
	$("a#nextPage").unbind();
	$("a#lastPage").unbind();
	var currentPage;
	var totalPage;
	$("a#firstPage").click(function() {
		$("input#currentPage").val(1);
		queryUserList(1);
	});
	$("a#prevPage").click(function() {
		currentPage = $("input#currentPage").val();
		if (currentPage > 1) {
			currentPage = parseInt(currentPage) - 1;
			$("input#currentPage").val(currentPage);
			queryUserList(currentPage);
		}

	});
	$("a#nextPage").click(function() {
		currentPage = parseInt($("input#currentPage").val());
		totalPage = parseInt($("input#totalPage").val());
		if (currentPage < totalPage) {
			currentPage = parseInt(currentPage) + 1
			$("input#currentPage").val(currentPage);
			queryUserList(currentPage);
		}

	});
	$("a#lastPage").click(function() {
		totalPage = $("input#totalPage").val();
		$("input#currentPage").val(totalPage);
		queryUserList(totalPage);
	});

}