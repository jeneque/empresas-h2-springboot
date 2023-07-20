package com.tuempresa.bdh2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.tuempresa.bdh2.model.Empresa;
import com.tuempresa.bdh2.repository.EmpresaRepository;
import java.util.List;

@RestController
@RequestMapping("/empresas")
@CrossOrigin(origins = "*") 
public class EmpresaController {

    private final EmpresaRepository empresaRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmpresaController.class);

    @Autowired
    public EmpresaController(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @GetMapping("/ultimas-empresas")
    public ResponseEntity<List<Empresa>> obtenerUltimasEmpresas() {
        List<Empresa> ultimasEmpresas = empresaRepository.findUltimas3Empresas();
        return ResponseEntity.ok(ultimasEmpresas);
    }

    @Transactional
    @PostMapping("/guardar")
    public ResponseEntity<Empresa> guardarEmpresa(@RequestBody Empresa empresa) {
        logger.info("Recibiendo datos para guardar: {}", empresa);
        try {
            Empresa nuevaEmpresa = empresaRepository.save(empresa);
            logger.info("Empresa guardada correctamente: {}", nuevaEmpresa);
            return new ResponseEntity<>(nuevaEmpresa, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error al guardar la empresa: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}