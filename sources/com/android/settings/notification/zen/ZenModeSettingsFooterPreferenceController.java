package com.android.settings.notification.zen;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.ListFormatter;
import android.net.Uri;
import android.os.Bundle;
import android.service.notification.ZenModeConfig;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settings.notification.zen.ZenModeSettings;
import com.android.settings.utils.AnnotationSpan;
import com.android.settingslib.core.lifecycle.Lifecycle;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
/* loaded from: classes.dex */
public class ZenModeSettingsFooterPreferenceController extends AbstractZenModePreferenceController {
    private FragmentManager mFragment;

    @Override // com.android.settings.notification.zen.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "footer_preference";
    }

    public ZenModeSettingsFooterPreferenceController(Context context, Lifecycle lifecycle, FragmentManager fragmentManager) {
        super(context, "footer_preference", lifecycle);
        this.mFragment = fragmentManager;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        int zenMode = getZenMode();
        return zenMode == 1 || zenMode == 2 || zenMode == 3;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        boolean isAvailable = isAvailable();
        preference.setVisible(isAvailable);
        if (isAvailable) {
            preference.setTitle(getFooterText());
        }
    }

    protected CharSequence getFooterText() {
        ZenModeConfig zenModeConfig = getZenModeConfig();
        if (!Objects.equals(this.mBackend.getConsolidatedPolicy(), zenModeConfig.toNotificationPolicy())) {
            List<ZenModeConfig.ZenRule> activeRules = getActiveRules(zenModeConfig);
            ArrayList arrayList = new ArrayList();
            for (ZenModeConfig.ZenRule zenRule : activeRules) {
                String str = zenRule.name;
                if (str != null) {
                    arrayList.add(str);
                }
            }
            if (arrayList.size() > 0) {
                String format = ListFormatter.getInstance().format(arrayList);
                if (!format.isEmpty()) {
                    return TextUtils.concat(this.mContext.getResources().getString(R.string.zen_mode_settings_dnd_custom_settings_footer, format), AnnotationSpan.linkify(this.mContext.getResources().getText(R.string.zen_mode_settings_dnd_custom_settings_footer_link), new AnnotationSpan.LinkInfo("link", new View.OnClickListener() { // from class: com.android.settings.notification.zen.ZenModeSettingsFooterPreferenceController.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            ZenModeSettingsFooterPreferenceController.this.showCustomSettingsDialog();
                        }
                    })));
                }
            }
        }
        return getDefaultPolicyFooter(zenModeConfig);
    }

    private String getDefaultPolicyFooter(ZenModeConfig zenModeConfig) {
        ZenModeConfig.ZenRule zenRule = zenModeConfig.manualRule;
        String str = "";
        long j = -1;
        if (zenRule != null) {
            Uri uri = zenRule.conditionId;
            String str2 = zenRule.enabler;
            if (str2 != null) {
                String ownerCaption = AbstractZenModePreferenceController.mZenModeConfigWrapper.getOwnerCaption(str2);
                if (!ownerCaption.isEmpty()) {
                    str = this.mContext.getString(R.string.zen_mode_settings_dnd_automatic_rule_app, ownerCaption);
                }
            } else if (uri == null) {
                return this.mContext.getString(R.string.zen_mode_settings_dnd_manual_indefinite);
            } else {
                j = AbstractZenModePreferenceController.mZenModeConfigWrapper.parseManualRuleTime(uri);
                if (j > 0) {
                    str = this.mContext.getString(R.string.zen_mode_settings_dnd_manual_end_time, AbstractZenModePreferenceController.mZenModeConfigWrapper.getFormattedTime(j, this.mContext.getUserId()));
                }
            }
        }
        for (ZenModeConfig.ZenRule zenRule2 : zenModeConfig.automaticRules.values()) {
            if (zenRule2.isAutomaticActive()) {
                if (!AbstractZenModePreferenceController.mZenModeConfigWrapper.isTimeRule(zenRule2.conditionId)) {
                    return this.mContext.getString(R.string.zen_mode_settings_dnd_automatic_rule, zenRule2.name);
                }
                long parseAutomaticRuleEndTime = AbstractZenModePreferenceController.mZenModeConfigWrapper.parseAutomaticRuleEndTime(zenRule2.conditionId);
                if (parseAutomaticRuleEndTime > j) {
                    str = this.mContext.getString(R.string.zen_mode_settings_dnd_automatic_rule, zenRule2.name);
                    j = parseAutomaticRuleEndTime;
                }
            }
        }
        return str;
    }

    private List<ZenModeConfig.ZenRule> getActiveRules(ZenModeConfig zenModeConfig) {
        ArrayList arrayList = new ArrayList();
        ZenModeConfig.ZenRule zenRule = zenModeConfig.manualRule;
        if (zenRule != null) {
            arrayList.add(zenRule);
        }
        for (ZenModeConfig.ZenRule zenRule2 : zenModeConfig.automaticRules.values()) {
            if (zenRule2.isAutomaticActive()) {
                arrayList.add(zenRule2);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCustomSettingsDialog() {
        ZenCustomSettingsDialog zenCustomSettingsDialog = new ZenCustomSettingsDialog();
        zenCustomSettingsDialog.setNotificationPolicy(this.mBackend.getConsolidatedPolicy());
        zenCustomSettingsDialog.show(this.mFragment, ZenCustomSettingsDialog.class.getName());
    }

    /* loaded from: classes.dex */
    public static class ZenCustomSettingsDialog extends InstrumentedDialogFragment {
        private String KEY_POLICY = "policy";
        private NotificationManager.Policy mPolicy;
        private ZenModeSettings.SummaryBuilder mSummaryBuilder;

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 1612;
        }

        public void setNotificationPolicy(NotificationManager.Policy policy) {
            this.mPolicy = policy;
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            NotificationManager.Policy policy;
            final FragmentActivity activity = getActivity();
            if (bundle != null && (policy = (NotificationManager.Policy) bundle.getParcelable(this.KEY_POLICY)) != null) {
                this.mPolicy = policy;
            }
            this.mSummaryBuilder = new ZenModeSettings.SummaryBuilder(activity);
            final AlertDialog create = new AlertDialog.Builder(activity).setTitle(R.string.zen_custom_settings_dialog_title).setNeutralButton(R.string.zen_custom_settings_dialog_review_schedule, new DialogInterface.OnClickListener() { // from class: com.android.settings.notification.zen.ZenModeSettingsFooterPreferenceController.ZenCustomSettingsDialog.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    new SubSettingLauncher(activity).setDestination(ZenModeAutomationSettings.class.getName()).setSourceMetricsCategory(142).launch();
                }
            }).setPositiveButton(R.string.zen_custom_settings_dialog_ok, (DialogInterface.OnClickListener) null).setView(LayoutInflater.from(activity).inflate((XmlPullParser) activity.getResources().getLayout(R.layout.zen_custom_settings_dialog), (ViewGroup) null, false)).create();
            create.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.android.settings.notification.zen.ZenModeSettingsFooterPreferenceController.ZenCustomSettingsDialog.2
                @Override // android.content.DialogInterface.OnShowListener
                public void onShow(DialogInterface dialogInterface) {
                    ((TextView) create.findViewById(R.id.zen_custom_settings_dialog_calls_allow)).setText(ZenCustomSettingsDialog.this.mSummaryBuilder.getCallsSettingSummary(ZenCustomSettingsDialog.this.mPolicy));
                    ((TextView) create.findViewById(R.id.zen_custom_settings_dialog_messages_allow)).setText(ZenCustomSettingsDialog.this.mSummaryBuilder.getMessagesSettingSummary(ZenCustomSettingsDialog.this.mPolicy));
                    ZenCustomSettingsDialog zenCustomSettingsDialog = ZenCustomSettingsDialog.this;
                    ((TextView) create.findViewById(R.id.zen_custom_settings_dialog_alarms_allow)).setText(zenCustomSettingsDialog.getAllowRes(zenCustomSettingsDialog.mPolicy.allowAlarms()));
                    ZenCustomSettingsDialog zenCustomSettingsDialog2 = ZenCustomSettingsDialog.this;
                    ((TextView) create.findViewById(R.id.zen_custom_settings_dialog_media_allow)).setText(zenCustomSettingsDialog2.getAllowRes(zenCustomSettingsDialog2.mPolicy.allowMedia()));
                    ZenCustomSettingsDialog zenCustomSettingsDialog3 = ZenCustomSettingsDialog.this;
                    ((TextView) create.findViewById(R.id.zen_custom_settings_dialog_system_allow)).setText(zenCustomSettingsDialog3.getAllowRes(zenCustomSettingsDialog3.mPolicy.allowSystem()));
                    ZenCustomSettingsDialog zenCustomSettingsDialog4 = ZenCustomSettingsDialog.this;
                    ((TextView) create.findViewById(R.id.zen_custom_settings_dialog_reminders_allow)).setText(zenCustomSettingsDialog4.getAllowRes(zenCustomSettingsDialog4.mPolicy.allowReminders()));
                    ZenCustomSettingsDialog zenCustomSettingsDialog5 = ZenCustomSettingsDialog.this;
                    ((TextView) create.findViewById(R.id.zen_custom_settings_dialog_events_allow)).setText(zenCustomSettingsDialog5.getAllowRes(zenCustomSettingsDialog5.mPolicy.allowEvents()));
                    ((TextView) create.findViewById(R.id.zen_custom_settings_dialog_show_notifications)).setText(ZenCustomSettingsDialog.this.mSummaryBuilder.getBlockedEffectsSummary(ZenCustomSettingsDialog.this.mPolicy));
                }
            });
            return create;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getAllowRes(boolean z) {
            return z ? R.string.zen_mode_sound_summary_on : R.string.switch_off_text;
        }

        @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putParcelable(this.KEY_POLICY, this.mPolicy);
        }
    }
}
