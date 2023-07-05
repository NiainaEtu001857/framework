/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitment;

import java.io.File;
import java.io.ObjectInputStream.GetField;
import java.lang.ProcessBuilder.Redirect.Type;
import java.lang.annotation.Annotation;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.lang.reflect.*;


import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Line;
import javax.sql.rowset.BaseRowSet;
import javax.swing.ListModel;

import annotation.DBField;
import annotation.DBMethod;
import annotation.DBParameter;
import annotation.DBTable;
import annotation.DBTable;
import etu1857.framework.Mapping;
import etu1857.model.*;
/**
 *
 * @author ITU
 */
public class Utilitaire {

    String[] Url;
    List<Method> listeMethode;
    private static List<String> listpath = new ArrayList<String>();
    private static List<String> listClass= new ArrayList<String>();


    public Utilitaire(String url) {
        String[] valiny = url.split("/");
        this.setUrl(valiny);
    }
    public Utilitaire(){}

    public String[] getUrl() {
        return Url;
    }

    public void setUrl(String[] url) {
        Url = url;
    }


//------- PRENDRE LES CHEMEINS ET LES CLASSE DANS LE MODELS------------ 


    public static void ScanPackage(String path,File file){
        try { 
                File[] listOfFiles = file.listFiles();

                for (File files : listOfFiles) {
                    if (files.isFile() && files.getName().endsWith(".class")) {
                        listClass.add(files.getName().split(".class")[0]);
                        listpath.add(files.getPath().replace("\\", ".").split("WEB-INF.classes.")[1].split(".class")[0]);
                        // System.out.println(files.getPath().replace("\\", ".").split("WEB-INF.classes.")[1].split(".class")[0]);
                    } else if (files.isDirectory()) {
                        if(files.getName().equals("annotation")==false)
                            ScanPackage(path , files);
                    }
                }
        }catch (Exception e) {
            throw e;
        }
    }


//------ Prendre les parametres d' un methode ----------------------

// private static HashMap<String , String> get_Parameter_Methode(Method m){
//     HashMap<String , String> parameters = new HashMap<String,String>();
//     Class<Object> classes = m.getReturnType();
//     for (Class class1 : classes) {
//         System.out.println(class1.getName());
//         if(m.isAnnotationPresent(DBParameter.class)){
//             DBParameter dbParameter = (DBParameter) class1.getAnnotation(annotation.DBParameter.class);
//             parameters.put(dbParameter.name(), dbParameter.type());
//         }
//     }
//     return parameters;
// }


// verifier si une fonction est plus annoter par un DBParameter 
    

// verifier une fonction avec ses methode et se argument 

public static void processDBMethod(String url) {
        Method[] methods = clazz.getMethods();
        Object[] objects = null;

        for (Method method : methods) {
            if (method.isAnnotationPresent(DBMethod.class)) {
                int isa = method.getParameterCount();
                if(isa == 0){
                    method.invoke();
                }
                Parameter[] parameters = method.getParameters();
                objects = new Object[parameters.length];
                for (Parameter parameter : parameters) {
                    if (parameter.isAnnotationPresent(DBParameter.class)) {
                        DBParameter dbParameterAnnotation = parameter.getAnnotation(DBParameter.class);
                        String parameterName = dbParameterAnnotation.name();
                        String parameterType = dbParameterAnnotation.type();

                        Object parameterValue = null;
                    }
                }
            }
        }
    }

//------ CREER UN INSTANCE DES CLASSES OBTENUES ET VERIFICATION DE L'ANNOTATION , Insertion HashMap --------


