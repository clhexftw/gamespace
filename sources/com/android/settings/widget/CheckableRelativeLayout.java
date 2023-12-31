package com.android.settings.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.RelativeLayout;
/* loaded from: classes.dex */
public class CheckableRelativeLayout extends RelativeLayout implements Checkable {
    private Checkable mCheckable;
    private View mCheckableChild;
    private boolean mChecked;

    public CheckableRelativeLayout(Context context) {
        super(context);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        View findFirstCheckableView = findFirstCheckableView(this);
        this.mCheckableChild = findFirstCheckableView;
        if (findFirstCheckableView != null) {
            findFirstCheckableView.setClickable(false);
            this.mCheckableChild.setFocusable(false);
            this.mCheckableChild.setImportantForAccessibility(2);
            Checkable checkable = (Checkable) this.mCheckableChild;
            this.mCheckable = checkable;
            checkable.setChecked(isChecked());
            setStateDescriptionIfNeeded();
        }
        super.onFinishInflate();
    }

    private static View findFirstCheckableView(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof Checkable) {
                return childAt;
            }
            if (childAt instanceof ViewGroup) {
                findFirstCheckableView((ViewGroup) childAt);
            }
        }
        return null;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.mChecked != z) {
            this.mChecked = z;
            Checkable checkable = this.mCheckable;
            if (checkable != null) {
                checkable.setChecked(z);
            }
        }
        setStateDescriptionIfNeeded();
        notifyViewAccessibilityStateChangedIfNeeded(0);
    }

    private void setStateDescriptionIfNeeded() {
        View view = this.mCheckableChild;
        if (view == null) {
            return;
        }
        setStateDescription(view.getStateDescription());
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.mChecked;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.mChecked);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setChecked(this.mChecked);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setChecked(this.mChecked);
    }
}
