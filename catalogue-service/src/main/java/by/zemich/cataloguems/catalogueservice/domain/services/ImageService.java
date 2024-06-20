package by.zemich.cataloguems.catalogueservice.domain.services;

import by.zemich.cataloguems.catalogueservice.domain.model.dto.ProductDto;
import com.google.common.base.CaseFormat;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ImageService {
    public static String getFromDtoStructure(ProductDto dto){
        String fields = Arrays.stream(ProductDto.class.getDeclaredFields())
                .map(field -> {
                    String[] types = field.getType().getTypeName().split("\\.");
                    String type = types[types.length - 1];
                    String name = field.getName();
                    return type + " " + CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
                }).collect(Collectors.joining("\n"));


        return  """
                public class ProductDescription{
                %s
                }
                """.formatted(fields);
    }
}
