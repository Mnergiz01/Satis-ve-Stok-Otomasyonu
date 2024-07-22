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

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.demo.JCalendarDemo;

import tr.com.cafeotomasyon.entity.KategoriContract;
import tr.com.cafeotomasyon.entity.UrunlerContract;
import tr.com.cafeotomasyon.templatemethod.UrunEklemeTemplate;
import tr.com.cafeotomasyon.templatemethod.VeritabaniUrunEkleme;
import tr.com.cafeotomasyonu.dal.KategoriDAL;
import tr.com.cafeotomasyonu.dal.UrunlerDAL;
import tr.com.cafeotomasyonu.interfaces.Feinterfaces;

public class UrunEkleFE extends JDialog implements Feinterfaces {
	private UrunEklemeTemplate urunEklemeTemplate;

	public UrunEkleFE() {
		urunEklemeTemplate = new VeritabaniUrunEkleme();
		initpencere();
	}

	@Override
	public void initpencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Ürün Kayıt Alanı"));
		add(panel);
		setTitle("Ürün Ekleyin");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);//pencereyi önde aç arkaya geçmesine izin verme
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);//

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5, 2));// 5 satır 2 sütunlük panel
		JLabel adıLabel = new JLabel("Ürün Adı : ", JLabel.RIGHT);
		panel.add(adıLabel);
		JTextField adiField = new JTextField(10);// 10 kelimelik text bölümü
		panel.add(adiField);
		JLabel kategoriLabel = new JLabel("Kategorisi : ", JLabel.RIGHT);
		panel.add(kategoriLabel);
		JComboBox kategoriBox = new JComboBox(new KategoriDAL().GetAll().toArray());
		panel.add(kategoriBox);
		kategoriBox.insertItemAt("---Kategori Seçiniz---", 0);
		kategoriBox.setSelectedIndex(0);
		//JLabel tarihLabel = new JLabel("Tarih : ", JLabel.RIGHT);
		//panel.add(tarihLabel);
		//JCalendar tarihdate = new JCalendar();
		//panel.add(tarihdate);

		JLabel fiyatlabel = new JLabel("Fiyatı : ", JLabel.RIGHT);
		panel.add(fiyatlabel);
		JTextField fiyatfield = new JTextField(10);
		panel.add(fiyatfield);
		JLabel bos1 = new JLabel("", JLabel.RIGHT);
		JLabel bos2 = new JLabel("", JLabel.RIGHT);
		panel.add(bos2);
		panel.add(bos1);
		
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
				UrunlerContract contract = new UrunlerContract();
				KategoriContract cascontract =(KategoriContract) kategoriBox.getSelectedItem();
				contract.setAdi(adiField.getText());
				contract.setKategoriId(cascontract.getId());
				contract.setFiyat(Float.parseFloat(fiyatfield.getText()));
				/*new UrunlerDAL().Insert(contract);
				JOptionPane.showMessageDialog(null, contract.getAdi()+" adlı ürün başarılı bir şeklide eklenmiştir");
				*/
				 try {
	                    urunEklemeTemplate.urunEkle(contract);
	                    JOptionPane.showMessageDialog(null, contract.getAdi() + " adlı ürün başarılı bir şekilde eklenmiştir");
	                } catch (Exception ex) {
	                    JOptionPane.showMessageDialog(null, "Ürün eklenirken bir hata oluştu: " + ex.getMessage());
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
