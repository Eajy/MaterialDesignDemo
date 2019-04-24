package com.eajy.materialdesigndemo;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;

//import sdk.insert.io.Insert;
import sdk.pendo.io.Pendo;

public class App extends Application {
    @Override
    public void onCreate() {
        Map userData = new HashMap<String, String>();
        userData.put("UserCountry", "Bulgaria");
        userData.put("UserGender", "Female");

        Map accountData = new HashMap<String, String>();
        accountData.put("accountCountry", "Israel");
        accountData.put("Version", "7");

        super.onCreate();
        Pendo.initSDK(
                this,
                "12f266809be042389bc045754c578bc245f733ac726afe4514989bdbc95034daf924b6a460a6cb6203f4273f0f6d45e365bd8a63e74d9db00ce124302392d214d499b4e44175e6e39e5e64d6d88f31dd.ca257853b20c97111c15c29dadeac262.eb5d0121051ac73b45b98799b114d4f11645ccdef0c3888b1b38c4ac30fd96a6",
                "",
                new Pendo.PendoInitParams()
//                      .setUserData(userAttributes)
//                      .setAccountData(userAttributes)
//                      .setVisitorId("222")
//                      .setAccountId("Pendo")
        );


//        Insert.switchVisitor("Mor", "Pendo", userData, accountData);
//        Insert.setAccountData(accountData);
//        Insert.setUserData(userData);
//        Insert.pauseGuides();
//        Insert.resumeGuides();
//        Insert.clearVisitor();
//        Insert.dismissVisibleInserts();

//        Pendo.switchVisitor("Visitor1", "Yo", userData, accountData);
//        Pendo.setAccountData(accountData);
//        Pendo.setUserData(userData);
//        Pendo.clearVisitor();



    }
}
