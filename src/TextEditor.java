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
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.ComponentOrientation;

public class TextEditor extends JFrame implements ActionListener {
	
	JTextArea textArea;
	JScrollPane scrollPane;
	JLabel fontLabel;
	JSpinner fontSizeSpinner; //for the user be able to change the font size
	Button fontColorButton; //user will change color for the font
	JComboBox fontBox; //user will be able to change fonts
	
	//menu bar variables
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenu editMenu;
	JMenu aboutMenu;
	JMenuItem newItem;
	JMenuItem openItem;
	JMenuItem saveItem;
	JMenuItem exitItem;
	JMenuItem cutItem;
	JMenuItem copyItem;
	JMenuItem pasteItem;
	JMenuItem infoItem;
	
	
	TextEditor(){
		setResizable(false);
		getContentPane().setBackground(new Color(199, 199, 216));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //click x on the corner to exit
		this.setTitle("FEFS Text Editor Project!");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null); //this will set the window to be at the middle of the screen
		
		
		textArea = new JTextArea();
		textArea.setBorder(null);
		textArea.setPreferredSize(new Dimension(450,450)); //no scroll
		textArea.setLineWrap(true); //this will let the text continue in the next line
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBackground(new Color(255, 255, 234));
		scrollPane.setBorder(null);
		scrollPane.setBounds(18, 36, 450, 450);
		scrollPane.setPreferredSize(new Dimension(450,450));
		
		fontLabel = new JLabel("Size:");
		fontLabel.setBackground(new Color(199, 199, 216));
		fontLabel.setFont(new Font("MoeumT R", Font.BOLD, 15));
		fontLabel.setBounds(179, 13, 40, 16);
		fontLabel.setForeground(new Color(102, 102, 147));
		
