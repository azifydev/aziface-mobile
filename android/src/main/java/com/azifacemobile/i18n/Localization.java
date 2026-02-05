package com.azifacemobile.i18n;

import androidx.annotation.NonNull;

public enum Localization {
  DEFAULT("en"),
  AF("af"),
  AR("ar"),
  DE("de"),
  EL("el"),
  ES("es"),
  FR("fr"),
  JA("ja"),
  KK("kk"),
  NB("nb"),
  PT_BR("pt-rBR"),
  RU("ru"),
  VI("vi"),
  ZH("zh");

  @NonNull
  private String locale;

  Localization(@NonNull String locale) {
    this.locale = locale;
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
