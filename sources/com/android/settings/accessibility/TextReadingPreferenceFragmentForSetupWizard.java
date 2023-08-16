package com.android.settings.accessibility;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settings.R;
import com.android.settingslib.Utils;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupdesign.GlifPreferenceLayout;
/* loaded from: classes.dex */
public class TextReadingPreferenceFragmentForSetupWizard extends TextReadingPreferenceFragment {
    @Override // com.android.settings.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return 0;
    }

    @Override // com.android.settings.accessibility.TextReadingPreferenceFragment, com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1915;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        GlifPreferenceLayout glifPreferenceLayout = (GlifPreferenceLayout) view;
        String string = getContext().getString(R.string.accessibility_text_reading_options_title);
        Drawable drawable = getContext().getDrawable(R.drawable.ic_accessibility_visibility);
        drawable.setTintList(Utils.getColorAttr(getContext(), 16843827));
        AccessibilitySetupWizardUtils.updateGlifPreferenceLayout(getContext(), glifPreferenceLayout, string, null, drawable);
        FooterBarMixin footerBarMixin = (FooterBarMixin) glifPreferenceLayout.getMixin(FooterBarMixin.class);
        AccessibilitySetupWizardUtils.setPrimaryButton(getContext(), footerBarMixin, R.string.done, new Runnable() { // from class: com.android.settings.accessibility.TextReadingPreferenceFragmentForSetupWizard$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                TextReadingPreferenceFragmentForSetupWizard.this.lambda$onViewCreated$0();
            }
        });
        AccessibilitySetupWizardUtils.setSecondaryButton(getContext(), footerBarMixin, R.string.accessibility_text_reading_reset_button_title, new Runnable() { // from class: com.android.settings.accessibility.TextReadingPreferenceFragmentForSetupWizard$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                TextReadingPreferenceFragmentForSetupWizard.this.lambda$onViewCreated$1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0() {
        setResult(0);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$1() {
        showDialog(1009);
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return ((GlifPreferenceLayout) viewGroup).onCreateRecyclerView(layoutInflater, viewGroup, bundle);
    }
}
