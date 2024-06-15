package dao;

import entities.Task;

public interface TaskDao {
	
	void insert(Task task);
	void update(Task task);
	void deleteById(Integer id);
	void exibirTabela(String nomeTabela);
}
