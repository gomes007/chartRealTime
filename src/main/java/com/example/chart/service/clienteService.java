package com.example.chart.service;

import com.example.chart.model.Cliente;
import com.example.chart.repository.ClienteRepository;
import com.example.chart.repository.VendaRepository;
import org.springframework.stereotype.Service;

@Service
public class clienteService {

    private final ClienteRepository clienteRepository;

    public clienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }



    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }






}
