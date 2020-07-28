/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientes.payaramicro.service;

import com.mycompany.clientes.payaramicro.domain.Cliente;
import com.mycompany.clientes.payaramicro.domain.dto.ClienteCidadeDTO;
import com.mycompany.clientes.payaramicro.exception.IdIsNotNullException;
import com.mycompany.clientes.payaramicro.thirdPartyAPIs.CidadeAPI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author andre
 */
@Stateless
public class ClienteService {

    @Inject
    private CidadeAPI cidadeAPI;
    
    protected final SessionFactory sessionFactory;
    
    public ClienteService() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public List<ClienteCidadeDTO> listarClientes() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Cliente> clientes = session.createQuery("from Cliente order by nome asc").list();
        session.getTransaction().commit();
        session.close();
        
        return clientes.stream()
            .map(cliente -> converteClienteEmClienteCidadeDto(
                    cliente, 
                    cliente.getCidadeId().intValue()))
            .collect(Collectors.toList());
    }

    public Long cadastrarNovoCliente(Cliente cliente) {
        if (cliente.getId() != null) {
            throw new IdIsNotNullException("Cadastro de Cliente não deve conter ID");
        }
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Long id = (Long) session.save(cliente);
        session.getTransaction().commit();
        session.close();

        return id;
    }

    public ClienteCidadeDTO atualizarCliente(Cliente cliente) {
        if (cliente.getId() == null) {
            throw new BadRequestException("O registro de cliente precisa de um ID");
        }
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(cliente);
        session.getTransaction().commit();
        session.close();
        
        ClienteCidadeDTO clienteCidade = converteClienteEmClienteCidadeDto(
                cliente, 
                cliente.getCidadeId().intValue()
            );

        return clienteCidade;
    }
    
    private ClienteCidadeDTO converteClienteEmClienteCidadeDto(Cliente cliente, int cidadeId) {
        return new ClienteCidadeDTO(
                cliente, 
                cidadeAPI.buscarCidadeId(cidadeId)
            );
    }

    public Cliente buscarCliente(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Optional<Cliente> cliente = session.createQuery("from Cliente where id = :id").setParameter("id", id).uniqueResultOptional();
        session.getTransaction().commit();
        session.close();

        if (!cliente.isPresent())
            throw new NotFoundException("Cliente não encontrado");
        else
            return cliente.get();
    }

    public void deletarCliente(Long id) {
        if (!this.verificaSeClienteExistePeloId(id)) {
            throw new BadRequestException("Cliente informado não existe");
        }
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete from Cliente where id = :id").setParameter("id", id).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    
    private boolean verificaSeClienteExistePeloId(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Optional<Cliente> cliente = session.createQuery("from Cliente where id = :id").setParameter("id", id).uniqueResultOptional();
        session.getTransaction().commit();
        session.close();
        
        return cliente.isPresent();
    }
}
