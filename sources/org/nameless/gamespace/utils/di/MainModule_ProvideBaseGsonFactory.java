package org.nameless.gamespace.utils.di;

import com.google.gson.Gson;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes.dex */
public final class MainModule_ProvideBaseGsonFactory implements Provider {

    /* loaded from: classes.dex */
    private static final class InstanceHolder {
        private static final MainModule_ProvideBaseGsonFactory INSTANCE = new MainModule_ProvideBaseGsonFactory();
    }

    @Override // javax.inject.Provider
    public Gson get() {
        return provideBaseGson();
    }

    public static MainModule_ProvideBaseGsonFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Gson provideBaseGson() {
        return (Gson) Preconditions.checkNotNullFromProvides(MainModule.INSTANCE.provideBaseGson());
    }
}
