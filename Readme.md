==Installation==
1. Put *GoogleAdMobAdsSdk-6.4.1.jar* and *UnityAndroidAdmob.jar* into */Assets/Plugins/Android* folder
2. Add this Activity to your */Assets/Plugins/Android/AndroidManifest.xml* file
```xml
<activity android:name="com.google.ads.AdActivity"
            android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"/>
```
3. Add permission to *AndroidManifest.xml* file
```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```	
==How to use==

*Init the AdsPlugin*
```csharp
AndroidJavaClass plugin = new AndroidJavaClass("com.gamarist.unityadmob.AdsPlugin");
AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
plugin.CallStatic("InitAdmob", activity, "publisher-id", "interstitial-publisher-id");
```

*Show Banner*
```csharp
plugin.CallStatic("ShowBanner");
```

*Show Interstitial*
```csharp
plugin.CallStatic("ShowInterstitial");
```

*Manually refresh ads banner*
```csharp
while (true) {
	plugin.CallStatic("ShowBanner");
	yield return new WaitForSeconds(30.0f);
}
```

==Ads placement==
I hardcoded the ad banner placement position is Top-Center using SmartBanner. If you want to put your ads somewhere else, you should modify the code here in the Android source:
```csharp
layout.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL);
adBanner = new AdView(activityParent, AdSize.SMART_BANNER, publisherId);
```
