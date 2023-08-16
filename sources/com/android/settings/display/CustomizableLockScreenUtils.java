package com.android.settings.display;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.settings.R;
import java.util.ArrayList;
/* loaded from: classes.dex */
public final class CustomizableLockScreenUtils {
    static final String AFFORDANCE_NAME = "affordance_name";
    private static final Uri BASE_URI;
    static final String ENABLED_FLAG = "is_custom_lock_screen_quick_affordances_feature_enabled";
    static final Uri FLAGS_URI;
    static final String NAME = "name";
    static final Uri SELECTIONS_URI;
    static final String VALUE = "value";

    static {
        Uri build = new Uri.Builder().scheme("content").authority("com.android.systemui.customization").build();
        BASE_URI = build;
        FLAGS_URI = build.buildUpon().path("flags").build();
        SELECTIONS_URI = build.buildUpon().appendPath("lockscreen_quickaffordance").appendPath("selections").build();
    }

    public static boolean isFeatureEnabled(Context context) {
        try {
            Cursor query = context.getContentResolver().query(FLAGS_URI, null, null, null);
            if (query == null) {
                Log.w("CustomizableLockScreenUtils", "Cursor was null!");
                if (query != null) {
                    query.close();
                }
                return false;
            }
            int columnIndex = query.getColumnIndex(NAME);
            int columnIndex2 = query.getColumnIndex(VALUE);
            if (columnIndex != -1 && columnIndex2 != -1) {
                while (query.moveToNext()) {
                    String string = query.getString(columnIndex);
                    int i = query.getInt(columnIndex2);
                    if (TextUtils.equals(ENABLED_FLAG, string)) {
                        Log.d("CustomizableLockScreenUtils", "is_custom_lock_screen_quick_affordances_feature_enabled=" + i);
                        boolean z = i == 1;
                        query.close();
                        return z;
                    }
                }
                Log.w("CustomizableLockScreenUtils", "Flag with name \"is_custom_lock_screen_quick_affordances_feature_enabled\" not found!");
                query.close();
                return false;
            }
            Log.w("CustomizableLockScreenUtils", "Cursor doesn't contain name or value!");
            query.close();
            return false;
        } catch (Exception e) {
            Log.e("CustomizableLockScreenUtils", "Exception while querying quick affordance content provider", e);
            return false;
        }
    }

    public static CharSequence getQuickAffordanceSummary(Context context) {
        try {
            Cursor query = context.getContentResolver().query(SELECTIONS_URI, null, null, null);
            if (query == null) {
                Log.w("CustomizableLockScreenUtils", "Cursor was null!");
                if (query != null) {
                    query.close();
                }
                return null;
            }
            int columnIndex = query.getColumnIndex(AFFORDANCE_NAME);
            if (columnIndex == -1) {
                Log.w("CustomizableLockScreenUtils", "Cursor doesn't contain \"affordance_name\" column!");
                query.close();
                return null;
            }
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                String string = query.getString(columnIndex);
                if (!TextUtils.isEmpty(string)) {
                    arrayList.add(string);
                }
            }
            int min = Math.min(2, arrayList.size());
            ArrayList arrayList2 = new ArrayList(min);
            if (!arrayList.isEmpty()) {
                arrayList2.add((String) arrayList.get(0));
            }
            if (arrayList.size() > 1) {
                arrayList2.add((String) arrayList.get(1));
            }
            String quantityString = context.getResources().getQuantityString(R.plurals.lockscreen_quick_affordances_summary, min, arrayList2.toArray());
            query.close();
            return quantityString;
        } catch (Exception e) {
            Log.e("CustomizableLockScreenUtils", "Exception while querying quick affordance content provider", e);
            return null;
        }
    }
}
