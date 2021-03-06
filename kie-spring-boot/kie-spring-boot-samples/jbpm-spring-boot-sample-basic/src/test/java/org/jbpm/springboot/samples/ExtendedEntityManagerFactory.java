/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jbpm.springboot.samples;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

public class ExtendedEntityManagerFactory {

    protected static final String PERSISTENCE_UNIT_NAME = "org.jbpm.domain";
    protected static final String PERSISTENCE_XML_LOCATION = "classpath:/META-INF/jbpm-persistence.xml";
    
    @Bean("entityManagerFactory")    
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaProperties jpaProperties, DataSource dataSource){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
        factoryBean.setPersistenceXmlLocation(PERSISTENCE_XML_LOCATION);
        factoryBean.setJtaDataSource(dataSource);
        factoryBean.setJpaPropertyMap(jpaProperties.getProperties());
                
        factoryBean.setPersistenceUnitPostProcessors(new PersistenceUnitPostProcessor() {
            
            
            @Override
            public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo pui) {
                pui.addManagedClassName("org.jbpm.springboot.samples.entities.Person");
                
            }
        });
   
        return factoryBean;
    }
}
