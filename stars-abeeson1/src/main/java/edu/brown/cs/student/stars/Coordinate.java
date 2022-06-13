package edu.brown.cs.student.stars;


/**
 * Coordinate class for storing multidimensional points.
 * The star class extends this.
 */
public class Coordinate {
  private double[] coordinates;
  private int id;

  /**
   * Two fields.
   * coordinates to represent the xyz of the coordinate.
   * id to represent a numerical id for the coordinate.
   * @param points the points of the coordinate
   * @param idnumber the id of the coordinate
   */

  public Coordinate(double[] points, int idnumber) {
    coordinates = points;
    id = idnumber;
  }

  /**
   * Accessor and Mutator Methods for Coordinate object.
   * @return this.coordinates
   */
  public double[] getCoordinate() {
    return this.coordinates;
  }

  public void setCoordinate(double[] coordinate) {
    this.coordinates = coordinate;
  }

  public int getID() {
    return this.id;
  }

  public void setID(int newid) {
    this.id = newid;
  }

}
