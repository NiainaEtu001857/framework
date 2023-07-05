package model;
import java.lang.annotation.*;
import annotation.*;

@DBTable(name = "depts")
public class Dept {


    @DBField(name = "id")
    private Integer id;

    @DBField(name = "name")
    private String name;

    @DBField(name = "emplacement")
    private String emplacement;

    @DBField(name = "lieu")
    private String lieu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @DBMethod(name = "calcul-test")
    public int CalculTest(){
        return 1+1;
    }

    @DBMethod(name = "find-all")
    public int findAll(){
        return 1+1;
    }

    @DBMethod(name = "find-where")
    public int findWhere(){
        return 1+1;
    }

}