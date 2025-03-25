
package com.info.caixa.repository;

import com.info.caixa.model.Saldo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface SaldoRepository  extends JpaRepository<Saldo, Integer>{
    Optional<Saldo> findByData(Date data);
    @Query("select s from Saldo s where s.data<:data order by s.data")
    List<Saldo> findSaldoPrevious(@Param("data") Date data);
    @Transactional
    @Modifying
    @Query("update Saldo s set s.valor=s.valor+:vm where s.data>=:data")
    void atualizaSaldos(@Param("vm")double vm,@Param("data") Date data);
}


