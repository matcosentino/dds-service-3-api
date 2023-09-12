package domain.controllers;

import domain.entities.Coefficient;
import domain.entities.ErrorResponse;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiContent;
import io.javalin.openapi.OpenApiParam;
import io.javalin.openapi.OpenApiResponse;
import org.jetbrains.annotations.NotNull;

public class SetCoefficientController implements Handler {
  private final Coefficient coefficient;

  public SetCoefficientController(Coefficient coefficient) {
    this.coefficient = coefficient;
  }

  @OpenApi(
      path = "/coefficient/{newValue}",
      methods = HttpMethod.PUT,
      summary = "Modify the coefficient value",
      tags = {"Coefficient"},
      pathParams = {
          @OpenApiParam(name = "newValue", description = "New value to be set in the coefficient", required = true, type = Double.class)
      },
      responses = {
          @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Coefficient.class)}),
          @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
      }
  )
  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    try {
      this.coefficient.setValue(Double.parseDouble(ctx.pathParam("newValue")));
      ctx.json(this.coefficient);
    } catch (NumberFormatException e) {
      ctx.status(400);
      ctx.json(new ErrorResponse("The new coefficient value must be a valid decimal number"));
    }
  }
}