/**
 * Copyright (c) 2010-2021 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.deutschebahn.internal;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.deutschebahn.internal.timetable.dto.Event;
import org.openhab.binding.deutschebahn.internal.timetable.dto.TimetableStop;
import org.openhab.core.types.State;
import org.openhab.core.types.UnDefType;

/**
 * Selection that returns the value of an {@link EventAttribute}. The required {@link Event} is
 * selected with the given {@link EventType}.
 * 
 * @author Sönke Küper - Initial contribution
 */
@NonNullByDefault
public final class EventAttributeSelection implements AttributeSelection {

    private final EventType eventType;
    private final EventAttribute<?, ?> eventAttribute;

    /**
     * Creates an new {@link EventAttributeSelection}.
     */
    public EventAttributeSelection(EventType eventType, EventAttribute<?, ?> eventAttribute) {
        this.eventType = eventType;
        this.eventAttribute = eventAttribute;
    }

    @Nullable
    @Override
    public State getState(TimetableStop stop) {
        final Event event = eventType.getEvent(stop);
        if (event == null) {
            return UnDefType.UNDEF;
        } else {
            return this.eventAttribute.getState(event);
        }
    }
}
