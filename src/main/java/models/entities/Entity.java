package models.entities;

import io.javalin.openapi.OpenApiExample;
import java.util.List;

public class Entity {
  private String name;
  private List<Double> incidentsDuration;
  private Integer incidentsNotResolved;
  private Integer membersAffected;

  public Entity() {}

  public Entity(String name, List<Double> incidentsDuration, Integer incidentsNotResolved, Integer membersAffected) {
    this.name = name;
    this.incidentsDuration = incidentsDuration;
    this.incidentsNotResolved = incidentsNotResolved;
    this.membersAffected = membersAffected;
  }

  public String getName() {
    return name;
  }

  @OpenApiExample("[0.1]")
  public List<Double> getIncidentsDuration() {
    return incidentsDuration;
  }

  public Integer getIncidentsNotResolved() {
    return incidentsNotResolved;
  }

  public Integer getMembersAffected() {
    return membersAffected;
  }

  public Double calculateImpactLevel(Double coefficient) {
    return (this.incidentsDurationTotal() + (this.incidentsNotResolved * coefficient)) * this.membersAffected;
  }

  private Double incidentsDurationTotal() {
    return incidentsDuration.stream().mapToDouble(Double::doubleValue).sum();
  }
}