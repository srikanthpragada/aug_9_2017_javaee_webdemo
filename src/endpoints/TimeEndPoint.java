/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoints;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/time")
public class TimeEndPoint {
	Thread childThread;
	int count = 1;

	@OnOpen
	public void openConnection(final Session session) {
		System.out.println("Opened connection for TimeEndPoint");
		childThread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(5000);
						// write to all
						// session.getBasicRemote().sendText(new java.util.Date().toString());
						session.getBasicRemote().sendText(String.valueOf(count));
						count ++;
					} catch (Exception ex) {
					}
				}
			}
		});
		childThread.start();
	}

	
	@OnClose
	public void closeConnection(final Session session) {
		System.out.println("Closed connection");
		
	}

}
