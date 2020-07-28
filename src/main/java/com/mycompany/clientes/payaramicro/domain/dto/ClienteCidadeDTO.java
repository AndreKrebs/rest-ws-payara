/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientes.payaramicro.domain.dto;

import com.mycompany.clientes.payaramicro.domain.Cidade;
import com.mycompany.clientes.payaramicro.domain.Cliente;
import javax.validation.constraints.NotNull;

/**
 *
 * @author andre
 */
public class ClienteCidadeDTO {
    @NotNull
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private Cidade cidade;
    
    public ClienteCidadeDTO(Cliente cliente, Cidade cidade) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cidade = cidade;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

}
