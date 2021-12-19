package Helper;

import javax.swing.JOptionPane;

public class Helper {
	
	public static void showMsg(String str) {
		String msg;
		
		switch(str) {
		case "fill":
			msg = "Lütfen Tüm Alanları Doldurunuz";
			break;
		default:
			msg = str;
		}
		
		JOptionPane.showConfirmDialog(null, msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
	}

}
