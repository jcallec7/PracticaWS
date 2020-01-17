package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import datos.MovimientoDAO;
import datos.CuentaDAO;
import modelo.Movimiento;
import utils.Transferencia;
import modelo.Cuenta;

@Stateless
public class GestionMovimiento implements GestionMovimientoRemote, GestionMovimientoLocal {

	@Inject
	private MovimientoDAO dao;
	
	@Inject
	private CuentaDAO daoCuenta;

	private List<Movimiento> movimientos = new ArrayList<Movimiento>();
	

	public void guardarMovimiento(Transferencia t) {

		Movimiento m = new Movimiento();
		m.setFecha(new Date());
		Cuenta cuenta = daoCuenta.read(t.getOrigenId());
		
		if(t.getMonto() < 0 && cuenta.getSaldo() >= t.getMonto()) {
			m.setMonto(t.getMonto());
			m.setMonto(t.getMonto());
			cuenta.getMovimientos().add(m);
			cuenta.setSaldo(cuenta.getSaldo() + t.getMonto());
			dao.insert(m);
			daoCuenta.update(cuenta);
		} else if(t.getMonto() > 0) {
			m.setMonto(t.getMonto());
			m.setMonto(t.getMonto());
			cuenta.getMovimientos().add(m);
			cuenta.setSaldo(cuenta.getSaldo() + t.getMonto());
			dao.insert(m);
			daoCuenta.update(cuenta);
		} else {
			System.out.println("Error, no hay suficiente saldo o no se permite un monto de 0");
		}
		
	}
	
	public void guardarTransferencia(Transferencia t) {
		
		Cuenta cuentaOrigen = daoCuenta.read(t.getOrigenId());
		Cuenta cuentaDestino = daoCuenta.read(t.getDestinoId());
		
		if(cuentaOrigen.getSaldo() >= t.getMonto()) {
			Movimiento m1 = new Movimiento();
			Movimiento m2 = new Movimiento();
			m1.setFecha(new Date());
			m2.setFecha(new Date());
			m1.setMonto(t.getMonto());
			m2.setMonto(t.getMonto() * -1);
			cuentaOrigen.getMovimientos().add(m2);
			cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - t.getMonto());
			cuentaDestino.getMovimientos().add(m1);
			cuentaDestino.setSaldo(cuentaDestino.getSaldo() + t.getMonto());
			dao.insert(m1);
			dao.insert(m2);
			daoCuenta.update(cuentaOrigen);
			daoCuenta.update(cuentaDestino);
		} else {
			System.out.println("No hay suficiente saldo");
		}
		
	}
	
	

	public List<Movimiento> getMovimientos() {
		System.out.println(movimientos);
		//daoCuenta.getCuentas();
		return dao.getMovimientos();
	}

	public List<Movimiento> getMovimientosPorId(int filtro) {
		Cuenta cuenta = daoCuenta.read(filtro);
		return cuenta.getMovimientos();
	}
	
}
