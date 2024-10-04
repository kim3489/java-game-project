package panels;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SelectPlayerPanel extends JPanel {

	// 플레이어 수 버튼 이미지아이콘
	private ImageIcon player1 = new ImageIcon("img/select/Player1Btn.png");
	private ImageIcon player2 = new ImageIcon("img/select/Player2Btn.png");


	// 플레이어 수 버튼
	private JButton Player1Btn;
	private JButton Player2Btn;
	
	// 게임에서 사용할 쿠키 이미지들을 담을 오브젝트
	private int num_of_player;

	// 쿠키 이미지를 메인에서 gamePanel로 보내기 위한 게터
	public int getNumOfPlayer() {
		return num_of_player;
	}

	public SelectPlayerPanel(Object o) {

		// 시작 버튼
		Player1Btn = new JButton(player1);
		Player1Btn.setName("Player1Btn");
		Player1Btn.addMouseListener((MouseListener) o);
		Player1Btn.setBounds(254, 100, 291, 81);
		add(Player1Btn);
		Player1Btn.setBorderPainted(false);
		Player1Btn.setContentAreaFilled(false);
		Player1Btn.setFocusPainted(false);

		Player2Btn = new JButton(player2);
		Player2Btn.setName("Player2Btn");
		Player2Btn.addMouseListener((MouseListener) o);
		Player2Btn.setBounds(254, 300, 291, 81);
		add(Player2Btn);
		Player2Btn.setBorderPainted(false);
		Player2Btn.setContentAreaFilled(false);
		Player2Btn.setFocusPainted(false);
		
		// 배경
		JLabel selectBg = new JLabel("");
		selectBg.setForeground(Color.ORANGE);
		selectBg.setHorizontalAlignment(SwingConstants.CENTER);
		selectBg.setIcon(new ImageIcon("img/select/selectBg.png"));
		selectBg.setBounds(0, 0, 784, 461);
		add(selectBg);
	}
}
