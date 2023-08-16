package com.android.settingslib.widget;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
/* loaded from: classes.dex */
public class FooterPreference extends Preference {
    private CharSequence mContentDescription;
    int mIconVisibility;
    View.OnClickListener mLearnMoreListener;
    private FooterLearnMoreSpan mLearnMoreSpan;
    private CharSequence mLearnMoreText;

    public FooterPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R$attr.footerPreferenceStyle);
        this.mIconVisibility = 0;
        init();
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        TextView textView = (TextView) preferenceViewHolder.itemView.findViewById(16908310);
        if (!TextUtils.isEmpty(this.mContentDescription)) {
            textView.setContentDescription(this.mContentDescription);
        }
        TextView textView2 = (TextView) preferenceViewHolder.itemView.findViewById(R$id.settingslib_learn_more);
        if (textView2 != null && this.mLearnMoreListener != null) {
            textView2.setVisibility(0);
            if (TextUtils.isEmpty(this.mLearnMoreText)) {
                this.mLearnMoreText = textView2.getText();
            } else {
                textView2.setText(this.mLearnMoreText);
            }
            SpannableString spannableString = new SpannableString(this.mLearnMoreText);
            FooterLearnMoreSpan footerLearnMoreSpan = this.mLearnMoreSpan;
            if (footerLearnMoreSpan != null) {
                spannableString.removeSpan(footerLearnMoreSpan);
            }
            FooterLearnMoreSpan footerLearnMoreSpan2 = new FooterLearnMoreSpan(this.mLearnMoreListener);
            this.mLearnMoreSpan = footerLearnMoreSpan2;
            spannableString.setSpan(footerLearnMoreSpan2, 0, spannableString.length(), 0);
            textView2.setText(spannableString);
        } else {
            textView2.setVisibility(8);
        }
        preferenceViewHolder.itemView.findViewById(R$id.icon_frame).setVisibility(this.mIconVisibility);
    }

    @Override // androidx.preference.Preference
    public void setSummary(CharSequence charSequence) {
        setTitle(charSequence);
    }

    @Override // androidx.preference.Preference
    public CharSequence getSummary() {
        return getTitle();
    }

    CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    private void init() {
        setLayoutResource(R$layout.preference_footer);
        if (getIcon() == null) {
            setIcon(R$drawable.settingslib_ic_info_outline_24);
        }
        setOrder(2147483646);
        if (TextUtils.isEmpty(getKey())) {
            setKey("footer_preference");
        }
        setSelectable(false);
    }

    /* loaded from: classes.dex */
    static class FooterLearnMoreSpan extends URLSpan {
        private final View.OnClickListener mClickListener;

        FooterLearnMoreSpan(View.OnClickListener onClickListener) {
            super("");
            this.mClickListener = onClickListener;
        }

        @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
        public void onClick(View view) {
            View.OnClickListener onClickListener = this.mClickListener;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }
}
