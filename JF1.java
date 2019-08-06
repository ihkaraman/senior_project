import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JF1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFileChooser fileChooser;
	private static JLayeredPane contentPane;
	private static JTextField kumeSayisi;
	private static JTextField txtMaxmumMesafeyiGiriniz;
	private static JTextField txtMaxmumAlanGiriniz;
	private final static ButtonGroup buttonGroup = new ButtonGroup();
	private static File file;
	public static String fname ="";
	private static JTextField gunSayiText;
	
	/**
	 * Launch the application.
	 */
	public static void parametrePenceresi(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					JFrame1();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public JF1(){
		
	}
	public static void JFrame1() throws IOException {
		
		
		JF1 frame = new JF1();
		frame.setBackground(new Color(255, 250, 250));
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		frame.setTitle("  İHA ROTALAMA");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1012, 688);
		contentPane = new JLayeredPane();
		contentPane.setBackground(new Color(216, 191, 216));
		contentPane.setForeground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKmeSaysnGiriniz = new JLabel("KÜME SAYISI");
		lblKmeSaysnGiriniz.setHorizontalAlignment(SwingConstants.LEFT);
		lblKmeSaysnGiriniz.setForeground(Color.BLACK);
		lblKmeSaysnGiriniz.setBackground(Color.DARK_GRAY);
		lblKmeSaysnGiriniz.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblKmeSaysnGiriniz.setBounds(61, 199, 131, 37);
		contentPane.add(lblKmeSaysnGiriniz);
		
		kumeSayisi = new JTextField();
		contentPane.setLayer(kumeSayisi, 0);
		lblKmeSaysnGiriniz.setLabelFor(kumeSayisi);
		kumeSayisi.setBounds(239, 203, 160, 32);
		contentPane.add(kumeSayisi);
		kumeSayisi.setColumns(10);
		
		JLabel lblMaxmumMesafeyiGiriniz = new JLabel("MAX MESAFE");
		lblMaxmumMesafeyiGiriniz.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblMaxmumMesafeyiGiriniz.setBounds(61, 256, 131, 43);
		contentPane.add(lblMaxmumMesafeyiGiriniz);
		
		txtMaxmumMesafeyiGiriniz = new JTextField();
		txtMaxmumMesafeyiGiriniz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KMeans.maxDistance = Integer.parseInt(txtMaxmumMesafeyiGiriniz.getText());
			}
		});
		txtMaxmumMesafeyiGiriniz.setColumns(10);
		txtMaxmumMesafeyiGiriniz.setBounds(239, 264, 160, 30);
		contentPane.add(txtMaxmumMesafeyiGiriniz);
		
		JLabel lblMaxmumAlanGiriniz = new JLabel("MAX ALAN");
		lblMaxmumAlanGiriniz.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblMaxmumAlanGiriniz.setBounds(61, 312, 146, 43);
		contentPane.add(lblMaxmumAlanGiriniz);
		
		txtMaxmumAlanGiriniz = new JTextField();
		txtMaxmumAlanGiriniz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KMeans.maxArea = (1000000*(Integer.parseInt(txtMaxmumAlanGiriniz.getText())));
			}
		});
		txtMaxmumAlanGiriniz.setColumns(10);
		txtMaxmumAlanGiriniz.setBounds(239, 320, 160, 30);
		contentPane.add(txtMaxmumAlanGiriniz);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(643, 382, 271, 88);
		contentPane.add(scrollPane_1);
		
		JButton btnaltr = new JButton("     \u00C7ALI\u015ETIR");
		scrollPane_1.setViewportView(btnaltr);
		btnaltr.setForeground(new Color(0, 128, 0));
		btnaltr.setIcon(new ImageIcon("C:/Users/Asus/workspace/KMeans/resim/run1.png"));
		btnaltr.setHorizontalAlignment(SwingConstants.RIGHT);
		btnaltr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				KMeans.maxDistance = Integer.parseInt(txtMaxmumMesafeyiGiriniz.getText());
				KMeans.maxArea = (1000000*(Integer.parseInt(txtMaxmumAlanGiriniz.getText())));
				KMeans.paramCNumber = Integer.parseInt(kumeSayisi.getText());
				Heuristic.gunSayisi = Integer.parseInt(gunSayiText.getText());
				JF2.JFrame2();
				KMeans.calistir();
				frame.dispose();
				
			}
		});
		buttonGroup.add(btnaltr);
		contentPane.setLayer(btnaltr, 0);
		btnaltr.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		gunSayiText = new JTextField();
		gunSayiText.setColumns(10);
		gunSayiText.setBounds(239, 376, 160, 30);
		contentPane.add(gunSayiText);
		
		
		JLabel lblMetre = new JLabel("metre");
		lblMetre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMetre.setBounds(421, 271, 56, 16);
		contentPane.add(lblMetre);
		
		JLabel lblGnSays = new JLabel("GÜN SAYISI");
		lblGnSays.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblGnSays.setBounds(61, 368, 146, 43);
		contentPane.add(lblGnSays);
		
		JLabel label = new JLabel("gün");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(421, 383, 56, 16);
		contentPane.add(label);
		
		JLabel lblKm = new JLabel("km 2");
		lblKm.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKm.setBounds(421, 327, 56, 16);
		contentPane.add(lblKm);
		
		JButton btnDurdur = new JButton("ÇIKIŞ");
		btnDurdur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		btnDurdur.setBounds(722, 500, 124, 43);
		contentPane.add(btnDurdur);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(74, 101, 176, 32);
		contentPane.add(scrollPane);
		
		JButton btnDosyaSe = new JButton("DOSYA SE\u00C7");
		scrollPane.setViewportView(btnDosyaSe);
		btnDosyaSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.showOpenDialog((Component) e.getSource());
				file = fileChooser.getSelectedFile();
				Point.filename = file.getAbsolutePath();
				fname = file.getAbsolutePath();
				System.out.println(fname);
			}
		});
		btnDosyaSe.setFont(new Font("Times New Roman", Font.BOLD, 14));
		JPanel panel = new JPanel();
		panel.setBounds(785, 48, 160, 160);
		contentPane.add(panel);
		panel.setLayout(null);
		BufferedImage myPicture3 = ImageIO.read(new File("C:/Users/Asus/workspace/KMeans/resim/tobb.jpg"));
		
		
	
		JLabel picLabel3 = new JLabel(new ImageIcon(myPicture3));
		picLabel3.setBackground(new Color(176, 196, 222));
		picLabel3.setBounds(0, 0, 160, 160);
		panel.add(picLabel3);
		picLabel3.setForeground(Color.GRAY);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:/Users/Asus/workspace/KMeans/resim/arkaplan.jpg"));
		lblNewLabel.setBounds(0, 0, 994, 641);
		contentPane.add(lblNewLabel);
		
		

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	
	}
}
