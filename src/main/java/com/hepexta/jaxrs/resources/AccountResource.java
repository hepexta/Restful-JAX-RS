package com.hepexta.jaxrs.resources;

import com.google.gson.Gson;
import com.hepexta.jaxrs.bank.model.Account;
import com.hepexta.jaxrs.bank.model.Client;
import com.hepexta.jaxrs.bank.repository.cache.AccountRepositoryCache;
import com.hepexta.jaxrs.bank.repository.cache.ClientRepositoryCache;
import com.hepexta.jaxrs.util.AppConstants;

import javax.ws.rs.*;
import java.math.BigDecimal;

@Path(AppConstants.PATH_ACCOUNT)
public class AccountResource {

    private Gson mapper = new Gson();
    private AccountRepositoryCache accountRepository = AccountRepositoryCache.getINSTANCE();
    private ClientRepositoryCache clientRepository = ClientRepositoryCache.getINSTANCE();

    @GET
    @Path(AppConstants.PATH_LIST)
    @Produces(AppConstants.JSON_FORMAT)
    public String getClients() {
        return mapper.toJson(accountRepository.getList());
    }

    @GET
    @Path(AppConstants.PATH_FIND_BY_ID)
    @Produces(AppConstants.JSON_FORMAT)
    public String getClientById(@QueryParam("id") String id) {
        return mapper.toJson(accountRepository.findById(id));
    }

    @DELETE
    @Path(AppConstants.PATH_DELETE)
    @Produces(AppConstants.JSON_FORMAT)
    public String deleteClient(@QueryParam("id") String number) {
        return accountRepository.delete(number) ? "Deleted" : "Not Deleted";
    }

    @PUT
    @Path(AppConstants.PATH_INSERT)
    @Produces(AppConstants.JSON_FORMAT)
    public String insertAccount(@QueryParam("clientId") String clientId, @QueryParam("balance") String balance) {
        Client client = clientRepository.findById(clientId);
        BigDecimal bigDecimal = new BigDecimal(balance);
        return accountRepository.insert(new Account(client, bigDecimal) );
    }

    @POST
    @Path(AppConstants.PATH_DEPOSIT)
    @Produces(AppConstants.JSON_FORMAT)
    public String deposit(@QueryParam("number") String number, @QueryParam("amount") BigDecimal amount) {
        Account account = accountRepository.findById(number);
        account.deposit(amount);
        return "Modified";
    }

    @POST
    @Path(AppConstants.PATH_WITHDRAWAL)
    @Produces(AppConstants.JSON_FORMAT)
    public String withdrawal(@QueryParam("number") String number, @QueryParam("amount") BigDecimal amount) {
        Account account = accountRepository.findById(number);
        account.withdraw(amount);
        return "Modified";
    }
}
