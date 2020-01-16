package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import datos.MovimientoDAO;
import datos.CuentaDAO;
import modelo.Movimiento;
import modelo.Cuenta;

@Stateless
public class GestionMovimiento implements GestionMovimientoRemote, GestionMovimientoLocal {

	@Inject
	private MovimientoDAO dao;
	
	@Inject
	private CuentaDAO daoCuenta;

	private List<Movimiento> movimientos = new ArrayList<Movimiento>();

	public void guardarMovimiento(int id, Date fecha, Cuenta origen, Cuenta destino, double monto) {

		Movimiento m = new Movimiento();
		m.setId(id);
		m.setFecha(fecha);
		m.setOrigen(origen);
		m.setDestino(destino);
		m.setMonto(monto);
		movimientos.add(m);
		System.out.println(m);
		dao.insert(m);
	}

	public List<Movimiento> getMovimientos() {
		System.out.println(movimientos);
		//daoCuenta.getCuentas();
		return dao.getMovimientos();
	}

	public List<Movimiento> getMovimientosPorNombre(int filtro) {
		return dao.getMovimientosPorCuenta(filtro);
	}
	
}
