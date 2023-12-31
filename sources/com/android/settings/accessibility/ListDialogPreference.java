package com.android.settings.accessibility;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import com.android.settingslib.CustomDialogPreferenceCompat;
/* loaded from: classes.dex */
public abstract class ListDialogPreference extends CustomDialogPreferenceCompat {
    private CharSequence[] mEntryTitles;
    private int[] mEntryValues;
    private int mListItemLayout;
    private OnValueChangedListener mOnValueChangedListener;
    private int mValue;
    private int mValueIndex;
    private boolean mValueSet;

    /* loaded from: classes.dex */
    public interface OnValueChangedListener {
        void onValueChanged(ListDialogPreference listDialogPreference, int i);
    }

    protected abstract void onBindListItem(View view, int i);

    public ListDialogPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnValueChangedListener(OnValueChangedListener onValueChangedListener) {
        this.mOnValueChangedListener = onValueChangedListener;
    }

    public void setListItemLayoutResource(int i) {
        this.mListItemLayout = i;
    }

    public void setValues(int[] iArr) {
        this.mEntryValues = iArr;
        if (this.mValueSet && this.mValueIndex == -1) {
            this.mValueIndex = getIndexForValue(this.mValue);
        }
    }

    public void setTitles(CharSequence[] charSequenceArr) {
        this.mEntryTitles = charSequenceArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CharSequence getTitleAt(int i) {
        CharSequence[] charSequenceArr = this.mEntryTitles;
        if (charSequenceArr == null || charSequenceArr.length <= i) {
            return null;
        }
        return charSequenceArr[i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getValueAt(int i) {
        return this.mEntryValues[i];
    }

    @Override // androidx.preference.Preference
    public CharSequence getSummary() {
        int i = this.mValueIndex;
        if (i >= 0) {
            return getTitleAt(i);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.CustomDialogPreferenceCompat
    public void onPrepareDialogBuilder(AlertDialog.Builder builder, DialogInterface.OnClickListener onClickListener) {
        super.onPrepareDialogBuilder(builder, onClickListener);
        Context context = getContext();
        View inflate = LayoutInflater.from(context).inflate(getDialogLayoutResource(), (ViewGroup) null);
        ListPreferenceAdapter listPreferenceAdapter = new ListPreferenceAdapter();
        AbsListView absListView = (AbsListView) inflate.findViewById(16908298);
        absListView.setAdapter((ListAdapter) listPreferenceAdapter);
        absListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.android.settings.accessibility.ListDialogPreference.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int i2 = (int) j;
                if (ListDialogPreference.this.callChangeListener(Integer.valueOf(i2))) {
                    ListDialogPreference.this.setValue(i2);
                }
                Dialog dialog = ListDialogPreference.this.getDialog();
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        int indexForValue = getIndexForValue(this.mValue);
        if (indexForValue != -1) {
            absListView.setSelection(indexForValue);
        }
        builder.setView(inflate);
        builder.setPositiveButton((CharSequence) null, (DialogInterface.OnClickListener) null);
    }

    protected int getIndexForValue(int i) {
        int[] iArr = this.mEntryValues;
        if (iArr != null) {
            int length = iArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (iArr[i2] == i) {
                    return i2;
                }
            }
            return -1;
        }
        return -1;
    }

    public void setValue(int i) {
        boolean z = this.mValue != i;
        if (z || !this.mValueSet) {
            this.mValue = i;
            this.mValueIndex = getIndexForValue(i);
            this.mValueSet = true;
            persistInt(i);
            if (z) {
                notifyDependencyChange(shouldDisableDependents());
                notifyChanged();
            }
            OnValueChangedListener onValueChangedListener = this.mOnValueChangedListener;
            if (onValueChangedListener != null) {
                onValueChangedListener.onValueChanged(this, i);
            }
        }
    }

    public int getValue() {
        return this.mValue;
    }

    @Override // androidx.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Integer.valueOf(typedArray.getInt(i, 0));
    }

    @Override // androidx.preference.Preference
    protected void onSetInitialValue(boolean z, Object obj) {
        setValue(z ? getPersistedInt(this.mValue) : ((Integer) obj).intValue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (isPersistent()) {
            return onSaveInstanceState;
        }
        SavedState savedState = new SavedState(onSaveInstanceState);
        savedState.value = getValue();
        return savedState;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setValue(savedState.value);
    }

    /* loaded from: classes.dex */
    private class ListPreferenceAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }

        private ListPreferenceAdapter() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return ListDialogPreference.this.mEntryValues.length;
        }

        @Override // android.widget.Adapter
        public Integer getItem(int i) {
            return Integer.valueOf(ListDialogPreference.this.mEntryValues[i]);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return ListDialogPreference.this.mEntryValues[i];
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                if (this.mInflater == null) {
                    this.mInflater = LayoutInflater.from(viewGroup.getContext());
                }
                view = this.mInflater.inflate(ListDialogPreference.this.mListItemLayout, viewGroup, false);
            }
            ListDialogPreference.this.onBindListItem(view, i);
            return view;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.android.settings.accessibility.ListDialogPreference.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        public int value;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.value = parcel.readInt();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.value);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
