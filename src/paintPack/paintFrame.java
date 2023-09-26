package paintPack;

import java.awt.Cursor;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTextField;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class paintFrame extends JFrame {

	public class Ecouteur implements MouseListener, ActionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		// to change the color
		@Override
		public void mouseReleased(MouseEvent e) {
			for (Component comp : panelColor.getComponents()) {
				if (comp instanceof JLabel)
					if (e.getSource() == comp)
						if (tglbtnColor1.isSelected())
							Color1.setBackground(comp.getBackground());
						else if (tglbtnColor2.isSelected())
							Color2.setBackground(comp.getBackground());

			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		// to change icon cursor
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JToggleButton && e.getSource() != tglbtnColor1
					&& e.getSource() != tglbtnColor2) {
				monCurseur = tk.createCustomCursor(((ImageIcon) ((AbstractButton) e.getSource()).getIcon()).getImage(),
						new Point(10, 26), "ok");
				rootPane.setCursor(monCurseur);
			}

			// to change stroke
			if (e.getSource() == txtFdLargeur) {
				if (!txtFdLargeur.getText().isEmpty()) {
					int largeur = Integer.parseInt(txtFdLargeur.getText());

					if (largeur <= 100)
						panel.setStroke(largeur);
					else
						txtFdLargeur.setText(Integer.toString(panel.getStroke()));

				} else
					txtFdLargeur.setText(Integer.toString(panel.getStroke()));

			}
			// lthe save button
			if (e.getSource() == save) {

				try {
					ImageIO.write(panel.recupererImage(panel), "PNG", new File("beauDessin.png"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	}

	private class deleteThis extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			panel.annuler();
		}

	}

	Cursor monCurseur;
	Toolkit tk = Toolkit.getDefaultToolkit();

	private JPanel contentPane;
	private JToggleButton crayon;
	private JToggleButton efface;
	private JToggleButton rectangle;
	private JToggleButton fill;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					paintFrame frame = new paintFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private ButtonGroup buttonGroupOutilsEtFormes;
	private ButtonGroup buttonGroupColor;
	private JPanel panelColor;
	private JLabel labelRed;
	private JLabel labelPink;
	private JLabel labelOrange;
	private JLabel labelCyan;
	private JLabel labelBlue;
	private JLabel labelMagenta;
	private JLabel labelYellow;
	private JLabel labelBlack;
	private JLabel labelGreen;
	private JLabel labelWhite;
	private drawPanel panel;
	private JTextField txtFdLargeur;
	private JPanel panelOutils;
	private JPanel panelFormes;
	private JToggleButton circle;
	private JToggleButton triangle;
	private JToggleButton pipette;
	private JPanel panelColorSelected;
	private JToggleButton tglbtnColor2;
	private JLabel Color2;
	private JToggleButton tglbtnColor1;
	private JLabel Color1;
	private JButton save;
	private JLabel tailleTrait;

	public paintFrame() {

		setTitle("Paint 0.01");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new drawPanel();
		panel.setBounds(10, 87, 904, 543);
		getContentPane().add(panel);
		panel.setLayout(null);

	

		panel.setFocusable(true);

		panelColor = new JPanel();
		panelColor.setBounds(686, 10, 228, 65);
		contentPane.add(panelColor);
		panelColor.setLayout(new GridLayout(2, 2, 2, 2));

		labelPink = new JLabel("");
		labelPink.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelPink.setBackground(Color.PINK);
		labelPink.setOpaque(true);
		panelColor.add(labelPink);

		labelYellow = new JLabel("");
		labelYellow.setOpaque(true);
		labelYellow.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelYellow.setBackground(Color.YELLOW);
		panelColor.add(labelYellow);

		labelMagenta = new JLabel("");
		labelMagenta.setOpaque(true);
		labelMagenta.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelMagenta.setBackground(Color.MAGENTA);
		panelColor.add(labelMagenta);

		labelBlue = new JLabel("");
		labelBlue.setOpaque(true);
		labelBlue.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelBlue.setBackground(Color.BLUE);
		panelColor.add(labelBlue);

		labelRed = new JLabel("");
		labelRed.setBackground(Color.RED);
		labelRed.setOpaque(true);
		labelRed.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelColor.add(labelRed);

		labelOrange = new JLabel("");
		labelOrange.setOpaque(true);
		labelOrange.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelOrange.setBackground(Color.ORANGE);
		panelColor.add(labelOrange);

		labelGreen = new JLabel("");
		labelGreen.setOpaque(true);
		labelGreen.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelGreen.setBackground(Color.GREEN);
		panelColor.add(labelGreen);

		labelCyan = new JLabel("");
		labelCyan.setOpaque(true);
		labelCyan.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelCyan.setBackground(Color.CYAN);
		panelColor.add(labelCyan);

		labelBlack = new JLabel("");
		labelBlack.setOpaque(true);
		labelBlack.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelBlack.setBackground(Color.BLACK);
		panelColor.add(labelBlack);

		labelWhite = new JLabel("");
		labelWhite.setOpaque(true);
		labelWhite.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelWhite.setBackground(Color.WHITE);
		panelColor.add(labelWhite);

		txtFdLargeur = new JTextField();
		txtFdLargeur.setText("10");
		// https://stackoverflow.com/questions/20541230/allow-only-numbers-in-jtextfield
		// to only accept ints
		((AbstractDocument) txtFdLargeur.getDocument()).setDocumentFilter(new DocumentFilter() {
			Pattern regEx = Pattern.compile("\\d*");

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				Matcher matcher = regEx.matcher(text);
				if (!matcher.matches()) {
					return;
				}
				super.replace(fb, offset, length, text, attrs);
			}
		});
		txtFdLargeur.setBounds(146, 55, 38, 20);
		contentPane.add(txtFdLargeur);
		txtFdLargeur.setColumns(10);

		panelOutils = new JPanel();
		panelOutils.setBounds(10, 11, 220, 33);
		contentPane.add(panelOutils);
		panelOutils.setLayout(new GridLayout(0, 4, 8, 0));

		crayon = new JToggleButton("");
		crayon.setSelected(true);
		panelOutils.add(crayon);
		crayon.setIcon(new ImageIcon("icones/crayon.gif"));

		efface = new JToggleButton("");
		efface.setIcon(new ImageIcon("icones/efface.gif"));
		panelOutils.add(efface);

		pipette = new JToggleButton("");
		pipette.setIcon(new ImageIcon("icones/pipette.gif"));
		panelOutils.add(pipette);

		fill = new JToggleButton("");
		fill.setIcon(new ImageIcon("icones/remplissage.gif"));
		panelOutils.add(fill);

		panelFormes = new JPanel();
		panelFormes.setBounds(367, 42, 162, 33);
		contentPane.add(panelFormes);
		panelFormes.setLayout(new GridLayout(0, 3, 8, 0));

		rectangle = new JToggleButton("");
		rectangle.setIcon(new ImageIcon("icones/rectangle.gif"));
		panelFormes.add(rectangle);

		circle = new JToggleButton("");
		circle.setIcon(new ImageIcon("icones/cercle.gif"));
		panelFormes.add(circle);

		triangle = new JToggleButton("");
		triangle.setIcon(new ImageIcon("icones/triangle.gif"));
		panelFormes.add(triangle);

		panelColorSelected = new JPanel();
		panelColorSelected.setBounds(539, 10, 137, 65);
		contentPane.add(panelColorSelected);
		panelColorSelected.setLayout(new GridLayout(0, 2, 0, 0));

		tglbtnColor1 = new JToggleButton("Couleur 1");
		tglbtnColor1.setSelected(true);
		tglbtnColor1.setFont(new Font("Dialog", Font.PLAIN, 8));
		tglbtnColor1.setVerticalAlignment(SwingConstants.BOTTOM);
		tglbtnColor1.setLayout(new FlowLayout());
		panelColorSelected.add(tglbtnColor1);

		Color1 = new JLabel("");
		Color1.setPreferredSize(new Dimension(50, 40));
		Color1.setOpaque(true);
		Color1.setBackground(new Color(0, 0, 0));
		tglbtnColor1.add(Color1);

		tglbtnColor2 = new JToggleButton("Couleur 2");
		tglbtnColor2.setFont(new Font("Dialog", Font.PLAIN, 8));
		tglbtnColor2.setVerticalAlignment(SwingConstants.BOTTOM);
		tglbtnColor2.setLayout(new FlowLayout());
		panelColorSelected.add(tglbtnColor2);

		Color2 = new JLabel("");
		Color2.setPreferredSize(new Dimension(50, 40));
		Color2.setOpaque(true);
		Color2.setBackground(new Color(255, 255, 255));
		tglbtnColor2.add(Color2);

		save = new JButton("");
		save.setBounds(277, 11, 49, 33);
		save.setIcon(new ImageIcon("icones/save.gif"));
		contentPane.add(save);

		tailleTrait = new JLabel("Taille du trait :");
		tailleTrait.setBounds(55, 58, 81, 14);
		contentPane.add(tailleTrait);

		panel.requestFocusInWindow();

		// set the cursor to pencil
		monCurseur = tk.createCustomCursor(((ImageIcon) crayon.getIcon()).getImage(), new Point(10, 26), "");
		rootPane.setCursor(monCurseur);

		var e = new Ecouteur();

		save.addActionListener(e);
		txtFdLargeur.addActionListener(e);

		// add event listener to all panelColor components
		for (Component comp : panelColor.getComponents())
			// only adds event listener to JLabel
			if (comp instanceof JLabel)

				comp.addMouseListener(e);

		buttonGroupOutilsEtFormes = new ButtonGroup();
		for (Component comp : panelOutils.getComponents())
			if (comp instanceof JToggleButton) {
				buttonGroupOutilsEtFormes.add((JToggleButton) comp);
				comp.addMouseListener(e);
				((JToggleButton) comp).addActionListener(e);
			}

		for (Component comp : panelFormes.getComponents())
			if (comp instanceof JToggleButton) {
				buttonGroupOutilsEtFormes.add((JToggleButton) comp);
				comp.addMouseListener(e);
				((JToggleButton) comp).addActionListener(e);
			}

		buttonGroupColor = new ButtonGroup();
		for (Component comp : panelColorSelected.getComponents())
			if (comp instanceof JToggleButton) {
				buttonGroupColor.add((JToggleButton) comp);
				comp.addMouseListener(e);
			}

		// merci pour l'aide
		// set le binding du control+z
		var keys = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keys, "deleteThis");
		getRootPane().getActionMap().put("deleteThis", new deleteThis());

	}

	private class drawPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		private Color color1 = Color.black;
		private int stroke = 10;
		private Vector<figureGeometrique> VectFigure = new Vector<figureGeometrique>();

		// pour la pipette et le save
		public BufferedImage recupererImage(JPanel pan) {
			Dimension size = pan.getSize();
			BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = image.createGraphics();
			pan.paint(g2);
			return image;
		}

		// pour la pipette
		public Color utiliserPipette(JPanel pan, Point p) {
			return new Color(recupererImage(pan).getRGB(p.x, p.y));
		}

		public class EcouteurPanel implements MouseMotionListener, MouseListener {

			// set le deuxieme point de la figure geometrique
			@Override
			public void mouseDragged(MouseEvent e) {

				if (rectangle.isSelected() || circle.isSelected() || triangle.isSelected() || crayon.isSelected()
						|| efface.isSelected()) {
					VectFigure.elementAt(VectFigure.size() - 1).setP2(new Point(e.getX(), e.getY()));
					repaint();
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			// creates a new figureGeometrique on click
			@Override
			public void mousePressed(MouseEvent e) {

				if (e.getButton() == MouseEvent.BUTTON1) {
					setColor1(Color1.getBackground());
				} else if (e.getButton() == MouseEvent.BUTTON3) {
					setColor1(Color2.getBackground());
				}
				if (rectangle.isSelected())
					VectFigure.add(new rectangle(new Point(e.getX(), e.getY()), color1, stroke));
				else if (circle.isSelected())
					VectFigure.add(new circle(new Point(e.getX(), e.getY()), color1, stroke));
				else if (crayon.isSelected())
					VectFigure.add(new crayon(new Point(e.getX(), e.getY()), color1, stroke));
				else if (triangle.isSelected())
					VectFigure.add(new triangle(new Point(e.getX(), e.getY()), color1, stroke));
				else if (efface.isSelected())
					VectFigure.add(new efface(new Point(e.getX(), e.getY()), Color2.getBackground(), stroke));
				else if (fill.isSelected()) {
					panel.setBackground(color1);

					for (int i = 0; i < VectFigure.size(); ++i)
						((figureGeometrique) VectFigure.elementAt(i)).setColorFill(color1);

				}
				repaint();

			}

			// the pipette
			@Override
			public void mouseReleased(MouseEvent e) {
				if (pipette.isSelected()) {
					Color couleur = panel.utiliserPipette(panel, new Point(e.getX(), e.getY()));

					if (e.getButton() == MouseEvent.BUTTON1) {
						Color1.setBackground(couleur);
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						Color2.setBackground(couleur);
					}
				
					crayon.setSelected(true);
					monCurseur = tk.createCustomCursor(((ImageIcon) crayon.getIcon()).getImage(), new Point(10, 26),
							"");
					rootPane.setCursor(monCurseur);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		}

		public drawPanel() {
			this.setBackground(Color.white);

			
			setFocusable(true);
			setLayout(null);

			requestFocusInWindow();

			var e = new EcouteurPanel();

			addMouseMotionListener(e);
			addMouseListener(e);

		}

		// Draw the vector
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int i = 0; i < VectFigure.size(); ++i)
				VectFigure.elementAt(i).drawFigure(g);
		}

		public void setColor1(Color c) {
			color1 = c;
		}

		public void setStroke(int s) {
			stroke = s;
		}

		public int getStroke() {
			return stroke;
		}

		// for ctrl+z
		public void annuler() {
			if (VectFigure.size() > 0) {
				VectFigure.remove(VectFigure.size() - 1);
				repaint();
			}
		}
	}
}
