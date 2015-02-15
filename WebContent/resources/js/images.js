/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {

	$("#frameUpload").load(function() {
		var responseText = $('#frameUpload').contents().find('body').html();
		if(!responseText) {
			return;
		}
		else if (responseText =="ok") {
			alert("bien upload");
		}
		else {
			alert("probleme upload");
		}
		
	});

});
