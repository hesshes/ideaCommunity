package application.service;

import java.io.IOException;

import application.control.Controller;
import application.dao.DatabaseService;
import application.dao.DatabaseServiceImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MemberServiceImpl implements MemberService {

	CommonService cs = new CommonServiceImpl();

	@Override
	public void memberStageOpen() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../IDCmemberReg.fxml"));
			loader.load();
			Parent root = loader.getRoot();
			Controller ctrl = loader.getController();
			ctrl.setCurParent(root);
			Stage membership = new Stage();
			membership.setScene(new Scene(root));
			membership.initModality(Modality.APPLICATION_MODAL);
			membership.setResizable(false);
			membership.show();
		} catch (IOException e) {
			System.out.println("memberStage Open Error");
			e.printStackTrace();
		}

	}

	@Override
	public void memberReg(Parent parent) {
		DatabaseService dao = new DatabaseServiceImpl();
		TextField id = (TextField) parent.lookup("#txtId");
		PasswordField pw = (PasswordField) parent.lookup("#txtPw");
		PasswordField pwChk = (PasswordField) parent.lookup("#txtPwChk");
		TextField name = (TextField) parent.lookup("#txtName");
		DatePicker birth = (DatePicker) parent.lookup("#birth");
		TextField txtMail = (TextField) parent.lookup("#txtMail");

		if (id.getText().isEmpty() || pw.getText().isEmpty() || pwChk.getText().isEmpty() || name.getText().isEmpty()
				|| birth.getValue().toString().isEmpty() || txtMail.getText().isEmpty()) {
			cs.errorAlert("미입력", "미입력 항목", "빈 항목 확인");
			id.requestFocus();
		} else if (!pw.getText().equals(pwChk.getText())) {
			cs.errorAlert("비밀번호 입력", "비밀번호 항목", "비밀번호 확인");
			pw.clear();
			pw.requestFocus();
		} else if (cs.validCheck("id", id.getText()) || dao.dupChecker("id", id.getText())) {
			boolean a = cs.validCheck("id", id.getText());
			boolean b = dao.dupChecker("id", id.getText());
			System.out.println("DB 중복 확인 결과 " + b);
			System.out.println("아이디 무결성 확인 결과 "+a);
			cs.errorAlert("아이디 입력", "아이디 입력 항목", "아이디 입력 확인");
			id.clear();
			id.requestFocus();
		}
	}
//	
}
