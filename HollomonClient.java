import java.util.*;
import java.io.*;
import java.net.*;
class HollomonClient
{
    String server;
    int port;
    Socket sock;
    BufferedReader reader;
    BufferedWriter writer;
    OutputStream output_stream;
    InputStream inputstream;
    
    public HollomonClient(String server, int port) //instantiates the HollomonClient class with the given server and port.
    {
        this.server = server;
        this.port = port;
    }
    
    public List<Card> login(String username, String password)
    {
        
        try 
        {
            this.sock = new Socket("netsrv.cim.rhul.ac.uk", 1812);
            this.inputstream = sock.getInputStream();
            this.output_stream = sock.getOutputStream();
            this.reader = new BufferedReader(new InputStreamReader(inputstream));
            
            this.writer = new BufferedWriter(new OutputStreamWriter(this.output_stream));
            
            writer.write(username+ "\r\n"); //input username and password for server to read
            writer.write(password+ "\r\n");
            
            writer.flush();
            String temp;
            temp = reader.readLine(); 
            if (!temp.equals("User "+username+" logged in successfully."))
            {
                return null;
            }
            ArrayList<Card> holder = new ArrayList<Card>();
            CardInputStream card_input_stream = new CardInputStream(inputstream);
            Card card_temp;
            
            while (true)
            {
                
                if((card_temp = card_input_stream.readCard()) == null)
                {
                    break;
                }
                holder.add(card_temp);
                
            }
            Collections.sort(holder);
            return holder;
        }
        catch(IOException e)
        {return null;}
    }
    public void close()
    {
        try 
        {
            this.reader.close();
            this.writer.close();
            this.sock.close();
        }
        catch(IOException e)
        {
        }
    }
    
    public long getCredits()
    {
        try
        {
            
            writer.write("CREDITS\r\n");
            writer.flush();
            return Long.parseLong(reader.readLine());
        }
        catch(IOException e)
        {}
        
        return 0;
    }
    
    public List<Card> getCards()
    {
        try
        {
            
            writer.write("CARDS\r\n");//sends request to server
            writer.flush();
            ArrayList<Card> holder = new ArrayList<Card>();
            CardInputStream card_input_stream = new CardInputStream(inputstream);
            Card card_temp;
            
            while (true)
            {
                
                if((card_temp = card_input_stream.readCard()) == null) //are there more cards to be read?
                {
                    break;
                }
                holder.add(card_temp);
                
            }
            Collections.sort(holder);
            return holder;
        }
        catch(IOException e)
        {}
        return null;
    }
    
    public List<Card> getOffers()
    {
    
        try
        {
            
            writer.write("OFFERS\r\n"); //sends request to server
            writer.flush();
            ArrayList<Card> holder = new ArrayList<Card>();
            CardInputStream card_input_stream = new CardInputStream(inputstream);
            Card card_temp;
            
            while (true)
            {
                
                if((card_temp = card_input_stream.readCard()) == null)
                {
                    break;//stops loop when there are no more cards
                }
                holder.add(card_temp); //adds the card to a list
                
            }
            Collections.sort(holder);
            return holder;
        }
        catch(IOException e)
        {}
        return null;
    }
    
    
    public boolean buyCard(Card card)
    {
        
            
            try
            {
                writer.write("BUY " + card.id + "\r\n"); //sends request to server
                writer.flush();
                String temp = reader.readLine();
                if(temp.equals("OK"))
                {
                    return true;
                    
                }
                
                
            }
            catch(IOException e)
            {System.out.println(e);}
        
        return false;
    }
    
    public boolean sellCard(Card card, long price)
    {
        try
            {
                writer.write("SELL " + card.id + " " + price + "\r\n");//sends request to server
                writer.flush();
                String temp = reader.readLine();
                if(temp.equals("OK"))
                {
                    return true;
                    
                }
                
                
            }
            catch(IOException e)
            {System.out.println(e);}
            return false;
            
    }
}
