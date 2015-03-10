/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function deleteImage(id) {
	if(confirm("Voulez vous supprimer cette image?")) {
		 $.ajax({
	            type: 'POST',
	            url: "ImageDeleteServlet",
	            data: { imageId : id },
	            success:function() {
	            	window.location.replace("myimages.jsp");
	            },
	            error: function (data){	         
	            	alert("Erreur lors de la suppresion de l'image.")
	            }
	       });
		 
	}
		
}

function setPublic(id,boolPublic) {
		 $.ajax({
	            type: 'POST',
	            url: "ChangeVisibilityServlet",
	            data: { imageId : id, visibility:boolPublic },
	            success:function() {
	            	if(boolPublic) {
		            	$("#public-"+id).html("<span class=\"glyphicon glyphicon-ok\"></span>Publique");
		            	$("#private-"+id).html("Privé");
	            	}else {
	            		$("#public-"+id).html("Publique");
		            	$("#private-"+id).html("<span class=\"glyphicon glyphicon-ok\"></span>Privé");
	            	}
	            },
	            error: function (data){	         
	            	//alert("Erreur lors de la suppresion de l'image.")
	            }
	       }); 		
}

$(document).ready(function() {
	$("#errorsZone").hide();
	$("#successZone").hide();
	
	$("#frameUpload").load(function() {
		var responseText = $('#frameUpload').contents().find('#answer').html();
		var idImg = $('#frameUpload').contents().find('#idImg').html();
		var upTime = $('#frameUpload').contents().find('#upTime').html();
		var username = $('#frameUpload').contents().find('#username').html();
		if(!responseText) {
			return;
		}
		else if (responseText =="ok") {
			$("#errorsZone").hide();
			$("#successZone").show(500);
			$("#successZone").html("<span class=\"glyphicon glyphicon-ok-circle\" aria-hidden=\"true\"></span>Image envoyée avec succès.<br/>");
			window.location.replace("myimages.jsp");
			
		}
		else {
			$("#successZone").hide();
			$("#errorsZone").show(500);
			$("#errorsZone").html("<span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span>Erreur lors de l'envoi de l'image.<br/>");		}
		
	});

});
