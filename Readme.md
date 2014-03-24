[Installation]
1. Put GoogleAdMobAdsSdk-6.4.1.jar and UnityAndroidAdmob.jar into /Assets/Plugins/Android folder
2. Add this Activity to your /Assets/Plugins/Android/AndroidManifest.xml file
	<activity android:name="com.google.ads.AdActivity"
            android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"/>
3. Add permission to AndroidManifest.xml file
	<uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
			
[How to use]

// Init the AdsPlugin
AndroidJavaClass plugin = new AndroidJavaClass("com.gamarist.unityadmob.AdsPlugin");
        
AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        
AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
plugin.CallStatic("InitAdmob", activity, "publisher-id", "interstitial-publisher-id");

// Show Banner
plugin.CallStatic("ShowBanner");

// Show Interstitial
plugin.CallStatic("ShowInterstitial");

// Manually refresh ads banner
while (true) {
	plugin.CallStatic("ShowBanner");
	yield return new WaitForSeconds(30.0f);
}