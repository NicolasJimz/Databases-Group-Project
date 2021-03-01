import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;

public class ConnectDB {

	private static Connection conn = null;
	private static String databaseName = "movies"; //Provide database name
	private static String url = "jdbc:mysql://localhost:3306/" + databaseName; //Provide url to database. Example: "jdbc:mysql://localhost:3306/" + databaseName
	private static String username = "root"; //Provide username
	private static String password = "password"; //Provide password
	
	// Select for problem 5
	// Highest movie score with actor name
	public static String Select5(String ActorName) throws ClassNotFoundException, SQLException
	{
		Class.forName("java.sql.Driver");
		conn = DriverManager.getConnection(url,username,password);
		
		// Execute select statement
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT mc.aname, mns.mtitle, mns.mscore " + 
				"FROM movienamescore mns " +
				"JOIN moviecast mc ON mns.mid=mc.mid " +
				"WHERE mc.aname='" + ActorName + "' AND mns.mscore = " +
					"(SELECT MAX(mscore) FROM movienamescore mns " +
					"JOIN moviecast mc ON mns.mid=mc.mid " +
				    "WHERE mc.aname='" + ActorName +"');");
		
		// Get results and format for printing
		String output = "";
		while(rs.next()) {
			String aname = rs.getString("aname");
			String mtitle = rs.getString("mtitle");
			String mscore = rs.getString("mscore");
			
			output += "Actor name: " + aname +
					"\nMovie title: " + mtitle +
					"\nMovie score: " + mscore + "\n\n";
		}
		return output;
	}
	
	// Select for problem 6
	// Find movies with key words
	public static String Select6(String keyword1, String keyword2, String keyword3) throws ClassNotFoundException, SQLException
	{
		Class.forName("java.sql.Driver");
		conn = DriverManager.getConnection(url,username,password);
		String output = "";
		int keywords = 0;
		// Ensure correct select is executed based on which arguments are passed
		if(keyword1.length() != 0) {
			keywords += 1;
		}
		if(keyword2.length() != 0) {
			keywords += 2;
		}
		if(keyword3.length() != 0) {
			keywords += 4;
		}
		
		Statement statement = conn.createStatement();
		
		switch(keywords) {
		// keyword1
		case 1:
			ResultSet k1 = statement.executeQuery("SELECT mtitle FROM movienamescore " + 
					"WHERE mtitle LIKE '%" + keyword1 +"%';");
			// Get results and format for printing
			while(k1.next()) {
				String mtitle = k1.getString("mtitle");
				output += "Movie title: " + mtitle + "\n";
			}
			break;
			
		// keyword2
		case 2:
			ResultSet k2 = statement.executeQuery("SELECT mtitle FROM movienamescore " + 
					"WHERE mtitle LIKE '%" + keyword2 +"%';");
			// Get results and format for printing
			while(k2.next()) {
				String mtitle = k2.getString("mtitle");					
				output += "Movie title: " + mtitle + "\n";
			}
			break;
		
		// keywords 1 & 2
		case 3:
			ResultSet k12 = statement.executeQuery("SELECT mtitle FROM movienamescore " + 
					"WHERE mtitle LIKE '%" + keyword1 + "%' " + 
					"AND mtitle LIKE '%" + keyword2 + "%';");
			// Get results and format for printing
			while(k12.next()) {
				String mtitle = k12.getString("mtitle");					
				output += "Movie title: " + mtitle + "\n";
			}
			break;
			
		// keyword3
		case 4:
			ResultSet k3 = statement.executeQuery("SELECT mtitle FROM movienamescore " + 
					"WHERE mtitle LIKE '%" + keyword3 +"%';");
			// Get results and format for printing
			while(k3.next()) {
				String mtitle = k3.getString("mtitle");				
				output += "Movie title: " + mtitle + "\n";
			}
			break;
			
		// keywords 1 & 3
		case 5:
			ResultSet k13 = statement.executeQuery("SELECT mtitle FROM movienamescore " + 
					"WHERE mtitle LIKE '%" + keyword1 + "%' " + 
					"AND mtitle LIKE '%" + keyword3 + "%';");
			// Get results and format for printing
			while(k13.next()) {
				String mtitle = k13.getString("mtitle");				
				output += "Movie title: " + mtitle + "\n";
			}
			break;
			
		// keywords 2 & 3
		case 6:
			ResultSet k23 = statement.executeQuery("SELECT mtitle FROM movienamescore " + 
					"WHERE mtitle LIKE '%" + keyword2 + "%' " + 
					"AND mtitle LIKE '%" + keyword3 + "%';");
			// Get results and format for printing
			while(k23.next()) {
				String mtitle = k23.getString("mtitle");					
				output += "Movie title: " + mtitle + "\n";
			}
			break;
			
		// keywords 1, 2, & 3
		case 7:
			ResultSet k123 = statement.executeQuery("SELECT mtitle FROM movienamescore " + 
					"WHERE mtitle LIKE '%" + keyword1 + "%' " + 
					"AND mtitle LIKE '%" + keyword2 + "%' " + 
					"AND mtitle LIKE '%" + keyword3 + "%';");
			// Get results and format for printing
			while(k123.next()) {
				String mtitle = k123.getString("mtitle");	
				output += "Movie title: " + mtitle + "\n";
			}
			break;
			
		// No keywords entered
		default:
			output = "Please enter at least 1 key word before executing";
			break;
		}
		return output;
	}
	
	// Select for problem 7
	// Find 10 most popular actors based on movies they have been in
	public static String Select7() throws ClassNotFoundException, SQLException
	{
		Class.forName("java.sql.Driver");
		conn = DriverManager.getConnection(url,username,password);
		
		// Execute select statement
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT mc.aname, AVG(mns.mscore) as avgscore " + 
				"FROM movienamescore mns " + 
				"JOIN moviecast mc ON mns.mid=mc.mid " + 
				"GROUP BY mc.aid " + 
				"ORDER BY avgscore DESC " + 
				"LIMIT 10");
		
		// Get results and format for printing 
		String output = "";
		while(rs.next()) {
			String aname = rs.getString("aname");
			String avgscore = rs.getString("avgscore");
			
			output += "Actor name: " + aname + "\n" +
						"Average score: " + avgscore + "\n\n";
		}
		return output;
	}
	
	// Select for problem 8
	// Find group of actors that have been in the most movies together
	public static String Select8() throws ClassNotFoundException, SQLException
	{
		Class.forName("java.sql.Driver");
		conn = DriverManager.getConnection(url,username,password);
		
		// Execute select statement
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT namepairs.actorone, namepairs.actortwo, COUNT(mid) AS pairings " + 
				"FROM (SELECT a.aname AS actorone, b.aname AS actortwo, a.mid " + 
					"FROM moviecast a " + 
					"INNER JOIN moviecast b ON a.mid=b.mid " + 
					"AND a.aname!=b.aname) AS namepairs " + 
				"GROUP BY namepairs.actorone, namepairs.actortwo " + 
				"ORDER BY count(mid) DESC, " + 
				"namepairs.actorone, " + 
				"namepairs.actortwo LIMIT 1;");
		
		// Get results and format for printing 
		String output = "";
		while(rs.next()) {
			String actorone = rs.getString("actorone");
			String actortwo = rs.getString("actortwo");
			String pairings = rs.getString("pairings");
					
			output += actorone + " and " + actortwo + " have been in " + pairings + 
					" movies together";
		}
		return output;
	}
	
	// Select for problem 9
	// Find lowest scoring movie
	public static String Select9() throws ClassNotFoundException, SQLException
	{
		Class.forName("java.sql.Driver");
		conn = DriverManager.getConnection(url,username,password);
		
		// Execute select statement
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT mtitle, mscore FROM movienamescore " +
				"WHERE mscore != -1 " +
				"ORDER BY mscore ASC " +
				"LIMIT 1;");
		
		// Get results and format for printing 
		String output = "";
		while(rs.next()) {
			String mtitle = rs.getString("mtitle");
			String mscore = rs.getString("mscore");
					
			output += "Movie title: " + mtitle + " - " +
						"Movie score: " + mscore + "\n";
		}
		return output;
	}
	
	// Insert movie cast text file
	public static void InsertTextFiles(String pathToMNS, String pathToMC) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("java.sql.Driver");
        conn = DriverManager.getConnection(url,username,password);
        
        // Create tables
        // movienamescore table
        PreparedStatement mns = conn.prepareStatement("CREATE TABLE movienamescore( " +
        		"mid int, " + 
        		"mtitle varchar(300), " + 
        		"mscore int, " + 
        		"PRIMARY KEY (mid));");
		mns.execute();
		
		// moviecast table
		PreparedStatement mc = conn.prepareStatement("CREATE TABLE moviecast( " + 
				"mid int, " + 
				"aid int, " + 
				"aname varchar(100), " + 
				"PRIMARY KEY (mid, aid), " + 
				"FOREIGN KEY (mid) REFERENCES movienamescore(mid));");
		mc.execute();
        
        BufferedReader reader = null;
        
        // Read movienamescore text file line by line
        try {
			reader = new BufferedReader(new FileReader(pathToMNS));
					//"/Users/Nicolas/Desktop/Databases/GroupProject/movie-name-score.txt"));
			String line = null;
			String mid = null;
			String mtitle = null;
			String mscore = null;
			int c = 1;
			while ((line = reader.readLine()) != null) {
				// Seperate data and insert
				String splitLine = line.replaceFirst(",\"", "\t");
				splitLine = splitLine.replaceFirst("\",", "\t");
				splitLine = splitLine.replaceAll("'", "''");
				String[] data = splitLine.split("\t");
				mid = data[0];
				mtitle = data[1];
				mscore = data[2];
				System.out.println(c);
				c++;
				
				// Insert
				PreparedStatement ps = conn.prepareStatement("INSERT INTO movienamescore (mid, mtitle, mscore) " +
				"VALUE (" + mid + ", '" + mtitle + "', " + mscore + ");");
				ps.execute();
				}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Read moviecast text file line by line
        try {
			reader = new BufferedReader(new FileReader(pathToMC));
					//"/Users/Nicolas/Desktop/Databases/GroupProject/movie-cast.txt"));
			String line = null;
			String mid = null;
			String aid = null;
			String aname = null;
			int c = 1;
			while ((line = reader.readLine()) != null) {
				// Seperate data and insert
				String splitLine = line.replaceFirst(",", "\t");
				splitLine = splitLine.replaceFirst(",\"", "\t");
				splitLine = splitLine.replaceAll("\"", "");
				splitLine = splitLine.replaceAll("'", "''");
				String[] data = splitLine.split("\t");
				mid = data[0];
				aid = data[1];
				aname = data[2];
				System.out.println(c);
				c++;
				
				// Insert
				PreparedStatement ps = conn.prepareStatement("INSERT INTO moviecast (mid, aid, aname) " +
				"VALUE (" + mid + ", " + aid + ", '" + aname + "');");
				ps.execute();
				}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}