package edu.uade.apd.tpo.config;

import com.google.inject.AbstractModule;
import edu.uade.apd.tpo.service.ClienteService;
import edu.uade.apd.tpo.service.impl.ClienteServiceImpl;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ClienteService.class).to(ClienteServiceImpl.class);
    }
}
