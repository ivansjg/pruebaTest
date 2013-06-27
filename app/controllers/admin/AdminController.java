package controllers.admin;

import play.*;
import play.mvc.*;

import play.i18n.Messages;

import play.data.validation.Validation;
import play.data.validation.Error;

import java.util.List;
import java.util.LinkedList;

public class AdminController extends Controller {

	public static void index(){
		render();
	}

    public static void showSignUpPoliticianElectedForm() {
        
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

        LinkedList<Item> politicalSystems = new LinkedList();
        Item politicalSystem = new Item((long)1, "EU");
        politicalSystems.add(politicalSystem);

        LinkedList<Item> institutions = new LinkedList();
        Item intitution = new Item((long)1, "EuropeanCommission");
        institutions.add(intitution);
        intitution = new Item((long)2, "EuropeanParliament");
        institutions.add(intitution);

        LinkedList<Item> positions = new LinkedList();
        Item position = new Item((long)1, "President");
        positions.add(position);
        position = new Item((long)2, "VicePresident");
        positions.add(position);
        position = new Item((long)3, "Parliamentary");
        positions.add(position);
        position = new Item((long)4, "Commissioner");
        positions.add(position);
        position = new Item((long)5, "MemberCommission");
        positions.add(position);
        position = new Item((long)6, "MemberParliament");
        positions.add(position);

        LinkedList<Item> politicalParties = new LinkedList();
        Item politicalParty = new Item((long)1, "PP");
        politicalParties.add(politicalParty);
        politicalParty = new Item((long)2, "PS");
        politicalParties.add(politicalParty);

        LinkedList<Item> politicalGroups = new LinkedList();
        Item politicalGroup = new Item((long)1, "GroupPP");
        politicalGroups.add(politicalGroup);
        politicalGroup = new Item((long)2, "GroupPS");
        politicalGroups.add(politicalGroup);       

        renderArgs.put("languages", languages);
        renderArgs.put("nationalities", nationalities);
        renderArgs.put("politicalSystems",politicalSystems );
        renderArgs.put("institutions", institutions );
        renderArgs.put("positions", positions);
        renderArgs.put("politicalParties", politicalParties);
        renderArgs.put("politicalGroups", politicalGroups);
        render();
    }

    public static void submitSignUpPoliticianElectedForm(String name, String alias, String email, String birthDate, 
        Integer sex) {            
        
        validation.required(name);
        validation.required(alias);
        validation.required(email);
        validation.required(birthDate);
        validation.required(sex);
        
        if(validation.hasErrors()) {
            params.flash(); 
            validation.keep();
            showSignUpPoliticianElectedForm();            
        }else{
            index();
        }
    }

    public static void showSignUpPoliticianNonElectedForm() {
        render();
    }

    public static void submitSignUpPoliticianNonElectedForm(String name, String alias, String email) {
        
        //Errors forced
        //validation.required(name).message("javikey");
        validation.required(name);
        validation.required(alias);
        validation.required(email);
        validation.email(email);
               
        if(validation.hasErrors()) {
            params.flash(); 
            validation.keep();
            showSignUpPoliticianNonElectedForm();
        }else{
            index();
        }
    }

}