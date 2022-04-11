import java.util.ArrayList;
import java.util.Collections;

public class Main {

//•	первый символ — это номинал карты. Допустимые значения: 2, 3, 4, 5, 6, 7, 8, 9, T(en), J(ack), Q(ueen), K(ing), A(ce);
//•	второй символ — масть. Допустимые значения: S(pades), H(earts), D(iamonds), C(lubs).

    public static void main(String[] args) {


        ArrayList<PokerHand> hands = new ArrayList<PokerHand>();

        hands.add(new PokerHand("KS 2H 5C JD TD"));
        hands.add(new PokerHand("2C 3C AC 4C 5C"));

        /*

        hands.add(new PokerHand("JS QS 2S TS 3S")); // Флеш1
        hands.add(new PokerHand("TS JH QS 9S 8S")); // Стрит
        hands.add(new PokerHand("QS JH JS QD QH")); // Фул Хаус
        hands.add(new PokerHand("TS QS JS AS KS")); // Роял Флеш
        hands.add(new PokerHand("JS QS 2S TS 6S")); // Флеш2   (Флеш2 > Флеш1)
        hands.add(new PokerHand("JH JS 2S JD JC")); // Каре
        hands.add(new PokerHand("QS JS 2S AH 3D")); // Старшая карта

        */

        System.out.println("Before:" + hands);
        Collections.sort(hands);
        System.out.println("After:" +hands);
    }
}
