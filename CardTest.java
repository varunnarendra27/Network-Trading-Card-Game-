import java.util.HashSet;
import java.util.TreeSet;
class CardTest //tests whether adding the cards to a HashSet leaves out multiple objects representing the same card if they only differ in price.
//Add the cards to a TreeSet and check that if you print out all elements of that set, they are ordered by rank and name.
{
    public static void main(String arg[])
    {
        Card test1 = new Card(1, "test 1", Rank.UNIQUE);
        Card test2 = new Card(1, "test 1", Rank.UNIQUE);
        HashSet<Card> test = new HashSet<Card>();
        //TreeSet tree_test = new TreeSet(Comparator Card.compareTo);
        test.add(test1);
        test.add(test2);
        //tree_test.add(test1);
        //tree_test.add(test2);
        //System.out.println(tree_test);
        System.out.println(test);
    }

}
