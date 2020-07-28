/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientes.payaramicro.rest;

import com.mycompany.clientes.payaramicro.domain.Cidade;
import com.mycompany.clientes.payaramicro.thirdPartyAPIs.CidadeAPI;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author andre
 */
@Path("cidade")
@RequestScoped
public class CidadeRest {
    
    @Inject
    private CidadeAPI cidadeAPI;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cidade> getListarClientes() {
        return cidadeAPI.listarCidades();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cidade getBuscarCidade(@PathParam("id") int id) {
        return cidadeAPI.buscarCidadeId(id);
    }
    
    
}
