package com.android.settings.fuelgauge.batteryusage;

import androidx.core.util.Preconditions;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class BatteryChartViewModel {
    private final AxisLabelPosition mAxisLabelPosition;
    private final String[] mFullTexts;
    private final LabelTextGenerator mLabelTextGenerator;
    private final List<Integer> mLevels;
    private int mSelectedIndex = -1;
    private final String[] mTexts;
    private final List<Long> mTimestamps;

    /* loaded from: classes.dex */
    enum AxisLabelPosition {
        BETWEEN_TRAPEZOIDS,
        CENTER_OF_TRAPEZOIDS
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface LabelTextGenerator {
        String generateFullText(List<Long> list, int i);

        String generateText(List<Long> list, int i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BatteryChartViewModel(List<Integer> list, List<Long> list2, AxisLabelPosition axisLabelPosition, LabelTextGenerator labelTextGenerator) {
        Preconditions.checkArgument(list.size() == list2.size() && list.size() >= 2, String.format(Locale.ENGLISH, "Invalid BatteryChartViewModel levels.size: %d, timestamps.size: %d.", Integer.valueOf(list.size()), Integer.valueOf(list2.size())));
        this.mLevels = list;
        this.mTimestamps = list2;
        this.mAxisLabelPosition = axisLabelPosition;
        this.mLabelTextGenerator = labelTextGenerator;
        this.mTexts = new String[size()];
        this.mFullTexts = new String[size()];
    }

    public int size() {
        return this.mLevels.size();
    }

    public Integer getLevel(int i) {
        return this.mLevels.get(i);
    }

    public String getText(int i) {
        String[] strArr = this.mTexts;
        if (strArr[i] == null) {
            strArr[i] = this.mLabelTextGenerator.generateText(this.mTimestamps, i);
        }
        return this.mTexts[i];
    }

    public String getFullText(int i) {
        String[] strArr = this.mFullTexts;
        if (strArr[i] == null) {
            strArr[i] = this.mLabelTextGenerator.generateFullText(this.mTimestamps, i);
        }
        return this.mFullTexts[i];
    }

    public AxisLabelPosition axisLabelPosition() {
        return this.mAxisLabelPosition;
    }

    public int selectedIndex() {
        return this.mSelectedIndex;
    }

    public void setSelectedIndex(int i) {
        this.mSelectedIndex = i;
    }

    public int hashCode() {
        return Objects.hash(this.mLevels, this.mTimestamps, Integer.valueOf(this.mSelectedIndex), this.mAxisLabelPosition);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BatteryChartViewModel) {
            BatteryChartViewModel batteryChartViewModel = (BatteryChartViewModel) obj;
            return Objects.equals(this.mLevels, batteryChartViewModel.mLevels) && Objects.equals(this.mTimestamps, batteryChartViewModel.mTimestamps) && this.mAxisLabelPosition == batteryChartViewModel.mAxisLabelPosition && this.mSelectedIndex == batteryChartViewModel.mSelectedIndex;
        }
        return false;
    }

    public String toString() {
        for (int i = 0; i < size(); i++) {
            getText(i);
            getFullText(i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("levels: " + Objects.toString(this.mLevels));
        sb.append(", timestamps: " + Objects.toString(this.mTimestamps));
        sb.append(", texts: " + Arrays.toString(this.mTexts));
        sb.append(", fullTexts: " + Arrays.toString(this.mFullTexts));
        sb.append(", axisLabelPosition: " + this.mAxisLabelPosition);
        sb.append(", selectedIndex: " + this.mSelectedIndex);
        return sb.toString();
    }
}
