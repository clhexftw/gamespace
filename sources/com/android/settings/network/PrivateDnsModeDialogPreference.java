package com.android.settings.network;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivitySettingsManager;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.R;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.utils.AnnotationSpan;
import com.android.settingslib.CustomDialogPreferenceCompat;
import com.android.settingslib.HelpUtils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.google.common.net.InternetDomainName;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class PrivateDnsModeDialogPreference extends CustomDialogPreferenceCompat implements DialogInterface.OnClickListener, RadioGroup.OnCheckedChangeListener, TextWatcher {
    static final String HOSTNAME_KEY = "private_dns_specifier";
    static final String MODE_KEY = "private_dns_mode";
    private static final Map<Integer, Integer> PRIVATE_DNS_MAP;
    EditText mEditText;
    int mMode;
    RadioGroup mRadioGroup;
    private final AnnotationSpan.LinkInfo mUrlLinkInfo;

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    static {
        HashMap hashMap = new HashMap();
        PRIVATE_DNS_MAP = hashMap;
        hashMap.put(1, Integer.valueOf(R.id.private_dns_mode_off));
        hashMap.put(2, Integer.valueOf(R.id.private_dns_mode_opportunistic));
        hashMap.put(3, Integer.valueOf(R.id.private_dns_mode_provider));
    }

    public static String getHostnameFromSettings(ContentResolver contentResolver) {
        return Settings.Global.getString(contentResolver, HOSTNAME_KEY);
    }

    public PrivateDnsModeDialogPreference(Context context) {
        super(context);
        this.mUrlLinkInfo = new AnnotationSpan.LinkInfo("url", new View.OnClickListener() { // from class: com.android.settings.network.PrivateDnsModeDialogPreference$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivateDnsModeDialogPreference.lambda$new$0(view);
            }
        });
    }

    public PrivateDnsModeDialogPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mUrlLinkInfo = new AnnotationSpan.LinkInfo("url", new View.OnClickListener() { // from class: com.android.settings.network.PrivateDnsModeDialogPreference$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivateDnsModeDialogPreference.lambda$new$0(view);
            }
        });
    }

    public PrivateDnsModeDialogPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mUrlLinkInfo = new AnnotationSpan.LinkInfo("url", new View.OnClickListener() { // from class: com.android.settings.network.PrivateDnsModeDialogPreference$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivateDnsModeDialogPreference.lambda$new$0(view);
            }
        });
    }

    public PrivateDnsModeDialogPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mUrlLinkInfo = new AnnotationSpan.LinkInfo("url", new View.OnClickListener() { // from class: com.android.settings.network.PrivateDnsModeDialogPreference$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivateDnsModeDialogPreference.lambda$new$0(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$0(View view) {
        Context context = view.getContext();
        Intent helpIntent = HelpUtils.getHelpIntent(context, context.getString(R.string.help_uri_private_dns), context.getClass().getName());
        if (helpIntent != null) {
            try {
                view.startActivityForResult(helpIntent, 0);
            } catch (ActivityNotFoundException unused) {
                Log.w("PrivateDnsModeDialog", "Activity was not found for intent, " + helpIntent.toString());
            }
        }
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        if (isDisabledByAdmin()) {
            preferenceViewHolder.itemView.setEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.CustomDialogPreferenceCompat
    public void onBindDialogView(View view) {
        Context context = getContext();
        ContentResolver contentResolver = context.getContentResolver();
        this.mMode = ConnectivitySettingsManager.getPrivateDnsMode(context);
        EditText editText = (EditText) view.findViewById(R.id.private_dns_mode_provider_hostname);
        this.mEditText = editText;
        editText.addTextChangedListener(this);
        this.mEditText.setText(getHostnameFromSettings(contentResolver));
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.private_dns_radio_group);
        this.mRadioGroup = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        RadioGroup radioGroup2 = this.mRadioGroup;
        Map<Integer, Integer> map = PRIVATE_DNS_MAP;
        Integer valueOf = Integer.valueOf(this.mMode);
        int i = R.id.private_dns_mode_opportunistic;
        radioGroup2.check(map.getOrDefault(valueOf, Integer.valueOf(i)).intValue());
        ((RadioButton) view.findViewById(R.id.private_dns_mode_off)).setText(R.string.private_dns_mode_off);
        ((RadioButton) view.findViewById(i)).setText(R.string.private_dns_mode_opportunistic);
        ((RadioButton) view.findViewById(R.id.private_dns_mode_provider)).setText(R.string.private_dns_mode_provider);
        TextView textView = (TextView) view.findViewById(R.id.private_dns_help_info);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        AnnotationSpan.LinkInfo linkInfo = new AnnotationSpan.LinkInfo(context, "url", HelpUtils.getHelpIntent(context, context.getString(R.string.help_uri_private_dns), context.getClass().getName()));
        if (linkInfo.isActionable()) {
            textView.setText(AnnotationSpan.linkify(context.getText(R.string.private_dns_help_message), linkInfo));
        } else {
            textView.setVisibility(8);
        }
    }

    @Override // com.android.settingslib.CustomDialogPreferenceCompat
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            Context context = getContext();
            if (this.mMode == 3) {
                ConnectivitySettingsManager.setPrivateDnsHostname(context, this.mEditText.getText().toString());
            }
            FeatureFactory.getFactory(context).getMetricsFeatureProvider().action(context, 1249, this.mMode);
            ConnectivitySettingsManager.setPrivateDnsMode(context, this.mMode);
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.private_dns_mode_off) {
            this.mMode = 1;
        } else if (i == R.id.private_dns_mode_opportunistic) {
            this.mMode = 2;
        } else if (i == R.id.private_dns_mode_provider) {
            this.mMode = 3;
        }
        updateDialogInfo();
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        updateDialogInfo();
    }

    @Override // androidx.preference.Preference
    public void performClick() {
        RestrictedLockUtils.EnforcedAdmin enforcedAdmin = getEnforcedAdmin();
        if (enforcedAdmin == null) {
            super.performClick();
        } else {
            RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getContext(), enforcedAdmin);
        }
    }

    private RestrictedLockUtils.EnforcedAdmin getEnforcedAdmin() {
        return RestrictedLockUtilsInternal.checkIfRestrictionEnforced(getContext(), "disallow_config_private_dns", UserHandle.myUserId());
    }

    private boolean isDisabledByAdmin() {
        return getEnforcedAdmin() != null;
    }

    private Button getSaveButton() {
        AlertDialog alertDialog = (AlertDialog) getDialog();
        if (alertDialog == null) {
            return null;
        }
        return alertDialog.getButton(-1);
    }

    private void updateDialogInfo() {
        boolean z = 3 == this.mMode;
        EditText editText = this.mEditText;
        if (editText != null) {
            editText.setEnabled(z);
        }
        Button saveButton = getSaveButton();
        if (saveButton != null) {
            saveButton.setEnabled(z ? InternetDomainName.isValid(this.mEditText.getText().toString()) : true);
        }
    }
}
