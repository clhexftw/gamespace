package org.nameless.gamespace.utils.di;

import android.content.Context;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.nameless.gamespace.data.SystemSettings;
import org.nameless.gamespace.utils.GameModeUtils;
/* loaded from: classes.dex */
public final class MainModule_ProvideSystemSettingsFactory implements Provider {
    private final Provider<Context> contextProvider;
    private final Provider<GameModeUtils> gameModeUtilsProvider;

    public MainModule_ProvideSystemSettingsFactory(Provider<Context> provider, Provider<GameModeUtils> provider2) {
        this.contextProvider = provider;
        this.gameModeUtilsProvider = provider2;
    }

    @Override // javax.inject.Provider
    public SystemSettings get() {
        return provideSystemSettings(this.contextProvider.get(), this.gameModeUtilsProvider.get());
    }

    public static MainModule_ProvideSystemSettingsFactory create(Provider<Context> provider, Provider<GameModeUtils> provider2) {
        return new MainModule_ProvideSystemSettingsFactory(provider, provider2);
    }

    public static SystemSettings provideSystemSettings(Context context, GameModeUtils gameModeUtils) {
        return (SystemSettings) Preconditions.checkNotNullFromProvides(MainModule.INSTANCE.provideSystemSettings(context, gameModeUtils));
    }
}
