package com.android.settings.accessibility;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import com.android.settings.R;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupdesign.GlifPreferenceLayout;
import com.google.android.setupdesign.util.ThemeHelper;
/* loaded from: classes.dex */
class AccessibilitySetupWizardUtils {
    public static void updateGlifPreferenceLayout(Context context, GlifPreferenceLayout glifPreferenceLayout, CharSequence charSequence, CharSequence charSequence2, Drawable drawable) {
        LinearLayout linearLayout;
        glifPreferenceLayout.setHeaderText(charSequence);
        glifPreferenceLayout.setDescriptionText(charSequence2);
        glifPreferenceLayout.setIcon(drawable);
        glifPreferenceLayout.setDividerInsets(Integer.MAX_VALUE, 0);
        if (!ThemeHelper.shouldApplyMaterialYouStyle(context) || (linearLayout = (LinearLayout) glifPreferenceLayout.findManagedViewById(R.id.sud_layout_header)) == null) {
            return;
        }
        linearLayout.setPadding(0, glifPreferenceLayout.getPaddingTop(), 0, glifPreferenceLayout.getPaddingBottom());
    }

    public static void setPrimaryButton(Context context, FooterBarMixin footerBarMixin, int i, final Runnable runnable) {
        footerBarMixin.setPrimaryButton(new FooterButton.Builder(context).setText(i).setListener(new View.OnClickListener() { // from class: com.android.settings.accessibility.AccessibilitySetupWizardUtils$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                runnable.run();
            }
        }).setButtonType(4).setTheme(R.style.SudGlifButton_Primary).build());
    }

    public static void setSecondaryButton(Context context, FooterBarMixin footerBarMixin, int i, final Runnable runnable) {
        footerBarMixin.setSecondaryButton(new FooterButton.Builder(context).setText(i).setListener(new View.OnClickListener() { // from class: com.android.settings.accessibility.AccessibilitySetupWizardUtils$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                runnable.run();
            }
        }).setButtonType(3).setTheme(R.style.SudGlifButton_Secondary).build());
    }
}
