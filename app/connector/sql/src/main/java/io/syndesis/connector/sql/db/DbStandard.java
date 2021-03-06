/*
 * Copyright (C) 2016 Red Hat, Inc.
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
package io.syndesis.connector.sql.db;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbStandard implements Db {

    @Override
    public String getDefaultSchema(final String dbUser) {
        return null;
    }

    @Override
    public String adaptPattern(final String pattern) {
        return pattern;
    }

    @Override
    public ResultSet fetchProcedureColumns(final DatabaseMetaData meta, final String catalog, final String schema, final String procedureName) throws SQLException {
        return meta.getProcedureColumns(
                catalog,
                adaptPattern(schema),
                adaptPattern(procedureName),
                adaptPattern(null));
    }

    @Override
    public ResultSet fetchProcedures(final DatabaseMetaData meta, final String catalog, final String schemaPattern, final String procedurePattern)
            throws SQLException {
        return meta.getProcedures(
                catalog,
                adaptPattern(schemaPattern),
                adaptPattern(procedurePattern));
    }

    @Override
    public String getAutoIncrementGrammar() {
        return "NUMBER GENERATED ALWAYS AS IDENTITY";
    }

    @Override
    public String getName() {
        return "Standard";
    }

}
