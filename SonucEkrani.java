import javax.swing.JFrame;
import javax.swing.JLabel;

public class SonucEkrani extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the frame.
	 */
	public static void SonucEK(){
		SonucEkrani sc= new SonucEkrani();
		sc.calistirSonucEkrani();
	}
	public void calistirSonucEkrani() {
		
		
		JFrame w = new JFrame();
		w.setSize(1012, 688);
		w.setTitle("  İHA ROTALAMA");
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawingComponent dc = new drawingComponent();	
		w.add(dc);
		JLabel lab = new JLabel();
		lab.setText("35");
		//w.add(lab);
		
		
		
		w.setLocationRelativeTo(null);
		w.setVisible(true);
		
		
		/*
		//setBounds(100, 100, 1012, 688);
		//contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		//contentPane.setLayout(null);
	 
		
		java.awt.Graphics g = panel.getGraphics();
		g.setColor(Color.BLACK);
		g.drawLine(100, 100, 500, 500);
		g.dispose();
		
		add(panel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(12, 33, 97, 25);
		contentPane.add(btnNewButton);
		panel.setVisible(true);
		*/
	}
}
