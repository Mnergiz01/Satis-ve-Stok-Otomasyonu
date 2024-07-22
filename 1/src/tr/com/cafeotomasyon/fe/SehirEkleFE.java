package tr.com.cafeotomasyon.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tr.com.cafeotomasyonu.interfaces.Feinterfaces;

public class SehirEkleFE extends JDialog implements Feinterfaces {

	public SehirEkleFE() {
		initpencere();
	}

	@Override
	public void initpencere() {
		JPanel panel =initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Şehir Ekle"));
		add(panel);
		setTitle("Şehir Ekle");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(2,2));
		JLabel sehirlabel= new JLabel("Şehir Giriniz :",JLabel.RIGHT);
		panel.add(sehirlabel);
		JTextField sehirfield = new JTextField(15);
		panel.add(sehirfield);
		JButton kaydetbutton = new JButton("Kaydet");
		panel.add(kaydetbutton);
		JButton iptalbutton = new JButton("İptal");
		panel.add(iptalbutton);
		
		kaydetbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
