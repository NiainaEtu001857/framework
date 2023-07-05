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

import javax.management.modelmbean.ModelMBean;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Line;
import javax.sql.rowset.BaseRowSet;
import javax.swing.ListModel;

import org.xml.sax.SAXException;

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

    private List<String> listpath = new ArrayList<String>();
    private List<String> listClass= new ArrayList<String>();
    private HashMap<String,Class> listClassSinglton =new HashMap<>();


//------- PRENDRE LES CHEMEINS ET LES CLASSE DANS LE MODELS------------ 


    public HashMap<String, Class> getListClassSinglton() {
        return listClassSinglton;
    }

    public void setListClassSinglton(String keys , Class<?> classe) {
        this.listClassSinglton.put(keys, classe);
    }

    public void ScanPackage(String path,File file){
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

//------ CREER UN INSTANCE DES CLASSES OBTENUES ET VERIFICATION DE L'ANNOTATION , Insertion HashMap --------

    public HashMap<String,Mapping> getInstancesWithClass() {
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
                            // System.out.println("Methode "+method.getName()+" classe "+instance.getClass().getName());
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

    public Method InvokeFonctionWithParameter(HashMap<String,Mapping> list, String url , Object[] argument){
        Method model = null;
        try {
            if (list.containsKey(url)) {
                Mapping map = list.get(url);
                Class<?> myClass = Class.forName(map.getClasse());
                Object instance = myClass.newInstance();
                Method[] methods = instance.getClass().getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(DBMethod.class)) {
                       DBMethod annotation = method.getAnnotation(DBMethod.class);
                       String annotationValue = annotation.name();
                        if (annotationValue.equals(url)) {
                            model = method;
                        }
                    }
                }
            } else {
               throw new Exception("Le fonction que vous essaie d' appeller n' existe pas dans les classes avec annotation !");
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return model;
    }

    public static String[] getDBParameterAnnotations(Method method) {
        String[] annotationNames  = null;
        if(method.isAnnotationPresent(DBParameter.class)){
            DBParameter[] annotations = method.getAnnotationsByType(DBParameter.class);
            annotationNames = new String[annotations.length];

            for (int i = 0; i < annotations.length; i++) {
                annotationNames[i] = annotations[i].name();
            }
        }
        return annotationNames;
    }

    //public static HashMap<String , Class> get_Siglton(){}

    // public static void main(String[] args) throws Exception {
    //     // getContenuSousDossier("C:/ApacheSoftwareFoundation/Tomcat10.0/webapps", "FrameWorkNiaina");
    //     File f = new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\ConceptionFrameWork\\");
    //     ScanPackage("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\ConceptionFrameWork\\" , f);
    //     HashMap<String, Mapping> list =  getInstancesWithClass();
    //     String url = "find-emp";
    //     String val = "find-all-emp Annotation";
    //     int id = 14;
    //     Object[] obj = {"Tonga"};
    
    //     ModelView valiny = InvokeFonctionWithParameter(list, url,obj);
    //     System.out.println(valiny);
    // }
}
