package edu.brown.cs.student.stars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;

public class Naive_Radius {

  /**
   * A naive radius implementation using a coordinate
   * Determines how many stars within a specific radius from a coordinate
   * Takes 4 Arguments: k x y z
   * Implements specific comparator for comparing Euclidean Distance
   * The final ArrayList is truncated according to the number of neighbors
   * Prints out the stars, makes sure to omit the named star from output
   */

  public void RadiusPosition(String input, ArrayList<Star> stars) {

    Pattern regex = Pattern.compile("\\s+(?=(?:[^\"]*[\"][^\"]*[\"])*[^\"]*$)");
    String[] args = input.split(regex.pattern());

    ArrayList<Star> radiusstar = new ArrayList<>();

    try {
      double radius = Double.parseDouble(args[0]);
      double inputX = Double.parseDouble(args[1]);
      double inputY = Double.parseDouble(args[2]);
      double inputZ = Double.parseDouble(args[3]);

      if (radius < 0) {
        System.out.println("ERROR: The Radius Must Be A Non-Negative Number");
        return;
      } else {
        double[] targetcoords = new double[] {inputX, inputY, inputZ};
        ArrayList<Star> iter = stars;

        for (int i = 0; i < iter.size(); i++) {
          Star currentstar = iter.get(i);
          double[] currentCoords = currentstar.getCoordinate();
          double currentDistance = euclideanDistance(currentCoords, targetcoords);
          currentstar.setDistance(currentDistance);

          if (currentDistance <= radius) {
            radiusstar.add(currentstar);
          }

          Collections.sort(radiusstar, new Comparator<Star>() {

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

        }

      }
    } catch (NullPointerException e) {
      System.out.println("ERROR: RadiusPosition Null Pointer Exception");
      return;
    } catch (NumberFormatException e) {
      System.out.println("ERROR: RadiusPosition Number Format Exception");
      return;
    } catch (IllegalArgumentException e) {
      System.out.println("ERROR: RadiusPosition Arguments Incorrect");
      return;
    }

    for (int i = 0; i < radiusstar.size(); i++) {
      System.out.println(radiusstar.get(i).getID());
    }
  }


  /**
   * A naive radius implementation using a star name.
   * Determines how many stars within a specific radius of named star
   * Takes 2 Arguments: k name
   * Implements specific comparator for comparing Euclidean Distance
   * The final ArrayList is truncated according to the number of neighbors
   * Prints out the stars, makes sure to omit the named star from output
   */

  public void RadiusName(String input, ArrayList<Star> stars) {

    Pattern regex = Pattern.compile("\\s+(?=(?:[^\"]*[\"][^\"]*[\"])*[^\"]*$)");
    String[] args = input.split(regex.pattern());

    ArrayList<Star> radiusstar = new ArrayList<>();

    try {

      Double inputcheck = Double.parseDouble(args[0]);

      String targetname = args[1].substring(1, args[1].length() - 1);

      double[] targetcoords = null;

      if (StarsCommandManager.StarNames.containsKey(targetname)) {
        Star targetstar = StarsCommandManager.StarNames.get(targetname);
        targetcoords = targetstar.getCoordinate();
      } else {
        System.out.println("ERROR: No Star by that name in the data");
        return;
      }

      double radius = Double.parseDouble(args[0]);

      if (radius < 0) {
        System.out.println("ERROR: The Radius Must Be A Non-Negative Integral");
        return;
      } else {
        ArrayList<Star> iter = stars;

        for (int i = 0; i < iter.size(); i++) {
          Star currentstar = iter.get(i);
          double[] currentCoords = currentstar.getCoordinate();
          double currentDistance = euclideanDistance(currentCoords, targetcoords);
          currentstar.setDistance(currentDistance);

          if (currentDistance <= radius) {
            if (currentstar.getStarName().equals(targetname) == false) {
              radiusstar.add(currentstar);
            }
          }

          Collections.sort(radiusstar, new Comparator<Star>() {

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

        }

      }
    } catch (NullPointerException e) {
      System.out.println("ERROR: RadiusName Null Pointer Exception");
      return;
    } catch (NumberFormatException e) {
      System.out.println("ERROR: RadiusName Number Format Exception");
      return;
    } catch (IllegalArgumentException e) {
      System.out.println("ERROR: RadiusName IllegalArgument Exception");
      return;
    } catch (StringIndexOutOfBoundsException e) {
      System.out.println("ERROR: NeighborsName Second Argument Needs To Be a String");
      return;
    }

    for (int i = 0; i < radiusstar.size(); i++) {
      System.out.println(radiusstar.get(i).getID());
    }

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