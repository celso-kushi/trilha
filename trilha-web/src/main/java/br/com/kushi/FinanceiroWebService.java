/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kushi;

import br.com.kushi.financeiro.ejb.FinanceiroBeanLocal;
import br.com.kushi.financeiro.model.Lancamento;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Hiro
 */
@Path("financeiro")
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
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() throws Exception {
        //TODO return proper representation object
        return "";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lancamento> obtemLancamento() throws Exception {
        return financeiroBean.obtemLancamentos();
    }

    /**
     * PUT method for updating or creating an instance of FinanceiroWebService
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
