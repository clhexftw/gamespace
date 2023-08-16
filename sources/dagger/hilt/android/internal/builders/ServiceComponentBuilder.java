package dagger.hilt.android.internal.builders;

import android.app.Service;
import dagger.hilt.android.components.ServiceComponent;
/* loaded from: classes.dex */
public interface ServiceComponentBuilder {
    ServiceComponent build();

    ServiceComponentBuilder service(Service service);
}
