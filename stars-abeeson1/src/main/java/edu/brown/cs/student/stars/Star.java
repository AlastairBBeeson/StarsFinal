package edu.brown.cs.student.stars;

/**
 * Star class that extends Coordinate class. Inherits coordinates and id from Coordinate
 * Also includes a distance field for storing calculated euclidean distances
 * for comparison in stars methods.
 */
public class Star extends Coordinate {

  private String StarName;
  private Double distance;

  /**
   * four fields
   * coordinates to represent the xyz of the star
   * StarID is a numerical id for the star
   * StarName for the name of the star
   * distance for the star's distance from a specific point
   */
  public Star(int id, String name, double[] coordinates) {
    super(coordinates, id);
    StarName = name;
    distance = null;
  }

  /**
   * Accessor and Mutator Methods for Star object
   */

  public String getStarName() {
    return this.StarName;
  }

  public void setStarName(String name) {
    this.StarName = name;
  }

  public void setDistance(double euclideandistance) {
    this.distance = euclideandistance;
  }

  public double getDistance() {
    return this.distance;
  }
}
