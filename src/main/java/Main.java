import domain.controllers.RankingEntityController;
import io.javalin.Javalin;
import io.javalin.openapi.OpenApiContact;
import io.javalin.openapi.OpenApiInfo;
import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;


public class Main {
  public static void main(String[] args) {
    var app = Javalin.create(config -> {
      OpenApiContact openApiContact = new OpenApiContact();
      openApiContact.setName("API Support");
      openApiContact.setEmail("2023-tpa-mi-no-grupo-15@frba.utn.edu.ar");

      OpenApiInfo openApiInfo = new OpenApiInfo();
      openApiInfo.setTitle("Service 3 API");
      openApiInfo.setSummary("App summary");
      openApiInfo.setDescription("Incident Impact Ranking Calculator");
      openApiInfo.setContact(openApiContact);
      openApiInfo.setVersion("1.0.0");

      OpenApiConfiguration openApiConfiguration = new OpenApiConfiguration();
      openApiConfiguration.setInfo(openApiInfo);

      config.plugins.register(new OpenApiPlugin(openApiConfiguration));

      SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration();
      config.plugins.register(new SwaggerPlugin(swaggerConfiguration));
    }).start(8080);

    app.post("/calculateImpactRanking", new RankingEntityController());
  }
}