package org.zerock.persistence;

import static org.junit.Assert.*;

import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	// Class �� JDBC �� ���������� ���� ���� ���θ� �׽�Ʈ �ϱ� 
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	@Test
	public void testConnection() {
		
		try {
			DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE", 
					"kosta192", 
					"1234");
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}

}
