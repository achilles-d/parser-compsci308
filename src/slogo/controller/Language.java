package slogo.controller;

/**
 * An enum containing the name of the current language being used for commands and the resource file
 * mapping each command to the same command in a specified language
 * Controller has an instance of this enum
 * Example: call getLanguageFile() to access the directory of the current language resource file in use
 * to make it possible to compare entered commands in that same language to the possible commands that can
 * be parsed and executed
 * @author Achilles Dabrowski
 */
public enum Language {
  CHINESE("Chinese"), FRENCH("French"), GERMAN("German"),
  ITALIAN("Italian"), PORTUGUESE("Portuguese"),
  RUSSIAN("Russian"), ENGLISH("English"), SPANISH("Spanish"),
  URDU("Urdu");

  private final String LANGUAGES_DIR = "resources.languages.";
  private String myPropertyDir;

  /**
   * Set the command language resource file mapping each command to the same command in a
   * specified language
   * @param languageFileName the name of the command language resource file WITHOUT the
   *                         directory suffix
   */
  Language(String languageFileName){
    myPropertyDir = LANGUAGES_DIR + languageFileName;
  }

  /**
   * Return the directory of the command language resource file currently in use
   * @return the directory of the command language resource file currently in use
   */
  public String getLanguageFile(){
    return myPropertyDir;
  }

}