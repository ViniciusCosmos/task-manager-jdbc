package application;

import java.sql.Connection;
import java.util.Scanner;
import dao.TaskDao;
import dao.implement.TaskDaoJDBC;
import db.DB;
import entities.Task;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean continueRunning = true;

		while (continueRunning) {
			System.out.println("\n==== Tasks ====");
			showTasks();

			System.out.println("\n==== Menu ====");
			System.out.println("1 - Add Task");
			System.out.println("2 - Delete Task");
			System.out.println("3 - Edit Task");
			System.out.println("0 - Exit");

			if (sc.hasNextInt()) {
				int option = sc.nextInt();
				sc.nextLine();

				switch (option) {
				case 1:
					addTasks(sc);
					break;
				case 2:
					deleteTask(sc);
					break;
				case 3:
					editTask(sc);
					break;
				case 0:
					continueRunning = false;
					break;
				default:
					System.out.println("Invalid Option! Try Again.");
				}
			} else {
				System.out.println("Invalid Enter! Try Again.");
				sc.next();
			}
		}

		System.out.println("End!");

		sc.close();
	}

	public static void showTasks() {
		Connection conn = DB.getConnection();
		TaskDao taskDao = new TaskDaoJDBC(conn);

		String table = "tasks";
		taskDao.showTable(table);
	}

	public static void addTasks(Scanner sc) {
		Connection conn = DB.getConnection();
		TaskDao taskDao = new TaskDaoJDBC(conn);

		System.out.println("Digite a tarefa: ");
		String tarefa = sc.nextLine();

		Task newTask = new Task(tarefa);
		taskDao.insert(newTask);

		System.out.println("Task add!");
	}

	public static void deleteTask(Scanner sc) {
		Connection conn = DB.getConnection();
		TaskDao taskDao = new TaskDaoJDBC(conn);

		System.out.println("Enter the id you want to delete: ");
		int id = sc.nextInt();

		taskDao.deleteById(id);
	}

	public static void editTask(Scanner sc) {
		
		//ainda pra implementar o findById
		
		Connection conn = DB.getConnection();
		TaskDao taskDao = new TaskDaoJDBC(conn);
		
		System.out.println("Enter the id you want to edit: ");
		int id = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Enter the alteration: ");
		String alter = sc.nextLine();
		
		Task newtask = new Task(id, alter);
		
		taskDao.update(newtask);
	}
}
