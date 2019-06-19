/*
 *  Copyright (c) 2017 Otávio Santana and others
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   and Apache License v2.0 which accompanies this distribution.
 *   The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *   and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 *   You may elect to redistribute this code under either of these licenses.
 *
 *   Contributors:
 *
 *   Otavio Santana
 */
package org.jnosql.diana.couchbase.document;

import jakarta.nosql.document.DocumentCollectionManagerFactory;
import jakarta.nosql.document.DocumentConfiguration;
import jakarta.nosql.document.DocumentConfigurationAsync;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static jakarta.nosql.document.DocumentConfiguration.getConfiguration;
import static jakarta.nosql.document.DocumentConfigurationAsync.getConfiguration;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CouchbaseDocumentConfigurationTest {

    @Test
    public void shouldCreateDocumentCollectionManagerFactoryByMap() {

        CouchbaseDocumentConfiguration configuration = new CouchbaseDocumentConfiguration();
        DocumentCollectionManagerFactory managerFactory = configuration.get();
        assertNotNull(managerFactory);
    }

    @Test
    public void shouldCreateDocumentCollectionManagerFactoryByFile() {
        CouchbaseDocumentConfiguration configuration = new CouchbaseDocumentConfiguration();
        DocumentCollectionManagerFactory managerFactory = configuration.get();
        assertNotNull(managerFactory);
    }

    @Test
    public void shouldGetConfiguration() {
        DocumentConfiguration configuration = DocumentConfiguration.getConfiguration();
        Assertions.assertNotNull(configuration);
        Assertions.assertTrue(configuration instanceof CouchbaseDocumentConfiguration);
    }

    @Test
    public void shouldGetConfigurationFromQuery() {
        CouchbaseDocumentConfiguration configuration = DocumentConfiguration
                .getConfiguration(CouchbaseDocumentConfiguration.class);
        Assertions.assertNotNull(configuration);
        Assertions.assertTrue(configuration instanceof CouchbaseDocumentConfiguration);
    }


    @Test
    public void shouldGetConfigurationAsync() {
        DocumentConfigurationAsync configuration = DocumentConfigurationAsync.getConfiguration();
        Assertions.assertNotNull(configuration);
        Assertions.assertTrue(configuration instanceof CouchbaseDocumentConfiguration);
    }

    @Test
    public void shouldGetConfigurationAsyncFromQuery() {
        CouchbaseDocumentConfiguration configuration = DocumentConfigurationAsync
                .getConfiguration(CouchbaseDocumentConfiguration.class);
        Assertions.assertNotNull(configuration);
        Assertions.assertTrue(configuration instanceof CouchbaseDocumentConfiguration);
    }
}