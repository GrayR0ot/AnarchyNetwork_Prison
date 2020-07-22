package eu.grayroot.anarchyprison.object;


public class PrisonPlayer {
	
	int id;
	String name;
	String uuid;
	String rank;
	double balance;
	int blocks;
	int prestige;
	int mana;

	public PrisonPlayer(int id, String name, String uuid, String rank, double balance, int blocks, int prestige, int mana) {
		this.id = id;
		this.name = name;
		this.uuid = uuid;
		this.rank = rank;
		this.balance = balance;
		this.blocks = blocks;
		this.prestige = prestige;
		this.mana = mana;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getBlocks() {
		return blocks;
	}

	public void setBlocks(int blocks) {
		this.blocks = blocks;
	}

	public int getPrestige() {
		return prestige;
	}

	public void setPrestige(int prestige) {
		this.prestige = prestige;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}
}
