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
package io.syndesis.connector.calendar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.camel.CamelContext;
import org.apache.camel.component.extension.MetaDataExtension;

import com.google.api.services.calendar.model.CalendarListEntry;

import io.syndesis.connector.support.verifier.api.ComponentMetadataRetrieval;
import io.syndesis.connector.support.verifier.api.PropertyPair;
import io.syndesis.connector.support.verifier.api.SyndesisMetadata;

public class GoogleCalendarMetaDataRetrieval extends ComponentMetadataRetrieval {

    /**
     * TODO: use local extension, remove when switching to camel 2.22.x
     */
    @Override
    protected MetaDataExtension resolveMetaDataExtension(CamelContext context, Class<? extends MetaDataExtension> metaDataExtensionClass, String componentId,
        String actionId) {
        return new GoogleCalendarMetaDataExtension(context);
    }

    @Override
    protected SyndesisMetadata adapt(CamelContext context, String componentId, String actionId, Map<String, Object> properties,
        MetaDataExtension.MetaData metadata) {

        @SuppressWarnings("unchecked")
        Set<CalendarListEntry> calendars = (Set<CalendarListEntry>) metadata.getPayload();

        List<PropertyPair> calendarResult = new ArrayList<>();
        calendars.stream().forEach(t -> calendarResult.add(new PropertyPair(t.getId(), t.getSummary())));

        return SyndesisMetadata.of(Collections.singletonMap("calendarId", calendarResult));
    }

}
