package application.service;

import java.io.IOException;

import application.Member;
import application.control.Controller;
import application.dao.DatabaseService;
import application.dao.DatabaseServiceImpl;
import application.util.SHA256;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
		TextField mail = (TextField) parent.lookup("#txtMail");
		Member m = new Member();
		System.out.println("??");
		boolean chk = false;
		if (id.getText().isEmpty() || pw.getText().isEmpty() || pwChk.getText().isEmpty() || name.getText().isEmpty()
				|| birth.getValue().toString().isEmpty() || mail.getText().isEmpty()) {
			cs.errorAlert("미입력", "미입력 항목", "빈 항목 확인");
			id.requestFocus();
			chk=true;
		} else if (!pw.getText().equals(pwChk.getText())) {
			cs.errorAlert("비밀번호 입력", "비밀번호 항목", "비밀번호 확인");
			pw.clear();
			pw.requestFocus();
			chk=true;
		} else if (dao.dupChecker("id", id.getText())) {
			cs.errorAlert("아이디 입력", "아이디 입력 항목", "아이디 입력 확인");
			id.clear();
			id.requestFocus();
			chk=true;
		}

		if (!chk) {
			m.setId(id.getText());
			m.setEmail(mail.getText());
			m.setName(name.getText());
			m.setBirth(birth.getValue().toString());
			m.setSalt(SHA256.generateSalt());
			m.setPw(SHA256.getEncrypt(pw.getText(), m.getSalt()));
			if (dao.insert(m)) {
				Alert alr = new Alert(AlertType.INFORMATION);
				alr.setTitle("회원가입");
				alr.setHeaderText("회원가입 완료");
				alr.setContentText("회원 가입이 완료 되었습니다. 로그인 해주세요");
				alr.showAndWait();
				Stage s = (Stage) parent.getScene().getWindow();
				s.close();
			} else {
				cs.errorAlert("회원가입", "회원 가입", "다시 시도");
			}
		}
	}

}
