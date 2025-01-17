package xyz.violaflower.legacy_tweaks.util.common.lang;

public interface Lang {

    Translate MOD_NAME = new Translate("common.legacy_tweaks.mod.name");
    Translate ACTIVE_TWEAKS = new Translate("common.legacy_tweaks.debug.activeTweaks");
    Translate WINDOW_TITLE = new Translate("common.legacy_tweaks.window.title");
    Translate WINDOW_TITLE_TU = new Translate("common.legacy_tweaks.window.titleTU");

    interface KeyBindings
    {
        Translate OPEN_CONFIG = from("openConfig");
        Translate MAIN_CATEGORY = from("mainCategory");

        private static Translate from(String name)
        {
            return new Translate("key.legacy_tweaks.keybindings." + name);
        }
    }

    interface ConfigScreen
    {
        Translate TITLE = from("title");
        Translate ENABLED = from("enabled");
        Translate DISABLED = from("disabled");
        Translate SETTINGS = from("settings");
        Translate RESET = from("reset");
        Translate LOCKED_SERVER = from("lockedServer");
        Translate VALUE = from("value");
        Translate SUB_TYASKA = from("subTyaska");

        private static Translate from(String name)
        {
            return new Translate("gui.legacy_tweaks.configScreen." + name);
        }
    }

    interface ScreenNotImplemented
    {
        Translate WARN = from("warn");
        Translate PLACEHOLDER_BUTTON = from("placeholderButton");

        private static Translate from(String name)
        {
            return new Translate("gui.legacy_tweaks.notImplemented." + name);
        }
    }

    interface OptionsScreen
    {
        Translate GAME_OPTIONS = from("gameOptions");
        Translate AUDIO = from("audio");
        Translate GRAPHICS = from("graphics");
        Translate UI = from("ui");
        Translate RESET = from("reset");
        Translate JAVA_SETTINGS = from("javaSettings");

        private static Translate from(String name)
        {
            return new Translate("gui.legacy_tweaks.optionsScreen." + name);
        }
    }

    interface HelpOptionsScreen
    {
        Translate CHANGE_SKIN = from("changeSkin");
        Translate HOW_TO_PLAY = from("howToPlay");
        Translate CONTROLS = from("controls");
        Translate SETTINGS = from("settings");
        Translate CREDITS = from("credits");

        private static Translate from(String name)
        {
            return new Translate("gui.legacy_tweaks.helpOptionsScreen." + name);
        }
    }

    interface PauseScreen
    {
        Translate RESUME_GAME = from("resumeGame");
        Translate ACHIEVEMENTS = from("achievements");
        Translate DISCONNECT = from("disconnect");
        Translate EXIT_GAME = from("exitGame");

        private static Translate from(String name)
        {
            return new Translate("gui.legacy_tweaks.pauseScreen." + name);
        }
    }

    interface TitleScreen
    {
        Translate PLAY_GAME = from("playGame");
        Translate MINI_GAMES = from("miniGames");
        Translate LEADERBOARDS = from("leaderboards");
        Translate HELP_OPTIONS = from("helpOptions");
        Translate STORE = from("store");
        Translate JAVA = from("java");
        Translate QUIT = from("quit");

        private static Translate from(String name)
        {
            return new Translate("gui.legacy_tweaks.titleScreen." + name);
        }
    }

    interface Container
    {
        Translate INVENTORY = from("inventory");

        private static Translate from(String name)
        {
            return new Translate("gui.legacy_tweaks.container." + name);
        }
    }

    interface Dimension
    {
        Translate ENTRANCE = from("entrance");
        Translate LEAVE = from("leave");

        private static Translate from(String name)
        {
            return new Translate("game.legacy_tweaks.dimension." + name);
        }
    }

    interface Gameplay
    {
        Translate MAP_COORDS = from("mapCoords");

        private static Translate from(String name)
        {
            return new Translate("game.legacy_tweaks.gameplay." + name);
        }
    }

    interface Networking
    {
        Translate RECEIVED = from("received");
        Translate FROM_SERVER = from("fromServer");
        Translate RESPONDING = from("responding");
        Translate SERVER_CONFIG_VER = from("serverConfigVer");
        Translate PLAYER_CONFIG_VER = from("playerConfigVer");
        Translate RECEIVED_CONFIG = from("receivedConfig");
        Translate SERVER_RECEIVED = from("serverReceived");
        Translate FROM_CONNECT_CLIENT = from("fromConnectClient");

        private static Translate from(String name)
        {
            return new Translate("networking.legacy_tweaks." + name);
        }
    }

    interface TitleUpdates
    {
        Translate TU_VERSION = from("tuVersion");
        Translate TU1 = from("tu1");
        Translate TU3 = from("tu3");
        Translate TU5 = from("tu5");
        Translate TU12 = from("tu12");
        Translate TU25 = from("tu25");

        private static Translate from(String name)
        {
            return new Translate("common.legacy_tweaks.title_update." + name);
        }
    }

    interface EnumTweak
    {
        Translate JAVA = from("java");
        Translate DISABLED = from("disabled");

        private static Translate from(String name)
        {
            return new Translate("tweak.legacy_tweaks.enum." + name);
        }
    }

    interface Resolution
    {
        Translate RES544P = from("544p");
        Translate RES720P = from("720p");
        Translate RES1080P = from("1080p");
        Translate RES1440P = from("1440p");
        Translate RES2160P = from("2160p");

        private static Translate from(String name)
        {
            return new Translate("common.legacy_tweaks.resolution." + name);
        }
    }

    static Translate geTweakLangKey(String name)
    {
        return new Translate("tweak.legacy_tweaks.setting." + name);
    }
}
