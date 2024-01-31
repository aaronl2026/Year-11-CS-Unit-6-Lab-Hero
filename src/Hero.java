import java.util.Random;
public class Hero {
    private String name;
    private int hitPoints;
    public Hero(String name){
        this.name = name;
        this.hitPoints = 100;
    }
    public String getName(){
        return this.name;
    }
    public int getHitPoints(){
        return hitPoints;
    }
    public String toString(){
        return("Hero{name='"+this.name+"', hitPoints="+hitPoints+"}");
    }
    public void attack(Hero opponent){
        double randomNum = Math.random();
        if (randomNum >= 0.5){
            this.hitPoints = this.hitPoints - 10;
        }
        else if (randomNum < 0.5 && randomNum >= 0){
            opponent.hitPoints = opponent.hitPoints - 10;
        }
    }
    public void senzuBean(){
        hitPoints = 100;
    }
    private void fightUntilTheDeathHelper(Hero opponent){
        while(this.hitPoints > 0 && opponent.hitPoints > 0){
            attack(opponent);
        }
    }
    public String fightUntilTheDeath(Hero opponent){
        senzuBean();
        opponent.senzuBean();
        fightUntilTheDeathHelper(opponent);
        return(this.name+": "+this.hitPoints+" "+opponent.name+": "+opponent.hitPoints);
    }
    private int[] nFightsToTheDeathHelper(Hero opponent, int n){
        int propTally = 0;
        int oppTally = 0;
        while (n > 0){
            fightUntilTheDeath(opponent);
            if(this.hitPoints <= 0){
                oppTally = oppTally + 1;
            }
            else if (opponent.hitPoints <= 0){
                propTally = propTally + 1;
            }
            n = n-1;
        }
        int[] tally = {propTally, oppTally};
        return tally;
    }
    public String nFightsToTheDeath(Hero opponent, int n){
        int[] scores = nFightsToTheDeathHelper(opponent, n);
        String answer = this.name + ": " +scores[0] + " wins\n" + opponent.name + ": " + scores[1] + " wins\n" ;
        if(scores[0]>scores[1]){
            answer += this.name + " wins!";
        }
        else if(scores[1]>scores[0]){
            answer += opponent.name + " wins!";
        }
        else {
            answer += "OMG! It was actually a draw!";
        }
        return answer;
    }
    public void dramaticFightToTheDeath(Hero opponent) throws InterruptedException {
        while(hitPoints > 0 || opponent.hitPoints > 0){
            attack(opponent);
            Thread.sleep(1000);
            System.out.println(this.name + ": " + hitPoints+"\t"+opponent.name+": "+opponent.hitPoints);
            if(hitPoints == 0){
                System.out.println(this.name+" wins!");
            }
            else if (opponent.hitPoints == 0){
                System.out.println(opponent.name+" wins!");
            }
        }
    }
}
