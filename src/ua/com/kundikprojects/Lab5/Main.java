package ua.com.kundikprojects.Lab5;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Main {
	private int type = 0;
	private int xPad;
	private int xf;
	private int yf;
	private int yPad;
	private int size = 10;
	private boolean pressed = false;
	// current color
	private Color maincolor;
	private MyFrame f;
	private MyPanel japan;
	private JButton colorbutton;
	private JColorChooser tcc;
	private BufferedImage imag;
	private boolean loading = false;
	private String fileName;
	private Graphics g;// = imag.getGraphics();
	private Graphics2D g2;

	private Main() {
		f = new MyFrame("Graphic editor");
		f.setSize(2000, 2000);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		maincolor = Color.black;

		japan = new MyPanel();
		japan.setBounds(f.getWidth() / 20, f.getHeight() / 20, f.getWidth() / 10, f.getHeight() / 10);
		japan.setBackground(Color.white);
		japan.setOpaque(true);
		f.add(japan);

		Font font = new Font("Verdana", Font.BOLD, f.getWidth() / 150);
		JMenuBar menuBar = new JMenuBar();
		f.setJMenuBar(menuBar);
		menuBar.setBounds(0, 0, 350, 30);
		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(font);
		menuBar.add(fileMenu);

		JMenu sizeMenu = new JMenu("Size");
		sizeMenu.setFont(font);
		menuBar.add(sizeMenu);

		JMenu viewMenu = new JMenu("View");
		viewMenu.setFont(font);
		menuBar.add(viewMenu);

		Action clearAction = new AbstractAction("Clear") {
			public void actionPerformed(ActionEvent event) {
				Rectangle2D.Double rect1 = new Rectangle2D.Double(0, 0, (japan.getWidth()), (japan.getHeight()));
				g.setColor(Color.WHITE);
				g2.fill(rect1);
				japan.repaint();
			}
		};

		JMenuItem clearMenu = new JMenuItem(clearAction);
		clearMenu.setFont(font);
		viewMenu.add(clearMenu);

		Action smAction = new AbstractAction("Small") {
			public void actionPerformed(ActionEvent event) {
				size = 10;

			}
		};
		JMenuItem smMenu = new JMenuItem(smAction);
		sizeMenu.setFont(font);
		sizeMenu.add(smMenu);

		Action midAction = new AbstractAction("Medium") {
			public void actionPerformed(ActionEvent event) {
				size = 30;
			}
		};
		JMenuItem midMenu = new JMenuItem(midAction);
		sizeMenu.setFont(font);
		sizeMenu.add(midMenu);

		Action bigAction = new AbstractAction("Big") {
			public void actionPerformed(ActionEvent event) {
				size = 60;
			}
		};
		JMenuItem bigMenu = new JMenuItem(bigAction);
		sizeMenu.setFont(font);
		sizeMenu.add(bigMenu);

		Action loadAction = new AbstractAction("Open") {
			public void actionPerformed(ActionEvent event) {
				JFileChooser jf = new JFileChooser();
				int result = jf.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						// the frame size depends on the picture size
						fileName = jf.getSelectedFile().getAbsolutePath();
						File iF = new File(fileName);
						jf.addChoosableFileFilter(new TextFileFilter(".png"));
						jf.addChoosableFileFilter(new TextFileFilter(".jpg"));
						imag = ImageIO.read(iF);
						loading = true;
						f.setSize(imag.getWidth() + 40, imag.getWidth() + 80);
						japan.setSize(imag.getWidth(), imag.getWidth());
						japan.repaint();
					} catch (FileNotFoundException ex) {
						JOptionPane.showMessageDialog(f, "This file doesn't exist");
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(f, "The exception of input/output");
					} catch (Exception ignored) {
					}
				}
			}
		};
		JMenuItem loadMenu = new JMenuItem(loadAction);
		loadMenu.setFont(font);
		fileMenu.add(loadMenu);

		Action saveAction = new AbstractAction("Save") {
			public void actionPerformed(ActionEvent event) {
				try {
					JFileChooser jf = new JFileChooser();
					// Creating file filters
					TextFileFilter pngFilter = new TextFileFilter(".png");
					TextFileFilter jpgFilter = new TextFileFilter(".jpg");
					if (fileName == null) {
						// Adding filters
						jf.addChoosableFileFilter(pngFilter);
						jf.addChoosableFileFilter(jpgFilter);
						int result = jf.showSaveDialog(null);
						if (result == JFileChooser.APPROVE_OPTION) {
							fileName = jf.getSelectedFile().getAbsolutePath();
						}
					}
					// check which filter was choosen
					if (jf.getFileFilter() == pngFilter) {
						ImageIO.write(imag, "png", new File(fileName + ".png"));
					} else {
						ImageIO.write(imag, "jpeg", new File(fileName + ".jpg"));
					}
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(f, "Error of input/output");
				}
			}
		};
		JMenuItem saveMenu = new JMenuItem(saveAction);
		saveMenu.setFont(font);
		fileMenu.add(saveMenu);

		Action saveasAction = new AbstractAction("Save as...") {
			public void actionPerformed(ActionEvent event) {
				try {
					JFileChooser jf = new JFileChooser();
					// Creating filters
					TextFileFilter pngFilter = new TextFileFilter(".png");
					TextFileFilter jpgFilter = new TextFileFilter(".jpg");
					// Adding filters
					jf.addChoosableFileFilter(pngFilter);
					jf.addChoosableFileFilter(jpgFilter);
					int result = jf.showSaveDialog(null);
					if (result == JFileChooser.APPROVE_OPTION) {
						fileName = jf.getSelectedFile().getAbsolutePath();
					}
					// check which filter was choosen
					if (jf.getFileFilter() == pngFilter) {
						ImageIO.write(imag, "png", new File(fileName + ".png"));
					} else {
						ImageIO.write(imag, "jpeg", new File(fileName + ".jpg"));
					}
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(f, "Erro of input/output");
				}
			}
		};
		JMenuItem saveasMenu = new JMenuItem(saveasAction);
		saveasMenu.setFont(font);
		fileMenu.add(saveasMenu);

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setFont(font);
		menuBar.add(exitItem);

		exitItem.addActionListener(e -> System.exit(0));

		JToolBar toolbar = new JToolBar("Toolbar", JToolBar.VERTICAL);

		ImageIcon pen = new ImageIcon("/Users/kyrylo_kundik/IdeaProjects/PracticeOOP/src/ua/com/kundikprojects/Lab5/assets/images/pen.png");
		Image image = pen.getImage();
		Image newimage = image.getScaledInstance(f.getHeight() / 30, f.getHeight() / 30, Image.SCALE_SMOOTH);
		pen = new ImageIcon(newimage);
		JButton penbutton = new JButton(pen);
		penbutton.addActionListener(event -> type = 0);
		toolbar.add(penbutton);
		ImageIcon brush = new ImageIcon("/Users/kyrylo_kundik/IdeaProjects/PracticeOOP/src/ua/com/kundikprojects/Lab5/assets/images/brush.png");
		image = brush.getImage();
		newimage = image.getScaledInstance(f.getHeight() / 30, f.getHeight() / 30, Image.SCALE_SMOOTH);
		brush = new ImageIcon(newimage);
		JButton brushbutton = new JButton(brush);
		brushbutton.addActionListener(event -> type = 1);
		toolbar.add(brushbutton);

		ImageIcon lastic = new ImageIcon("/Users/kyrylo_kundik/IdeaProjects/PracticeOOP/src/ua/com/kundikprojects/Lab5/assets/images/eraser.jpg");
		image = lastic.getImage();
		newimage = image.getScaledInstance(f.getHeight() / 30, f.getHeight() / 30, Image.SCALE_SMOOTH);
		lastic = new ImageIcon(newimage);
		JButton lasticbutton = new JButton(lastic);
		lasticbutton.addActionListener(event -> type = 2);
		toolbar.add(lasticbutton);

		ImageIcon text = new ImageIcon("/Users/kyrylo_kundik/IdeaProjects/PracticeOOP/src/ua/com/kundikprojects/Lab5/assets/images/letter.png");
		image = text.getImage();
		newimage = image.getScaledInstance(f.getHeight() / 30, f.getHeight() / 30, Image.SCALE_SMOOTH);
		text = new ImageIcon(newimage);
		JButton textbutton = new JButton(text);
		textbutton.addActionListener(event -> type = 3);
		toolbar.add(textbutton);

		ImageIcon line = new ImageIcon("/Users/kyrylo_kundik/IdeaProjects/PracticeOOP/src/ua/com/kundikprojects/Lab5/assets/images/line.png");
		image = line.getImage();
		newimage = image.getScaledInstance(f.getHeight() / 30, f.getHeight() / 30, Image.SCALE_SMOOTH);
		line = new ImageIcon(newimage);
		JButton linebutton = new JButton(line);
		linebutton.addActionListener(event -> type = 4);
		toolbar.add(linebutton);

		ImageIcon elips = new ImageIcon("/Users/kyrylo_kundik/IdeaProjects/PracticeOOP/src/ua/com/kundikprojects/Lab5/assets/images/circle.png");
		image = elips.getImage();
		newimage = image.getScaledInstance(f.getHeight() / 30, f.getHeight() / 30, Image.SCALE_SMOOTH);
		elips = new ImageIcon(newimage);
		JButton elipsbutton = new JButton(elips);
		elipsbutton.addActionListener(event -> type = 5);
		toolbar.add(elipsbutton);

		ImageIcon felips = new ImageIcon("/Users/kyrylo_kundik/IdeaProjects/PracticeOOP/src/ua/com/kundikprojects/Lab5/assets/images/filledcircle.png");
		image = felips.getImage();
		newimage = image.getScaledInstance(f.getHeight() / 30, f.getHeight() / 30, Image.SCALE_SMOOTH);
		felips = new ImageIcon(newimage);
		JButton felipsbutton = new JButton(felips);
		felipsbutton.addActionListener(event -> type = 6);
		toolbar.add(felipsbutton);

		ImageIcon rect = new ImageIcon("/Users/kyrylo_kundik/IdeaProjects/PracticeOOP/src/ua/com/kundikprojects/Lab5/assets/images/rectangle.png");
		image = rect.getImage();
		newimage = image.getScaledInstance(f.getHeight() / 30, f.getHeight() / 30, Image.SCALE_SMOOTH);
		rect = new ImageIcon(newimage);
		JButton rectbutton = new JButton(rect);
		rectbutton.addActionListener(event -> type = 7);
		toolbar.add(rectbutton);

		ImageIcon frect = new ImageIcon("/Users/kyrylo_kundik/IdeaProjects/PracticeOOP/src/ua/com/kundikprojects/Lab5/assets/images/filledrect.png");
		image = frect.getImage();
		newimage = image.getScaledInstance(f.getHeight() / 30, f.getHeight() / 30, Image.SCALE_SMOOTH);
		frect = new ImageIcon(newimage);
		JButton frectbutton = new JButton(frect);
		frectbutton.addActionListener(event -> type = 8);
		toolbar.add(frectbutton);

		toolbar.setBounds(0, f.getHeight() / 20, f.getWidth() / 20, f.getHeight());
		f.add(toolbar);

		// Toolbar for buttons
		JToolBar colorbar = new JToolBar("Colorbar", JToolBar.HORIZONTAL);
		colorbar.setBounds(f.getWidth() / 20, 0, f.getWidth(), f.getHeight() / 20);
		colorbutton = new JButton();
		colorbutton.setBackground(maincolor);
		colorbutton.setBounds(15, 5, (int) (colorbar.getHeight() * 0.9), (int) (colorbar.getHeight() * 0.9));
		colorbutton.addActionListener(event -> {
		});
		colorbar.add(colorbutton);

		JButton redbutton = new JButton();
		redbutton.setBackground(Color.red);
		redbutton.setBounds(colorbutton.getX() + colorbutton.getWidth() + colorbar.getHeight() / 10, 5,
				(int) (colorbar.getHeight() * 0.6), (int) (colorbar.getHeight() * 0.6));
		redbutton.addActionListener(event -> {
			maincolor = Color.red;
			colorbutton.setBackground(maincolor);
		});
		colorbar.add(redbutton);

		JButton orangebutton = new JButton();
		orangebutton.setBackground(Color.orange);
		orangebutton.setBounds(redbutton.getX() + redbutton.getWidth() + colorbar.getHeight() / 10, 5,
				(int) (colorbar.getHeight() * 0.6), (int) (colorbar.getHeight() * 0.6));
		orangebutton.addActionListener(event -> {
			maincolor = Color.orange;
			colorbutton.setBackground(maincolor);
		});
		colorbar.add(orangebutton);

		JButton yellowbutton = new JButton();
		yellowbutton.setBackground(Color.yellow);
		yellowbutton.setBounds(orangebutton.getX() + orangebutton.getWidth() + colorbar.getHeight() / 10, 5,
				(int) (colorbar.getHeight() * 0.6), (int) (colorbar.getHeight() * 0.6));
		yellowbutton.addActionListener(event -> {
			maincolor = Color.yellow;
			colorbutton.setBackground(maincolor);
		});
		colorbar.add(yellowbutton);

		JButton greenbutton = new JButton();
		greenbutton.setBackground(Color.green);
		greenbutton.setBounds(yellowbutton.getX() + yellowbutton.getWidth() + colorbar.getHeight() / 10, 5,
				(int) (colorbar.getHeight() * 0.6), (int) (colorbar.getHeight() * 0.6));
		greenbutton.addActionListener(event -> {
			maincolor = Color.green;
			colorbutton.setBackground(maincolor);
		});
		colorbar.add(greenbutton);

		JButton bluebutton = new JButton();
		bluebutton.setBackground(Color.blue);
		bluebutton.setBounds(greenbutton.getX() + greenbutton.getWidth() + colorbar.getHeight() / 10, 5,
				(int) (colorbar.getHeight() * 0.6), (int) (colorbar.getHeight() * 0.6));
		bluebutton.addActionListener(event -> {
			maincolor = Color.blue;
			colorbutton.setBackground(maincolor);
		});
		colorbar.add(bluebutton);

		JButton cyanbutton = new JButton();
		cyanbutton.setBackground(Color.cyan);
		cyanbutton.setBounds(bluebutton.getX() + bluebutton.getWidth() + colorbar.getHeight() / 10, 5,
				(int) (colorbar.getHeight() * 0.6), (int) (colorbar.getHeight() * 0.6));
		cyanbutton.addActionListener(event -> {
			maincolor = Color.cyan;
			colorbutton.setBackground(maincolor);
		});
		colorbar.add(cyanbutton);

		JButton magentabutton = new JButton();
		magentabutton.setBackground(Color.magenta);
		magentabutton.setBounds(cyanbutton.getX() + cyanbutton.getWidth() + colorbar.getHeight() / 10, 5,
				(int) (colorbar.getHeight() * 0.6), (int) (colorbar.getHeight() * 0.6));
		magentabutton.addActionListener(event -> {
			maincolor = Color.magenta;
			colorbutton.setBackground(maincolor);
		});
		colorbar.add(magentabutton);

		JButton whitebutton = new JButton();
		whitebutton.setBackground(Color.white);
		whitebutton.setBounds(magentabutton.getX() + magentabutton.getWidth() + colorbar.getHeight() / 10, 5,
				(int) (colorbar.getHeight() * 0.6), (int) (colorbar.getHeight() * 0.6));
		whitebutton.addActionListener(event -> {
			maincolor = Color.white;
			colorbutton.setBackground(maincolor);
		});
		colorbar.add(whitebutton);

		JButton blackbutton = new JButton();
		blackbutton.setBackground(Color.black);
		blackbutton.setBounds(whitebutton.getX() + whitebutton.getWidth() + colorbar.getHeight() / 10, 5,
				(int) (colorbar.getHeight() * 0.6), (int) (colorbar.getHeight() * 0.6));
		blackbutton.addActionListener(event -> {
			maincolor = Color.black;
			colorbutton.setBackground(maincolor);
		});
		colorbar.add(blackbutton);
		colorbar.setLayout(null);
		f.add(colorbar);

		JButton graybutton = new JButton();
		graybutton.setBackground(Color.gray);
		graybutton.setBounds(blackbutton.getX() + blackbutton.getWidth() + colorbar.getHeight() / 10, 5,
				(int) (colorbar.getHeight() * 0.6), (int) (colorbar.getHeight() * 0.6));
		graybutton.addActionListener(event -> {
			maincolor = Color.gray;
			colorbutton.setBackground(maincolor);
		});
		colorbar.add(graybutton);

		JButton pinkbutton = new JButton();
		pinkbutton.setBackground(Color.PINK);
		pinkbutton.setBounds(graybutton.getX() + graybutton.getWidth() + colorbar.getHeight() / 10, 5,
				(int) (colorbar.getHeight() * 0.6), (int) (colorbar.getHeight() * 0.6));
		pinkbutton.addActionListener(event -> {
			maincolor = Color.pink;
			colorbutton.setBackground(maincolor);
		});
		colorbar.add(pinkbutton);

		colorbar.setLayout(null);
		f.add(colorbar);

		tcc = new JColorChooser(maincolor);
		tcc.getSelectionModel().addChangeListener(e -> {
			maincolor = tcc.getColor();
			colorbutton.setBackground(maincolor);
		});

		japan.addMouseWheelListener(new MouseAdapter() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				int x = e.getWheelRotation();
				if ((x > 0) && (size < 40)) {
					size += 2;
				}

				if ((x < 0) && (size > 2)) {
					size -= 2;
				}
			}
		});

		japan.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (pressed) {
					Graphics g = imag.getGraphics();
					Graphics2D g2 = (Graphics2D) g;
					// choosing the color
					g2.setColor(maincolor);
					switch (type) {
					// pencil
					case 0:

						g2.drawLine(xPad, yPad, e.getX(), e.getY());
						break;
					// brush
					case 1:
						japan.addMouseWheelListener(new MouseAdapter() {
						});

						g2.setStroke(new BasicStroke(size));
						g2.drawLine(xPad, yPad, e.getX(), e.getY());
						break;
					// eraser
					case 2:
						g2.setStroke(new BasicStroke(size));
						g2.setColor(Color.WHITE);
						g2.drawLine(xPad, yPad, e.getX(), e.getY());
						break;
					}
					xPad = e.getX();
					yPad = e.getY();
				}
				japan.repaint();
			}
		});

		japan.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				Graphics g = imag.getGraphics();
				Graphics2D g2 = (Graphics2D) g;
				// choosing the color
				g2.setColor(maincolor);
				switch (type) {
				// pencil
				case 0:
					g2.drawLine(xPad, yPad, xPad + 1, yPad + 1);
					break;
				// brush
				case 1:
					g2.setStroke(new BasicStroke(size));
					g2.drawLine(xPad, yPad, xPad + 1, yPad + 1);
					break;
				// eraser
				case 2:
					g2.setStroke(new BasicStroke(3.0f));
					g2.setColor(Color.WHITE);
					g2.drawLine(xPad, yPad, xPad + 1, yPad + 1);
					break;
				// text
				case 3:
					// focusing on the panel to add text
					japan.requestFocus();
					break;
				}
				xPad = e.getX();
				yPad = e.getY();

				pressed = true;
				japan.repaint();
			}

			public void mousePressed(MouseEvent e) {
				xPad = e.getX();
				yPad = e.getY();
				xf = e.getX();
				yf = e.getY();
				pressed = true;
			}

			public void mouseReleased(MouseEvent e) {

				g = imag.getGraphics();
				g2 = (Graphics2D) g;
				// choosing the color
				g2.setColor(maincolor);
				// General counting for oval and rectangel
				int x1 = xf, x2 = xPad, y1 = yf, y2 = yPad;
				if (xf > xPad) {
					x2 = xf;
					x1 = xPad;
				}
				if (yf > yPad) {
					y2 = yf;
					y1 = yPad;
				}
				switch (type) {
				// line
				case 4:
					g.drawLine(xf, yf, e.getX(), e.getY());
					break;
				// circle
				case 5:
					g.drawOval(x1, y1, (x2 - x1), (y2 - y1));
					break;
				case 6:
					Ellipse2D.Double elip1 = new Ellipse2D.Double(x1, y1, (x2 - x1), (y2 - y1));
					g2.fill(elip1);
					break;
				case 7:
					g.drawRect(x1, y1, (x2 - x1), (y2 - y1));
					break;
				// rectangle
				case 8:
					Rectangle2D.Double rect1 = new Rectangle2D.Double(x1, y1, (x2 - x1), (y2 - y1));
					g2.fill(rect1);
					break;
				}
				xf = 0;
				yf = 0;
				pressed = false;
				japan.repaint();
			}
		});
		japan.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				// focusing on the panel to add text
				japan.requestFocus();
			}

			public void keyTyped(KeyEvent e) {
				if (type == 3) {
					Graphics g = imag.getGraphics();
					Graphics2D g2 = (Graphics2D) g;
					// choosing color
					g2.setColor(maincolor);
					g2.setStroke(new BasicStroke(2.0f));

					String str = "";
					str += e.getKeyChar();
					g2.setFont(new Font("Arial", Font.PLAIN, 15));
					g2.drawString(str, xPad, yPad);
					xPad += 10;
					// focusing on the panel to add text
					japan.requestFocus();
					japan.repaint();
				}
			}
		});
		f.addComponentListener(new ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent evt) {
				// if loading then change the size in the code of loading
				if (!loading) {
					japan.setSize(f.getWidth() - 40, f.getHeight() - 80);
					BufferedImage tempImage = new BufferedImage(japan.getWidth(), japan.getHeight(),
							BufferedImage.TYPE_INT_RGB);
					Graphics2D d2 = tempImage.createGraphics();
					d2.setColor(Color.white);
					d2.fillRect(0, 0, japan.getWidth(), japan.getHeight());
					tempImage.setData(imag.getRaster());
					imag = tempImage;
					japan.repaint();
				}
				loading = false;
			}
		});
		f.setLayout(null);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		// String path = "src//res//pen.png";
		// Main.class.getResourceAsStream(path);
		SwingUtilities.invokeLater(Main::new);
	}

	class MyFrame extends JFrame {
		public void paint(Graphics g) {
			super.paint(g);
		}

		MyFrame(String title) {
			super(title);
		}
	}

	class MyPanel extends JPanel {
		MyPanel() {
		}

		public void paintComponent(Graphics g) {
			if (imag == null) {
				imag = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics2D d2 = imag.createGraphics();
				d2.setColor(Color.white);
				d2.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
			super.paintComponent(g);
			g.drawImage(imag, 0, 0, this);
		}
	}
}