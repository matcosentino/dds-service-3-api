package models.entities;

import io.javalin.openapi.OpenApiExample;
import java.util.List;

public class InputDataClass {
  private double cfn;
  private List<Entity> entities;

  public InputDataClass() {}

  public InputDataClass(double cfn, List<Entity> entities) {
    this.cfn = cfn;
    this.entities = entities;
  }

  @OpenApiExample("0.1")
  public double getCfn() {
    return cfn;
  }

  public List<Entity> getEntities() {
    return entities;
  }
}
