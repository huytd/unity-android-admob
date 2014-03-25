package com.gamarist.unityadmob;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.google.ads.*;
import com.google.ads.AdRequest.ErrorCode;

public class AdsPlugin {
	static final int bannerViewId = 0x661ad306; // Don't be scared, it's just a random unique number
	static Activity activityParent = null;
	static String publisherId = "";
	static String publisherInterstitialId = "";
	
	static AdView adBanner = null;
	static InterstitialAd interstitial;
	
	static public void InitAdmob(final Activity aa, final String pub, final String pubI)
	{
		activityParent = aa;
		publisherId = pub;
		publisherInterstitialId = pubI;
	}
	
	static public void ShowInterstitial()
	{
		activityParent.runOnUiThread(new Runnable() {
            public void run() {
			    interstitial = new InterstitialAd(activityParent, publisherInterstitialId);
			    AdRequest adRequest = new AdRequest();
			    interstitial.loadAd(adRequest);
			    interstitial.setAdListener(new AdListener() {
					@Override
					public void onReceiveAd(Ad ad) {
						// TODO Auto-generated method stub
						Log.d("OK", "Received ad");
					    if (ad == interstitial) {
					      interstitial.show();
					    }
					}
					
					@Override
					public void onPresentScreen(Ad arg0) { }
					@Override
					public void onLeaveApplication(Ad arg0) { }
					@Override
					public void onFailedToReceiveAd(Ad arg0, ErrorCode arg1) { }
					@Override
					public void onDismissScreen(Ad arg0) { }
				});
            }});
	}
	
	static public void ShowBanner() {
		activityParent.runOnUiThread(new Runnable() {
            public void run() {
                adBanner = (AdView)activityParent.findViewById(bannerViewId);
                if (adBanner == null) {
                    Log.d("AdMobPlugin", "creates an ad banner.");
                    // Make a layout for ad banner.
                    RelativeLayout layout = new RelativeLayout(activityParent);
                    activityParent.addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                    layout.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL);
                    // Make a banner.
                    adBanner = new AdView(activityParent, AdSize.SMART_BANNER, publisherId);
                    adBanner.setId(bannerViewId);
                    layout.addView(adBanner);
                }
                // Request an ad for the banner.
                Log.d("AdMobPlugin", "requests an ad.");
                AdRequest adRequest = new AdRequest();
                adBanner.loadAd(adRequest);
            }
        });
    }
}
