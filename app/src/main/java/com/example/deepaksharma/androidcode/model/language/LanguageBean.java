package com.example.deepaksharma.androidcode.model.language;

/**
 * Created by Deepak Sharma(webaddicted) on 19-08-2019.
 */
public class LanguageBean {
    private int id;
    private String languageName;
    private String languageCode;
    private String languageFlag;
    private boolean isSelected;

    public LanguageBean(int id, String languageName, String languageCode, String languageFlag) {
        this.id = id;
        this.languageName = languageName;
        this.languageCode = languageCode;
        this.languageFlag = languageFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguageFlag() {
        return languageFlag;
    }

    public void setLanguageFlag(String languageFlag) {
        this.languageFlag = languageFlag;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
