package insights;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class MySQL {
	String driver;// JDBCドライバの登録
    String server, dbname, url, user, password;// データベースの指定
    Connection con;
    Statement stmt;
    Map<String, Object> lng = new HashMap<>();
    
	public MySQL() {
		this.driver = "org.gjt.mm.mysql.Driver";
        this.server = "ms000.sist.ac.jp";
        this.dbname = "ms000";
        this.url = "jdbc:mysql://" + server + "/" + dbname + "?useUnicode=true&characterEncoding=UTF-8";
        this.user = "naisyo";
        this.password = "naisyo";
        try {
            this.con = DriverManager.getConnection(url, user, password);
            this.stmt = con.createStatement ();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Class.forName (driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
		
	//値の無い物全て
	public ResultSet getID() {
		ResultSet rs = null;
		String sql = "SELECT * FROM  `screens` WHERE  `openness` = -1";
		try {
			rs = stmt.executeQuery (sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getContent(int id) {
		ResultSet rs = null;
		String sql = "SELECT * FROM  `timelines` WHERE  `screen_id` =" + id;
		try {
			rs = stmt.executeQuery (sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void updateImage(double result[][], int id) {
		//keywordテーブルへ格納
		StringBuffer buf = new StringBuffer();
		buf.append("UPDATE screens SET `openness` = " + result[0][0] + ", `conscientiousness` = " + result[1][0] + ", `extraversion` = " + result[2][0] + ", `agreeableness` = " + result[3][0] + ", `neuroticism` = " + result[4][0] + ",`adventurousness`= " + result[0][1] + ",`artistic`= " + result[0][2] + ",`emotionality`= " + result[0][3] + ",`imagination`= " + result[0][4] + ",`intellect`= " + result[0][5] + ",`challenging`= " + result[0][6] + ",`striving`= " + result[1][1] + ",`cautiousness`= " + result[1][2] + ",`dutifulness`= " + result[1][3] + ",`orderliness`= " + result[1][4] + ",`discipline`= " + result[1][5] + ",`efficacy`= " + result[1][6] + ",`activity`= " + result[2][1] + ",`assertiveness`= " + result[2][2] + ",`cheerfulness`= " + result[2][3] + ",`seeking`= " + result[2][4] + ",`outgoing`= " + result[2][5] + ",`gregariousness`= " + result[2][6] + ",`altruism`= " + result[3][1] + ",`cooperation`= " + result[3][2] + ",`modesty`= " + result[3][3] + ",`uncompromising`= " + result[3][4] + ",`sympathy`= " + result[3][5] + ",`trust`= " + result[3][6] + ",`fiery`= " + result[4][1] + ",`worry`= " + result[4][2] + ",`melancholy`= " + result[4][3] + ",`immoderation`= " + result[4][4] + ",`consciousness`= " + result[4][5] + ",`susceptible`= " + result[4][6] + "  WHERE  id = " + id + ";");
		String sql = buf.toString();
		try {
			stmt.execute (sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
