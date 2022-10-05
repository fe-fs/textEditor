import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; //to be able to handle events

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TextEditor extends JFrame implements ActionListener {
	
	JTextArea textArea;
	JScrollPane scrollPane;
	JLabel fontLabel;
	JSpinner fontSizeSpinner; //for the user be able to change the font size
	JButton fontColorButton; //user will change color for the font
	JComboBox fontBox; //user will be able to change fonts
	
	//menu bar
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem openItem;
	JMenuItem saveItem;
	JMenuItem exitItem;
	
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
		openItem = new JMenuItem("Open");
		saveItem = new JMenuItem("Save");
		exitItem = new JMenuItem("Exit");
		
		//Layers here -> Adding all the menu itens to the menu. The menu to the menuBar. And the menuBar to the frame.
		
		//menu itens to the menu
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
		
		//menu to the menuBar
		menuBar.add(fileMenu);
		
		//Adding to the frame
		this.setJMenuBar(menuBar);
		this.add(fontLabel);
		this.add(fontSizeSpinner);
		this.add(fontColorButton);
		this.add(fontBox);
		this.add(scrollPane);
		this.setVisible(true);
	}
	
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
	}
	
	
}
