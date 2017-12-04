/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kushi;

import br.com.kushi.financeiro.ejb.FinanceiroBeanLocal;
import br.com.kushi.financeiro.model.Filtro;
import br.com.kushi.financeiro.model.Lancamento;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Hiro
 */
@Path("/financeiro")
@RequestScoped
public class FinanceiroWebService {

    @EJB
    FinanceiroBeanLocal financeiroBean;
    
    @Context
    private UriInfo context;
    
    /**
     * Creates a new instance of FinanceiroWebService
     */
    public FinanceiroWebService() {
    }

    @GET
    @Path("/obterLancamentos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterLancamentos() throws Exception {
        
        try {
            return Response.ok(financeiroBean.obterLancamentos()).build();
        } catch (Exception e) {
            Logger.getLogger(FinanceiroWebService.class.getName()).log(Level.SEVERE, "Falha obterLancamentos", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirLancamento(Lancamento lancamento) {
        
        try {
            return Response.ok(financeiroBean.inserir(lancamento)).build();
        } catch (Exception e) {
            Logger.getLogger(FinanceiroWebService.class.getName()).log(Level.SEVERE, "Falha inserirLancamento", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterarLancamento(Lancamento lancamento) {
        
        try {
            return Response.ok(financeiroBean.alterar(lancamento)).build();
        } catch (Exception e) {
            Logger.getLogger(FinanceiroWebService.class.getName()).log(Level.SEVERE, "Falha alterarLancamento", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirLancamento(@PathParam("id") int id) {
        
        try {
            return Response.ok(financeiroBean.excluir(id)).build();
        } catch (Exception e) {
            Logger.getLogger(FinanceiroWebService.class.getName()).log(Level.SEVERE, "Falha excluirLancamento", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/obterLancamentosPorData")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterLancamentosPorData(Filtro filtro) throws Exception {
        
        try {
            return Response.ok(financeiroBean.obterLancamentosPorData(filtro)).build();
        } catch (Exception e) {
            Logger.getLogger(FinanceiroWebService.class.getName()).log(Level.SEVERE, "Falha obterLancamentos", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
