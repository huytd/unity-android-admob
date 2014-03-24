##Installation

1. Put *GoogleAdMobAdsSdk-6.4.1.jar* and *UnityAndroidAdmob.jar* into */Assets/Plugins/Android* folder

2. Add this Activity to your */Assets/Plugins/Android/AndroidManifest.xml* file

```xml
<activity android:name="com.google.ads.AdActivity"
            android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"/>
```

And don't forget the permission

```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```	

##How to use

_Init the AdsPlugin_

```csharp
AndroidJavaClass plugin = new AndroidJavaClass("com.gamarist.unityadmob.AdsPlugin");
AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
AndroidJavaObject activity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
plugin.CallStatic("InitAdmob", activity, "publisher-id", "interstitial-publisher-id");
```

_Show Banner_

```csharp
plugin.CallStatic("ShowBanner");
```

_Show Interstitial_

```csharp
plugin.CallStatic("ShowInterstitial");
```

_Manually refresh ads banner_

```csharp
while (true) {
	plugin.CallStatic("ShowBanner");
	yield return new WaitForSeconds(30.0f);
}
```

##Ads placement

I hardcoded the ad banner placement position is Top-Center using SmartBanner. If you want to put your ads somewhere else, you should modify the code here in the Android source:

```java
layout.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL);
adBanner = new AdView(activityParent, AdSize.SMART_BANNER, publisherId);
```
