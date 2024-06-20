package by.zemich.aims.getproductdescription.impl;

import by.zemich.aims.getproductdescription.GetProductDescriptionRequest;
import by.zemich.aims.getproductdescription.GetProductDescriptionResponse;
import by.zemich.aims.getproductdescription.GetProductDescriptionServiceApi;
import com.google.cloud.aiplatform.v1.EndpointName;
import com.google.cloud.aiplatform.v1.PredictResponse;
import com.google.cloud.aiplatform.v1.PredictionServiceClient;
import com.google.cloud.aiplatform.v1.PredictionServiceSettings;
import com.google.protobuf.Value;
import com.google.protobuf.util.JsonFormat;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@Primary
public class GetProductDescriptionTextBisonAIService implements GetProductDescriptionServiceApi {
    private final String parameters =
            """
                    {
                        "temperature": 0.9,
                        "maxOutputTokens": 256,
                        "topP": 0.95,
                        "topK": 40
                    }
                    """;

    private final String project = "sadovod-423711";
    private final String location = "us-central1";
    private final String publisher = "google";
    private final String model = "text-bison@002";

    @Override
    public GetProductDescriptionResponse createJsonProductDescription(GetProductDescriptionRequest request) {

        String prompt = """
                Make JSON response for POJO:
                %s
                Important! If Is not specified that the product has no color selection, then there is an option to choose a color.
                Product description: %s
                """.formatted(request.getJsonDestination(), request.getSource());

        String instance = """
                {
                    "prompt": "%s"
                }
                """.formatted(prompt);

        String endpoint = String.format("%s-aiplatform.googleapis.com:443", location);


        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests.
        try (PredictionServiceClient predictionServiceClient =
                     PredictionServiceClient.create(PredictionServiceSettings.newBuilder()
                             .setEndpoint(endpoint)
                             .build())) {

            final EndpointName endpointName =
                    EndpointName.ofProjectLocationPublisherModelName(project, location, publisher, model);

            // Initialize client that will be used to send requests. This client only needs to be created
            // once, and can be reused for multiple requests.
            Value.Builder instanceValue = Value.newBuilder();
            JsonFormat.parser().merge(instance, instanceValue);
            List<Value> instances = new ArrayList<>();
            instances.add(instanceValue.build());

            // Use Value.Builder to convert instance to a dynamically typed value that can be
            // processed by the service.
            Value.Builder parameterValueBuilder = Value.newBuilder();
            JsonFormat.parser().merge(parameters, parameterValueBuilder);
            Value parameterValue = parameterValueBuilder.build();

            PredictResponse predictResponse =
                    predictionServiceClient.predict(endpointName, instances, parameterValue);


            String result = predictResponse
                    .getPredictionsList()
                    .get(0)
                    .getStructValue()
                    .getFieldsMap()
                    .get("content")
                    .getStringValue();

            return new GetProductDescriptionResponse(result);

        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}

