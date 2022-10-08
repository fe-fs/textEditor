import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; //to be able to handle events
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextEditor extends JFrame implements ActionListener {
	
	JTextArea textArea;
	JScrollPane scrollPane;
	JLabel fontLabel;
	JSpinner fontSizeSpinner; //for the user be able to change the font size
	JButton fontColorButton; //user will change color for the font
	JComboBox fontBox; //user will be able to change fonts
	
	//menu bar var
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenu editMenu;
	JMenu aboutMenu;
	JMenuItem openItem;
	JMenuItem saveItem;
	JMenuItem exitItem;
	JMenuItem cutItem;
	JMenuItem copyItem;
	JMenuItem pasteItem;
	JMenuItem infoItem;
	
	
	TextEditor(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //click x on the corner to exit
		this.setTitle("FEFS Text Editor Project!");
		this.setSize(500, 500); //size of screen
		this.setLayout(new FlowLayout()); //can set to null and manually place all components
		this.setLocationRelativeTo(null); //this will set the window to be at the middle of the screen
		
		
		textArea = new JTextArea();
		//textArea.setPreferredSize(new Dimension(450,450)); //no scroll
		textArea.setLineWrap(true); //this will let the text continue in the next line
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Comic Sans MS",Font.PLAIN,20));//how to import font? 6:39
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(450,450)); //this add the paper window with the scroll.
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); //this make the scrollbar always visible
		
		fontLabel = new JLabel("Size:");
		
		fontSizeSpinner = new JSpinner();
		fontSizeSpinner.setPreferredSize(new Dimension(50,25));
		fontSizeSpinner.setValue(20);
		fontSizeSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				//this use the spinner to change the size of the font
				textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int)fontSizeSpinner.getValue()));
				
			}
		});
		
		fontColorButton = new JButton("Color");
		fontColorButton.addActionListener(this);
	
		
		
		//Get all available fonts in current in Java and assing to fonts array
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		//JComboBox let you add options to the stray combo box by passing in an array to this constructor.
		fontBox = new JComboBox(fonts);
		fontBox.addActionListener(this);
		fontBox.setSelectedItem("Comic Sans MS"); //set initial font
		
		//Menubar
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		aboutMenu = new JMenu("About");
		openItem = new JMenuItem("Open");
		saveItem = new JMenuItem("Save");
		exitItem = new JMenuItem("Exit");
		cutItem = new JMenuItem("Cut");
		copyItem = new JMenuItem("Copy");
		pasteItem = new JMenuItem("Paste");
		infoItem = new JMenuItem("Info");
		
		//adding action listeners to the items on file menu
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		exitItem.addActionListener(this);
		cutItem.addActionListener(this);
		copyItem.addActionListener(this);
		pasteItem.addActionListener(this);
		infoItem.addActionListener(this);
		
		//Layers here -> Adding all the tab items to the file menu. The file menu to the menuBar. And the menuBar to the frame.
		
		//tab itens to the file menu 
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
		
		//tab edit itens to edit menu
		editMenu.add(cutItem);
		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		
		//tab about item
		aboutMenu.add(infoItem);
		
		
		//menu tabs to the menuBar
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(aboutMenu);
		
		
		//Adding to the frame
		this.setJMenuBar(menuBar);
		this.add(fontLabel);
		this.add(fontSizeSpinner);
		this.add(fontColorButton);
		this.add(fontBox);
		this.add(scrollPane);
		this.setVisible(true);
	}
	
	//Making the UI work
	@Override
	public void actionPerformed(ActionEvent e) {
		//change font color
		if(e.getSource()==fontColorButton) {
			JColorChooser colorChooser = new JColorChooser();
			
			//color BOX | initial color in the box is pink 
			Color color = colorChooser.showDialog(null, "Choose a color", Color.PINK);
			textArea.setForeground(color);
		}
		
		//check the font and change it || keep the font size the same
		if(e.getSource()==fontBox) {
			textArea.setFont(new Font((String)fontBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
		}
		
		//File Menu OPEN | SAVE | EXIT
		if(e.getSource()==openItem) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			
			//add a filter to search for the file extension .txt
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
			fileChooser.setFileFilter(filter); //apply 
			
			//show the open window and return the file you selected to be opened.
			int response = fileChooser.showOpenDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				Scanner fileIn = null;
				
				try {
					fileIn = new Scanner(file);
					
					if(file.isFile()) { //check if file is real
						while(fileIn.hasNextLine()) { //reading the file line by line
							String line = fileIn.nextLine()+"\n"; 
							textArea.append(line); //show it on the text area
						}
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					//close scanner
					fileIn.close();
				}
			}
			
		}
		
		
		if(e.getSource()==saveItem) {
			 JFileChooser fileChooser = new JFileChooser();
			 
			 												//use a file path to where you wish to begin the file as default or
			 fileChooser.setCurrentDirectory(new File(".")); //"." to set default save place to the current project folder
			 
			 int response = fileChooser.showSaveDialog(null);
			 
			 if(response == JFileChooser.APPROVE_OPTION) {
				 File file;
				 PrintWriter fileOut = null;
				 
				 file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				 
				 try {
					fileOut = new PrintWriter(file);
					fileOut.println(textArea.getText());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally { //to close this try catch block
					fileOut.close();
					
				}
			 }
			 
		}
		
		
		if(e.getSource()==exitItem) {
			 System.exit(0);
		}
		
		//EditMenu
		
		if(e.getSource()==cutItem) {
			 System.exit(0);
	}
		if(e.getSource()==copyItem) {
			 System.exit(0);
	}
		if(e.getSource()==pasteItem) {
			 System.exit(0);
	}
		
	
	//About
		if(e.getSource()==infoItem) {
			 JOptionPane.showMessageDialog(this, "Created by Fernanda Silva - Text Editor made with JAVA  ");
	}
		
	
	}
}
