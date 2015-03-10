var index = 3
$(window).scroll(function() {
	if ($(document).height() <= $(window).scrollTop() + $(window).height() + 100) {
		$.ajax({
			type : 'POST',
			url : contextPath + "/LoadMoreProfilImagesServlet",
			data : {
				index : index,
				number : 3,
				username : username
			},
			success : function(data) {
				$("#imgList").append(data);
				index += 3;
			},
			error : function(data) {

			}

		});
	}
});

