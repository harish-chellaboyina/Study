package ProjectUtils;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashSet;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.io.IOUtils;
import org.reflections.Reflections;

/*
 * This class the utilities required for the project
 */
public class Utils {
	
	private static Connection dataBaseConnection = null;
	static String uuidJS = null;
	static ScriptEngineManager engineManager = new ScriptEngineManager();
	static ScriptEngine engine = engineManager.getEngineByName("JavaScript");
	static Reflections reflections = null;
	static Set<String> cssFiles = new HashSet<String>();
	
	/*
	 * This is the place where the database connection is created.
	 * Connection is created only once at the time of server start
	 */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dataBaseConnection = DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/DoctorAppointmentManagementSystem","root","harish");
		} catch (Exception e) {
			e.printStackTrace();
		}   
	}
	
	
	/*
	 * This method returns the connection of the database
	 */
	public static Connection getDBConnection() {
		return dataBaseConnection;
	}
	
	
	/*
	 * This method returns the text of required CSS file
	 */
	public String getCSSFile(String fileName){
		try {
			return IOUtils.toByteArray(this
					.getClass().getResourceAsStream(fileName)).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
