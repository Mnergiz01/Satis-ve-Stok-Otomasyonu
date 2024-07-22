package tr.com.cafeotomasyon.test;

import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import tr.com.cafeotomasyon.fe.AnaPencereFE;
import tr.com.cafeotomasyon.fe.LoginFE;
import tr.com.cafeotomasyonu.dal.UrunlerDAL;

public class Run {

	public static void main(String[] args) {
		
		try {
			for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());;
					break;
				}
			}
		}catch(Exception e) {
			
		}
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				//new AnaPencereFE();
				new LoginFE();
				//new UrunlerDAL().GetAll(); //ürünleri döndürür
			}
			
		});

	}

}
