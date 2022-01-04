
public class SpiceVissicchio 
{
    String color = "";
    double total_price = 0;
    int quantity = 0;
    double unit_price = 0;
    
    //constructor for SpiceVissicchio
    public SpiceVissicchio (String newColor, double newTotal, int newQuant)
    {
        color = newColor;
        total_price = newTotal;
        quantity = newQuant;
        //unit_price is found by dividing the total_price by the total quantity of the spices
        unit_price = total_price/quantity;
    }

    //Getters and Setters to use in main
    public String getColor ()
    {
        return color;
    }

    public double getTotal ()
    {
        return total_price;
    }

    public void setQuantity(int newQuantity)
    {
        quantity = newQuantity;
    }

    public int getQuantity ()
    {
        return quantity;
    }

    public double getUnitPrice ()
    {
        return unit_price;
    }
}
