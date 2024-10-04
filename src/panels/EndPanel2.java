package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class EndPanel2 extends JPanel {

	
	ImageIcon btn = new ImageIcon("img/end/button.png");

	JButton btnNewButton;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_2;
	JLabel lblNewLabel;
	JLabel lblNewLabel2;
	

	JLabel lblNewLabel_1_2;
	JLabel lblNewLabel_2_2;
	JLabel lblNewLabel_22;
	
	JLabel resultLabel;
	JLabel resultLabel2;
	
	
	public void setResultScore(int resultScore) {
		lblNewLabel_2.setText(resultScore+"");
	}
	
	public void setResultScore2(int resultScore_2) {
		lblNewLabel_2_2.setText(resultScore_2+"");
	}

	public EndPanel2(Object o) {
		//버튼
		btnNewButton = new JButton(btn);  
		btnNewButton.setName("endAccept2");
		btnNewButton.addMouseListener((MouseListener) o);
		btnNewButton.setBounds(550, 800, 199, 81);  // 버튼 아래로 이동
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setContentAreaFilled(false);
		add(btnNewButton);
		
		//1P 점수 글자 
		lblNewLabel_1 = new JLabel("1P SCORE");	
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 37));
		lblNewLabel_1.setBounds(451, 10, 400, 55);
		add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("0");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 49));
		lblNewLabel_2.setBounds(450, 52, 459, 87);
		add(lblNewLabel_2);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBackground(SystemColor.activeCaptionText);
		lblNewLabel.setIcon(new ImageIcon("img/end/cookierunbg.jpg"));
		lblNewLabel.setBounds(0, 0, 900, 461);
		add(lblNewLabel);
		
	
		
		//2P 점수 글자 
		lblNewLabel_1_2 = new JLabel("2P SCORE");	
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 37));
		lblNewLabel_1_2.setBounds(451, 500, 400, 55);
		add(lblNewLabel_1_2);
		
		lblNewLabel_2_2 = new JLabel("0");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 49));
		lblNewLabel_2_2.setBounds(450, 550, 459, 87);
		add(lblNewLabel_2_2);
		
		lblNewLabel2 = new JLabel("");
		lblNewLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel2.setBackground(SystemColor.activeCaptionText);
		lblNewLabel2.setIcon(new ImageIcon("img/end/cookierunbg.jpg"));
		lblNewLabel2.setBounds(0, 461, 900, 700);
		add(lblNewLabel2);
		
		
	}
	
	
}
