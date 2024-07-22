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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tr.com.cafeotomasyon.entity.KategoriContract;
import tr.com.cafeotomasyonu.dal.KategoriDAL;
import tr.com.cafeotomasyonu.interfaces.Feinterfaces;

public class KategoriEkleFE extends JDialog implements Feinterfaces {

	public KategoriEkleFE() {
		initpencere();
	}

	@Override
	public void initpencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Kategori Ekle"));
		add(panel);
		setTitle("Kategori Ekle");
		setSize(500,200);
		setModalityType(DEFAULT_MODALITY_TYPE);//PENCEREYİ ÖNDE TUT
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);//ÇIKIŞ YAPMASINA İZİN VERME
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3,2));
		JLabel adilabel = new JLabel("Kategori Adı :" , JLabel.RIGHT);
		panel.add(adilabel);
		JTextField adifield = new JTextField(10);
		panel.add(adifield);
		JLabel kategorilabel = new JLabel("Kategori Seç" , JLabel.RIGHT);
		panel.add(kategorilabel);
		//JComboBox kategoribox = new JComboBox();
		JComboBox kategoribox = new JComboBox(new KategoriDAL().GetAll().toArray());
		panel.add(kategoribox);
		kategoribox.insertItemAt("---Kategori Seçiniz---", 0);
		kategoribox.setSelectedIndex(0);
		JButton kaydetbutton = new JButton("Kaydet");
		panel.add(kaydetbutton);
		kaydetbutton.addActionListener(new ActionListener() { 
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KategoriContract contract =new KategoriContract();
				if (kategoribox.getSelectedIndex() != 0 ) {
				KategoriContract cascontract =(KategoriContract) kategoribox.getSelectedItem();
				
				
				contract.setAdi(adifield.getText());
				contract.setParentId(cascontract.getId());
				
				new KategoriDAL().Insert(contract);
				
				JOptionPane.showMessageDialog(null, contract.getAdi()+" Adlı Kategori Başarılı bir Şekilde Eklendi");
				}
				else {
					contract.setAdi(adifield.getText());
					contract.setParentId(kategoribox.getSelectedIndex());
					
					new KategoriDAL().Insert(contract);
					
					JOptionPane.showMessageDialog(null, contract.getAdi()+" Adlı Kategori Başarılı bir Şekilde Eklendi");
					kategoribox.addItem(new KategoriDAL().GetAll());//kategorileri parent idsine göre sınıflandıracak getall değişecek
					revalidate();
					repaint();
				}
			}
		});
		JButton iptalbutton = new JButton("İptal");
		panel.add(iptalbutton);
		iptalbutton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        dispose(); // Pencereyi kapat
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
