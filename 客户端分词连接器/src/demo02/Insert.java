package demo02;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {
	public static final String url = "jdbc:mysql://localhost:3306/book";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "lhy942821";

	public static void main(String[] args) {

		Connection con = null;
		Statement stmt = null;

		try {
			Class.forName(name);// 指定连接类型
			con = DriverManager.getConnection(url, user, password);// 获取连接

			stmt = con.createStatement();

			BufferedReader bre = null;
			// String file = "d.txt";

			String file = "Dict.txt";
			bre = new BufferedReader(new FileReader(file));
			String line;

			while ((line = bre.readLine()) != null) // 判断最后一行不存在，为空结束循环
			{
				String sql = "insert into dictionary(word)values('"+line+"')";
				stmt.executeUpdate(sql);
			}
			bre.close();

			// ResultSet提供的借口对结果集中的数据进行操作

			// ....查询指定集合
			
			
			/*String selectSql = "select * from bookinfo";
			ResultSet selectRes = stmt.executeQuery(selectSql);
			while (selectRes.next()) {
				String author = selectRes.getString("word");
				System.out.println(author);
			}*/

			// ...插入数据

			// stmt.executeUpdate("insert into bookinfo(id,bookname,author,price)
			// values('1','sdf','sdfs','34')");
			con.close();
			//selectRes.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println("yes");
		// ResultSet res = stmt.executeQuery("select LAST_INSERT_ID()");
		/*
		 * int ret_id; if (res.next()) { ret_id = res.getInt(1);
		 * System.out.print(ret_id); }
		 */

		// sql = "Delete from bookinfo where id = 1";
		// long deleteRes = stmt.executeUpdate(sql);
		// System.out.print("DELETE:"+deleteRes);

		/*
		 * while (selectRes.next()) { String username =
		 * selectRes.getString("id"); String author =
		 * selectRes.getString("author"); System.out.println("username:" +
		 * username + "author:" + author); }
		 */

	}

}