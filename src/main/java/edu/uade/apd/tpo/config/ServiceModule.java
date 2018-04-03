package edu.uade.apd.tpo.config;

import com.google.inject.AbstractModule;
import edu.uade.apd.tpo.service.UsuarioService;
import edu.uade.apd.tpo.service.impl.UsuarioServiceImpl;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UsuarioService.class).to(UsuarioServiceImpl.class);
    }
}
