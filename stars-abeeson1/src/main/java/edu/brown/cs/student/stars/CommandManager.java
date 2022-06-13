package edu.brown.cs.student.stars;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class handles the commands taken from REPL and registers commands given
 * to it from other classes.
 */
public class CommandManager {

  private Map<String, Command> commands;

  /**
   * Instantiates commands.
   */
  public CommandManager() {
    commands = new HashMap<>();
  }

  /**
   * gets the Hashmap of registered commands.
   */
  public Map<String, Command> getCommands() {
    return commands;
  }

  /**
   * This function registers commands so the command manager knows it exists.
   */
  public void register(String pattern, Command command) {
    commands.put(pattern, command);
  }

  /**
   * This function processes user input from the REPL.
   * If the command exists, then it splits the command into the command and arguments
   * then gives it to the execute function.
   * It throws an error if there is only 1 argument or if the command is invalid
   */
  public void process(String line, PrintWriter printWriter) {

    List<String> inputs = Arrays.asList(line.split(" ", 2));

    if (commands.containsKey(inputs.get(0))) {


      if (inputs.size() == 1) {
        printWriter.println("ERROR: Needs More Than 1 Argument");
      } else {


        if (inputs.get(1).trim().isEmpty()) {
          commands.get(inputs.get(0)).execute(null, printWriter);
        } else {

          commands.get(inputs.get(0)).execute(inputs.get(1), printWriter);
        }
      }
    } else {

      printWriter.println("ERROR: Invalid Command");
    }
  }

  /**
   * This interface lets other classes know what the command manager needs.
   */
  public interface Command {

    /**
     * This method is to execute a specific command when the
     * proper pattern is inputted into the REPL.
     * Each Command has to define a specific action for execute
     */
    void execute(String tokens, PrintWriter pw);
  }

}

