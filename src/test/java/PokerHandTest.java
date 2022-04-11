import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokerHandTest {
    //•	первый символ — это номинал карты. Допустимые значения: 2, 3, 4, 5, 6, 7, 8, 9, T(en), J(ack), Q(ueen), K(ing), A(ce);
    //•	второй символ — масть. Допустимые значения: S(pades), H(earts), D(iamonds), C(lubs).

    @Test
    void checkToHighcard() {
        assertAll(
                () -> assertEquals(-2, new PokerHand("AS AC AH 2S 2D").checkToHighcard(new PokerHand("AS AC QH 2S 2D"))), // A-Q
                () -> assertEquals(0, new PokerHand("AS AC AH 2S 2D").checkToHighcard(new PokerHand("AS AC AH 2S 2D"))) // equal hand
        );
    }

    @Test
    void checkToPair() {
        assertAll(
                () -> assertEquals(14, new PokerHand("AS AC AH 2S 2D").checkToPair()), // FullHouse - yes
                () -> assertEquals(2, new PokerHand("2C 2D 2H AS 2S").checkToPair()), // Four - yes
                () -> assertEquals(9, new PokerHand("2D 5S 5D 9H 9D").checkToPair()), // TwoPair - yes
                () -> assertEquals(9, new PokerHand("2D 5S 9D 9H 9S").checkToPair()), // Three - yes
                () -> assertEquals(5, new PokerHand("2D 5S 5D 8H 9D").checkToPair()), //
                () -> assertEquals(14, new PokerHand("2D AS AD 9H KD").checkToPair()), //

                () -> assertEquals(0, new PokerHand("8C 3C 7D 6C 2S").checkToPair()), // not
                () -> assertEquals(0, new PokerHand("9C KC AS 7C 8H").checkToPair()) // not
        );
    }

    @Test
    void checkToTwo() {
        assertAll(
                () -> assertEquals(212, new PokerHand("AS AC AH 2S 2D").checkToTwo()), // FullHouse - yes (A*15+2)
                () -> assertEquals(32, new PokerHand("2C 2D 2H AS 2S").checkToTwo()), // Four - yes (2*15+2)
                () -> assertEquals(140, new PokerHand("2D 5S 5D 9H 9D").checkToTwo()), // 9*15+5
                () -> assertEquals(219, new PokerHand("2D AS AD 9H 9D").checkToTwo()), // A*15+9

                () -> assertEquals(0, new PokerHand("8C 8S 7D 6C 2S").checkToTwo()), // not
                () -> assertEquals(0, new PokerHand("9C KC AS 7C 8H").checkToTwo()) // not
        );
    }

    @Test
    void checkToThree() {
        assertAll(
                () -> assertEquals(14, new PokerHand("AS AC AH 2S 2D").checkToThree()), // FullHouse - yes
                () -> assertEquals(2, new PokerHand("2C 2D 2H AS 2S").checkToThree()), // Four - yes
                () -> assertEquals(9, new PokerHand("2D 5S 9D 9H 9C").checkToThree()), //
                () -> assertEquals(8, new PokerHand("8C JC 8D 6C 8S").checkToThree()), //

                () -> assertEquals(0, new PokerHand("9C KC 9S 7C 8H").checkToThree()) // not
        );
    }

    @Test
    void checkToStraight() {
        assertAll(
                () -> assertEquals(14, new PokerHand("KS TS JS AS QS").checkToStraight()), // RoyalFlush - yes
                () -> assertEquals(12, new PokerHand("9D TD JD 8D QD").checkToStraight()), // StraightFlush - yes
                () -> assertEquals(12, new PokerHand("9S TH JS 8C QD").checkToStraight()), //
                () -> assertEquals(9, new PokerHand("9H 7C 5H 6H 8S").checkToStraight()), //

                () -> assertEquals(0, new PokerHand("9C 8C 7C 6C AH").checkToStraight()), // Dif suit junior card
                () -> assertEquals(0, new PokerHand("AH 2C 3C 5C 4C").checkToStraight()) // A-2 don't work
        );
    }

    @Test
    void checkToFlush() {
        assertAll(
                () -> assertEquals(14, new PokerHand("KS TS JS AS QS").checkToFlush()), // RoyalFlush - yes
                () -> assertEquals(12, new PokerHand("9D TD JD 8D QD").checkToFlush()), // StraightFlush - yes
                () -> assertEquals(13, new PokerHand("3S TS 7S 8S KS").checkToFlush()), // Spades
                () -> assertEquals(14, new PokerHand("AH 7H 2H 6H TH").checkToFlush()), // Hearts
                () -> assertEquals(12, new PokerHand("2D 4D 7D 6D QD").checkToFlush()), // Diamonds
                () -> assertEquals(14, new PokerHand("2C 8C TC AC 5C").checkToFlush()), // Clubs


                () -> assertEquals(0, new PokerHand("2H 9S 7S 9C 2S").checkToFlush()), // not
                () -> assertEquals(0, new PokerHand("8S 8H 7H 6H JH").checkToFlush()), // not
                () -> assertEquals(0, new PokerHand("9C KC 9S 7C 8H").checkToFlush()) // not
        );
    }

    @Test
    void checkToFullHouse() {
        assertAll(
                () -> assertEquals(212, new PokerHand("AS AC AH 2S 2D").checkToFullHouse()), // A*15+2
                () -> assertEquals(44, new PokerHand("2C AH 2H AS 2S").checkToFullHouse()), // 2*15+14
                () -> assertEquals(0, new PokerHand("2D 9S 9D 9H 9C").checkToFullHouse()), // not
                () -> assertEquals(0, new PokerHand("8C 8D 7D 6C 8S").checkToFullHouse()), // not
                () -> assertEquals(0, new PokerHand("9C KC 9S 7C 8H").checkToFullHouse()) // not
        );
    }

    @Test
    void checkToFour() {
        assertAll(
                () -> assertEquals(14, new PokerHand("AS AC AH AD QD").checkToFour()), // end
                () -> assertEquals(2, new PokerHand("2C 2H 5C 2D 2S").checkToFour()), // begin
                () -> assertEquals(9, new PokerHand("2D 9S 9D 9H 9C").checkToFour()), // other
                () -> assertEquals(0, new PokerHand("8C 8D 7D 6C 8S").checkToFour()), // not four
                () -> assertEquals(0, new PokerHand("9C KC 7S KD 5H").checkToFour()) // not four
        );
    }

    @Test
    void checkToStraightFlush() {
        assertAll(
                () -> assertEquals(14, new PokerHand("KS TS JS AS QS").checkToStraightFlush()), // RoyalFlush - yes
                () -> assertEquals(12, new PokerHand("9S TS JS 8S QS").checkToStraightFlush()), // Spades
                () -> assertEquals(9, new PokerHand("9H 7H 5H 6H 8H").checkToStraightFlush()), // Hearts
                () -> assertEquals(6, new PokerHand("2D 4D 3D 6D 5D").checkToStraightFlush()), // Diamonds
                () -> assertEquals(9, new PokerHand("9C 8C 7C 6C 5C").checkToStraightFlush()), // Clubs
                () -> assertEquals(0, new PokerHand("9C 8C 7C 6C 5H").checkToStraightFlush()), // Dif suit junior card
                () -> assertEquals(0, new PokerHand("9H 8C 7C 6C 5C").checkToStraightFlush()) // Dif suit high card
        );
    }

    @Test
    void checkToRoyalFlush() {
        assertAll(
                () -> assertEquals(14, new PokerHand("KS TS JS AS QS").checkToRoyalFlush()), // Spades
                () -> assertEquals(14, new PokerHand("TH AH KH QH JH").checkToRoyalFlush()), // Hearts
                () -> assertEquals(14, new PokerHand("AD KD QD JD TD").checkToRoyalFlush()), // Diamonds
                () -> assertEquals(14, new PokerHand("TC JC QC KC AC").checkToRoyalFlush()), // Clubs
                () -> assertEquals(0, new PokerHand("TH JC QC KC AC").checkToRoyalFlush()), // Dif suit junior card
                () -> assertEquals(0, new PokerHand("TC JC QC KC AD").checkToRoyalFlush()), // Dif suit high card
                () -> assertEquals(0, new PokerHand("2C JC QC KC AC").checkToRoyalFlush()) // 2 not work
        );
    }


    @Test
    void compareTo() {
        assertAll(
                () -> assertEquals(2, new PokerHand("2S 7C 6H AS TD").compareTo(new PokerHand("AS QC 2H 5S 8D"))), // highcard (Q-T)

                () -> assertEquals(3, new PokerHand("2S 6C 6H AS TD").compareTo(new PokerHand("AS QC 2H 9S 9D"))), // pair (9-6)
                () -> assertEquals(1, new PokerHand("2S 9C 9H KS TD").compareTo(new PokerHand("AS QC 2H 9S 9D"))), // pair equal. higcard (9-8)

                () -> assertEquals(9, new PokerHand("8S TC TH 9S 8D").compareTo(new PokerHand("2S 2C 6H JS JD"))), // twopair 11*15+2 - 10*15+8
                () -> assertEquals(1, new PokerHand("5S JC JH 9S 5D").compareTo(new PokerHand("6S 6C 3H JS JD"))), // twopair 11*15+6 - 11*15+5
                () -> assertEquals(1, new PokerHand("6S JC JH 9S 6D").compareTo(new PokerHand("6S 6C TH JS JD"))), // twopair equal. highcard T-9

                () -> assertEquals(4, new PokerHand("2S JC 2H 9S 2D").compareTo(new PokerHand("6S 6C TH JS 6D"))), // three (6-2)
                () -> assertEquals(1, new PokerHand("6S 7C 6H 9S 6D").compareTo(new PokerHand("6S 6C TH 8S 6D"))), // three equal. highcard 8-7

                () -> assertEquals(1, new PokerHand("3S 2C 5H 6S 4D").compareTo(new PokerHand("5S 3C 4H 7S 6D"))), // straight (7-6)

                () -> assertEquals(4, new PokerHand("9S 2S TS 6S 4S").compareTo(new PokerHand("5S AS TS 7S 9S"))), // flush (A-T)

                () -> assertEquals(3, new PokerHand("4S AC 4H AS 4D").compareTo(new PokerHand("5S 5C 2H 2S 5D"))), // fullHouse 5*15+2 - 4*15+A
                () -> assertEquals(1, new PokerHand("5S KC 5H KS 5D").compareTo(new PokerHand("5S 5C AH AS 5D"))), // fullHouse 5*15+A - 5*15+K

                () -> assertEquals(1, new PokerHand("TS TC TH AS TD").compareTo(new PokerHand("JS JC JH 2S JD"))), // four J-T
                () -> assertEquals(1, new PokerHand("TS TC TH 7S TD").compareTo(new PokerHand("TS TC TH 8S TD"))), // four equal. highcard 8-7

                () -> assertEquals(5, new PokerHand("6S 2S 5S 3S 4S").compareTo(new PokerHand("JS 8S TS 7S 9S"))), // straightFlush (J-6)
                () -> assertEquals(11, new PokerHand("9S 2S TS 6S 4S").compareTo(new PokerHand("JS 8S TS 7S 9S"))), // straightFlush (J-0)

                () -> assertEquals(14, new PokerHand("6S 2S 5S 3S 4S").compareTo(new PokerHand("JS KS TS AS QS"))) // royalFlush  A-0
        );
    }
}