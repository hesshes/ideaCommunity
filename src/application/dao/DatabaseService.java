package application.dao;

import application.Member;

public interface DatabaseService {
	
	public boolean insert(Member m);
	public boolean dupChecker(String item, String value);

}
