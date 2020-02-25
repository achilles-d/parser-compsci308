package slogo.controller;

import slogo.model.backEndInternal.CommandParser;

public enum Language {
  CHINESE("Chinese.properties"), FRENCH("French.properties"), GERMAN("German.properties"),
  ITALIAN("Italian.properties"), PORTUGUESE("Portuguese.properties"),
  RUSSIAN("Russian.properties"), ENGLISH("English.properties"), SPANISH("Spanish.properties"),
  URDU("Urdu.properties");

  //TODO reconsider adding static modifier
  private final String LANGUAGES_DIR = "resources.languages.";
  public String myPropertyDir;

  Language(String languageFileName){
    myPropertyDir = LANGUAGES_DIR + languageFileName;
  }
}