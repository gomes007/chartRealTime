package com.example.chart.repository;

import com.example.chart.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query("SELECT v.dataVenda, COUNT(v.id) FROM Venda v GROUP BY v.dataVenda")
    List<Object[]> findQuantidadeVendasPorDataVenda();

    @Query("SELECT v.dataVenda, COUNT(v.id) FROM Venda v GROUP BY v.dataVenda")
    List<Venda> findQuantidadeVendasPorDataVenda2();

    @Query(value = "SELECT v.dataVenda, SUM(v.quantidade) FROM Venda v GROUP BY v.dataVenda")
    List<Object[]> findTotalVendasByData();

}
