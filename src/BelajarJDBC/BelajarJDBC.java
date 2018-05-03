package BelajarJDBC;

import BelajarJDBC.dao.DaoNasabah;
import BelajarJDBC.model.Nasabah;
import java.util.List;

public class BelajarJDBC {
    public static void main(String[] args){
        DaoNasabah daoNasabah = new DaoNasabah();
        
        //Insert
        Nasabah nasabah = new Nasabah();
        nasabah.setUser("Narendra");
        nasabah.setPass("lalala123");
        nasabah.setSaldo(1000000);
        daoNasabah.save(nasabah);
        Nasabah result = daoNasabah.findByUser(nasabah);
        System.out.println("Insert");
        System.out.println("user:"+result.getUser()+", pass:"+result.getPass()+", saldo:"+result.getSaldo());
        System.out.println("");
    
        //Update
//        Nasabah updt = daoNasabah.findByUser(nasabah);
//        updt.setUser("Yogha");
//        updt.setPass("nnn123");
//        updt.setSaldo(999999);
//        daoNasabah.update(1, updt);
//        System.out.println("Update");
//        System.out.println("user:"+updt.getUser()+", pass:"+updt.getPass()+", saldo:"+updt.getSaldo());
//        System.out.println("");
        
        //Delete
        Nasabah delet = daoNasabah.findByUser(nasabah);
        daoNasabah.delete(1);
        System.out.println("Delete");
        System.out.println("user:"+result.getUser()+", pass:"+result.getPass()+", saldo:"+result.getSaldo());
        System.out.println("");
    }
}
