package edu.brown.cs.student.stars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.After;
import org.junit.Before;
import java.io.*;


import java.util.regex.Pattern;
import org.junit.Test;

/*
 * Below are my JUnit tests for stars.
 * Most of them concern the system tests provided in the tests folder.
 */

public class starsTest {

  /*
   * These lines are specifically for taking system output printed data so it can be converted to string.
   * And used with assertEquals.
   */

  private final ByteArrayOutputStream out = new ByteArrayOutputStream();
  private final ByteArrayOutputStream err = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @Before
  public void setStreams() {
    System.setOut(new PrintStream(out));
    System.setErr(new PrintStream(err));
  }

  @After
  public void restoreInitialStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  public void load_ten_stars() {

    ArrayList<Star> loadstars = new ArrayList<>();
    stars tester = new stars();
    loadstars = tester.stars("data/stars/ten-star.csv");
    assertEquals(loadstars.size(), 10);

  }

  @Test
  public void bad_stars_file() {

    ArrayList<Star> loadstars = new ArrayList<>();
    stars tester = new stars();
    loadstars = tester.stars("CS32.csv");
    String inputcommand = out.toString();
    inputcommand = inputcommand.replace("\r\n", "\n");
    String badcommand = "ERROR: Could Not Find File" + "\n" + "";
    assertEquals(inputcommand, badcommand);
    assertEquals(loadstars, null);
  }

  @Test
  public void no_stars_file() {

    ArrayList<Star> loadstars = new ArrayList<>();
    stars tester = new stars();
    loadstars = tester.stars("");
    String inputcommand = out.toString();
    inputcommand = inputcommand.replace("\r\n", "\n");
    String badcommand = "ERROR: Could Not Find File" + "\n" + "";
    assertEquals(inputcommand, badcommand);
    assertEquals(loadstars, null);

  }

  @Test
  public void incorrect_command() {

    Repl starsREPL = new Repl();
    CommandManager manager = starsREPL.getCommandManager();
    PrintWriter writer = starsREPL.getprintWriter();
    manager.process("neighboar 1 \"Proxima Centauri\"", writer);
    String inputcommand = out.toString();
    inputcommand = inputcommand.replace("\r\n", "\n");
    String badcommand = "ERROR: Invalid Command" + "\n" + "";
    assertEquals(inputcommand, badcommand);


  }

  @Test
  public void illegal_neighbors_position() {

    Repl starsREPL = new Repl();
    CommandManager manager = starsREPL.getCommandManager();
    PrintWriter writer = starsREPL.getprintWriter();
    manager.process("stars data/stars/ten-star.csv", writer);
    manager.process("neighbors string string string string", writer);
    String inputcommand = out.toString();
    inputcommand = inputcommand.replace("\r\n", "\n");
    String badcommand = "Read 10 stars from data/stars/ten-star.csv" + "\n" + "ERROR: NeighborsPosition Number Format Exception. Format should be: Int Double Double Double" + "\n" + "";
    assertEquals(inputcommand, badcommand);


  }

  @Test
  public void illegal_neighbors_name() {

    Repl starsREPL = new Repl();
    CommandManager manager = starsREPL.getCommandManager();
    PrintWriter writer = starsREPL.getprintWriter();
    manager.process("stars data/stars/ten-star.csv", writer);
    manager.process("neighbors 0 0", writer);
    String inputcommand = out.toString();
    inputcommand = inputcommand.replace("\r\n", "\n");
    String badcommand = "Read 10 stars from data/stars/ten-star.csv" + "\n" + "ERROR: NeighborsName 2nd Argument Needs To Be a String" + "\n" + "";
    assertEquals(inputcommand, badcommand);


  }

  @Test
  public void try_neighbors_xyz() {

    ArrayList<Star> loadstars = new ArrayList<>();
    stars tester = new stars();
    loadstars = tester.stars("data/stars/stardata.csv");

    Naive_Neighbors imported = new Naive_Neighbors();
    String neighborscommand = "5 0 0 0";
    imported.NeighborsPosition(neighborscommand, loadstars);
    String output = out.toString();
    output = output.replace("\r\n", "\n");
    String check = String.format("Read 119617 stars from data/stars/stardata.csv" + "\n" + "0" + "\n" + "70667" + "\n" + "71454" + "\n" + "71457" + "\n" + "87666" + "\n" + "");

    assertEquals(check, output);

  }

