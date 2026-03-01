package com.narxoz.rpg.hero;

public class Warrior implements Hero {
    private final String name;
    private final int power;
    private int health;

    public Warrior(String name) {
        this.name = name;
        this.power = 25;
        this.health = 150;
    }

    @Override public String getName() { return name; }
    @Override public int getPower() { return power; }
    @Override public int getHealth() { return health; }
    @Override public boolean isAlive() { return health > 0; }
    @Override public void receiveDamage(int amount) {
        this.health = Math.max(0, this.health - amount);
    }
}