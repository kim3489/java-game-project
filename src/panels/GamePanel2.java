package panels;

import java.awt.AlphaComposite;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ingame.Back;
import ingame.Cookie;
import ingame.CookieImg;
import ingame.Field;
import ingame.Jelly;
import ingame.MapObjectImg;
import ingame.Tacle;
import main.Main;
import util.Util;

public class GamePanel2 extends JPanel {

	// 1인용
	// 쿠키 이미지 아이콘들
	private ImageIcon cookieIc; // 기본모션
	private ImageIcon jumpIc; // 점프모션
	private ImageIcon doubleJumpIc; // 더블점프모션
	private ImageIcon fallIc; // 낙하모션(더블 점프 후)
	private ImageIcon slideIc; // 슬라이드 모션
	private ImageIcon hitIc; // 부딛히는 모션

	// 배경 이미지
	private ImageIcon backIc; // 제일 뒷 배경
	private ImageIcon secondBackIc; // 2번째 배경

	private ImageIcon backIc2;
	private ImageIcon secondBackIc2;

	private ImageIcon backIc3;
	private ImageIcon secondBackIc3;

	private ImageIcon backIc4;
	private ImageIcon secondBackIc4;

	// 젤리 이미지 아이콘들
	private ImageIcon jelly1Ic;
	private ImageIcon jelly2Ic;
	private ImageIcon jelly3Ic;
	private ImageIcon jellyHPIc;

	private ImageIcon jellyEffectIc;

	// 발판 이미지 아이콘들
	private ImageIcon field1Ic; // 발판
	private ImageIcon field2Ic; // 공중발판

	// 장애물 이미지 아이콘들
	private ImageIcon tacle10Ic; // 1칸 장애물
	private ImageIcon tacle20Ic; // 2칸 장애물
	private ImageIcon tacle30Ic; // 3칸 장애물
	private ImageIcon tacle40Ic; // 4칸 장애물
	
	private ImageIcon tacleAddIc;

	// 추가 이미지 아이콘들
	private ImageIcon jellyReverseIc;
	private ImageIcon jellyAddIc;
	private ImageIcon jellySizeIc;

	// 체력 게이지
	private ImageIcon lifeBar;

	private ImageIcon redBg; // 피격시 붉은 화면

	private ImageIcon jumpButtonIconUp;
	private ImageIcon jumpButtonIconDown;

	private ImageIcon slideIconUp;
	private ImageIcon slideIconDown;

	Image jumpBtn;
	Image slideBtn;

	boolean is_reverse = false; //reverse함수 제어
	boolean is_reverse2 = false;
	
	boolean	is_add=false;	//add함수 제어
	boolean is_add2=false;
	
	boolean one_die=false;	//먼저 죽으면 대기 걸기
	boolean	two_die=false; 
	// 리스트 생성
	private List<Jelly> jellyList; // 젤리 리스트

	private List<Field> fieldList; // 발판 리스트

	private List<Tacle> tacleList; // 장애물 리스트
	

	private List<Integer> mapLengthList;

	private int mapLength = 0;

	private int runPage = 0; // 한 화면 이동할때마다 체력을 깎기 위한 변수

	private int runStage = 1; // 스테이지를 확인하는 변수이다. (미구현)

	private int resultScore = 0; // 결과점수를 수집하는 변수

	private int gameSpeed = 5; // 게임 속도
	
	private int nowField = 2000; // 발판의 높이를 저장.

	private JButton escButton; // esc 버튼 (테스트 중)

	private boolean fadeOn = false;

	private boolean escKeyOn = false; // 일시정지를 위한 esc키 확인

	private boolean downKeyOn = false; // 다운키 눌렀는지 여부

	private boolean redScreen = false; // 피격시 반짝 붉은 화면 여부

	int face; // 쿠키의 정면
	int foot; // 쿠키의 발

	// 이미지 파일로 된 맵을 가져온다.
	private int[] sizeArr; // 이미지의 넓이와 높이를 가져오는 1차원 배열
	private int[][] colorArr; // 이미지의 x y 좌표의 픽셀 색값을 저장하는 2차원배열

	private Image buffImage; // 더블버퍼 이미지
	private Graphics buffg; // 더블버퍼 g
	
	private Image buffImage_1; // 더블버퍼 이미지
	private Graphics buffg_1; // 더블버퍼 g

	private AlphaComposite alphaComposite; // 투명도 관련 오브젝트

	Cookie c1; // 쿠키 오브젝트

	Back b11; // 배경1-1 오브젝트
	Back b12; // 배경1-2 오브젝트

	Back b21; // 배경2-1 오브젝트
	Back b22; // 배경2-2 오브젝트

	Color backFade; // 배경이 바뀔 때 페이드 아웃 페이드 인 하기 위한 컬러변수

	// 맵 오브젝트의 이미지들
	MapObjectImg mo1;
	MapObjectImg mo2;
	MapObjectImg mo3;
	MapObjectImg mo4;


	// 2인용
	// 쿠키 이미지 아이콘들
	private ImageIcon cookieIc_2; // 기본모션
	private ImageIcon jumpIc_2; // 점프모션
	private ImageIcon doubleJumpIc_2; // 더블점프모션
	private ImageIcon fallIc_2; // 낙하모션(더블 점프 후)
	private ImageIcon slideIc_2; // 슬라이드 모션
	private ImageIcon hitIc_2; // 부딛히는 모션

	// 배경 이미지
	private ImageIcon backIc_2; // 제일 뒷 배경
	private ImageIcon secondBackIc_2; // 2번째 배경

	private ImageIcon backIc2_2;
	private ImageIcon secondBackIc2_2;

	private ImageIcon backIc3_2;
	private ImageIcon secondBackIc3_2;

	private ImageIcon backIc4_2;
	private ImageIcon secondBackIc4_2;

	// 젤리 이미지 아이콘들
	private ImageIcon jelly1Ic_2;
	private ImageIcon jelly2Ic_2;
	private ImageIcon jelly3Ic_2;
	private ImageIcon jellyHPIc_2;

	private ImageIcon jellyEffectIc_2;

	// 발판 이미지 아이콘들
	private ImageIcon field1Ic_2; // 발판
	private ImageIcon field2Ic_2; // 공중발판

	// 장애물 이미지 아이콘들
	private ImageIcon tacle10Ic_2; // 1칸 장애물
	private ImageIcon tacle20Ic_2; // 2칸 장애물
	private ImageIcon tacle30Ic_2; // 3칸 장애물
	private ImageIcon tacle40Ic_2; // 4칸 장애물

	// 추가 이미지 아이콘들
	private ImageIcon jellyReverseIc_2;
	private ImageIcon jellyAddIc_2;
	private ImageIcon jellySizeIc_2;

	// 체력 게이지
	private ImageIcon lifeBar_2;

	private ImageIcon redBg_2; // 피격시 붉은 화면

	private ImageIcon jumpButtonIconUp_2;
	private ImageIcon jumpButtonIconDown_2;

	private ImageIcon slideIconUp_2;
	private ImageIcon slideIconDown_2;

	Image jumpBtn_2;
	Image slideBtn_2;

	// 리스트 생성
	private List<Jelly> jellyList_2; // 젤리 리스트

	private List<Field> fieldList_2; // 발판 리스트

	private List<Tacle> tacleList_2; // 장애물 리스트

	private List<Integer> mapLengthList_2;

	private int mapLength_2 = 0;

	private int runPage_2 = 0; // 한 화면 이동할때마다 체력을 깎기 위한 변수

	private int runStage_2 = 1; // 스테이지를 확인하는 변수이다. (미구현)

	private int resultScore_2 = 0; // 결과점수를 수집하는 변수

	private int gameSpeed_2 = 5; // 게임 속도

	private int nowField_2 = 2000; // 발판의 높이를 저장.

	private JButton escButton_2; // esc 버튼 (테스트 중)

	private boolean fadeOn_2 = false;

	private boolean escKeyOn_2 = false; // 일시정지를 위한 esc키 확인

	private boolean downKeyOn_2 = false; // 다운키 눌렀는지 여부

	private boolean redScreen_2 = false; // 피격시 반짝 붉은 화면 여부
	
	
	int face_2; // 쿠키의 정면
	int foot_2; // 쿠키의 발

	// 이미지 파일로 된 맵을 가져온다.
	private int[] sizeArr_2; // 이미지의 넓이와 높이를 가져오는 1차원 배열
	private int[][] colorArr_2; // 이미지의 x y 좌표의 픽셀 색값을 저장하는 2차원배열

	private Image buffImage_2; // 더블버퍼 이미지
	private Graphics buffg_2; // 더블버퍼 g

	private AlphaComposite alphaComposite_2; // 투명도 관련 오브젝트
	


	
	Cookie c1_2; // 쿠키 오브젝트

	Back b11_2; // 배경1-1 오브젝트
	Back b12_2; // 배경1-2 오브젝트

	Back b21_2; // 배경2-1 오브젝트
	Back b22_2; // 배경2-2 오브젝트

	Color backFade_2; // 배경이 바뀔 때 페이드 아웃 페이드 인 하기 위한 컬러변수

	// 맵 오브젝트의 이미지들
	MapObjectImg mo1_2;
	MapObjectImg mo2_2;
	MapObjectImg mo3_2;
	MapObjectImg mo4_2;



	// 외부
	JFrame superFrame;
	CardLayout cl;
	Main main;
	
