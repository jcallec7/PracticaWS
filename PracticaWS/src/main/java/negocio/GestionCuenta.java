package negocio;

import javax.ejb.Stateless;
import javax.inject.Inject;

import datos.CuentaDAO;
import modelo.Cuenta;
import modelo.Movimiento;

@Stateless
public class GestionCuenta {
	
	@Inject
	private CuentaDAO dao;
	
	public void insertCuenta(int id, String nombre, String apellido, Double saldo) {
		
		Cuenta cuenta = new Cuenta();
		cuenta.setId(id);
		cuenta.setNombre(nombre);
		cuenta.setApellido(apellido);
		cuenta.setSaldo(saldo);
		dao.insert(cuenta);
		
	}
	

	

}

