package domain.entities;

import io.javalin.openapi.OpenApiExample;

public class EntityResponse {
  private String name;
  private Double impactLevel;

  public EntityResponse() {}

  public EntityResponse(String name, Double impactLevel) {
    this.name = name;
    this.impactLevel = impactLevel;
  }

  public String getName() {
    return name;
  }

  @OpenApiExample("0.1")
  public Double getImpactLevel() {
    return impactLevel;
  }
}