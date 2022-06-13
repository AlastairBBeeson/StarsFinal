package edu.brown.cs.student.stars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Repl {

  private CommandManager commandManager;
  private PrintWriter printWriter;
  private BufferedReader bufferedReader;

  /**
   * Instantiates instance variables.
   */
  public Repl() {

    // This is the Command Manager and the printwriter
    // The printwriter outputs automatically to System.out
    this.commandManager = new CommandManager();
    this.printWriter = new PrintWriter(System.out, true);

    // Register the StarsCommandManager's commands in our REPL
    new StarsCommandManager(commandManager);
  }

  /**
   * Accessor for command manager.
   */
  public CommandManager getCommandManager() {
    return this.commandManager;
  }
  public PrintWriter getprintWriter() {
    return this.printWriter;
  }

  /**
   * This command stars running the Repl,
   * which has a while true loop.
   */
  public void runRepl() {
    System.out.println("REPL Initialized");
    try {

      bufferedReader = new BufferedReader(new InputStreamReader(System.in));

      String input;
      while ((input = bufferedReader.readLine()) != null) {
        commandManager.process(input, printWriter);
      }

      bufferedReader.close();

    } catch (IOException e) {

      System.out.println("ERROR: IO exception");

    } catch (Exception e) {

      try {
        bufferedReader.close();
      } catch (IOException e1) {
        System.out.println("ERROR: IO exception");
      }
      e.printStackTrace();
      System.out.println("ERROR: Program is shutting down");
    }
  }


}
