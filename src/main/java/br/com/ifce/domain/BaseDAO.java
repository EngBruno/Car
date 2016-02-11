package br.com.ifce.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	public BaseDAO(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	protected Connection getConnetion() throws SQLException{
		String url = "jdbc:mysql://localhost/livro";
		Connection conn = DriverManager.getConnection(url, "livro", "livro123");
		return conn;
	}
}
