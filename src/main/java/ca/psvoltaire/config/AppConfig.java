package ca.psvoltaire.config;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("app")
public class AppConfig {
    private boolean islocal;

    public boolean isIslocal() {
        return islocal;
    }

    public void setIslocal(boolean islocal) {
        this.islocal = islocal;
    }
}
