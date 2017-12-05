/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kushi;

import br.com.kushi.financeiro.ejb.FinanceiroBeanLocal;
import br.com.kushi.financeiro.model.Filtro;
import br.com.kushi.financeiro.model.Lancamento;
import br.com.kushi.model.ResultWeb;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.ws.rs.QueryParam;
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
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ResultWeb(e.getMessage())).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirLancamento(Lancamento lancamento) {
        
        try {
            financeiroBean.inserir(lancamento);
            return Response.ok(new ResultWeb("Sucesso")).build();
        } catch (Exception e) {
            Logger.getLogger(FinanceiroWebService.class.getName()).log(Level.SEVERE, "Falha inserirLancamento", e.getMessage());
            return Response. status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ResultWeb(e.getMessage())).build();
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
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ResultWeb(e.getMessage())).build();
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
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ResultWeb(e.getMessage())).build();
        }
    }
    
    @GET
    @Path("/obterLancamentosPorData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterLancamentosPorData(@QueryParam("dataInicial") String dataInicial, @QueryParam("dataFinal") String dataFinal) throws Exception {
        
        try {
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            
            Filtro filtro = new Filtro();
            filtro.setDataInicial(sdf.parse(dataInicial));
            filtro.setDataFinal(sdf.parse(dataFinal));
            
            return Response.ok(financeiroBean.obterLancamentosPorData(filtro)).build();
        } catch (Exception e) {
            Logger.getLogger(FinanceiroWebService.class.getName()).log(Level.SEVERE, "Falha obterLancamentos", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ResultWeb(e.getMessage())).build();
        }
    }
    
    @GET
    @Path("/obterLancamentosPorNome")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterLancamentosPorNome(@QueryParam("nome") String nome) throws Exception {
        
        try {
            
            Filtro filtro = new Filtro();
            filtro.setNome(nome);
            
            return Response.ok(financeiroBean.obterLancamentosPorNome(filtro)).build();
        } catch (Exception e) {
            Logger.getLogger(FinanceiroWebService.class.getName()).log(Level.SEVERE, "Falha obterLancamentos", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ResultWeb(e.getMessage())).build();
        }
    }
    
    @GET
    @Path("/obterLancamentosPorTipo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterLancamentosPorTipo(@QueryParam("tipo") Integer tipo) throws Exception {
        
        try {
            
            Filtro filtro = new Filtro();
            filtro.setTipo(tipo);
            
            return Response.ok(financeiroBean.obterLancamentosPorTipo(filtro)).build();
        } catch (Exception e) {
            Logger.getLogger(FinanceiroWebService.class.getName()).log(Level.SEVERE, "Falha obterLancamentos", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ResultWeb(e.getMessage())).build();
        }
    }
}
