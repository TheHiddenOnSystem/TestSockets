package org.example.store;

import org.junit.Assert;
import org.junit.Test;

public class TestAdministrationKeyStore {

    @Test
    public void basicTestAdministrationKeyStore(){
        AdministrationKeyStore administrationKeyStore=new AdministrationKeyStore("pruebaCertificado");
        Assert.assertNotNull(administrationKeyStore.getKeyStore());
    }
}
