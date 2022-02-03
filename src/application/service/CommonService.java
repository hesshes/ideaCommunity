package application.service;

import javafx.event.Event;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

public interface CommonService {
	
	public void handCursor(MouseEvent event);

	public void windowClose(Event event);
	
	public void errorAlert(String errTitle, String errHeader, String errContent);
	
	public boolean validCheck(String checkName, String value);
	
}
