package tr.com.cafeotomasyonu.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.cafeotomasyon.core.ObjectHelper;
import tr.com.cafeotomasyonu.interfaces.DALinterfaces;
import tr.com.cafeotomasyon.entity.KategoriContract;
import tr.com.cafeotomasyon.entity.UrunlerContract;

public class UrunlerDAL extends ObjectHelper implements DALinterfaces<UrunlerContract> {

	@Override
	public void Insert(UrunlerContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO urunler (Adi,KategoriId,Fiyat) " + "values('" + entity.getAdi() + "',"
					+ entity.getKategoriId() + "," + entity.getFiyat() + ") ");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public UrunlerContract Delete(UrunlerContract entitiy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(UrunlerContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UrunlerContract> GetAll() {
		List<UrunlerContract> datacontract = new ArrayList<UrunlerContract>();
		Connection connection = getConnection();
		UrunlerContract contract;
		try {
			// girdiğimiz verileri veritabanına uazma işlemleri
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("select * from urunler");// sql den gelen cümleyi result sete
																					// doldur while ile onu domain
																					// içerisine set edeceğiz
			while (resultset.next()) {
				contract = new UrunlerContract();
				contract.setId(resultset.getInt("Id"));
				contract.setAdi(resultset.getString("Adi"));
				contract.setKategoriId(resultset.getInt("KategoriId"));
				contract.setTarih(resultset.getDate("Tarih"));

				datacontract.add(contract);
				//System.out.println(resultset.getString("Adi"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

	@Override
	public List<UrunlerContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
