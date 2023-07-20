package com.tuempresa.bdh2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tuempresa.bdh2.model.Empresa;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "*")
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    // Aquí puedes agregar métodos de consulta personalizados si es necesario
	@Query("SELECT e FROM Empresa e ORDER BY e.idEmpresa DESC")
	List<Empresa> findUltimas3Empresas();
}
