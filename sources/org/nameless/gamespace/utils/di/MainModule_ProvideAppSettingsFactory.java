package org.nameless.gamespace.utils.di;

import android.content.Context;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.nameless.gamespace.data.AppSettings;
/* loaded from: classes.dex */
public final class MainModule_ProvideAppSettingsFactory implements Provider {
    private final Provider<Context> contextProvider;

    public MainModule_ProvideAppSettingsFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public AppSettings get() {
        return provideAppSettings(this.contextProvider.get());
    }

    public static MainModule_ProvideAppSettingsFactory create(Provider<Context> provider) {
        return new MainModule_ProvideAppSettingsFactory(provider);
    }

    public static AppSettings provideAppSettings(Context context) {
        return (AppSettings) Preconditions.checkNotNullFromProvides(MainModule.INSTANCE.provideAppSettings(context));
    }
}
