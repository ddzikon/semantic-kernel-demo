package org.example.agent;

import com.google.inject.Inject;
import com.microsoft.semantickernel.semanticfunctions.annotations.DefineKernelFunction;
import com.microsoft.semantickernel.semanticfunctions.annotations.KernelFunctionParameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
class WeatherPlugin {

    private final WeatherClient weatherClient;

    @DefineKernelFunction(
            name = "getCurrentWeather",
            description = "Get / fetch info about current weather at given location.",
            returnType = "string"
    )
    public String getCurrentWeather(
            @KernelFunctionParameter(name = "location", description = "Name of a place / location") String location
    ) {
        log.info("getCurrentWeather called with argument: location = {}", location);
        return weatherClient.getCurrent(location);
    }
}
