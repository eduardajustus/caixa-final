/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info.caixa.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author info20242
 */
public class Extrato {
    private Date dataInicial=new Date();
    private Date dataFinal=new Date();

    /**
     * @return the dataInicial
     */
    public Date getDataInicial() {
        return dataInicial;
    }

    /**
     * @param dataInicial the dataInicial to set
     */
    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }
     public String getDataInicialFormatada(){
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataInicial);
    }
    public void setDataInicialFormatada(String data){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try{
            this.dataInicial= sdf.parse(data);
        }catch(Exception e){
            System.out.println("Erro de data");
        }
    }  
     public String getDataFinalFormatada(){
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataFinal);
    }
     public void setDataFinalFormatada(String data){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try{
            this.dataFinal= sdf.parse(data);
        }catch(Exception e){
            System.out.println("Erro de data");
        }
    }  
    /**
     * @return the dataFinal
     */
    public Date getDataFinal() {
        return dataFinal;
    }

    /**
     * @param dataFinal the dataFinal to set
     */
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
}
