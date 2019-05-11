package bateria4_dao;

public interface DepartamentoDAO {
	
	public boolean InsertarDep(Departamento dep);
	public boolean EliminarDep(int deptno);
	public boolean ModificarDep(int deptn, Departamento dep);
	public Departamento ConsultarDep(int deptno);
	
}
