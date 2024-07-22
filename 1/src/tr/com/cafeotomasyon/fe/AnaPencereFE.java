package tr.com.cafeotomasyon.fe;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import tr.com.cafeotomasyon.complex.entity.SatisContractComplex;
import tr.com.cafeotomasyon.complex.entity.StokContractComplex;
import tr.com.cafeotomasyon.entity.MusteriContract;
import tr.com.cafeotomasyon.entity.PersonelContract;
import tr.com.cafeotomasyon.entity.SatisContract;
import tr.com.cafeotomasyon.entity.StokContract;
import tr.com.cafeotomasyon.entity.UrunlerContract;
import tr.com.cafeotomasyon.facade.CafeOtomasyonFacade;
import tr.com.cafeotomasyonu.FactoryMethod.StokContractFactory;
import tr.com.cafeotomasyonu.dal.MusteriDAL;
import tr.com.cafeotomasyonu.dal.SatisDAL;
import tr.com.cafeotomasyonu.dal.StokDAL;
import tr.com.cafeotomasyonu.dal.UrunlerDAL;
import tr.com.cafeotomasyonu.interfaces.Feinterfaces;
import tr.com.cafeotomasyonu.utilities.MenulerCom;

public class AnaPencereFE extends JFrame implements Feinterfaces {
	 private CafeOtomasyonFacade facade;

	public AnaPencereFE() {
		// TODO Auto-generated constructor stub
		facade = new CafeOtomasyonFacade();
		initpencere();
	}
	private void yeniSatisVerileriniEkle(DefaultTableModel satismodel) {
	    satismodel.setRowCount(0); // Mevcut tüm satırları temizle
	    for (SatisContractComplex contract : new SatisDAL().GetAllSatis()) {
	        satismodel.addRow(contract.getVeriler()); // Yeniden verileri ekle
	    }
	}
	private void yeniStokVerileriniEkle(DefaultTableModel model) {
	    model.setRowCount(0); // Mevcut tüm satırları temizle
	    for (StokContractComplex contract : new StokDAL().getAllStok()) {
	        model.addRow(contract.getVeriler()); // Yeniden verileri ekle
	    }
	}


	@Override
	public void initpencere() {
		JPanel panel = initPanel();

		JMenuBar bar = initBar();

		add(panel);
		setJMenuBar(bar);

		setTitle("Satış Ve Stok Programı");
		setSize(600, 250);
		// pack();//otomatik size alsın
		setVisible(true);// görünürlük
		setLocationRelativeTo(null);// pencere merkezde dursun
		setDefaultCloseOperation(EXIT_ON_CLOSE);// kapatırken

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JTabbedPane pane = initTabs();
		panel.add(pane, BorderLayout.CENTER);
		return panel;
	}

