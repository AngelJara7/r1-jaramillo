package com.example.reto1.restservice;

import com.example.reto1.Cuenta;
import com.example.reto1.CuentaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;

	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public ResponseEntity<Object> ObtenerCuentas() {
        List<Cuenta> cuentas = (List<Cuenta>) cuentaRepository.findAll();
        try {
            return new ResponseEntity<>(cuentas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No existen registros", HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> ObtenerCuenta(@PathVariable() Long id){
        try {
            Cuenta cuenta = cuentaRepository.findById(id).get();
            return new ResponseEntity<>(cuenta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("La cuenta no existe", HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
	public ResponseEntity<Object> Crear(@RequestBody Cuenta cuenta){
        try {
            Cuenta nuevaCuenta = cuentaRepository.save(cuenta);
            return new ResponseEntity<>(nuevaCuenta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Algo salio mal, no se pudo crear la cuenta", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> Actualizar(@RequestBody() Cuenta cuenta, @PathVariable() Long id){
        try {
            Cuenta cuentaActual = cuentaRepository.findById(id).get();
            cuentaActual.saldo = cuenta.saldo;
            Cuenta cuentaActualizada = cuentaRepository.save(cuentaActual);
            return new ResponseEntity<>(cuentaActualizada, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("La cuenta no existe", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Eliminar(@PathVariable() Long id){
        try {
            final String estado = "desactivada";
            Cuenta cuenta = cuentaRepository.findById(id).get();
            cuenta.estadoCuenta = estado;
            Cuenta cuentaEliminada = cuentaRepository.save(cuenta);
            return new ResponseEntity<>(cuentaEliminada, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("La cuenta no existe", HttpStatus.NOT_FOUND);
        }
    }
}
