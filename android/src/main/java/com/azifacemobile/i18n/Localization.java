package com.azifacemobile.i18n;

import androidx.annotation.NonNull;

public class Localization {
  public static final String DEFAULT = "en";

  @NonNull
  private String locale;

  public Localization() {
    this.locale = Localization.DEFAULT;
  }

  @NonNull
  public String getLocale() {
    return this.locale;
  }

  public void setLocale(@NonNull String locale) {
    String temp = locale.toLowerCase();

    switch (temp) {
      case "af":
      case "ar":
      case "de":
      case "el":
      case "es":
      case "fr":
      case "ja":
      case "kk":
      case "nb":
      case "ru":
      case "vi":
      case "zh":
        this.locale = temp;
        break;
      case "pt-br":
        this.locale = "pt";
        break;
      default:
        this.locale = "en";
        break;
    }
  }
}
