package tr.com.cafeotomasyon.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;

import tr.com.cafeotomasyon.entity.PersonelContract;
import tr.com.cafeotomasyonu.dal.AccountsDAL;
import tr.com.cafeotomasyonu.dal.PersonelDAL;
import tr.com.cafeotomasyonu.interfaces.Feinterfaces;

public class LoginFE extends JDialog implements Feinterfaces {

	public static JComboBox emailbox;
	public LoginFE() {
		initpencere();
	}

	@Override
	public void initpencere() {
		JPanel panel = initPanel();
		add(panel);
		panel.setBorder(BorderFactory.createTitledBorder("Bilgilerinizi Giriniz"));
		setTitle("Giriş Yapınız");
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3,2));
		JLabel emillabel = new JLabel("Email",JLabel.RIGHT);
		panel.add(emillabel);
	    emailbox = new JComboBox(new PersonelDAL().GetAll().toArray());
		
		panel.add(emailbox);
		JLabel passlabel = new JLabel("ŞİFRENİZ :",JLabel.RIGHT);
		panel.add(passlabel);
		JPasswordField passfield = new JPasswordField(15);
		panel.add(passfield);
		
 		JButton loginbutton = new JButton("Giriş Yap");
 		panel.add(loginbutton);
 		JButton iptalbutton = new JButton("İptal");
 		panel.add(iptalbutton);
 		
 		loginbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract contract = (PersonelContract) emailbox.getSelectedItem();
				String sifre = passfield.getText();
				if(new AccountsDAL().getpersonelidvesifre(contract.getId(), sifre).getId()!=0) {//kullanıcı adı ve şifrenin id ye bağlı mı onu kontrol ediyoruz
					new AnaPencereFE();
					dispose();//loginfe penceresini kapatır anapencere açılınca
					
				}
				else {
					JOptionPane.showMessageDialog(null,"Giriş Başarısız");
				}
			}
		});
        iptalbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // LoginFE penceresini kapat
            }
        });
 		
 		return panel;
 		

	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JMenuBar initBar() {
		// TODO Auto-generated method stub
		return null;
	}

}
