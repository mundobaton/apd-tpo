package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.exception.BusinessException;
import org.junit.Before;
import org.junit.Test;

public class SistemaAdministracionTest {

    private SistemaAdministracion sistemaAdministracion;

    @Before
    public void setup() {
        this.sistemaAdministracion = SistemaAdministracion.getInstance();
    }

    @Test(expected = BusinessException.class)
    public void testLoginUsuarioNoExiste() throws BusinessException {
        sistemaAdministracion.login("fulano de tal", "asd");
    }

    @Test(expected = BusinessException.class)
    public void testLoginPasswordIncorrecto() throws BusinessException {
        sistemaAdministracion.login("cliente@scratch.com", "pwd2");
    }

    @Test
    public void testLoginOk() throws BusinessException {
        sistemaAdministracion.login("cliente@scratch.com", "pwd");
    }


}
