package com.iestp.sigefar.dao;

import com.iestp.sigefar.modelo.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    List<Medicamento> findAllByOrderByNombMedAsc();
}
