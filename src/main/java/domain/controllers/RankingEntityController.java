package domain.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.entities.Coefficient;
import domain.entities.Entity;
import domain.entities.EntityResponse;
import domain.entities.ErrorResponse;
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
  private final Coefficient coefficient;
  private List<Entity> entitiesInput;
  private List<EntityResponse> entitiesResponse;

  public RankingEntityController(Coefficient coefficient) {
    this.coefficient = coefficient;
    this.entitiesInput = new ArrayList<>();
    this.entitiesResponse = new ArrayList<>();
  }

  @OpenApi(
      path = "/calculateImpactRanking",
      methods = HttpMethod.POST,
      summary = "Obtain entities sorted by impact level from highest to lowest",
      tags = {"Entity"},
      requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Entity[].class)}),
      responses = {
          @OpenApiResponse(status = "200", content = {@OpenApiContent(from = EntityResponse[].class)}),
          @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
      }
  )
  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      this.entitiesInput = objectMapper.readValue(ctx.body(), new TypeReference<List<Entity>>() {});
      this.entitiesResponse = this.createSortedList();
      ctx.json(entitiesResponse);
    } catch (Exception e) {
      ctx.status(400);
      ctx.json(new ErrorResponse("Error processing JSON"));
    }
  }

  private List<EntityResponse> createSortedList() {
    List<EntityResponse> sortedList = new ArrayList<>();

    for (Entity entityInput : this.entitiesInput) {
      sortedList.add(new EntityResponse(entityInput.getName(), entityInput.calculateImpactLevel(this.coefficient)));
    }
    sortedList.sort((entity1, entity2) -> Double.compare(entity2.getImpactLevel(), entity1.getImpactLevel()));
    return sortedList;
  }
}