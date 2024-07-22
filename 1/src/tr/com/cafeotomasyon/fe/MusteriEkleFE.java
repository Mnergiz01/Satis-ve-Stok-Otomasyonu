package tr.com.cafeotomasyon.fe;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tr.com.cafeotomasyon.entity.KategoriContract;
import tr.com.cafeotomasyon.entity.MusteriContract;
import tr.com.cafeotomasyon.entity.UrunlerContract;
import tr.com.cafeotomasyonu.dal.MusteriDAL;
import tr.com.cafeotomasyonu.dal.UrunlerDAL;
import tr.com.cafeotomasyonu.interfaces.Feinterfaces;

public class MusteriEkleFE extends JDialog implements Feinterfaces{

	public MusteriEkleFE() {
		initpencere();
	}

	@Override
	public void initpencere() {
		JPanel panel = initPanel(); 
		panel.setBorder(BorderFactory.createTitledBorder("Müşteri Ekle"));
		//panel.setBackground(Color.black);
		add(panel);
		setTitle("Müşteri Ekle");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel fieldpanel = new JPanel(new GridLayout(5,2));
		JPanel buttonpanel = new JPanel(new GridLayout(1,2));
		JLabel adisoyadilabel = new JLabel("Adı Soyadı :",JLabel.LEFT);
		fieldpanel.add(adisoyadilabel);
		JTextField adisoyadifield = new JTextField(10);
		fieldpanel.add(adisoyadifield);
		JLabel telefonlabel = new JLabel("Telefon :",JLabel.LEFT);
		fieldpanel.add(telefonlabel);
		JTextField telefonfield = new JTextField(10);
		fieldpanel.add(telefonfield);
		//JLabel sehirlabel = new JLabel("Şehir :",JLabel.RIGHT);
		//fieldpanel.add(sehirlabel);
		//JComboBox sehirlerbox = new JComboBox();
		//fieldpanel.add(sehirlerbox);
		//JLabel adreslabel = new JLabel("Adres :");
		JLabel adreslabel = new JLabel();
		fieldpanel.add(adreslabel);
		JTextArea adresfield = new JTextArea(7,1);
		JScrollPane pane = new JScrollPane(adresfield);
		pane.setBorder(BorderFactory.createTitledBorder("Adres Bilgisi"));
		
		JButton kaydetbutton = new JButton("KAYDET");
		buttonpanel.add(kaydetbutton);
		JButton iptalbutton = new JButton("İPTAL");
		buttonpanel.add(iptalbutton);
		panel.add(fieldpanel,BorderLayout.NORTH);
		panel.add(pane,BorderLayout.CENTER);
		panel.add(buttonpanel,BorderLayout.SOUTH);
		iptalbutton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        dispose(); // Pencereyi kapat
		    }
		});
		
		kaydetbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MusteriContract contract = new MusteriContract();
				contract.setAdiSoyadi(adisoyadifield.getText());
				contract.setAdres(adresfield.getText());
				contract.setTelefon(telefonfield.getText());
				new MusteriDAL().Insert(contract);
				JOptionPane.showMessageDialog(null, contract.getAdiSoyadi()+" adlı Müşteri başarılı bir şeklide eklenmiştir");
				
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
