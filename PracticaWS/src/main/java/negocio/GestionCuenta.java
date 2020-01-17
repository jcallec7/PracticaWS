package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import datos.CuentaDAO;
import datos.MovimientoDAO;
import modelo.Cuenta;
import modelo.Movimiento;
import modelo.Cuenta;

@Stateless
public class GestionCuenta implements GestionCuentaLocal{
	
	@Inject
	private CuentaDAO dao;
	
	@Inject
	private MovimientoDAO daoMovimiento;

	private List<Cuenta> cuentas = new ArrayList<Cuenta>();
	private List<Movimiento> movimientos = new ArrayList<Movimiento>();
	
	public void insertCuenta(int id, String nombre, String apellido, Double saldo) {
		
		Cuenta cuenta = new Cuenta();
		cuenta.setId(id);
		cuenta.setNombre(nombre);
		cuenta.setApellido(apellido);
		cuenta.setSaldo(saldo);
		dao.insert(cuenta);
		
	}
	
	public List<Cuenta> getCuentas() {
		System.out.println(cuentas);
		//daoCuenta.getCuentas();
		cuentas = dao.getCuentas();
		return cuentas;
	}
	
	
	
}

