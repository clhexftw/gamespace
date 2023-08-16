package com.android.settings.network.apn;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Telephony;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import com.android.internal.util.ArrayUtils;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settings.network.ProxySubscriptionManager;
import com.android.settingslib.utils.ThreadUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import org.nameless.custom.preference.SwitchPreference;
/* loaded from: classes.dex */
public class ApnEditor extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, View.OnKeyListener {
    static final int APN_INDEX = 2;
    static final int CARRIER_ENABLED_INDEX = 17;
    static final int MCC_INDEX = 9;
    static final int MENU_DELETE = 1;
    static final int MNC_INDEX = 10;
    static final int NAME_INDEX = 1;
    static final int PROTOCOL_INDEX = 16;
    static final int ROAMING_PROTOCOL_INDEX = 20;
    static final int TYPE_INDEX = 15;
    static String sNotSet;
    EditTextPreference mApn;
    ApnData mApnData;
    EditTextPreference mApnType;
    ListPreference mAuthType;
    private int mBearerInitialVal = 0;
    MultiSelectListPreference mBearerMulti;
    SwitchPreference mCarrierEnabled;
    private Uri mCarrierUri;
    private String mCurMcc;
    private String mCurMnc;
    String mDefaultApnProtocol;
    String mDefaultApnRoamingProtocol;
    String[] mDefaultApnTypes;
    private boolean mIsAddApnAllowed;
    private boolean mIsCarrierIdApn;
    EditTextPreference mMcc;
    EditTextPreference mMmsPort;
    EditTextPreference mMmsProxy;
    EditTextPreference mMmsc;
    EditTextPreference mMnc;
    EditTextPreference mMvnoMatchData;
    private String mMvnoMatchDataStr;
    ListPreference mMvnoType;
    private String mMvnoTypeStr;
    EditTextPreference mName;
    private boolean mNewApn;
    EditTextPreference mPassword;
    EditTextPreference mPort;
    ListPreference mProtocol;
    EditTextPreference mProxy;
    ProxySubscriptionManager mProxySubscriptionMgr;
    String[] mReadOnlyApnTypes;
    ListPreference mRoamingProtocol;
    EditTextPreference mServer;
    private int mSubId;
    EditTextPreference mUser;
    private static final String TAG = ApnEditor.class.getSimpleName();
    public static final String[] APN_TYPES = {"default", "mms", "supl", "dun", "hipri", "fota", "ims", "cbs", "ia", "emergency", "mcx", "xcap"};
    private static final String[] sProjection = {"_id", "name", "apn", "proxy", "port", "user", "server", "password", "mmsc", "mcc", "mnc", "numeric", "mmsproxy", "mmsport", "authtype", "type", "protocol", "carrier_enabled", "bearer", "bearer_bitmask", "roaming_protocol", "mvno_type", "mvno_match_data", "edited", "user_editable", "carrier_id"};

    private static boolean bitmaskHasTech(int i, int i2) {
        if (i == 0) {
            return true;
        }
        return i2 >= 1 && (i & (1 << (i2 - 1))) != 0;
    }

    private static int getBitmaskForTech(int i) {
        if (i >= 1) {
            return 1 << (i - 1);
        }
        return 0;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 13;
    }

