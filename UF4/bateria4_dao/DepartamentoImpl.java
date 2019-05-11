package bateria4_dao;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.IValuesQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class DepartamentoImpl implements DepartamentoDAO {
	private static ODB bd;

	DepartamentoImpl(){
		if (bd == null) {
			bd = ODBFactory.open("Departamento.DB");
		}
	}

	@Override
	public boolean InsertarDep(Departamento dep) {
		
		if (dep == null) {
			return false;
		} else {
			
			IQuery query = new CriteriaQuery(Departamento.class, Where.equal("deptno", dep.getDeptno()));
			Objects<Departamento> departamento = bd.getObjects(query);

			if (departamento.size() > 0) {
				return false;
			}

			bd.store(dep);
			return true;
		}
	}

	@Override
	public boolean EliminarDep(int deptno) {
		IQuery query = new CriteriaQuery(Departamento.class, Where.equal("deptno", deptno));
		Objects<Departamento> depart = bd.getObjects(query);

		if (deptno > 0 || depart.size() > 0) {
			bd.delete(depart.getFirst());
			bd.commit();
			return true;
		}
		
		return false;
	}

	@Override
	public boolean ModificarDep(int deptno, Departamento dep) {
		Departamento depEditado = ConsultarDep(deptno);

		if (depEditado!=null) {
			depEditado.setDeptno(dep.getDeptno());
			depEditado.setDnombre(dep.getDnombre());
			depEditado.setLoc(dep.getLoc());
			bd.store(depEditado);
			return true;
		}
		
		return false;
	}

	@Override
	public Departamento ConsultarDep(int deptno) {
		IQuery query = new CriteriaQuery(Departamento.class, Where.equal("deptno", deptno));
		Objects<Departamento> dept = bd.getObjects(query);  
		
		if(dept!=null && dept.size()>0) {
			Departamento dept2 = (Departamento) dept.getFirst();
			return dept2;  
			
		} else {
			System.out.println("No existe este departamento");
			return null;
		}
	}
}
