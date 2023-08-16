package com.android.settings.accessibility;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settings.R;
import com.android.settingslib.widget.TopIntroPreference;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupdesign.GlifPreferenceLayout;
import org.nameless.custom.preference.SwitchPreference;
/* loaded from: classes.dex */
public class ToggleScreenMagnificationPreferenceFragmentForSetupWizard extends ToggleScreenMagnificationPreferenceFragment {
    @Override // com.android.settings.accessibility.ToggleScreenMagnificationPreferenceFragment, com.android.settings.accessibility.ToggleFeaturePreferenceFragment, com.android.settings.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return 0;
    }

    @Override // com.android.settings.accessibility.ToggleScreenMagnificationPreferenceFragment, com.android.settings.accessibility.ToggleFeaturePreferenceFragment, com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 368;
    }

    @Override // com.android.settings.accessibility.ToggleFeaturePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        GlifPreferenceLayout glifPreferenceLayout = (GlifPreferenceLayout) view;
        AccessibilitySetupWizardUtils.updateGlifPreferenceLayout(getContext(), glifPreferenceLayout, getContext().getString(R.string.accessibility_screen_magnification_title), getContext().getString(R.string.accessibility_screen_magnification_intro_text), getContext().getDrawable(R.drawable.ic_accessibility_visibility));
        AccessibilitySetupWizardUtils.setPrimaryButton(getContext(), (FooterBarMixin) glifPreferenceLayout.getMixin(FooterBarMixin.class), R.string.done, new Runnable() { // from class: com.android.settings.accessibility.ToggleScreenMagnificationPreferenceFragmentForSetupWizard$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ToggleScreenMagnificationPreferenceFragmentForSetupWizard.this.lambda$onViewCreated$0();
            }
        });
        hidePreferenceSettingComponents();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0() {
        setResult(0);
        finish();
    }

    private void hidePreferenceSettingComponents() {
        TopIntroPreference topIntroPreference = this.mTopIntroPreference;
        if (topIntroPreference != null) {
            topIntroPreference.setVisible(false);
        }
        Preference preference = this.mSettingsPreference;
        if (preference != null) {
            preference.setVisible(false);
        }
        SwitchPreference switchPreference = this.mFollowingTypingSwitchPreference;
        if (switchPreference != null) {
            switchPreference.setVisible(false);
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return ((GlifPreferenceLayout) viewGroup).onCreateRecyclerView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("checked") && this.mToggleServiceSwitchPreference.isChecked() != arguments.getBoolean("checked")) {
            this.mMetricsFeatureProvider.action(getContext(), 368, this.mToggleServiceSwitchPreference.isChecked());
        }
        super.onStop();
    }
}
