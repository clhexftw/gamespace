package com.android.settings;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.android.settingslib.license.LicenseHtmlLoaderCompat;
import java.io.File;
/* loaded from: classes.dex */
public class SettingsLicenseActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<File> {
    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<File> loader) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        File file = new File("/system/etc/NOTICE.html.gz");
        if (isFileValid(file)) {
            showHtmlFromUri(Uri.fromFile(file));
        } else {
            showHtmlFromDefaultXmlFiles();
        }
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public Loader<File> onCreateLoader(int i, Bundle bundle) {
        return new LicenseHtmlLoaderCompat(this);
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoadFinished(Loader<File> loader, File file) {
        showGeneratedHtmlFile(file);
    }

    private void showHtmlFromDefaultXmlFiles() {
        getSupportLoaderManager().initLoader(0, Bundle.EMPTY, this);
    }

    Uri getUriFromGeneratedHtmlFile(File file) {
        return FileProvider.getUriForFile(this, "com.android.settings.files", file);
    }

    private void showGeneratedHtmlFile(File file) {
        if (file != null) {
            showHtmlFromUri(getUriFromGeneratedHtmlFile(file));
            return;
        }
        Log.e("SettingsLicenseActivity", "Failed to generate.");
        showErrorAndFinish();
    }

    private void showHtmlFromUri(Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "text/html");
        intent.putExtra("android.intent.extra.TITLE", getString(R.string.settings_license_activity_title));
        if ("content".equals(uri.getScheme())) {
            intent.addFlags(1);
        }
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setPackage("com.android.htmlviewer");
        try {
            startActivity(intent);
            finish();
        } catch (ActivityNotFoundException e) {
            Log.e("SettingsLicenseActivity", "Failed to find viewer", e);
            showErrorAndFinish();
        }
    }

    private void showErrorAndFinish() {
        Toast.makeText(this, R.string.settings_license_activity_unavailable, 1).show();
        finish();
    }

    boolean isFileValid(File file) {
        return file.exists() && file.length() != 0;
    }
}
