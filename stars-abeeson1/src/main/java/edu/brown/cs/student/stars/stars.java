package edu.brown.cs.student.stars;

import java.util.ArrayList;


public class stars {

  /**
   *stars method loads in stars using the CSVparser object.
   * Then iterates through the data of the CSVparser and populates an ArrayList of individual stars.
   * Also makes sure to add stars to StarNames hashmap for search in name methods.
   * Then prints the number of stars it read and from what file
   */

  public ArrayList<Star> stars(String args) {
    String[] headerCheck = {"StarID", "ProperName", "X", "Y", "Z"};

    CSVparser parser = new CSVparser(args, headerCheck);

    if (parser.getCSVData().size() != 0) {
      ArrayList<Star> Stars = new ArrayList<>();
      ArrayList<String[]> CSVData = parser.getCSVData();

      for (String[] stardata : CSVData) {

        try {

          int starID = Integer.parseInt(stardata[0]);
          String starName = stardata[1];
          double[] starCoordinates = new double[] {Double.parseDouble(stardata[2]), Double.parseDouble(stardata[3]), Double.parseDouble(stardata[4])};
          Star star = new Star(starID, starName, starCoordinates);
          Stars.add(star);
          StarsCommandManager.StarNames.put(starName, star);

        } catch (NumberFormatException e) {
          System.out.println("ERROR: Numbers Not Formatted Correctly");
          return null;
        } catch (NullPointerException e) {
          System.out.println("ERROR: NullPointException");
          return null;
        }

      }

      System.out.println("Read " + Stars.size() + " stars from " + args);
      return Stars;

    } else {
      return null;
    }

  }

}
