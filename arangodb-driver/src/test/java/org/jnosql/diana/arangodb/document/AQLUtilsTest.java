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
package org.jnosql.diana.arangodb.document;

import org.jnosql.diana.api.document.DocumentQuery;
import org.junit.Test;

import java.util.Map;

import static org.jnosql.diana.api.document.query.DocumentQueryBuilder.select;
import static org.junit.Assert.assertEquals;

public class AQLUtilsTest {

    @Test
    public void shouldRunEqualsQuery() {
        DocumentQuery query = select().from("collection")
                .where("name").eq("value").build();

        AQLQueryResult convert = AQLUtils.select(query);
        String aql = convert.getQuery();
        Map<String, Object> values = convert.getValues();
        assertEquals("value", values.get("name"));
        assertEquals("FOR c IN collection FILTER  c.name == @name RETURN c", aql);

    }

    @Test
    public void shouldRunEqualsQueryAnd() {
        DocumentQuery query = select().from("collection")
                .where("name").eq("value")
                .and("age").lte(10)
                .build();

        AQLQueryResult convert = AQLUtils.select(query);
        String aql = convert.getQuery();
        Map<String, Object> values = convert.getValues();
        assertEquals("value", values.get("name"));
        assertEquals("FOR c IN collection FILTER  c.name == @name AND  c.age <= @age RETURN c", aql);

    }

    @Test
    public void shouldRunEqualsQueryOr() {
        DocumentQuery query = select().from("collection")
                .where("name").eq("value")
                .or("age").lte(10)
                .build();

        AQLQueryResult convert = AQLUtils.select(query);
        String aql = convert.getQuery();
        Map<String, Object> values = convert.getValues();
        assertEquals("value", values.get("name"));
        assertEquals("FOR c IN collection FILTER  c.name == @name OR  c.age <= @age RETURN c", aql);

    }

    @Test
    public void shouldRunEqualsQuerySort() {
        DocumentQuery query = select().from("collection")
                .where("name").eq("value")
                .orderBy("name").asc().build();

        AQLQueryResult convert = AQLUtils.select(query);
        String aql = convert.getQuery();
        Map<String, Object> values = convert.getValues();
        assertEquals("value", values.get("name"));
        assertEquals("FOR c IN collection FILTER  c.name == @name SORT  c.name ASC RETURN c", aql);
    }

    @Test
    public void shouldRunEqualsQuerySort2() {
        DocumentQuery query = select().from("collection")
                .where("name").eq("value")
                .orderBy("name").asc()
                .orderBy("age").desc().build();

        AQLQueryResult convert = AQLUtils.select(query);
        String aql = convert.getQuery();
        Map<String, Object> values = convert.getValues();
        assertEquals("value", values.get("name"));
        assertEquals("FOR c IN collection FILTER  c.name == @name SORT  c.name ASC , c.age DESC RETURN c", aql);
    }


    @Test
    public void shouldRunEqualsQueryLimit() {
        DocumentQuery query = select().from("collection")
                .where("name").eq("value")
                .limit(5).build();

        AQLQueryResult convert = AQLUtils.select(query);
        String aql = convert.getQuery();
        Map<String, Object> values = convert.getValues();
        assertEquals("value", values.get("name"));
        assertEquals("FOR c IN collection FILTER  c.name == @name LIMIT 5 RETURN c", aql);

    }

    @Test
    public void shouldRunEqualsQueryLimit2() {
        DocumentQuery query = select().from("collection")
                .where("name").eq("value")
                .start(1).limit(5).build();

        AQLQueryResult convert = AQLUtils.select(query);
        String aql = convert.getQuery();
        Map<String, Object> values = convert.getValues();
        assertEquals("value", values.get("name"));
        assertEquals("FOR c IN collection FILTER  c.name == @name LIMIT 1, 5 RETURN c", aql);
    }

    @Test
    public void shouldRunEqualsQueryNot() {
        DocumentQuery query = select().from("collection")
                .where("name").not().eq("value").build();

        AQLQueryResult convert = AQLUtils.select(query);
        String aql = convert.getQuery();
        Map<String, Object> values = convert.getValues();
        assertEquals("value", values.get("name"));
        assertEquals("FOR c IN collection FILTER  NOT  c.name == @name RETURN c", aql);

    }


}