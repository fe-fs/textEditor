/*Class with custom setup for swing J button look
 * Contain radius setup, changes for click, over mouse colors
 * */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;


import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;


public class Button extends JButton {

	
	public boolean isOver() {
		return over;
	}
	public void setOver(boolean over) {
		this.over = over;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
		setBackground(color);
	}
	public Color getColorOver() {
		return colorOver;
	}
	public void setColorOver(Color colorOver) {
		this.colorOver = colorOver;
	}
	public Color getColorClick() {
		return colorClick;
	}
	public void setColorClick(Color colorClick) {
		this.colorClick = colorClick;
	}
	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	
	public Button() {
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		//Initial COLOR
		setColor(Color.WHITE);
		colorOver = new Color(198,174,203);
		colorClick = new Color(127,174,203);
		borderColor = new Color(150,174,203);
		setContentAreaFilled(false);
		
		//Mouse events
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent me) {
				setBackground(colorOver);
				over = true;
				}
			@Override
			public void mouseExited(MouseEvent me) {
				setBackground(color);
				over = false;
			}
			@Override
			public void mousePressed(MouseEvent me) {
				setBackground(colorClick);
			}
			@Override
			public void mouseReleased(MouseEvent me) {
				if(over) {
					setBackground(colorOver);
				}else {
					setBackground(color);
				}
			}
			
		});
		
	}

	private boolean over;
	private Color color;
	private Color colorOver;
	private Color colorClick;
	private Color borderColor;
	private int radius = 0;
	/**
	 * @wbp.nonvisual location=111,139
	 */
	private final JButton button = new JButton("New button");
	
	@Override
	protected void paintComponent(Graphics graphics) {
		Graphics2D g2=(Graphics2D)graphics;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		//Paint border
		g2.setColor(borderColor);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
		g2.setColor(getBackground());
		//Border 2px
		g2.fillRoundRect(1, 1, getWidth()-2, getHeight()-2, radius, radius);
		super.paintComponent(graphics);
		
	}
	
	
	
}
