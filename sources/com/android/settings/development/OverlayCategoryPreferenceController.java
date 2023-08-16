package com.android.settings.development;

import android.content.Context;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.R;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class OverlayCategoryPreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {
    private static final Comparator<OverlayInfo> OVERLAY_INFO_COMPARATOR = Comparator.comparing(new Function() { // from class: com.android.settings.development.OverlayCategoryPreferenceController$$ExternalSyntheticLambda0
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return ((OverlayInfo) obj).getPackageName();
        }
    });
    static final String PACKAGE_DEVICE_DEFAULT = "package_device_default";
    private final boolean mAvailable;
    private final String mCategory;
    private final String mDeviceDefaultLabel;
    private final boolean mIsFonts;
    private final IOverlayManager mOverlayManager;
    private final PackageManager mPackageManager;
    private ListPreference mPreference;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OverlayCategoryPreferenceController(Context context, PackageManager packageManager, IOverlayManager iOverlayManager, String str) {
        super(context);
        this.mOverlayManager = iOverlayManager;
        this.mPackageManager = packageManager;
        this.mCategory = str;
        this.mAvailable = (iOverlayManager == null || getOverlayInfos().isEmpty()) ? false : true;
        this.mDeviceDefaultLabel = this.mContext.getString(R.string.overlay_option_device_default);
        this.mIsFonts = "android.theme.customization.font".equals(str);
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mAvailable;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return this.mCategory;
    }

    @Override // com.android.settingslib.development.DeveloperOptionsPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        setPreference((ListPreference) preferenceScreen.findPreference(getPreferenceKey()));
    }

    void setPreference(ListPreference listPreference) {
        this.mPreference = listPreference;
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        return setOverlay((String) obj);
    }

    private boolean setOverlay(final String str) {
        JSONObject jSONObject;
        List<OverlayInfo> overlayInfos = getOverlayInfos();
        final ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (OverlayInfo overlayInfo : overlayInfos) {
            if (overlayInfo.isEnabled()) {
                arrayList.add(overlayInfo.packageName);
                arrayList2.add(overlayInfo.category);
            }
            if (str.equals(getPackageLabel(overlayInfo.packageName))) {
                arrayList3.add(overlayInfo.packageName);
                arrayList4.add(overlayInfo.category);
            }
        }
        Log.w("OverlayCategoryPC", "setOverlay currentPackageNames=" + arrayList.toString());
        Log.w("OverlayCategoryPC", "setOverlay packageNames=" + arrayList3.toString());
        Log.w("OverlayCategoryPC", "setOverlay label=" + str);
        if (this.mIsFonts) {
            String stringForUser = Settings.Secure.getStringForUser(this.mContext.getContentResolver(), "theme_customization_overlay_packages", -2);
            if (stringForUser == null) {
                jSONObject = new JSONObject();
            } else {
                try {
                    jSONObject = new JSONObject(stringForUser);
                } catch (JSONException e) {
                    Log.e("OverlayCategoryPC", "Error parsing current settings value:\n" + e.getMessage());
                    return false;
                }
            }
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                jSONObject.remove((String) it.next());
            }
            for (int i = 0; i < arrayList4.size(); i++) {
                try {
                    jSONObject.put((String) arrayList4.get(i), arrayList3.get(i));
                } catch (JSONException e2) {
                    Log.e("OverlayCategoryPC", "Error adding new settings value:\n" + e2.getMessage());
                    return false;
                }
            }
            Settings.Secure.putStringForUser(this.mContext.getContentResolver(), "theme_customization_overlay_packages", jSONObject.toString(), -2);
        }
        new AsyncTask<Void, Void, Boolean>() { // from class: com.android.settings.development.OverlayCategoryPreferenceController.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Boolean doInBackground(Void... voidArr) {
                try {
                    if (str.equals(OverlayCategoryPreferenceController.this.mDeviceDefaultLabel)) {
                        Iterator it2 = arrayList.iterator();
                        while (it2.hasNext()) {
                            String str2 = (String) it2.next();
                            Log.w("OverlayCategoryPC", "setOverlay Disabing overlay" + str2);
                            OverlayCategoryPreferenceController.this.mOverlayManager.setEnabled(str2, false, 0);
                        }
                    } else {
                        Iterator it3 = arrayList3.iterator();
                        while (it3.hasNext()) {
                            String str3 = (String) it3.next();
                            Log.w("OverlayCategoryPC", "setOverlay Enabling overlay" + str3);
                            OverlayCategoryPreferenceController.this.mOverlayManager.setEnabledExclusiveInCategory(str3, 0);
                        }
                    }
                    return Boolean.TRUE;
                } catch (Exception e3) {
                    Log.w("OverlayCategoryPC", "Error enabling overlay.", e3);
                    return Boolean.FALSE;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Boolean bool) {
                OverlayCategoryPreferenceController overlayCategoryPreferenceController = OverlayCategoryPreferenceController.this;
                overlayCategoryPreferenceController.updateState(overlayCategoryPreferenceController.mPreference);
                if (bool.booleanValue()) {
                    return;
                }
                Toast.makeText(((AbstractPreferenceController) OverlayCategoryPreferenceController.this).mContext, R.string.overlay_toast_failed_to_apply, 1).show();
            }
        }.execute(new Void[0]);
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        ArrayList arrayList = new ArrayList();
        String str = this.mDeviceDefaultLabel;
        arrayList.add(str);
        for (OverlayInfo overlayInfo : getOverlayInfos()) {
            String packageLabel = getPackageLabel(overlayInfo.packageName);
            if (!arrayList.contains(packageLabel)) {
                arrayList.add(packageLabel);
            }
            if (overlayInfo.isEnabled()) {
                Log.w("OverlayCategoryPC", "updateState Selecting label" + packageLabel);
                str = packageLabel;
            }
        }
        this.mPreference.setEntries((CharSequence[]) arrayList.toArray(new String[arrayList.size()]));
        this.mPreference.setEntryValues((CharSequence[]) arrayList.toArray(new String[arrayList.size()]));
        this.mPreference.setValue(str);
        this.mPreference.setSummary(str);
    }

    private List<OverlayInfo> getOverlayInfos() {
        ArrayList arrayList = new ArrayList();
        try {
            for (List<OverlayInfo> list : this.mOverlayManager.getAllOverlays(0).values()) {
                for (OverlayInfo overlayInfo : list) {
                    String str = overlayInfo.category;
                    if (str != null && str.contains(this.mCategory)) {
                        arrayList.add(overlayInfo);
                    }
                }
            }
            arrayList.sort(OVERLAY_INFO_COMPARATOR);
            Log.w("OverlayCategoryPC", "getOverlays list=" + arrayList.toString());
            return arrayList;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private String getPackageLabel(String str) {
        try {
            return this.mPackageManager.getApplicationInfo(str, 0).loadLabel(this.mPackageManager).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            return this.mDeviceDefaultLabel;
        }
    }
}
