/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function (){
	
	$("#errorsZone").hide();
	$("#successZone").hide();
	var errors = ["Les mots de passes ne correspondent pas.","Le nom d'utilisateur est déjà prit.","Email invalide.","Erreur lors de l'inscription, vérifiez les champs."];
	var errorsActive=[false,false,false,false];
	
	
	var checkPassword = function() {
		var password = document.getElementById("password").value;
		var password2 = document.getElementById("password2").value;
		if(password!=password2) {
			errorsActive[0]=true;
			$( "div[name='passwordGroup']" ).removeClass("has-success" );
			$( "div[name='passwordGroup']" ).addClass("has-error" );
		}
		else {
			errorsActive[0]=false;
			$( "div[name='passwordGroup']" ).addClass("has-success" );
			$( "div[name='passwordGroup']" ).removeClass("has-error" );
		}
	}
	
	var checkEmail = function() {
		var email = document.getElementById("email").value;
		if(email.match("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$")) {
			errorsActive[2]=false;
			$( "#emailGroup" ).addClass("has-success" );
			$( "#emailGroup" ).removeClass("has-error" );
		}
		else {
			$( "#emailGroup" ).removeClass("has-success" );
			$( "#emailGroup" ).addClass("has-error" );
			errorsActive[2]=true;
		}
	}
	
	var updateErrorField=function() {
		var html = "";
		for(i=0;i<errors.length;i++) {
			if(errorsActive[i])
				html += "<span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span>"+errors[i]+"<br/>";
		}
		if(html=="")
			$("#errorsZone").hide(500);
		else
			$("#errorsZone").show(500);
		$("#errorsZone").html(html);
		
	}
	
	$("#username").keyup(function (){
        var name = document.getElementById("username").value;
        
            $.ajax({
            type: 'POST',
            url: "UserServlet",
            data: { username : name },
            success: function (data) {
            	errorsActive[1]=false;
            	 $( "#usernameGroup" ).addClass("has-success" );
            	 $( "#usernameGroup" ).removeClass("has-error" );
            	 updateErrorField();
            },
            error: function (data){
            	errorsActive[1]=true;
            	$( "#usernameGroup" ).removeClass("has-success" );
            	$( "#usernameGroup" ).addClass("has-error" );
            	updateErrorField();
            	
            }
            
             });
        
    });
	
	$("#submitRegister").click(function() {
		var name = document.getElementById("username").value;
		var pass1 = document.getElementById("password").value;
		var pass2 = document.getElementById("password2").value;
		var email = document.getElementById("email").value;
        
        $.ajax({
        type: 'POST',
        url: "UserRegistrationServlet",
        data: { username : name, password1:pass1,password2:pass2,email:email },
        success: function (data) {
        	errorsActive[3]=false;
        	updateErrorField();
        	$("#successZone").show(500);
        	$("#successZone").html("<span class=\"glyphicon glyphicon-ok-circle\" aria-hidden=\"true\"></span>Inscription effectuée, vous pouvez vous connecter en utilisant le formulaire en haut de page.<br/>");
        	
        },
        error: function (data){
        	errorsActive[3]=true;
        	updateErrorField();
        	
        }
        
         });
	} );
	
	$("#password").keyup(checkPassword);
	$("#password2").keyup(checkPassword);	
	$("#email").keyup(checkEmail);	
	
	$("#registerForm input").keyup(updateErrorField);	
    
    
    
});

