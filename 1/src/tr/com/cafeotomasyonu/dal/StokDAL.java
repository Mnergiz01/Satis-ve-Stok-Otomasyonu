package tr.com.cafeotomasyonu.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.cafeotomasyon.complex.entity.StokContractComplex;
import tr.com.cafeotomasyon.core.ObjectHelper;
import tr.com.cafeotomasyon.entity.StokContract;
import tr.com.cafeotomasyon.entity.UrunlerContract;
import tr.com.cafeotomasyonu.interfaces.DALinterfaces;


public class StokDAL extends ObjectHelper implements DALinterfaces<StokContract> {

	@Override
	public void Insert(StokContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into stok(UrunId,PersonelId,Tarih,adet) values("+entity.getUrunId()+","+entity.getPersonelId()+","+entity.getTarih()+","+entity.getAdet()+")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
	}
	/*
	 SELECT stok.Id,AdiSoyadi,Adi,adet,stok.Tarih from stok
	  left join urunler on stok.UrunId = urunler.Id 
	  left join personel on stok.PersonelId = personel.Id;
	 */
	public List<StokContractComplex> getAllStok(){
		List<StokContractComplex> datacontract = new ArrayList<StokContractComplex>();
		Connection connection = getConnection();
		StokContractComplex contract;
		try {
			// girdiğimiz verileri veritabanına uazma işlemleri
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("	 SELECT stok.Id,AdiSoyadi,Adi,adet,stok.Tarih from stok\r\n"
					+ "	  left join urunler on stok.UrunId = urunler.Id \r\n"
					+ "	  left join personel on stok.PersonelId = personel.Id;");// sql den gelen cümleyi result sete
																					// doldur while ile onu domain
																					// içerisine set edeceğiz
			while (resultset.next()) {
				contract = new StokContractComplex();
				contract.setId(resultset.getInt("Id"));
				contract.setPersonelAdi(resultset.getString("AdiSoyadi"));
				contract.setUrunAdi(resultset.getString("Adi"));
				contract.setAdet(resultset.getInt("adet"));
				contract.setTarih(null);
				datacontract.add(contract);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}
	@Override
	public List<StokContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StokContract Delete(StokContract entitiy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(StokContract entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<StokContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
