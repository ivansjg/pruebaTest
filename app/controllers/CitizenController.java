package controllers;

import play.*;
import play.mvc.*;
import play.i18n.Messages;
import play.i18n.Lang;

import play.data.validation.Validation;
import play.data.validation.Error;

import java.util.List;
import java.util.LinkedList;

public class CitizenController extends Controller{

    public static void showSignUpForm(){
        
        class Item{
            Long id;
            String name;

            Item(Long _id, String _name){
                id = _id;
                name = _name;
            }
        }

        LinkedList<Item> languages = new LinkedList();
        Item l = new Item((long)1, "EN");
        languages.add(l);
        l = new Item((long)2, "ES");
        languages.add(l);
        l = new Item((long)3, "FR");
        languages.add(l);

        LinkedList<Item> nationalities = new LinkedList();
        Item nationality = new Item((long)1, "UK");
        nationalities.add(nationality);
        nationality = new Item((long)2, "Spain");
        nationalities.add(nationality);
        nationality = new Item((long)3, "France");
        nationalities.add(nationality);

        renderArgs.put("languages", languages);
        renderArgs.put("nationalities", nationalities);
        
        render();

    }

    public static void submitSignUpForm(String name, String alias, String email, String password, String birthDate, String sex, Long nationality, Long language, String photo) {        
        
        validation.required(name);
        validation.required(alias);
        validation.required(email);
        validation.email(email);
        validation.required(password);
        validation.required(birthDate);
        validation.required(sex);
        validation.required(nationality);        
        validation.required(language);

        if(validation.hasErrors()) {
            params.flash(); 
            validation.keep();
            showSignUpForm();
        }else{
            Application.index();
        }
    }


}