package by.zemich.aims.getproductdescription.impl;

import by.zemich.aims.getproductdescription.GetProductDescriptionServiceApi;
import com.google.cloud.aiplatform.v1.EndpointName;
import com.google.cloud.aiplatform.v1.PredictResponse;
import com.google.cloud.aiplatform.v1.PredictionServiceClient;
import com.google.cloud.aiplatform.v1.PredictionServiceSettings;
import com.google.protobuf.Value;
import com.google.protobuf.util.JsonFormat;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@Order(1)
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
    public String getProductDescription(String request) {

        String prompt = request;

        String instance = """
                {
                    "prompt": "%s"
                }
                """.formatted(prompt);

        String endpoint = String.format("%s-aiplatform.googleapis.com:443", location);

        try (PredictionServiceClient predictionServiceClient =
                     PredictionServiceClient.create(PredictionServiceSettings.newBuilder()
                             .setEndpoint(endpoint)
                             .build())) {

            final EndpointName endpointName =
                    EndpointName.ofProjectLocationPublisherModelName(project, location, publisher, model);

            Value.Builder instanceValue = Value.newBuilder();
            JsonFormat.parser().merge(instance, instanceValue);
            List<Value> instances = new ArrayList<>();
            instances.add(instanceValue.build());

            Value.Builder parameterValueBuilder = Value.newBuilder();
            JsonFormat.parser().merge(parameters, parameterValueBuilder);
            Value parameterValue = parameterValueBuilder.build();

            PredictResponse predictResponse =
                    predictionServiceClient.predict(endpointName, instances, parameterValue);


            String result = predictResponse
                    .getPredictionsList()
                    .getFirst()
                    .getStructValue()
                    .getFieldsMap()
                    .get("content")
                    .getStringValue();

            return request;

        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}

