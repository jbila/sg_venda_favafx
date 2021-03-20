package mz.co.mahs.dao;

import java.util.List;

public interface BaseDeDados {


	public void add(Object object);

	public List<Object> list();

	public void delete(int id);

	public void update(Object object);

	public boolean isRecorded(String nome);

}
