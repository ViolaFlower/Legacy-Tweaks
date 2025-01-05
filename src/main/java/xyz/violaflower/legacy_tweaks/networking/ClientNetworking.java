package xyz.violaflower.legacy_tweaks.networking;

import xyz.violaflower.legacy_tweaks.client.LegacyTweaksClient;

// This class is only here for class loader isolation, so we don't load client-side classes on the server.
public class ClientNetworking {
	public ClientNetworking() {
		LegacyTweaksClient.initNetworking();
	}
}
