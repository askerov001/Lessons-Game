package com.askerov;

        import java.util.Random;

public class Main {

    public static int[] heroesHealth = {270, 280, 250};
    // Создали массив их всего лиш 3. То есть 3 гером с здровоьем
    public static int[] heroesDamage = {20, 15, 25};
    // Создали урон 3 из тоже 3
    public static String[] hereosAttackType = {"Physykal",
            "Magical", "Kinetic"};
    // Тип атаки наших героев


    public static int bossHealth = 700;
    // Здоровья Босса
    public static int bossDamage = 50;
    // удар Босса
    public static String bossDefensType = ""; // "" Сюда приходит hereosAttackType
    // Тип защиты Босса
    public static int roundNumber = 0;
    // Количество Раундов


    public static void main(String[] args) {
        // write your code here
        printStatistics();
        System.out.println("The game is started");
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        roundNumber++;
        System.out.println("______Round - " + roundNumber
                + "________");
        bossDefensType = changeBossDefense();
        System.out.println("Hero " + bossDefensType + " Boss receive supper damage");
        bossHits();
        heroesHits();
        printStatistics();

    }

    public static boolean  isGameFinished() {
//        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 &&
//                heroesHealth[2] <= 0) {
//            System.out.println("Boss win");
//            return true;
//        } // Hard code

        if (bossHealth <= 0) {
            System.out.println("Heroes Win");
            return true;
        }

        boolean allHeroesDead = true;

        for (int hero : heroesHealth) {
            if (hero > 0) {
                allHeroesDead = false;
                break;
            }

        }

        if (allHeroesDead) {
            System.out.println("Boss Win");
        }
        return allHeroesDead;
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
                if (heroesHealth[i] < 0){
                    heroesHealth[i] = 0;
                }
            }
        }
    }

    public static void heroesHits() {
        Random random = new Random();

        int coeff = random.nextInt(8) + 2; //2,3,4,5,6,7,8,9,10
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (hereosAttackType[i] == bossDefensType) {
                    bossHealth = bossHealth - heroesDamage[i] * coeff;
                    System.out.println("Supper damage" + heroesDamage[i]
                            * coeff + "[" + coeff + "]");

                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
                if (bossHealth < 0){
                    bossHealth = 0;
                }
            }
        }
    }

    public static String changeBossDefense() {
        Random random = new Random();
        int randomIndex = random.nextInt(hereosAttackType.length);
        return hereosAttackType[randomIndex];

    }

    public static void printStatistics() {
        System.out.println("Boss heath: " + bossHealth +
                " [" + bossHealth + "]");
        for (int i = 0; i < hereosAttackType.length; i++) {
            System.out.println(hereosAttackType[i] + " health: " +
                    heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
    }


}
