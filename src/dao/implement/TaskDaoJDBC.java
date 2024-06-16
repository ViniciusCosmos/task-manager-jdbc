package dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.StringJoiner;

import dao.TaskDao;
import db.DB;
import db.DbException;
import entities.Task;

public class TaskDaoJDBC implements TaskDao {

	private Connection conn;

	public TaskDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Task task) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = conn.prepareStatement("INSERT INTO tasks" + "(Description)" + "VALUES (?)",
					+PreparedStatement.RETURN_GENERATED_KEYS);

			statement.setString(1, task.getDescription());

			int rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					int id = resultSet.getInt(1);
					task.setId(id);
				} else {
					System.out.println("Unexpected error! No rows affected.");
				}
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(resultSet);
			DB.closeStatement(statement);
		}

	}

	@Override
	public void update(Task task) {
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("UPDATE tasks " + "SET Description = ?" + "WHERE ID = ?");

			statement.setString(1, task.getDescription());
			statement.setInt(2, task.getId());

			int rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Update realizado! Tarefa alterada: " + task.getId());
			} else {
				throw new DbException("Erro na alteração!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(statement);
		}

	}

	@Override
	public void deleteById(Integer id){
		PreparedStatement statement = null;

		try {
			statement = conn.prepareStatement("DELETE FROM tasks WHERE id = ?");

			statement.setInt(1, id);

			int rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0)
				System.out.println("Task Deleted!");
			else
				System.out.println("Unexpected Error!");
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(statement);
		}

	}

	@Override
	public void showTable(String tableName) {
	    ResultSet rs = null;
	    PreparedStatement statement = null;

	    try {
	        if (!tableName.matches("[a-zA-Z_]+")) {
	            throw new DbException("Invalid Table Name! >( ");
	        }

	        statement = conn.prepareStatement("SELECT * FROM " + tableName);
	        rs = statement.executeQuery();
	        ResultSetMetaData metaData = rs.getMetaData();
	        int numColumn = metaData.getColumnCount();

	        while (rs.next()) {
	            StringJoiner joiner = new StringJoiner(" | ");
	            for (int column = 1; column <= numColumn; column++) {
	                //int nomeColuna = metaData.getColumnCount();
	                joiner.add(""+ rs.getObject(column));
	            }
	            System.out.println(joiner.toString());
	        }

	    } catch (SQLException e) {
	        throw new DbException(e.getMessage());
	    } finally {
	        DB.closeResultSet(rs);
	        DB.closeStatement(statement);
	    }
	}


}
