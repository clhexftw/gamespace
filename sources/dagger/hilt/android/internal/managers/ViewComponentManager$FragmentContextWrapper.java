package dagger.hilt.android.internal.managers;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import dagger.hilt.internal.Preconditions;
/* loaded from: classes.dex */
public final class ViewComponentManager$FragmentContextWrapper extends ContextWrapper {
    private LayoutInflater baseInflater;
    private Fragment fragment;
    private final LifecycleEventObserver fragmentLifecycleObserver;
    private LayoutInflater inflater;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewComponentManager$FragmentContextWrapper(Context context, Fragment fragment) {
        super((Context) Preconditions.checkNotNull(context));
        LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper.1
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    ViewComponentManager$FragmentContextWrapper.this.fragment = null;
                    ViewComponentManager$FragmentContextWrapper.this.baseInflater = null;
                    ViewComponentManager$FragmentContextWrapper.this.inflater = null;
                }
            }
        };
        this.fragmentLifecycleObserver = lifecycleEventObserver;
        this.baseInflater = null;
        Fragment fragment2 = (Fragment) Preconditions.checkNotNull(fragment);
        this.fragment = fragment2;
        fragment2.getLifecycle().addObserver(lifecycleEventObserver);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewComponentManager$FragmentContextWrapper(LayoutInflater layoutInflater, Fragment fragment) {
        super((Context) Preconditions.checkNotNull(((LayoutInflater) Preconditions.checkNotNull(layoutInflater)).getContext()));
        LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper.1
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    ViewComponentManager$FragmentContextWrapper.this.fragment = null;
                    ViewComponentManager$FragmentContextWrapper.this.baseInflater = null;
                    ViewComponentManager$FragmentContextWrapper.this.inflater = null;
                }
            }
        };
        this.fragmentLifecycleObserver = lifecycleEventObserver;
        this.baseInflater = layoutInflater;
        Fragment fragment2 = (Fragment) Preconditions.checkNotNull(fragment);
        this.fragment = fragment2;
        fragment2.getLifecycle().addObserver(lifecycleEventObserver);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.inflater == null) {
            if (this.baseInflater == null) {
                this.baseInflater = (LayoutInflater) getBaseContext().getSystemService("layout_inflater");
            }
            this.inflater = this.baseInflater.cloneInContext(this);
        }
        return this.inflater;
    }
}
