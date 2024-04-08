package application;
import adventurers.*;
import gui.*;	

public class Main {
 public static void main(String[] args) {
     GUI.launch(GUI.class, args);
     MagicWand spoon = new MagicWand(5);
     Mage mage = new Mage(80, 15, 50, spoon);
     mage.setAttack(60, 50);
     Characters dude = new Characters(100, 20);
     dude.setAttack(60);
 }
}















