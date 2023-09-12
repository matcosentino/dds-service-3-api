package domain.entities;

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

  public Double getImpactLevel() {
    return impactLevel;
  }
}