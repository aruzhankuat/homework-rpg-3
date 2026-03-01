package com.narxoz.rpg.battle;

import java.util.List;
import java.util.Random;

public final class BattleEngine {
    private static BattleEngine instance;
    private Random random = new Random(1L);

    private BattleEngine() {}

    public static BattleEngine getInstance() {
        if (instance == null) {
            instance = new BattleEngine();
        }
        return instance;
    }

    public EncounterResult runEncounter(List<Combatant> teamA, List<Combatant> teamB) {
        EncounterResult result = new EncounterResult();
        int rounds = 0;

        while (hasAlive(teamA) && hasAlive(teamB)) {
            rounds++;
            result.addLog("--- Round " + rounds + " ---");

            Combatant attacker = getFirstAlive(teamA);
            Combatant defender = getFirstAlive(teamB);

            if (attacker != null && defender != null) {
                int damage = attacker.getAttackPower();
                defender.takeDamage(damage);
                result.addLog(attacker.getName() + " attacks " + defender.getName() + " for " + damage + " damage.");
            }

            if (hasAlive(teamB)) {
                Combatant enemyAttacker = getFirstAlive(teamB);
                Combatant heroDefender = getFirstAlive(teamA);

                if (enemyAttacker != null && heroDefender != null) {
                    int damage = enemyAttacker.getAttackPower();
                    heroDefender.takeDamage(damage);
                    result.addLog(enemyAttacker.getName() + " counters " + heroDefender.getName() + " for " + damage + " damage.");
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