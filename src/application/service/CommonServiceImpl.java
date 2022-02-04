package application.service;

import java.util.regex.Pattern;

import javafx.event.Event;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CommonServiceImpl implements CommonService {

	@Override
	public void handCursor(MouseEvent event) {
		Node node =  (Node) event.getSource();
		node.setCursor(Cursor.HAND);
	}
	

	public void windowClose(Event event) {
		Node node = (Node) event.getSource();
		Stage s = (Stage) node.getScene().getWindow();
		s.close();

	}

	public void errorAlert(String errTitle, String errHeader, String errContent) {
		Alert alr = new Alert(AlertType.INFORMATION);
		alr.setTitle(errTitle + " 오류");
		alr.setHeaderText(errHeader + "을 확인해주세요");
		alr.setContentText(errContent);
		alr.showAndWait();
	}

	public boolean validCheck(String checkName, String value) {
		// 무결성 확인이 되면 false로 반환
		if (checkName.equals("mail")) {
			return !Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", value);
		}
		if (checkName.equals("pw")) {
			return !Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,}$", value);
		} else if (checkName.equals("id")) {
			return !Pattern.matches("^[a-zA-Z0-9_]{4,11}$", value);
		} else if (checkName.equals("name")) {
			return !Pattern.matches("^[ㄱ-ㅎ가-힣a-zA-Z]*$", value);
		} else {
			System.out.println("ㅇㅇㅇ 오류");
			return false;
		}

	}
}
