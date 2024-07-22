package tr.com.cafeotomasyon.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tr.com.cafeotomasyon.entity.KategoriContract;
import tr.com.cafeotomasyon.entity.UrunlerContract;
import tr.com.cafeotomasyon.templatemethod.UrunEklemeTemplate;
import tr.com.cafeotomasyon.templatemethod.VeritabaniUrunEkleme;
import tr.com.cafeotomasyonu.dal.KategoriDAL;
import tr.com.cafeotomasyonu.dal.UrunlerDAL;

public class UrunEkleTemplateTest {

	private UrunlerDAL urunlerDAL;
	private KategoriDAL kategoriDAL;

	@Before
	public void setUp() {
		urunlerDAL = new UrunlerDAL();
		kategoriDAL = new KategoriDAL();
	}

	@After
	public void tearDown() {
		// Eklenen test ürünlerini veritabanından sil
		List<UrunlerContract> urunler = urunlerDAL.GetAll();
		for (UrunlerContract urun : urunler) {
			if (urun.getAdi().startsWith("Test Ürünü")) {
				urunlerDAL.Delete(urun);
			}
		}
	}

	@Test
	
	public void testUrunEkle_InvalidUrunAdi() {
		
		String urunAdi = "asd";
		int kategoriId = 1;
		float fiyat = 55;
		
		
	      // Kategorinin varlığını kontrol et
        List<KategoriContract> kategoriler = kategoriDAL.GetAll();
        boolean kategoriVar = kategoriler.stream().anyMatch(k -> k.getId() == kategoriId);
        assertTrue("Kategori mevcut değil", kategoriVar);

	

		// Ürün ekleme işlemi
		UrunEklemeTemplate urunEkleme = new VeritabaniUrunEkleme();
		UrunlerContract urun = new UrunlerContract();
		urun.setAdi(urunAdi);
		urun.setKategoriId(kategoriId);
		urun.setFiyat(fiyat);

		
		try {
			urunEkleme.urunEkle(urun);
			System.out.println("Ürün Ekleme İşlemi Template Method Kullanılarak Yapılıyor");
		} catch (IllegalArgumentException e) {
			assertEquals("Hata mesajı eşleşmiyor", "Ürün adı boş olamaz.", e.getMessage());
			System.out.println("Ürün Ekleme İşlemi tamamlanmadı ürün adı boş olamaz");
		}
	}

}
