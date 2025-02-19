package ca.psvoltaire.Clients;

import ca.psvoltaire.config.AppConfig;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.CustomObjectsApi;
import io.kubernetes.client.util.ClientBuilder;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@Singleton
@Requires(property = "islocal", value = "true")

public class KubernetesClientFactory {
    private final ApiClient client;
    public KubernetesClientFactory(AppConfig appConfig)  {
        boolean islocal=appConfig.isIslocal();
        log.info("islocal: {}", islocal);
        try {
            if (islocal) {               this.client = ClientBuilder.defaultClient();
            }
            else {            this.client = ClientBuilder.cluster().build();
            }
            Configuration.setDefaultApiClient(client);
        } catch ( IOException e) {
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
