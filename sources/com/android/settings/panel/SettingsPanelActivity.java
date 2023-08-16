package com.android.settings.panel;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.R;
import com.android.settings.Utils;
import com.android.settingslib.core.lifecycle.HideNonSystemOverlayMixin;
/* loaded from: classes.dex */
public class SettingsPanelActivity extends FragmentActivity {
    @VisibleForTesting
    final Bundle mBundle = new Bundle();
    @VisibleForTesting
    boolean mForceCreation = false;
    @VisibleForTesting
    PanelFragment mPanelFragment;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getApplicationContext().getTheme().rebase();
        createOrUpdatePanel(true);
        getLifecycle().addObserver(new HideNonSystemOverlayMixin(this));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        createOrUpdatePanel(this.mForceCreation);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mForceCreation = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        PanelFragment panelFragment = this.mPanelFragment;
        if (panelFragment == null || panelFragment.isPanelCreating()) {
            return;
        }
        this.mForceCreation = true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mForceCreation = true;
    }

    private void createOrUpdatePanel(boolean z) {
        Intent intent = getIntent();
        if (intent == null) {
            Log.e("SettingsPanelActivity", "Null intent, closing Panel Activity");
            finish();
            return;
        }
        String action = intent.getAction();
        String stringExtra = intent.getStringExtra("package_name");
        this.mBundle.putString("PANEL_TYPE_ARGUMENT", action);
        this.mBundle.putString("PANEL_CALLING_PACKAGE_NAME", getCallingPackage());
        this.mBundle.putString("PANEL_MEDIA_PACKAGE_NAME", stringExtra);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        int i = R.id.main_content;
        Fragment findFragmentById = supportFragmentManager.findFragmentById(i);
        if (!z && findFragmentById != null && (findFragmentById instanceof PanelFragment)) {
            PanelFragment panelFragment = (PanelFragment) findFragmentById;
            this.mPanelFragment = panelFragment;
            if (panelFragment.isPanelCreating()) {
                Log.w("SettingsPanelActivity", "A panel is creating, skip " + action);
                return;
            }
            Bundle arguments = findFragmentById.getArguments();
            if (arguments != null && TextUtils.equals(action, arguments.getString("PANEL_TYPE_ARGUMENT"))) {
                Log.w("SettingsPanelActivity", "Panel is showing the same action, skip " + action);
                return;
            }
            this.mPanelFragment.setArguments(new Bundle(this.mBundle));
            this.mPanelFragment.updatePanelWithAnimation();
            return;
        }
        setContentView(R.layout.settings_panel);
        Window window = getWindow();
        window.setGravity(80);
        window.setLayout(-1, -2);
        setupNavigationBar();
        PanelFragment panelFragment2 = new PanelFragment();
        this.mPanelFragment = panelFragment2;
        panelFragment2.setArguments(new Bundle(this.mBundle));
        supportFragmentManager.beginTransaction().add(i, this.mPanelFragment).commit();
    }

    private void setupNavigationBar() {
        ViewCompat.setOnApplyWindowInsetsListener(getWindow().getDecorView(), new OnApplyWindowInsetsListener() { // from class: com.android.settings.panel.SettingsPanelActivity$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat lambda$setupNavigationBar$0;
                lambda$setupNavigationBar$0 = SettingsPanelActivity.lambda$setupNavigationBar$0(view, windowInsetsCompat);
                return lambda$setupNavigationBar$0;
            }
        });
        WindowInsetsControllerCompat windowInsetsController = ViewCompat.getWindowInsetsController(getWindow().getDecorView());
        if (windowInsetsController != null) {
            windowInsetsController.setAppearanceLightNavigationBars(!Utils.isNightMode(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ WindowInsetsCompat lambda$setupNavigationBar$0(View view, WindowInsetsCompat windowInsetsCompat) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), 0);
        return windowInsetsCompat;
    }
}
