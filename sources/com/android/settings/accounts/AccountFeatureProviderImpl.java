package com.android.settings.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
/* loaded from: classes.dex */
public class AccountFeatureProviderImpl implements AccountFeatureProvider {
    @Override // com.android.settings.accounts.AccountFeatureProvider
    public String getAccountType() {
        return "com.google";
    }

    @Override // com.android.settings.accounts.AccountFeatureProvider
    public Account[] getAccounts(Context context) {
        return AccountManager.get(context).getAccountsByType("com.google");
    }
}
