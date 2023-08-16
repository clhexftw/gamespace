package com.android.settingslib.suggestions;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.service.settings.suggestions.Suggestion;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import java.util.List;
@Deprecated
/* loaded from: classes.dex */
public class SuggestionControllerMixin implements LifecycleObserver, LoaderManager.LoaderCallbacks<List<Suggestion>> {
    private final Context mContext;
    private boolean mSuggestionLoaded;

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        throw null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        throw null;
    }

    @Override // android.app.LoaderManager.LoaderCallbacks
    public Loader<List<Suggestion>> onCreateLoader(int i, Bundle bundle) {
        if (i == 42) {
            this.mSuggestionLoaded = false;
            return new SuggestionLoader(this.mContext, null);
        }
        throw new IllegalArgumentException("This loader id is not supported " + i);
    }

    @Override // android.app.LoaderManager.LoaderCallbacks
    public void onLoadFinished(Loader<List<Suggestion>> loader, List<Suggestion> list) {
        this.mSuggestionLoaded = true;
        throw null;
    }

    @Override // android.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<List<Suggestion>> loader) {
        this.mSuggestionLoaded = false;
    }
}
