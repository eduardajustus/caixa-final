package com.info.caixa.controller;

import com.info.caixa.model.Extrato;
import com.info.caixa.model.Movimento;
import com.info.caixa.model.Saldo;
import com.info.caixa.repository.MovimentoRepository;
import com.info.caixa.repository.SaldoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping({"/caixa"})
public class CaixaController {

    @Autowired
    private MovimentoRepository movimentoRepository;
    @Autowired
    private SaldoRepository saldoRepository;
    List<Movimento> listaMovimento;
    
    //mostra tela inicial
    @GetMapping("/")
    public String telaInicial(Model model) {
        Movimento movimento = new Movimento();
        model.addAttribute("movimento", movimento);
        return "paginas/form-caixa";
    }
    
    // fazer um movimento que vai ser mandado para extrato
     @PostMapping("/salvar")
    public String salvaMovimento(@ModelAttribute("movimento") Movimento movimento) {
        Saldo saldo=new Saldo();
        movimentoRepository.save(movimento);
        
       Saldo saldoAnterior,saldoVelho=new Saldo();

               saldoAnterior = saldoRepository.findByData(movimento.getData()).orElse(null);
        if(saldoAnterior==null){
            Saldo saldoNovo=new Saldo();
             List<Saldo> listaSaldo= saldoRepository.findSaldoPrevious(movimento.getData());
             if(!listaSaldo.isEmpty()){
                saldoVelho=listaSaldo.getLast();
             }
             saldoNovo.setValor(saldoVelho.getValor());
            saldoNovo.setData(movimento.getData());
            saldoRepository.save(saldoNovo);
        }    
        
        // Verifica o tipo e ajusta o valor
        if ("1".equals(movimento.getTipo())) {
            saldoRepository.atualizaSaldos(movimento.getValor(), movimento.getData());
        } else  {
            saldoRepository.atualizaSaldos(-movimento.getValor(), movimento.getData());
        }

        movimentoRepository.save(movimento);
        return "redirect:/caixa/";
    }
    
    //saldos das datas selicionadas
    //selecionado as datas tem que pegar os extratos dessas datas 
    //saldo anterior, movimentos e os saldos dos ultimos dias
    
    @PostMapping("/saldos")
    public String listaMovimento()){
        //saldo anterior
         saldoRepository.findSaldoPrevious(movimento.getData());
        
        movimentoSaldoAnterior.setData(saldoAnterior.getData);
        movimentoSaldoAnterior.setDescricao(saldoAnterior.getDescricao);
        movimentoSaldoAnterior.setValor(saldoAnterior.getValor);
        
        //saldo final
         saldoRepository.findSaldoFinsh(movimento.getData());
        movimentoSaldoAnterior.setData(saldoAnterior.getData);
        movimentoSaldoAnterior.setDescricao(saldoAnterior.getDescricao);
        movimentoSaldoAnterior.setValor(saldoAnterior.getValor);
         
        //movimentos 
        movimentoSaldoFinal
        listaMovimento.add(movimentoSaldoAnterior);
        listaMovimento.addAll(movimentoRepository.findExtrato(extrato.getDataInicial(), extrato.getDataFinal()));
        listaMovimento.add(movimentoSaldoFinal);
        
        return "paginas/lista-caixa";
    }     
    
    @GetMapping("/extrato") //mostrar a tela extrato
    public String Extrato(@ModelAttribute("extrato")Extrato extrato,Model model){
        extrato = new Extrato();
        model.addAttribute("extrato",extrato);
        return "paginas/form-extrato";
    }
    
    //volta para a tela movimento
   @GetMapping("/voltar")
    public String voltar() {
        return "redirect:/movimento/";
    }
}
