package domain.entities;

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

  public Double calculateImpactLevel(Coefficient coefficient) {
    return (this.incidentsDurationTotal() + (this.incidentsNotResolved * coefficient.getValue())) * this.membersAffected;
  }

  private Double incidentsDurationTotal() {
    return incidentsDuration.stream().mapToDouble(Double::doubleValue).sum();
  }

  public String getName() {
    return name;
  }

  public List<Double> getIncidentsDuration() {
    return incidentsDuration;
  }

  public Integer getIncidentsNotResolved() {
    return incidentsNotResolved;
  }

  public Integer getMembersAffected() {
    return membersAffected;
  }
}