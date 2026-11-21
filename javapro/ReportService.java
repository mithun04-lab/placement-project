public class ReportService {

    public void showTeamReport(Team team) {

        System.out.println("\n===== TEAM REPORT =====");

        team.display();

        double spending = 0;

        for (Player player : team.getSquad()) {
            spending += player.getBasePrice();
        }

        System.out.println("Players Purchased: "
                + team.getSquad().size());
    }

    public void showAllReports(AuctionService service) {

        System.out.println("\n===== IPL TEAM REPORTS =====");

        for (Team team : service.getTeams()) {
            showTeamReport(team);
        }
    }
}