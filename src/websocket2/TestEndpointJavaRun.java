package websocket2;

public class TestEndpointJavaRun {

	public static void main(String[] args) {
		try {
			// open websocket
			TestEndpointClient clientEndPoint = new TestEndpointClient();
			
			// send message
			clientEndPoint.sendMessage("메세지를 첫번째로 보냅니다......");
			clientEndPoint.sendMessage("메세지를 두번째로 보냅니다......");
			clientEndPoint.sendMessage("메세지를 세번째로 보냅니다......");

			// close websocket
			clientEndPoint.onClose();

		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}