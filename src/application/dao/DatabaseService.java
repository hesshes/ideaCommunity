package application.dao;

import application.LogInfo;
import application.Member;

public interface DatabaseService {
	
	public boolean insert(Member m);
	
	public boolean delete(String id, String pw);
	
	public boolean dupChecker(String item, String value);
	
	public boolean login(String id, String pw, LogInfo log);
	
	public String getSalt(String id);

}
