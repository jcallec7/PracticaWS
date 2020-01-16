package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Movimiento implements Serializable {

	private static final long serialVersionUID = 8494633931799962941L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ws_mov_id")
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ws_mov_fecha")
	private Date fecha;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ws_ori_id")
	private Cuenta origen;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ws_dest_id")
	private Cuenta destino;
	
	@Column(name = "ws_mov_monto")
	private double monto;
	
	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cuenta getOrigen() {
		return origen;
	}

	public void setOrigen(Cuenta origen) {
		this.origen = origen;
	}

	public Cuenta getDestino() {
		return destino;
	}

	public void setDestino(Cuenta destino) {
		this.destino = destino;
	}

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", fecha=" + fecha + ", origen=" + origen + ", destino=" + destino + "]";
	}
	
	
}
