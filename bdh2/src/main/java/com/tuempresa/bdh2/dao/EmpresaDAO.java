package com.tuempresa.bdh2.dao;

import org.springframework.stereotype.Repository;
import com.tuempresa.bdh2.model.Empresa;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmpresaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void guardarEmpresa(Empresa empresa) {
        entityManager.persist(empresa);
    }

    public List<Empresa> obtenerUltimas3Empresas() {
        return entityManager.createQuery("SELECT e FROM Empresa e ORDER BY e.idEmpresa DESC", Empresa.class)
                            .setMaxResults(3)
                            .getResultList();
    }
}
