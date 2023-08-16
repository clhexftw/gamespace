package dagger.hilt.android.internal.modules;

import android.content.Context;
/* loaded from: classes.dex */
public final class ApplicationContextModule {
    private final Context applicationContext;

    public ApplicationContextModule(Context context) {
        this.applicationContext = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Context provideContext() {
        return this.applicationContext;
    }
}
