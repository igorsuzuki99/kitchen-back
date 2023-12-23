package com.carcara.oracle.kitchencloud.repository;

import com.carcara.oracle.kitchencloud.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor,Long> {
}
