 var index=0
$(window).scroll(function () {
        if ($(document).height() <= $(window).scrollTop() + $(window).height()) {
        	index+=9;
        	loadMoreImg(index);
        	
        }
    });
 
 function loadMoreImg(index) {
	var size = document.getElementById("imgSize").value;
	var name = document.getElementById("imgName").value;
	 $.ajax({
         type: 'POST',
         url: "LoadMoreImagesServlet",
         data: { index : index,number: 9, size:size, name:name},
         success: function (data) {
        	 if(index!=0)
         	 $( "#imgList" ).append(data);
        	 else
        		 $( "#imgList" ).html(data);
         },
         error: function (data){
         	
         }
	 });
 }
 
 $(document).ready(function() {
	 	loadMoreImg(index);
		$("#imgSize").change(function() {

			index=0;
			loadMoreImg(index);
		});
		$("#imgName").keyup(function() {
			index=0;
			loadMoreImg(index);
		});
});
 
