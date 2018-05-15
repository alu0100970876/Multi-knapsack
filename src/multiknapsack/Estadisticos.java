package multiknapsack;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Estadisticos {
  public static void main(String [] argc) throws IOException {
    ArrayList<String> instancias = new ArrayList<>();
    instancias.add("instancias/MK75_01.txt");
    instancias.add("instancias/MK75_02.txt");
    instancias.add("instancias/MK75_03.txt");
    instancias.add("instancias/MK75_04.txt");
    instancias.add("instancias/MK75_05.txt");
    instancias.add("instancias/MK75_06.txt");
    instancias.add("instancias/MK75_07.txt");
    instancias.add("instancias/MK75_08.txt");
    instancias.add("instancias/MK75_09.txt");
    instancias.add("instancias/MK75_10.txt");
    instancias.add("instancias/MK100_01.txt");
    instancias.add("instancias/MK100_02.txt");
    instancias.add("instancias/MK100_03.txt");
    instancias.add("instancias/MK100_04.txt");
    instancias.add("instancias/MK100_05.txt");
    instancias.add("instancias/MK100_06.txt");
    instancias.add("instancias/MK100_07.txt");
    instancias.add("instancias/MK100_08.txt");
    instancias.add("instancias/MK100_09.txt");
    instancias.add("instancias/MK100_10.txt");
    instancias.add("instancias/MK200_01.txt");
    instancias.add("instancias/MK200_02.txt");
    instancias.add("instancias/MK200_03.txt");
    instancias.add("instancias/MK200_04.txt");
    instancias.add("instancias/MK200_05.txt");
    instancias.add("instancias/MK200_06.txt");
    instancias.add("instancias/MK200_07.txt");
    instancias.add("instancias/MK200_08.txt");
    instancias.add("instancias/MK200_09.txt");
    instancias.add("instancias/MK200_10.txt");
    Clock temporizador = new Clock();
    String line = new String();
    FileWriter fw = new FileWriter(argc[0]);
    BufferedWriter bw = new BufferedWriter(fw);
    bw.write("Greedy");
    System.out.println("Greedy");
    bw.newLine();
    for(int i = 0; i < instancias.size(); i++) {
      Solucion sol = new Solucion(instancias.get(i));
      temporizador.start();
      sol.greedySolve();
      temporizador.stop();
      line = sol.valorTotal() + ", " + temporizador.getElapsedTime();
      bw.write(line);
      bw.newLine();
      System.out.println(line);
    }
    bw.write("Grasp mov 1 10 iter");
    System.out.println("Graso mov 1 10 iter");
    bw.newLine();
    for(int i = 0; i < instancias.size(); i++) {
      Solucion sol = new Solucion(instancias.get(i));
      temporizador.start();
      sol.GRASP(10, 1, 5);
      temporizador.stop();
      line = sol.valorTotal() + ", " + temporizador.getElapsedTime();
      bw.write(line);
      bw.newLine();
      System.out.println(line);
    }
    bw.write("Grasp mov 2 10 iter");
    System.out.println("Grasp mov 2 10 iter");
    bw.newLine();
    for(int i = 0; i < instancias.size(); i++) {
      Solucion sol = new Solucion(instancias.get(i));
      temporizador.start();
      sol.GRASP(10, 2, 5);
      temporizador.stop();
      line = sol.valorTotal() + ", " + temporizador.getElapsedTime();
      bw.write(line);
      bw.newLine();
      System.out.println(line);
    }
    bw.write("Grasp mov 3 10 iter");
    System.out.println("Grasp mov 3 10 iter");
    bw.newLine();
    for(int i = 0; i < instancias.size(); i++) {
      Solucion sol = new Solucion(instancias.get(i));
      temporizador.start();
      sol.GRASP(10, 3, 5);
      temporizador.stop();
      line = sol.valorTotal() + ", " + temporizador.getElapsedTime();
      bw.write(line);
      bw.newLine();
      System.out.println(line);
    }
    bw.write("Grasp mov 4 10 iter");
    System.out.println("Grasp mov 4 10 iter");
    bw.newLine();
    for(int i = 0; i < instancias.size(); i++) {
      Solucion sol = new Solucion(instancias.get(i));
      temporizador.start();
      sol.GRASP(10, 4, 5);
      temporizador.stop();
      line = sol.valorTotal() + ", " + temporizador.getElapsedTime();
      bw.write(line);
      bw.newLine();
      System.out.println(line);
    }
    bw.write("Grasp mov 1 100 iter");
    System.out.println("Graso mov 1 100 iter");
    bw.newLine();
    for(int i = 0; i < instancias.size(); i++) {
      Solucion sol = new Solucion(instancias.get(i));
      temporizador.start();
      sol.GRASP(100, 1, 5);
      temporizador.stop();
      line = sol.valorTotal() + ", " + temporizador.getElapsedTime();
      bw.write(line);
      bw.newLine();
      System.out.println(line);
    }
    bw.write("Grasp mov 2 100 iter");
    System.out.println("Grasp mov 2 100 iter");
    bw.newLine();
    for(int i = 0; i < instancias.size(); i++) {
      Solucion sol = new Solucion(instancias.get(i));
      temporizador.start();
      sol.GRASP(100, 2, 5);
      temporizador.stop();
      line = sol.valorTotal() + ", " + temporizador.getElapsedTime();
      bw.write(line);
      bw.newLine();
      System.out.println(line);
    }
    bw.write("Grasp mov 3 100 iter");
    System.out.println("Grasp mov 3 100 iter");
    bw.newLine();
    for(int i = 0; i < instancias.size(); i++) {
      Solucion sol = new Solucion(instancias.get(i));
      temporizador.start();
      sol.GRASP(100, 3, 5);
      temporizador.stop();
      line = sol.valorTotal() + ", " + temporizador.getElapsedTime();
      bw.write(line);
      bw.newLine();
      System.out.println(line);
    }
    bw.write("Grasp mov 4 100 iter");
    System.out.println("Grasp mov 4 100 iter");
    bw.newLine();
    for(int i = 0; i < instancias.size(); i++) {
      Solucion sol = new Solucion(instancias.get(i));
      temporizador.start();
      sol.GRASP(100, 4, 5);
      temporizador.stop();
      line = sol.valorTotal() + ", " + temporizador.getElapsedTime();
      bw.write(line);
      bw.newLine();
      System.out.println(line);
    }
    bw.write("VNS 10 iter");
    System.out.println("VNS 10 iter");
    bw.newLine();
    for(int i = 0; i < instancias.size(); i++) {
      Solucion sol = new Solucion(instancias.get(i));
      temporizador.start();
      sol.VNS(10, 5);
      temporizador.stop();
      line = sol.valorTotal() + ", " + temporizador.getElapsedTime();
      bw.write(line);
      bw.newLine();
      System.out.println(line);
    }
    bw.write("VNS 100 iter");
    System.out.println("VNS 100 iter");
    bw.newLine();
    for(int i = 0; i < instancias.size(); i++) {
      Solucion sol = new Solucion(instancias.get(i));
      temporizador.start();
      sol.VNS(100, 5);
      temporizador.stop();
      line = sol.valorTotal() + ", " + temporizador.getElapsedTime();
      bw.write(line);
      bw.newLine();
      System.out.println(line);
    }
    bw.write("Multiarranque mov 1");
    System.out.println("Multiarranque mov 1");
    bw.newLine();
    for(int i = 0; i < instancias.size(); i++) {
      Solucion sol = new Solucion(instancias.get(i));
      temporizador.start();
      sol.multiArranque(1);
      temporizador.stop();
      line = sol.valorTotal() + ", " + temporizador.getElapsedTime();
      bw.write(line);
      bw.newLine();
      System.out.println(line);
    }
    bw.write("Multiarranque mov 2");
    System.out.println("Multiarranque mov 2");
    bw.newLine();
    for(int i = 0; i < instancias.size(); i++) {
      Solucion sol = new Solucion(instancias.get(i));
      temporizador.start();
      sol.multiArranque(2);
      temporizador.stop();
      line = sol.valorTotal() + ", " + temporizador.getElapsedTime();
      bw.write(line);
      bw.newLine();
      System.out.println(line);
    }
    bw.write("Multiarranque mov 3");
    System.out.println("Multiarranque mov 3");
    bw.newLine();
    for(int i = 0; i < instancias.size(); i++) {
      Solucion sol = new Solucion(instancias.get(i));
      temporizador.start();
      sol.multiArranque(3);
      temporizador.stop();
      line = sol.valorTotal() + ", " + temporizador.getElapsedTime();
      bw.write(line);
      bw.newLine();
      System.out.println(line);
    }
    bw.write("Multiarranque mov 4");
    System.out.println("Multiarranque mov 4");
    bw.newLine();
    for(int i = 0; i < instancias.size(); i++) {
      Solucion sol = new Solucion(instancias.get(i));
      temporizador.start();
      sol.multiArranque(1);
      temporizador.stop();
      line = sol.valorTotal() + ", " + temporizador.getElapsedTime();
      bw.write(line);
      bw.newLine();
      System.out.println(line);
    }
    bw.write("LNS 10 iter");
    System.out.println("LNS 10 iter");
    bw.newLine();
    for(int i = 0; i < instancias.size(); i++) {
      Solucion sol = new Solucion(instancias.get(i));
      temporizador.start();
      sol.LNS(10, 5);
      temporizador.stop();
      line = sol.valorTotal() + ", " + temporizador.getElapsedTime();
      bw.write(line);
      bw.newLine();
      System.out.println(line);
    }
    bw.write("LNS 100 iter");
    System.out.println("LNS 100 iter");
    bw.newLine();
    for(int i = 0; i < instancias.size(); i++) {
      Solucion sol = new Solucion(instancias.get(i));
      temporizador.start();
      sol.LNS(100, 5);
      temporizador.stop();
      line = sol.valorTotal() + ", " + temporizador.getElapsedTime();
      bw.write(line);
      bw.newLine();
      System.out.println(line);
    }
    bw.close();
    fw.close();
  }
}
