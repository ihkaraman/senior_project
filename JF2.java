import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JLabel;
import java.awt.Desktop;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JF2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void JFrame2 (){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					JF2 frame = new JF2();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	

	
	
	public JF2() throws IOException {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1012, 688);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setTitle("  İHA ROTALAMA");
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(new Color(60, 179, 113));
		progressBar.setValue(100);
		progressBar.setStringPainted(true);
		progressBar.setBounds(202, 415, 606, 14);
		contentPane.add(progressBar);
		
		JButton btnSonularIinTklaynz = new JButton("SONUÇLAR İÇİN TIKLAYINIZ");
		btnSonularIinTklaynz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//plotScatter.grupCiz();
				//SonucEkrani.JFrame3();
				SonucEkrani.SonucEK();
				
			}
		});
		btnSonularIinTklaynz.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnSonularIinTklaynz.setBounds(310, 464, 410, 74);
		contentPane.add(btnSonularIinTklaynz);
		
		JButton btnExSonularIinTklaynz = new JButton("       SONU\u00C7LARI EXCEL'DE G\u00D6RMEK \u0130\u00C7\u0130N TIKLAYINIZ");
		btnExSonularIinTklaynz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					File file = new File("C:/Users/Asus/workspace/KMeans/Sonuclar.xlsx");
					Desktop.getDesktop().open(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnExSonularIinTklaynz.setIcon(new ImageIcon("C:\\Users\\Asus\\workspace\\KMeans\\resim\\excel.jpg"));
		btnExSonularIinTklaynz.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExSonularIinTklaynz.setBounds(283, 567, 498, 34);
		contentPane.add(btnExSonularIinTklaynz);
		
		JLabel lblAtananNoktaSays = new JLabel("Atanan nokta sayısı           :");
		lblAtananNoktaSays.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAtananNoktaSays.setBounds(70, 158, 254, 34);
		contentPane.add(lblAtananNoktaSays);
		
		JLabel lblOluturulanKmeSays = new JLabel("Oluşturulan Küme Sayısı  :");
		lblOluturulanKmeSays.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOluturulanKmeSays.setBounds(70, 205, 243, 34);
		contentPane.add(lblOluturulanKmeSays);
		
		JLabel lblOluturulanKmeSays_1 = new JLabel("Oluşturulan Grup Sayısı    :");
		lblOluturulanKmeSays_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOluturulanKmeSays_1.setBounds(70, 252, 254, 34);
		contentPane.add(lblOluturulanKmeSays_1);
		
		JLabel lblIlemTamamland = new JLabel("\u0130\u015ELEM TAMAMLANDI . . .");
		lblIlemTamamland.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblIlemTamamland.setBounds(82, 63, 286, 46);
		contentPane.add(lblIlemTamamland);
		

		
		JLabel lblGerekliEkipSays = new JLabel("Gerekli Ekip Sayısı              :");
		lblGerekliEkipSays.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGerekliEkipSays.setBounds(70, 299, 254, 34);
		contentPane.add(lblGerekliEkipSays);
		
		BufferedImage myPicture3 = ImageIO.read(new File("C:/Users/Asus/workspace/KMeans/resim/tobb.jpg"));
		JPanel panel = new JPanel();
		panel.setBounds(785, 48, 160, 160);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel picLabel3 = new JLabel(new ImageIcon(myPicture3));
		picLabel3.setBackground(new Color(176, 196, 222));
		picLabel3.setBounds(0, 0, 160, 160);
		panel.add(picLabel3);
		picLabel3.setForeground(Color.GRAY);
		
		JLabel label_1 = new JLabel(" "+KMeans.points.size()+"");
		label_1.setBackground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_1.setBounds(354, 158, 211, 34);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel(" "+KMeans.numCluster+"");
		label_1.setBackground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_2.setBounds(354, 205, 211, 34);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel(" " + Heuristic.gruplar.size() );
		label_1.setBackground(Color.WHITE);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_3.setBounds(354, 252, 211, 34);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel(" "+ Heuristic.ekipSayisi);
		label_1.setBackground(Color.WHITE);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_4.setBounds(354, 299, 211, 34);
		contentPane.add(label_4);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:/Users/Asus/workspace/KMeans/resim/arkaplan.jpg"));
		lblNewLabel.setBounds(0, 0, 994, 641);
		contentPane.add(lblNewLabel);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
