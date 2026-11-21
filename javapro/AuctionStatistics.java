public class AuctionStatistics {

    public void showStatistics(AuctionService service) {

        int sold = 0;
        int unsold = 0;
        double totalAmount = 0;
        double highestPrice = 0;

        for (AuctionResult result : service.getResults()) {

            if (result.isSold()) {
                sold++;
                totalAmount += result.getSoldPrice();

                if (result.getSoldPrice() > highestPrice) {
                    highestPrice = result.getSoldPrice();
                }
            } else {
                unsold++;
            }
        }

        System.out.println("\n===== AUCTION STATISTICS =====");
        System.out.println("Total Players: " +
                service.getPlayers().size());
        System.out.println("Sold Players: " + sold);
        System.out.println("Unsold Players: " + unsold);
        System.out.println("Total Auction Spending: " + totalAmount);
        System.out.println("Highest Sold Price: " + highestPrice);
    }
}