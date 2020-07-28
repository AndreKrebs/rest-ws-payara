/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientes.payaramicro.thirdPartyAPIs;

import com.mycompany.clientes.payaramicro.domain.Cidade;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author andre
 */
@Stateless
public class CidadeAPI {
    // Os endpoints rest podem ser verificados em https://github.com/alerario/mavenTest/blob/master/src/main/java/br/rest/CidadeRest.java
    
    private static Client clientAPI;
    private static WebTarget baseTarget;
    
    public CidadeAPI() {
        clientAPI = ClientBuilder.newClient();
        baseTarget = clientAPI.target("http://maventest.herokuapp.com/mavenTest-1.0-SNAPSHOT/webresources");
    }
    
    public List<Cidade> listarCidades() {
        return baseTarget.path("cidade")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Cidade>>(){});
    }
    
    public Cidade buscarCidadeId(int id) {
        return baseTarget.path("cidade/"+String.valueOf(id))
                .request(MediaType.APPLICATION_JSON)
                .get(Cidade.class);
    }
}
