package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;

public class GraphWindow extends JFrame {

	private JPanel contentPane;
	private static JLabel nbrSmts;	 
	
	public GraphWindow(String str) {
		
		
		int nbrSmtsInt= Integer.parseInt(str);	

//FrONT END START ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		this.setTitle("a* GUI - ABKLR");//initials for our names

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

		JPanel butPan= new JPanel();
		butPan.setLayout(new GridLayout(15,2,10,1));
		//GridBagConstraints gbc = new GridBagConstraints();
        butPan.setBorder(BorderFactory.createEmptyBorder(120, 50, -120, 50));
        
		JPanel butPan2= new JPanel();
	    butPan2.setLayout(new GridBagLayout());
	    butPan2.add(butPan);
	    mainContainer.add(butPan2,BorderLayout.WEST);
	    
// CENTER PANEL ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    
	    JPanel mainPan= new JPanel();
		mainPan.setLayout(new GridLayout(nbrSmtsInt+1, nbrSmtsInt+1));
		mainPan.setBorder(new EmptyBorder(50, 0, 50, 50));
	    JScrollPane scrollPane = new JScrollPane(mainPan,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.setPreferredSize(new Dimension(600, 600));

	    GridBagConstraints frameConstraints = new GridBagConstraints();
	    frameConstraints.gridx = 0;
	    frameConstraints.gridy = 1;
	    frameConstraints.weighty = 1;
	    mainContainer.add(mainPan,BorderLayout.CENTER ); // add scrollpane to frame


		
		JLabel lbdprt = new JLabel("Point de depart :");
		JLabel lbarv = new JLabel("Point d'arrive :");

		JTextField dprt= new JTextField();
		butPan.add(lbdprt);				
		butPan.add(dprt);
		dprt.setHorizontalAlignment(JTextField.CENTER);
		
		JTextField arv= new JTextField();
		butPan.add(lbarv);				
		butPan.add(arv);
		arv.setHorizontalAlignment(JTextField.CENTER);
		//arv.setColumns(10);
				
				
	

		
// Front End end ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		int rows = nbrSmtsInt + 1;//we want 1 column more for sommet
		int cols = nbrSmtsInt + 2;//2 more, 1 for sommet 1 for H

		
// Création de la matrice ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		JTextField[][] tfs= new JTextField[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				
// Colonne des Heuristiques ////////////////////////////////////////////////////////////////////////////////////////////////////
				
				if(j == cols-1) {
					
					// Cellule des Heuristiques ////////////////////////////////////////////////////////////////////////////////////////////////////
					
					if(i == 0) {  
					tfs[i][j] = new JTextField();
					tfs[i][j].setBounds(180+j*40,i*40+40, 40, 40);
					tfs[i][j].setBackground(new Color(238, 238, 238));
					tfs[i][j].setEditable(false);
					tfs[i][j].setHorizontalAlignment(JTextField.CENTER);
					tfs[i][j].setText("Heuristiques");
					Border border = BorderFactory.createLineBorder(new Color(79, 52, 160), 2);
			        tfs[i][j].setBorder(border);
			      	Font newTextFieldFont=new Font( tfs[i][j].getFont().getName(),Font.BOLD, 13);
			        tfs[i][j].setFont(newTextFieldFont);
					mainPan.add(tfs[i][j]);
					tfs[i][j].setColumns(10);}
					
					// Cellules Poids ////////////////////////////////////////////////////////////////////////////////////////////////////

					else {
						tfs[i][j]=new JTextField();
						tfs[i][j].setBounds(180+j*40,i*40+40, 40, 40);
						tfs[i][j].setBackground(new Color(79, 52, 160));
						tfs[i][j].setForeground(Color.WHITE);
						tfs[i][j].setHorizontalAlignment(JTextField.CENTER);
						mainPan.add(tfs[i][j]);
						tfs[i][j].setColumns(10);
					}
				}
				
// Cellule Sommets + Cellules immodifiables  ////////////////////////////////////////////////////////////////////////////////////////////////////

				else if(i==j || (i>j && j!=0)) {
					
					// Cellule des Sommets ////////////////////////////////////////////////////////////////////////////////////////////////////

					if(i == 0) {
						tfs[i][j] = new JTextField("Sommets");
						tfs[i][j].setBounds(140+j*40,i*40+40, 40, 40);
						tfs[i][j].setHorizontalAlignment(JTextField.CENTER);
						Border border = BorderFactory.createLineBorder(new Color(79, 52, 160), 2);
						tfs[i][j].setBorder(border);
						Font newTextFieldFont=new Font( tfs[i][j].getFont().getName(),Font.BOLD, 13);
					    tfs[i][j].setFont(newTextFieldFont);
						tfs[i][j].setBackground(new Color(238,238,238));
						tfs[i][j].setEditable(false);
						mainPan.add(tfs[i][j]);
						tfs[i][j].setColumns(10);
					}
					
					// Les autres cellule immodifiables  ////////////////////////////////////////////////////////////////////////////////////////////////////

					else {
						tfs[i][j] = new JTextField();
						tfs[i][j].setBounds(140+j*40,i*40+40, 40, 40);
						tfs[i][j].setHorizontalAlignment(JTextField.CENTER);
						tfs[i][j].setBackground(new Color(238,238,238));
						tfs[i][j].setBorder(null);
						tfs[i][j].setEditable(false);
						mainPan.add(tfs[i][j]);
						tfs[i][j].setColumns(10);
					}
				}

				
// Cellule Sommets Verticaux + Horizontaux + Remplissage Auto   ////////////////////////////////////////////////////////////////////////////////////////////////////

				else if(i == 0 || j == 0){
					
					// Cellule Sommets Verticaux  ////////////////////////////////////////////////////////////////////////////////////////////////////

					if(i == 0) {
						String jtxName = "Sommet"+Integer.toString(j);
						tfs[i][j]=new JTextField(jtxName);
						tfs[i][j].setBounds(140+j*40,i*40+40, 40, 40);
						tfs[i][j].setHorizontalAlignment(JTextField.CENTER);
						mainPan.add(tfs[i][j]);
						tfs[i][j].setColumns(10);}
					
					// Cellule Sommets Horizontaux  ////////////////////////////////////////////////////////////////////////////////////////////////////

					else {
						String jtxName = "Sommet"+Integer.toString(i);
						tfs[i][j]=new JTextField(jtxName);
						tfs[i][j].setBounds(140+j*40,i*40+40, 40, 40);
						tfs[i][j].setHorizontalAlignment(JTextField.CENTER);
						mainPan.add(tfs[i][j]);
						tfs[i][j].setColumns(10);
					}
				}
					
					
// Autres Cellules  ////////////////////////////////////////////////////////////////////////////////////////////////////

				else {
					tfs[i][j]=new JTextField();
					tfs[i][j].setBounds(140+j*40,i*40+40, 40, 40);
					tfs[i][j].setHorizontalAlignment(JTextField.CENTER);
					mainPan.add(tfs[i][j]);
					tfs[i][j].setColumns(10);
				}
			}
		}
		 

	
		
