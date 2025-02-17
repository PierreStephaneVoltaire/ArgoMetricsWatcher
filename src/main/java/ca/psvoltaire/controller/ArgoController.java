package ca.psvoltaire.controller;

import ca.psvoltaire.dto.ArgoApplication;
import ca.psvoltaire.job.EventWatcherJob;
import ca.psvoltaire.service.ArgoService;
import io.kubernetes.client.openapi.ApiException;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/") // (1)
public class ArgoController {
    private final ArgoService argoService;
    private final EventWatcherJob eventWatcherJob;

    @Inject
    public ArgoController(ArgoService argoService, EventWatcherJob eventWatcherJob) {
        this.argoService = argoService;
        this.eventWatcherJob = eventWatcherJob;
    }

    @Get(uri = "ns", produces = MediaType.APPLICATION_JSON)
    public List<String> ns() {
        try {
            return this.argoService.getAllNameSpaces();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public List<ArgoApplication> index() {
        try {
            return this.argoService.getAllApplications();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

}