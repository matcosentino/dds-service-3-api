package domain.entities;

public class Coefficient {
  private Double value;
  public Coefficient() {}

  public Coefficient(Double value) {
    this.value = value;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }
}