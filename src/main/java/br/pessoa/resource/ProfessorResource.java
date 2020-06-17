package br.pessoa.resource;

import br.pessoa.api.Professor;
import io.dropwizard.jersey.params.IntParam;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("professores")
@Produces(MediaType.APPLICATION_JSON)
public class ProfessorResource {
    List<Professor> dao;
    int contador;

    public ProfessorResource() {
        this.contador = 0;
        this.dao = new ArrayList<>();

        for(int i=0; i<10; i++) {
            this.dao.add(new Professor(contador++, i, "Professor " + i));
        }
    }

    @POST
    public Professor create(Professor p) {
        p.setId(contador++);
        this.dao.add(p);
        return p;
    }

    @GET
    public List<Professor> readAll() {
        return this.dao;
    }

    @PUT
    @Path("/{id}")
    public Professor update(@PathParam("id") long id, Professor p) {
        for (int i=0; i<this.dao.size(); i++) {
            if (this.dao.get(i).getId() == id) {
                this.dao.get(i).setNome(p.getNome());
                this.dao.get(i).setMatricula(p.getMatricula());
                return this.dao.get(i);
            }
        }
        throw new NotFoundException();
    }

    @DELETE
    @Path("/{id}")
    public Professor update(@PathParam("id") long id) {
        for (int i=0; i<this.dao.size(); i++) {
            if (this.dao.get(i).getId() == id) {
                Professor aux = this.dao.get(i);
                this.dao.remove(i);
                return aux;
            }
        }
        throw new NotFoundException();
    }

}
