package tr.com.cafeotomasyonu.interfaces;

import java.util.List;

public interface DALinterfaces<T> {//generic olduğunu gösterir
	public void Insert(T entity);
	public List<T> GetAll();//tüm verileri listeler
	public T Delete(T entitiy);
	public void Update(T entity);
	public List<T> GetById(int id);//parametreli listeleme yapabilmek için

	
	
}
