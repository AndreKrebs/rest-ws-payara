/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientes.payaramicro.service;

import com.mycompany.clientes.payaramicro.domain.Cliente;
import com.mycompany.clientes.payaramicro.exception.IdIsNotNullException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author andre
 */
@Stateless
public class ClienteService {

    protected final SessionFactory sessionFactory;
    
    public ClienteService() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public List<Cliente> listaClientes() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Cliente> clientes = session.createQuery("from Cliente order by nome asc").list();
        session.getTransaction().commit();
        session.close();

        return clientes;
    }

    public Long cadastrarNovoCliente(Cliente novoCliente) {
        if (novoCliente.getId() != null) {
            throw new IdIsNotNullException("Cadastro de Cliente não deve conter ID");
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Long id = (Long) session.save(novoCliente);
        session.getTransaction().commit();
        session.close();

        return id;
    }
}
