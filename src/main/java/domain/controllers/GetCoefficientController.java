package domain.controllers;

import domain.entities.Coefficient;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiContent;
import io.javalin.openapi.OpenApiResponse;
import org.jetbrains.annotations.NotNull;

public class GetCoefficientController implements Handler {
  private final Coefficient coefficient;

  public GetCoefficientController(Coefficient coefficient) {
    this.coefficient = coefficient;
  }

  @OpenApi(
      path = "/coefficient",
      methods = HttpMethod.GET,
      summary = "Obtain the coefficient current value",
      tags = {"Coefficient"},
      responses = {
          @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Coefficient.class)})
      }
  )
  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    ctx.json(this.coefficient);
  }
}