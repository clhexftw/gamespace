package com.android.settings.development;

import android.content.Context;
import android.content.pm.IShortcutService;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
/* loaded from: classes.dex */
public class ShortcutManagerThrottlingPreferenceController extends DeveloperOptionsPreferenceController implements PreferenceControllerMixin {
    private final IShortcutService mShortcutService;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "reset_shortcut_manager_throttling";
    }

    public ShortcutManagerThrottlingPreferenceController(Context context) {
        super(context);
        this.mShortcutService = getShortCutService();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (TextUtils.equals("reset_shortcut_manager_throttling", preference.getKey())) {
            resetShortcutManagerThrottling();
            return true;
        }
        return false;
    }

    private void resetShortcutManagerThrottling() {
        IShortcutService iShortcutService = this.mShortcutService;
        if (iShortcutService == null) {
            return;
        }
        try {
            iShortcutService.resetThrottling();
            Toast.makeText(this.mContext, R.string.reset_shortcut_manager_throttling_complete, 0).show();
        } catch (RemoteException e) {
            Log.e("ShortcutMgrPrefCtrl", "Failed to reset rate limiting", e);
        }
    }

    private IShortcutService getShortCutService() {
        try {
            return IShortcutService.Stub.asInterface(ServiceManager.getService("shortcut"));
        } catch (VerifyError unused) {
            return null;
        }
    }
}
