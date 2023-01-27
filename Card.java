class Card implements Comparable<Card>
{
    long id;
    private String name;
    private String rank;
    long price;
    
    public Card(long id, String name, Rank rank) // creates a new card for given values of ID, name, and rank, and sets the price to 0.
    {
        this.id = id;
        this.name = name;
        this.rank = rank.toString();
        this.price = 0;
        
        
    }
    @Override
    public String toString() {
        return this.id + "  " + this.name + "  "+ this.rank + "  " + this.price; //return a representation of the card as a string.
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        if(obj == null)
        {
            return false;
            
        }
        if(obj instanceof Card)
        {
            Card test = (Card) obj;
            if(this.id == test.id && this.name.equals(test.name) && this.rank.equals(test.rank)) //two cards are considered equal if and only if they have the same numeric ID, name, and rank
            {
                return true;
            }
        }
        return false;
        

    }
    @Override
    public int hashCode()
    {
        return (int) (((this.id >> 13) ^ 3) / 2);
    }
    
    public int compareTo(Card card)
    {
        int curent_hash = this.hashCode();
        int card_hash = card.hashCode();
        int result_of_subtraction = curent_hash - card_hash;
        
        if(result_of_subtraction == 0)
        {// compares two cards first by rank, with unique cards coming first, then by name, and finally by their numeric ID
            result_of_subtraction = this.name.compareTo(card.name);
            if (result_of_subtraction == 0)
            {
                return this.rank.compareTo(card.rank);
            }
        }
        return result_of_subtraction;
    }
    



}
