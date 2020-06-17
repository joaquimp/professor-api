package br.pessoa;

import br.pessoa.resource.ProfessorResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

/**
 * Hello world!
 *
 */
public class App extends Application<Configuration> {
    public static void main( String[] args ) throws Exception {
        new App().run(new String[]{"server"});
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        ProfessorResource pr = new ProfessorResource();
        environment.jersey().register(pr);

        environment.jersey().setUrlPattern("/api/*");
    }
}
