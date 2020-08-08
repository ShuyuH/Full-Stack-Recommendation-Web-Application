package db;

public class MySQLDBUtil {
	private static final String INSTANCE = "project-recommendation-system.c5wlnssr0ap0.us-east-2.rds.amazonaws.com";
	private static final String PORT_NUM ="3306";
	public static final String DB_NAME = "RecomSystem";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "Hsy97422";
	public static final String URL = "jdbc:mysql://" 
								+ INSTANCE + ":" + PORT_NUM +'/' +DB_NAME 
								+ "?user=" + USERNAME + "&password=" + PASSWORD 
								+ "&autoReconnect=true&serverTimezone=UTC"; 
	

}
