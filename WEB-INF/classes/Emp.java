package etu1857.model;
import java.lang.annotation.*;
import java.util.HashMap;

import annotation.*;
import traitment.ModelView;

@DBTable(name = "Emp")
public class Emp {

    @DBField(name = "type")
    private Integer id;

    @DBField(name = "Koto")
    private String name;
    
    @DBField(name = "prenom")
    private String Prenom;

    @DBField(name = "age")
    private String age;

    @DBMethod(name = "calcul-test")
    public int CalculTest(){
        System.out.println("calcul-test");
        return 1+1;
    }

    @DBMethod(name = "find-all-emp")   
    public ModelView findAll( @DBParameter(name = "valiny") String valiny , @DBParameter(name = "id") int id){
        System.out.println("find-all");
        ModelView model = new ModelView();
        HashMap<String , Object> ObjectRetour = new HashMap<String , Object>();
        int a= 15;
        String str = "Tonga";
        ObjectRetour.put("int", a);
        ObjectRetour.put("String", str);
        model.setObjectAEnvoyer(ObjectRetour);
        System.out.println(str+" Valiny :"+valiny + " int : " + id); 
        return model;
    }

    @DBMethod(name = "find-where-emp")
    public ModelView findWhere(){
        ModelView m = null;
        System.out.println("Find-Where-emp");
        return m;
    }

     @DBMethod(name = "find-emp")
    public ModelView find(@DBParameter(name = "valiny") String valiny){
        ModelView m = null;
        System.out.println("Find-emp : " + valiny);
        return m;
    }

}