package ca.psvoltaire.service;

import ca.psvoltaire.dto.ArgoApplication;
import ca.psvoltaire.dto.ArgoApplicationList;
import ca.psvoltaire.repository.KubernetesClientFactory;
import com.google.gson.Gson;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.CustomObjectsApi;
import io.kubernetes.client.openapi.models.CoreV1EventList;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Slf4j
@Singleton
public class ArgoService {
    private static final Logger logger = LoggerFactory.getLogger(ArgoService.class);
    private final CustomObjectsApi customObjectsApi;
    private final CoreV1Api coreV1Api;
    private final Gson gson;

    public ArgoService(KubernetesClientFactory clientFactory) {
        this.customObjectsApi = clientFactory.customObjectsApi();
        this.coreV1Api = clientFactory.coreV1Api();
        this.gson = new Gson();

    }

    public List<ArgoApplication> getAllApplications() throws ApiException {
        var applicationList = this.customObjectsApi.listClusterCustomObject(
                "argoproj.io",
                "v1alpha1",
                "applications",
                "true", null, null, null, null, null, null, null, null, null
        );

        String json = gson.toJson(applicationList);
        ArgoApplicationList list = gson.fromJson(json, ArgoApplicationList.class);
        return list.getItems();
    }
    public List<ArgoApplication> getNameSpacedApplications(String namespace) throws ApiException {
        var applicationList = this.customObjectsApi.listNamespacedCustomObject(
                "argoproj.io",
                "v1alpha1",
                namespace,
                "applications",
                "true", null, null, null, null, null, null, null, null, null
        );
        String json = gson.toJson(applicationList);
        ArgoApplicationList list = gson.fromJson(json, ArgoApplicationList.class);
        return list.getItems();
    }
    public CoreV1EventList getNameSpacedEvents(String namespace) throws ApiException {

        return this.coreV1Api.listNamespacedEvent(
                namespace,
                "true",
null,                null,

                null,
                null, null, null, null, null, null, null
        );
    }


    public List<String> getAllNameSpaces() throws ApiException {
        V1NamespaceList namespaceList = this.coreV1Api.listNamespace(null, null, null, null, null, null, null, null, null, null, null);
        return namespaceList.getItems().stream()
                .map(V1Namespace::getMetadata).filter(Objects::nonNull)
                .map(V1ObjectMeta::getName)
                .collect(Collectors.toList());
    }

}
