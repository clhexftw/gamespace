package com.android.settings.homepage.contextualcards;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.ArrayMap;
import android.util.Log;
import com.android.settings.R;
import com.android.settingslib.utils.ThreadUtils;
/* loaded from: classes.dex */
public class CardContentProvider extends ContentProvider {
    private static final UriMatcher URI_MATCHER;
    public static final Uri REFRESH_CARD_URI = new Uri.Builder().scheme("content").authority("com.android.settings.homepage.CardContentProvider").appendPath("cards").build();
    public static final Uri DELETE_CARD_URI = new Uri.Builder().scheme("content").authority("com.android.settings.homepage.CardContentProvider").appendPath("dismissed_timestamp").build();

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        URI_MATCHER = uriMatcher;
        uriMatcher.addURI("com.android.settings.homepage.CardContentProvider", "cards", 100);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        bulkInsert(uri, new ContentValues[]{contentValues});
        return uri;
    }

    @Override // android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        SQLiteDatabase writableDatabase = CardDatabaseHelper.getInstance(getContext()).getWritableDatabase();
        boolean z = getContext().getResources().getBoolean(R.bool.config_keep_contextual_card_dismissal_timestamp);
        ArrayMap arrayMap = new ArrayMap();
        try {
            maybeEnableStrictMode();
            String tableFromMatch = getTableFromMatch(uri);
            writableDatabase.beginTransaction();
            if (z) {
                Cursor query = writableDatabase.query(tableFromMatch, new String[]{"name", "dismissed_timestamp"}, "dismissed_timestamp IS NOT NULL", null, null, null, null);
                query.moveToFirst();
                while (!query.isAfterLast()) {
                    arrayMap.put(query.getString(query.getColumnIndex("name")), Long.valueOf(query.getLong(query.getColumnIndex("dismissed_timestamp"))));
                    query.moveToNext();
                }
                query.close();
            }
            String str = null;
            writableDatabase.delete(tableFromMatch, null, null);
            int length = contentValuesArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                ContentValues contentValues = contentValuesArr[i];
                if (z) {
                    String obj = contentValues.get("name").toString();
                    if (arrayMap.containsKey(obj)) {
                        contentValues.put("dismissed_timestamp", (Long) arrayMap.get(obj));
                        Log.d("CardContentProvider", "Replace dismissed time: " + obj);
                        str = null;
                    }
                }
                if (writableDatabase.insert(tableFromMatch, str, contentValues) != -1) {
                    i2++;
                } else {
                    Log.e("CardContentProvider", "The row " + contentValues.getAsString("name") + " insertion failed! Please check your data.");
                }
                i++;
                str = null;
            }
            writableDatabase.setTransactionSuccessful();
            getContext().getContentResolver().notifyChange(uri, null);
            return i2;
        } finally {
            writableDatabase.endTransaction();
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("delete operation not supported currently.");
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("getType operation not supported currently.");
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            maybeEnableStrictMode();
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables(getTableFromMatch(uri));
            Cursor query = sQLiteQueryBuilder.query(CardDatabaseHelper.getInstance(getContext()).getReadableDatabase(), strArr, str, strArr2, null, null, str2);
            query.setNotificationUri(getContext().getContentResolver(), uri);
            return query;
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("update operation not supported currently.");
    }

    void maybeEnableStrictMode() {
        if (Build.IS_ENG && ThreadUtils.isMainThread()) {
            enableStrictMode();
        }
    }

    void enableStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().build());
    }

    String getTableFromMatch(Uri uri) {
        if (URI_MATCHER.match(uri) == 100) {
            return "cards";
        }
        throw new IllegalArgumentException("Unknown Uri format: " + uri);
    }
}
