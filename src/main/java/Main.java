import domain.controllers.GetCoefficientController;
import domain.controllers.RankingEntityController;
import domain.controllers.SetCoefficientController;
import domain.entities.Coefficient;
import io.javalin.Javalin;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.OpenApiPluginConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;

public class Main {
  public static void main(String[] args) {
    var app = Javalin.create(config -> {
      config.plugins.register(new OpenApiPlugin(
          new OpenApiPluginConfiguration()
              .withDefinitionConfiguration((version, definition) -> definition
                  .withOpenApiInfo((openApiInfo) -> {
                    openApiInfo.setTitle("Service 3 API");
                    openApiInfo.setVersion("1.0.0");
                    openApiInfo.setDescription("Incident Impact Ranking Calculator");
                  })
              )
      ));
      SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration();
      config.plugins.register(new SwaggerPlugin(swaggerConfiguration));
    }).start(8080);

    Coefficient coefficient = new Coefficient(1.0);

    app.get("/coefficient", new GetCoefficientController(coefficient));
    app.put("/coefficient/{newValue}", new SetCoefficientController(coefficient));
    app.post("/calculateImpactRanking", new RankingEntityController(coefficient));
  }
}