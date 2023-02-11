package com.example.chart.service;

import com.example.chart.model.Venda;
import com.example.chart.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;

    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }



//    public Optional<Venda> save(Venda venda) {
//        BigDecimal quantidade = BigDecimal.valueOf(venda.getQuantidade());
//        BigDecimal precoUnit = BigDecimal.valueOf(venda.getPrecoUnit());
//        BigDecimal subtotal = quantidade.multiply(precoUnit);
//
//        venda.setTotal(subtotal.doubleValue());
//        return Optional.ofNullable(vendaRepository.save(venda));
//    }

    public Venda salvarVenda(Venda venda) {
        Double subtotal = venda.getQuantidade() * venda.getPrecoUnit();
        venda.setTotal(subtotal);

        return vendaRepository.save(venda);
    }



    public List<Object[]> findQuantidadeVendasPorDataVendaList() {
        return vendaRepository.findQuantidadeVendasPorDataVenda();
    }


}
