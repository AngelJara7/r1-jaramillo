package com.example.reto1.restservice;

import java.util.Optional;

import com.example.reto1.Cuenta;
import com.example.reto1.CuentaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
/*import org.springframework.web.bind.annotation.PutMapping;*/
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
	public Iterable<Cuenta> Obtener() throws JsonProcessingException{
        return cuentaRepository.findAll();
    }
    
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
	public Optional<Cuenta> ObtenerCuenta(@PathVariable() Long id){
        return cuentaRepository.findById(id);
    }
    
    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
	public Cuenta Crear(@RequestBody Cuenta cuenta){
        return cuentaRepository.save(cuenta);
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.PUT)
	public Cuenta Actualizar(@RequestBody() Cuenta cuenta){        
        return cuentaRepository.save(cuenta);
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
	public Cuenta Eliminar(@PathVariable() Long id){
        String estado = "desactivada";
        Cuenta cuenta = cuentaRepository.findById(id).get();
        cuenta.estadoCuenta = estado;
        return cuentaRepository.save(cuenta);
    }
}
