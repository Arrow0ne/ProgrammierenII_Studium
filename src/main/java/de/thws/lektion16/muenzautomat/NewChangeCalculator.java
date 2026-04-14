package de.thws.lektion16.muenzautomat;

public class NewChangeCalculator implements ChangeCalculator{

    @Override
    public int[] getChange(int euros, int cent) {

        int total = euros * 100 + cent;
        int[] change = new int[8];

        change[7] = total / 200; total %= 200;  // 2,00 €
        change[6] = total / 100; total %= 100;  // 1,00 €
        change[5] = total /  50; total %=  50;  // 0,50 €
        change[4] = total /  20; total %=  20;  // 0,20 €
        change[3] = total /  10; total %=  10;  // 0,10 €
        change[2] = total /   5; total %=   5;  // 0,05 €
        change[1] = total /   2; total %=   2;  // 0,02 €
        change[0] = total;                      // 0,01 €

        return change;
    }
}
