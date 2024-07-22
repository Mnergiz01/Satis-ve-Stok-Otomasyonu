package tr.com.cafeotomasyon.fe;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


import tr.com.cafeotomasyon.entity.PersonelContract;

import tr.com.cafeotomasyonu.dal.PersonelDAL;

import tr.com.cafeotomasyonu.interfaces.Feinterfaces;

public class PersonelEkleFE extends JDialog implements Feinterfaces{

	public PersonelEkleFE() {
		initpencere();
	}

	@Override
	public void initpencere() {
		JPanel panel=initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Personel Ekle"));
		add(panel);
		setTitle("Personel Ekle");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3,2));
		
		JLabel adisoyadilabel=new JLabel("Adı Soyadı : ",JLabel.RIGHT);
		panel.add(adisoyadilabel);
		JTextField adisoyadifield = new JTextField(10);
		panel.add(adisoyadifield);
		JLabel epostalabel = new JLabel("E-Posta :",JLabel.RIGHT);
		panel.add(epostalabel);
		JTextField epostafield = new JTextField(10);
		panel.add(epostafield);
		JButton Kaydetbutton = new JButton("Kaydet");
		panel.add(Kaydetbutton);
		JButton iptalButton = new JButton("İptal");
		panel.add(iptalButton);
		iptalButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        dispose(); // Pencereyi kapat
		    }
		});
		Kaydetbutton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract contract = new PersonelContract();
				
				contract.setAdiSoyadi(adisoyadifield.getText());
				contract.setEmail(epostafield.getText()); 
				if(contract.getAdiSoyadi().trim().length()!=0) {
				new PersonelDAL().Insert(contract);
				JOptionPane.showMessageDialog(null, contract.getAdiSoyadi()+" adlı Personel başarılı bir şeklide eklenmiştir");
				}
				else {
					JOptionPane.showMessageDialog(null, "Lütfen Geçerli Bir İsim Giriniz !");
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
