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

import tr.com.cafeotomasyon.entity.AccountContract;
import tr.com.cafeotomasyon.entity.PersonelContract;
import tr.com.cafeotomasyon.entity.Yetkiler;
import tr.com.cafeotomasyonu.dal.AccountsDAL;
import tr.com.cafeotomasyonu.dal.PersonelDAL;
import tr.com.cafeotomasyonu.dal.YetkilerDAL;
import tr.com.cafeotomasyonu.interfaces.Feinterfaces;

public class SifreBelirleFE extends JDialog implements Feinterfaces{

	public SifreBelirleFE() {
		initpencere();
	}

	@Override
	public void initpencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Şifre Belirleme İşlemleri"));
		add(panel);
		setTitle("Şifre Belirleme İşlemleri");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5,2));
		JLabel personellabel=new JLabel("Personel Seç",JLabel.RIGHT);
		panel.add(personellabel);
		JComboBox personelbox = new JComboBox(new PersonelDAL().GetAll().toArray());
		panel.add(personelbox);
		JLabel yetkilabel = new JLabel("Yetki Seç",JLabel.RIGHT);
		panel.add(yetkilabel);
		JComboBox yetkibox = new JComboBox(new YetkilerDAL().GetAll().toArray());
		panel.add(yetkibox);
		JLabel pass1label = new JLabel("Şifre Belirle",JLabel.RIGHT);
		panel.add(pass1label);
		JPasswordField pass1field = new JPasswordField(10);
		panel.add(pass1field);
		JLabel pass2label = new JLabel("Şifre Tekrar",JLabel.RIGHT);
		panel.add(pass2label);
		JPasswordField pass2field = new JPasswordField(10);
		panel.add(pass2field);
		
		JButton kaydetbutton = new JButton("Kaydet");
		panel.add(kaydetbutton);
		JButton iptalbutton = new JButton("İptal");
		panel.add(iptalbutton);
		iptalbutton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        dispose(); // Pencereyi kapat
		    }
		});
		
		kaydetbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AccountContract contract = new AccountContract();
				PersonelContract pcontract = (PersonelContract) personelbox.getSelectedItem();
				Yetkiler ycontract = (Yetkiler) yetkibox.getSelectedItem();
				if(pass1field.getText().equals(pass2field.getText())) {
					
					contract.setPersonelId(pcontract.getId());
					contract.setYetkiId(ycontract.getId());
					contract.setSifre(pass1field.getText());
					
					new AccountsDAL().Insert(contract);
					JOptionPane.showMessageDialog(null,ycontract.getAdi()+" adlı yetkiyle "+pcontract.getAdiSoyadi()+" İsimli Kullanıcı Başarılı bir şekilde yetki oluşturulmuştur ");
					
				}else {
					JOptionPane.showMessageDialog(null,"Şifreler Uyuşmuyor Tekrar Kontrol Ediniz");
				}
				
				
				
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
