import java.io.File;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Fonction fonct = new Fonction();
         File f = new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\ConceptionFrameWork\\");
        fonct.ScanPackage("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\ConceptionFrameWork\\" , f);
        // HashMap<String, Mapping> list =  getInstancesWithClass();
    }
}
