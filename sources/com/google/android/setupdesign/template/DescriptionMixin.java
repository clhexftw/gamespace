package com.google.android.setupdesign.template;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.template.Mixin;
import com.google.android.setupdesign.R$id;
import com.google.android.setupdesign.R$styleable;
import com.google.android.setupdesign.util.HeaderAreaStyler;
import com.google.android.setupdesign.util.PartnerStyleHelper;
/* loaded from: classes2.dex */
public class DescriptionMixin implements Mixin {
    private final TemplateLayout templateLayout;

    public DescriptionMixin(TemplateLayout templateLayout, AttributeSet attributeSet, int i) {
        this.templateLayout = templateLayout;
        TypedArray obtainStyledAttributes = templateLayout.getContext().obtainStyledAttributes(attributeSet, R$styleable.SudDescriptionMixin, i, 0);
        CharSequence text = obtainStyledAttributes.getText(R$styleable.SudDescriptionMixin_sudDescriptionText);
        if (text != null) {
            setText(text);
        }
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R$styleable.SudDescriptionMixin_sudDescriptionTextColor);
        if (colorStateList != null) {
            setTextColor(colorStateList);
        }
        obtainStyledAttributes.recycle();
    }

    public void tryApplyPartnerCustomizationStyle() {
        TextView textView = (TextView) this.templateLayout.findManagedViewById(R$id.sud_layout_subtitle);
        if (textView == null || !PartnerStyleHelper.shouldApplyPartnerResource(this.templateLayout)) {
            return;
        }
        HeaderAreaStyler.applyPartnerCustomizationDescriptionHeavyStyle(textView);
    }

    public TextView getTextView() {
        return (TextView) this.templateLayout.findManagedViewById(R$id.sud_layout_subtitle);
    }

    public void setText(int i) {
        TextView textView = getTextView();
        if (textView != null && i != 0) {
            textView.setText(i);
            setVisibility(0);
            return;
        }
        Log.w("DescriptionMixin", "Fail to set text due to either invalid resource id or text view not found.");
    }

    public void setText(CharSequence charSequence) {
        TextView textView = getTextView();
        if (textView != null) {
            textView.setText(charSequence);
            setVisibility(0);
        }
    }

    public CharSequence getText() {
        TextView textView = getTextView();
        if (textView != null) {
            return textView.getText();
        }
        return null;
    }

    public void setVisibility(int i) {
        TextView textView = getTextView();
        if (textView != null) {
            textView.setVisibility(i);
        }
    }

    public void setTextColor(ColorStateList colorStateList) {
        TextView textView = getTextView();
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }
}
