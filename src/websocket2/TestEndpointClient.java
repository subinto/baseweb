package websocket2;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 * Websocket Test Client
 */
@ClientEndpoint
public class TestEndpointClient {
	public String SocketIp = "127.0.0.1";
	public String SocketPort = "7200";
	public String SocketPath = "/websocket";

	Session userSession = null;

	/**
	 * Websocket 연결
	 */
	public TestEndpointClient() {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, new URI("ws://" + SocketIp + ":" + SocketPort + SocketPath));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 웹 소켓이 연결되었을 때 호출
	 */
	@OnOpen
	public void onOpen(Session userSession) {
		System.out.println("################# open Websocket");
		this.userSession = userSession;
	}

	/**
	 * 웹 소켓 닫기
	 * @OnClose을 사용하였으나 연결이 끈기지 않어(왜?) 직접 연결끈기...ㅜ.ㅡ
	 */
	public void onClose() {
		System.out.println("################# close Websocket");
		try {
			if(this.userSession != null) {
				this.userSession.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			this.userSession = null;
		}
	}

	/**
	 * 웹 소켓에 메시지 보내기
	 */
	public void sendMessage(String message) {
		System.out.println("################# sendMessage : " + message);
		this.userSession.getAsyncRemote().sendText(message);
	}
}