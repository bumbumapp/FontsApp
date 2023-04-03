package com.bumbumapps.stylishtext.model;

public class Font {
    String fontName;
    String previewText = "Preview Text";

    public Font(String fontName) {
        this.fontName = fontName;
    }

    public String getFontName() {
        return fontName;
    }

    public String getPreviewText() {
        return previewText;
    }

    public void setPreviewText(String previewText) {
        this.previewText = previewText;
    }
}
