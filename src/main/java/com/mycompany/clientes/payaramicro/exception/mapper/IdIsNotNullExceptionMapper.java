/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientes.payaramicro.exception.mapper;

import com.mycompany.clientes.payaramicro.exception.IdIsNotNullException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author andre
 */
public class IdIsNotNullExceptionMapper implements ExceptionMapper<IdIsNotNullException>{

    @Override
    public Response toResponse(IdIsNotNullException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .type("text/plain")
                .build();
    }
    
}
