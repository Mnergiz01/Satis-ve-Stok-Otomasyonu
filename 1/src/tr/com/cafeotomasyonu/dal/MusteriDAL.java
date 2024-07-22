package tr.com.cafeotomasyonu.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.cafeotomasyon.core.ObjectHelper;
import tr.com.cafeotomasyon.entity.MusteriContract;
import tr.com.cafeotomasyonu.interfaces.DALinterfaces;

public class MusteriDAL extends ObjectHelper implements DALinterfaces<MusteriContract> {

	@Override
	public void Insert(MusteriContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into musteri(AdiSoyadi,Telefon,Adres) values('"
					+entity.getAdiSoyadi()+"','"+entity.getTelefon()+"','"
					+entity.getAdres()+"')");
			statement.close();
			connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}

	@Override
	public List<MusteriContract> GetAll() {
	    List<MusteriContract> musteriList = new ArrayList<>();
	    Connection connection = getConnection();
	    MusteriContract musteriContract;

	    try {
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM musteri");

	        while (resultSet.next()) {
	            musteriContract = new MusteriContract();
	            musteriContract.setId(resultSet.getInt("Id"));
	            musteriContract.setAdiSoyadi(resultSet.getString("AdiSoyadi"));
	            musteriContract.setTelefon(resultSet.getString("Telefon"));
	            musteriContract.setAdres(resultSet.getString("Adres"));
	            musteriList.add(musteriContract);
	        }

	        resultSet.close();
	        statement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return musteriList;
	}

	@Override
	public MusteriContract Delete(MusteriContract entitiy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(MusteriContract entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MusteriContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
