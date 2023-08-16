package org.nameless.gamespace.utils.di;

import org.nameless.gamespace.data.AppSettings;
import org.nameless.gamespace.data.SystemSettings;
import org.nameless.gamespace.utils.GameModeUtils;
import org.nameless.gamespace.utils.ScreenUtils;
/* compiled from: ServiceViewEntryPoint.kt */
/* loaded from: classes.dex */
public interface ServiceViewEntryPoint {
    AppSettings appSettings();

    GameModeUtils gameModeUtils();

    ScreenUtils screenUtils();

    SystemSettings systemSettings();
}
