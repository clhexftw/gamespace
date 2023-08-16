package com.android.settings.deviceinfo;

import android.app.ActivityManager;
import android.os.Bundle;
import android.os.UserManager;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.android.settings.R;
import com.android.settings.overlay.FeatureFactory;
/* loaded from: classes.dex */
public class StorageWizardInit extends StorageWizardBase {
    private View.OnTouchListener listener = new View.OnTouchListener() { // from class: com.android.settings.deviceinfo.StorageWizardInit.1
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return motionEvent.getAction() == 1 && !isInside(view, motionEvent);
        }

        private boolean isInside(View view, MotionEvent motionEvent) {
            return motionEvent.getX() >= 0.0f && motionEvent.getY() >= 0.0f && motionEvent.getX() <= ((float) view.getMeasuredWidth()) && motionEvent.getY() <= ((float) view.getMeasuredHeight());
        }
    };
    private ViewFlipper mFlipper;
    private boolean mIsPermittedToAdopt;
    private boolean mPortable;

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("IS_PORTABLE", this.mPortable);
        super.onSaveInstanceState(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.deviceinfo.StorageWizardBase, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mDisk == null) {
            finish();
            return;
        }
        boolean z = UserManager.get(this).isAdminUser() && !ActivityManager.isUserAMonkey();
        this.mIsPermittedToAdopt = z;
        if (!z) {
            Toast.makeText(getApplicationContext(), R.string.storage_wizard_guest, 1).show();
            finish();
            return;
        }
        setContentView(R.layout.storage_wizard_init);
        setupHyperlink();
        this.mPortable = true;
        this.mFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        if (bundle != null) {
            this.mPortable = bundle.getBoolean("IS_PORTABLE");
        }
        if (this.mPortable) {
            this.mFlipper.setDisplayedChild(0);
            setHeaderText(R.string.storage_wizard_init_v2_external_title, getDiskShortDescription());
            setNextButtonText(R.string.storage_wizard_init_v2_external_action, new CharSequence[0]);
            setBackButtonText(R.string.wizard_back_adoptable, new CharSequence[0]);
            setNextButtonVisibility(0);
            if (this.mDisk.isAdoptable()) {
                return;
            }
            setBackButtonVisibility(8);
            return;
        }
        this.mFlipper.setDisplayedChild(1);
        setHeaderText(R.string.storage_wizard_init_v2_internal_title, getDiskShortDescription());
        setNextButtonText(R.string.storage_wizard_init_v2_internal_action, new CharSequence[0]);
        setBackButtonText(R.string.wizard_back_adoptable, new CharSequence[0]);
        setNextButtonVisibility(0);
    }

    @Override // com.android.settings.deviceinfo.StorageWizardBase
    public void onNavigateBack(View view) {
        if (!this.mIsPermittedToAdopt) {
            view.setEnabled(false);
        } else if (!this.mPortable) {
            this.mFlipper.showNext();
            setHeaderText(R.string.storage_wizard_init_v2_external_title, getDiskShortDescription());
            setNextButtonText(R.string.storage_wizard_init_v2_external_action, new CharSequence[0]);
            setBackButtonText(R.string.wizard_back_adoptable, new CharSequence[0]);
            setBackButtonVisibility(0);
            this.mPortable = true;
        } else {
            this.mFlipper.showNext();
            setHeaderText(R.string.storage_wizard_init_v2_internal_title, getDiskShortDescription());
            setNextButtonText(R.string.storage_wizard_init_v2_internal_action, new CharSequence[0]);
            setBackButtonText(R.string.wizard_back_adoptable, new CharSequence[0]);
            setBackButtonVisibility(0);
            this.mPortable = false;
        }
    }

    @Override // com.android.settings.deviceinfo.StorageWizardBase
    public void onNavigateNext(View view) {
        if (this.mPortable) {
            onNavigateExternal(view);
        } else {
            onNavigateInternal(view);
        }
    }

    public void onNavigateExternal(View view) {
        if (view != null) {
            FeatureFactory.getFactory(this).getMetricsFeatureProvider().action(this, 1407, new Pair[0]);
        }
        StorageWizardFormatConfirm.showPublic(this, this.mDisk.getId());
    }

    public void onNavigateInternal(View view) {
        if (view != null) {
            FeatureFactory.getFactory(this).getMetricsFeatureProvider().action(this, 1408, new Pair[0]);
        }
        StorageWizardFormatConfirm.showPrivate(this, this.mDisk.getId());
    }

    private void setupHyperlink() {
        TextView textView = (TextView) findViewById(R.id.storage_wizard_init_external_text);
        TextView textView2 = (TextView) findViewById(R.id.storage_wizard_init_internal_text);
        String string = getResources().getString(R.string.storage_wizard_init_v2_external_summary);
        String string2 = getResources().getString(R.string.storage_wizard_init_v2_internal_summary);
        Spannable styleFont = styleFont(string);
        Spannable styleFont2 = styleFont(string2);
        textView.setText(styleFont);
        textView2.setText(styleFont2);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setOnTouchListener(this.listener);
        textView2.setOnTouchListener(this.listener);
    }

    private Spannable styleFont(String str) {
        URLSpan[] uRLSpanArr;
        Spannable spannable = (Spannable) Html.fromHtml(str);
        for (URLSpan uRLSpan : (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class)) {
            spannable.setSpan(new TypefaceSpan("sans-serif-medium"), spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 0);
        }
        return spannable;
    }
}
