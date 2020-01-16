package datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Cuenta;

@Stateless
public class CuentaDAO {
	
	@Inject
	private EntityManager em;

	public void insert(Cuenta cuenta) {
		em.persist(cuenta);

	}

	public void update(Cuenta cuenta) {
		em.merge(cuenta);
	}

	public void remove(int id) {

		Cuenta cuenta = this.read(id);
		em.remove(cuenta);

	}

	public Cuenta read(int id) {

		Cuenta a = em.find(Cuenta.class, id);
		return a;

	}

	public List<Cuenta> getCuentas() {

		String jpql = "SELECT c FROM Cuenta c";
		Query q = em.createQuery(jpql, Cuenta.class);

		List<Cuenta> cuentas = q.getResultList();

		return cuentas;
	}

	public List<Cuenta> getCuentasPorId(int filtro) {
		String jpql = "SELECT a FROM Cuenta a WHERE ws_cu_id = ?1";
		Query q = em.createQuery(jpql, Cuenta.class);
		q.setParameter(1, "%" + filtro + "%");

		List<Cuenta> cuentas = q.getResultList();

		return cuentas;
	}
	
	

}
