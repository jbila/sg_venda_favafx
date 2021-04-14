package mz.co.mahs.dao;

import java.util.List;
/**
 * Esta interface   e usada para acesso a base de dados
 * 
 * */
public interface BaseDeDados {


	public void add(Object object);

	public List<Object> list();

	public void delete(Object object);

	public void update(Object object);

	public boolean isRecorded(String nome);

}
