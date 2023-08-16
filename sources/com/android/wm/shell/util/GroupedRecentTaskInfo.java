package com.android.wm.shell.util;

import android.app.ActivityManager;
import android.app.WindowConfiguration;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes2.dex */
public class GroupedRecentTaskInfo implements Parcelable {
    public static final Parcelable.Creator<GroupedRecentTaskInfo> CREATOR = new Parcelable.Creator<GroupedRecentTaskInfo>() { // from class: com.android.wm.shell.util.GroupedRecentTaskInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GroupedRecentTaskInfo createFromParcel(Parcel parcel) {
            return new GroupedRecentTaskInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GroupedRecentTaskInfo[] newArray(int i) {
            return new GroupedRecentTaskInfo[i];
        }
    };
    private final SplitBounds mSplitBounds;
    private final ActivityManager.RecentTaskInfo[] mTasks;
    private final int mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    GroupedRecentTaskInfo(Parcel parcel) {
        this.mTasks = (ActivityManager.RecentTaskInfo[]) parcel.createTypedArray(ActivityManager.RecentTaskInfo.CREATOR);
        this.mSplitBounds = (SplitBounds) parcel.readTypedObject(SplitBounds.CREATOR);
        this.mType = parcel.readInt();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < this.mTasks.length) {
            if (i == 0) {
                sb.append("Task");
            } else {
                sb.append(", Task");
            }
            int i2 = i + 1;
            sb.append(i2);
            sb.append(": ");
            sb.append(getTaskInfo(this.mTasks[i]));
            i = i2;
        }
        if (this.mSplitBounds != null) {
            sb.append(", SplitBounds: ");
            sb.append(this.mSplitBounds);
        }
        sb.append(", Type=");
        int i3 = this.mType;
        if (i3 == 1) {
            sb.append("TYPE_SINGLE");
        } else if (i3 == 2) {
            sb.append("TYPE_SPLIT");
        } else if (i3 == 3) {
            sb.append("TYPE_FREEFORM");
        }
        return sb.toString();
    }

    private String getTaskInfo(ActivityManager.RecentTaskInfo recentTaskInfo) {
        if (recentTaskInfo == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("id=");
        sb.append(recentTaskInfo.taskId);
        sb.append(" baseIntent=");
        Intent intent = recentTaskInfo.baseIntent;
        sb.append(intent != null ? intent.getComponent() : "null");
        sb.append(" winMode=");
        sb.append(WindowConfiguration.windowingModeToString(recentTaskInfo.getWindowingMode()));
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.mTasks, i);
        parcel.writeTypedObject(this.mSplitBounds, i);
        parcel.writeInt(this.mType);
    }
}
