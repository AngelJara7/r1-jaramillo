package com.example.reto1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cuenta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	public String nombreCliente;
    public String numeroCuenta;
    public String saldo;
    public String estadoCuenta;

	public Cuenta(){
	}

	public Cuenta(String nombreCliente, String numeroCuenta, String saldo, String estadoCuenta) {
		this.nombreCliente = nombreCliente;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.estadoCuenta = estadoCuenta;
	}
}
