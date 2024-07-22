package tr.com.cafeotomasyonu.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.cafeotomasyon.core.ObjectHelper;
import tr.com.cafeotomasyon.entity.PersonelContract;
import tr.com.cafeotomasyon.entity.Yetkiler;
import tr.com.cafeotomasyonu.interfaces.DALinterfaces;

public class YetkilerDAL extends ObjectHelper implements DALinterfaces<Yetkiler> {

	@Override
	public void Insert(Yetkiler entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into yetkiler(Adi) values('" + entity.getAdi() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Yetkiler> GetAll() {
		List<Yetkiler> datacontract = new ArrayList<Yetkiler>();
		Connection connection = getConnection();
		Yetkiler contract;
		try {
			// girdiğimiz verileri veritabanına uazma işlemleri
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("select * from yetkiler");// sql den gelen cümleyi result sete
																					// doldur while ile onu domain
																					// içerisine set edeceğiz
			while (resultset.next()) {
				contract = new Yetkiler();
				contract.setId(resultset.getInt("Id"));

				contract.setAdi(resultset.getString("Adi"));

				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

	@Override
	public Yetkiler Delete(Yetkiler entitiy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(Yetkiler entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Yetkiler> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
