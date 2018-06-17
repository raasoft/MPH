package mph;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import mph.gui.LoginScreen;

/**
 * MPH Client Startup Class.<br/>
 * It contains the main method.
 */
public class Main {

	/**
	 * The main method used to start up the MPH Client application.
	 * It sets the "Nimbus" Look & Feel for the GUI.
	 */
	public static void main(String[] args) {
		
		try {
		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager
							.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				new LoginScreen().setVisible(true);
			}
		});
		
		
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "AIUTO!" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
