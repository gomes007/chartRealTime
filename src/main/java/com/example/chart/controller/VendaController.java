package com.example.chart.controller;

import com.example.chart.model.Venda;
import com.example.chart.repository.VendaRepository;
import com.example.chart.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RestController
@RequestMapping("/venda")
@CrossOrigin(origins = "*")
public class VendaController {

    private final VendaService vendaService;
    private final VendaRepository vendaRepository;

    public VendaController(VendaService vendaService, VendaRepository vendaRepository) {
        this.vendaService = vendaService;
        this.vendaRepository = vendaRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Venda register(@RequestBody final Venda venda){
        return this.vendaService.salvarVenda(venda);
    }



    @GetMapping("/events")
    public SseEmitter handle() {
        SseEmitter emitter = new SseEmitter();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                List<Object[]> sales = vendaRepository.findTotalVendasByData();
                sales.forEach(sale -> {
                    try {
                        SseEmitter.SseEventBuilder event = SseEmitter.event().data(
                                "{\"date\": \"" + sale[0] + "\", \"quantity\": " + sale[1] + "}"
                        );
                        emitter.send(event);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                emitter.complete();
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }




//    @GetMapping("/events")
//    public SseEmitter handle() {
//        SseEmitter emitter = new SseEmitter();
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.execute(() -> {
//            try {
//                List<Object[]> sales = vendaRepository.findTotalVendasByData();
//                for (Object[] sale : sales) {
//                    SseEmitter.SseEventBuilder event = SseEmitter.event().data(sale[0] + ": " + sale[1]);
//                    emitter.send(event);
//                }
//                emitter.complete();
//            } catch (Exception ex) {
//                emitter.completeWithError(ex);
//            }
//        });
//        return emitter;
//    }


//    @GetMapping("/events")
//    public SseEmitter handle() {
//        SseEmitter emitter = new SseEmitter();
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.execute(() -> {
//            try {
//                List<Venda> sales = vendaRepository.findAll();
//                for (Venda venda : sales) {
//                    SseEmitter.SseEventBuilder event = SseEmitter.event().data(venda.getDataVenda() + ": " + venda.getQuantidade());
//                    emitter.send(event);
//                }
//                emitter.complete();
//            } catch (Exception ex) {
//                emitter.completeWithError(ex);
//            }
//        });
//        return emitter;
//    }



    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> getAll() {
        return this.vendaService.findQuantidadeVendasPorDataVendaList();
    }
}
