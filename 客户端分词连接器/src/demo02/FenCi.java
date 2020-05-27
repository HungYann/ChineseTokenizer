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
import java.util.ArrayList;

class FenCi {

	public static final String url = "jdbc:mysql://localhost:3306/book";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "lhy942821";

	int start = 0;
	int total_len;
	int max_len = 4;
	int tmp_len;
	int i;
	String receive;

	// tmp_len用来存放当前切取的词语的长度
	public FenCi() {
	}

	public FenCi(int total_len, String receive) {
		this.total_len = total_len;
		this.receive = receive;
	}

	/*
	 * public FenCi(int total_len, String receive) { this.total_len = 13;
	 * this.receive = "李迪安说要买个四百万的老婆"; }
	 */

	ArrayList<String> words = new ArrayList<>();// 存放分好词的字符
	ArrayList<String> temp = new ArrayList<>();// 存放文件词组
	// 用来存放分好词语的句子

	public void Zheng() throws ClassNotFoundException, SQLException {
	
			Connection con = null;
			Statement stmt = null;
			Class.forName(name);//指定连接类型
			con = DriverManager.getConnection(url, user, password);// 获取连接
			
			stmt = con.createStatement();
			
			
			
			BufferedReader bre = null;
			//String file = "d.txt";
			String file = "Dict.txt";
			
			String line;
		
				
				String selectSql = "select * from dictionary";
				ResultSet selectRes = stmt.executeQuery(selectSql);
				while(selectRes.next())
				{
					String author = selectRes.getString("word");
					temp.add(author);
				}
				
				
				con.close();
				selectRes.close();
				
			

		String sub_str;
		while (start < total_len) {
			boolean flag = false;
			tmp_len = start + max_len <= total_len ? max_len : total_len - start;
			label: while (tmp_len > 0) {
				// System.out.println(1);
				sub_str = receive.substring(start, start + tmp_len);
				// System.out.println(sub_str);
				for (i = 0; i < temp.size(); i++) {
					if (sub_str.equals(temp.get(i))) {
						flag = true;
					}
				}

				if (flag == true) {
					words.add(receive.substring(start, start + tmp_len));
					start = start + tmp_len;
					break label;
				} else {
					tmp_len = tmp_len - 1;
					// System.out.println(tmp_len);
				}

				/*
				 * if sub_str in dict: words.append(setence[start : start +
				 * tmp_len]) start = start + tmp_len break else: tmp_len =
				 * tmp_len -1
				 */
			}
			if (tmp_len == 0) {
				words.add(receive.substring(start, start + 1));
				start = start + 1;
			}

		}
		// '''''''''''''''''''''''''更改此处即可

		/*
		 * for (i = 0; i < words.size();i++) { if (i < words.size() - 1)
		 * System.out.print(words.get(i) + "\\"); else
		 * System.out.print(words.get(i)); }
		 */
		// '''''''''''''
	}

	public String main() throws ClassNotFoundException, SQLException {
		FenCi cc = new FenCi(total_len, receive);

		cc.Zheng();

		return cc.getString() + "\n" + cc.KeyWord();

	}

	public String getString() {

		String str = "";
		for (i = 0; i < words.size(); i++) {
			if (i < words.size() - 1)
				str = str + words.get(i) + "\\";
			else
				str = str + words.get(i);
		}

		return str;
	}

	public String KeyWord() {
		boolean pan = false;
		String temp = "";
		String[][] num = { { "喜欢就好！", "我不喜欢你！" }, { "是你？！", "对车!" }, { "不客气", "客气啦" }, { "我永远18岁!", "问我这个问题，你是要相亲吗？" },
				{ "你可以自己去下载一个来玩，然后把我训练的更逗！", "我支持多人一起调教哦，你可以让我更逗！" }, { "李迪安是谁，能吃吗？", "李迪安是谁，能玩吗？" },
				{ "早上好!", "脚上好!" }, { "还没呢！要不要陪我一起吃呀！", "是问早饭还是晚饭" }, { "那你就少穿点儿！", "在室内就开空调呀!" } };
		int j = 0;
		// int x = (int) (Math.random() * 1);

		label: for (i = 0; i < words.size(); i++) {
			int x = (int) (Math.random() * 2);
			switch (words.get(i)) {
			case "喜欢我":
				j = 0;
				temp = num[j][x];
				pan = true;
				break label;
			case "对象":
				j = 1;
				temp = num[j][x];
				pan = true;
				break label;
			case "谢谢你":
				j = 2;
				temp = num[j][x];
				pan = true;
				break label;
			case "年龄":
				j = 3;
				temp = num[j][x];
				pan = true;
				break label;
			case "真逗":
				j = 4;
				temp = num[j][x];
				pan = true;
				break label;
			case "李迪安":
				j = 5;
				temp = num[j][x];
				pan = true;
				break label;
			case "早上好":
				j = 6;
				temp = num[j][x];
				pan = true;
				break label;
			case "吃饭":
				j = 7;
				temp = num[j][x];
				pan = true;
				break label;
			case "好热":
				j = 8;
				temp = num[j][x];
				pan = true;
				break label;
			}

		}
		if (pan == false) {
			temp = "我耳朵不好！没有听懂你在说什么？";
		}

		return temp;
	}

}
/*
 * label: for (i = 0; i < words.size(); i++) { switch (words.get(i)) { case
 * "喜欢": j = 0; pan = true; break label; case "对象": j = 1; pan = true; break
 * label; case "谢谢": j = 2; pan = true; break label; case "年龄": j = 3; pan =
 * true; break label; case "真逗": j = 4; pan = true; break label; case "李迪安": j =
 * 5; pan = true; break label; }
 * 
 * }
 * 
 * if (pan == true) { switch (j) { case 0: temp = num[j][x]; break; case 1: temp
 * = num[j][x]; break; case 2: temp = num[j][x]; break; case 3: temp =
 * num[j][x]; break; case 4: temp = num[j][x]; break; case 5: temp = num[j][x];
 * break;
 * 
 * }
 * 
 * } else { temp = "我耳朵不好！没有听懂你在说什么？"; }
 */
