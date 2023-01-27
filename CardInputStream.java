import java.util.*;
import java.io.*;
import java.net.*;
class CardInputStream extends InputStream
{

    BufferedReader reader;
    public CardInputStream(InputStream input)
    {
        this.reader = new BufferedReader(new InputStreamReader(input));
    }
    @Override
    public int read()
    {return 1;}
    
    @Override
    public void close()
    {
        try 
        {
            this.reader.close();
        }
        catch(IOException e)
        {
        }
    }
    
    Card readCard()
    {
        int counter = 0; //counter to
        String card = "card";
        long id;
        String name;
        String rank;
        long price;
        String temp;
        try{
            temp = reader.readLine();
            
            if(temp.equals("OK"))
            {
                return null;
            }
            if(temp.equals("CARD")) //if server send card, then I know to start process to create corresponding card
            {
                
                id = Long.parseLong(reader.readLine());
                name = reader.readLine();
                rank = reader.readLine();
                price = Long.parseLong(reader.readLine());
                return new Card(id, name, Rank.valueOf(rank));
                
            }
            
            
        }
        catch(IOException e)
        {
            
        }
        //System.out.println("test");
        return null;
    }
    
    String readResponse() //returns response from server
    {
        try 
        {
            String temp;
            while((temp = reader.readLine()) != null)
            {   
                
                return temp;
            }
        }
        catch(IOException e)
        {}
        return null;
    }
    
}
