package main;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ingame.CookieImg;
import panels.EndPanel;
import panels.GamePanel;
import panels.GamePanel2;
import panels.IntroPanel;
import panels.SelectPanel;
import panels.SelectPanel2;
import panels.SelectPlayerPanel;

import panels.EndPanel2;
import main.listenAdapter;

import java.awt.CardLayout;

// windowBuilder 로 프레임만 제작하고 나머지는 입력

public class Main extends listenAdapter {

	private JFrame frame; // 창을 띄우기 위한 프레임

	private IntroPanel introPanel; // 인트로

	private SelectPlayerPanel selectPlayerPanel; // 플레이어 수 선택

	private SelectPanel selectPanel; // 캐릭터 선택

	private SelectPanel2 selectPanel2; // 캐릭터 선택2

	private GamePanel gamePanel; // 게임진행

	private GamePanel2 gamePanel2; // 게임진행

	private EndPanel endPanel; // 게임결과
	
	private EndPanel2 endPanel2; 

	private CardLayout cl; // 카드 레이이웃 오브젝트
	private CardLayout cl2; // 카드 레이이웃 오브젝트

	private CookieImg ci; // 쿠키이미지

	private CookieImg[] ci2; // 쿠키이미지

	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public GamePanel2 getGamePanel1() {
		return gamePanel2;
	}

	public void setGamePanel1(GamePanel2 gamePanel) {
		this.gamePanel2 = gamePanel;
	}

	public GamePanel2 getGamePanel2() {
		return gamePanel2;
	}

	public void setGamePanel2(GamePanel2 gamePanel) {
		this.gamePanel2 = gamePanel;
	}

	public EndPanel getEndPanel() {
		return endPanel;
	}
	
	public EndPanel2 getEndPanel2() {
		return endPanel2;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500); // 창 사이즈 (100,100좌표는 아래의 frame.setLocationRelativeTo(null) 때문에 의미가 없어진다)
		frame.setLocationRelativeTo(null); // 창을 화면 중앙에 배치
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 엑스버튼을 누르면 종료
		cl = new CardLayout(0, 0); // 카드레이아웃 객체 생성
		cl2 = new CardLayout(0, 0);
		frame.getContentPane().setLayout(cl); // 프레임을 카드레이아웃으로 변경
		//panel1 = new JPanel();

		introPanel = new IntroPanel();
		introPanel.addMouseListener(this); // intro패널은 여기서 바로 넣는 방식으로 마우스리스너를 추가함.
		
		selectPlayerPanel = new SelectPlayerPanel(this);

		selectPanel = new SelectPanel(this); // Main의 리스너를 넣기위한 this
		selectPanel2 = new SelectPanel2(this);
		gamePanel = new GamePanel(frame, cl, this); // Main의 프레임 및 카드레이아웃을 이용하고 리스너를 넣기위한 this

		gamePanel2 = new GamePanel2(frame, cl, this); // Main의 프레임 및 카드레이아웃을 이용하고 리스너를 넣기위한 this
		//multiGamePanel = new MultiGamePanel(selectPanel, selectPanel, this);

		endPanel = new EndPanel(this); // Main의 리스너를 넣기위한 this
		
		endPanel2= new EndPanel2(this);

		// 모든 패널의 레이아웃을 null로 변환
		introPanel.setLayout(null);
		selectPlayerPanel.setLayout(null);
		selectPanel.setLayout(null);
		selectPanel2.setLayout(null);
		gamePanel.setLayout(null);
		gamePanel2.setLayout(null);
		endPanel.setLayout(null);
		
		endPanel2.setLayout(null);

		// 프레임에 패널들을 추가한다.(카드 레이아웃을 위한 패널들)
		
		frame.getContentPane().add(introPanel, "intro");
		frame.getContentPane().add(selectPlayerPanel, "selectPlayer");
		frame.getContentPane().add(selectPanel, "select");
		frame.getContentPane().add(selectPanel2, "select2");
		frame.getContentPane().add(gamePanel, "game");
		frame.getContentPane().add(gamePanel2, "game2");
		frame.getContentPane().add(endPanel, "end");
		
