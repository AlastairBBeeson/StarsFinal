package edu.brown.cs.student.stars;

import edu.brown.cs.student.stars.CommandManager.Command;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class StarsCommandManager {

  /**
   * starslist is for storing the output of the stars method.
   * StarNames is a hashmap to store key value pairs for fast lookup of star names
   * in the methods that rely on names.
   */

  private ArrayList<Star> starslist;
  public static HashMap<String, Star> StarNames = new HashMap<String, Star>();

  /**
   * Constructor for StarsCommandManager which takes in a CommandManager.
   * This passes the Stars Commands to the CommandManager.
   * It also creates a starslist for use with stars methods.
   */

  public StarsCommandManager(CommandManager commandManager) {
    this.importCommands(commandManager);
    this.starslist = new ArrayList<Star>();
  }


  /**
   * Method for registering and importing commands into the CommandManager.
   */
  private void importCommands(CommandManager cm) {
    cm.register("stars", new runstars());
    cm.register("neighbors", new runneighbors());
    cm.register("radius", new runradius());
  }

  /**
   * Class for running the stars command.
   * Sets starslist equal to the output of the stars method so starslist
   * can be used with other stars methods.
   */
  private class runstars implements Command {

    public void execute(String tokens, PrintWriter pw) {
      stars imported = new stars();
      ArrayList<Star> starsoutput = imported.stars(tokens);
      if (starsoutput != null) {
        starslist = starsoutput;
      }
      return;
    }

  }

  /**
   * Class for running the neighbors command
   * execute checks to make sure that the number of arguments is 2 or 4
   * It then chooses the specific neighbors method either number or name
   * Otherwise it throws an error
   */
  private class runneighbors implements Command {

    public void execute(String tokens, PrintWriter pw) {
      if (starslist.isEmpty()) {
        System.out.println("ERROR: Need to Load Stars First");
        return;
      }
      Naive_Neighbors imported = new Naive_Neighbors();
      Pattern regex = Pattern.compile("\\s+(?=(?:[^\"]*[\"][^\"]*[\"])*[^\"]*$)");
      String[] args = tokens.split(regex.pattern());
      if (args.length == 4) {
        imported.NeighborsPosition(tokens, starslist);
        return;
      }

      if (args.length == 2) {
        imported.NeighborsName(tokens, starslist);
        return;
      }

      if (args.length != 2 || args.length != 4) {
        System.out.println("ERROR: Incorrect Number of Neighbors Arguments. Should be 2 or 4.");
        return;
      }
    }

  }

  /**
   * Class for running the radius command.
   * execute checks to make sure that the number of arguments is 2 or 4.
   * It then chooses the specific neighbors method either number or name.
   * Otherwise it throws an error.
   */
  private class runradius implements Command {

    public void execute(String tokens, PrintWriter pw) {
      if (starslist.isEmpty()) {
        System.out.println("ERROR: Need to Load Stars First");
        return;
      }

      Naive_Radius imported = new Naive_Radius();
      Pattern regex = Pattern.compile("\\s+(?=(?:[^\"]*[\"][^\"]*[\"])*[^\"]*$)");
      String[] args = tokens.split(regex.pattern());

      if (args.length == 4) {
        imported.RadiusPosition(tokens, starslist);
        return;
      }

      if (args.length == 2) {
        imported.RadiusName(tokens, starslist);
        return;
      }

      if (args.length != 2 || args.length != 4) {
        System.out.println("ERROR: Incorrect Number of Radius Arguments. Should be 2 or 4.");
        return;
      }
    }

  }

}
