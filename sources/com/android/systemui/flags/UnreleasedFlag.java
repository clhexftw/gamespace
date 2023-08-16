package com.android.systemui.flags;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: Flag.kt */
/* loaded from: classes2.dex */
public final class UnreleasedFlag extends BooleanFlag {
    private final int id;
    private final String name;
    private final String namespace;
    private final boolean overridden;
    private final boolean teamfood;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UnreleasedFlag) {
            UnreleasedFlag unreleasedFlag = (UnreleasedFlag) obj;
            return getId() == unreleasedFlag.getId() && Intrinsics.areEqual(getName(), unreleasedFlag.getName()) && Intrinsics.areEqual(getNamespace(), unreleasedFlag.getNamespace()) && getTeamfood() == unreleasedFlag.getTeamfood() && getOverridden() == unreleasedFlag.getOverridden();
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((((Integer.hashCode(getId()) * 31) + getName().hashCode()) * 31) + getNamespace().hashCode()) * 31;
        boolean teamfood = getTeamfood();
        int i = teamfood;
        if (teamfood) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        boolean overridden = getOverridden();
        return i2 + (overridden ? 1 : overridden);
    }

    public String toString() {
        int id = getId();
        String name = getName();
        String namespace = getNamespace();
        boolean teamfood = getTeamfood();
        boolean overridden = getOverridden();
        return "UnreleasedFlag(id=" + id + ", name=" + name + ", namespace=" + namespace + ", teamfood=" + teamfood + ", overridden=" + overridden + ")";
    }

    @Override // com.android.systemui.flags.BooleanFlag
    public int getId() {
        return this.id;
    }

    @Override // com.android.systemui.flags.BooleanFlag
    public String getName() {
        return this.name;
    }

    @Override // com.android.systemui.flags.BooleanFlag
    public String getNamespace() {
        return this.namespace;
    }

    @Override // com.android.systemui.flags.BooleanFlag
    public boolean getTeamfood() {
        return this.teamfood;
    }

    @Override // com.android.systemui.flags.BooleanFlag
    public boolean getOverridden() {
        return this.overridden;
    }
}
