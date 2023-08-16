package org.nameless.gamespace;

import android.app.Application;
import dagger.hilt.android.internal.managers.ApplicationComponentManager;
import dagger.hilt.android.internal.managers.ComponentSupplier;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.internal.GeneratedComponentManager;
import dagger.hilt.internal.UnsafeCasts;
/* loaded from: classes.dex */
public abstract class Hilt_GameSpace extends Application implements GeneratedComponentManager {
    private final ApplicationComponentManager componentManager = new ApplicationComponentManager(new ComponentSupplier() { // from class: org.nameless.gamespace.Hilt_GameSpace.1
        @Override // dagger.hilt.android.internal.managers.ComponentSupplier
        public Object get() {
            return DaggerGameSpace_HiltComponents_SingletonC.builder().applicationContextModule(new ApplicationContextModule(Hilt_GameSpace.this)).build();
        }
    });

    public final ApplicationComponentManager componentManager() {
        return this.componentManager;
    }

    @Override // dagger.hilt.internal.GeneratedComponentManager
    public final Object generatedComponent() {
        return componentManager().generatedComponent();
    }

    @Override // android.app.Application
    public void onCreate() {
        ((GameSpace_GeneratedInjector) generatedComponent()).injectGameSpace((GameSpace) UnsafeCasts.unsafeCast(this));
        super.onCreate();
    }
}