	@Override
	public JTabbedPane initTabs() {
		JTabbedPane pane = new JTabbedPane();
		ImageIcon icon = new ImageIcon("icons/stock.ico");

		// Stok İtemleri
		JPanel stokpanel = new JPanel(new BorderLayout());
		JPanel satispanel = new JPanel(new BorderLayout());
		JPanel stoksolpanel = new JPanel(new BorderLayout());

		JPanel stoksolustpanel = new JPanel(new GridLayout(4, 2));
		JPanel stoksolaltpanel = new JPanel();

		Object[] stokkolonlar = { "id", "Ürün Adı", "Personel Adı", "Adeti", "Tarihi" };
		stoksolpanel.setBorder(BorderFactory.createTitledBorder("Stok İşlemleri"));
		DefaultTableModel model = new DefaultTableModel(stokkolonlar, 0);
		JTable table = new JTable(model);

		JScrollPane stoktablepane = new JScrollPane(table);
		for (StokContractComplex contract : new StokDAL().getAllStok()) {
			model.addRow(contract.getVeriler());// modeldeki satırlara verilerimizi ekleyelim
		}

		JLabel stokurunadilabel = new JLabel("Ürün Adı : ", JLabel.RIGHT);
		stoksolustpanel.add(stokurunadilabel);
		JComboBox stokurunadıbox = new JComboBox(new UrunlerDAL().GetAll().toArray());
		stoksolustpanel.add(stokurunadıbox);
		JLabel stokAdetLabel = new JLabel("Adet : ", JLabel.RIGHT);
		stoksolustpanel.add(stokAdetLabel);
		JTextField stokadetfield = new JTextField(10);
		stoksolustpanel.add(stokadetfield);
		JLabel stoktarihilabel = new JLabel("Stok Tarihi : ", JLabel.RIGHT);
		stoksolustpanel.add(stoktarihilabel);
		JDateChooser stoktarihi = new JDateChooser();
		stoksolustpanel.add(stoktarihi);

		JButton stokeklebutton = new JButton("Stok Ekle");
		stoksolustpanel.add(stokeklebutton);
		JButton stokyenilebutton = new JButton("Stok Yenile");
		stoksolustpanel.add(stokyenilebutton);

		stokeklebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				UrunlerContract ucontract = (UrunlerContract) stokurunadıbox.getSelectedItem();
				PersonelContract pcontract = (PersonelContract) LoginFE.emailbox.getSelectedItem();
				
				/*StokContract contract = new StokContract();
				contract.setPersonelId(pcontract.getId());
				contract.setUrunId(ucontract.getId());
				contract.setTarih(null);
				contract.setAdet(Integer.parseInt(stokadetfield.getText()));*/
				
				
				
               StokContract contract = StokContractFactory.createStokContract(
                        ucontract.getId(),
                        pcontract.getId(),
                        //stoktarihi.getDate(),
                        Integer.parseInt(stokadetfield.getText())
                );
				
				new StokDAL().Insert(contract);

	

				JOptionPane.showMessageDialog(null, ucontract.getAdi() + " adlı ürün stok tablosuna eklenmiştir");

				yeniStokVerileriniEkle(model);

			}
		});
		
		stokyenilebutton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        model.setRowCount(0); // Tablonun mevcut tüm satırlarını temizle
		        for (StokContractComplex contract : new StokDAL().getAllStok()) {
		            model.addRow(contract.getVeriler()); // Yeniden verileri ekle
		        }
		        JOptionPane.showMessageDialog(null, "Stoklar yenilendi.");
		    }
		});
		// satış itemleri

		JPanel satissagpanel = new JPanel(new BorderLayout());

		JPanel satissagustpanel = new JPanel(new GridLayout(5, 2));
		JPanel satissagaltpanel = new JPanel();

		Object[] satiskolonlar = { "id", "Müşteri Adı", "Personel Adı", "Ürün Adı", "Adeti", "Tarihi","Toplam Fiyatı"};
		stoksolpanel.setBorder(BorderFactory.createTitledBorder("Stok İşlemleri"));
		DefaultTableModel satismodel = new DefaultTableModel(satiskolonlar, 0);
		JTable satistable = new JTable(satismodel);
	

		JScrollPane satistablepane = new JScrollPane(satistable);
		
		for (SatisContractComplex contract : new SatisDAL().GetAllSatis()) {
			satismodel.addRow(contract.getVeriler());// modeldeki satırlara verilerimizi ekleyelim
		}

		JLabel satisurunadilabel = new JLabel("Ürün Adı : ", JLabel.RIGHT);
		satissagustpanel.add(satisurunadilabel);
		JComboBox satisurunadıbox = new JComboBox(new UrunlerDAL().GetAll().toArray());
		satissagustpanel.add(satisurunadıbox);
		JLabel müsteriadilabel = new JLabel("Müşteri Adı : ", JLabel.RIGHT);
		satissagustpanel.add(müsteriadilabel);
		JComboBox satismüsteriadıbox = new JComboBox(new MusteriDAL().GetAll().toArray());
		satissagustpanel.add(satismüsteriadıbox);
		JLabel satisAdetLabel = new JLabel("Adet : ", JLabel.RIGHT);
		satissagustpanel.add(satisAdetLabel);
		JTextField satisadetfield = new JTextField(10);
		satissagustpanel.add(satisadetfield);
		JLabel satistarihilabel = new JLabel("Satış Tarihi : ", JLabel.RIGHT);
		satissagustpanel.add(satistarihilabel);
		JDateChooser satistarihi = new JDateChooser();
		satissagustpanel.add(satistarihi);
		JButton satiseklebutton = new JButton("Satış Yap");
		satissagustpanel.add(satiseklebutton);
		//JLabel toplamFiyatLabel = new JLabel("Toplam Fiyat: 0");
        //satissagustpanel.add(toplamFiyatLabel);


		
		
		satiseklebutton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            UrunlerContract ucontract = (UrunlerContract) satisurunadıbox.getSelectedItem();
		            PersonelContract pcontract = (PersonelContract) LoginFE.emailbox.getSelectedItem();
		            MusteriContract mcontract = (MusteriContract) satismüsteriadıbox.getSelectedItem();

		            int adet = Integer.parseInt(satisadetfield.getText());
		            
		            
		            facade.satisYap(ucontract.getId(), pcontract.getId(), mcontract.getId(), adet);
		            
		         // Satış fiyatını hesaplayın ve ekleyin
		            double fiyat = ucontract.getFiyat() ;
		            double toplamFiyat = adet * fiyat;
		           

		            JOptionPane.showMessageDialog(null, ucontract.getAdi() + " adlı ürün satılmıştır." );

		            // Satış tablosunu güncelle
		            yeniSatisVerileriniEkle(satismodel);
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Lütfen geçerli bir adet giriniz.");
		        }
		    }
		});

		
		JButton satisyenilebutton = new JButton("Yenile");
		satissagustpanel.add(satisyenilebutton);

		satispanel.add(satissagpanel, BorderLayout.EAST);
		satispanel.add(satistablepane, BorderLayout.CENTER);
		satissagpanel.add(satissagustpanel, BorderLayout.NORTH);
		satissagpanel.add(satissagaltpanel, BorderLayout.SOUTH);

		stokpanel.add(stoksolpanel, BorderLayout.WEST);
		stokpanel.add(stoktablepane, BorderLayout.CENTER);
		stoksolpanel.add(stoksolustpanel, BorderLayout.NORTH);
		stoksolpanel.add(stoksolaltpanel, BorderLayout.SOUTH);

		pane.addTab("Stoklar", icon, stokpanel, "Does nothing");
		pane.addTab("Satışlar", icon, satispanel, "Does nothing");
		return pane;
	}

	@Override
	public JMenuBar initBar() {
		JMenuBar bar = MenulerCom.initBar();
		return bar;
	}

}
