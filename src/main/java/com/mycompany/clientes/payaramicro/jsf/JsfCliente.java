/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientes.payaramicro.jsf;

import com.mycompany.clientes.payaramicro.domain.Cidade;
import com.mycompany.clientes.payaramicro.domain.Cliente;
import com.mycompany.clientes.payaramicro.domain.dto.ClienteCidadeDTO;
import com.mycompany.clientes.payaramicro.service.ClienteService;
import com.mycompany.clientes.payaramicro.thirdPartyAPIs.CidadeAPI;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author andre
 */
@Named(value = "jsfCliente")
@RequestScoped
public class JsfCliente {
    
    @Inject
    private CidadeAPI cidadeAPI;
    
    @Inject
    private ClienteService clienteService;
    
    public JsfCliente() {
        setNome("");
        setCidadeId(0l);
    }
    
    private Long id;
    private String nome;
    private Long cidadeId;
    private Cidade cidade;
    private List<Cidade> cidades = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getCidades() {
        return cidadeAPI.listarCidades();
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
    
    public void addCliente() {
        Cliente cliente = new Cliente(getNome(), getCidadeId());
        
        clienteService.cadastrarNovoCliente(cliente);
    }
    
    public List<ClienteCidadeDTO> listarTodos() {
        return clienteService.listarClientes();
    }
    
}
