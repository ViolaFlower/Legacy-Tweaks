package xyz.violaflower.legacy_tweaks.networking.event;

import xyz.violaflower.legacy_tweaks.networking.configuration.event.ClientConfigurationEvents;

public interface InternalClientConfigurationEvents {
	Event<ClientConfigurationEvents.Start> START = EventFactory.create(ClientConfigurationEvents.Start.class);
}
