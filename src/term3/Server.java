package term3;

import java.util.*;
//import term3.Function;
import java.io.*;//입출력 (서버와 클라이언트 통신)
import java.net.*; //다른 컴퓨터와 연결
 
public class Server implements Runnable {
    private ServerSocket ss;
    private final int PORT = 1120;
    private Vector<Client> waitVc = new Vector<Client>();
    // 쓰레드에서 동기화 프로그램
	String p1 ="";
	String p2 ="";
	int p1Move = -1;
	int p2Move = -1;
    // 서버 가동
    public Server() {
        try {
            ss = new ServerSocket(PORT);
            /*
             * bind() => IP, PORT 묶어주는 역할 ==> 핸드폰 (개통) listen() => 대기상태(클라이언트가 접속하기 전까지 대기)
             */
            System.out.println("Server Start...");
        } catch (Exception ex) {
        }
    }
 
    // 클라이언트가 접속을 했을 경우 => 각자 통신이 가능하게 쓰레드와 연결
    @Override
    public void run() {
        try {
            while (true) {
                Socket s = ss.accept();
                // accept() : 특수한 메소드 => 클라이언트가 접속시에만 호출되는 메소드
                Client client = new Client(s);
                client.start(); // 클라이언트와 통신 승인
            }
        } catch (Exception ex) {
        }
    }
 
    public static void main(String[] args) {
        //서버가동
        Server server=new Server();
        new Thread(server).start();
        //Server 클래스에 있는 run을 호출
 
    }
 
    // 내부 클래스 => Server가 가지고 있는 데디터 쉽게 공유가 가능하게 만든다
    class Client extends Thread {
        // 클라이언트와 연결
        Socket s;
        // 클라이언트로부터 요청을 받는다
        BufferedReader in;
        // 요청을 처리한 다음에 결과를 응답
        OutputStream out;
        String id, name, birthdate, pos, email, sns, win, lose, loginCount, ip, currentlogin;
 
        public Client(Socket s) {
            try {
                this.s = s;
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                // 클라이언트 요청한 메소드를 읽어오는 메모리 공간
                out = s.getOutputStream();
            } catch (Exception ex) {
            }
        }
 
