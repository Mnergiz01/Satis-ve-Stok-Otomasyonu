package tr.com.cafeotomasyon.templatemethod;

import tr.com.cafeotomasyon.entity.UrunlerContract;
import tr.com.cafeotomasyonu.dal.UrunlerDAL;

public class VeritabaniUrunEkleme extends UrunEklemeTemplate {

	 @Override
	    protected void validateUrun(UrunlerContract urun) {
	        if (urun.getAdi() == null || urun.getAdi().isEmpty()) {
	            throw new IllegalArgumentException("Ürün adı boş olamaz.");
	        }
	        if (urun.getFiyat() <= 0) {
	            throw new IllegalArgumentException("Ürün fiyatı sıfırdan büyük olmalıdır.");
	        }
	    }

	    @Override
	    protected void insertUrun(UrunlerContract urun) {
	        UrunlerDAL urunlerDAL = new UrunlerDAL();
	        urunlerDAL.Insert(urun);
	    }

	    @Override
	    protected void afterInsert(UrunlerContract urun) {
	        System.out.println("Ürün başarıyla eklendi: " + urun.getAdi());
	    }

}


