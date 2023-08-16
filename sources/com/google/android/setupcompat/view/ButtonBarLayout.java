package com.google.android.setupcompat.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.setupcompat.R$id;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupcompat.template.FooterActionButton;
import com.google.android.setupcompat.util.Logger;
import java.util.ArrayList;
import java.util.Collections;
/* loaded from: classes.dex */
public class ButtonBarLayout extends LinearLayout {
    private static final Logger LOG = new Logger(ButtonBarLayout.class);
    private int originalPaddingLeft;
    private int originalPaddingRight;
    private boolean stacked;

    public ButtonBarLayout(Context context) {
        super(context);
        this.stacked = false;
    }

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.stacked = false;
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        boolean z;
        int i3;
        int size = View.MeasureSpec.getSize(i);
        setStacked(false);
        boolean z2 = true;
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            i3 = View.MeasureSpec.makeMeasureSpec(0, 0);
            z = true;
        } else {
            z = false;
            i3 = i;
        }
        super.onMeasure(i3, i2);
        if (isFooterButtonsEventlyWeighted(getContext()) || getMeasuredWidth() <= size) {
            z2 = z;
        } else {
            setStacked(true);
        }
        if (z2) {
            super.onMeasure(i, i2);
        }
    }

    private void setStacked(boolean z) {
        if (this.stacked == z) {
            return;
        }
        this.stacked = z;
        int childCount = getChildCount();
        int i = 0;
        boolean z2 = false;
        int i2 = 0;
        while (true) {
            if (i >= childCount) {
                break;
            }
            View childAt = getChildAt(i);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            if (z) {
                childAt.setTag(R$id.suc_customization_original_weight, Float.valueOf(layoutParams.weight));
                layoutParams.weight = 0.0f;
                layoutParams.leftMargin = 0;
            } else {
                Float f = (Float) childAt.getTag(R$id.suc_customization_original_weight);
                if (f != null) {
                    layoutParams.weight = f.floatValue();
                    z2 = z2;
                } else {
                    z2 = true;
                }
                if (isPrimaryButtonStyle(childAt)) {
                    i2++;
                }
            }
            childAt.setLayoutParams(layoutParams);
            i++;
            z2 = z2;
        }
        setOrientation(z ? 1 : 0);
        if (z2) {
            LOG.w("Reorder the FooterActionButtons in the container");
            ArrayList<View> childViewsInContainerInOrder = getChildViewsInContainerInOrder(childCount, i2 <= 1);
            for (int i3 = 0; i3 < childCount; i3++) {
                View view = childViewsInContainerInOrder.get(i3);
                if (view != null) {
                    bringChildToFront(view);
                }
            }
        } else {
            for (int i4 = childCount - 1; i4 >= 0; i4--) {
                bringChildToFront(getChildAt(i4));
            }
        }
        if (z) {
            setHorizontalGravity(17);
            this.originalPaddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            this.originalPaddingRight = paddingRight;
            int max = Math.max(this.originalPaddingLeft, paddingRight);
            setPadding(max, getPaddingTop(), max, getPaddingBottom());
            return;
        }
        setPadding(this.originalPaddingLeft, getPaddingTop(), this.originalPaddingRight, getPaddingBottom());
    }

    private boolean isPrimaryButtonStyle(View view) {
        return (view instanceof FooterActionButton) && ((FooterActionButton) view).isPrimaryButtonStyle();
    }

    private ArrayList<View> getChildViewsInContainerInOrder(int i, boolean z) {
        ArrayList<View> arrayList = new ArrayList<>();
        if (z) {
            arrayList.addAll(Collections.nCopies(3, null));
        }
        for (int i2 = 0; i2 < i; i2++) {
            View childAt = getChildAt(i2);
            if (z) {
                if (isPrimaryButtonStyle(childAt)) {
                    arrayList.set(2, childAt);
                } else if (!(childAt instanceof FooterActionButton)) {
                    arrayList.set(1, childAt);
                } else {
                    arrayList.set(0, childAt);
                }
            } else if (!(childAt instanceof FooterActionButton)) {
                arrayList.add(1, childAt);
            } else {
                arrayList.add(getChildAt(i2));
            }
        }
        return arrayList;
    }

    private boolean isFooterButtonsEventlyWeighted(Context context) {
        int childCount = getChildCount();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((childAt instanceof FooterActionButton) && ((FooterActionButton) childAt).isPrimaryButtonStyle()) {
                i++;
            }
        }
        return i == 2 && context.getResources().getConfiguration().smallestScreenWidthDp >= 600 && PartnerConfigHelper.shouldApplyExtendedPartnerConfig(context);
    }
}
