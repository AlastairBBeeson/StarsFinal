package edu.brown.cs.student.stars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;

public class Naive_Neighbors {

  private ArrayList<Star> neighborsoutput;

  /**
   * A naive nearest neighbors implementation using a xyz position.
   * Determines a specified number of nearest neighbors of a coordinate
   * Takes 4 Arguments: k x y z
   * Implements specific comparator for comparing Euclidean Distance
   * The final ArrayList is truncated according to the specified number of neighbors
   * Prints out the stars
   */
  public void NeighborsPosition(String input, ArrayList<Star> stars) {
    Pattern regex = Pattern.compile("\\s+(?=(?:[^\"]*[\"][^\"]*[\"])*[^\"]*$)");
    String[] args = input.split(regex.pattern());
    ArrayList<Star> neighborsoutput = new ArrayList<>();
    ArrayList<Star> neighbors = new ArrayList<>();

    try {
      int numneighbors = Integer.parseInt(args[0]);
      double inputX = Double.parseDouble(args[1]);
      double inputY = Double.parseDouble(args[2]);
      double inputZ = Double.parseDouble(args[3]);

      if (numneighbors < 0) {
        System.out.println("ERROR: The Number of Neighbors Must Be A Non-Negative Integer");
        return;

      } else {
        double[] targetcoords = new double[] {inputX, inputY, inputZ};
        ArrayList<Star> iter = stars;

        for (int i = 0; i < iter.size(); i++) {
          Star currentstar = iter.get(i);
          double[] currentCoords = currentstar.getCoordinate();
          double currentDistance = euclideanDistance(currentCoords, targetcoords);
          currentstar.setDistance(currentDistance);
          neighbors.add(currentstar);

          Collections.sort(neighbors, new Comparator<Star>() {

            @Override
            public int compare(Star s1, Star s2) {
              if (s1.getDistance() > s2.getDistance()) {
                return 1;
              }
              if (s1.getDistance() < s2.getDistance()) {
                return -1;
              }
              return 0;
            }
          });

          if (neighbors.size() > numneighbors) {
            neighbors = new ArrayList<Star>(neighbors.subList(0, numneighbors));
          }
        }

      }
    } catch (NullPointerException e) {
      System.out.println("ERROR: NeighborsPosition Null Pointer Exception");
      return;
    } catch (NumberFormatException e) {
      System.out.println("ERROR: NeighborsPosition Number Format Exception. Format should be: Int Double Double Double");
      return;
    }
    //Collections.copy(neighborsoutput,neighbors);

    for (int i = 0; i < neighbors.size(); i++) {
      System.out.println(neighbors.get(i).getID());
    }

  }

  /**
   * A naive nearest neighbors implementation using a star name.
   * Determines a specified number of nearest neighbors of named star
   * Takes 2 Arguments: k name
   * Implements specific comparator for comparing Euclidean Distance
   * The final ArrayList is truncated according to the specific number of neighbors
   * Prints out the stars, makes sure to omit the named star from output
   */
  public void NeighborsName(String input, ArrayList<Star> stars) {
    Pattern regex = Pattern.compile("\\s+(?=(?:[^\"]*[\"][^\"]*[\"])*[^\"]*$)");
    String[] args = input.split(regex.pattern());

    ArrayList<Star> neighbors = new ArrayList<>();

    try {

      int inputcheck = Integer.parseInt(args[0]);

      String targetname = args[1].substring(1, args[1].length() - 1);

      double[] targetcoords = null;

      if (StarsCommandManager.StarNames.containsKey(targetname)) {
        Star targetstar = StarsCommandManager.StarNames.get(targetname);
        targetcoords = targetstar.getCoordinate();
      } else {
        System.out.println("ERROR: No Star by that Name in the Data");
        return;
      }

      int numneighbors = Integer.parseInt(args[0]);

      if (numneighbors < 0) {
        System.out.println("ERROR: The Number of Neighbors Must Be A Non-Negative Integer");
        return;
      } else {
        ArrayList<Star> iter = stars;

        for (int i = 0; i < iter.size(); i++) {
          Star currentstar = iter.get(i);
          double[] currentCoords = currentstar.getCoordinate();
          double currentDistance = euclideanDistance(currentCoords, targetcoords);
          currentstar.setDistance(currentDistance);
          if (currentstar.getStarName().equals(targetname) == false) {
            neighbors.add(currentstar);
          }

          Collections.sort(neighbors, new Comparator<Star>() {

            @Override
            public int compare(Star s1, Star s2) {
              if (s1.getDistance() > s2.getDistance()) {
                return 1;
              }
              if (s1.getDistance() < s2.getDistance()) {
                return -1;
              }
              return 0;
            }
          });

          if (neighbors.size() > numneighbors) {
            neighbors = new ArrayList<Star>(neighbors.subList(0, numneighbors));
          }
        }

      }
    } catch (NullPointerException e) {
      System.out.println("ERROR: NeighborsName Null Pointer Exception");
      return;
    } catch (NumberFormatException e) {
      System.out.println("ERROR: NeighborsName Number Format Exception. 1st Argument Needs to be a Int.");
      return;
    } catch (StringIndexOutOfBoundsException e) {
      System.out.println("ERROR: NeighborsName 2nd Argument Needs To Be a String");
      return;
    }

    for (int i = 0; i < neighbors.size(); i++) {
      System.out.println(neighbors.get(i).getID());
    }

  }

  public ArrayList<Star> returnNeighbors() {
    return this.neighborsoutput;
  }

  /**
   * A method for calculating the euclidean distance between two points.
   * The points must have the same dimensions
   * Throws an error if they are not the same dimension.
   */
  public static double euclideanDistance(double[] coordinatesA, double[] coordinatesB) {

    double sum = 0;

    if (coordinatesA.length == coordinatesB.length) {

      for (int x = 0; x < coordinatesA.length; x++) {
        sum += Math.pow(coordinatesA[x] - coordinatesB[x], 2);
      }
      return Math.sqrt(sum);
    } else {
      throw new IllegalArgumentException("ERROR: Must be the same number of coordinates");
    }


  }

}
