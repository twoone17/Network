package term3;

//Client,Server=>공통으로 사용하는 클래스
//프로그램의 기능 설정=>내부 프로토콜(서버와 클라이언트의 약속)
//웹서버
public class Function {
 // 로그인 처리
 public static final int LOGIN = 100;
 public static final int MYLOG = 110;

 // 대기실 채팅
 public static final int WAITCHAT=500;
 // 방 채팅
    //방만들기
	// 방들어가기
 public static final int ENTER = 120;
	// 방나가기
	// 대기실 수정
	
	// 대기실 채팅
	// 방채팅
	
	// 쪽지보내기
	// 채팅 종료
	// 1:1 게임 요청
 public static final int GAMESTART = 200;
    // 1:1 게임 진행 각자 게임창을 열게 함
 public static final int GAMEBEGIN = 210;
 	// 1:1 게임 둘다 서버에 연결
 public static final int GAMECONN = 220;
 	// 1:1 게임 둘다 서버에 연결
 public static final int GAMEING = 230;
 	// game move 진행 - 두  move 다 들어오면 결과 출력
 public static final int GAMEMOVE = 240;
 	// 두 move 다 들어오면 결과출력
 public static final int MOVERESULT = 250;
 
 
 public static final int GARA = 40;
 
 
}

