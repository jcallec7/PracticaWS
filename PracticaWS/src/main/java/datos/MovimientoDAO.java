package datos;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Movimiento;

@Stateless
public class MovimientoDAO {

	@Inject
	private EntityManager em;

	public void insert(Movimiento movimiento) {
		em.persist(movimiento);

	}

	public void update(Movimiento movimiento) {
		em.merge(movimiento);
	}

	public void remove(int id) {

		Movimiento movimiento = this.read(id);
		em.remove(movimiento);

	}

	public Movimiento read(int id) {

		Movimiento a = em.find(Movimiento.class, id);
		return a;

	}

	public List<Movimiento> getMovimientos() {

		String jpql = "SELECT c FROM Movimiento c";
		Query q = em.createQuery(jpql, Movimiento.class);

		List<Movimiento> movimientos = q.getResultList();

		return movimientos;
	}

	public List<Movimiento> getMovimientosPorCuenta(int filtro) {
		
		String jpql = "SELECT a FROM Movimiento a WHERE ws_cu_id = ?1";
		Query q = em.createQuery(jpql, Movimiento.class);
		q.setParameter(1, "%" + filtro + "%");

		List<Movimiento> movimientos = q.getResultList();

		return movimientos;
	}

}
