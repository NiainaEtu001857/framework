import java.io.File;

public class Fonction {
    public void ScanPackage(String path,File file){
        try { 
                File[] listOfFiles = file.listFiles();

                for (File files : listOfFiles) {
                    if (files.isFile() && files.getName().endsWith(".class")) {
                        listClass.add(files.getName().split(".class")[0]);
                        listpath.add(files.getPath().replace("\\", ".").split(".class")[0]);
                    } else if (files.isDirectory()) {
                        if(files.getName().equals("annotation")==false)
                            ScanPackage(path , files);
                    }
                }
        }catch (Exception e) {
            throw e;
        }
    }
}
