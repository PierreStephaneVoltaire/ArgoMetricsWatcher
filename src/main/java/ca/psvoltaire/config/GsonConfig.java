package ca.psvoltaire.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class GsonConfig {

    @Singleton
    public Gson gson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();
    }
}