		JButton res = new JButton("Resultat");
		butPan.add(res);
		butPan.getRootPane().setDefaultButton(res);//on enter this clicks
		res.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String dprtC = dprt.getText();
				String arvC = arv.getText();
				Boolean hIsEmpty = false;
				Boolean sAreEmpty = false;
				
				for(int i = 0; i < rows;i++) {
					for(int j = 0;j < cols;j++) {
						
						if(i == 0 && (tfs[0][j].getText() == null || tfs[0][j].getText() == "" || tfs[0][j].getText().isEmpty())) {
							sAreEmpty = true;
							break;
						}
						
						else if(j == 0 && (tfs[i][0].getText() == null || tfs[i][0].getText() == "" || tfs[i][0].getText().isEmpty())) {
							sAreEmpty = true;
							break;
						}
						
						else if(j == cols-1 && tfs[i][cols-1].getText() == null || tfs[i][cols-1].getText() == "" || tfs[i][cols-1].getText().isEmpty()) {
							hIsEmpty = true;
							break;
						}
					}
				}
				
				
				
				if(!dprtC.isEmpty() && !arvC.isEmpty() && hIsEmpty == false && sAreEmpty == false) {
					//System.out.println(tfs[1][1].getText());
					ArrayList<String> algoResult = aStarAlgo.mainClass.runAlgorithm(tfs,dprt.getText(),arv.getText());
					String msg = algoResult.get(0) + ". \n" +algoResult.get(1);
					JOptionPane.showMessageDialog(null, msg);//frame + message
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Incomplete Informations");//frame + message

				}						
				
				
				
			}
		});
		res.setHorizontalAlignment(JLabel.CENTER);
				
		
				
	
		
	} 
}
