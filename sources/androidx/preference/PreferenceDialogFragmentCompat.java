package androidx.preference;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.preference.DialogPreference;
/* loaded from: classes.dex */
public abstract class PreferenceDialogFragmentCompat extends DialogFragment implements DialogInterface.OnClickListener {
    private BitmapDrawable mDialogIcon;
    private int mDialogLayoutRes;
    private CharSequence mDialogMessage;
    private CharSequence mDialogTitle;
    private CharSequence mNegativeButtonText;
    private CharSequence mPositiveButtonText;
    private DialogPreference mPreference;
    private int mWhichButtonClicked;

    protected boolean needInputMethod() {
        return false;
    }

    public abstract void onDialogClosed(boolean z);

    /* JADX INFO: Access modifiers changed from: protected */
    public void onPrepareDialogBuilder(AlertDialog.Builder builder) {
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Fragment targetFragment = getTargetFragment();
        if (!(targetFragment instanceof DialogPreference.TargetFragment)) {
            throw new IllegalStateException("Target fragment must implement TargetFragment interface");
        }
        DialogPreference.TargetFragment targetFragment2 = (DialogPreference.TargetFragment) targetFragment;
        String string = getArguments().getString("key");
        if (bundle == null) {
            DialogPreference dialogPreference = (DialogPreference) targetFragment2.findPreference(string);
            this.mPreference = dialogPreference;
            this.mDialogTitle = dialogPreference.getDialogTitle();
            this.mPositiveButtonText = this.mPreference.getPositiveButtonText();
            this.mNegativeButtonText = this.mPreference.getNegativeButtonText();
            this.mDialogMessage = this.mPreference.getDialogMessage();
            this.mDialogLayoutRes = this.mPreference.getDialogLayoutResource();
            Drawable dialogIcon = this.mPreference.getDialogIcon();
            if (dialogIcon == null || (dialogIcon instanceof BitmapDrawable)) {
                this.mDialogIcon = (BitmapDrawable) dialogIcon;
                return;
            }
            Bitmap createBitmap = Bitmap.createBitmap(dialogIcon.getIntrinsicWidth(), dialogIcon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            dialogIcon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            dialogIcon.draw(canvas);
            this.mDialogIcon = new BitmapDrawable(getResources(), createBitmap);
            return;
        }
        this.mDialogTitle = bundle.getCharSequence("PreferenceDialogFragment.title");
        this.mPositiveButtonText = bundle.getCharSequence("PreferenceDialogFragment.positiveText");
        this.mNegativeButtonText = bundle.getCharSequence("PreferenceDialogFragment.negativeText");
        this.mDialogMessage = bundle.getCharSequence("PreferenceDialogFragment.message");
        this.mDialogLayoutRes = bundle.getInt("PreferenceDialogFragment.layout", 0);
        Bitmap bitmap = (Bitmap) bundle.getParcelable("PreferenceDialogFragment.icon");
        if (bitmap != null) {
            this.mDialogIcon = new BitmapDrawable(getResources(), bitmap);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("PreferenceDialogFragment.title", this.mDialogTitle);
        bundle.putCharSequence("PreferenceDialogFragment.positiveText", this.mPositiveButtonText);
        bundle.putCharSequence("PreferenceDialogFragment.negativeText", this.mNegativeButtonText);
        bundle.putCharSequence("PreferenceDialogFragment.message", this.mDialogMessage);
        bundle.putInt("PreferenceDialogFragment.layout", this.mDialogLayoutRes);
        BitmapDrawable bitmapDrawable = this.mDialogIcon;
        if (bitmapDrawable != null) {
            bundle.putParcelable("PreferenceDialogFragment.icon", bitmapDrawable.getBitmap());
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        this.mWhichButtonClicked = -2;
        AlertDialog.Builder negativeButton = new AlertDialog.Builder(getContext()).setTitle(this.mDialogTitle).setIcon(this.mDialogIcon).setPositiveButton(this.mPositiveButtonText, this).setNegativeButton(this.mNegativeButtonText, this);
        View onCreateDialogView = onCreateDialogView(getContext());
        if (onCreateDialogView != null) {
            onBindDialogView(onCreateDialogView);
            negativeButton.setView(onCreateDialogView);
        } else {
            negativeButton.setMessage(this.mDialogMessage);
        }
        onPrepareDialogBuilder(negativeButton);
        AlertDialog create = negativeButton.create();
        if (needInputMethod()) {
            requestInputMethod(create);
        }
        return create;
    }

    public DialogPreference getPreference() {
        if (this.mPreference == null) {
            this.mPreference = (DialogPreference) ((DialogPreference.TargetFragment) getTargetFragment()).findPreference(getArguments().getString("key"));
        }
        return this.mPreference;
    }

    private void requestInputMethod(Dialog dialog) {
        Api30Impl.showIme(dialog.getWindow());
    }

    protected View onCreateDialogView(Context context) {
        int i = this.mDialogLayoutRes;
        if (i == 0) {
            return null;
        }
        return getLayoutInflater().inflate(i, (ViewGroup) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onBindDialogView(View view) {
        View findViewById = view.findViewById(16908299);
        if (findViewById != null) {
            CharSequence charSequence = this.mDialogMessage;
            int i = 8;
            if (!TextUtils.isEmpty(charSequence)) {
                if (findViewById instanceof TextView) {
                    ((TextView) findViewById).setText(charSequence);
                }
                i = 0;
            }
            if (findViewById.getVisibility() != i) {
                findViewById.setVisibility(i);
            }
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.mWhichButtonClicked = i;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        onDialogClosed(this.mWhichButtonClicked == -1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Api30Impl {
        static void showIme(Window window) {
            window.getDecorView().getWindowInsetsController().show(WindowInsets.Type.ime());
        }
    }
}
