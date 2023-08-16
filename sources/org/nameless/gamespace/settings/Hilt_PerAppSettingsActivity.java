package org.nameless.gamespace.settings;

import android.content.Context;
import androidx.activity.contextaware.OnContextAvailableListener;
import com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity;
import dagger.hilt.android.internal.managers.ActivityComponentManager;
import dagger.hilt.internal.GeneratedComponentManager;
import dagger.hilt.internal.UnsafeCasts;
/* loaded from: classes.dex */
public abstract class Hilt_PerAppSettingsActivity extends CollapsingToolbarBaseActivity implements GeneratedComponentManager {
    private volatile ActivityComponentManager componentManager;
    private final Object componentManagerLock = new Object();
    private boolean injected = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Hilt_PerAppSettingsActivity() {
        _initHiltInternal();
    }

    private void _initHiltInternal() {
        addOnContextAvailableListener(new OnContextAvailableListener() { // from class: org.nameless.gamespace.settings.Hilt_PerAppSettingsActivity.1
            @Override // androidx.activity.contextaware.OnContextAvailableListener
            public void onContextAvailable(Context context) {
                Hilt_PerAppSettingsActivity.this.inject();
            }
        });
    }

    @Override // dagger.hilt.internal.GeneratedComponentManager
    public final Object generatedComponent() {
        return componentManager().generatedComponent();
    }

    protected ActivityComponentManager createComponentManager() {
        return new ActivityComponentManager(this);
    }

    public final ActivityComponentManager componentManager() {
        if (this.componentManager == null) {
            synchronized (this.componentManagerLock) {
                if (this.componentManager == null) {
                    this.componentManager = createComponentManager();
                }
            }
        }
        return this.componentManager;
    }

    protected void inject() {
        if (this.injected) {
            return;
        }
        this.injected = true;
        ((PerAppSettingsActivity_GeneratedInjector) generatedComponent()).injectPerAppSettingsActivity((PerAppSettingsActivity) UnsafeCasts.unsafeCast(this));
    }
}
