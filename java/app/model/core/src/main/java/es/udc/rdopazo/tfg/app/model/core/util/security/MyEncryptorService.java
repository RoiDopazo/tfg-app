package es.udc.rdopazo.tfg.app.model.core.util.security;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Component;

@Component
public class MyEncryptorService {

    private StandardPBEStringEncryptor encryptor = null;

    private static final String PASS = "mySecret";

    private void initialize() {
        if (this.encryptor == null) {
            this.encryptor = new StandardPBEStringEncryptor();
            this.encryptor.setPassword(PASS);
        }
    }

    public String encrypt(String text) {
        this.initialize();
        return this.encryptor.encrypt(text);
    }

    public String decrypt(String text) {
        this.initialize();
        return this.encryptor.decrypt(text);
    }
}
