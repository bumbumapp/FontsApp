package com.bumbumapps.stylishtext.utils;

import com.bumbumapps.stylishtext.interfaces.Style;

import java.util.ArrayList;

public class FontsBuilder {
    private static ArrayList<String> createLeft() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("\u0e56\u06e3\u06dc");
        strings.add("\u2E3E");
        strings.add("\u2E3D");
        strings.add("\u2E3E\u2E3E");
        strings.add("\u2E3D\u2E3D");
        strings.add("☢");
        strings.add("☣");
        strings.add("☠");
        strings.add("⚠");
        strings.add("☤");
        strings.add("⚕");
        strings.add("⚚");
        strings.add("†");
        strings.add("☯");
        strings.add("⚖");
        strings.add("☮");
        strings.add("⚘");
        strings.add("⚔");
        strings.add("☭");
        strings.add("⚒");
        strings.add("⚓");
        strings.add("⚛");
        strings.add("⚜");
        strings.add("⚡");
        strings.add("⚶");
        strings.add("☥");
        strings.add("✠");
        strings.add("✙");
        strings.add("✞");
        strings.add("✟");
        strings.add("✧");
        strings.add("⋆");
        strings.add("★");
        strings.add("☆");
        strings.add("✪");
        strings.add("✫");
        strings.add("✬");
        strings.add("✭");
        strings.add("✮");
        strings.add("✯");
        strings.add("☸");
        strings.add("✵");
        strings.add("❂");
        strings.add("☘");
        strings.add("♡");
        strings.add("♥");
        strings.add("❤");
        strings.add("⚘");
        strings.add("❀");
        strings.add("❃");
        strings.add("❁");
        strings.add("✼");
        strings.add("☀");
        strings.add("✌");
        strings.add("♫");
        strings.add("♪");
        strings.add("☃");
        strings.add("❄");
        strings.add("❅");
        strings.add("❆");
        strings.add("☕");
        strings.add("☂");
        strings.add("❦");
        strings.add("✈");
        strings.add("♕");
        strings.add("♛");
        strings.add("♖");
        strings.add("♜");
        strings.add("☁");
        strings.add("☾");
        return strings;
    }


    private static ArrayList<Pair<String, String>> createLeftRightPair() {
        ArrayList<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("⫷", "⫸"));
        list.add(new Pair<>("╰", "╯"));
        list.add(new Pair<>("╭", "╮"));
        list.add(new Pair<>("╟", "╢"));
        list.add(new Pair<>("╚", "╝"));
        list.add(new Pair<>("╔", "╗"));
        list.add(new Pair<>("⚞", "⚟"));
        list.add(new Pair<>("⟅", "⟆"));
        list.add(new Pair<>("⟦", "⟧"));
        list.add(new Pair<>("☾", "☽"));
        list.add(new Pair<>("【", "】"));
        list.add(new Pair<>("〔", "〕"));
        list.add(new Pair<>("《", "》"));
        list.add(new Pair<>("〘", "〙"));
        list.add(new Pair<>("『", "』"));
        list.add(new Pair<>("┋", "┋"));
        list.add(new Pair<>("[\u0332\u0305", "\u0332\u0305]"));

        return list;
    }


    private static ArrayList<String> createRight() {
        ArrayList<String> list = new ArrayList<>();
        list.add("\u20e0");
        list.add("\u033e");
        list.add("\u035a");
        list.add("\u032b");
        list.add("\u030f");
        list.add("\u0352");
        list.add("\u0310");
        list.add("\u0325");
        list.add("\u0303");
        list.add("\u2665");
        list.add("\u034e");
        list.add("\u033d\u0353");
        list.add("\u031f");
        list.add("\u0359");
        list.add("\u033a");
        list.add("\u0346");
        list.add("\u033e");
        list.add("\u0333");
        list.add("\u0332");
        list.add("\u0338");
        list.add("\u0337");
        list.add("\u0334");
        list.add("\u0336");

    for (char c : Zalgo.UP_CHARS) list.add(c + "");
    for (char c : Zalgo.DOWN_CHARS) list.add(c + "");
     for (char c : Zalgo.MID_CHARS) list.add(c + "");
        return list;
    }

    public static ArrayList<Style> makeStyle() {
        ArrayList<Style> encoders = new ArrayList<>();
        encoders.add(new BlueEffect());
        encoders.addAll(ReplaceEffect.createStyle());

        ArrayList<Pair<String, String>> leftRightPair = createLeftRightPair();
        for (Pair<String, String> pair : leftRightPair) {
            encoders.add(new LeftRightStyle(pair.first, pair.second));
        }
        ArrayList<String> lefts = createLeft();
        for (String s : lefts) encoders.add(new LeftEffect(s));
        ArrayList<String> rights = createRight();
        for (String s : rights) encoders.add(new RightEffect(s));
        return encoders;
    }


    private static class Pair<F, S> {
        F first;
        S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }
}
