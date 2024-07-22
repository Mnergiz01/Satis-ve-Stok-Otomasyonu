package tr.com.cafeotomasyonu.FactoryMethod;

import java.sql.Date;

import tr.com.cafeotomasyon.entity.StokContract;

public class StokContractFactory {

    public static StokContract createStokContract(int urunId, int personelId, int adet) {
        return new StokContract(0,urunId, personelId, adet); // id = 0 olarak başlatıyoruz
        
        
    }
}
