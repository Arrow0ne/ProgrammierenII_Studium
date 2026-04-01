package de.thws.lektion14;

public class Kugelvolumen {
    public static double berechneKugelvolumen(double radius)
    {
        if(radius < 0){
            throw new RuntimeException("Nix kleiner 0");
        }
        return 4/3*Math.PI*radius*radius*radius;
    }
}
