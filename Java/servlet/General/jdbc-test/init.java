import java.sql.*;
import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class init {
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document config = db.parse(new File("config.xml"));

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/" +
				config.getElementsByTagName("database").item(0).getTextContent() +
				"?useUnicode=true&characterEncoding=UTF-8",
				config.getElementsByTagName("username").item(0).getTextContent(),
				config.getElementsByTagName("password").item(0).getTextContent()
			);

			Statement s = con.createStatement();
			s.executeUpdate(
			"CREATE TABLE forum (" +
				"ID INTEGER NOT NULL AUTO_INCREMENT," +
				"nickname VARCHAR(30) NOT NULL," +
				"message VARCHAR(200) NOT NULL," +
				"postTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL," +
				"PRIMARY KEY(ID)" +
			") ENGINE = InnoDB DEFAULT CHARSET = utf8;"
			);

			s.executeUpdate(
			"INSERT INTO " +
				"forum (nickname, message)" +
			"VALUES" +
				"('香港记者', '江主席，你觉得董先生连任，好不好啊？')," +
				"('江主席', '吼蛙')," +
				"('香港记者', '中央也资辞他吗？')," +
				"('江主席', '当然啦！')," +
				"('香港记者', '那为什么这么早就决定了，而不考虑别的人选了？')," +
				"('江主席', '[正在落座，没有回答]')," +
				"('江主席', '没听说过')," +
				"('香港记者', '是彭定康说的。')," +
				"('江主席', '你们媒体千万要注意了，不要见着风是得雨')," +
				"('江主席', '假使这些完全无中生有的东西，你再帮他说一遍，你等于——你也有责任吧？')," +
				"('香港记者', '现在那么早，你们就说支持董先生，会不会给人一种感觉——就是内定呀——是钦点董先生呀？')," +
				"('江主席', '没有，没有任何这个意思！')," +
				"('江主席', '还是按照香港的、按照基本法、按照选举法——去产生……')," +
				"('香港记者', '但是你们能不能……')," +
				"('江主席', '刚才你们问我呀，我可回答你说“无可奉告”。')," +
				"('江主席', '但是你们又不高兴，那怎么办？！')," +
				"('香港记者', '但董先生……')," +
				"('江主席', '我讲的意思，不是钦点他当下一任。')"
			);

			s.close(); con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}