        // 통신 ? => 기능(요청처리)
        public void run() {
            try {
                while (true) {

                	
                    // 클라이언트가 요청한 내용 받는다
                	System.out.println("ready to read");
                    String msg = in.readLine();
                    
                    StringTokenizer st = new StringTokenizer(msg, "|");
                    int protocol = Integer.parseInt(st.nextToken());
                    // 100|id|대화명|성별
                    System.out.println("line read! protocol : " + protocol);

                    switch (protocol) {
                    case Function.LOGIN: {
                        // 로그인처리
                        // 데이터 값 받기
                        id = st.nextToken();
                        name = st.nextToken();
                        birthdate = st.nextToken();
                        email = st.nextToken();
                        sns = st.nextToken();
                        win = st.nextToken();
                        lose = st.nextToken();
                        loginCount = st.nextToken();
                        ip = st.nextToken();
                        currentlogin = st.nextToken();
                        pos = "대기실"; // 사용자의 기본정보
 
                        // 접속한 모든 사용자 => 로그인한 정보를 보내준다
                        messageAll(Function.LOGIN + "|" + id + "|" + name + "|" + birthdate + "|" + pos
                        		+ "|" + email+ "|" + sns+ "|" + win+ "|" + lose+ "|" + loginCount+ "|" + ip+ "|" + currentlogin);
                        // 접속한 사람의 정보를 저장
                        waitVc.add(this);
                        // Client client=new Client();
                        messageTo(Function.MYLOG + "|" + id);
                        // 로그인창에서 => 대기창으로 변경
                        for (Client user : waitVc) {
                            messageTo(
                                    Function.LOGIN + "|" + user.id + "|" + user.name + "|" + user.birthdate + "|" + user.pos
                                    + "|" + user.email+ "|" + user.sns+ "|" + user.lose+ "|" + user.loginCount+ "|" + user.ip+ "|" + user.currentlogin);
                        }
                        // 입장 메세지 출력
                        messageAll(Function.WAITCHAT+"|[☞"+name+"님이 입장하셨습니다.");
                        // 개설된 방정보 전송
                    }
                        break;
                    case Function.WAITCHAT:
                    {
                        messageAll(Function.WAITCHAT+"|["+name+"]"+st.nextToken());
                        break;
                    }
                    case Function.ENTER:
                    {
                        messageAll(Function.WAITCHAT+"|" + st.nextToken());
                        break;
                    }
                    case Function.GAMESTART:
                    {
                    	String sender = st.nextToken();
                    	String reciever = st.nextToken();
                    	System.out.println("sender : " + sender);
                    	System.out.println("reciever : " + reciever);
                    	whisper(Function.GAMESTART+ "|" + sender, reciever);
                    	break;
                    }
                    case Function.GAMEBEGIN: // 각자 게임창을 열게 함
                    {
                    	p1 = st.nextToken();
                    	p2 = st.nextToken();
                    	System.out.println("p1 : " + p1);
                    	System.out.println("p2 : " + p2);
                    	whisper(Function.GAMEBEGIN+ "|" + p2, p1);
                    	whisper(Function.GAMEBEGIN+ "|" + p1, p2);
                    	break;
                    }
                    case Function.GAMECONN: // 게임 접속 확인
                    {
                    	String me = st.nextToken();
                    	String opp = st.nextToken();
                    	System.out.println("준비완료 : " + me);
                    	whisper(Function.GAMEING + "|" + opp, me);
                    	break;
                    }
                    case Function.GAMEMOVE: // 각자 선택 확인후 결과출력
                    {
                    	String thisisme = st.nextToken();
                    	String thisisopp = st.nextToken();
                    	String myMove = st.nextToken();
                    	System.out.println(thisisme + "'s move : " + myMove);
                    	System.out.println("p1 : " + p1Move + " p2 : " + p2Move);
                    	System.out.println("p1 = " + p1 + " p2 = " + p2 + thisisme);
                    	
                    	if(p1.equals(thisisme)) {
                    		p1Move = Integer.parseInt(myMove);
                    		System.out.println(p1 + "p1move : " + p1Move);
                    	}
                    	else if (p2.equals(thisisme)) {
                    		p2Move = Integer.parseInt(myMove);
                    		System.out.println(p2 + "p2move : " + p2Move);
                    	}
                    	if (p1Move != -1 && p2Move != -1) { //늦게 move한 유저가 실행하는 코de
                    			System.out.println("GOGO - p1: " + p1Move + " p2 : " + p2Move);
                    			whisper(Function.MOVERESULT+  "|" + p1Move + "|" + p2Move, p1);
                    			whisper(Function.MOVERESULT+  "|" + p2Move + "|" + p1Move, p2);
                    			p1Move = -1;
                    			p2Move = -1;
                        
                    	}
                    	break;
                    }
                    case Function.GARA: // 각자 선택 확인후 결과출력
                    {
                    	messageAll(Function.GARA + "");
                    }
                    }
                }
            } catch (Exception ex) {
            }
 
        }
 
        // 응답처리
        public synchronized void messageTo(String msg) {
            // synchronized
            /*
             * 쓰레드는 default : 비동기화 synchronized => 동기화
             */
            try {
                out.write((msg + "\n").getBytes()); // 데이터를 1명한테만 보내는 거
                // 인코딩 ==> 디코딩
            } catch (Exception ex) {
            }
        }
 
        // 전체적으로 응답
        public synchronized void messageAll(String msg) {
            try {
                for (Client user : waitVc) {
                    user.messageTo(msg);
                }
            } catch (Exception ex) {
            }
        }
        // 귓속말
        public synchronized void whisper(String msg, String id) {
        	try {
        		for (Client user : waitVc) {
        			if(user.id.equals(id)) {
        				//System.out.println("reciever found : " + user.id);
        			user.messageTo(msg);
        			}
        		}
        	} catch (Exception ex) {
        	}
        }
    }
}
