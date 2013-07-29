package cluedo.cards;

public class Weapon implements WeaponI {

	private String card;
	
	public Weapon(int i){
		this.card = Weapon.WEAPONS[i];
	}
	
	
	@Override
	public String getCard() {
		// TODO Auto-generated method stub
		return null;
	}

}
