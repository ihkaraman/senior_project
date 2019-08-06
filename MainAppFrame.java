import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainAppFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainAppFrame() {
	}

	public JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainAppFrame();					
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
	
	public static void mainAppFrame() throws IOException {
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1012, 688);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKayseriekerFabrikas = new JLabel("KAYSER\u0130 \u015EEKER FABR\u0130KASI");
		lblKayseriekerFabrikas.setBackground(new Color(0, 0, 0));
		lblKayseriekerFabrikas.setForeground(SystemColor.window);
		lblKayseriekerFabrikas.setHorizontalAlignment(SwingConstants.CENTER);
		lblKayseriekerFabrikas.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblKayseriekerFabrikas.setBounds(0, 220, 1000, 68);
		contentPane.add(lblKayseriekerFabrikas);
		
		BufferedImage myPicture1 = ImageIO.read(new File("C:/Users/Asus/workspace/KMeans/resim/ksf.png"));
		JLabel picLabel1 = new JLabel(new ImageIcon(myPicture1));
		picLabel1.setForeground(new Color(0, 102, 0));
		picLabel1.setSize(150, 150);
		picLabel1.setLocation(10, 13);
		contentPane.add(picLabel1);
		
		BufferedImage myPicture3 = ImageIO.read(new File("C:/Users/Asus/workspace/KMeans/resim/tobb.jpg"));
		JLabel picLabel3 = new JLabel(new ImageIcon(myPicture3));
		picLabel3.setForeground(new Color(0, 102, 0));
		picLabel3.setSize(150, 150);
		picLabel3.setLocation(832, 13);
		contentPane.add(picLabel3);

		JLabel lblTarmMahsulGzlemi = new JLabel("\u0130NSANSIZ HAVA ARACI\r\n");
		lblTarmMahsulGzlemi.setForeground(SystemColor.control);
		lblTarmMahsulGzlemi.setBackground(new Color(255, 255, 255));
		lblTarmMahsulGzlemi.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblTarmMahsulGzlemi.setHorizontalAlignment(SwingConstants.CENTER);
		lblTarmMahsulGzlemi.setBounds(0, 321, 1000, 60);
		contentPane.add(lblTarmMahsulGzlemi);
		
		JLabel lblFiloBelirlemeVe = new JLabel("F\u0130LO BEL\u0130RLEME VE ROTALAMA PROGRAMI");
		lblFiloBelirlemeVe.setForeground(new Color(255, 240, 245));
		lblFiloBelirlemeVe.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiloBelirlemeVe.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblFiloBelirlemeVe.setBounds(0, 394, 1000, 60);
		contentPane.add(lblFiloBelirlemeVe);
		
		JButton btnBala = new JButton("BA\u015ELA");
		btnBala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JF1.parametrePenceresi();
				frame.dispose();
			}
		});
		btnBala.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnBala.setBounds(359, 501, 331, 68);
		contentPane.add(btnBala);
		
		BufferedImage myPicture2 = ImageIO.read(new File("C:/Users/Asus/workspace/KMeans/resim/uav.jpg"));
		JLabel picLabel2 = new JLabel(new ImageIcon(myPicture2));
		picLabel2.setForeground(new Color(0, 152, 0));
		picLabel2.setSize(1000, 641);
		picLabel2.setLocation(0, 0);
		contentPane.add(picLabel2);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		
	}
}
