package adventurers;

public class Mage extends Characters {
	public int mana;
	 MagicWand magicwand;
	 
 public Mage(int healthPoints, int attack, int mana, MagicWand stick) {
     super(healthPoints, attack);
     this.mana = mana;
     this.magicwand = stick;
 }

 public int getMana() {
     return mana;
 }

 public void setMana(int mana) {
     this.mana = mana;
 }

 public void setAttack(int power, int mana) {
     this.attack = power+(mana/2);
     System.out.println("The mage's attack has been updated");
 }
 

 
}