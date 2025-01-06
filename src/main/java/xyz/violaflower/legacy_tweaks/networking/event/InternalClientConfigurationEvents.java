package xyz.violaflower.legacy_tweaks.networking.event;

import org.jetbrains.annotations.ApiStatus;
import xyz.violaflower.legacy_tweaks.networking.configuration.event.ClientConfigurationEvents;

@ApiStatus.Internal
public interface InternalClientConfigurationEvents {
	Event<ClientConfigurationEvents.Start> START = EventFactory.create(ClientConfigurationEvents.Start.class);
}
