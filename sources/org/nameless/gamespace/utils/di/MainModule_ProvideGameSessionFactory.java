package org.nameless.gamespace.utils.di;

import android.content.Context;
import com.google.gson.Gson;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.nameless.gamespace.data.AppSettings;
import org.nameless.gamespace.data.GameSession;
import org.nameless.gamespace.data.SystemSettings;
/* loaded from: classes.dex */
public final class MainModule_ProvideGameSessionFactory implements Provider {
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<Context> contextProvider;
    private final Provider<Gson> gsonProvider;
    private final Provider<SystemSettings> systemSettingsProvider;

    public MainModule_ProvideGameSessionFactory(Provider<Context> provider, Provider<AppSettings> provider2, Provider<SystemSettings> provider3, Provider<Gson> provider4) {
        this.contextProvider = provider;
        this.appSettingsProvider = provider2;
        this.systemSettingsProvider = provider3;
        this.gsonProvider = provider4;
    }

    @Override // javax.inject.Provider
    public GameSession get() {
        return provideGameSession(this.contextProvider.get(), this.appSettingsProvider.get(), this.systemSettingsProvider.get(), this.gsonProvider.get());
    }

    public static MainModule_ProvideGameSessionFactory create(Provider<Context> provider, Provider<AppSettings> provider2, Provider<SystemSettings> provider3, Provider<Gson> provider4) {
        return new MainModule_ProvideGameSessionFactory(provider, provider2, provider3, provider4);
    }

    public static GameSession provideGameSession(Context context, AppSettings appSettings, SystemSettings systemSettings, Gson gson) {
        return (GameSession) Preconditions.checkNotNullFromProvides(MainModule.INSTANCE.provideGameSession(context, appSettings, systemSettings, gson));
    }
}
