/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function (){
	
	
	
	$("#submitLogin").click(function() {
		var name = document.getElementById("usernameLogin").value;
		var pass = document.getElementById("passwordLogin").value;
        
        $.ajax({
        type: 'POST',
        url: contextPath+"/ConnectionUserServlet",
        data: { username : name, password:pass},
        success: function (data) {
        	window.location.replace("index.jsp");        	
        },
        error: function (data){
        	$("#usernameLogin").tooltip('show');
        }
        
         });
	} );
	
	$("#deconnection").click(function() {
		$.ajax({
	        type: 'POST',
	        url: contextPath+"/DeconnectionServlet",
	        success: function (data) {
	        	window.location.replace("index.jsp");        	
	        },
	        error: function (data){
	        	$("#usernameLogin").tooltip('show');
	        }
		});
	        
	   });
	

    
    
    
});