		frame.getContentPane().add(endPanel2, "end2");
		frame.getContentPane().add(endPanel2, "end3");
		
	}

	@Override
	public void mousePressed(MouseEvent e) { // mouseClicked로 변경가능
		if (e.getComponent().toString().contains("IntroPanel")) { // IntroPanel에서 마우스를 눌렀다면
			try {
				Thread.sleep(300);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			cl.show(frame.getContentPane(), "selectPlayer"); // select패널을 카드레이아웃 최상단으로 변경
			selectPanel.requestFocus(); // 리스너를 select패널에 강제로 줌
		} else if (e.getComponent().toString().contains("Player1Btn")) { // IntroPanel에서 마우스를 눌렀다면
			try {
				Thread.sleep(300);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			cl.show(frame.getContentPane(), "select"); // select패널을 카드레이아웃 최상단으로 변경
			selectPanel.requestFocus(); // 리스너를 select패널에 강제로 줌
		
		} else if (e.getComponent().toString().contains("Player2Btn")) { // IntroPanel에서 마우스를 눌렀다면
			try {
				Thread.sleep(300);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			frame.setBounds(100, 100, 800, 920);
			frame.setLocationRelativeTo(null); // 창을 화면 중앙에 배치
			cl.show(frame.getContentPane(), "select2"); // select패널을 카드레이아웃 최상단으로 변경
			selectPanel.requestFocus(); // 리스너를 select패널에 강제로 줌
		
		} else if (e.getComponent().getName().equals("StartBtn")) { // StartBtn이라는 이름을 가진 버튼을 눌렀다면
			if (selectPanel.getCi() == null) {
				JOptionPane.showMessageDialog(null, "캐릭터를 골라주세요"); // 캐릭터를 안골랐을경우 팝업
			} else {
				cl.show(frame.getContentPane(), "game"); // 캐릭터를 골랐다면 게임패널을 카드레이아웃 최상단으로 변경
				gamePanel.gameSet(selectPanel.getCi()); // 쿠키이미지를 넘겨주고 게임패널 세팅
				gamePanel.gameStart(); // 게임시작
				gamePanel.requestFocus(); // 리스너를 game패널에 강제로 줌
			}
		
		} else if (e.getComponent().getName().equals("StartBtn2")) { // StartBtn이라는 이름을 가진 버튼을 눌렀다면
			if (selectPanel2.getCi()[0] == null || selectPanel2.getCi()[1] == null) {
				JOptionPane.showMessageDialog(null, "캐릭터를 골라주세요"); // 캐릭터를 안골랐을경우 팝업
			} else {
				
				cl.show(frame.getContentPane(), "game2"); // 캐릭터를 골랐다면 게임패널을 카드레이아웃 최상단으로 변경
				gamePanel2.gameSet(selectPanel2.getCi()[0], selectPanel2.getCi()[1]); // 쿠키이미지를 넘겨주고 게임패널 세팅
				gamePanel2.gameStart(); // 게임시작
				gamePanel2.requestFocus(); // 리스너를 game패널에 강제로 줌
			}
		
		} 
				
			else if (e.getComponent().getName().equals("endAccept")) { // endAccept 이라는 이름을 가진 버튼을 눌렀다면
			frame.getContentPane().remove(gamePanel); // 방금 했던 게임 패널을 프레임에서 삭제
			gamePanel = new GamePanel(frame, cl, this); // 게임패널을 새 패널로 교체
			gamePanel.setLayout(null);
			frame.getContentPane().add(gamePanel, "game"); // 프레임에 새 게임패널 추가(카드레이아웃 하단)
			
			
			frame.getContentPane().remove(selectPanel); // 방금 선택했던 select패널을 삭제
			selectPanel = new SelectPanel(this); // select 패널을 새 패널로 교체
			selectPanel.setLayout(null);
			frame.getContentPane().add(selectPanel, "select"); // 프레임에 새 select패널 추가(카드레이아웃 하단)
			cl.show(frame.getContentPane(), "selectPlayer"); // 새 select패널을 카드레이아웃 최상단으로 이동 (화면에 보임)
			selectPanel.requestFocus(); // 리스너를 select패널에 강제로 줌
		}
		
		else if(e.getComponent().getName().equals("endAccept2")) 
		{
			frame.setBounds(400, 300, 800, 500);
			frame.getContentPane().remove(gamePanel2); // 방금 했던 게임 패널을 프레임에서 삭제
			gamePanel2 = new GamePanel2(frame, cl, this); // 게임패널을 새 패널로 교체
			gamePanel2.setLayout(null);
			frame.getContentPane().add(gamePanel2, "game2"); // 프레임에 새 게임패널 추가(카드레이아웃 하단)
			
			
			frame.getContentPane().remove(selectPanel2); // 방금 선택했던 select패널을 삭제
			selectPanel2 = new SelectPanel2(this); // select 패널을 새 패널로 교체
			selectPanel2.setLayout(null);
			frame.getContentPane().add(selectPanel2, "select2"); // 프레임에 새 select패널 추가(카드레이아웃 하단)
			cl.show(frame.getContentPane(), "selectPlayer"); // 새 select패널을 카드레이아웃 최상단으로 이동 (화면에 보임)
			selectPanel2.requestFocus(); // 리스너를 select패널에 강제로 줌
		}
	}
}
