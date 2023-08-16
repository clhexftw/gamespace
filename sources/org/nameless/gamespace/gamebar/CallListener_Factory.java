package org.nameless.gamespace.gamebar;

import android.content.Context;
import javax.inject.Provider;
import org.nameless.gamespace.data.AppSettings;
/* loaded from: classes.dex */
public final class CallListener_Factory implements Provider {
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<Context> contextProvider;

    public CallListener_Factory(Provider<Context> provider, Provider<AppSettings> provider2) {
        this.contextProvider = provider;
        this.appSettingsProvider = provider2;
    }

    @Override // javax.inject.Provider
    public CallListener get() {
        return newInstance(this.contextProvider.get(), this.appSettingsProvider.get());
    }

    public static CallListener_Factory create(Provider<Context> provider, Provider<AppSettings> provider2) {
        return new CallListener_Factory(provider, provider2);
    }

    public static CallListener newInstance(Context context, AppSettings appSettings) {
        return new CallListener(context, appSettings);
    }
}
