/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientes.payaramicro.rest;

import com.mycompany.clientes.payaramicro.domain.Cliente;
import com.mycompany.clientes.payaramicro.service.ClienteService;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author andre
 */
@Path("cliente")
@RequestScoped
public class ClienteRest {

    public ClienteRest() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getClientes() {
        List<Cliente> cliente = new ArrayList<>();
        
        ClienteService clienteService = new ClienteService();
        
        cliente = clienteService.listaClientes();
        
        /*cliente.add(new Cliente("Cliente 1"));
        cliente.add(new Cliente("Cliente 2"));
        cliente.add(new Cliente("Cliente 3"));
        cliente.add(new Cliente("Cliente 4"));*/
        
        return cliente;
    }
    
}
