package application.control;

import java.net.URL;
import java.util.ResourceBundle;

import application.service.CommonService;
import application.service.CommonServiceImpl;
import application.service.MemberService;
import application.service.MemberServiceImpl;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

public class Controller implements Initializable {

    private Parent curParent;    
	private CommonService cs;
	private MemberService ms;
	
	public Controller() {
		cs = new CommonServiceImpl();
		ms = new MemberServiceImpl();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// init method
	}
	

	public void setCurParent(Parent curParent) {
		this.curParent = curParent;
	}

	public void mouseOver(MouseEvent event) {
		cs.handCursor(event);
	}

	public void memberStageOpen() {
		ms.memberStageOpen();
	}

	public void cancel(Event event) {
		cs.windowClose(event);
	}
	
	public void memberReg() {
		ms.memberReg(curParent);
	}
}
