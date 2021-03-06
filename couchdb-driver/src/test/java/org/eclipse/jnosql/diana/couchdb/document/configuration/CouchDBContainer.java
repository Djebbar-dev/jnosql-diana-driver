/*
 *
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
 *
 */
package org.eclipse.jnosql.diana.couchdb.document.configuration;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;
import org.testcontainers.containers.wait.strategy.WaitAllStrategy;
import org.testcontainers.containers.wait.strategy.WaitStrategy;

import java.util.Arrays;

public class CouchDBContainer extends GenericContainer {

	public static final String IMAGE = "couchdb";
	public static final String DEFAULT_TAG = "latest";
	public static final Integer PORT = 5984;

	public CouchDBContainer() {
		super(IMAGE + ":" + DEFAULT_TAG);
	}

	@Override
	protected void configure() {
		setExposedPorts(Arrays.asList(PORT));
		setWaitStrategy(getCouchDBWaitStrategy());
		setEnv(Arrays.asList("COUCHDB_USER=admin", "COUCHDB_PASSWORD=password"));
	}

	private WaitStrategy getCouchDBWaitStrategy() {
		return new WaitAllStrategy()
			.withStrategy(new HttpWaitStrategy()
				.forPort(PORT)
				.forPath("/")
				.forStatusCode(200));
	}

}
