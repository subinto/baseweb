package websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collections;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class TestWebSocketServer extends WebSocketServer{
	public static String SocketIp = "127.0.0.1";//ut.etok(resourcebundle.getString("SocketIp"));		// 서버아이피
	public static int SocketPort = 7200;//ut.etok(resourcebundle.getString("SocketPort"));	// 서버포트
	public static String SocketPath = "/websocket";//ut.etok(resourcebundle.getString("SocketPath"));	// 서버패스
	
	public TestWebSocketServer() throws UnknownHostException {
		super( new InetSocketAddress(SocketIp, SocketPort ) );
		System.out.println("########## SocketPort : " + SocketPort);
		System.out.println("########## new InetSocketAddress( SocketIp + SocketPath, SocketPort ) : " + new InetSocketAddress( SocketIp + SocketPath, SocketPort ));
	}

	public TestWebSocketServer( InetSocketAddress address ) {
		super( address );
		System.out.println("########## address : " + address.toString());
	}

	public TestWebSocketServer(int port, Draft_6455 draft) {
		super( new InetSocketAddress( port ), Collections.<Draft>singletonList(draft));
		System.out.println("########## port - draft : " + port + " - " + draft);
	}

	@Override
	public void onOpen( WebSocket conn, ClientHandshake handshake ) {
//		conn.send("Welcome to the server!"); //This method sends a message to the new client
//		broadcast( "new connection: " + handshake.getResourceDescriptor() ); //This method sends a message to all clients connected
		System.out.println( conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!" );
	}

	@Override
	public void onClose( WebSocket conn, int code, String reason, boolean remote ) {
//		broadcast( conn + " has left the room!" );
		System.out.println( conn + " has left the room!" );
	}

	@Override
	public void onMessage( WebSocket conn, String message ) {
//		broadcast( message );
		System.out.println( conn + ": " + message );
	}
	
	@Override
	public void onMessage( WebSocket conn, ByteBuffer message ) {
//		broadcast( message.array() );
		System.out.println( conn + ": " + message );
	}


	public static void main( String[] args ) throws InterruptedException , IOException {
		TestWebSocketServer s = new TestWebSocketServer();
		s.start();
		System.out.println( "TestWebSocketServer started on port: " + s.getPort() );

//		BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
//		while ( true ) {
//			String in = sysin.readLine();
//			s.broadcast( in );
//			if( in.equals( "exit" ) ) {
//				s.stop(1000);
//				break;
//			}
//		}
	}
	@Override
	public void onError( WebSocket conn, Exception ex ) {
		ex.printStackTrace();
		if( conn != null ) {
			// some errors like port binding failed may not be assignable to a specific websocket
		}
	}

	@Override
	public void onStart() {
		System.out.println("Server started!");
		setConnectionLostTimeout(0);
		setConnectionLostTimeout(100);
	}
}