    public static HashMap<String,Mapping> getInstancesWithClass() {
        HashMap<String,Mapping> list = new HashMap<String,Mapping>();
        for (int i = 0; i < listpath.size(); i++) {
            try {
                Class<?> myClass = Class.forName(listpath.get(i));
                Object instance = myClass.newInstance();
                if(instance.getClass().isAnnotationPresent(DBTable.class)){
                    Method[] methods = instance.getClass().getDeclaredMethods(); 
                    for (Method method : methods) {
                        if(method.isAnnotationPresent(DBMethod.class)==true){
                            DBMethod nameMethod =  method.getAnnotation(DBMethod.class);
                            Mapping mapping = new Mapping();
                            HashMap<String , String> parameter = get_Parameter_Methode(method);
                            mapping.setParameter(parameter);
                            mapping.setMethode(method.getName());
                            mapping.setClasse(instance.getClass().getName());
                            list.put(nameMethod.name(), mapping);
                        }
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e.getMessage());
                break;
            }
        }
        return list;
    }

    // invoke la fonction correspandant au url

    public static ModelView get_Fonction_Url(String Url , HashMap<String , Mapping> listFonction){
        ModelView model = new ModelView();
        HashMap<String,Object> list = new HashMap<String,Object>();
        System.out.println("Tongava");
        for (int i = 0; i < listpath.size(); i++) {
            try {
                Class<?> myClass = Class.forName(listpath.get(i));
                Object instance = myClass.newInstance();
                if(instance.getClass().isAnnotationPresent(DBTable.class)){
                    Method[] methods = instance.getClass().getDeclaredMethods(); 
                    for (Method method : methods) {
                        if(method.isAnnotationPresent(DBMethod.class)==true){
                            DBMethod nameMethod =  method.getAnnotation(DBMethod.class);
                            if(nameMethod.name().equals(Url)){
                                Object[] arguments = new Object[]{/* arguments indéterminés */};
                                Object o = method.invoke(instance, arguments);
                                model = (ModelView) o;
                            }  
                        }
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e.getMessage());
                // break;
            }
        }
        return model;
    }


    //Prendre le type dans une List

    public Class<?> getParameterWithString(List<?> list){
        java.lang.reflect.Type listType = list.getClass().getGenericSuperclass();
        Class<?> elementClass = null;
        if (listType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) listType;
        java.lang.reflect.Type[] typeArguments = parameterizedType.getActualTypeArguments();

            if (typeArguments.length > 0) {
            java.lang.reflect.Type elementType = typeArguments[0];
                if (elementType instanceof Class<?>) {
                    elementClass = (Class<?>) elementType;
                }
            }
        }
        return elementClass;
    }



    public static void main(String[] args) throws Exception {
        // getContenuSousDossier("C:/ApacheSoftwareFoundation/Tomcat10.0/webapps", "FrameWorkNiaina");
        File f = new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\ConceptionFrameWork\\");
        ScanPackage("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\ConceptionFrameWork\\" , f);
        HashMap<String, Mapping> list =  getInstancesWithClass();
        Set<String> keys = list.keySet();
        for (String key : keys) {
            Mapping m = list.get(key);
            Set<String> keysParameter = m.getParameter().keySet();
            for (String keyParameter : keysParameter) {
                System.out.println(m.getParameter().get(keyParameter));
            }
            System.out.println();
        }
        //  getMappingWithHashMap(list,"find-all");
        // Mapping mapping = new Mapping();
        // mapping.setClasse("etu1857.model.Emp");
        // mapping.setMethode("find-where");
        // InvokeMethod(mapping);
        // File f = new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\ConceptionFrameWork");
        // ScanPackage("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\ConceptionFrameWork",f);
        // HashMap<String, Mapping> list =  getInstancesWithClass();

        // try{
            // Emp emp = new Emp();
            // ModelView m = get_Fonction_Url("find-all",list);
            // HashMap<String, String> p =get_Parameter_Fonction("find-all-emp",list);


        //     Set<String> keys = m.getObjectAEnvoyer().keySet();

        //     // Parcourir les clés et récupérer les objets correspondants
        //     for (String key : keys) {

        //         Object obj = list.get(key);

        //         Class<?> type = Class.forName("java.lang." + key);
        //         Object convertedValue = type.cast(obj);
                
        //         if (convertedValue instanceof Integer) {
        //             int intValue = (int) convertedValue;
        //             System.out.println("Valeur convertie : " + intValue);
        //             // Utilisez intValue comme une variable de type int
        //         }  else {
        //             System.out.println("La conversion a échoué.");
        //             // Gérez le cas où la conversion a échoué (par exemple, le type n'est pas un entier)
        //         }
        //     }
        // }catch(Exception e){
        //     System.out.println(e);
        // }
    }
    private static java.lang.reflect.Type getTypeFromGenericType(String genericType) throws ClassNotFoundException {
        String className = genericType.replaceAll("<.*>", "");
        return Class.forName(className);
    }

}
