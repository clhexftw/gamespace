package com.android.settings.homepage.contextualcards;

import android.net.Uri;
import com.android.settings.intelligence.ContextualCardProto$ContextualCard;
import com.android.settings.intelligence.ContextualCardProto$ContextualCardList;
import com.android.settings.slices.CustomSliceRegistry;
import com.google.android.settings.intelligence.libs.contextualcards.ContextualCardProvider;
/* loaded from: classes.dex */
public class SettingsContextualCardProvider extends ContextualCardProvider {
    @Override // com.google.android.settings.intelligence.libs.contextualcards.ContextualCardProvider
    public ContextualCardProto$ContextualCardList getContextualCards() {
        ContextualCardProto$ContextualCard.Builder newBuilder = ContextualCardProto$ContextualCard.newBuilder();
        Uri uri = CustomSliceRegistry.CONTEXTUAL_WIFI_SLICE_URI;
        ContextualCardProto$ContextualCard.Builder cardName = newBuilder.setSliceUri(uri.toString()).setCardName(uri.toString());
        ContextualCardProto$ContextualCard.Category category = ContextualCardProto$ContextualCard.Category.IMPORTANT;
        ContextualCardProto$ContextualCard.Builder newBuilder2 = ContextualCardProto$ContextualCard.newBuilder();
        Uri uri2 = CustomSliceRegistry.BLUETOOTH_DEVICES_SLICE_URI;
        ContextualCardProto$ContextualCard.Builder newBuilder3 = ContextualCardProto$ContextualCard.newBuilder();
        Uri uri3 = CustomSliceRegistry.LOW_STORAGE_SLICE_URI;
        String uri4 = CustomSliceRegistry.CONTEXTUAL_ADAPTIVE_SLEEP_URI.toString();
        ContextualCardProto$ContextualCard.Builder cardName2 = ContextualCardProto$ContextualCard.newBuilder().setSliceUri(uri4).setCardName(uri4);
        ContextualCardProto$ContextualCard.Category category2 = ContextualCardProto$ContextualCard.Category.DEFAULT;
        ContextualCardProto$ContextualCard.Builder newBuilder4 = ContextualCardProto$ContextualCard.newBuilder();
        Uri uri5 = CustomSliceRegistry.FACE_ENROLL_SLICE_URI;
        ContextualCardProto$ContextualCard.Builder newBuilder5 = ContextualCardProto$ContextualCard.newBuilder();
        Uri uri6 = CustomSliceRegistry.DARK_THEME_SLICE_URI;
        return ContextualCardProto$ContextualCardList.newBuilder().addCard(cardName.setCardCategory(category).build()).addCard(newBuilder2.setSliceUri(uri2.toString()).setCardName(uri2.toString()).setCardCategory(category).build()).addCard(newBuilder3.setSliceUri(uri3.toString()).setCardName(uri3.toString()).setCardCategory(category).build()).addCard(cardName2.setCardCategory(category2).build()).addCard(newBuilder4.setSliceUri(uri5.toString()).setCardName(uri5.toString()).setCardCategory(category2).build()).addCard(newBuilder5.setSliceUri(uri6.toString()).setCardName(uri6.toString()).setCardCategory(category).build()).build();
    }
}
