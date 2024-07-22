package tr.com.cafeotomasyon.complex.entity;

import java.sql.Date;

public class StokContractComplex {
	private int id;
	private String urunAdi;
	private String personelAdi;
	private Date tarih;
	private int adet;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrunAdi() {
		return urunAdi;
	}
	public void setUrunAdi(String urunAdi) {
		this.urunAdi = urunAdi;
	}
	public String getPersonelAdi() {
		return personelAdi;
	}
	public void setPersonelAdi(String personelAdi) {
		this.personelAdi = personelAdi;
	}
	public Date getTarih() {
		return tarih;
	}
	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}
	public int getAdet() {
		return adet;
	}
	public void setAdet(int adet) {
		this.adet = adet;
	}
	public Object[] getVeriler() {
		Object[] veriler = {id,urunAdi,personelAdi,adet,tarih};
		return veriler;
		
	}
	@Override
	public String toString() {//combobox da listelemek için 
		
		return personelAdi+" "+urunAdi;
	}
	
}
