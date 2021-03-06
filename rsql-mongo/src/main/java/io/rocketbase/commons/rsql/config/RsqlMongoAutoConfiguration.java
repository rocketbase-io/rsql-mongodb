package io.rocketbase.commons.rsql.config;

import io.rocketbase.commons.rsql.converter.ComparisonToCriteriaConverter;
import io.rocketbase.commons.rsql.RsqlMongoAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class RsqlMongoAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RsqlMongoAdapter rsqlMongoAdapter(@Autowired MongoMappingContext mongoMappingContext, @Autowired(required = false) ConversionService conversionService) {
        ComparisonToCriteriaConverter converter = new ComparisonToCriteriaConverter(conversionService != null ? conversionService : new DefaultConversionService(), mongoMappingContext);
        return new RsqlMongoAdapter(converter);
    }
}
