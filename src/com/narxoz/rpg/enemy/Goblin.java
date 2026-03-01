package com.narxoz.rpg.enemy;

public class Goblin implements Enemy {
    private int health = 100;

    @Override public String getTitle() { return "Гоблин-разведчик"; }
    @Override public int getDamage() { return 15; }
    @Override public boolean isDefeated() { return health <= 0; }
    @Override public void applyDamage(int amount) {
        this.health = Math.max(0, this.health - amount);
    }
}