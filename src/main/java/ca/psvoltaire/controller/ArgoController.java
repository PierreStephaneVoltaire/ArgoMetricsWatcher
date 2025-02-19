package ca.psvoltaire.controller;

import ca.psvoltaire.dto.ArgoApplication;
import ca.psvoltaire.job.EventWatcherJob;
import ca.psvoltaire.service.ArgoService;
import io.kubernetes.client.openapi.ApiException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.uri.UriBuilder;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.List;

@Slf4j
@Controller("/") // (1)
public class ArgoController {
    private final static URI SWAGGER_UI = UriBuilder.of("/swagger-ui").path("index.html").build();
    private final ArgoService argoService;

    @Inject
    public ArgoController(ArgoService argoService, EventWatcherJob eventWatcherJob) {
        this.argoService = argoService;
    }

    @Get(uri = "ns", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<List<String>> ns() {
        try {
            return HttpResponse.ok(this.argoService.getAllNameSpaces());
        } catch (ApiException e) {
            log.error(e.getMessage(), e);
            return HttpResponse.serverError();
        }
    }

    @Get(uri = "apps", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<List<ArgoApplication>> index() {
        try {
            return HttpResponse.ok(this.argoService.getAllApplications());
        } catch (ApiException e) {
            log.error(e.getMessage(), e);
            return HttpResponse.serverError();
        }
    }

    @Get
    @Hidden
    HttpResponse<?> home() {
        return HttpResponse.seeOther(SWAGGER_UI);
    }
}