  @Test
  public void try_neighbors_name() {

    ArrayList<Star> loadstars = new ArrayList<>();
    stars tester = new stars();
    loadstars = tester.stars("data/stars/stardata.csv");

    Naive_Neighbors imported = new Naive_Neighbors();
    String neighborscommand = "5 \"Sol\"";
    imported.NeighborsName(neighborscommand, loadstars);
    String output = out.toString();
    output = output.replace("\r\n", "\n");
    String check = String.format("Read 119617 stars from data/stars/stardata.csv" + "\n" + "70667" + "\n" + "71454" + "\n" + "71457" + "\n" + "87666" + "\n" + "118721" + "\n" + "");

    assertEquals(check, output);

  }

  @Test
  public void five_star_r_xyz() {

    ArrayList<Star> loadstars = new ArrayList<>();
    stars tester = new stars();
    loadstars = tester.stars("data/stars/ten-star.csv");

    Naive_Radius imported = new Naive_Radius();
    String radiusscommand = "10 0 0 0";
    imported.RadiusPosition(radiusscommand, loadstars);
    String output = out.toString();
    output = output.replace("\r\n", "\n");
    String check = String.format("Read 10 stars from data/stars/ten-star.csv" + "\n" + "0" + "\n" + "70667" + "\n" + "71454" + "\n" + "71457" + "\n" + "87666" + "\n" + "118721" + "\n" + "3759" + "\n" + "");

    assertEquals(check, output);

  }

  @Test
  public void five_stars_n_name() {

    ArrayList<Star> loadstars = new ArrayList<>();
    stars tester = new stars();
    loadstars = tester.stars("data/stars/ten-star.csv");

    Naive_Neighbors imported = new Naive_Neighbors();
    String neighborscommand = "5 \"Sol\"";
    imported.NeighborsName(neighborscommand, loadstars);
    String output = out.toString();
    output = output.replace("\r\n", "\n");
    String check = String.format("Read 10 stars from data/stars/ten-star.csv" + "\n" + "70667" + "\n" + "71454" + "\n" + "71457" + "\n" + "87666" + "\n" + "118721" + "\n" + "");

    assertEquals(check, output);

  }

  @Test
  public void zero_neighbors() {

    ArrayList<Star> loadstars = new ArrayList<>();
    stars tester = new stars();
    loadstars = tester.stars("data/stars/ten-star.csv");

    Naive_Neighbors imported = new Naive_Neighbors();
    String neighborscommand = "0 0 0 0";
    imported.NeighborsPosition(neighborscommand, loadstars);
    String output = out.toString();
    output = output.replace("\r\n", "\n");
    String check = String.format("Read 10 stars from data/stars/ten-star.csv" + "\n" + "");

    assertEquals(check, output);

  }

  @Test
  public void exclude_star() {

    ArrayList<Star> loadstars = new ArrayList<>();
    stars tester = new stars();
    loadstars = tester.stars("data/stars/one-star.csv");

    Naive_Neighbors imported = new Naive_Neighbors();
    String neighborscommand = "1 \"Lonely Star\"";
    imported.NeighborsName(neighborscommand, loadstars);
    String output = out.toString();
    output = output.replace("\r\n", "\n");
    String check = String.format("Read 1 stars from data/stars/one-star.csv" + "\n" + "");

    assertEquals(check, output);

  }

  @Test
  public void one_star() {

    ArrayList<Star> loadstars = new ArrayList<>();
    stars tester = new stars();
    loadstars = tester.stars("data/stars/one-star.csv");

    Naive_Neighbors imported = new Naive_Neighbors();
    String neighborscommand = "1 12.0025 1.0257 -105.5236";
    imported.NeighborsPosition(neighborscommand, loadstars);
    String output = out.toString();
    output = output.replace("\r\n", "\n");
    String check = String.format("Read 1 stars from data/stars/one-star.csv" + "\n" + "1" + "\n" + "");

    assertEquals(check, output);

  }


}