
package com.info.caixa.repository;

import com.info.caixa.model.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Date;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovimentoRepository extends JpaRepository<Movimento, Integer> {

    List<Movimento> findByDataOrderByDataAsc(Date data);
    List<Movimento> findByOrderByDataAsc();
    @Query("select m from Movimento m where m.data between :di and :df order by m.data")
    List<Movimento> findExtrato(@Param("di") Date dataInicial, @Param("df") Date dataFinal);
}
