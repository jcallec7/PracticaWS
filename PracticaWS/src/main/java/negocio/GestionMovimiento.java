package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import datos.MovimientoDAO;
import datos.CuentaDAO;
import modelo.Movimiento;
import servicios.Respuesta;
import utils.Transferencia;
import modelo.Cuenta;

@Stateless
public class GestionMovimiento implements GestionMovimientoRemote, GestionMovimientoLocal {

	@Inject
	private MovimientoDAO dao;
	
	@Inject
	private CuentaDAO daoCuenta;

	private List<Movimiento> movimientos = new ArrayList<Movimiento>();
	
	private Cuenta cuenta;
	private Cuenta cuentaOrigen;
	private Cuenta cuentaDestino;
	

	public Respuesta guardarMovimiento(Transferencia t) {
		
		Respuesta r = new Respuesta();
		
		Movimiento m = new Movimiento();
		m.setFecha(new Date());
		
		try {
			cuenta = daoCuenta.read(t.getOrigenId());
			
			if(cuenta != null) {
				r.setCodigo(10);
				r.setMensaje("OK");
				if(t.getMonto() < 0 && cuenta.getSaldo() >= (t.getMonto() * -1)) {
					m.setMonto(t.getMonto());
					cuenta.getMovimientos().add(m);
					cuenta.setSaldo(cuenta.getSaldo() + t.getMonto());
					dao.insert(m);
					daoCuenta.update(cuenta);
					r.setMensaje("OK" + " - Saldo Cuenta: " + cuenta.getSaldo());
				} else if(t.getMonto() > 0) {
					m.setMonto(t.getMonto());
					cuenta.getMovimientos().add(m);
					cuenta.setSaldo(cuenta.getSaldo() + t.getMonto());
					dao.insert(m);
					daoCuenta.update(cuenta);
					r.setMensaje("OK" + " - Saldo Cuenta: " + cuenta.getSaldo());
				} else {
					r.setMensaje("No dispone de suficiente saldo para realizar el debito o no ha ingresado ningun valor.");
				}
			} else {
				r.setCodigo(11);
				r.setMensaje("El numero de cuenta esta mal escrito o no existe");
			}
				
		} catch (Exception e) {
			r.setCodigo(11);
			r.setMensaje("El numero de cuenta esta mal escrito o no existe");
		}
		
		
		return r;
		
	}
	
	public Respuesta guardarTransferencia(Transferencia t) {

		Respuesta r = new Respuesta();
		
		
		try {
			
			cuentaOrigen = daoCuenta.read(t.getOrigenId());
			cuentaDestino = daoCuenta.read(t.getDestinoId());
			
			if(cuentaOrigen != null && cuentaDestino != null) {
				
				r.setCodigo(10);
				
				
				if(cuentaOrigen.getSaldo() >= t.getMonto()) {
					Movimiento m1 = new Movimiento();
					Movimiento m2 = new Movimiento();
					m1.setFecha(new Date());
					m2.setFecha(new Date());
					m1.setMonto(t.getMonto() * -1);
					m2.setMonto(t.getMonto());
					cuentaOrigen.getMovimientos().add(m1);
					cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - t.getMonto());
					cuentaDestino.getMovimientos().add(m2);
					cuentaDestino.setSaldo(cuentaDestino.getSaldo() + t.getMonto());
					dao.insert(m1);
					dao.insert(m2);
					daoCuenta.update(cuentaOrigen);
					daoCuenta.update(cuentaDestino);
					r.setMensaje("OK" + " - Saldo Cuenta Origen: " + cuentaOrigen.getSaldo() + " Saldo Cuenta Destino: " + cuentaDestino.getSaldo());
				} else {
					r.setMensaje("No dispone de suficiente saldo para realizar la transferencia");
				} 
				
			} else {
				r.setCodigo(11);
				r.setMensaje("El numero de cuenta esta mal escrito o no existe");
			}
			
			
			
		} catch (Exception e) {
			r.setCodigo(11);
			r.setMensaje("El numero de cuenta esta mal escrito o no existe");
		}
		
		
		return r;
	}

	public List<Movimiento> getMovimientos() {
		System.out.println(movimientos);
		//daoCuenta.getCuentas();
		return dao.getMovimientos();
	}

	public List<Movimiento> getMovimientosPorId(int filtro) {
		try {
			Cuenta cuenta = daoCuenta.read(filtro);
			return cuenta.getMovimientos();
		} catch (Exception e) {
			return null;
		}
	}
	
}
