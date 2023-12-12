package edu.labs.lab6.coordinategenerator.coordinategenerator;

import java.util.Random;

public class DataPointGenerator {

  private int nPoints;
  private double xMin;
  private double xMax;
  private double yMin;
  private double yMax;

  public DataPointGenerator(int nPoints, double xMin, double xMax, double yMin, double yMax) {
    this.nPoints = nPoints;
    this.xMin = xMin;
    this.xMax = xMax;
    this.yMin = yMin;
    this.yMax = yMax;
  }

  public double[][] generatePoints() {
    Random rand = new Random();
    double[][] points = new double[nPoints][2];

    for (int i = 0; i < nPoints; i++) {
      double x = xMin + (xMax - xMin) * rand.nextDouble();
      double y = yMin + (yMax - yMin) * rand.nextDouble();
      points[i][0] = Math.round(x * 100.0) / 100.0;
      points[i][1] = Math.round(y * 100.0) / 100.0;
    }

    return points;
  }

}
