package com.narxoz.rpg.hero;

public class Mage implements Hero {
    private final String name;
    private final int power;
    private int health;

    public Mage(String name) {
        this.name = name;
        this.power = 40;
        this.health = 80;
    }

    @Override public String getName() { return name; }
    @Override public int getPower() { return power; }
    @Override public int getHealth() { return health; }
    @Override public boolean isAlive() { return health > 0; }
    @Override public void receiveDamage(int amount) {
        this.health = Math.max(0, this.health - amount);
    }
}