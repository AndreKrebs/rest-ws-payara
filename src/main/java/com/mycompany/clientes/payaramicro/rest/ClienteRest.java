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
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author andre
 */
@Path("cliente")
@RequestScoped
public class ClienteRest {

    @Inject
    private ClienteService clienteService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Long postCadastrarCliente(Cliente cliente) {
        return clienteService.cadastrarNovoCliente(cliente);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente putAtualizarCliente(Cliente cliente) {
        return clienteService.atualizaCliente(cliente);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getListarClientes() {
        return clienteService.listaClientes();
    }
    
    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente getBuscarClienteCodigo(@Valid @PathParam("codigo") Long id) {
        return clienteService.buscaCliente(id);
    }
    
    @DELETE
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteClienteCodigo(@PathParam("codigo") Long id) {
        clienteService.deletaCliente(id);
    }
}
