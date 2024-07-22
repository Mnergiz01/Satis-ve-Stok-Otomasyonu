package tr.com.cafeotomasyon.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tr.com.cafeotomasyon.entity.SatisContract;
import tr.com.cafeotomasyon.facade.CafeOtomasyonFacade;
import tr.com.cafeotomasyonu.dal.SatisDAL;
import tr.com.cafeotomasyonu.dal.StokDAL;
import tr.com.cafeotomasyonu.dal.UrunlerDAL;

public class CafeOtomasyonFacadeTest {
    private CafeOtomasyonFacade facade;

    @Before
    public void setUp() {
        facade = new CafeOtomasyonFacade();
    }

    @Test
    public void testSatisYap() {
    	facade = new CafeOtomasyonFacade();
        int urunId = 1; // Test verileri, veritabanındaki geçerli bir ürün ID'sini temsil eder
        int personelId = 1; // Test verileri, veritabanındaki geçerli bir personel ID'sini temsil eder
        int musteriId = 1; // Test verileri, veritabanındaki geçerli bir müşteri ID'sini temsil eder
        int adet = 5;

        List<SatisContract> satisListBefore = new SatisDAL().GetAll();

        facade.satisYap(urunId, personelId, musteriId, adet);

        List<SatisContract> satisListAfter = new SatisDAL().GetAll();
        assertEquals(satisListBefore.size() + 1, satisListAfter.size());

        boolean satisYapildi = satisListAfter.stream()
            .anyMatch(satis -> satis.getUrunId() == urunId && satis.getPersonelId() == personelId && satis.getMusteriId() == musteriId && satis.getAdet() == adet);
        assertTrue(satisYapildi);
    }

}
