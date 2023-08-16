package com.android.settingslib.inputmethod;

import android.content.ContentResolver;
import android.content.Context;
import android.util.SparseArray;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import com.android.internal.annotations.GuardedBy;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class InputMethodSettingValuesWrapper {
    private final ContentResolver mContentResolver;
    private final InputMethodManager mImm;
    private final ArrayList<InputMethodInfo> mMethodList = new ArrayList<>();
    private static final Object sInstanceMapLock = new Object();
    @GuardedBy({"sInstanceMapLock"})
    private static SparseArray<InputMethodSettingValuesWrapper> sInstanceMap = new SparseArray<>();

    public static InputMethodSettingValuesWrapper getInstance(Context context) {
        int userId = context.getUserId();
        synchronized (sInstanceMapLock) {
            if (sInstanceMap.size() == 0) {
                InputMethodSettingValuesWrapper inputMethodSettingValuesWrapper = new InputMethodSettingValuesWrapper(context);
                sInstanceMap.put(userId, inputMethodSettingValuesWrapper);
                return inputMethodSettingValuesWrapper;
            } else if (sInstanceMap.indexOfKey(userId) >= 0) {
                return sInstanceMap.get(userId);
            } else {
                InputMethodSettingValuesWrapper inputMethodSettingValuesWrapper2 = new InputMethodSettingValuesWrapper(context);
                sInstanceMap.put(context.getUserId(), inputMethodSettingValuesWrapper2);
                return inputMethodSettingValuesWrapper2;
            }
        }
    }

    private InputMethodSettingValuesWrapper(Context context) {
        this.mContentResolver = context.getContentResolver();
        this.mImm = (InputMethodManager) context.getSystemService(InputMethodManager.class);
        refreshAllInputMethodAndSubtypes();
    }

    public void refreshAllInputMethodAndSubtypes() {
        this.mMethodList.clear();
        this.mMethodList.addAll(this.mImm.getInputMethodListAsUser(this.mContentResolver.getUserId(), 1));
    }
}
