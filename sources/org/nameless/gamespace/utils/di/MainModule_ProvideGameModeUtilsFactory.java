package org.nameless.gamespace.utils.di;

import android.content.Context;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.nameless.gamespace.utils.GameModeUtils;
/* loaded from: classes.dex */
public final class MainModule_ProvideGameModeUtilsFactory implements Provider {
    private final Provider<Context> contextProvider;

    public MainModule_ProvideGameModeUtilsFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public GameModeUtils get() {
        return provideGameModeUtils(this.contextProvider.get());
    }

    public static MainModule_ProvideGameModeUtilsFactory create(Provider<Context> provider) {
        return new MainModule_ProvideGameModeUtilsFactory(provider);
    }

    public static GameModeUtils provideGameModeUtils(Context context) {
        return (GameModeUtils) Preconditions.checkNotNullFromProvides(MainModule.INSTANCE.provideGameModeUtils(context));
    }
}
