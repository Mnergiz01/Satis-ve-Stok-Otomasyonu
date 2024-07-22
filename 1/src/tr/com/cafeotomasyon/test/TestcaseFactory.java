package tr.com.cafeotomasyon.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import tr.com.cafeotomasyon.entity.StokContract;
import tr.com.cafeotomasyonu.FactoryMethod.StokContractFactory;

public class TestcaseFactory {

	 @Test
	    public void testCreateStokContract() {
	        int urunId = 1;
	        int personelId = 1;
	        //Date tarih = new Date();
	        int adet = 10;

	        StokContract stokContract = StokContractFactory.createStokContract(urunId, personelId, adet);

	        assertNotNull(stokContract);
	        assertEquals(urunId, stokContract.getUrunId());
	        assertEquals(personelId, stokContract.getPersonelId());
	        //assertEquals(tarih, stokContract.getTarih());
	        assertEquals(adet, stokContract.getAdet());//karşılaştırma yapar eşitse uyarı döndürmez
	    }

}
/*createStokContract metodunun doğru şekilde çalışıp çalışmadığını test eder
 * stokcontract nesnesinin oluşturulup oluşturulmadığını kontrol eder*/
