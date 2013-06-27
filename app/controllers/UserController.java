package controllers;

import play.*;
import play.mvc.*;
import play.i18n.Messages;
import play.i18n.Lang;

public class UserController extends Controller{

	public static void index(){
		render();
	}
    
	public static void showLoginForm(){
		render();
	}

	public static void submitLoginForm(String email, String password){
		validation.required(email);
        validation.email(email);
        validation.required(password);
        
        if(validation.hasErrors()) {
            params.flash(); 
            validation.keep();
            showLoginForm();
        }else{
            Application.index();
        }
	}

    public static void showRecoverAccountForm(){
        render();
    }

    public static void submitRecoverAccountForm(String email){
        validation.required(email);
        validation.email(email);         

        if(validation.hasErrors()) {
            params.flash(); 
            validation.keep();
            showRecoverAccountForm();
        }else{
            Application.index();
        }
    }

    public static void showResetPasswordForm(String token) {
        render(token);
    }

    public static void submitResetPasswordForm(String token, String newPassword) {
        
        validation.required(token);
        validation.required(newPassword);
        
        if(validation.hasErrors()) {
            params.flash(); 
            validation.keep();
            showResetPasswordForm(token);
        }else{
            Application.index();
        }
    }

    public static void logout(){
        Application.index();
    }

}