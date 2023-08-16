package dagger.hilt.android.internal.managers;

import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager;
import javax.inject.Provider;
/* loaded from: classes.dex */
public final class ActivityRetainedComponentManager_Lifecycle_Factory implements Provider {

    /* loaded from: classes.dex */
    private static final class InstanceHolder {
        private static final ActivityRetainedComponentManager_Lifecycle_Factory INSTANCE = new ActivityRetainedComponentManager_Lifecycle_Factory();
    }

    @Override // javax.inject.Provider
    public ActivityRetainedComponentManager.Lifecycle get() {
        return newInstance();
    }

    public static ActivityRetainedComponentManager_Lifecycle_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ActivityRetainedComponentManager.Lifecycle newInstance() {
        return new ActivityRetainedComponentManager.Lifecycle();
    }
}
