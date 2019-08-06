import java.awt.*;
import javax.swing.*;

public class Arayuz {

	private static JFrame pencere;	
	private static JLabel etiket;
	private static Container icerik;
	private static GridBagConstraints gc;
	private static JButton dugme;
	private static JComboBox<Object> comboBox ;
	
	public static void baslangicEkranı() {
		
		Font standart1 = new Font("Helvetica", Font.BOLD, 16); // yazı fontu belirlenir
		
		pencere = new JFrame("İHA Rotalama");
		pencere.setLayout(new GridBagLayout());
		pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pencere.setSize(600, 400); // pencere boyutu belirlenir
		icerik = new JPanel(new GridBagLayout());
		gc = new GridBagConstraints();
		pencere.setContentPane(icerik);
		
		
		gc.gridx = 0; gc.gridy = 0;
		gc.gridwidth = 20; gc.gridheight = 3;
		gc.anchor = GridBagConstraints.WEST ;
		etiket = new JLabel("Arazi Dosyasını Seçiniz.");
		etiket.setFont(standart1);
		icerik.add(etiket, gc);
		
		// Dosya yolu ekleme kodu:
		gc.gridx = 50; gc.gridy = 0;
		gc.gridwidth = 20; gc.gridheight = 2;
		gc.anchor = GridBagConstraints.WEST ;
		dugme = new JButton("Dosya Seç");
		// dugme.addActionListener(new OpenFile());
		icerik.add(dugme, gc);
		
		gc.gridx = 0; gc.gridy = 10;
		gc.gridwidth = 20; gc.gridheight = 3;
		gc.anchor = GridBagConstraints.WEST ;
		etiket = new JLabel("Küme Sayısını Giriniz ");
		etiket.setFont(standart1);
		icerik.add(etiket, gc);
		
		gc.gridx = 50; gc.gridy = 10;
		gc.gridwidth = 20; gc.gridheight = 3;
		gc.anchor = GridBagConstraints.CENTER ;
		icerik.add(new JTextField(10), gc);
		// Küme sayısı eklenmesi için kutu kodu buraya 
		
		gc.gridx = 0; gc.gridy = 20;
		gc.gridwidth = 20; gc.gridheight = 3;
		gc.anchor = GridBagConstraints.WEST ;
		etiket = new JLabel("Maximum mesafeyi giriniz");
		etiket.setFont(standart1);
		icerik.add(etiket, gc);
		
		gc.gridx = 50; gc.gridy = 20;
		gc.gridwidth = 20; gc.gridheight = 3;
		gc.anchor = GridBagConstraints.CENTER ;
		icerik.add(new JTextField(10), gc);
		
		// mesafe girme kutusu için kod
		
		gc.gridx = 0; gc.gridy = 30;
		gc.gridwidth = 20; gc.gridheight = 3;
		gc.anchor = GridBagConstraints.WEST ;
		etiket = new JLabel("Maximum küme alanını giriniz");
		etiket.setFont(standart1);
		icerik.add(etiket, gc);
		
		gc.gridx = 50; gc.gridy = 30;
		gc.gridwidth = 20; gc.gridheight = 3;
		gc.anchor = GridBagConstraints.CENTER ;
		icerik.add(new JTextField(10), gc);
		// küme alanı girme kutusu için kod
		
		gc.gridx = 0; gc.gridy = 40;
		gc.gridwidth = 20; gc.gridheight = 3;
		gc.anchor = GridBagConstraints.WEST ;
		etiket = new JLabel("Uçak türü seçiniz");
		etiket.setFont(standart1);
		icerik.add(etiket, gc);
		
		
		// uçak türü seçmek için açılır pencere
		// seçilen türü alma komutu yok
		String[] ucakTurleri = {"İHA 1","İHA 2","İHA 3","İHA 4"};
		comboBox = new JComboBox<Object>(ucakTurleri);
		// secenek.addActionListener(this);
		gc.gridx = 40; gc.gridy = 40;
		gc.gridwidth = 12; gc.gridheight = 3;
		gc.anchor = GridBagConstraints.CENTER ;
		icerik.add(new JLabel("..."), gc);
		icerik.add(comboBox, gc);
		
		gc.gridx = 160; gc.gridy = 60;
		gc.gridwidth = 20; gc.gridheight = 3;
		JButton calistir = new JButton("ÇALIŞTIR");
		icerik.add(calistir, gc);
	
		pencere.setVisible(true);
	}
	
	public void takeParameters() {
		
		
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable()
				{ public void run()
					{
					Arayuz.baslangicEkranı();
					}
				});

	
	}


}
