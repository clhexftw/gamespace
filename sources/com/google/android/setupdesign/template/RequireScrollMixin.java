package com.google.android.setupdesign.template;

import android.os.Handler;
import android.os.Looper;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.template.Mixin;
/* loaded from: classes.dex */
public class RequireScrollMixin implements Mixin {
    private ScrollHandlingDelegate delegate;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private boolean requiringScrollToBottom = false;
    private boolean everScrolledToBottom = false;

    /* loaded from: classes.dex */
    public interface OnRequireScrollStateChangedListener {
        void onRequireScrollStateChanged(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface ScrollHandlingDelegate {
    }

    /* renamed from: -$$Nest$fgetlistener  reason: not valid java name */
    static /* bridge */ /* synthetic */ OnRequireScrollStateChangedListener m96$$Nest$fgetlistener(RequireScrollMixin requireScrollMixin) {
        requireScrollMixin.getClass();
        return null;
    }

    public RequireScrollMixin(TemplateLayout templateLayout) {
    }

    public void setScrollHandlingDelegate(ScrollHandlingDelegate scrollHandlingDelegate) {
        this.delegate = scrollHandlingDelegate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyScrollabilityChange(boolean z) {
        if (z == this.requiringScrollToBottom) {
            return;
        }
        if (z) {
            if (this.everScrolledToBottom) {
                return;
            }
            postScrollStateChange(true);
            this.requiringScrollToBottom = true;
            return;
        }
        postScrollStateChange(false);
        this.requiringScrollToBottom = false;
        this.everScrolledToBottom = true;
    }

    private void postScrollStateChange(final boolean z) {
        this.handler.post(new Runnable() { // from class: com.google.android.setupdesign.template.RequireScrollMixin.5
            @Override // java.lang.Runnable
            public void run() {
                if (RequireScrollMixin.m96$$Nest$fgetlistener(RequireScrollMixin.this) != null) {
                    RequireScrollMixin.m96$$Nest$fgetlistener(RequireScrollMixin.this).onRequireScrollStateChanged(z);
                }
            }
        });
    }
}
