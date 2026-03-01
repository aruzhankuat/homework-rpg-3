package com.narxoz.rpg.battle;

import java.util.List;
import java.util.Random;

public final class BattleEngine {
    private static BattleEngine instance;
    private Random random = new Random();

    private BattleEngine() {}

    public static synchronized BattleEngine getInstance() {
        if (instance == null) {
            instance = new BattleEngine();
        }
        return instance;
    }

    public void setRandomSeed(long seed) {
        this.random = new Random(seed);
    }

    public EncounterResult runEncounter(List<Combatant> teamA, List<Combatant> teamB) {
        EncounterResult result = new EncounterResult();
        int rounds = 0;

        while (hasAlive(teamA) && hasAlive(teamB)) {
            rounds++;
            result.addLog("--- Round " + rounds + " ---");

            Combatant hero = getFirstAlive(teamA);
            Combatant enemy = getFirstAlive(teamB);

            if (hero != null && enemy != null) {
                int hDmg = hero.getAttackPower();
                enemy.takeDamage(hDmg);
                result.addLog(hero.getName() + " наносит " + hDmg + " урона " + enemy.getName());

                if (enemy.isAlive()) {
                    int eDmg = enemy.getAttackPower();
                    hero.takeDamage(eDmg);
                    result.addLog(enemy.getName() + " отвечает и наносит " + eDmg + " урона " + hero.getName());
                }
            }
        }

        result.setRounds(rounds);
        result.setWinner(hasAlive(teamA) ? "Heroes" : "Enemies");
        return result;
    }

    private boolean hasAlive(List<Combatant> team) {
        return team.stream().anyMatch(Combatant::isAlive);
    }

    private Combatant getFirstAlive(List<Combatant> team) {
        return team.stream().filter(Combatant::isAlive).findFirst().orElse(null);
    }
}