package org.nameless.custom.preference;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.AttributeSet;
import android.util.Log;
import androidx.preference.R$attr;
import java.util.List;
import java.util.Objects;
import org.nameless.custom.preference.RemotePreferenceManager;
/* loaded from: classes2.dex */
public class RemotePreference extends SelfRemovingPreference implements RemotePreferenceManager.OnRemoteUpdateListener {
    private static final boolean DEBUG;
    private static final String TAG;
    protected final Context mContext;

    static {
        String simpleName = RemotePreference.class.getSimpleName();
        TAG = simpleName;
        DEBUG = Log.isLoggable(simpleName, 2);
    }

    public RemotePreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mContext = context;
    }

    public RemotePreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public RemotePreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ConstraintsHelper.getAttr(context, R$attr.preferenceStyle, 16842894));
    }

    @Override // org.nameless.custom.preference.RemotePreferenceManager.OnRemoteUpdateListener
    public void onRemoteUpdated(Bundle bundle) {
        boolean z;
        if (DEBUG) {
            String str = TAG;
            Log.d(str, "onRemoteUpdated: " + bundle.toString());
        }
        if (bundle.containsKey(":lineage:pref_enabled") && (z = bundle.getBoolean(":lineage:pref_enabled", true)) != isAvailable()) {
            setAvailable(z);
        }
        if (isAvailable()) {
            setSummary(bundle.getString(":lineage:pref_summary"));
        }
    }

    @Override // org.nameless.custom.preference.SelfRemovingPreference, androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        if (isAvailable()) {
            RemotePreferenceManager.get(this.mContext).attach(getKey(), this);
        }
    }

    @Override // androidx.preference.Preference
    public void onDetached() {
        super.onDetached();
        RemotePreferenceManager.get(this.mContext).detach(getKey(), this);
    }

    protected String getRemoteKey(Bundle bundle) {
        String string = bundle.getString("com.android.settings.summary.key");
        if (string == null || !string.equals(getKey())) {
            return null;
        }
        return string;
    }

    @Override // org.nameless.custom.preference.RemotePreferenceManager.OnRemoteUpdateListener
    public Intent getReceiverIntent() {
        Intent intent = getIntent();
        if (intent == null) {
            Log.w(TAG, "No target intent specified in preference!");
            return null;
        }
        List<ResolveInfo> queryIntentActivitiesAsUser = this.mContext.getPackageManager().queryIntentActivitiesAsUser(intent, 1048704, UserHandle.myUserId());
        if (queryIntentActivitiesAsUser.size() == 0) {
            String str = TAG;
            Log.w(str, "No activity found for: " + Objects.toString(intent));
        }
        for (ResolveInfo resolveInfo : queryIntentActivitiesAsUser) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            String str2 = TAG;
            Log.d(str2, "ResolveInfo " + Objects.toString(resolveInfo));
            Bundle bundle = activityInfo.metaData;
            if (bundle != null && bundle.containsKey("com.android.settings.summary.receiver")) {
                String string = bundle.getString("com.android.settings.summary.receiver");
                String str3 = activityInfo.packageName;
                String remoteKey = getRemoteKey(bundle);
                if (DEBUG) {
                    Log.d(str2, "getReceiverIntent class=" + string + " package=" + str3 + " key=" + remoteKey);
                }
                if (remoteKey != null) {
                    Intent intent2 = new Intent("lineageos.intent.action.UPDATE_PREFERENCE");
                    intent2.setComponent(new ComponentName(str3, string));
                    intent2.putExtra(":lineage:pref_key", remoteKey);
                    return intent2;
                }
            }
        }
        return null;
    }
}
