package BelajarJDBC.model;

import java.io.Serializable;

public class Nasabah implements Serializable{
    
    public Nasabah(String user, String pass){
        this.user = user;
        this.pass = pass;
        this.saldo = 0;
    }
    private long id;
    private String user;
    private String pass;
    private long saldo;
    
    public Nasabah(){
        this.user = "";
        this.pass = "";
        this.saldo = 0;
    }
    
    public Nasabah(String user, String pass, long saldo){
        this.user = user;
        this.pass = pass;
        this.saldo = saldo;
    }
    
    public Nasabah(long id, String user, String pass, long saldo){
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.saldo = saldo;
    }
    
    public long getId(){
        return id;
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    public String getUser(){
        return user;
    }
    
    public void setUser(String user){
        this.user = user;
    }
    
    public String getPass(){
        return pass;
    }
    
    public void setPass(String pass){
        this.pass = pass;
    }
    
    public long getSaldo(){
        return saldo;
    }
    
    public void setSaldo(long saldo){
        this.saldo = saldo;
    }
}
