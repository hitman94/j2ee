/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function deleteImage(id) {
	alert(id);
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
			$("#imgList").prepend("<div class=\"col-md-12\">"+
									"<img class=\"img-responsive\" src=\"uploadedImg/"+username+"-"+upTime+".jpg\"/>"+
									"<div class=\"btn-group\">"+
									"<button type=\"button\" class=\"btn btn-default dropdown-toggle\""+
									"data-toggle=\"dropdown\" aria-expanded=\"false\">"+
									"Visibilité <span class=\"caret\"></span>"+
								"</button>"+
								"<ul class=\"dropdown-menu\" role=\"menu\">"+
								"	<li><a href=\"#\" onclick=\"setPublic("+idImg+",true)\">Public</a></li>"+
								"	<li><a href=\"#\" onclick=\"setPublic("+idImg+",false)\">Privé</a></li>"+
								"</ul>"+
							"</div>"+
							"<div class=\"btn-group\">"+
							"	<button type=\"button\" class=\"btn btn-default dropdown-toggle\""+
							"		data-toggle=\"dropdown\" aria-expanded=\"true\""+
							"		id=\"dropdownAction\">"+
							"		Actions <span class=\"caret\"></span>"+
							"	</button>"+
							"	<ul class=\"dropdown-menu\" role=\"menu\""+
							"		aria-labelledby=\"dropdownAction\">"+
							"		<li><a href=\"#\" onclick=\"deleteImage("+idImg+")\">Supprimer</a></li>"+
							"	</ul>"+
							"</div>"+
					"	</div>");
			
		}
		else {
			$("#successZone").hide();
			$("#errorsZone").show(500);
			$("#errorsZone").html("<span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span>Erreur lors de l'envoi de l'image.<br/>");		}
		
	});

});
