package ca.psvoltaire.repository;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.CustomObjectsApi;
import io.kubernetes.client.util.Config;
import jakarta.inject.Singleton;

@Singleton
public class KubernetesClientFactory {
    private final ApiClient client;


    public KubernetesClientFactory() {
        try {
            this.client = Config.fromCluster();

        } catch (Exception e) {
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