	// 게임패널 생성자 (상위 프레임과 카드레이아웃, 그리고 Main인스턴스를 받는다)
	public GamePanel2(JFrame superFrame, CardLayout cl, Object o) {

		this.superFrame = superFrame;
		this.cl = cl;
		this.main = (Main) o;

		// 일시정지 버튼
		escButton = new JButton("back");
		escButton.setBounds(350, 200, 100, 30);
		escButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove(escButton);
				escKeyOn = false;
			}
		});

		// 일시정지 버튼
		escButton_2 = new JButton("back");
		escButton_2.setBounds(350, 640, 100, 30);
		escButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove(escButton_2);
				escKeyOn_2 = false;
			}
		});

	}

	// 게임을 세팅한다
	public void gameSet(CookieImg ci, CookieImg ci_2) {

		setFocusable(true);

		initCookieImg(ci); // 쿠키이미지를 세팅
		initCookieImg2(ci_2); // 쿠키이미지를 세팅

		initObject(); // 게임 내 지형지물 인스턴스 생성

		initListener(); // 키리스너 추가

		runRepaint(); // 리페인트 무한반복 실행
		runRepaint2(); // 리페인트 무한반복 실행
	}

	// 게임을 시작한다
	public void gameStart() {
		mapMove(); // 배경 젤리 발판 장애물 작동
		mapMove2(); // 배경 젤리 발판 장애물 작동

		fall(); // 낙하 스레드 발동
		fall2(); // 낙하 스레드 발동
	}

	// 화면을 그린다
	@Override
	protected void paintComponent(Graphics g) {

		// 더블버퍼는 그림을 미리그려놓고 화면에 출력한다.

		// 더블버퍼 관련
		if (buffg == null) {
			buffImage = createImage(this.getWidth(), this.getHeight());
			if (buffImage == null) {
				System.out.println("더블 버퍼링용 오프 스크린 생성 실패");
			} else {
				buffg = buffImage.getGraphics();
			}
		}
		
		// 투명도 관련
		Graphics2D g2 = (Graphics2D) buffg;
		
		

		super.paintComponent(buffg); // 이전 화면을 지운다.

		// 배경이미지를 그린다
		buffg.drawImage(b11.getImage(), b11.getX(), 0, b11.getWidth(), b11.getHeight() * 5 / 4, null);
		buffg.drawImage(b12.getImage(), b12.getX(), 0, b12.getWidth(), b12.getHeight() * 5 / 4, null);
		buffg.drawImage(b21.getImage(), b21.getX(), 0, b21.getWidth(), b21.getHeight() * 5 / 4, null);
		buffg.drawImage(b22.getImage(), b22.getX(), 0, b22.getWidth(), b22.getHeight() * 5 / 4, null);

		// 스테이지 넘어갈시 페이드아웃 인 효과
		if (fadeOn) {
			buffg.setColor(backFade); // 투명하게 하는방법 1
			buffg.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		// 발판을 그린다
		for (int i = 0; i < fieldList.size(); i++) {

			Field tempFoot = fieldList.get(i);

			// 사양을 덜 잡아먹게 하기위한 조치
			if (tempFoot.getX() > -90 && tempFoot.getX() < 810) { // x값이 -90~810인 객체들만 그린다.

				buffg.drawImage(tempFoot.getImage(), tempFoot.getX(), tempFoot.getY(), tempFoot.getWidth(),
						tempFoot.getHeight(), null);
			}

		}

		// 젤리를 그린다
		for (int i = 0; i < jellyList.size(); i++) {

			Jelly tempJelly = jellyList.get(i);

			if (tempJelly.getX() > -90 && tempJelly.getX() < 810) {

				alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
						(float) tempJelly.getAlpha() / 255);
				g2.setComposite(alphaComposite); // 투명하게 하는방법 2

				buffg.drawImage(tempJelly.getImage(), tempJelly.getX(), tempJelly.getY(), tempJelly.getWidth(),
						tempJelly.getHeight(), null);

				// alpha값을 되돌린다
				alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
				g2.setComposite(alphaComposite);
			}
		}

		// 장애물을 그린다
		for (int i = 0; i < tacleList.size(); i++) {

			Tacle tempTacle = tacleList.get(i);

			if (tempTacle.getX() > -90 && tempTacle.getX() < 810) {

				buffg.drawImage(tempTacle.getImage(), tempTacle.getX(), tempTacle.getY(), tempTacle.getWidth(),
						tempTacle.getHeight(), null);
			}
		}
				
		if (c1.isInvincible()) { // 무적상태일 경우
			// 쿠키의 alpha값을 받아온다
			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) c1.getAlpha() / 255);
			g2.setComposite(alphaComposite);

			// 쿠키를 그린다
			buffg.drawImage(c1.getImage(), c1.getX() - 110, c1.getY() - 170,
					cookieIc.getImage().getWidth(null) * 8 / 10, cookieIc.getImage().getHeight(null) * 8/ 10, null);

			// alpha값을 되돌린다
			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
			g2.setComposite(alphaComposite);

		} else { // 무적상태가 아닐 경우
			
			// 쿠키를 그린다
			buffg.drawImage(c1.getImage(), c1.getX() - 110 , c1.getY() - 170,
					cookieIc.getImage().getWidth(null) * 8 / 10, cookieIc.getImage().getHeight(null) * 8 / 10, null);
		}
		
		// 피격시 붉은 화면
		if (redScreen) {

			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 125 / 255);
			g2.setComposite(alphaComposite);

			buffg.drawImage(redBg.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
			g2.setComposite(alphaComposite);
		}

		// 점수를 그린다
		Util.drawFancyString(g2, Integer.toString(resultScore), 600, 58, 30, Color.WHITE);

		// 체력게이지를 그린다
		buffg.drawImage(lifeBar.getImage(), 20, 30, null);
		buffg.setColor(Color.BLACK);
		buffg.fillRect(84 + (int) (470 * ((double) c1.getHealth() / 1000)), 65,
				1 + 470 - (int) (470 * ((double) c1.getHealth() / 1000)), 21);

		// 버튼을 그린다
		buffg.drawImage(jumpBtn, 0, 360, 132, 100, null);
		buffg.drawImage(slideBtn, 650, 360, 132, 100, null);

		if (escKeyOn) { // esc키를 누를경우 화면을 흐리게 만든다
			// alpha값을 반투명하게 만든다
			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 100 / 255);
			g2.setComposite(alphaComposite);

			buffg.setColor(Color.BLACK);

			buffg.fillRect(0, 0, 850, 550);

			// alpha값을 되돌린다
			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
			g2.setComposite(alphaComposite);
		}

		// 버퍼이미지를 화면에 출력한다
		g.drawImage(buffImage, 0, 0, this);


		// 2인용
		// 더블버퍼는 그림을 미리그려놓고 화면에 출력한다.

		// 더블버퍼 관련
		if (buffg_2 == null) {
			buffImage_2 = createImage(this.getWidth(), this.getHeight());
			if (buffImage_2 == null) {
				System.out.println("더블 버퍼링용 오프 스크린 생성 실패");
			} else {
				buffg_2 = buffImage_2.getGraphics();
			}
		}
		
	
		// 투명도 관련
		Graphics2D g2_2 = (Graphics2D) buffg_2;


		super.paintComponent(buffg_2); // 이전 화면을 지운다.
		
		// 배경이미지를 그린다
		buffg_2.drawImage(b11_2.getImage(), b11_2.getX(), 0, b11_2.getWidth(), b11_2.getHeight() * 5 / 4, null);
		buffg_2.drawImage(b12_2.getImage(), b12_2.getX(), 0, b12_2.getWidth(), b12_2.getHeight() * 5 / 4, null);
		buffg_2.drawImage(b21_2.getImage(), b21_2.getX(), 0, b21_2.getWidth(), b21_2.getHeight() * 5 / 4, null);
		buffg_2.drawImage(b22_2.getImage(), b22_2.getX(), 0, b22_2.getWidth(), b22_2.getHeight() * 5 / 4, null);

		// 스테이지 넘어갈시 페이드아웃 인 효과
		if (fadeOn_2) {
			buffg_2.setColor(backFade_2); // 투명하게 하는방법 1
			buffg_2.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		// 발판을 그린다
		for (int i = 0; i < fieldList_2.size(); i++) {

			Field tempFoot_2 = fieldList_2.get(i);

			// 사양을 덜 잡아먹게 하기위한 조치
			if (tempFoot_2.getX() > -90 && tempFoot_2.getX() < 810) { // x값이 -90~810인 객체들만 그린다.

				buffg_2.drawImage(tempFoot_2.getImage(), tempFoot_2.getX(), tempFoot_2.getY(), tempFoot_2.getWidth(),
						tempFoot_2.getHeight(), null);
			}

		}

		// 젤리를 그린다
		for (int i = 0; i < jellyList_2.size(); i++) {

			Jelly tempJelly_2 = jellyList_2.get(i);

			if (tempJelly_2.getX() > -90 && tempJelly_2.getX() < 810) {

				alphaComposite_2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
						(float) tempJelly_2.getAlpha() / 255);
				g2_2.setComposite(alphaComposite_2); // 투명하게 하는방법 2

				buffg_2.drawImage(tempJelly_2.getImage(), tempJelly_2.getX(), tempJelly_2.getY(), tempJelly_2.getWidth(),
						tempJelly_2.getHeight(), null);

				// alpha값을 되돌린다
				alphaComposite_2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
				g2_2.setComposite(alphaComposite_2);
			}
		}

		// 장애물을 그린다
		for (int i = 0; i < tacleList_2.size(); i++) {

			Tacle tempTacle_2 = tacleList_2.get(i);

			if (tempTacle_2.getX() > -90 && tempTacle_2.getX() < 810) {

				buffg_2.drawImage(tempTacle_2.getImage(), tempTacle_2.getX(), tempTacle_2.getY(), tempTacle_2.getWidth(),
						tempTacle_2.getHeight(), null);
			}
		}

		if (c1_2.isInvincible()) { // 무적상태일 경우
			// 쿠키의 alpha값을 받아온다
			alphaComposite_2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) c1_2.getAlpha() / 255);
			g2_2.setComposite(alphaComposite_2);
			
			// 쿠키를 그린다
			buffg_2.drawImage(c1_2.getImage(), c1_2.getX() - 110, c1_2.getY() - 170,
					cookieIc_2.getImage().getWidth(null) * 8 / 10, cookieIc_2.getImage().getHeight(null) * 8 / 10, null);

			// alpha값을 되돌린다
			alphaComposite_2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
			g2_2.setComposite(alphaComposite_2);

		} else { // 무적상태가 아닐 경우
						
			// 쿠키를 그린다
			buffg_2.drawImage(c1_2.getImage(), c1_2.getX() - 110, c1_2.getY() - 170,
					cookieIc_2.getImage().getWidth(null) * 8 / 10, cookieIc_2.getImage().getHeight(null) * 8 / 10, null);
		}
		

		// 피격시 붉은 화면
		if (redScreen_2) {

			alphaComposite_2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 125 / 255);
			g2_2.setComposite(alphaComposite_2);

			buffg_2.drawImage(redBg_2.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

			alphaComposite_2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
			g2_2.setComposite(alphaComposite_2);
		}

		// 점수를 그린다
		Util.drawFancyString(g2_2, Integer.toString(resultScore_2), 600, 58, 30, Color.WHITE);

		// 체력게이지를 그린다
		buffg_2.drawImage(lifeBar_2.getImage(), 20, 30, null);
		buffg_2.setColor(Color.BLACK);
		buffg_2.fillRect(84 + (int) (470 * ((double) c1_2.getHealth() / 1000)), 65,
				1 + 470 - (int) (470 * ((double) c1_2.getHealth() / 1000)), 21);

		// 버튼을 그린다
		buffg_2.drawImage(jumpBtn_2, 0, 320, 132, 100, null);
		buffg_2.drawImage(slideBtn_2, 650, 320, 132, 100, null);

		if (escKeyOn_2) { // esc키를 누를경우 화면을 흐리게 만든다

			// alpha값을 반투명하게 만든다
			alphaComposite_2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 100 / 255);
			g2_2.setComposite(alphaComposite_2);

			buffg_2.setColor(Color.BLACK);

			buffg_2.fillRect(0, 0, 850, 550);

			// alpha값을 되돌린다
			alphaComposite_2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
			g2_2.setComposite(alphaComposite_2);
		}

		// 버퍼이미지를 화면에 출력한다
		g.drawImage(buffImage_2, 0, 440, this);

	}

	// 맵 오브젝트 이미지들을 저장
	private void makeMo() {

		mo1 = new MapObjectImg(new ImageIcon("img/Objectimg/map1img/bg1.png"),
				new ImageIcon("img/Objectimg/map1img/bg2.png"), new ImageIcon("img/Objectimg/map1img/jelly1.png"),
				new ImageIcon("img/Objectimg/map1img/jelly2.png"), new ImageIcon("img/Objectimg/map1img/jelly3.png"),
				new ImageIcon("img/Objectimg/map1img/life.png"), new ImageIcon("img/Objectimg/map1img/effectTest.png"),
				new ImageIcon("img/Objectimg/map1img/fieldIc1.png"),
				new ImageIcon("img/Objectimg/map1img/fieldIc2.png"), new ImageIcon("img/Objectimg/map1img/tacle1.gif"),
				new ImageIcon("img/Objectimg/map1img/tacle2.png"), new ImageIcon("img/Objectimg/map1img/tacle3.png"),
				new ImageIcon("img/Objectimg/map1img/tacle3.png"),
				new ImageIcon("img/Objectimg/map1img/reverse.png"), new ImageIcon("img/Objectimg/map1img/add.png")
				);

		mo2 = new MapObjectImg(new ImageIcon("img/Objectimg/map2img/back1.png"),
				new ImageIcon("img/Objectimg/map2img/back2.png"), new ImageIcon("img/Objectimg/map1img/jelly1.png"),
				new ImageIcon("img/Objectimg/map1img/jelly2.png"), new ImageIcon("img/Objectimg/map1img/jelly3.png"),
				new ImageIcon("img/Objectimg/map1img/life.png"), new ImageIcon("img/Objectimg/map1img/effectTest.png"),
				new ImageIcon("img/Objectimg/map2img/field1.png"), new ImageIcon("img/Objectimg/map2img/field2.png"),
				new ImageIcon("img/Objectimg/map2img/tacle1.png"), new ImageIcon("img/Objectimg/map2img/tacle2.png"),
				new ImageIcon("img/Objectimg/map2img/tacle3.png"), new ImageIcon("img/Objectimg/map2img/tacle3.png"),
				new ImageIcon("img/Objectimg/map1img/reverse.png"), new ImageIcon("img/Objectimg/map1img/add.png")
				);

		mo3 = new MapObjectImg(new ImageIcon("img/Objectimg/map3img/bg.png"),
				new ImageIcon("img/Objectimg/map3img/bg2.png"), new ImageIcon("img/Objectimg/map1img/jelly1.png"),
				new ImageIcon("img/Objectimg/map1img/jelly2.png"), new ImageIcon("img/Objectimg/map1img/jelly3.png"),
				new ImageIcon("img/Objectimg/map1img/life.png"), new ImageIcon("img/Objectimg/map1img/effectTest.png"),
				new ImageIcon("img/Objectimg/map3img/field.png"), new ImageIcon("img/Objectimg/map3img/field2.png"),
				new ImageIcon("img/Objectimg/map3img/tacle1.png"), new ImageIcon("img/Objectimg/map3img/tacle2.png"),
				new ImageIcon("img/Objectimg/map3img/tacle3.png"), new ImageIcon("img/Objectimg/map3img/tacle3.png"),
				new ImageIcon("img/Objectimg/map1img/reverse.png"), new ImageIcon("img/Objectimg/map1img/add.png")
			);

		mo4 = new MapObjectImg(new ImageIcon("img/Objectimg/map4img/bback.png"),
				new ImageIcon("img/Objectimg/map4img/bback2.png"), new ImageIcon("img/Objectimg/map1img/jelly1.png"),
				new ImageIcon("img/Objectimg/map1img/jelly2.png"), new ImageIcon("img/Objectimg/map1img/jelly3.png"),
				new ImageIcon("img/Objectimg/map1img/life.png"), new ImageIcon("img/Objectimg/map1img/effectTest.png"),
				new ImageIcon("img/Objectimg/map4img/ffootTest.png"),
				new ImageIcon("img/Objectimg/map4img/ffootTest2.png"),
				new ImageIcon("img/Objectimg/map4img/tacle1.png"), new ImageIcon("img/Objectimg/map4img/tacle2.png"),
				new ImageIcon("img/Objectimg/map4img/tacle2.png"), new ImageIcon("img/Objectimg/map4img/tacle2.png"),
				new ImageIcon("img/Objectimg/map1img/reverse.png"), new ImageIcon("img/Objectimg/map1img/add.png")
	);
		
		// 2인용
		mo1_2 = new MapObjectImg(new ImageIcon("img/Objectimg/map1img/bg1.png"),
				new ImageIcon("img/Objectimg/map1img/bg2.png"), new ImageIcon("img/Objectimg/map1img/jelly1.png"),
				new ImageIcon("img/Objectimg/map1img/jelly2.png"), new ImageIcon("img/Objectimg/map1img/jelly3.png"),
				new ImageIcon("img/Objectimg/map1img/life.png"), new ImageIcon("img/Objectimg/map1img/effectTest.png"),
				new ImageIcon("img/Objectimg/map1img/fieldIc1.png"),
				new ImageIcon("img/Objectimg/map1img/fieldIc2.png"), new ImageIcon("img/Objectimg/map1img/tacle1.gif"),
				new ImageIcon("img/Objectimg/map1img/tacle2.png"), new ImageIcon("img/Objectimg/map1img/tacle3.png"),
				new ImageIcon("img/Objectimg/map1img/tacle3.png"),
				new ImageIcon("img/Objectimg/map1img/reverse.png"), new ImageIcon("img/Objectimg/map1img/add.png")
				);

		mo2_2 = new MapObjectImg(new ImageIcon("img/Objectimg/map2img/back1.png"),
				new ImageIcon("img/Objectimg/map2img/back2.png"), new ImageIcon("img/Objectimg/map1img/jelly1.png"),
				new ImageIcon("img/Objectimg/map1img/jelly2.png"), new ImageIcon("img/Objectimg/map1img/jelly3.png"),
				new ImageIcon("img/Objectimg/map1img/life.png"), new ImageIcon("img/Objectimg/map1img/effectTest.png"),
				new ImageIcon("img/Objectimg/map2img/field1.png"), new ImageIcon("img/Objectimg/map2img/field2.png"),
				new ImageIcon("img/Objectimg/map2img/tacle1.png"), new ImageIcon("img/Objectimg/map2img/tacle2.png"),
				new ImageIcon("img/Objectimg/map2img/tacle3.png"), new ImageIcon("img/Objectimg/map2img/tacle3.png"),
				new ImageIcon("img/Objectimg/map1img/reverse.png"), new ImageIcon("img/Objectimg/map1img/add.png")
	);

		mo3_2 = new MapObjectImg(new ImageIcon("img/Objectimg/map3img/bg.png"),
				new ImageIcon("img/Objectimg/map3img/bg2.png"), new ImageIcon("img/Objectimg/map1img/jelly1.png"),
				new ImageIcon("img/Objectimg/map1img/jelly2.png"), new ImageIcon("img/Objectimg/map1img/jelly3.png"),
				new ImageIcon("img/Objectimg/map1img/life.png"), new ImageIcon("img/Objectimg/map1img/effectTest.png"),
				new ImageIcon("img/Objectimg/map3img/field.png"), new ImageIcon("img/Objectimg/map3img/field2.png"),
				new ImageIcon("img/Objectimg/map3img/tacle1.png"), new ImageIcon("img/Objectimg/map3img/tacle2.png"),
				new ImageIcon("img/Objectimg/map3img/tacle3.png"), new ImageIcon("img/Objectimg/map3img/tacle3.png"),
				new ImageIcon("img/Objectimg/map1img/reverse.png"), new ImageIcon("img/Objectimg/map1img/add.png")
		);

		mo4_2 = new MapObjectImg(new ImageIcon("img/Objectimg/map4img/bback.png"),
				new ImageIcon("img/Objectimg/map4img/bback2.png"), new ImageIcon("img/Objectimg/map1img/jelly1.png"),
				new ImageIcon("img/Objectimg/map1img/jelly2.png"), new ImageIcon("img/Objectimg/map1img/jelly3.png"),
				new ImageIcon("img/Objectimg/map1img/life.png"), new ImageIcon("img/Objectimg/map1img/effectTest.png"),
				new ImageIcon("img/Objectimg/map4img/ffootTest.png"),
				new ImageIcon("img/Objectimg/map4img/ffootTest2.png"),
				new ImageIcon("img/Objectimg/map4img/tacle1.png"), new ImageIcon("img/Objectimg/map4img/tacle2.png"),
				new ImageIcon("img/Objectimg/map4img/tacle2.png"), new ImageIcon("img/Objectimg/map4img/tacle2.png"),
				new ImageIcon("img/Objectimg/map1img/reverse.png"), new ImageIcon("img/Objectimg/map1img/add.png")
			);
	}

	// 메인에서 받은 쿠키 이미지 아이콘들을 인스턴스화
	private void initCookieImg(CookieImg ci) {
		// 쿠키 이미지 아이콘들
		cookieIc = ci.getCookieIc(); // 기본모션
		jumpIc = ci.getJumpIc(); // 점프모션
		doubleJumpIc = ci.getDoubleJumpIc(); // 더블점프모션
		fallIc = ci.getFallIc(); // 낙하모션(더블 점프 후)
		slideIc = ci.getSlideIc(); // 슬라이드 모션
		hitIc = ci.getHitIc(); // 부딛히는 모션
	}

	private void initCookieImg2(CookieImg ci) {
		// 쿠키 이미지 아이콘들
		cookieIc_2 = ci.getCookieIc(); // 기본모션
		jumpIc_2 = ci.getJumpIc(); // 점프모션
		doubleJumpIc_2 = ci.getDoubleJumpIc(); // 더블점프모션
		fallIc_2 = ci.getFallIc(); // 낙하모션(더블 점프 후)
		slideIc_2 = ci.getSlideIc(); // 슬라이드 모션
		hitIc_2 = ci.getHitIc(); // 부딛히는 모션
	}


	// 젤리 발판 장애물 등을 인스턴스화
	private void initImageIcon(MapObjectImg mo) {

		// 젤리 이미지 아이콘들
		jelly1Ic = mo.getJelly1Ic();
		jelly2Ic = mo.getJelly2Ic();
		jelly3Ic = mo.getJelly3Ic();
		jellyHPIc = mo.getJellyHPIc();

		jellyEffectIc = mo.getJellyEffectIc();

		// 발판 이미지 아이콘들
		field1Ic = mo.getField1Ic(); // 발판
		field2Ic = mo.getField2Ic(); // 공중발판

		// 장애물 이미지 아이콘들
		tacle10Ic = mo.getTacle10Ic(); // 1칸 장애물
		tacle20Ic = mo.getTacle20Ic(); // 2칸 장애물
		tacle30Ic = mo.getTacle30Ic(); // 3칸 장애물
		tacle40Ic = mo.getTacle40Ic(); // 4칸 장애물

		jellyReverseIc = mo.getJellyReverseIc();
		jellyAddIc = mo.getJellyAddIc();
	}

	private void initImageIcon2(MapObjectImg mo) {

		// 젤리 이미지 아이콘들
		jelly1Ic_2 = mo.getJelly1Ic();
		jelly2Ic_2 = mo.getJelly2Ic();
		jelly3Ic_2 = mo.getJelly3Ic();
		jellyHPIc_2 = mo.getJellyHPIc();

		jellyEffectIc_2 = mo.getJellyEffectIc();

		// 발판 이미지 아이콘들
		field1Ic_2 = mo.getField1Ic(); // 발판
		field2Ic_2 = mo.getField2Ic(); // 공중발판

		// 장애물 이미지 아이콘들
		tacle10Ic_2 = mo.getTacle10Ic(); // 1칸 장애물
		tacle20Ic_2 = mo.getTacle20Ic(); // 2칸 장애물
		tacle30Ic_2 = mo.getTacle30Ic(); // 3칸 장애물
		tacle40Ic_2 = mo.getTacle40Ic(); // 4칸 장애물

		jellyReverseIc_2 = mo.getJellyReverseIc();
		jellyAddIc_2 = mo.getJellyAddIc();
	}

	// 맵의 구조를 그림판 이미지를 받아서 세팅
	private void initMap(int num, int mapLength) {

		String tempMap = null;
		int tempMapLength = 0;

		if (num == 1) {
			tempMap = "img/map/map1.png";
		} else if (num == 2) {
			tempMap = "img/map/map2.png";
		} else if (num == 3) {
			tempMap = "img/map/map3.png";
		} else if (num == 4) {
			tempMap = "img/map/map4.png";
		}

		// 맵 정보 불러오기
		try {
			sizeArr = Util.getSize(tempMap); // 맵 사이즈를 배열에 저장
			colorArr = Util.getPic(tempMap); // 맵 픽셀값을 배열에 저장
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		tempMapLength = sizeArr[0];
		int maxX = sizeArr[0]; // 맵의 넓이
		int maxY = sizeArr[1]; // 맵의 높이

		for (int i = 0; i < maxX; i += 1) { // 젤리는 1칸을 차지하기 때문에 1,1사이즈로 반복문을 돌린다.
			for (int j = 0; j < maxY; j += 1) {
				if (colorArr[i][j] == 16776960) { // 색값이 16776960일 경우 기본젤리 생성
					// 좌표에 40을 곱하고, 넓이와 높이는 30으로 한다.
					jellyList.add(new Jelly(jelly1Ic.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 255, 1234));

				} else if (colorArr[i][j] == 13158400) { // 색값이 13158400일 경우 노란젤리 생성
					// 좌표에 40을 곱하고, 넓이와 높이는 30으로 한다.
					jellyList.add(new Jelly(jelly2Ic.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 255, 2345));

				} else if (colorArr[i][j] == 9868800) { // 색값이 9868800일 경우 노란젤리 생성
					// 좌표에 40을 곱하고, 넓이와 높이는 30으로 한다.
					jellyList.add(new Jelly(jelly3Ic.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 255, 3456));

				} else if (colorArr[i][j] == 16737280) { // 색값이 16737280일 경우 피 물약 생성
					// 좌표에 40을 곱하고, 넓이와 높이는 30으로 한다.
					Random rd = new Random();
					int rn = rd.nextInt(1000);
					// 아이템 랜덤 설정
					if(rn < 300) {
						jellyList.add(new Jelly(jellyReverseIc.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 255, 4567));
					}
					else if(rn < 600) {
						jellyList.add(new Jelly(jellyAddIc.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 255, 4567));
					}
					else {
						jellyList.add(new Jelly(jellyHPIc.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 255, 4567));
					}
					
				}
			}
		}

		for (int i = 0; i < maxX; i += 2) { // 발판은 4칸을 차지하는 공간이기 때문에 2,2사이즈로 반복문을 돌린다.
			for (int j = 0; j < maxY; j += 2) {
				if (colorArr[i][j] == 0) { // 색값이 0 일경우 (검은색)
					// 좌표에 40을 곱하고, 넓이와 높이는 80으로 한다.
					fieldList.add(new Field(field1Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 80));

				} else if (colorArr[i][j] == 6579300) { // 색값이 6579300 일경우 (회색)
					// 좌표에 40을 곱하고, 넓이와 높이는 80으로 한다.
					fieldList.add(new Field(field2Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 80));
				}
			}
		}

		for (int i = 0; i < maxX; i += 2) { // 장애물은 4칸 이상을 차지한다. 추후 수정
			for (int j = 0; j < maxY; j += 2) {
				if (colorArr[i][j] == 16711680) { // 색값이 16711680일 경우 (빨간색) 1칸
					// 좌표에 40을 곱하고, 넓이와 높이는 80으로 한다.
					tacleList.add(new Tacle(tacle10Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 80, 0));

				} else if (colorArr[i][j] == 16711830) { // 색값이 16711830일 경우 (분홍) 2칸
					// 좌표에 40을 곱하고, 넓이와 높이는 160으로 한다.
					tacleList.add(new Tacle(tacle20Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 160, 0));

				} else if (colorArr[i][j] == 16711935) { // 색값이 16711830일 경우 (핫핑크) 3칸
					// 좌표에 40을 곱하고, 넓이와 높이는 240으로 한다.
					tacleList.add(new Tacle(tacle30Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 240, 0));
				}
				
				else if(is_add2) { //add 아이템 먹으면 장애물 생성
					
					int tn = tacleList.size() - 1;
					int ti = (tacleList.get(tn).getX() - mapLength * 40) / 40;
					int tj = tacleList.get(tn).getY() / 40;
					
					tacleList.add(new Tacle(tacle20Ic.getImage(), ti * 40 + mapLength * 40, tj * 40, 80, 80, 0));
					
					repaint();
				}
				
			
			}
		}


		
		this.mapLength = this.mapLength + tempMapLength;
	}

	//2인용
	// 맵의 구조를 그림판 이미지를 받아서 세팅
	private void initMap2(int num, int mapLength) {

		String tempMap_2 = null;
		int tempMapLength_2 = 0;

		if (num == 1) {
			tempMap_2 = "img/map/map1.png";
		} else if (num == 2) {
			tempMap_2 = "img/map/map2.png";
		} else if (num == 3) {
			tempMap_2 = "img/map/map3.png";
		} else if (num == 4) {
			tempMap_2 = "img/map/map4.png";
		}

		// 맵 정보 불러오기
		try {
			sizeArr_2 = Util.getSize(tempMap_2); // 맵 사이즈를 배열에 저장
			colorArr_2 = Util.getPic(tempMap_2); // 맵 픽셀값을 배열에 저장
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		tempMapLength_2 = sizeArr_2[0];
		int maxX_2 = sizeArr_2[0]; // 맵의 넓이
		int maxY_2 = sizeArr_2[1]; // 맵의 높이

		for (int i = 0; i < maxX_2; i += 1) { // 젤리는 1칸을 차지하기 때문에 1,1사이즈로 반복문을 돌린다.
			for (int j = 0; j < maxY_2; j += 1) {
				if (colorArr_2[i][j] == 16776960) { // 색값이 16776960일 경우 기본젤리 생성
					// 좌표에 40을 곱하고, 넓이와 높이는 30으로 한다.
					jellyList_2.add(new Jelly(jelly1Ic_2.getImage(), i * 40 + mapLength_2 * 40, j * 40, 30, 30, 255, 1234));

				} else if (colorArr_2[i][j] == 13158400) { // 색값이 13158400일 경우 노란젤리 생성
					// 좌표에 40을 곱하고, 넓이와 높이는 30으로 한다.
					jellyList_2.add(new Jelly(jelly2Ic_2.getImage(), i * 40 + mapLength_2 * 40, j * 40, 30, 30, 255, 2345));

				} else if (colorArr_2[i][j] == 9868800) { // 색값이 9868800일 경우 노란젤리 생성
					// 좌표에 40을 곱하고, 넓이와 높이는 30으로 한다.
					jellyList_2.add(new Jelly(jelly3Ic_2.getImage(), i * 40 + mapLength_2 * 40, j * 40, 30, 30, 255, 3456));

				} else if (colorArr_2[i][j] == 16737280) { // 색값이 16737280일 경우 피 물약 생성
					// 좌표에 40을 곱하고, 넓이와 높이는 30으로 한다.
					Random rd = new Random();
					int rn = rd.nextInt(1000);
					// 아이템 랜덤 설정
					if(rn < 300) {
						jellyList_2.add(new Jelly(jellyReverseIc_2.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 255, 4567));
					}
					else if(rn < 600) {
						jellyList_2.add(new Jelly(jellyAddIc_2.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 255, 4567));
					}
					else {
						jellyList_2.add(new Jelly(jellyHPIc_2.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 255, 4567));
					}
				}
			}
		}

		for (int i = 0; i < maxX_2; i += 2) { // 발판은 4칸을 차지하는 공간이기 때문에 2,2사이즈로 반복문을 돌린다.
			for (int j = 0; j < maxY_2; j += 2) {
				if (colorArr_2[i][j] == 0) { // 색값이 0 일경우 (검은색)
					// 좌표에 40을 곱하고, 넓이와 높이는 80으로 한다.
					fieldList_2.add(new Field(field1Ic_2.getImage(), i * 40 + mapLength_2 * 40, j * 40, 80, 80));

				} else if (colorArr_2[i][j] == 6579300) { // 색값이 6579300 일경우 (회색)
					// 좌표에 40을 곱하고, 넓이와 높이는 80으로 한다.
					fieldList_2.add(new Field(field2Ic_2.getImage(), i * 40 + mapLength_2 * 40, j * 40, 80, 80));
				}
			}
		}

		for (int i = 0; i < maxX_2; i += 2) { // 장애물은 4칸 이상을 차지한다. 추후 수정
			for (int j = 0; j < maxY_2; j += 2) {
				if (colorArr_2[i][j] == 16711680) { // 색값이 16711680일 경우 (빨간색) 1칸
					// 좌표에 40을 곱하고, 넓이와 높이는 80으로 한다.
					tacleList_2.add(new Tacle(tacle10Ic_2.getImage(), i * 40 + mapLength_2 * 40, j * 40, 80, 80, 0));

				} else if (colorArr_2[i][j] == 16711830) { // 색값이 16711830일 경우 (분홍) 2칸
					// 좌표에 40을 곱하고, 넓이와 높이는 160으로 한다.
					tacleList_2.add(new Tacle(tacle20Ic_2.getImage(), i * 40 + mapLength_2 * 40, j * 40, 80, 160, 0));

				} else if (colorArr_2[i][j] == 16711935) { // 색값이 16711830일 경우 (핫핑크) 3칸
					// 좌표에 40을 곱하고, 넓이와 높이는 240으로 한다.
					tacleList_2.add(new Tacle(tacle30Ic_2.getImage(), i * 40 + mapLength_2 * 40, j * 40, 80, 240, 0));
				}
				else if(is_add) { //add 아이템 먹으면 장애물 생성
					int tn = tacleList_2.size() - 1;
					int ti = (tacleList_2.get(tn).getX() - mapLength_2 * 40) / 40;
					int tj = tacleList_2.get(tn).getY() / 40;
					
					tacleList_2.add(new Tacle(tacle10Ic_2.getImage(), i * 40 + mapLength_2 * 40, j * 40, 80, 80, 0));
					
					repaint();

			}
			}
			
		
		}

		this.mapLength_2 = this.mapLength_2 + tempMapLength_2;
	}


	// makeMo, initImageIcon, imitMap 메서드를 이용해서 객체 생성
	private void initObject() {

		// 생명게이지 이미지아이콘
		lifeBar = new ImageIcon("img/Objectimg/lifebar/lifeBar1.png");

		// 피격 붉은 이미지
		redBg = new ImageIcon("img/Objectimg/lifebar/redBg.png");

		// 점프버튼
		jumpButtonIconUp = new ImageIcon("img/Objectimg/lifebar/jumpno.png");
		jumpButtonIconDown = new ImageIcon("img/Objectimg/lifebar/jumpdim.png");

		// 슬라이드 버튼
		slideIconUp = new ImageIcon("img/Objectimg/lifebar/slideno.png");
		slideIconDown = new ImageIcon("img/Objectimg/lifebar/slidedim.png");

		jumpBtn = jumpButtonIconUp.getImage();
		slideBtn = slideIconUp.getImage();

		jellyList = new ArrayList<>(); // 젤리 리스트

		fieldList = new ArrayList<>(); // 발판 리스트

		tacleList = new ArrayList<>(); // 장애물 리스트
		
		mapLengthList = new ArrayList<>(); // 다음 맵의 시작지점을 확인하기위한 배열

		// 맵 인스턴스들을 생성

		makeMo();

		initImageIcon(mo1);
		initMap(1, mapLength);
		mapLengthList.add(mapLength);

		initImageIcon(mo2);
		initMap(2, mapLength);
		mapLengthList.add(mapLength);

		initImageIcon(mo3);
		initMap(3, mapLength);
		mapLengthList.add(mapLength);

		initImageIcon(mo4);
		initMap(4, mapLength);

		// 배경이미지 아이콘
		backIc = mo1.getBackIc();
		secondBackIc = mo1.getSecondBackIc();

		backIc2 = mo2.getBackIc();
		secondBackIc2 = mo2.getSecondBackIc();

		backIc3 = mo3.getBackIc();
		secondBackIc3 = mo3.getSecondBackIc();

		backIc4 = mo4.getBackIc();
		secondBackIc4 = mo4.getSecondBackIc();

		// 쿠키 인스턴스 생성 / 기본 자료는 클래스안에 내장 되어 있기 때문에 이미지만 넣었다.
		c1 = new Cookie(cookieIc.getImage());

		// 쿠키의 정면 위치 / 쿠키의 x값과 높이를 더한 값
		face = c1.getX() + c1.getWidth();

		// 쿠키의 발밑 위치 / 쿠키의 y값과 높이를 더한 값
		foot = c1.getY() + c1.getHeight();

		// 배경1-1 인스턴스 생성
		b11 = new Back(backIc.getImage(), 0, 0, backIc.getImage().getWidth(null), backIc.getImage().getHeight(null));

		// 배경1-2 인스턴스 생성
		b12 = new Back(backIc.getImage(), backIc.getImage().getWidth(null), 0, // y 값 (조정 필요)
				backIc.getImage().getWidth(null), backIc.getImage().getHeight(null));

		// 배경2-1 인스턴스 생성
		b21 = new Back(secondBackIc.getImage(), 0, 0, secondBackIc.getImage().getWidth(null),
				secondBackIc.getImage().getHeight(null));

		// 배경2-2 인스턴스 생성
		b22 = new Back(secondBackIc.getImage(), secondBackIc.getImage().getWidth(null), 0, // y 값 (조정 필요)
				secondBackIc.getImage().getWidth(null), secondBackIc.getImage().getHeight(null));

		backFade = new Color(0, 0, 0, 0);


		// 2인용
		// 생명게이지 이미지아이콘
		lifeBar_2 = new ImageIcon("img/Objectimg/lifebar/lifeBar1.png");

		// 피격 붉은 이미지
		redBg_2 = new ImageIcon("img/Objectimg/lifebar/redBg.png");

		// 점프버튼
		jumpButtonIconUp_2 = new ImageIcon("img/Objectimg/lifebar/jumpno.png");
		jumpButtonIconDown_2 = new ImageIcon("img/Objectimg/lifebar/jumpdim.png");

		// 슬라이드 버튼
		slideIconUp_2 = new ImageIcon("img/Objectimg/lifebar/slideno.png");
		slideIconDown_2 = new ImageIcon("img/Objectimg/lifebar/slidedim.png");

		jumpBtn_2 = jumpButtonIconUp_2.getImage();
		slideBtn_2 = slideIconUp_2.getImage();

		jellyList_2 = new ArrayList<>(); // 젤리 리스트

		fieldList_2 = new ArrayList<>(); // 발판 리스트

		tacleList_2 = new ArrayList<>(); // 장애물 리스트

		mapLengthList_2 = new ArrayList<>(); // 다음 맵의 시작지점을 확인하기위한 배열

		// 맵 인스턴스들을 생성

		makeMo();

		initImageIcon2(mo1_2);
		initMap2(1, mapLength_2);
		mapLengthList_2.add(mapLength_2);

		initImageIcon2(mo2_2);
		initMap2(2, mapLength_2);
		mapLengthList_2.add(mapLength_2);

		initImageIcon2(mo3_2);
		initMap2(3, mapLength_2);
		mapLengthList_2.add(mapLength_2);

		initImageIcon2(mo4_2);
		initMap2(4, mapLength_2);

		// 배경이미지 아이콘
		backIc_2 = mo1_2.getBackIc();
		secondBackIc_2 = mo1_2.getSecondBackIc();

		backIc2_2 = mo2_2.getBackIc();
		secondBackIc2_2 = mo2_2.getSecondBackIc();

		backIc3_2 = mo3_2.getBackIc();
		secondBackIc3_2 = mo3_2.getSecondBackIc();

		backIc4_2 = mo4_2.getBackIc();
		secondBackIc4_2 = mo4_2.getSecondBackIc();

		// 쿠키 인스턴스 생성 / 기본 자료는 클래스안에 내장 되어 있기 때문에 이미지만 넣었다.
		c1_2 = new Cookie(cookieIc_2.getImage());



	
		// 쿠키의 정면 위치 / 쿠키의 x값과 높이를 더한 값
		face_2 = c1_2.getX() + c1_2.getWidth();

		// 쿠키의 발밑 위치 / 쿠키의 y값과 높이를 더한 값
		foot_2 = c1_2.getY() + c1_2.getHeight();

		// 배경1-1 인스턴스 생성
		b11_2 = new Back(backIc_2.getImage(), 0, 460, backIc_2.getImage().getWidth(null), backIc_2.getImage().getHeight(null));

		// 배경1-2 인스턴스 생성
		b12_2 = new Back(backIc_2.getImage(), backIc_2.getImage().getWidth(null), 460, // y 값 (조정 필요)
				backIc_2.getImage().getWidth(null), backIc_2.getImage().getHeight(null));

		// 배경2-1 인스턴스 생성
		b21_2 = new Back(secondBackIc_2.getImage(), 0, 460, secondBackIc_2.getImage().getWidth(null),
				secondBackIc_2.getImage().getHeight(null));

		// 배경2-2 인스턴스 생성
		b22_2 = new Back(secondBackIc_2.getImage(), secondBackIc_2.getImage().getWidth(null), 460, // y 값 (조정 필요)
				secondBackIc_2.getImage().getWidth(null), secondBackIc_2.getImage().getHeight(null));

		backFade_2 = new Color(0, 0, 0, 0);

	}

	// 리스너 추가 메서드
	private void initListener() {
		addKeyListener(new KeyAdapter() { // 키 리스너 추가

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { // esc키를 눌렀을 때
					if (!escKeyOn) {
						escKeyOn = true;
						add(escButton);
						repaint(); // 화면을 어둡게 하기위한 리페인트
					} else {
						remove(escButton);
						escKeyOn = false;
					}

					if (!escKeyOn_2) {
						escKeyOn_2 = true;
						add(escButton_2);
						repaint(); // 화면을 어둡게 하기위한 리페인트
					} else {
						remove(escButton_2);
						escKeyOn_2 = false;
					}
				}

				if (!escKeyOn) {
					if (e.getKeyCode() == KeyEvent.VK_W) {// 스페이스 키를 누르고 더블점프가 2가 아닐때
						if(is_reverse) {
							slideBtn = slideIconDown.getImage();
							downKeyOn = true; // downKeyOn 변수를 true로

							if (c1.getImage() != slideIc.getImage() // 쿠키이미지가 슬라이드 이미지가 아니고
									&& !c1.isJump() // 점프 중이 아니며
									&& !c1.isFall()) { // 낙하 중도 아닐 때

								c1.setImage(slideIc.getImage()); // 이미지를 슬라이드이미지로 변경

							}
						}
						else {
							jumpBtn = jumpButtonIconDown.getImage();
							if (c1.getCountJump() < 2) {
								jump(); // 점프 메서드 가동
							}
						}
					}

					if (e.getKeyCode() == KeyEvent.VK_S) { // 다운키를 눌렀을 때
						if(is_reverse) {
							jumpBtn = jumpButtonIconDown.getImage();
							if (c1.getCountJump() < 2) {
								jump(); // 점프 메서드 가동
							}
						}
						else {
							slideBtn = slideIconDown.getImage();
							downKeyOn = true; // downKeyOn 변수를 true로

							if (c1.getImage() != slideIc.getImage() // 쿠키이미지가 슬라이드 이미지가 아니고
									&& !c1.isJump() // 점프 중이 아니며
									&& !c1.isFall()) { // 낙하 중도 아닐 때

								c1.setImage(slideIc.getImage()); // 이미지를 슬라이드이미지로 변경

							}
						}
					}

				}

				if (!escKeyOn_2) {
					
					if (e.getKeyCode() == KeyEvent.VK_UP) {// 스페이스 키를 누르고 더블점프가 2가 아닐때
						if(is_reverse2) {
							slideBtn_2 = slideIconDown_2.getImage();
							downKeyOn_2 = true; // downKeyOn 변수를 true로

							if (c1_2.getImage() != slideIc_2.getImage() // 쿠키이미지가 슬라이드 이미지가 아니고
									&& !c1_2.isJump() // 점프 중이 아니며
									&& !c1_2.isFall()) { // 낙하 중도 아닐 때

								c1_2.setImage(slideIc_2.getImage()); // 이미지를 슬라이드이미지로 변경

							}
						}
						else {
							jumpBtn_2 = jumpButtonIconDown_2.getImage();
							if (c1_2.getCountJump() < 2) {
								jump2(); // 점프 메서드 가동
							}
						}
					}

					if (e.getKeyCode() == KeyEvent.VK_DOWN) { // 다운키를 눌렀을 때
						if(is_reverse2) {
							jumpBtn_2 = jumpButtonIconDown_2.getImage();
							if (c1_2.getCountJump() < 2) {
								jump2(); // 점프 메서드 가동
							}
						}
						else {
							slideBtn_2 = slideIconDown_2.getImage();
							downKeyOn_2 = true; // downKeyOn 변수를 true로

							if (c1_2.getImage() != slideIc_2.getImage() // 쿠키이미지가 슬라이드 이미지가 아니고
									&& !c1_2.isJump() // 점프 중이 아니며
									&& !c1_2.isFall()) { // 낙하 중도 아닐 때

								c1_2.setImage(slideIc_2.getImage()); // 이미지를 슬라이드이미지로 변경

							}
						}
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_S) { // 다운키를 뗐을 때
					slideBtn = slideIconUp.getImage();
					downKeyOn = false; // downKeyOn 변수를 false로

					if (c1.getImage() != cookieIc.getImage() // 쿠키이미지가 기본이미지가 아니고
							&& !c1.isJump() // 점프 중이 아니며
							&& !c1.isFall()) { // 낙하 중도 아닐 때

						c1.setImage(cookieIc.getImage()); // 이미지를 기본이미지로 변경
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) { // 다운키를 뗐을 때
					slideBtn_2 = slideIconUp_2.getImage();
					downKeyOn_2 = false; // downKeyOn 변수를 false로

					if (c1_2.getImage() != cookieIc_2.getImage() // 쿠키이미지가 기본이미지가 아니고
							&& !c1_2.isJump() // 점프 중이 아니며
							&& !c1_2.isFall()) { // 낙하 중도 아닐 때

						c1_2.setImage(cookieIc_2.getImage()); // 이미지를 기본이미지로 변경
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_W) {
					jumpBtn = jumpButtonIconUp.getImage();
				}

				if (e.getKeyCode() == KeyEvent.VK_UP) {
					jumpBtn_2 = jumpButtonIconUp_2.getImage();
				}
			}
		});
	}

	// 리페인트 전용 쓰레드 추가 메서드
	private void runRepaint() {
		// 리페인트 전용 쓰레드
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					repaint();

					if (escKeyOn) { // esc 키를 누를경우 리페인트를 멈춘다
						while (escKeyOn) {
							try {
								Thread.sleep(10);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}

					try {
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	// 리페인트 전용 쓰레드 추가 메서드
	private void runRepaint2() {
		// 리페인트 전용 쓰레드
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					repaint();

					if (escKeyOn_2) { // esc 키를 누를경우 리페인트를 멈춘다
						while (escKeyOn_2) {
							try {
								Thread.sleep(10);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}

					try {
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	JLabel readyLabel; //화면에 dead 나타내기

	// 화면을 움직이고 젤리를 먹거나, 장애물에 부딛히는 등의 이벤트를 발생시키는 메서드
	private void mapMove() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {

					if (runPage > 800) { // 800픽셀 이동 마다 체력이 10씩 감소한다 (추후 맵길이에 ?上?감소량 조절)
						c1.setHealth(c1.getHealth() - 10);
						runPage = 0;
					}

					runPage += gameSpeed; // 화면이 이동하면 runPage에 이동한 만큼 저장된다.

					foot = c1.getY() + c1.getHeight(); // 캐릭터 발 위치 재스캔
					if (foot > 1999 || c1.getHealth() < 1) {
						one_die=true;
						
						while(two_die==false) {	//먼저 죽으면 대기
							
							readyLabel = new JLabel("Dead");	
							readyLabel .setHorizontalAlignment(SwingConstants.CENTER);
							readyLabel .setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 37));
							readyLabel .setBounds(150, 50, 600, 500);
							add(readyLabel );
							
							try { // 1.5초 대기
								Thread.sleep(7500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
						}
						if(two_die==true) {	
						main.getEndPanel2().setResultScore(resultScore);
						cl.show(superFrame.getContentPane(), "end2");
						main.setGamePanel1(new GamePanel2(superFrame, cl, main));
					
					
						superFrame.requestFocus();
						escKeyOn = true;
						}
						
						
						try { // 1.5초 대기
							Thread.sleep(6000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						one_die=false;
					}



					// 배경 이미지 변경
					if (fadeOn == false) { // 페이드아웃인 상태가 아닐때
						if (mapLength > mapLengthList.get(2) * 40 + 800 && b11.getImage() != backIc4.getImage()) {
							fadeOn = true;

							new Thread(new Runnable() {

								@Override
								public void run() {

									backFadeOut();

									b11 = new Back(backIc4.getImage(), 0, 0, backIc4.getImage().getWidth(null),
											backIc4.getImage().getHeight(null));

									b12 = new Back(backIc4.getImage(), backIc4.getImage().getWidth(null), 0,
											backIc4.getImage().getWidth(null), backIc4.getImage().getHeight(null));

									b21 = new Back(secondBackIc4.getImage(), 0, 0,
											secondBackIc4.getImage().getWidth(null),
											secondBackIc4.getImage().getHeight(null));

									b22 = new Back(secondBackIc4.getImage(), secondBackIc4.getImage().getWidth(null), 0,
											secondBackIc4.getImage().getWidth(null),
											secondBackIc4.getImage().getHeight(null));

									backFadeIn();
									fadeOn = false;
								}
							}).start();

						} else if (mapLength > mapLengthList.get(1) * 40 + 800
								&& mapLength < mapLengthList.get(2) * 40 + 800
								&& b11.getImage() != backIc3.getImage()) {
							fadeOn = true;

							new Thread(new Runnable() {

								@Override
								public void run() {

									backFadeOut();

									b11 = new Back(backIc3.getImage(), 0, 0, backIc3.getImage().getWidth(null),
											backIc3.getImage().getHeight(null));

									b12 = new Back(backIc3.getImage(), backIc3.getImage().getWidth(null), 0,
											backIc3.getImage().getWidth(null), backIc3.getImage().getHeight(null));

									b21 = new Back(secondBackIc3.getImage(), 0, 0,
											secondBackIc3.getImage().getWidth(null),
											secondBackIc3.getImage().getHeight(null));

									b22 = new Back(secondBackIc3.getImage(), secondBackIc3.getImage().getWidth(null), 0,
											secondBackIc3.getImage().getWidth(null),
											secondBackIc3.getImage().getHeight(null));

									backFadeIn();
									fadeOn = false;
								}
							}).start();

						} else if (mapLength > mapLengthList.get(0) * 40 + 800
								&& mapLength < mapLengthList.get(1) * 40 + 800
								&& b11.getImage() != backIc2.getImage()) {
							fadeOn = true;

							new Thread(new Runnable() {

								@Override
								public void run() {

									backFadeOut();

									b11 = new Back(backIc2.getImage(), 0, 0, backIc2.getImage().getWidth(null),
											backIc2.getImage().getHeight(null));

									b12 = new Back(backIc2.getImage(), backIc2.getImage().getWidth(null), 0,
											backIc2.getImage().getWidth(null), backIc2.getImage().getHeight(null));

									b21 = new Back(secondBackIc2.getImage(), 0, 0,
											secondBackIc2.getImage().getWidth(null),
											secondBackIc2.getImage().getHeight(null));

									b22 = new Back(secondBackIc2.getImage(), secondBackIc2.getImage().getWidth(null), 0,
											secondBackIc2.getImage().getWidth(null),
											secondBackIc2.getImage().getHeight(null));

									backFadeIn();
									fadeOn = false;
								}
							}).start();
						}
					}


					// 배경이미지 변경을 위한 맵이동 길이 측정
					mapLength += gameSpeed;

					if (b11.getX() < -(b11.getWidth() - 1)) { // 배경1-1 이 -(배경넓이)보다 작으면, 즉 화면밖으로 모두나가면 배경 1-2뒤에 붙음
						b11.setX(b11.getWidth());
					}
					if (b12.getX() < -(b12.getWidth() - 1)) { // 배경1-2 가 -(배경넓이)보다 작으면, 즉 화면밖으로 모두나가면 배경 1-1뒤에 붙음
						b12.setX(b12.getWidth());
					}

					if (b21.getX() < -(b21.getWidth() - 1)) { // 배경1-1 이 -(배경넓이)보다 작으면, 즉 화면밖으로 모두나가면 배경 1-2뒤에 붙음
						b21.setX(b21.getWidth());
					}
					if (b22.getX() < -(b22.getWidth() - 1)) { // 배경1-2 가 -(배경넓이)보다 작으면, 즉 화면밖으로 모두나가면 배경 1-1뒤에 붙음
						b22.setX(b22.getWidth());
					}

					// 배경의 x좌표를 -1 해준다 (왼쪽으로 흐르는 효과)
					b11.setX(b11.getX() - gameSpeed / 3);
					b12.setX(b12.getX() - gameSpeed / 3);

					b21.setX(b21.getX() - gameSpeed * 2 / 3);
					b22.setX(b22.getX() - gameSpeed * 2 / 3);


					// 발판위치를 -3 씩 해준다. (왼쪽으로 흐르는 효과)
					for (int i = 0; i < fieldList.size(); i++) {

						Field tempField = fieldList.get(i); // 임시 변수에 리스트 안에 있는 개별 발판을 불러오자

						if (tempField.getX() < -90) { // 발판의 x좌표가 -90 미만이면 해당 발판을 제거한다.(최적화)

							fieldList.remove(tempField);

						} else {

							tempField.setX(tempField.getX() - gameSpeed); // 위 조건에 해당이 안되면 x좌표를 줄이자

						}
					}


					// 젤리위치를 -4 씩 해준다.
					for (int i = 0; i < jellyList.size(); i++) {

						Jelly tempJelly = jellyList.get(i); // 임시 변수에 리스트 안에 있는 개별 젤리를 불러오자

						if (tempJelly.getX() < -90) { // 젤리의 x 좌표가 -90 미만이면 해당 젤리를 제거한다.(최적화)

							fieldList.remove(tempJelly);

						} else {

							tempJelly.setX(tempJelly.getX() - gameSpeed); // 위 조건에 해당이 안되면 x좌표를 줄이자
							if (tempJelly.getImage() == jellyEffectIc.getImage() && tempJelly.getAlpha() > 4) {
								tempJelly.setAlpha(tempJelly.getAlpha() - 5);
							}

							foot = c1.getY() + c1.getHeight(); // 캐릭터 발 위치 재스캔

							if ( // 캐릭터의 범위 안에 젤리가 있으면 아이템을 먹는다.
							c1.getImage() != slideIc.getImage()
									&& tempJelly.getX() + tempJelly.getWidth() * 20 / 100 >= c1.getX()
									&& tempJelly.getX() + tempJelly.getWidth() * 80 / 100 <= face
									&& tempJelly.getY() + tempJelly.getWidth() * 20 / 100 >= c1.getY()
									&& tempJelly.getY() + tempJelly.getWidth() * 80 / 100 <= foot
									&& tempJelly.getImage() != jellyEffectIc.getImage()) {

								if (tempJelly.getImage() == jellyHPIc.getImage()) {
									if ((c1.getHealth() + 100) > 1000) {
										c1.setHealth(1000);
									} else {
										c1.setHealth(c1.getHealth() + 100);
									}
								}
								else if (tempJelly.getImage() == jellyReverseIc.getImage()) { //reverse 젤리 먹었을 때
									Reverse2();
								}
								else if (tempJelly.getImage() == jellyAddIc.getImage()) {	// add 젤리 먹었을 때
									Add2();
									
									if(is_add2==true) {	//add 젤리 먹었을 때 장애물 발생
										Tacle tempTacle_2 = tacleList_2.get(i); // 임시 변수에 리스트 안에 있는 개별 장애물을 불러오자
									int ti = (tempTacle_2.getX() - mapLength * 40) / 40;
									int tj = tempTacle_2.getY() / 40;

									tacleList_2.add(new Tacle(tacle10Ic.getImage(), ti * 40 + mapLength * 40, tj * 40, 80, 80, 0));
									}
								}
								
								tempJelly.setImage(jellyEffectIc.getImage()); // 젤리의 이미지를 이펙트로 바꾼다
								resultScore = resultScore + tempJelly.getScore(); // 총점수에 젤리 점수를 더한다

							} else if ( // 슬라이딩 하는 캐릭터의 범위 안에 젤리가 있으면 아이템을 먹는다.
							c1.getImage() == slideIc.getImage()
									&& tempJelly.getX() + tempJelly.getWidth() * 20 / 100 >= c1.getX()
									&& tempJelly.getX() + tempJelly.getWidth() * 80 / 100 <= face
									&& tempJelly.getY() + tempJelly.getWidth() * 20 / 100 >= c1.getY()
											+ c1.getHeight() * 1 / 3
									&& tempJelly.getY() + tempJelly.getWidth() * 80 / 100 <= foot
									&& tempJelly.getImage() != jellyEffectIc.getImage()) {

								if (tempJelly.getImage() == jellyHPIc.getImage()) {
									if ((c1.getHealth() + 100) > 1000) {
										c1.setHealth(1000);
									} else {
										c1.setHealth(c1.getHealth() + 100);
									}
								}
								else if (tempJelly.getImage() == jellyReverseIc.getImage()) {
									Reverse2();
								}
								else if (tempJelly.getImage() == jellyAddIc.getImage()) {
									Add2();
									if(is_add2==true) {
									int tn = tacleList_2.size() - 1;
									int ti = (tacleList_2.get(tn).getX() - mapLength_2 * 40) / 40;
									int tj = tacleList_2.get(tn).getY() / 40;

									tacleList_2.add(new Tacle(tacle10Ic_2.getImage(), ti * 40 + mapLength_2 * 40, tj * 40, 80, 80, 0));
									repaint();
									}
								}
							
								tempJelly.setImage(jellyEffectIc.getImage()); // 젤리의 이미지를 이펙트로 바꾼다
								resultScore = resultScore + tempJelly.getScore(); // 총점수에 젤리 점수를 더한다

							}
						}
					}

					// 장애물위치를 - 4 씩 해준다.
					for (int i = 0; i < tacleList.size(); i++) {

						Tacle tempTacle = tacleList.get(i); // 임시 변수에 리스트 안에 있는 개별 장애물을 불러오자

						if (tempTacle.getX() < -90) {

							fieldList.remove(tempTacle); // 장애물의 x 좌표가 -90 미만이면 해당 젤리를 제거한다.(최적화)

						} else {

							tempTacle.setX(tempTacle.getX() - gameSpeed); // 위 조건에 해당이 안되면 x좌표를 줄이자

							face = c1.getX() + c1.getWidth(); // 캐릭터 정면 위치 재스캔
							foot = c1.getY() + c1.getHeight(); // 캐릭터 발 위치 재스캔

							if ( // 무적상태가 아니고 슬라이드 중이 아니며 캐릭터의 범위 안에 장애물이 있으면 부딛힌다
							!c1.isInvincible() && c1.getImage() != slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 <= face
									&& tempTacle.getY() + tempTacle.getHeight() / 2 >= c1.getY()
									&& tempTacle.getY() + tempTacle.getHeight() / 2 <= foot) {

								hit(); // 피격 + 무적 쓰레드 메서드

							} else if ( // 슬라이딩 아닐시 공중장애물
							!c1.isInvincible() && c1.getImage() != slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 <= face
									&& tempTacle.getY() <= c1.getY()
									&& tempTacle.getY() + tempTacle.getHeight() * 95 / 100 > c1.getY()) {

								hit(); // 피격 + 무적 쓰레드 메서드

							} else if ( // 무적상태가 아니고 슬라이드 중이며 캐릭터의 범위 안에 장애물이 있으면 부딛힌다
							!c1.isInvincible() && c1.getImage() == slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 <= face
									&& tempTacle.getY() + tempTacle.getHeight() / 2 >= c1.getY()
											+ c1.getHeight() * 2 / 3
									&& tempTacle.getY() + tempTacle.getHeight() / 2 <= foot) {

								hit(); // 피격 + 무적 쓰레드 메서드

							} else if ( // 슬라이딩시 공중장애물
							!c1.isInvincible() && c1.getImage() == slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 <= face
									&& tempTacle.getY() < c1.getY() && tempTacle.getY()
											+ tempTacle.getHeight() * 95 / 100 > c1.getY() + c1.getHeight() * 2 / 3) {

								hit(); // 피격 + 무적 쓰레드 메서드
							}
						}
					}

					 // 쿠키가 밟을 발판을 계산하는 코드
					int tempField; // 발판위치를 계속 스캔하는 지역변수
					int tempNowField; // 캐릭터와 발판의 높이에 따라 저장되는 지역변수, 결과를 nowField에 저장한다

					// 쿠키가 무적상태라면 낙사 하지 않기 때문에 400으로 세팅 / 무적이 아니라면 2000(낙사지점);
					if (c1.isInvincible()) {
						tempNowField = 400;
					} else {
						tempNowField = 2000;
					}

					for (int i = 0; i < fieldList.size(); i++) { // 발판의 개수만큼 반복

						int tempX = fieldList.get(i).getX(); // 발판의 x값

						if (tempX > c1.getX() - 60 && tempX <= face) { // 발판이 캐릭 범위 안이라면

							tempField = fieldList.get(i).getY(); // 발판의 y값을 tempField에 저장한다

							foot = c1.getY() + c1.getHeight(); // 캐릭터 발 위치 재스캔

							// 발판위치가 tempNowField보다 높고, 발바닥 보다 아래 있다면
							// 즉, 캐릭터 발 아래에 제일 높이 있는 발판이라면 tempNowField에 저장한다.
							if (tempField < tempNowField && tempField >= foot) {

								tempNowField = tempField;

							}
						}
					}

					nowField = tempNowField; // 결과를 nowField에 업데이트 한다.

					if (escKeyOn) { // esc키를 누르면 게임이 멈춘다
						while (escKeyOn) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}


					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
	}

	
	// 2인용
	// 화면을 움직이고 젤리를 먹거나, 장애물에 부딛히는 등의 이벤트를 발생시키는 메서드
	private void mapMove2() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					// 2인용
					if (runPage_2 > 800) { // 800픽셀 이동 마다 체력이 10씩 감소한다 (추후 맵길이에 ?上?감소량 조절)
						c1_2.setHealth(c1_2.getHealth() - 10);
						runPage_2 = 0;
					}


					// 2인용
					runPage_2 += gameSpeed_2; // 화면이 이동하면 runPage에 이동한 만큼 저장된다.

					foot_2 = c1_2.getY() + c1_2.getHeight(); // 캐릭터 발 위치 재스캔
					if (foot_2 > 1999 || c1_2.getHealth() < 1) {
						two_die=true;
						readyLabel = new JLabel("Dead");	
						readyLabel .setHorizontalAlignment(SwingConstants.CENTER);
						readyLabel .setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 37));
						readyLabel .setBounds(150, 500, 600, 500);
						add(readyLabel );
						
						while(one_die==false) { // 먼저 죽었을 때 대기
							
							
							try { // 1.5초 대기
								Thread.sleep(7500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
						}
						if(one_die==true) {
							
						main.getEndPanel2().setResultScore2(resultScore_2);
						cl.show(superFrame.getContentPane(), "end3");
						main.setGamePanel2(new GamePanel2(superFrame, cl, main));
						
						
						superFrame.requestFocus();
						escKeyOn_2 = true;
						}
						
						try { // 1.5초 대기
							Thread.sleep(6000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						two_die=false;
					}

					// 2인용
					// 배경 이미지 변경
					if (fadeOn_2 == false) { // 페이드아웃인 상태가 아닐때
						if (mapLength_2 > mapLengthList_2.get(2) * 40 + 800 && b11_2.getImage() != backIc4_2.getImage()) {
							fadeOn_2 = true;

							new Thread(new Runnable() {

								@Override
								public void run() {

									backFadeOut2();

									b11_2 = new Back(backIc4_2.getImage(), 0, 460, backIc4.getImage().getWidth(null),
											backIc4.getImage().getHeight(null));

									b12_2 = new Back(backIc4_2.getImage(), backIc4_2.getImage().getWidth(null), 460,
											backIc4_2.getImage().getWidth(null), backIc4_2.getImage().getHeight(null));

									b21_2 = new Back(secondBackIc4_2.getImage(), 0, 460,
											secondBackIc4_2.getImage().getWidth(null),
											secondBackIc4_2.getImage().getHeight(null));

									b22_2 = new Back(secondBackIc4_2.getImage(), secondBackIc4_2.getImage().getWidth(null), 460,
											secondBackIc4_2.getImage().getWidth(null),
											secondBackIc4_2.getImage().getHeight(null));

									backFadeIn2();
									fadeOn_2 = false;
								}
							}).start();

						} else if (mapLength_2 > mapLengthList_2.get(1) * 40 + 800
								&& mapLength_2 < mapLengthList_2.get(2) * 40 + 800
								&& b11_2.getImage() != backIc3_2.getImage()) {
							fadeOn_2 = true;

							new Thread(new Runnable() {

								@Override
								public void run() {

									backFadeOut2();

									b11_2 = new Back(backIc3_2.getImage(), 0, 460, backIc3.getImage().getWidth(null),
											backIc3_2.getImage().getHeight(null));

									b12_2 = new Back(backIc3_2.getImage(), backIc3_2.getImage().getWidth(null), 460,
											backIc3_2.getImage().getWidth(null), backIc3_2.getImage().getHeight(null));

									b21_2 = new Back(secondBackIc3_2.getImage(), 0, 460,
											secondBackIc3_2.getImage().getWidth(null),
											secondBackIc3_2.getImage().getHeight(null));

									b22_2 = new Back(secondBackIc3_2.getImage(), secondBackIc3_2.getImage().getWidth(null), 460,
											secondBackIc3_2.getImage().getWidth(null),
											secondBackIc3_2.getImage().getHeight(null));

									backFadeIn2();
									fadeOn_2 = false;
								}
							}).start();

						} else if (mapLength_2 > mapLengthList_2.get(0) * 40 + 800
								&& mapLength_2 < mapLengthList_2.get(1) * 40 + 800
								&& b11_2.getImage() != backIc2_2.getImage()) {
							fadeOn_2 = true;

							new Thread(new Runnable() {

								@Override
								public void run() {

									backFadeOut2();

									b11_2 = new Back(backIc2_2.getImage(), 0, 460, backIc2_2.getImage().getWidth(null),
											backIc2_2.getImage().getHeight(null));

									b12_2 = new Back(backIc2_2.getImage(), backIc2_2.getImage().getWidth(null), 460,
											backIc2_2.getImage().getWidth(null), backIc2_2.getImage().getHeight(null));

									b21_2 = new Back(secondBackIc2_2.getImage(), 0, 460,
											secondBackIc2_2.getImage().getWidth(null),
											secondBackIc2_2.getImage().getHeight(null));

									b22_2 = new Back(secondBackIc2_2.getImage(), secondBackIc2_2.getImage().getWidth(null), 460,
											secondBackIc2_2.getImage().getWidth(null),
											secondBackIc2_2.getImage().getHeight(null));

									backFadeIn2();
									fadeOn_2 = false;
								}
							}).start();
						}
					}


					// 2인용
					// 배경이미지 변경을 위한 맵이동 길이 측정
					mapLength_2 += gameSpeed_2;

					if (b11_2.getX() < -(b11_2.getWidth() - 1)) { // 배경1-1 이 -(배경넓이)보다 작으면, 즉 화면밖으로 모두나가면 배경 1-2뒤에 붙음
						b11_2.setX(b11_2.getWidth());
					}
					if (b12_2.getX() < -(b12_2.getWidth() - 1)) { // 배경1-2 가 -(배경넓이)보다 작으면, 즉 화면밖으로 모두나가면 배경 1-1뒤에 붙음
						b12_2.setX(b12_2.getWidth());
					}

					if (b21_2.getX() < -(b21_2.getWidth() - 1)) { // 배경1-1 이 -(배경넓이)보다 작으면, 즉 화면밖으로 모두나가면 배경 1-2뒤에 붙음
						b21_2.setX(b21_2.getWidth());
					}
					if (b22_2.getX() < -(b22_2.getWidth() - 1)) { // 배경1-2 가 -(배경넓이)보다 작으면, 즉 화면밖으로 모두나가면 배경 1-1뒤에 붙음
						b22_2.setX(b22_2.getWidth());
					}

					// 배경의 x좌표를 -1 해준다 (왼쪽으로 흐르는 효과)
					b11_2.setX(b11_2.getX() - gameSpeed_2 / 3);
					b12_2.setX(b12_2.getX() - gameSpeed_2 / 3);

					b21_2.setX(b21_2.getX() - gameSpeed_2 * 2 / 3);
					b22_2.setX(b22_2.getX() - gameSpeed_2 * 2 / 3);

					// 2인용
					// 발판위치를 -3 씩 해준다. (왼쪽으로 흐르는 효과)
					for (int i = 0; i < fieldList_2.size(); i++) {

						Field tempField_2 = fieldList_2.get(i); // 임시 변수에 리스트 안에 있는 개별 발판을 불러오자

						if (tempField_2.getX() < -90) { // 발판의 x좌표가 -90 미만이면 해당 발판을 제거한다.(최적화)

							fieldList_2.remove(tempField_2);

						} else {

							tempField_2.setX(tempField_2.getX() - gameSpeed_2); // 위 조건에 해당이 안되면 x좌표를 줄이자

						}
					}

					// 2인용
					// 젤리위치를 -4 씩 해준다.
					for (int i = 0; i < jellyList_2.size(); i++) {

						Jelly tempJelly_2 = jellyList_2.get(i); // 임시 변수에 리스트 안에 있는 개별 젤리를 불러오자

						if (tempJelly_2.getX() < -90) { // 젤리의 x 좌표가 -90 미만이면 해당 젤리를 제거한다.(최적화)

							fieldList_2.remove(tempJelly_2);

						} else {

							tempJelly_2.setX(tempJelly_2.getX() - gameSpeed_2); // 위 조건에 해당이 안되면 x좌표를 줄이자
							if (tempJelly_2.getImage() == jellyEffectIc_2.getImage() && tempJelly_2.getAlpha() > 4) {
								tempJelly_2.setAlpha(tempJelly_2.getAlpha() - 5);
							}

							foot_2 = c1_2.getY() + c1_2.getHeight(); // 캐릭터 발 위치 재스캔

							if ( // 캐릭터의 범위 안에 젤리가 있으면 아이템을 먹는다.
							c1_2.getImage() != slideIc_2.getImage()
									&& tempJelly_2.getX() + tempJelly_2.getWidth() * 20 / 100 >= c1_2.getX()
									&& tempJelly_2.getX() + tempJelly_2.getWidth() * 80 / 100 <= face_2
									&& tempJelly_2.getY() + tempJelly_2.getWidth() * 20 / 100 >= c1_2.getY()
									&& tempJelly_2.getY() + tempJelly_2.getWidth() * 80 / 100 <= foot_2
									&& tempJelly_2.getImage() != jellyEffectIc_2.getImage()) {

								if (tempJelly_2.getImage() == jellyHPIc_2.getImage()) {
									if ((c1_2.getHealth() + 100) > 1000) {
										c1_2.setHealth(1000);
									} else {
										c1_2.setHealth(c1_2.getHealth() + 100);
									}
								}
								else if (tempJelly_2.getImage() == jellyReverseIc_2.getImage()) {
									Reverse();
								}
								else if (tempJelly_2.getImage() == jellyAddIc_2.getImage()) {
									Add();
									if(is_add==true) {
									int tn = tacleList.size() - 1;
									int ti = (tacleList.get(tn).getX() - mapLength * 40) / 40;
									int tj = tacleList.get(tn).getY() / 40;

									tacleList.add(new Tacle(tacle10Ic.getImage(), ti * 40 + mapLength * 40, tj * 40, 80, 80, 0));
									}
								}
								
							
								tempJelly_2.setImage(jellyEffectIc_2.getImage()); // 젤리의 이미지를 이펙트로 바꾼다
								resultScore_2 = resultScore_2 + tempJelly_2.getScore(); // 총점수에 젤리 점수를 더한다

							} else if ( // 슬라이딩 하는 캐릭터의 범위 안에 젤리가 있으면 아이템을 먹는다.
							c1_2.getImage() == slideIc_2.getImage()
									&& tempJelly_2.getX() + tempJelly_2.getWidth() * 20 / 100 >= c1_2.getX()
									&& tempJelly_2.getX() + tempJelly_2.getWidth() * 80 / 100 <= face_2
									&& tempJelly_2.getY() + tempJelly_2.getWidth() * 20 / 100 >= c1_2.getY()
											+ c1_2.getHeight() * 1 / 3
									&& tempJelly_2.getY() + tempJelly_2.getWidth() * 80 / 100 <= foot_2
									&& tempJelly_2.getImage() != jellyEffectIc_2.getImage()) {

								if (tempJelly_2.getImage() == jellyHPIc_2.getImage()) {
									if ((c1_2.getHealth() + 100) > 1000) {
										c1_2.setHealth(1000);
									} else {
										c1_2.setHealth(c1_2.getHealth() + 100);
									}
								}
								else if (tempJelly_2.getImage() == jellyReverseIc_2.getImage()) {
									Reverse();
								}
								else if (tempJelly_2.getImage() == jellyAddIc_2.getImage()) {
									Add();
									if(is_add==true) {
									int tn = tacleList.size() - 1;
									int ti = (tacleList.get(tn).getX() - mapLength * 40) / 40;
									int tj = tacleList.get(tn).getY() / 40;

									tacleList.add(new Tacle(tacle10Ic_2.getImage(), ti * 40 + mapLength * 40, tj * 40, 80, 80, 0));
									}
								}
							
								tempJelly_2.setImage(jellyEffectIc_2.getImage()); // 젤리의 이미지를 이펙트로 바꾼다
								resultScore_2 = resultScore_2 + tempJelly_2.getScore(); // 총점수에 젤리 점수를 더한다

							}
						}
					}

					// 2인용
					// 장애물위치를 - 4 씩 해준다.
					for (int i = 0; i < tacleList_2.size(); i++) {

						Tacle tempTacle_2 = tacleList_2.get(i); // 임시 변수에 리스트 안에 있는 개별 장애물을 불러오자

						if (tempTacle_2.getX() < -90) {

							fieldList_2.remove(tempTacle_2); // 장애물의 x 좌표가 -90 미만이면 해당 젤리를 제거한다.(최적화)

						} else {

							tempTacle_2.setX(tempTacle_2.getX() - gameSpeed_2); // 위 조건에 해당이 안되면 x좌표를 줄이자

							face_2 = c1_2.getX() + c1_2.getWidth(); // 캐릭터 정면 위치 재스캔
							foot_2 = c1_2.getY() + c1_2.getHeight(); // 캐릭터 발 위치 재스캔

							if ( // 무적상태가 아니고 슬라이드 중이 아니며 캐릭터의 범위 안에 장애물이 있으면 부딛힌다
							!c1_2.isInvincible() && c1_2.getImage() != slideIc_2.getImage()
									&& tempTacle_2.getX() + tempTacle_2.getWidth() / 2 >= c1_2.getX()
									&& tempTacle_2.getX() + tempTacle_2.getWidth() / 2 <= face_2
									&& tempTacle_2.getY() + tempTacle_2.getHeight() / 2 >= c1_2.getY()
									&& tempTacle_2.getY() + tempTacle_2.getHeight() / 2 <= foot_2) {

								hit2(); // 피격 + 무적 쓰레드 메서드

							} else if ( // 슬라이딩 아닐시 공중장애물
							!c1_2.isInvincible() && c1_2.getImage() != slideIc_2.getImage()
									&& tempTacle_2.getX() + tempTacle_2.getWidth() / 2 >= c1_2.getX()
									&& tempTacle_2.getX() + tempTacle_2.getWidth() / 2 <= face_2
									&& tempTacle_2.getY() <= c1_2.getY()
									&& tempTacle_2.getY() + tempTacle_2.getHeight() * 95 / 100 > c1_2.getY()) {

								hit2(); // 피격 + 무적 쓰레드 메서드

							} else if ( // 무적상태가 아니고 슬라이드 중이며 캐릭터의 범위 안에 장애물이 있으면 부딛힌다
							!c1.isInvincible() && c1_2.getImage() == slideIc_2.getImage()
									&& tempTacle_2.getX() + tempTacle_2.getWidth() / 2 >= c1_2.getX()
									&& tempTacle_2.getX() + tempTacle_2.getWidth() / 2 <= face_2
									&& tempTacle_2.getY() + tempTacle_2.getHeight() / 2 >= c1_2.getY()
											+ c1_2.getHeight() * 2 / 3
									&& tempTacle_2.getY() + tempTacle_2.getHeight() / 2 <= foot_2) {

								hit2(); // 피격 + 무적 쓰레드 메서드

							} else if ( // 슬라이딩시 공중장애물
							!c1_2.isInvincible() && c1_2.getImage() == slideIc_2.getImage()
									&& tempTacle_2.getX() + tempTacle_2.getWidth() / 2 >= c1_2.getX()
									&& tempTacle_2.getX() + tempTacle_2.getWidth() / 2 <= face_2
									&& tempTacle_2.getY() < c1_2.getY() && tempTacle_2.getY()
											+ tempTacle_2.getHeight() * 95 / 100 > c1_2.getY() + c1_2.getHeight() * 2 / 3) {

								hit2(); // 피격 + 무적 쓰레드 메서드
							}
						}
					}

					//2인용
					// 쿠키가 밟을 발판을 계산하는 코드
					int tempField_2; // 발판위치를 계속 스캔하는 지역변수
					int tempNowField_2; // 캐릭터와 발판의 높이에 따라 저장되는 지역변수, 결과를 nowField에 저장한다

					// 쿠키가 무적상태라면 낙사 하지 않기 때문에 400으로 세팅 / 무적이 아니라면 2000(낙사지점);
					if (c1_2.isInvincible()) {
						tempNowField_2 = 400;
					} else {
						tempNowField_2 = 2000;
					}

					for (int i = 0; i < fieldList_2.size(); i++) { // 발판의 개수만큼 반복

						int tempX_2 = fieldList_2.get(i).getX(); // 발판의 x값

						if (tempX_2 > c1_2.getX() - 60 && tempX_2 <= face) { // 발판이 캐릭 범위 안이라면

							tempField_2 = fieldList_2.get(i).getY(); // 발판의 y값을 tempField에 저장한다

							foot_2 = c1_2.getY() + c1_2.getHeight(); // 캐릭터 발 위치 재스캔

							// 발판위치가 tempNowField보다 높고, 발바닥 보다 아래 있다면
							// 즉, 캐릭터 발 아래에 제일 높이 있는 발판이라면 tempNowField에 저장한다.
							if (tempField_2 < tempNowField_2 && tempField_2 >= foot_2) {

								tempNowField_2 = tempField_2;

							}
						}
					}

					nowField_2 = tempNowField_2; // 결과를 nowField에 업데이트 한다.

					if (escKeyOn_2) { // esc키를 누르면 게임이 멈춘다
						while (escKeyOn_2) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}


					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
	}

	private void Reverse() {	//reverse 아이템 통제
		new Thread(new Runnable() {

			@Override
			public void run() {
				is_reverse = true;

				System.out.println("조작키 반대 시작");

				try { // 1.5초 대기
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				is_reverse = false;
				System.out.println("조작키 반대 종료");
			}
		}).start();
	}

	private void Reverse2() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				is_reverse2 = true;

				System.out.println("조작키 반대 시작");

				try { // 1.5초 대기
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				is_reverse2 = false;
				System.out.println("조작키 반대 종료");
			}
		}).start();
	}
		
	// 부딛혔을 때 일어나는 상태를 담당하는 메서드
	private void hit() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				c1.setInvincible(true); // 쿠키를 무적상태로 전환

				System.out.println("피격무적시작");

				redScreen = true; // 피격 붉은 이펙트 시작

				c1.setHealth(c1.getHealth() - 100); // 쿠키의 체력을 100 깎는다

				c1.setImage(hitIc.getImage()); // 쿠키를 부딛힌 모션으로 변경

				c1.setAlpha(80); // 쿠키의 투명도를 80으로 변경

				try { // 0.5초 대기
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				redScreen = false; // 피격 붉은 이펙트 종료

				try { // 0.5초 대기
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (c1.getImage() == hitIc.getImage()) { // 0.5초 동안 이미지가 바뀌지 않았다면 기본이미지로 변경

					c1.setImage(cookieIc.getImage());

				}

				for (int j = 0; j < 11; j++) { // 2.5초간 캐릭터가 깜빡인다. (피격후 무적 상태를 인식)

					if (c1.getAlpha() == 80) { // 이미지의 알파값이 80이면 160으로

						c1.setAlpha(160);

					} else { // 아니면 80으로

						c1.setAlpha(80);

					}
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				c1.setAlpha(255); // 쿠키의 투명도를 정상으로 변경

				c1.setInvincible(false);
				System.out.println("피격무적종료");
			}
		}).start();
	}

	// 2인용
	// 부딛혔을 때 일어나는 상태를 담당하는 메서드
	private void hit2() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				c1_2.setInvincible(true); // 쿠키를 무적상태로 전환

				System.out.println("피격무적시작2");

				redScreen_2 = true; // 피격 붉은 이펙트 시작

				c1_2.setHealth(c1_2.getHealth() - 100); // 쿠키의 체력을 100 깎는다

				c1_2.setImage(hitIc_2.getImage()); // 쿠키를 부딛힌 모션으로 변경

				c1_2.setAlpha(80); // 쿠키의 투명도를 80으로 변경

				try { // 0.5초 대기
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				redScreen_2 = false; // 피격 붉은 이펙트 종료

				try { // 0.5초 대기
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (c1_2.getImage() == hitIc_2.getImage()) { // 0.5초 동안 이미지가 바뀌지 않았다면 기본이미지로 변경

					c1_2.setImage(cookieIc_2.getImage());

				}

				for (int j = 0; j < 11; j++) { // 2.5초간 캐릭터가 깜빡인다. (피격후 무적 상태를 인식)

					if (c1_2.getAlpha() == 80) { // 이미지의 알파값이 80이면 160으로

						c1_2.setAlpha(160);

					} else { // 아니면 80으로

						c1_2.setAlpha(80);

					}
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				c1_2.setAlpha(255); // 쿠키의 투명도를 정상으로 변경

				c1_2.setInvincible(false);
				System.out.println("피격무적종료2");
			}
		}).start();
	}
	
	private void Add()		// add 아이템 통제
	{
		new Thread(new Runnable() {
			public void run()
			{
				is_add=true;
				System.out.println("2P에게 아이템 투하");
				
				
				try {
					Thread.sleep(6000);
					
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				is_add=false;
				
			}
		}).start();
	}
	
	private void Add2()
	{
		new Thread(new Runnable() {
			public void run()
			{
				is_add2=true;
				System.out.println("1P에게 아이템 투하");
				
				try {
					Thread.sleep(6000);
					
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				is_add2=false;
				
			}
		}).start();
	}


	// 낙하 메서드
	private void fall() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {

					foot = c1.getY() + c1.getHeight(); // 캐릭터 발 위치 재스캔

					// 발바닥이 발판보다 위에 있으면 작동
					if (!escKeyOn // 일시중지가 발동 안됐을 때
							&& foot < nowField // 공중에 있으며
							&& !c1.isJump() // 점프 중이 아니며
							&& !c1.isFall()) { // 떨어지는 중이 아닐 때

						c1.setFall(true); // 떨어지는 중으로 전환
						System.out.println("낙하");

						if (c1.getCountJump() == 2) { // 더블점프가 끝났을 경우 낙하 이미지로 변경
							c1.setImage(fallIc.getImage());
						}

						long t1 = Util.getTime(); // 현재시간을 가져온다
						long t2;
						int set = 1; // 처음 낙하량 (0~10) 까지 테스트해보자

						while (foot < nowField) { // 발이 발판에 닿기 전까지 반복

							t2 = Util.getTime() - t1; // 지금 시간에서 t1을 뺀다

							int fallY = set + (int) ((t2) / 40); // 낙하량을 늘린다.

							foot = c1.getY() + c1.getHeight(); // 캐릭터 발 위치 재스캔

							if (foot + fallY >= nowField) { // 발바닥+낙하량 위치가 발판보다 낮다면 낙하량을 조정한다.
								fallY = nowField - foot;
							}

							c1.setY(c1.getY() + fallY); // Y좌표에 낙하량을 더한다

							if (c1.isJump()) { // 떨어지다가 점프를 하면 낙하중지
								break;
							}

							if (escKeyOn) {
								long tempT1 = Util.getTime();
								long tempT2 = 0;
								while (escKeyOn) {
									try {
										Thread.sleep(10);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								tempT2 = Util.getTime() - tempT1;
								t1 = t1 + tempT2;
							}

							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

						}
						c1.setFall(false);

						if (downKeyOn // 다운키를 누른상태고
								&& !c1.isJump() // 점프 상태가 아니고
								&& !c1.isFall() // 낙하 상태가 아니고
								&& c1.getImage() != slideIc.getImage()) { // 쿠키 이미지가 슬라이드 이미지가 아닐 경우

							c1.setImage(slideIc.getImage()); // 쿠키 이미지를 슬라이드로 변경

						} else if (!downKeyOn // 다운키를 누른상태가 아니고
								&& !c1.isJump() // 점프 상태가 아니고
								&& !c1.isFall() // 낙하 상태가 아니고
								&& c1.getImage() != cookieIc.getImage()) { // 쿠키 이미지가 기본 이미지가 아닐 경우

							c1.setImage(cookieIc.getImage());
						}

						if (!c1.isJump()) { // 발이 땅에 닿고 점프 중이 아닐 때 더블점프 카운트를 0으로 변경
							c1.setCountJump(0);
						}
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	// 낙하 메서드
	private void fall2() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					// 2인용
					foot_2 = c1_2.getY() + c1_2.getHeight(); // 캐릭터 발 위치 재스캔

					// 발바닥이 발판보다 위에 있으면 작동
					if (!escKeyOn_2 // 일시중지가 발동 안됐을 때
							&& foot_2 < nowField_2 // 공중에 있으며
							&& !c1_2.isJump() // 점프 중이 아니며
							&& !c1_2.isFall()) { // 떨어지는 중이 아닐 때

						c1_2.setFall(true); // 떨어지는 중으로 전환
						System.out.println("낙하2");

						if (c1_2.getCountJump() == 2) { // 더블점프가 끝났을 경우 낙하 이미지로 변경
							c1_2.setImage(fallIc_2.getImage());
						}

						long t1_2 = Util.getTime(); // 현재시간을 가져온다
						long t2_2;
						int set_2 = 1; // 처음 낙하량 (0~10) 까지 테스트해보자

						while (foot_2 < nowField_2) { // 발이 발판에 닿기 전까지 반복

							t2_2 = Util.getTime() - t1_2; // 지금 시간에서 t1을 뺀다

							int fallY_2 = set_2 + (int) ((t2_2) / 40); // 낙하량을 늘린다.

							foot_2 = c1_2.getY() + c1_2.getHeight(); // 캐릭터 발 위치 재스캔

							if (foot_2 + fallY_2 >= nowField_2) { // 발바닥+낙하량 위치가 발판보다 낮다면 낙하량을 조정한다.
								fallY_2 = nowField_2 - foot_2;
							}

							c1_2.setY(c1_2.getY() + fallY_2); // Y좌표에 낙하량을 더한다

							if (c1_2.isJump()) { // 떨어지다가 점프를 하면 낙하중지
								break;
							}

							if (escKeyOn_2) {
								long tempT1_2 = Util.getTime();
								long tempT2_2 = 0;
								while (escKeyOn_2) {
									try {
										Thread.sleep(10);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								tempT2_2 = Util.getTime() - tempT1_2;
								t1_2 = t1_2 + tempT2_2;
							}

							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

						}
						c1_2.setFall(false);

						if (downKeyOn_2 // 다운키를 누른상태고
								&& !c1_2.isJump() // 점프 상태가 아니고
								&& !c1_2.isFall() // 낙하 상태가 아니고
								&& c1_2.getImage() != slideIc_2.getImage()) { // 쿠키 이미지가 슬라이드 이미지가 아닐 경우

							c1_2.setImage(slideIc_2.getImage()); // 쿠키 이미지를 슬라이드로 변경

						} else if (!downKeyOn_2 // 다운키를 누른상태가 아니고
								&& !c1_2.isJump() // 점프 상태가 아니고
								&& !c1_2.isFall() // 낙하 상태가 아니고
								&& c1_2.getImage() != cookieIc_2.getImage()) { // 쿠키 이미지가 기본 이미지가 아닐 경우

							c1_2.setImage(cookieIc_2.getImage());
						}

						if (!c1_2.isJump()) { // 발이 땅에 닿고 점프 중이 아닐 때 더블점프 카운트를 0으로 변경
							c1_2.setCountJump(0);
						}
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	// 점프 메서드
	private void jump() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				c1.setCountJump(c1.getCountJump() + 1); // 점프 횟수 증가

				int nowJump = c1.getCountJump(); // 이번점프가 점프인지 더블점프인지 저장

				c1.setJump(true); // 점프중으로 변경

				if (c1.getCountJump() == 1) { // 점프 횟수가 1이라면

					System.out.println("점프");
					c1.setImage(jumpIc.getImage());

				} else if (c1.getCountJump() == 2) { // 점프 횟수가 2라면

					System.out.println("더블점프");
					c1.setImage(doubleJumpIc.getImage());

				}

				long t1 = Util.getTime(); // 현재시간을 가져온다
				long t2;
				int set = 9; // 점프 계수 설정(0~20) 등으로 바꿔보자
				int jumpY = 1; // 1이상으로만 설정하면 된다.(while문 조건 때문)

				while (jumpY >= 0) { // 상승 높이가 0일때까지 반복

					t2 = Util.getTime() - t1; // 지금 시간에서 t1을 뺀다

					jumpY = set - (int) ((t2) / 40); // jumpY 를 세팅한다.

					c1.setY(c1.getY() - jumpY); // Y값을 변경한다.

					if (nowJump != c1.getCountJump()) { // 점프가 한번 더되면 첫번째 점프는 멈춘다.
						break;
					}

					if (escKeyOn) {
						long tempT1 = Util.getTime();
						long tempT2 = 0;
						while (escKeyOn) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						tempT2 = Util.getTime() - tempT1;
						t1 = t1 + tempT2;
					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				if (nowJump == c1.getCountJump()) { // 점프가 진짜 끝났을 때를 확인
					c1.setJump(false); // 점프상태를 false로 변경
				}

			}
		}).start();
	}

	// 2인용
	// 점프 메서드
	private void jump2() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				c1_2.setCountJump(c1_2.getCountJump() + 1); // 점프 횟수 증가

				int nowJump_2 = c1_2.getCountJump(); // 이번점프가 점프인지 더블점프인지 저장

				c1_2.setJump(true); // 점프중으로 변경

				if (c1_2.getCountJump() == 1) { // 점프 횟수가 1이라면

					System.out.println("점프2");
					c1_2.setImage(jumpIc_2.getImage());

				} else if (c1_2.getCountJump() == 2) { // 점프 횟수가 2라면

					System.out.println("더블점프2");
					c1_2.setImage(doubleJumpIc_2.getImage());

				}

				long t1_2 = Util.getTime(); // 현재시간을 가져온다
				long t2_2;
				int set_2 = 9; // 점프 계수 설정(0~20) 등으로 바꿔보자
				int jumpY_2 = 1; // 1이상으로만 설정하면 된다.(while문 조건 때문)

				while (jumpY_2 >= 0) { // 상승 높이가 0일때까지 반복

					t2_2 = Util.getTime() - t1_2; // 지금 시간에서 t1을 뺀다

					jumpY_2 = set_2 - (int) ((t2_2) / 40); // jumpY 를 세팅한다.

					c1_2.setY(c1_2.getY() - jumpY_2); // Y값을 변경한다.

					if (nowJump_2 != c1_2.getCountJump()) { // 점프가 한번 더되면 첫번째 점프는 멈춘다.
						break;
					}

					if (escKeyOn_2) {
						long tempT1_2 = Util.getTime();
						long tempT2_2 = 0;
						while (escKeyOn_2) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						tempT2_2 = Util.getTime() - tempT1_2;
						t1_2 = t1_2 + tempT2_2;
					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				if (nowJump_2 == c1_2.getCountJump()) { // 점프가 진짜 끝났을 때를 확인
					c1_2.setJump(false); // 점프상태를 false로 변경
				}

			}
		}).start();
	}


	private void backFadeOut() {
		for (int i = 0; i < 256; i += 2) {
			backFade = new Color(0, 0, 0, i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void backFadeIn() {
		for (int i = 255; i >= 0; i -= 2) {
			backFade = new Color(0, 0, 0, i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	// 2인용
	private void backFadeOut2() {
		for (int i = 0; i < 256; i += 2) {
			backFade_2 = new Color(0, 0, 0, i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void backFadeIn2() {
		for (int i = 255; i >= 0; i -= 2) {
			backFade_2 = new Color(0, 0, 0, i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
