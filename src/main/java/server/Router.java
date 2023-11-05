package server;

import controllers.RankingEntityController;

public class Router {
  public static void init() {
    Server.app().post("/calculateImpactRanking", new RankingEntityController());
  }
}
