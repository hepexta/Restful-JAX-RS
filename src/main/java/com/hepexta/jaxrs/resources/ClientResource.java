package com.hepexta.jaxrs.resources;

import com.google.gson.Gson;
import com.hepexta.jaxrs.bank.model.Client;
import com.hepexta.jaxrs.bank.repository.cache.ClientRepositoryCache;
import com.hepexta.jaxrs.util.AppConstants;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path(AppConstants.PATH_CLIENT)
public class ClientResource {

    private Gson mapper = new Gson();
    private ClientRepositoryCache clientRepository = ClientRepositoryCache.getINSTANCE();

    @GET
    @Path(AppConstants.PATH_LIST)
    @Produces(AppConstants.JSON_FORMAT)
    public String getClients() {
        return mapper.toJson(clientRepository.getList());
    }

    @GET
    @Path(AppConstants.PATH_FIND_BY_ID)
    @Produces(AppConstants.JSON_FORMAT)
    public String getClientById(@QueryParam("id") String id) {
        return mapper.toJson(clientRepository.findById(id));
    }

    @DELETE
    @Path(AppConstants.PATH_DELETE)
    @Produces(AppConstants.JSON_FORMAT)
    public String deleteClient(@QueryParam("id") String clientId) {
        return clientRepository.delete(clientId) ? "Deleted" : "Not Deleted";
    }

    @PUT
    @Path(AppConstants.PATH_INSERT)
    @Produces(AppConstants.JSON_FORMAT)
    public String insertClient(@QueryParam("name") String name) {
        return clientRepository.insert(new Client(name));
    }

    @POST
    @Path(AppConstants.PATH_MODIFY)
    @Produces(AppConstants.JSON_FORMAT)
    public String modifyClient(@QueryParam("id") String id, @QueryParam("newName") String newName) {
        return clientRepository.modify(new Client(id, newName)) ? "Modified" : "Not Modified";
    }
}
