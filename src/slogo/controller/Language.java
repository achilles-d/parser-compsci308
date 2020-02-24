package slogo.controller;

import slogo.model.backEndInternal.CommandParser;

public enum Language {
  CHINESE("Chinese.properties"), FRENCH("French.properties"), GERMAN("German.properties"),
  ITALIAN("Italian.properties"), PORTUGUESE("Portuguese.properties"),
  RUSSIAN("Russian.properties"), ENGLISH("English.properties"), SPANISH("Spanish.properties"),
  URDU("Urdu.properties");

  //FIXME maybe remove the constant
  private final String RESOURCES_PACKAGE_DIR = CommandParser.class.getPackageName() + ".resources.languages.";
  public String myPropertyDir;

  Language(String languageFileName){
    myPropertyDir = RESOURCES_PACKAGE_DIR + languageFileName;
  }
}