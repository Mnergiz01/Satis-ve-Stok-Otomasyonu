package tr.com.cafeotomasyonu.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.cafeotomasyon.complex.entity.SatisContractComplex;
import tr.com.cafeotomasyon.complex.entity.StokContractComplex;
import tr.com.cafeotomasyon.core.ObjectHelper;
import tr.com.cafeotomasyon.entity.KategoriContract;
import tr.com.cafeotomasyon.entity.SatisContract;
import tr.com.cafeotomasyonu.interfaces.DALinterfaces;

public class SatisDAL extends ObjectHelper implements DALinterfaces<SatisContract> {

	@Override
	public void Insert(SatisContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into satis(UrunId,Musteriid,Tarih,adet,Personelid) values("+entity.getUrunId()+","+entity.getMusteriId()+","+entity.getTarih()+","+entity.getAdet()+","+entity.getPersonelId()+")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public List<SatisContract> GetAll() {
		List<SatisContract> datacontract = new ArrayList<SatisContract>();
		Connection connection = getConnection();
		SatisContract contract;
		try {
			// girdiğimiz verileri veritabanına uazma işlemleri
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("select * from satis");// sql den gelen cümleyi result sete
																					// doldur while ile onu domain
																					// içerisine set edeceğiz
			while (resultset.next()) {
				contract = new SatisContract();
				contract.setId(resultset.getInt("Id"));
				contract.setMusteriId(resultset.getInt("Musteriid"));
				contract.setAdet(resultset.getInt("adet"));
				contract.setPersonelId(resultset.getInt("Personelid"));
				contract.setTarih(null);
				contract.setUrunId(resultset.getInt("UrunId"));

				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	    }
	
	/*	 SELECT satis.Id,musteri.AdiSoyadi,personel.adisoyadi,urunler.Adi,adet,satis.Tarih from satis
	  left join urunler on satis.UrunId = urunler.Id 
	  left join personel on satis.PersonelId = personel.Id
      left join musteri on satis.Musteriid =musteri.Id;*/
	
	public List<SatisContractComplex> GetAllSatis() {
		 List<SatisContractComplex> datacontract = new ArrayList<SatisContractComplex>();
	        Connection connection = getConnection();
	        SatisContractComplex contract;
	        try {
	            // Veritabanından verileri çekmek için kullanılan SQL sorgusu
	            Statement statement = connection.createStatement();
	            
	            ResultSet resultset = statement.executeQuery("	 SELECT satis.Id,musteri.AdiSoyadi,personel.adisoyadi,urunler.Adi,adet,satis.Tarih,urunler.fiyat from satis\r\n"
	            		+ "	  left join urunler on satis.UrunId = urunler.Id \r\n"
	            		+ "	  left join personel on satis.PersonelId = personel.Id\r\n"
	            		+ "      left join musteri on satis.Musteriid =musteri.Id;");
	            
	            // Veritabanından gelen verileri SatisContractComplex nesnesine dönüştürme
	            while (resultset.next()) {
	                contract = new SatisContractComplex();
	                contract.setId(resultset.getInt("Id"));
	                contract.setMusteriAdi(resultset.getString("musteri.Adisoyadi"));
	                contract.setPersonelAdi(resultset.getString("personel.adisoyadi"));
	                contract.setUrunAdi(resultset.getString("Adi"));
	                contract.setfiyat(resultset.getInt("fiyat"));
	                contract.setAdet(resultset.getInt("adet"));
	                contract.setTarih(null);
	                datacontract.add(contract);
	            }
	        
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return datacontract;
	    }
	
	

	@Override
	public SatisContract Delete(SatisContract entitiy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(SatisContract entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SatisContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
