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
import javax.swing.SwingUtilities;

import tr.com.cafeotomasyon.entity.Yetkiler;
import tr.com.cafeotomasyonu.dal.YetkilerDAL;
import tr.com.cafeotomasyonu.interfaces.Feinterfaces;

public class YetkiEkleFE extends JDialog implements Feinterfaces{

	public YetkiEkleFE() {
		initpencere();
	}

	@Override
	public void initpencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Yetki Ekle"));
		add(panel);
		setTitle("Yetki Ekle ");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(2,2));
		JLabel adilabel=new JLabel("Yetki Adı:",JLabel.RIGHT);
		panel.add(adilabel);
		JTextField adifield = new JTextField(10);
		panel.add(adifield);
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
				Yetkiler contract =new Yetkiler();
				contract.setAdi(adifield.getText());
				new YetkilerDAL().Insert(contract);
				JOptionPane.showMessageDialog(null,contract.getAdi()+" Adında ki Yetki Başarıyla Oluşturuldu");
				
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
