package slogo.controller;

public enum Language {
  CHINESE("Chinese"), FRENCH("French"), GERMAN("German"),
  ITALIAN("Italian"), PORTUGUESE("Portuguese"),
  RUSSIAN("Russian"), ENGLISH("English"), SPANISH("Spanish"),
  URDU("Urdu");

  //TODO reconsider adding static modifier
  private final String LANGUAGES_DIR = "resources.languages.";
  private String myPropertyDir;

  Language(String languageFileName){
    myPropertyDir = LANGUAGES_DIR + languageFileName;
  }

  public String getLanguageFile(){
    return myPropertyDir;
  }

}