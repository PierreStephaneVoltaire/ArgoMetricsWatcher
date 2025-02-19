package ca.psvoltaire.Clients;

import ca.psvoltaire.config.AppConfig;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.CustomObjectsApi;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Singleton

public class KubernetesClientFactory {
    private final ApiClient client;

    public KubernetesClientFactory(AppConfig appConfig) {
        boolean islocal = appConfig.isIslocal();
        log.info("islocal: {}", islocal);
        try {
            if (islocal) {
                this.client = ClientBuilder.defaultClient();
            } else {
                final String TOKEN_PATH = "/var/run/secrets/kubernetes.io/serviceaccount/token";
                final String K8S_API_SERVER = "https://kubernetes.default.svc";
                String token = new String(Files.readAllBytes(Paths.get(TOKEN_PATH)));
                this.client = Config.fromToken(K8S_API_SERVER, token, false);
            }
            Configuration.setDefaultApiClient(client);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Failed to initialize Kubernetes client", e);
        }
    }


    @Singleton
    public CoreV1Api coreV1Api() {
        return new CoreV1Api(client);
    }

    @Singleton
    public CustomObjectsApi customObjectsApi() {
        return new CustomObjectsApi(client);
    }
}