    @Override // com.android.settings.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setLifecycleForAllControllers();
        Intent intent = getIntent();
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            finish();
            return;
        }
        this.mSubId = intent.getIntExtra("sub_id", -1);
        initApnEditorUi();
        getCarrierCustomizedConfig(getContext());
        Uri uri = null;
        if (action.equals("android.intent.action.EDIT")) {
            uri = intent.getData();
            if (!uri.isPathPrefixMatch(Telephony.Carriers.CONTENT_URI)) {
                String str = TAG;
                Log.e(str, "Edit request not for carrier table. Uri: " + uri);
                finish();
                return;
            }
        } else if (action.equals("android.intent.action.INSERT")) {
            Uri data = intent.getData();
            this.mCarrierUri = data;
            if (!data.isPathPrefixMatch(Telephony.Carriers.CONTENT_URI)) {
                String str2 = TAG;
                Log.e(str2, "Insert request not for carrier table. Uri: " + this.mCarrierUri);
                finish();
                return;
            }
            this.mNewApn = true;
            this.mMvnoTypeStr = intent.getStringExtra("mvno_type");
            this.mMvnoMatchDataStr = intent.getStringExtra("mvno_match_data");
        } else {
            finish();
            return;
        }
        if (uri != null) {
            this.mApnData = getApnDataFromUri(uri);
        } else {
            this.mApnData = new ApnData(sProjection.length);
        }
        this.mIsCarrierIdApn = this.mApnData.getInteger(25, -1).intValue() > -1;
        boolean z = this.mApnData.getInteger(23, 1).intValue() == 1;
        String str3 = TAG;
        Log.d(str3, "onCreate: EDITED " + z);
        for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
            getPreferenceScreen().getPreference(i).setOnPreferenceChangeListener(this);
        }
    }

    private void setLifecycleForAllControllers() {
        if (this.mProxySubscriptionMgr == null) {
            this.mProxySubscriptionMgr = ProxySubscriptionManager.getInstance(getContext());
        }
        this.mProxySubscriptionMgr.setLifecycle(getLifecycle());
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        fillUI(bundle == null);
        setCarrierCustomizedConfigToUi();
    }

    static String formatInteger(String str) {
        try {
            return String.format(getCorrectDigitsFormat(str), Integer.valueOf(Integer.parseInt(str)));
        } catch (NumberFormatException unused) {
            return str;
        }
    }

    static String getCorrectDigitsFormat(String str) {
        return str.length() == 2 ? "%02d" : "%03d";
    }

    void fillUI(boolean z) {
        String str;
        if (z) {
            this.mName.setText(this.mApnData.getString(1));
            this.mApn.setText(this.mApnData.getString(2));
            this.mProxy.setText(this.mApnData.getString(3));
            this.mPort.setText(this.mApnData.getString(4));
            this.mUser.setText(this.mApnData.getString(5));
            this.mServer.setText(this.mApnData.getString(6));
            this.mPassword.setText(this.mApnData.getString(7));
            this.mMmsProxy.setText(this.mApnData.getString(12));
            this.mMmsPort.setText(this.mApnData.getString(13));
            this.mMmsc.setText(this.mApnData.getString(8));
            this.mMcc.setText(this.mApnData.getString(9));
            this.mMnc.setText(this.mApnData.getString(10));
            this.mApnType.setText(this.mApnData.getString(15));
            if (this.mNewApn) {
                SubscriptionInfo accessibleSubscriptionInfo = this.mProxySubscriptionMgr.getAccessibleSubscriptionInfo(this.mSubId);
                String mccString = accessibleSubscriptionInfo == null ? null : accessibleSubscriptionInfo.getMccString();
                String mncString = accessibleSubscriptionInfo == null ? null : accessibleSubscriptionInfo.getMncString();
                if (!TextUtils.isEmpty(mccString) && !TextUtils.isEmpty(mccString)) {
                    this.mMcc.setText(mccString);
                    this.mMnc.setText(mncString);
                    this.mCurMnc = mncString;
                    this.mCurMcc = mccString;
                }
            }
            int intValue = this.mApnData.getInteger(14, -1).intValue();
            if (intValue != -1) {
                this.mAuthType.setValueIndex(intValue);
            } else {
                this.mAuthType.setValue(null);
            }
            this.mProtocol.setValue(this.mApnData.getString(16));
            this.mRoamingProtocol.setValue(this.mApnData.getString(20));
            this.mCarrierEnabled.setChecked(this.mApnData.getInteger(17, 1).intValue() == 1);
            this.mBearerInitialVal = this.mApnData.getInteger(18, 0).intValue();
            HashSet hashSet = new HashSet();
            int intValue2 = this.mApnData.getInteger(19, 0).intValue();
            if (intValue2 != 0) {
                int i = 1;
                while (intValue2 != 0) {
                    if ((intValue2 & 1) == 1) {
                        hashSet.add("" + i);
                    }
                    intValue2 >>= 1;
                    i++;
                }
            } else if (this.mBearerInitialVal == 0) {
                hashSet.add("0");
            }
            if (this.mBearerInitialVal != 0) {
                if (!hashSet.contains("" + this.mBearerInitialVal)) {
                    hashSet.add("" + this.mBearerInitialVal);
                }
            }
            this.mBearerMulti.setValues(hashSet);
            this.mMvnoType.setValue(this.mApnData.getString(21));
            this.mMvnoMatchData.setEnabled(false);
            this.mMvnoMatchData.setText(this.mApnData.getString(22));
            if (this.mNewApn && (str = this.mMvnoTypeStr) != null && this.mMvnoMatchDataStr != null) {
                this.mMvnoType.setValue(str);
                this.mMvnoMatchData.setText(this.mMvnoMatchDataStr);
            }
        }
        EditTextPreference editTextPreference = this.mName;
        editTextPreference.setSummary(checkNull(editTextPreference.getText()));
        EditTextPreference editTextPreference2 = this.mApn;
        editTextPreference2.setSummary(checkNull(editTextPreference2.getText()));
        EditTextPreference editTextPreference3 = this.mProxy;
        editTextPreference3.setSummary(checkNull(editTextPreference3.getText()));
        EditTextPreference editTextPreference4 = this.mPort;
        editTextPreference4.setSummary(checkNull(editTextPreference4.getText()));
        EditTextPreference editTextPreference5 = this.mUser;
        editTextPreference5.setSummary(checkNull(editTextPreference5.getText()));
        EditTextPreference editTextPreference6 = this.mServer;
        editTextPreference6.setSummary(checkNull(editTextPreference6.getText()));
        EditTextPreference editTextPreference7 = this.mPassword;
        editTextPreference7.setSummary(starify(editTextPreference7.getText()));
        EditTextPreference editTextPreference8 = this.mMmsProxy;
        editTextPreference8.setSummary(checkNull(editTextPreference8.getText()));
        EditTextPreference editTextPreference9 = this.mMmsPort;
        editTextPreference9.setSummary(checkNull(editTextPreference9.getText()));
        EditTextPreference editTextPreference10 = this.mMmsc;
        editTextPreference10.setSummary(checkNull(editTextPreference10.getText()));
        EditTextPreference editTextPreference11 = this.mMcc;
        editTextPreference11.setSummary(formatInteger(checkNull(editTextPreference11.getText())));
        EditTextPreference editTextPreference12 = this.mMnc;
        editTextPreference12.setSummary(formatInteger(checkNull(editTextPreference12.getText())));
        EditTextPreference editTextPreference13 = this.mApnType;
        editTextPreference13.setSummary(checkNull(editTextPreference13.getText()));
        String value = this.mAuthType.getValue();
        if (value != null) {
            int parseInt = Integer.parseInt(value);
            this.mAuthType.setValueIndex(parseInt);
            this.mAuthType.setSummary(getResources().getStringArray(R.array.apn_auth_entries)[parseInt]);
        } else {
            this.mAuthType.setSummary(sNotSet);
        }
        ListPreference listPreference = this.mProtocol;
        listPreference.setSummary(checkNull(protocolDescription(listPreference.getValue(), this.mProtocol)));
        ListPreference listPreference2 = this.mRoamingProtocol;
        listPreference2.setSummary(checkNull(protocolDescription(listPreference2.getValue(), this.mRoamingProtocol)));
        MultiSelectListPreference multiSelectListPreference = this.mBearerMulti;
        multiSelectListPreference.setSummary(checkNull(bearerMultiDescription(multiSelectListPreference.getValues())));
        ListPreference listPreference3 = this.mMvnoType;
        listPreference3.setSummary(checkNull(mvnoDescription(listPreference3.getValue())));
        EditTextPreference editTextPreference14 = this.mMvnoMatchData;
        editTextPreference14.setSummary(checkNullforMvnoValue(editTextPreference14.getText()));
        if (getResources().getBoolean(R.bool.config_allow_edit_carrier_enabled)) {
            this.mCarrierEnabled.setEnabled(true);
        } else {
            this.mCarrierEnabled.setEnabled(false);
        }
    }

    private String protocolDescription(String str, ListPreference listPreference) {
        String upperCase = checkNull(str).toUpperCase();
        if (upperCase.equals("IPV4")) {
            upperCase = "IP";
        }
        int findIndexOfValue = listPreference.findIndexOfValue(upperCase);
        if (findIndexOfValue == -1) {
            return null;
        }
        try {
            return getResources().getStringArray(R.array.apn_protocol_entries)[findIndexOfValue];
        } catch (ArrayIndexOutOfBoundsException unused) {
            return null;
        }
    }

    private String bearerMultiDescription(Set<String> set) {
        String[] stringArray = getResources().getStringArray(R.array.bearer_entries);
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : set) {
            int findIndexOfValue = this.mBearerMulti.findIndexOfValue(str);
            if (z) {
                try {
                    sb.append(stringArray[findIndexOfValue]);
                    z = false;
                } catch (ArrayIndexOutOfBoundsException unused) {
                }
            } else {
                sb.append(", " + stringArray[findIndexOfValue]);
            }
        }
        String sb2 = sb.toString();
        if (TextUtils.isEmpty(sb2)) {
            return null;
        }
        return sb2;
    }

    private String mvnoDescription(String str) {
        int findIndexOfValue = this.mMvnoType.findIndexOfValue(str);
        String value = this.mMvnoType.getValue();
        if (findIndexOfValue == -1) {
            return null;
        }
        String[] stringArray = getResources().getStringArray(R.array.mvno_type_entries);
        this.mMvnoMatchData.setEnabled(findIndexOfValue != 0);
        if (str != null && !str.equals(value)) {
            if (stringArray[findIndexOfValue].equals("SPN")) {
                TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService(TelephonyManager.class);
                TelephonyManager createForSubscriptionId = telephonyManager.createForSubscriptionId(this.mSubId);
                if (createForSubscriptionId != null) {
                    telephonyManager = createForSubscriptionId;
                }
                this.mMvnoMatchData.setText(telephonyManager.getSimOperatorName());
            } else {
                if (stringArray[findIndexOfValue].equals("IMSI")) {
                    SubscriptionInfo accessibleSubscriptionInfo = this.mProxySubscriptionMgr.getAccessibleSubscriptionInfo(this.mSubId);
                    String objects = accessibleSubscriptionInfo == null ? "" : Objects.toString(accessibleSubscriptionInfo.getMccString(), "");
                    String objects2 = accessibleSubscriptionInfo != null ? Objects.toString(accessibleSubscriptionInfo.getMncString(), "") : "";
                    EditTextPreference editTextPreference = this.mMvnoMatchData;
                    editTextPreference.setText(objects + objects2 + "x");
                } else if (stringArray[findIndexOfValue].equals("GID")) {
                    TelephonyManager telephonyManager2 = (TelephonyManager) getContext().getSystemService(TelephonyManager.class);
                    TelephonyManager createForSubscriptionId2 = telephonyManager2.createForSubscriptionId(this.mSubId);
                    if (createForSubscriptionId2 != null) {
                        telephonyManager2 = createForSubscriptionId2;
                    }
                    this.mMvnoMatchData.setText(telephonyManager2.getGroupIdLevel1());
                } else {
                    this.mMvnoMatchData.setText("");
                }
            }
        }
        try {
            return stringArray[findIndexOfValue];
        } catch (ArrayIndexOutOfBoundsException unused) {
            return null;
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        String key = preference.getKey();
        if ("auth_type".equals(key)) {
            try {
                int parseInt = Integer.parseInt((String) obj);
                this.mAuthType.setValueIndex(parseInt);
                this.mAuthType.setSummary(getResources().getStringArray(R.array.apn_auth_entries)[parseInt]);
                return true;
            } catch (NumberFormatException unused) {
                return false;
            }
        } else if ("apn_type".equals(key)) {
            String str = (String) obj;
            if (TextUtils.isEmpty(str) && !ArrayUtils.isEmpty(this.mDefaultApnTypes)) {
                str = getEditableApnType(this.mDefaultApnTypes);
            }
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            this.mApnType.setSummary(str);
            return true;
        } else if ("apn_protocol".equals(key)) {
            String str2 = (String) obj;
            String protocolDescription = protocolDescription(str2, this.mProtocol);
            if (protocolDescription == null) {
                return false;
            }
            this.mProtocol.setSummary(protocolDescription);
            this.mProtocol.setValue(str2);
            return true;
        } else if ("apn_roaming_protocol".equals(key)) {
            String str3 = (String) obj;
            String protocolDescription2 = protocolDescription(str3, this.mRoamingProtocol);
            if (protocolDescription2 == null) {
                return false;
            }
            this.mRoamingProtocol.setSummary(protocolDescription2);
            this.mRoamingProtocol.setValue(str3);
            return true;
        } else if ("bearer_multi".equals(key)) {
            Set<String> set = (Set) obj;
            String bearerMultiDescription = bearerMultiDescription(set);
            if (bearerMultiDescription == null) {
                return false;
            }
            this.mBearerMulti.setValues(set);
            this.mBearerMulti.setSummary(bearerMultiDescription);
            return true;
        } else if ("mvno_type".equals(key)) {
            String str4 = (String) obj;
            String mvnoDescription = mvnoDescription(str4);
            if (mvnoDescription == null) {
                return false;
            }
            this.mMvnoType.setValue(str4);
            this.mMvnoType.setSummary(mvnoDescription);
            EditTextPreference editTextPreference = this.mMvnoMatchData;
            editTextPreference.setSummary(checkNullforMvnoValue(editTextPreference.getText()));
            return true;
        } else if ("apn_password".equals(key)) {
            this.mPassword.setSummary(starify(obj != null ? String.valueOf(obj) : ""));
            return true;
        } else if ("carrier_enabled".equals(key)) {
            return true;
        } else {
            preference.setSummary(checkNull(obj != null ? String.valueOf(obj) : null));
            return true;
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        if (!this.mNewApn && this.mIsAddApnAllowed) {
            menu.add(0, 1, 0, R.string.menu_delete).setIcon(R.drawable.ic_delete);
        }
        menu.add(0, 2, 0, R.string.menu_save).setIcon(17301582);
        menu.add(0, 3, 0, R.string.menu_cancel).setIcon(17301560);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            deleteApn();
            finish();
            return true;
        } else if (itemId == 2) {
            if (validateAndSaveApnData()) {
                finish();
            }
            return true;
        } else if (itemId == 3) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0 && i == 4) {
            if (validateAndSaveApnData()) {
                finish();
                return true;
            }
            return true;
        }
        return false;
    }

    boolean setStringValueAndCheckIfDiff(ContentValues contentValues, String str, String str2, boolean z, int i) {
        String string = this.mApnData.getString(i);
        boolean z2 = z || (!(TextUtils.isEmpty(str2) && TextUtils.isEmpty(string)) && (str2 == null || !str2.equals(string)));
        if (z2 && str2 != null) {
            contentValues.put(str, str2);
        }
        return z2;
    }

    boolean setIntValueAndCheckIfDiff(ContentValues contentValues, String str, int i, boolean z, int i2) {
        boolean z2 = z || i != this.mApnData.getInteger(i2).intValue();
        if (z2) {
            contentValues.put(str, Integer.valueOf(i));
        }
        return z2;
    }

    boolean validateAndSaveApnData() {
        int i;
        int i2;
        String checkNotSet = checkNotSet(this.mName.getText());
        String checkNotSet2 = checkNotSet(this.mApn.getText());
        String checkNotSet3 = checkNotSet(this.mMcc.getText());
        String checkNotSet4 = checkNotSet(this.mMnc.getText());
        if (validateApnData() != null) {
            showError();
            return false;
        }
        ContentValues contentValues = new ContentValues();
        boolean stringValueAndCheckIfDiff = setStringValueAndCheckIfDiff(contentValues, "mmsc", checkNotSet(this.mMmsc.getText()), setStringValueAndCheckIfDiff(contentValues, "password", checkNotSet(this.mPassword.getText()), setStringValueAndCheckIfDiff(contentValues, "server", checkNotSet(this.mServer.getText()), setStringValueAndCheckIfDiff(contentValues, "user", checkNotSet(this.mUser.getText()), setStringValueAndCheckIfDiff(contentValues, "mmsport", checkNotSet(this.mMmsPort.getText()), setStringValueAndCheckIfDiff(contentValues, "mmsproxy", checkNotSet(this.mMmsProxy.getText()), setStringValueAndCheckIfDiff(contentValues, "port", checkNotSet(this.mPort.getText()), setStringValueAndCheckIfDiff(contentValues, "proxy", checkNotSet(this.mProxy.getText()), setStringValueAndCheckIfDiff(contentValues, "apn", checkNotSet2, setStringValueAndCheckIfDiff(contentValues, "name", checkNotSet, this.mNewApn, 1), 2), 3), 4), 12), 13), 5), 6), 7), 8);
        String value = this.mAuthType.getValue();
        if (value != null) {
            stringValueAndCheckIfDiff = setIntValueAndCheckIfDiff(contentValues, "authtype", Integer.parseInt(value), stringValueAndCheckIfDiff, 14);
        }
        boolean stringValueAndCheckIfDiff2 = setStringValueAndCheckIfDiff(contentValues, "mnc", checkNotSet4, setStringValueAndCheckIfDiff(contentValues, "mcc", checkNotSet3, setStringValueAndCheckIfDiff(contentValues, "type", checkNotSet(getUserEnteredApnType()), setStringValueAndCheckIfDiff(contentValues, "roaming_protocol", checkNotSet(this.mRoamingProtocol.getValue()), setStringValueAndCheckIfDiff(contentValues, "protocol", checkNotSet(this.mProtocol.getValue()), stringValueAndCheckIfDiff, 16), 20), 15), 9), 10);
        contentValues.put("numeric", checkNotSet3 + checkNotSet4);
        String str = this.mCurMnc;
        if (str != null && this.mCurMcc != null && str.equals(checkNotSet4) && this.mCurMcc.equals(checkNotSet3)) {
            contentValues.put("current", (Integer) 1);
        }
        Iterator<String> it = this.mBearerMulti.getValues().iterator();
        int i3 = 0;
        while (true) {
            if (!it.hasNext()) {
                i = i3;
                break;
            }
            String next = it.next();
            if (Integer.parseInt(next) == 0) {
                i = 0;
                break;
            }
            i3 |= getBitmaskForTech(Integer.parseInt(next));
        }
        boolean intValueAndCheckIfDiff = setIntValueAndCheckIfDiff(contentValues, "carrier_enabled", this.mCarrierEnabled.isChecked() ? 1 : 0, setStringValueAndCheckIfDiff(contentValues, "mvno_match_data", checkNotSet(this.mMvnoMatchData.getText()), setStringValueAndCheckIfDiff(contentValues, "mvno_type", checkNotSet(this.mMvnoType.getValue()), setIntValueAndCheckIfDiff(contentValues, "bearer", (i == 0 || (i2 = this.mBearerInitialVal) == 0 || !bitmaskHasTech(i, i2)) ? 0 : this.mBearerInitialVal, setIntValueAndCheckIfDiff(contentValues, "bearer_bitmask", i, stringValueAndCheckIfDiff2, 19), 18), 21), 22), 17);
        contentValues.put("edited", (Integer) 1);
        if (intValueAndCheckIfDiff) {
            updateApnDataToDatabase(this.mApnData.getUri() == null ? this.mCarrierUri : this.mApnData.getUri(), contentValues);
        }
        return true;
    }

    private void updateApnDataToDatabase(final Uri uri, final ContentValues contentValues) {
        ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.settings.network.apn.ApnEditor$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ApnEditor.this.lambda$updateApnDataToDatabase$0(uri, contentValues);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateApnDataToDatabase$0(Uri uri, ContentValues contentValues) {
        if (uri.equals(this.mCarrierUri)) {
            if (getContentResolver().insert(this.mCarrierUri, contentValues) == null) {
                String str = TAG;
                Log.e(str, "Can't add a new apn to database " + this.mCarrierUri);
                return;
            }
            return;
        }
        getContentResolver().update(uri, contentValues, null, null);
    }

    String validateApnData() {
        String checkNotSet = checkNotSet(this.mName.getText());
        String checkNotSet2 = checkNotSet(this.mApn.getText());
        String checkNotSet3 = checkNotSet(this.mMcc.getText());
        String checkNotSet4 = checkNotSet(this.mMnc.getText());
        boolean z = this.mIsCarrierIdApn && TextUtils.isEmpty(checkNotSet3) && TextUtils.isEmpty(checkNotSet4);
        if (TextUtils.isEmpty(checkNotSet)) {
            return getResources().getString(R.string.error_name_empty);
        }
        if (TextUtils.isEmpty(checkNotSet2)) {
            return getResources().getString(R.string.error_apn_empty);
        }
        if (z) {
            Log.d(TAG, "validateApnData: carrier id APN does not have mcc/mnc defined");
        } else if (checkNotSet3 == null || checkNotSet3.length() != 3) {
            return getResources().getString(R.string.error_mcc_not3);
        } else {
            if (checkNotSet4 == null || (checkNotSet4.length() & 65534) != 2) {
                return getResources().getString(R.string.error_mnc_not23);
            }
        }
        return null;
    }

    void showError() {
        ErrorDialog.showError(this);
    }

    private void deleteApn() {
        if (this.mApnData.getUri() != null) {
            getContentResolver().delete(this.mApnData.getUri(), null, null);
            this.mApnData = new ApnData(sProjection.length);
        }
    }

    private String starify(String str) {
        if (str == null || str.length() == 0) {
            return sNotSet;
        }
        int length = str.length();
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = '*';
        }
        return new String(cArr);
    }

    private String checkNull(String str) {
        return TextUtils.isEmpty(str) ? sNotSet : str;
    }

    private String checkNullforMvnoValue(String str) {
        return TextUtils.isEmpty(str) ? getResources().getString(R.string.apn_not_set_for_mvno) : str;
    }

    private String checkNotSet(String str) {
        if (sNotSet.equals(str)) {
            return null;
        }
        return str;
    }

    String getUserEnteredApnType() {
        String text = this.mApnType.getText();
        if (text != null) {
            text = text.trim();
        }
        if (TextUtils.isEmpty(text) || "*".equals(text)) {
            text = getEditableApnType(APN_TYPES);
        }
        String str = TAG;
        Log.d(str, "getUserEnteredApnType: changed apn type to editable apn types: " + text);
        return text;
    }

    private String getEditableApnType(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : strArr) {
            if (!str.equals("ia") && !str.equals("emergency") && !str.equals("mcx") && !str.equals("ims")) {
                if (z) {
                    z = false;
                } else {
                    sb.append(",");
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }

    private void initApnEditorUi() {
        addPreferencesFromResource(R.xml.apn_editor);
        sNotSet = getResources().getString(R.string.apn_not_set);
        this.mName = (EditTextPreference) findPreference("apn_name");
        this.mApn = (EditTextPreference) findPreference("apn_apn");
        this.mProxy = (EditTextPreference) findPreference("apn_http_proxy");
        this.mPort = (EditTextPreference) findPreference("apn_http_port");
        this.mUser = (EditTextPreference) findPreference("apn_user");
        this.mServer = (EditTextPreference) findPreference("apn_server");
        this.mPassword = (EditTextPreference) findPreference("apn_password");
        this.mMmsProxy = (EditTextPreference) findPreference("apn_mms_proxy");
        this.mMmsPort = (EditTextPreference) findPreference("apn_mms_port");
        this.mMmsc = (EditTextPreference) findPreference("apn_mmsc");
        this.mMcc = (EditTextPreference) findPreference("apn_mcc");
        this.mMnc = (EditTextPreference) findPreference("apn_mnc");
        this.mApnType = (EditTextPreference) findPreference("apn_type");
        this.mAuthType = (ListPreference) findPreference("auth_type");
        this.mProtocol = (ListPreference) findPreference("apn_protocol");
        this.mRoamingProtocol = (ListPreference) findPreference("apn_roaming_protocol");
        this.mCarrierEnabled = (SwitchPreference) findPreference("carrier_enabled");
        this.mBearerMulti = (MultiSelectListPreference) findPreference("bearer_multi");
        this.mMvnoType = (ListPreference) findPreference("mvno_type");
        this.mMvnoMatchData = (EditTextPreference) findPreference("mvno_match_data");
    }

    protected void getCarrierCustomizedConfig(Context context) {
        PersistableBundle configForSubId;
        this.mIsAddApnAllowed = true;
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) context.getSystemService("carrier_config");
        if (carrierConfigManager == null || (configForSubId = carrierConfigManager.getConfigForSubId(this.mSubId)) == null) {
            return;
        }
        if (!ArrayUtils.isEmpty(this.mDefaultApnTypes)) {
            String str = TAG;
            Log.d(str, "onCreate: default apn types: " + Arrays.toString(this.mDefaultApnTypes));
        }
        String string = configForSubId.getString("apn.settings_default_protocol_string");
        this.mDefaultApnProtocol = string;
        if (!TextUtils.isEmpty(string)) {
            String str2 = TAG;
            Log.d(str2, "onCreate: default apn protocol: " + this.mDefaultApnProtocol);
        }
        String string2 = configForSubId.getString("apn.settings_default_roaming_protocol_string");
        this.mDefaultApnRoamingProtocol = string2;
        if (!TextUtils.isEmpty(string2)) {
            String str3 = TAG;
            Log.d(str3, "onCreate: default apn roaming protocol: " + this.mDefaultApnRoamingProtocol);
        }
        boolean z = configForSubId.getBoolean("allow_adding_apns_bool");
        this.mIsAddApnAllowed = z;
        if (z) {
            return;
        }
        Log.d(TAG, "onCreate: not allow to add new APN");
    }

    private void setCarrierCustomizedConfigToUi() {
        if (TextUtils.isEmpty(this.mApnType.getText()) && !ArrayUtils.isEmpty(this.mDefaultApnTypes)) {
            String editableApnType = getEditableApnType(this.mDefaultApnTypes);
            this.mApnType.setText(editableApnType);
            this.mApnType.setSummary(editableApnType);
        }
        String protocolDescription = protocolDescription(this.mDefaultApnProtocol, this.mProtocol);
        if (TextUtils.isEmpty(this.mProtocol.getValue()) && !TextUtils.isEmpty(protocolDescription)) {
            this.mProtocol.setValue(this.mDefaultApnProtocol);
            this.mProtocol.setSummary(protocolDescription);
        }
        String protocolDescription2 = protocolDescription(this.mDefaultApnRoamingProtocol, this.mRoamingProtocol);
        if (!TextUtils.isEmpty(this.mRoamingProtocol.getValue()) || TextUtils.isEmpty(protocolDescription2)) {
            return;
        }
        this.mRoamingProtocol.setValue(this.mDefaultApnRoamingProtocol);
        this.mRoamingProtocol.setSummary(protocolDescription2);
    }

    /* loaded from: classes.dex */
    public static class ErrorDialog extends InstrumentedDialogFragment {
        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 530;
        }

        public static void showError(ApnEditor apnEditor) {
            ErrorDialog errorDialog = new ErrorDialog();
            errorDialog.setTargetFragment(apnEditor, 0);
            errorDialog.show(apnEditor.getFragmentManager(), "error");
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            return new AlertDialog.Builder(getContext()).setTitle(R.string.error_title).setPositiveButton(17039370, (DialogInterface.OnClickListener) null).setMessage(((ApnEditor) getTargetFragment()).validateApnData()).create();
        }
    }

    ApnData getApnDataFromUri(Uri uri) {
        ApnData apnData;
        Cursor query = getContentResolver().query(uri, sProjection, null, null, null);
        if (query != null) {
            try {
                query.moveToFirst();
                apnData = new ApnData(uri, query);
            } catch (Throwable th) {
                try {
                    query.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } else {
            apnData = null;
        }
        if (query != null) {
            query.close();
        }
        if (apnData == null) {
            String str = TAG;
            Log.d(str, "Can't get apnData from Uri " + uri);
        }
        return apnData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ApnData {
        Object[] mData;
        Uri mUri;

        ApnData(int i) {
            this.mData = new Object[i];
        }

        ApnData(Uri uri, Cursor cursor) {
            this.mUri = uri;
            this.mData = new Object[cursor.getColumnCount()];
            for (int i = 0; i < this.mData.length; i++) {
                int type = cursor.getType(i);
                if (type == 1) {
                    this.mData[i] = Integer.valueOf(cursor.getInt(i));
                } else if (type == 2) {
                    this.mData[i] = Float.valueOf(cursor.getFloat(i));
                } else if (type == 3) {
                    this.mData[i] = cursor.getString(i);
                } else if (type == 4) {
                    this.mData[i] = cursor.getBlob(i);
                } else {
                    this.mData[i] = null;
                }
            }
        }

        Uri getUri() {
            return this.mUri;
        }

        Integer getInteger(int i) {
            return (Integer) this.mData[i];
        }

        Integer getInteger(int i, Integer num) {
            Integer integer = getInteger(i);
            return integer == null ? num : integer;
        }

        String getString(int i) {
            return (String) this.mData[i];
        }
    }
}
