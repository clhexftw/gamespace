package ink.kscope.settings.wifi.tether.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import com.android.settings.R;
import com.android.settings.SeekBarDialogPreference;
/* loaded from: classes2.dex */
public class WifiTetherClientLimitPreference extends SeekBarDialogPreference implements SeekBar.OnSeekBarChangeListener {
    private Context mContext;
    private int mMax;
    private int mMin;
    private SeekBar mSeekBar;
    private int mValue;

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public WifiTetherClientLimitPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        setText(getSummaryForValue(i + this.mMin));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settings.SeekBarDialogPreference, com.android.settingslib.CustomDialogPreferenceCompat
    public void onBindDialogView(View view) {
        super.onBindDialogView(view);
        SeekBar seekBar = SeekBarDialogPreference.getSeekBar(view);
        this.mSeekBar = seekBar;
        seekBar.setOnSeekBarChangeListener(this);
        this.mSeekBar.setMax(this.mMax - this.mMin);
        this.mSeekBar.setProgress(this.mValue - this.mMin);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.CustomDialogPreferenceCompat
    public void onDialogClosed(boolean z) {
        super.onDialogClosed(z);
        if (z) {
            setValue(this.mSeekBar.getProgress() + this.mMin, true);
        }
    }

    private String getSummaryForValue(int i) {
        return this.mContext.getResources().getQuantityString(R.plurals.wifi_hotspot_client_limit_summary, i, Integer.valueOf(i));
    }

    public void setMin(int i) {
        this.mMin = i;
    }

    public void setMax(int i) {
        this.mMax = i;
    }

    public void setValue(int i, boolean z) {
        if (i == 0) {
            i = this.mMax;
        }
        this.mValue = i;
        String summaryForValue = getSummaryForValue(i);
        setSummary(summaryForValue);
        setText(summaryForValue);
        if (z) {
            callChangeListener(Integer.valueOf(i));
        }
    }
}
