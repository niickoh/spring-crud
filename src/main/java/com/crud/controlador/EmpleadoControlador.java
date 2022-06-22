package com.crud.controlador;

import com.crud.excepciones.ResourceNotFoundException;
import com.crud.modelo.Empleado;
import com.crud.repositorio.EmpleadoRepositorio;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoControlador {
    @Autowired
    private EmpleadoRepositorio repositorio;

    @GetMapping("/empleados")
    public List<Empleado> listaEmpleados() {
        return repositorio.findAll();
    }

    @PostMapping("/empleados")
    public ResponseEntity<String>  guardarEmpleado(@RequestBody Empleado emp) {
        repositorio.save(emp);
        return new ResponseEntity<>("Usuario Registrado", HttpStatus.OK);
    }

    // Buscar empleado por ID
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleado(@PathVariable Long id) {
        Empleado emp = repositorio.findById(id).orElseThrow(() -> new RuntimeException("No existe empleado con ID: " + id));
        return  ResponseEntity.ok(emp);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleado(@PathVariable Long id, @RequestBody Empleado detallesEmpleados) {
        Empleado emp = repositorio.findById(id).orElseThrow(() -> new RuntimeException("No existe empleado con ID: " + id));
        emp.setNombre(detallesEmpleados.getNombre());
        emp.setApellido(detallesEmpleados.getApellido());
        emp.setEmail(detallesEmpleados.getEmail());

        Empleado empActualizado = repositorio.save(emp);
        return  ResponseEntity.ok(empActualizado);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Long id) {
        Empleado emp = repositorio.findById(id).orElseThrow(() -> new RuntimeException("No existe empleado con ID: " + id));

        repositorio.delete(emp);
        Map<String, Boolean> resp = new HashMap<>();
        resp.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(resp);

    }
}
