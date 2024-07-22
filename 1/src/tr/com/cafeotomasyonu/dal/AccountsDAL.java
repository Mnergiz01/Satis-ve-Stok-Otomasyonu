package tr.com.cafeotomasyonu.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.cafeotomasyon.core.ObjectHelper;
import tr.com.cafeotomasyon.entity.AccountContract;
import tr.com.cafeotomasyon.entity.PersonelContract;
import tr.com.cafeotomasyon.fe.SifreBelirleFE;
import tr.com.cafeotomasyonu.interfaces.DALinterfaces;

public class AccountsDAL extends ObjectHelper implements DALinterfaces<AccountContract> {

	@Override
	public void Insert(AccountContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into accounts(PersonelId,YetkiId,Sifre) values"
					+ "("+entity.getPersonelId()
					+","
					+entity.getYetkiId()
					+",'"
					+entity.getSifre()+"')");
			statement.close();
			connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		
	}
	public AccountContract getpersonelidvesifre(int personelid,String sifre) {
		AccountContract contract = new AccountContract();
		List<AccountContract> listele = new ArrayList<AccountContract>();
		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM accounts WHERE PersonelId = " + personelid + " AND Sifre = '" + sifre.trim() + "'");
			while(rs.next()) {
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setSifre(rs.getString("Sifre"));
				contract.setYetkiId(rs.getInt("YetkiId"));
			}
			sorgu.close();
			baglanti.close();
		}catch(SQLException e){
			System.out.println(e);
		}
		return contract;
		
	}
	public AccountContract getyetkiid(int personelid) {//yetkilere göre görünüm sağlamak için kullanacağız şimdi değil
		AccountContract contract = new AccountContract();
		List<AccountContract> listele = new ArrayList<AccountContract>();
		Connection baglanti = getConnection();
		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM accounts WHERE PersonelId = " + personelid  +"");
			while(rs.next()) {
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setYetkiId(rs.getInt("YetkiId"));
			}
			sorgu.close();
			baglanti.close();
		}catch(SQLException e){
			System.out.println(e);
		}
		return contract;
		
	}

	@Override
	public List<AccountContract> GetAll() {
	    List<AccountContract> accountList = new ArrayList<>();
	    Connection connection = getConnection();
	    try {
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts");
	        while (resultSet.next()) {
	            AccountContract account = new AccountContract();
	            account.setId(resultSet.getInt("Id"));
	            account.setPersonelId(resultSet.getInt("PersonelId"));
	            account.setYetkiId(resultSet.getInt("YetkiId"));
	            account.setSifre(resultSet.getString("Sifre"));
	            accountList.add(account);
	        }
	        statement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return accountList;
	}

	@Override
	public AccountContract Delete(AccountContract entitiy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(AccountContract entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AccountContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
