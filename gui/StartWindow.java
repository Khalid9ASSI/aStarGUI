package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.modelmbean.ModelMBeanNotificationBroadcaster;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class StartWindow extends JFrame {

	private JPanel contentPane;
	
	//public static void nbrSmts(String str) {nbrSmts.setText(str);}
	
	 /*
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow frame = new StartWindow();
					frame.setVisible(true);
					frame.setResizable(false);	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StartWindow() {
//Front End Start ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		this.setTitle("a* GUI - ABKLR");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	    this.setLocationRelativeTo(null);
	    this.setSize(screenSize.width, screenSize.height);
		this.setLocation(100,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
// MAIN CONTAINER ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		Container mainContainer = this.getContentPane();
		mainContainer.setLayout(new BorderLayout());
		mainContainer.setBackground(Color.WHITE);
		
		
// EAST PANEL ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		JPanel butPan = new JPanel();
		butPan.setLayout(new GridLayout(15,2,10,1));
		//GridBagConstraints gbc = new GridBagConstraints();
        butPan.setBorder(BorderFactory.createEmptyBorder(120, 50, -120, 50));
		butPan.setBackground(new Color(79, 52, 160));

		JPanel butPan2 = new JPanel();
		butPan2.setBackground(new Color(79, 52, 160));

	    butPan2.setLayout(new GridBagLayout());
	    butPan2.add(butPan);
	    mainContainer.add(butPan2,BorderLayout.WEST);
	    
// CENTER PANEL ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    
	    JPanel mainPan = new JPanel();
		mainPan.setBorder(new EmptyBorder(225, 0, 225, 50));
	    GridBagConstraints frameConstraints = new GridBagConstraints();
	    frameConstraints.gridx = 0;
	    frameConstraints.gridy = 1;
	    frameConstraints.weighty = 1;
	    mainContainer.add(mainPan,BorderLayout.CENTER ); // add acrollpane to frame


		
		JLabel lbnbrSmts = new JLabel("Nombre de sommets de votre graphe : (Integer)");
		lbnbrSmts.setForeground(Color.WHITE);

		butPan.add(lbnbrSmts);
	
	JTextArea ta = new JTextArea();
	String doc="a* GUI by: ABKLG \n \n  \n \nComment ca marche: \n \n         1: Tapez le nombre des sommets de votre graphe \n \n         2: saisissez les poids des arcs entres sommets et modifier les noms des sommets \n \n         3: Saisissez le sommet de depart et d'arrive dans leurs emplacements \n \n         4: Cliquez sur Resultat \n \nCe travail est realise par:\n \n Achraf AGLMOUS && Adnane BENAZZOU && Khalid KASSI  &&  Hajar LACHHEB  &&  Jamila REGUIG \n ESI 2020/2021";
	ta.setBackground(new Color(238,238,238));
	ta.setText(doc);
	mainPan.add(ta);
		
		
		

		

		

	

		JTextField nbrSmts= new JTextField();
		butPan.add(nbrSmts);				
		butPan.add(nbrSmts);
		nbrSmts.setHorizontalAlignment(JTextField.CENTER);
		
		JButton svt= new JButton("Suivant");
		svt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nbrSmtsIns= nbrSmts.getText();
				if(!nbrSmtsIns.isEmpty()) {
					GraphWindow scnd = new GraphWindow(nbrSmtsIns);
					scnd.setVisible(true);}
				else {
					JOptionPane.showMessageDialog(null, "Veuillez Saisir le champ present avec un nombre valide");//frame + message

				}
			}
		});
		butPan.add(svt);
		butPan.getRootPane().setDefaultButton(svt);
	}
}
