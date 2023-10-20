package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.entities.Entity;
import models.entities.EntityResponse;
import models.entities.ErrorResponse;
import models.entities.InputDataClass;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiContent;
import io.javalin.openapi.OpenApiRequestBody;
import io.javalin.openapi.OpenApiResponse;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class RankingEntityController implements Handler {
  public RankingEntityController() {}

  @OpenApi(
      path = "/calculateImpactRanking",
      methods = HttpMethod.POST,
      summary = "Obtain entities sorted by impact level from highest to lowest",
      description = "Obtain entities sorted by impact level from highest to lowest",
      tags = {"Ranking"},
      requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = InputDataClass.class)}),
      responses = {
          @OpenApiResponse(status = "200", content = {@OpenApiContent(from = EntityResponse[].class)}),
          @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
      }
  )
  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      InputDataClass input = objectMapper.readValue(ctx.body(), InputDataClass.class);
      ctx.json(this.createSortedList(input));
    } catch (Exception e) {
      ctx.status(400);
      ctx.json(new ErrorResponse("Error processing JSON"));
    }
  }

  private List<EntityResponse> createSortedList(InputDataClass input) {
    List<EntityResponse> sortedList = new ArrayList<>();
    for (Entity entityInput : input.getEntities()) {
      sortedList.add(new EntityResponse(entityInput.getName(), entityInput.calculateImpactLevel(input.getCfn())));
    }
    sortedList.sort((entity1, entity2) -> Double.compare(entity2.getImpactLevel(), entity1.getImpactLevel()));
    return sortedList;
  }
}