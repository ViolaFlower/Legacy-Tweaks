package xyz.violaflower.legacy_tweaks.networking;

import org.jetbrains.annotations.ApiStatus;

// This class is only here for class loader isolation, so we don't load client-side classes on the server.
@ApiStatus.Internal
class ClientNetworking {
	ClientNetworking() {
		xyz.violaflower.legacy_tweaks.network.client.ClientNetworking.initNetworking();
	}
}
