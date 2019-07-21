package com.eajy.materialdesigndemo;

import com.eajy.materialdesigndemo.model.MyAppsModel;

import java.util.ArrayList;
import java.util.List;

public class Data {

    public static List<MyAppsModel> getMyAppsData() {
        List<MyAppsModel> lists = new ArrayList();

        MyAppsModel model0 = new MyAppsModel();
        model0.setName("Material Design");
        model0.setDescription("A beautiful app designed with Material Design.");
        model0.setPackageName("com.eajy.materialdesigndemo");
        model0.setGooglePlayUrl("https://play.google.com/store/apps/details?id=com.eajy.materialdesigndemo");
        model0.setImageUrl("material_design_demo");
        if (!model0.getPackageName().equals(BuildConfig.APPLICATION_ID))
            lists.add(model0);

        MyAppsModel model7 = new MyAppsModel();
        model7.setName("Tasks");
        model7.setDescription("Simply create any task or goal or target, and to achieve it.");
        model7.setPackageName("com.eajy.apps.tasks");
        model7.setGooglePlayUrl("https://play.google.com/store/apps/details?id=com.eajy.apps.tasks");
        model7.setImageUrl("tasks");
        if (!model7.getPackageName().equals(BuildConfig.APPLICATION_ID))
            lists.add(model7);

        MyAppsModel model6 = new MyAppsModel();
        model6.setName("Material Design 2");
        model6.setDescription("A beautiful app designed with Material Design 2.");
        model6.setPackageName("com.eajy.materialdesign2");
        model6.setGooglePlayUrl("https://play.google.com/store/apps/details?id=com.eajy.materialdesign2");
        model6.setImageUrl("material_design_2");
        if (!model6.getPackageName().equals(BuildConfig.APPLICATION_ID))
            lists.add(model6);

        MyAppsModel model3 = new MyAppsModel();
        model3.setName("X Launcher");
        model3.setDescription("A beautiful Pixel-Launcher-liked launcher with simplify.");
        model3.setPackageName("com.eajy.launcher");
        model3.setGooglePlayUrl("https://play.google.com/store/apps/details?id=com.eajy.launcher");
        model3.setImageUrl("x_launcher");
        if (!model3.getPackageName().equals(BuildConfig.APPLICATION_ID))
            lists.add(model3);

        MyAppsModel model4 = new MyAppsModel();
        model4.setName("All Messengers");
        model4.setDescription("Messengers all in one.");
        model4.setPackageName("messenger.all.messengerall");
        model4.setGooglePlayUrl("https://play.google.com/store/apps/details?id=messenger.all.messengerall");
        model4.setImageUrl("all_messenger");
        if (!model4.getPackageName().equals(BuildConfig.APPLICATION_ID))
            lists.add(model4);

        MyAppsModel model2 = new MyAppsModel();
        model2.setName("Flutter Demo");
        model2.setDescription("A beautiful app designed with Material Design by using Flutter.");
        model2.setPackageName("com.eajy.flutterdemo");
        model2.setGooglePlayUrl("https://play.google.com/store/apps/details?id=com.eajy.flutterdemo");
        model2.setImageUrl("flutter_demo");
        if (!model2.getPackageName().equals(BuildConfig.APPLICATION_ID))
            lists.add(model2);

        MyAppsModel model1 = new MyAppsModel();
        model1.setName("Material Design Color");
        model1.setDescription("This app shows the color in Material Design.");
        model1.setPackageName("com.eajy.materialdesigncolor");
        model1.setGooglePlayUrl("https://play.google.com/store/apps/details?id=com.eajy.materialdesigncolor");
        model1.setImageUrl("material_design_color");
        if (!model1.getPackageName().equals(BuildConfig.APPLICATION_ID))
            lists.add(model1);

        MyAppsModel model5 = new MyAppsModel();
        model5.setName("2048 Game");
        model5.setDescription("Classic game - 2048. Please enjoy it.");
        model5.setPackageName("com.eajy.game.game2048");
        model5.setGooglePlayUrl("https://play.google.com/store/apps/details?id=com.eajy.game.game2048");
        model5.setImageUrl("game_2048");
        if (!model5.getPackageName().equals(BuildConfig.APPLICATION_ID))
            lists.add(model5);

        return lists;
    }
}
