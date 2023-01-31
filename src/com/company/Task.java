package com.company;

public class Task {
    public double f(double[] x){
        return -2 * Math.pow(x[0],2) + 3 * Math.pow(x[2], 2) - x[0] * x[1] + 3;
    }
    public double fPrimX0(double[] x){
        return -4 * x[0] - x[2];
    }
    public double fPrimX1(double[] x){
        return -x[1];
    }
    public double fPrimX2(double[] x){
        return 6 * x[2];
    }
    private double[] x = new double[]{1.0,1.0,1.0};
    double[] d = new double[3];
    double currentLambda = 1;
    double nextLambda = 1;
    private final double EPSILON = 0.00001;
    void run(){
        do{
            currentLambda=nextLambda;
            d[0] = fPrimX0(x);
            d[1] = fPrimX1(x);
            d[2] = fPrimX2(x);
            System.out.println("");
            System.out.println("Current point: [" + x[0] + " " + x[1] + " " + x[2] + "]" + " f(x) = " + f(x));
            System.out.println("Current gradient: [" + d[0] + " " + d[1] + " " + d[2] + "]");
            System.out.println("Current lambda: " + currentLambda);
            nextLambda = newLambda(x,d,currentLambda);
            System.out.println("Next lambda: " + nextLambda);
            x[0] = x[0] + nextLambda * d[0];
            x[1] = x[1] + nextLambda * d[1];
            x[2] = x[2] + nextLambda * d[2];
        }while(Math.abs(nextLambda - currentLambda) > EPSILON);
    }
    public double newLambda(double[] x, double[] d, double lambda){
        return lambda - (fLambdaPrim(d,x,lambda) / fLambdaSecondPrim(d));
    }
    public double fLambda(double[] x, double[] d, double lambda){
        double d1 = d[0];
        double d2 = d[1];
        double d3 = d[2];
        double x1 = x[0];
        double x2 = x[1];
        double x3 = x[2];
        return Math.pow(lambda,2) * (-2 * Math.pow(d1,2) + 3 * Math.pow(d3,2) + d1 * d2)
                + lambda * ( -4 * x1 * d1 + 6 * d3 * x2 + x1 * d2 + x1 * d1)
                + (-2 * Math.pow(x1,2) + 3 * Math.pow(x3,2) + Math.pow(x1,2) + 3);
    }
    public double fLambdaPrim(double[] d, double[] x, double lambda){
        double d1 = d[0];
        double d2 = d[1];
        double d3 = d[2];
        double x1 = x[0];
        double x2 = x[1];
        double x3 = x[2];
        return -4 * x1 * d1 - 4 * lambda * Math.pow(d1,2) + 6*d3*x3 + 6 * lambda * Math.pow(d3,2) + x1*d2 + x1 *d1 + 2 * lambda * d1 * d2;
    }
    public double fLambdaSecondPrim(double[] d){
        double d1 = d[0];
        double d2 = d[1];
        double d3 = d[2];
        return -4 * Math.pow(d1,2) + 6 * Math.pow(d3,2) + 2 * d1 * d2;
    }
}
