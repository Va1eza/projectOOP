package adventurers;

public class Characters {
 public int healthPoints;
 public int attack;
 public int power;

 public Characters(int healthPoints, int attack) {
     this.healthPoints = healthPoints;
     this.attack = attack;
 }

 public int getHealthPoints() {
     return healthPoints;
 }

 public void setHealthPoints(int healthPoints) {
     this.healthPoints = healthPoints;
 }

 public int getPower() {
     return power;
 }

 public void setPower(int power) {
     this.power = power;
 }
 
 public int getAttack() {
	 return attack;
 }
 
 public void setAttack(int power) {
     this.attack = power;
     System.out.println("The character attack has been updated");
 }
}
