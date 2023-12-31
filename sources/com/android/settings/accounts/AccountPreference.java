package com.android.settings.accounts;

import android.util.Log;
import android.widget.ImageView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.R;
/* loaded from: classes.dex */
public class AccountPreference extends Preference {
    private boolean mShowTypeIcon;
    private int mStatus;
    private ImageView mSyncStatusIcon;

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        if (this.mShowTypeIcon) {
            return;
        }
        ImageView imageView = (ImageView) preferenceViewHolder.findViewById(16908294);
        this.mSyncStatusIcon = imageView;
        imageView.setImageResource(getSyncStatusIcon(this.mStatus));
        this.mSyncStatusIcon.setContentDescription(getSyncContentDescription(this.mStatus));
    }

    private int getSyncStatusIcon(int i) {
        if (i != 0) {
            if (i == 1) {
                return R.drawable.ic_settings_sync_disabled;
            }
            if (i == 2) {
                return R.drawable.ic_settings_sync_failed;
            }
            if (i != 3) {
                int i2 = R.drawable.ic_settings_sync_failed;
                Log.e("AccountPreference", "Unknown sync status: " + i);
                return i2;
            }
        }
        return R.drawable.ic_settings_sync;
    }

    private String getSyncContentDescription(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        return getContext().getString(R.string.accessibility_sync_in_progress);
                    }
                    Log.e("AccountPreference", "Unknown sync status: " + i);
                    return getContext().getString(R.string.accessibility_sync_error);
                }
                return getContext().getString(R.string.accessibility_sync_error);
            }
            return getContext().getString(R.string.accessibility_sync_disabled);
        }
        return getContext().getString(R.string.accessibility_sync_enabled);
    }
}
