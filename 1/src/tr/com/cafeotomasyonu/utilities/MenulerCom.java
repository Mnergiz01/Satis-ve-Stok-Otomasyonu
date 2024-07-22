package tr.com.cafeotomasyonu.utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import tr.com.cafeotomasyon.fe.KategoriEkleFE;
import tr.com.cafeotomasyon.fe.MusteriEkleFE;
import tr.com.cafeotomasyon.fe.UrunEkleFE;
import tr.com.cafeotomasyon.fe.YetkiEkleFE;
import tr.com.cafeotomasyon.fe.PersonelEkleFE;
import tr.com.cafeotomasyon.fe.SifreBelirleFE;

public class MenulerCom {
	public static JMenuBar initBar() {
		JMenuBar bar = new JMenuBar();
		//dosya menüsü
		JMenu dosyaMenu = new JMenu("Dosya");
		bar.add(dosyaMenu);
		JMenuItem cıkısItem = new JMenuItem("Çıkış");
		dosyaMenu.add(cıkısItem);
		//ürünler menüsü
		JMenu urunlerMenu = new JMenu("Ürünler");
		bar.add(urunlerMenu);
		JMenuItem urunEkleItem = new JMenuItem("Ürün ekle");
		urunlerMenu.add(urunEkleItem);
		JMenuItem kategoriEkleItem = new JMenuItem("Kategori ekle");
		urunlerMenu.add(kategoriEkleItem);
		urunlerMenu.addSeparator();//ayraç
		JMenuItem urunDuzenleItem = new JMenuItem("Ürünü Düzenle");
		urunlerMenu.add(urunDuzenleItem);
		JMenuItem kategoriDuzenleItem = new JMenuItem("Kategoriyi Düzenle");
		urunlerMenu.add(kategoriDuzenleItem);
		
		//personel menüsü
		JMenu personellermenu = new JMenu("Personel İşlemleri");
		bar.add(personellermenu);
		JMenuItem personelekleitem = new JMenuItem("Personel Ekle");
		personellermenu.add(personelekleitem);
		JMenuItem yetkiekleitem = new JMenuItem("Yetki Ekle");
		personellermenu.add(yetkiekleitem);
		JMenuItem sifrebelirleitem = new JMenuItem("Şifre Belirleme");
		personellermenu.add(sifrebelirleitem);
		personellermenu.addSeparator();
		
		JMenuItem personelduzenleitem = new JMenuItem("Personel Düzenle");
		personellermenu.add(personelduzenleitem);
		JMenuItem yetkiduzenleitem = new JMenuItem("Yetki Düzenle");
		personellermenu.add(yetkiduzenleitem);
		JMenuItem sifreduzenleitem = new JMenuItem("Şifre Düzenle");
		personellermenu.add(sifreduzenleitem);
		
		//Müşteri Menüsü
		JMenu musterilermenu = new JMenu("Müşteriler");
		bar.add(musterilermenu);
		JMenuItem musteriekleitem = new JMenuItem("Müşteri Ekle");
		musterilermenu.add(musteriekleitem);
		//JMenuItem sehirekleitem = new JMenuItem("Şehir Ekle");
		//musterilermenu.add(sehirekleitem);
		musterilermenu.addSeparator();
		
		JMenuItem musteriduzenleitem = new JMenuItem("Müşteri Düzenle");
		musterilermenu.add(musteriduzenleitem);
		//JMenuItem sehirduzenleitem = new JMenuItem("Şehir Düzenle");
		//musterilermenu.add(sehirduzenleitem);
		
		musteriekleitem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						
						new MusteriEkleFE();
					}
				});
				
			}
		});
		
		urunEkleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						
						new UrunEkleFE();
					}
				});
			}
		});
		kategoriEkleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new KategoriEkleFE();
						
					}
					
				});
				
				
			}
		});
		
		personelekleitem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						new PersonelEkleFE();
					}
				});
				
			}
		});
		yetkiekleitem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new YetkiEkleFE();
						
					}
				});
				
			}
		});
		cıkısItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        System.exit(0); // Uygulamayı kapat
		    }
		});

		sifrebelirleitem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						new SifreBelirleFE();
						
					}
				});
				
			}
		});
		return bar;
	}
}
