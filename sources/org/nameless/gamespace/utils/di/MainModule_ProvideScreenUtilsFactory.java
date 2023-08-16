package org.nameless.gamespace.utils.di;

import android.content.Context;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import org.nameless.gamespace.utils.ScreenUtils;
/* loaded from: classes.dex */
public final class MainModule_ProvideScreenUtilsFactory implements Provider {
    private final Provider<Context> contextProvider;

    public MainModule_ProvideScreenUtilsFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public ScreenUtils get() {
        return provideScreenUtils(this.contextProvider.get());
    }

    public static MainModule_ProvideScreenUtilsFactory create(Provider<Context> provider) {
        return new MainModule_ProvideScreenUtilsFactory(provider);
    }

    public static ScreenUtils provideScreenUtils(Context context) {
        return (ScreenUtils) Preconditions.checkNotNullFromProvides(MainModule.INSTANCE.provideScreenUtils(context));
    }
}
