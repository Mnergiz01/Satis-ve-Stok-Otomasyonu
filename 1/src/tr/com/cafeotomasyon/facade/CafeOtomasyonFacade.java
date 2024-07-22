package tr.com.cafeotomasyon.facade;

import java.util.Date;
import java.util.List;

import tr.com.cafeotomasyon.complex.entity.StokContractComplex;
import tr.com.cafeotomasyon.entity.SatisContract;
import tr.com.cafeotomasyon.entity.StokContract;
import tr.com.cafeotomasyon.entity.UrunlerContract;
import tr.com.cafeotomasyon.templatemethod.UrunEklemeTemplate;
import tr.com.cafeotomasyon.templatemethod.VeritabaniUrunEkleme;
import tr.com.cafeotomasyonu.dal.SatisDAL;
import tr.com.cafeotomasyonu.dal.StokDAL;
import tr.com.cafeotomasyonu.dal.UrunlerDAL;

public class CafeOtomasyonFacade {

	    private StokDAL stokDAL;
	    private UrunlerDAL urunlerDAL;
	    private SatisDAL satisDAL;
	    private UrunEklemeTemplate urunEklemeTemplate;

	    public CafeOtomasyonFacade() {
	        stokDAL = new StokDAL();
	        urunlerDAL = new UrunlerDAL();
	        satisDAL = new SatisDAL();
	        urunEklemeTemplate = new VeritabaniUrunEkleme();
	        
	    }

	    public void stokEkle(int urunId, int personelId, int adet) {
	        StokContract stokContract = new StokContract();
	        stokContract.setUrunId(urunId);
	        stokContract.setPersonelId(personelId);
	        //stokContract.setTarih(tarih);
	        stokContract.setAdet(adet);
	        stokDAL.Insert(stokContract);
	    }

	    public List<StokContractComplex> tumStoklariGetir() {
	        return stokDAL.getAllStok();
	    }

	    public void satisYap(int urunId, int personelId, int musteriId, int adet) {
	        SatisContract satisContract = new SatisContract();
	        satisContract.setUrunId(urunId);
	        satisContract.setPersonelId(personelId);
	        satisContract.setMusteriId(musteriId);
	        //satisContract.setTarih(tarih);
	        satisContract.setAdet(adet);
	        satisDAL.Insert(satisContract);
	    }
	    public List<UrunlerContract> urunleriGetir() {
	        return urunlerDAL.GetAll();
	    }
	    public void urunEkle(UrunlerContract urun) {
	        urunEklemeTemplate.urunEkle(urun);
	    }
	    

}
