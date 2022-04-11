public class Card implements Comparable<Card> {
    private char value;
    private char suit;
    //•	первый символ — это номинал карты. Допустимые значения: 2, 3, 4, 5, 6, 7, 8, 9, T(en), J(ack), Q(ueen), K(ing), A(ce);
    //•	второй символ — масть. Допустимые значения: S(pades), H(earts), D(iamonds), C(lubs).
    public Card() {
    }

    Card(String str)
    {
        this.value=str.charAt(0);
        this.suit=str.charAt(1);
    }

    @Override
    public String toString() {
        return this.value + "" + this.suit;
    }

    //Выдача числового эквивалента карты
    public int getValueToInt() {

        if (Character.isDigit(this.value))
            return (int)(this.value-'0');
        else
            switch (value) {
                case  ('T'):
                    return 10;
                case ('J'):
                    return 11;
                case ('Q'):
                    return 12;
                case ('K'):
                    return 13;
                case ('A'):
                    return 14;
                default:
                    return 0;
            }
    }

    //Выдача числового эквивалента масти
    public int getSuitToInt() {

            switch (this.suit) {
                case  ('S'):
                    return 1;
                case ('H'):
                    return 2;
                case ('D'):
                    return 3;
                case ('C'):
                    return 4;
                default:
                    return 0;
            }
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public char getSuit() {
        return suit;
    }

    public void setSuit(char suit) {
        this.suit = suit;
    }

    //Для сравнения (сортировки)
    public int compareTo(Card o)
    {
        return (15*this.getValueToInt()+this.getSuitToInt()) - (15*o.getValueToInt() + o.getSuitToInt()) ;
    }


}


