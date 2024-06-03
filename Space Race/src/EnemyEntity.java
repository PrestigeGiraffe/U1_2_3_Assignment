/*
 * Purpose: Class that contains template values for 'enemy' entities in this game
 * Author: Johnson
 */

public class EnemyEntity extends Entity {
    private double reward; // Enemies will drop rewards

    // GETTER AND SETTER FOR REWARD
    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }
}
