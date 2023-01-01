import java.io.*;
import java.util.*;

public class AIController {

    protected BufferedReader br;

    protected int health;

    protected int aiState;

    protected int lastAiState;

    protected int lastDamageTaken;

    protected Object enemy;

    protected static final int STATE_Idle = 0;

    protected static final int STATE_Hungry = 1;

    protected static final int STATE_Hunting = 2;

    protected static final int STATE_Pissed = 3;

    protected static final int STATE_Scared = 4;

    protected static final int STATE_Dead = 5;

    protected static final double aggro = 0.4;

    protected static final int scareHealth = 10;

    protected static final int scareDamage = 20;

    protected static final double huntDistractability = 0.3;

    protected ArrayList[] attacks;

    public AIController() {
    }

    public void tick(long dt) {
        switch(aiState) {
            case STATE_Idle:
                break;
            case STATE_Hungry:
                break;
            case STATE_Hunting:
                break;
            case STATE_Pissed:
                break;
            case STATE_Scared:
                break;
            case STATE_Dead:
                break;
            default:
                System.out.println("Something is rotten in the state of AI.");
        }
    }

    public void takeDamage(int dmg, Object instigator) {
        health -= dmg;
        lastDamageTaken = dmg;
        if (health > 0) switch(aiState) {
            case STATE_Idle:
                if (aggro > 0 && Math.random() < aggro) System.out.println("Idle -> Pissed");
                if (aggro < 0 || lastDamageTaken > scareDamage) System.out.println("Idle -> Scared");
                break;
            case STATE_Hungry:
                if (aggro > 0) System.out.println("Hungry -> Pissed"); else System.out.println("Hungry -> Scared");
                break;
            case STATE_Hunting:
                if (!instigator.equals(enemy) && Math.random() < huntDistractability) enemy = instigator;
                break;
            case STATE_Pissed:
                break;
            case STATE_Scared:
                break;
            case STATE_Dead:
                break;
            default:
                System.out.println("Something is rotten in the state of AI.");
        } else {
            aiState = STATE_Dead;
            lastAiState = aiState;
        }
    }

    public void touch(Object other) {
        switch(aiState) {
            case STATE_Idle:
                break;
            case STATE_Hungry:
                break;
            case STATE_Hunting:
                if (!other.equals(enemy)) if (Math.random() < huntDistractability) {
                    enemy = other;
                    goToState(STATE_Pissed);
                } else goToState(STATE_Pissed);
                break;
            case STATE_Pissed:
                break;
            case STATE_Scared:
                break;
            default:
                System.out.println("Something is rotten in the state of AI.");
        }
    }

    private void goToState(int nuState) {
        lastAiState = aiState;
        aiState = nuState;
    }

    private void moveTowards(Object other) {
    }

    private void attack(Object other) {
    }

    private void moveAway(Object other) {
    }
}
