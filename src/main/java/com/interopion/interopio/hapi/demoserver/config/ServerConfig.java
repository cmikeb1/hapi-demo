package com.interopion.interopio.hapi.demoserver.config;

import ca.uhn.fhir.jpa.config.BaseJavaConfigDstu2;
import ca.uhn.fhir.jpa.config.BaseJavaConfigDstu3;
import ca.uhn.fhir.jpa.config.BaseJavaConfigR4;
import ca.uhn.fhir.jpa.dao.DaoConfig;
import org.hl7.fhir.instance.model.Subscription;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

    @Configuration
    @ConditionalOnProperty(name = "hapi.fhir.version", havingValue = "DSTU3")
    static class Dstu3 extends BaseJavaConfigDstu3 {
        @Bean(name = "fhirDaoConfig")
        public DaoConfig daoConfig() {
            return universalDaiConfig();
        }
    }

    @Configuration
    @ConditionalOnProperty(name = "hapi.fhir.version", havingValue = "DSTU2")
    static class Dstu2 extends BaseJavaConfigDstu2 {
        @Bean(name = "fhirDaoConfig")
        public DaoConfig daoConfig() {
            return universalDaiConfig();
        }
    }

    @Configuration
    @ConditionalOnProperty(name = "hapi.fhir.version", havingValue = "R4")
    static class R4 extends BaseJavaConfigR4 {
        @Bean(name = "fhirDaoConfig")
        public DaoConfig daoConfig() {
            return universalDaiConfig();
        }
    }

    public static DaoConfig universalDaiConfig() {
        DaoConfig retVal = new DaoConfig();
        retVal.setAllowMultipleDelete(true);
        retVal.addSupportedSubscriptionType(Subscription.SubscriptionChannelType.WEBSOCKET);
        retVal.addSupportedSubscriptionType(Subscription.SubscriptionChannelType.RESTHOOK);
        retVal.setSubscriptionMatchingEnabled(true);
        return retVal;
    }
}
