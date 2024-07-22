package tr.com.cafeotomasyonu.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.cafeotomasyon.core.ObjectHelper;
import tr.com.cafeotomasyon.entity.KategoriContract;
import tr.com.cafeotomasyon.entity.PersonelContract;
import tr.com.cafeotomasyonu.interfaces.DALinterfaces;

public class PersonelDAL extends ObjectHelper implements DALinterfaces<PersonelContract> {

	@Override
	public void Insert(PersonelContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into personel(adisoyadi,eposta) values('" + entity.getAdiSoyadi() + "','"
					+ entity.getEmail() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public List<PersonelContract> GetAll() {
		List<PersonelContract> datacontract = new ArrayList<PersonelContract>();
		Connection connection = getConnection();
		PersonelContract contract;
		try {
			// girdiğimiz verileri veritabanına uazma işlemleri
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("select * from personel");// sql den gelen cümleyi result sete
																					// doldur while ile onu domain
																					// içerisine set edeceğiz
			while (resultset.next()) {
				contract = new PersonelContract();
				
				contract.setId(resultset.getInt("Id"));
				contract.setAdiSoyadi(resultset.getString("adisoyadi"));
				contract.setEmail(resultset.getString("eposta"));

				datacontract.add(contract);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datacontract;
	}

	@Override
	public PersonelContract Delete(PersonelContract entitiy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(PersonelContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PersonelContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
