package edu.brown.cs.student.stars;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class creates a CSVparser object that allows stars to intake data from a CSV.
 * It checks to make sure that the header is valid before iterating through the file
 * line by line and populating an arraylist with data.
 * There is also a singular accessor method to get the data from our CSVparser object.
 */

public class CSVparser {

  private ArrayList<String[]> data;
  private String[] header;
  private BufferedReader bufferedReader;
  private FileReader fileReader;

  public CSVparser(String path, String[] csvheader) {
    data = new ArrayList<>();
    header = csvheader;

    if (header == null || path == null) {
      System.out.println("ERROR: Path/Header Required to Read Data");
      data.clear();
      return;
    } else {
      try {
        fileReader = new FileReader(path);
        bufferedReader = new BufferedReader(fileReader);
        String[] columns = bufferedReader.readLine().split(",");
        String input;

        if (Arrays.equals(header, columns)) {
          while ((input = bufferedReader.readLine()) != null) {
            String[] datacells = input.split(",");
            data.add(datacells);
          }
        } else {
          System.out.println("ERROR: Header Does Not Match");
          data.clear();
          return;
        }
      } catch (NullPointerException e) {
        System.out.println("ERROR: NullPointerException");
        data.clear();
        return;
      } catch (NumberFormatException e) {
        System.out.println("ERROR: Numbers Not Formatted Correctly");
        data.clear();
        return;
      } catch (IllegalArgumentException e) {
        System.out.println("ERROR: Illegal Arguments Used");
        data.clear();
        return;
      } catch (FileNotFoundException e) {
        System.out.println("ERROR: Could Not Find File");
        data.clear();
        return;
      } catch (IOException e) {
        System.out.println("ERROR: Could Not Read Line");
        data.clear();
        return;
      }


    }
  }
  public void setCSVData(ArrayList<String[]> input) {
    this.data = input;
  }

  public ArrayList<String[]> getCSVData() {
    return this.data;
  }
}
