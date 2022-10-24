package Main;

public class Property extends Square
{
    private String name;
    private Player owner;
    private int price;
    private int tax;

    public Property(String name, int price, int tax) {
        this.name = name;
        this.owner = null;
        this.price = price;
        this.tax = tax;
    }

    public void buyProperty(Player player)
    {
        if (this.owner != null) {
            player.removeCash(this.price);
            this.owner = player;
        }
    }

    public void taxPlayer(Player player)
    {
        if (this.owner == null) {
            player.removeCash(this.tax);
            this.owner.addCash(this.tax);
        }
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int payout) {
        this.tax = payout;
    }
}
