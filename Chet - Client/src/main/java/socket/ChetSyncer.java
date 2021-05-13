package main.java.socket;

import main.java.gui.ChetRoom;

public class ChetSyncer extends Thread {
	private final ChetRoom CHET_ROOM;
	
	public ChetSyncer(ChetRoom chetRoom) {
		this.CHET_ROOM = chetRoom;
	}
	
	@Override
	public void run() {
		while (true) {
			var chet = CHET_ROOM.CLIENT.getResponse();
			CHET_ROOM.updateChet(chet);
		}
	}
}
