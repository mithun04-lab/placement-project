import java.util.ArrayList;

public class Auction {

    private Player player;
    private ArrayList<Bid> bids;
    private double highestBid;
    private Team highestBidder;
    private boolean closed;

    public Auction(Player player) {
        this.player = player;
        bids = new ArrayList<>();
        highestBid = player.getBasePrice();
        highestBidder = null;
        closed = false;
    }

    public Player getPlayer() {
        return player;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public Team getHighestBidder() {
        return highestBidder;
    }

    public boolean isClosed() {
        return closed;
    }

    public ArrayList<Bid> getBids() {
        return bids;
    }

    public boolean placeBid(Team team, double amount) {

        if (closed || team == null) {
            System.out.println("Invalid auction or team!");
            return false;
        }

        if (amount < highestBid + 10) {
            System.out.println("Minimum increment is 10!");
            return false;
        }

        if (!team.canAfford(amount)) {
            System.out.println("Insufficient purse!");
            return false;
        }

        if (team.getSquad().size() >= 25) {
            System.out.println("Team squad is full!");
            return false;
        }

        if (highestBidder == team) {
            System.out.println("Team already has the highest bid!");
            return false;
        }

        bids.add(new Bid(team, amount));
        highestBid = amount;
        highestBidder = team;

        System.out.println("Bid accepted!");
        System.out.println("Highest Bid: " + highestBid);
        System.out.println("Highest Bidder: " + team.getName());

        return true;
    }

    public AuctionResult closeAuction() {

        if (closed) {
            System.out.println("Auction already closed!");
            return null;
        }

        closed = true;

        if (highestBidder != null) {

            boolean success =
                    highestBidder.buyPlayer(player, highestBid);

            if (success) {
                System.out.println("\nPLAYER SOLD!");
                return new AuctionResult(
                        player, highestBidder, highestBid, true);
            }

            System.out.println("Player could not be sold!");
        }

        System.out.println("\nPLAYER UNSOLD: " + player.getName());

        return new AuctionResult(player, null, 0, false);
    }

    public void displayBids() {

        System.out.println("\n===== BID HISTORY =====");

        if (bids.isEmpty()) {
            System.out.println("No bids available.");
        }

        for (Bid bid : bids) {
            bid.display();
        }
    }
}