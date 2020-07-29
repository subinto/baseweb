package websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import com.pb.util.Ut;

public class TestWebSocketClient  extends WebSocketClient {
	Ut ut = new Ut();
	
//	ResourceBundle resourcebundle = ResourceBundle.getBundle("sehwa.egovProps.IpInfoSample");
	public static String SocketIp = "127.0.0.1";//ut.etok(resourcebundle.getString("SocketIp"));		// 서버아이피
	public static String SocketPort = "7200";//ut.etok(resourcebundle.getString("SocketPort"));	// 서버포트
	public static String SocketPath = "/websocket";//ut.etok(resourcebundle.getString("SocketPath"));	// 서버포트
	private String sMsg = "";
	
	public TestWebSocketClient() throws Exception {
		super( new URI("ws://" + SocketIp + ":" + SocketPort + SocketPath) );
	}
	
	public void setSend(String message) {
		this.sMsg = message;
	}

	@Override
	public void onOpen( ServerHandshake handshakedata ) {
		//send("Hello, it is me. Mario :)");
//		send(this.sMsg);
		System.out.println( "onOpen : opened connection" );
		// if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
	}
	
	@Override
	public void onMessage( String message ) {
		System.out.println( "onMessage : received: " + message );
	}

	@Override
	public void onClose( int code, String reason, boolean remote ) {
		// The codecodes are documented in class org.java_websocket.framing.CloseFrame
		System.out.println( "onClose : Connection closed by " + ( remote ? "remote peer" : "us" ) + " Code: " + code + " Reason: " + reason );
	}

	@Override
	public void onError( Exception ex ) {
		ex.printStackTrace();
		// if the error is fatal then onClose will be called additionally
	}
	
	public void fnClose() {
		this.close();
	}
	
	public void fnConnect() {
		this.connect();
	}
	
	public void socketMemorySend(int sysid, int a, int b, int c, byte[] data) throws Exception{
		//TestWebSocketClient twsc = null;
		try {
//			TestWebSocketClient twsc = new TestWebSocketClient( new URI( "ws://localhost:7200" )); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
			
			String message = sysid + "|" + a + "|" + b + "|" + c + "|" + new String(data);
			//this.setSend(message);
			//twsc = new TestWebSocketClient( new URI("ws://" + SocketIp + ":" + SocketPort)); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
			//this.connect();
			System.out.println("## send msg : " + message);
			send(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			System.out.println("############## finnally 1");
//			this.onClose(0, null, true);
//			this.close();
			System.out.println("############## finnally 2");
		}
	}
	
	public void wow() {
		List<Map> list = new ArrayList<Map>();
		Map map = null;
		for(int i = 0 ; i < 10 ; i++) {
			map = new HashMap();
			map.put("a", i);
			map.put("b", i+1);
			map.put("c", i+2);
			map.put("data", new byte[i]);
			list.add(map);
		}
		TestWebSocketClient sc = null;
		try {
			sc = new TestWebSocketClient();
			sc.connect();
			for(Map mp : list) {
				int a = (int)mp.get("a");
				int b = (int)mp.get("b");
				int c = (int)mp.get("c");
				byte[] data = (byte[])mp.get("data");
				sc.socketMemorySend(255, a, b, c, data);
				break;
			}
			sc.close();
			System.out.println("############## sc.close");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			System.out.println("############## finnally 11");
//			sc.onClose(0, null, true);
//			sc.close();
			System.out.println("############## finnally 22");
		}
	}

	public static void main( String[] args ) throws URISyntaxException {
		try {
			TestWebSocketClient tc = new TestWebSocketClient();
			tc.wow();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
