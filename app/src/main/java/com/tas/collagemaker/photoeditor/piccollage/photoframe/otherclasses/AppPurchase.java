package com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

public class AppPurchase {

    public static final String PRODUCT_IDW = "subone";  //no capital word and number included
    public static final String PRODUCT_ID2M = "subtwo";  //no capital word and number included
    public static final String PRODUCT_ID3M = "subthree";  //no capital word and number included
    public static final String PRODUCT_IDL = "proone";  //no capital word and number included
    public static final String LICENSE_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2ICuhyMO3KRQwA9emtZHHgkDC2ERpfq62z2KTOU7SFbQSfPV6cRtR8UABDFJwp1DpZ47/Ck2R094DKJDebSHh64lZgQlRNktFODqSkTsWmiR2XbsmSoNUwVEn+DJEdYxgdinHO7E9WND3SwD+6r34tEfilwlydpeusDxk6XgaT2eJr6fVWWZ5sNJWajtiBFrwQk4xgo8NDU/AWHUNw3VJVBU+FPxYFRiUFUnVAOjLU7CV9KKeIyBQQ99+3ZVo5/XfW4a/wmlyadpzP1fRk1pw0gLi2zVxRLQgzEfhuAQ1fQ8omKYteUpQFlyNuwIX55MqX5SufGG2Da7QLWAR8xp+QIDAQAB";
    public static final String MERCHANT_ID = "03269321825029656653";
    public static BillingProcessor bp;
    public static boolean purchase = false;
    public static boolean readyToPurchase = false;
    private static final String LOG_TAG = "iabv3";

    public static boolean checkPurchases() {
        try {
            if (bp.isSubscribed(PRODUCT_IDW)||bp.isSubscribed(PRODUCT_ID2M)||bp.isSubscribed(PRODUCT_ID3M)||bp.isPurchased(PRODUCT_IDL)) {
                purchase = true;
            }
            else {
                purchase = false;
            }
        } catch (Exception e) {
            Log.d("checkk", "checkpurchases: " + e.toString());
        }
        return purchase;
    }

    public static void runfirst(final Context mContext) {
        try {

            if (!BillingProcessor.isIabServiceAvailable(mContext)) {
                showToast("In-app billing service is unavailable, please upgrade Android Market/Play to version >= 3.9.16", mContext);
            }

            bp = new BillingProcessor(mContext, LICENSE_KEY, MERCHANT_ID, new BillingProcessor.IBillingHandler() {
                @Override
                public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {

//                updateTextViews();
                }

                @Override
                public void onBillingError(int errorCode, @Nullable Throwable error) {
                    showToast("Something went wrong..", mContext);
                }

                @Override
                public void onBillingInitialized() {
//                showToast("onBillingInitialized");
                    readyToPurchase = true;
//                updateTextViews();
                }


                @Override
                public void onPurchaseHistoryRestored() {
//                showToast("onPurchaseHistoryRestored");
                    for (String sku : bp.listOwnedProducts())
                        Log.d(LOG_TAG, "Owned Managed Product: " + sku);
                    for (String sku : bp.listOwnedSubscriptions())
                        Log.d(LOG_TAG, "Owned Subscription: " + sku);
//                updateTextViews();
                }

            });
        } catch (Exception er) {
        }
    }

    public static void showToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }



}









