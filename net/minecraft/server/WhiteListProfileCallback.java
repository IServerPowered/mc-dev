package net.minecraft.server;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import com.mojang.authlib.yggdrasil.ProfileNotFoundException;

final class WhiteListProfileCallback implements ProfileLookupCallback {

    final MinecraftServer a;
    final WhiteList b;

    WhiteListProfileCallback(MinecraftServer minecraftserver, WhiteList whitelist) {
        this.a = minecraftserver;
        this.b = whitelist;
    }

    public void onProfileLookupSucceeded(GameProfile gameprofile) {
        this.a.getUserCache().a(gameprofile);
        this.b.add(new WhiteListEntry(gameprofile));
    }

    public void onProfileLookupFailed(GameProfile gameprofile, Exception exception) {
        NameReferencingFileConverter.a().warn("Could not lookup user whitelist entry for " + gameprofile.getName(), (Throwable) exception);
        if (!(exception instanceof ProfileNotFoundException)) {
            throw new FileConversionException("Could not request user " + gameprofile.getName() + " from backend systems", exception, (PredicateEmptyList) null);
        }
    }
}