		fontSizeSpinner = new JSpinner();
		fontSizeSpinner.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		fontSizeSpinner.setForeground(new Color(102, 102, 147));
		fontSizeSpinner.setFont(new Font("MoeumT R", Font.BOLD, 12));
		fontSizeSpinner.setBackground(new Color(199, 199, 216));
		fontSizeSpinner.setBorder(null);
		fontSizeSpinner.setBounds(217, 12, 40, 16);
		fontSizeSpinner.setPreferredSize(new Dimension(50,25));
		fontSizeSpinner.setValue(20);
		fontSizeSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				//this use the spinner to change the size of the font
				textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int)fontSizeSpinner.getValue()));
				
			}
		});
		
		fontColorButton = new Button();
		fontColorButton.setBorderPainted(false);
		fontColorButton.setForeground(new Color(102, 102, 147));
		fontColorButton.setFont(new Font("MoeumT R", Font.BOLD, 15));
		fontColorButton.setBounds(18, 6, 101, 26);
		fontColorButton.setColorOver(new Color(167, 167, 211));
		fontColorButton.setColorClick(new Color(205, 205, 231));
		fontColorButton.setColor(new Color(255, 255, 255));
		fontColorButton.setBorderColor(new Color(102, 102, 147));
		fontColorButton.setBackground(new Color(199, 199, 216));
		fontColorButton.setRadius(25);
		fontColorButton.setText("Color");
		fontColorButton.addActionListener(this);
	
		
		
		//Get all available fonts in current in Java and assing to fonts array
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		//JComboBox let you add options to the stray combo box by passing in an array to this constructor.
		fontBox = new JComboBox(fonts);
		fontBox.setBackground(new Color(199, 199, 216));
		fontBox.setFont(new Font("MoeumT R", Font.BOLD, 12));
		fontBox.setForeground(new Color(102, 102, 147));
		fontBox.setBorder(null);
		fontBox.setBounds(317, 9, 151, 20);
		fontBox.addActionListener(this);
		fontBox.setSelectedItem("Comic Sans MS"); //set initial font
		
		//Menubar
		menuBar = new JMenuBar();
		menuBar.setBounds(new Rectangle(0, 0, 0, 20));
		menuBar.setMargin(new Insets(400, 0, 400, 0));
		menuBar.setFont(new Font("MoeumT R", Font.PLAIN, 15));
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setBorderPainted(false);
		menuBar.setBackground(new Color(102, 102, 147));
		menuBar.setBorder(null);
		fileMenu = new JMenu("File");
		fileMenu.setMargin(new Insets(10, 2, 10, 2));
		fileMenu.setFont(new Font("MoeumT R", Font.PLAIN, 15));
		fileMenu.setForeground(new Color(255, 255, 255));
		fileMenu.setBorder(null);
		fileMenu.setBackground(new Color(102, 102, 147));
		editMenu = new JMenu("Edit");
		editMenu.setMargin(new Insets(10, 2, 10, 2));
		editMenu.setFont(new Font("MoeumT R", Font.PLAIN, 15));
		editMenu.setForeground(new Color(255, 255, 255));
		editMenu.setBorder(null);
		editMenu.setBackground(new Color(102, 102, 147));
		aboutMenu = new JMenu("About");
		aboutMenu.setMargin(new Insets(10, 2, 10, 2));
		aboutMenu.setFont(new Font("MoeumT R", Font.PLAIN, 15));
		aboutMenu.setForeground(new Color(255, 255, 255));
		aboutMenu.setBorder(null);
		aboutMenu.setBackground(new Color(102, 102, 147));
		newItem = new JMenuItem("New");
		newItem.setMargin(new Insets(10, 2, 10, 2));
		newItem.setBorderPainted(false);
		newItem.setFont(new Font("MoeumT R", Font.PLAIN, 15));
		newItem.setForeground(new Color(255, 255, 255));
		newItem.setBorder(null);
		newItem.setBackground(new Color(102, 102, 147));
		openItem = new JMenuItem("Open");
		openItem.setMargin(new Insets(10, 2, 10, 2));
		openItem.setBorderPainted(false);
		openItem.setFont(new Font("MoeumT R", Font.PLAIN, 15));
		openItem.setForeground(new Color(255, 255, 255));
		openItem.setBorder(null);
		openItem.setBackground(new Color(102, 102, 147));
		saveItem = new JMenuItem("Save");
		saveItem.setMargin(new Insets(10, 2, 10, 2));
		saveItem.setBorderPainted(false);
		saveItem.setFont(new Font("MoeumT R", Font.PLAIN, 15));
		saveItem.setForeground(new Color(255, 255, 255));
		saveItem.setBorder(null);
		saveItem.setBackground(new Color(102, 102, 147));
		exitItem = new JMenuItem("Exit");
		exitItem.setMargin(new Insets(10, 2, 10, 2));
		exitItem.setBorderPainted(false);
		exitItem.setFont(new Font("MoeumT R", Font.PLAIN, 15));
		exitItem.setForeground(new Color(255, 255, 255));
		exitItem.setBorder(null);
		exitItem.setBackground(new Color(102, 102, 147));
		cutItem = new JMenuItem("Cut");
		cutItem.setMargin(new Insets(10, 2, 10, 2));
		cutItem.setBorderPainted(false);
		cutItem.setFont(new Font("MoeumT R", Font.PLAIN, 15));
		cutItem.setForeground(new Color(255, 255, 255));
		cutItem.setBorder(null);
		cutItem.setBackground(new Color(102, 102, 147));
		copyItem = new JMenuItem("Copy");
		copyItem.setMargin(new Insets(10, 2, 10, 2));
		copyItem.setBorderPainted(false);
		copyItem.setFont(new Font("MoeumT R", Font.PLAIN, 15));
		copyItem.setForeground(new Color(255, 255, 255));
		copyItem.setBorder(null);
		copyItem.setBackground(new Color(102, 102, 147));
		pasteItem = new JMenuItem("Paste");
		pasteItem.setMargin(new Insets(10, 2, 10, 2));
		pasteItem.setBorderPainted(false);
		pasteItem.setFont(new Font("MoeumT R", Font.PLAIN, 15));
		pasteItem.setForeground(new Color(255, 255, 255));
		pasteItem.setBorder(null);
		pasteItem.setBackground(new Color(102, 102, 147));
		infoItem = new JMenuItem("Info");
		infoItem.setMargin(new Insets(10, 2, 10, 2));
		infoItem.setBorderPainted(false);
		infoItem.setFont(new Font("MoeumT R", Font.PLAIN, 15));
		infoItem.setForeground(new Color(255, 255, 255));
		infoItem.setBorder(null);
		infoItem.setBackground(new Color(102, 102, 147));
		
		//adding action listeners to the items on file menu
		newItem.addActionListener(this);
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		exitItem.addActionListener(this);
		cutItem.addActionListener(this);
		copyItem.addActionListener(this);
		pasteItem.addActionListener(this);
		infoItem.addActionListener(this);
		
		//Layers here -> Adding all the tab items to the file menu. The file menu to the menuBar. And the menuBar to the frame.
		
		//tab itens to the file menu
		fileMenu.add(newItem);
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
		getContentPane().setLayout(null);
		getContentPane().add(fontLabel);
		getContentPane().add(fontSizeSpinner);
		getContentPane().add(fontColorButton);
		getContentPane().add(fontBox);
		getContentPane().add(scrollPane);
		
		JLabel lblFont = new JLabel("Font:");
		lblFont.setFont(new Font("MoeumT R", Font.BOLD, 15));
		lblFont.setForeground(new Color(102, 102, 147));
		lblFont.setBounds(279, 13, 40, 16);
		getContentPane().add(lblFont);
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
		
		//File Menu NEW | OPEN | SAVE | EXIT
		if(e.getSource()==newItem) {
			this.setTitle("Untitled.txt");
			textArea.setText(" ");
		}
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
		
		//EditMenu CUT | COPY | PASTE
		
		if(e.getSource()==cutItem) {
			 textArea.cut(); //JTextComponent
	}
		if(e.getSource()==copyItem) {
			 textArea.copy();
	}
		if(e.getSource()==pasteItem) {
			 textArea.paste();
	}
		
	
	//About
		if(e.getSource()==infoItem) {
			 JOptionPane.showMessageDialog(this, "Created by Fernanda Silva - Text Editor made with JAVA  ");
	}
		
	
	}
}
