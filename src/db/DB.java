package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	public static Connection getConnection() {

		if (conn == null) {
			try {
				Properties properties = loadProperties(); // passando a lista de propriedades para a variavel
				String url = properties.getProperty("dburl"); // pegando somente uma das propriedades
				conn = DriverManager.getConnection(url, properties);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}

		}
		return conn;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static Properties loadProperties() {
		try (InputStream parameters = new FileInputStream("db.properties")) { // fazendo a leitura dos parametros do
																				// banco de dados
			Properties properties = new Properties();
			properties.load(parameters); // passando a lista de parametros para um variavel do tipo Properties
			return properties;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}

	}

	public static void closeStatement(PreparedStatement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}

	}
}
