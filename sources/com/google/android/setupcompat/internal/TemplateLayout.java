package com.google.android.setupcompat.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.annotation.Keep;
import com.google.android.setupcompat.R$attr;
import com.google.android.setupcompat.R$styleable;
import com.google.android.setupcompat.template.Mixin;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class TemplateLayout extends FrameLayout {
    private ViewGroup container;
    private final Map<Class<? extends Mixin>, Mixin> mixins;
    private ViewTreeObserver.OnPreDrawListener preDrawListener;
    private float xFraction;

    protected void onBeforeTemplateInflated(AttributeSet attributeSet, int i) {
    }

    protected void onTemplateInflated() {
    }

    public TemplateLayout(Context context, int i, int i2) {
        super(context);
        this.mixins = new HashMap();
        init(i, i2, null, R$attr.sucLayoutTheme);
    }

    public TemplateLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mixins = new HashMap();
        init(0, 0, attributeSet, R$attr.sucLayoutTheme);
    }

    @TargetApi(11)
    public TemplateLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mixins = new HashMap();
        init(0, 0, attributeSet, i);
    }

    private void init(int i, int i2, AttributeSet attributeSet, int i3) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.SucTemplateLayout, i3, 0);
        if (i == 0) {
            i = obtainStyledAttributes.getResourceId(R$styleable.SucTemplateLayout_android_layout, 0);
        }
        if (i2 == 0) {
            i2 = obtainStyledAttributes.getResourceId(R$styleable.SucTemplateLayout_sucContainer, 0);
        }
        onBeforeTemplateInflated(attributeSet, i3);
        inflateTemplate(i, i2);
        obtainStyledAttributes.recycle();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <M extends Mixin> void registerMixin(Class<M> cls, M m) {
        this.mixins.put(cls, m);
    }

    public <T extends View> T findManagedViewById(int i) {
        return (T) findViewById(i);
    }

    public <M extends Mixin> M getMixin(Class<M> cls) {
        return (M) this.mixins.get(cls);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        this.container.addView(view, i, layoutParams);
    }

    private void addViewInternal(View view) {
        super.addView(view, -1, generateDefaultLayoutParams());
    }

    private void inflateTemplate(int i, int i2) {
        addViewInternal(onInflateTemplate(LayoutInflater.from(getContext()), i));
        ViewGroup findContainer = findContainer(i2);
        this.container = findContainer;
        if (findContainer == null) {
            throw new IllegalArgumentException("Container cannot be null in TemplateLayout");
        }
        onTemplateInflated();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final View inflateTemplate(LayoutInflater layoutInflater, int i, int i2) {
        if (i2 == 0) {
            throw new IllegalArgumentException("android:layout not specified for TemplateLayout");
        }
        if (i != 0) {
            layoutInflater = LayoutInflater.from(new FallbackThemeWrapper(layoutInflater.getContext(), i));
        }
        return layoutInflater.inflate(i2, (ViewGroup) this, false);
    }

    protected View onInflateTemplate(LayoutInflater layoutInflater, int i) {
        return inflateTemplate(layoutInflater, 0, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ViewGroup findContainer(int i) {
        return (ViewGroup) findViewById(i);
    }

    @Keep
    @TargetApi(11)
    public void setXFraction(float f) {
        this.xFraction = f;
        int width = getWidth();
        if (width != 0) {
            setTranslationX(width * f);
        } else if (this.preDrawListener == null) {
            this.preDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: com.google.android.setupcompat.internal.TemplateLayout.1
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    TemplateLayout.this.getViewTreeObserver().removeOnPreDrawListener(TemplateLayout.this.preDrawListener);
                    TemplateLayout templateLayout = TemplateLayout.this;
                    templateLayout.setXFraction(templateLayout.xFraction);
                    return true;
                }
            };
            getViewTreeObserver().addOnPreDrawListener(this.preDrawListener);
        }
    }

    @Keep
    @TargetApi(11)
    public float getXFraction() {
        return this.xFraction;
    }
}
