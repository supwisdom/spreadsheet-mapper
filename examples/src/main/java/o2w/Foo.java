package o2w;

/**
 * Created by qianjia on 2017/3/13.
 */
public class Foo {

  private String name;

  private int age;

  private Boolean passed;

  private Bar bar = new Bar();

  private Bar bar2 = new Bar();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Bar getBar() {
    return bar;
  }

  public void setBar(Bar bar) {
    this.bar = bar;
  }

  public void setPassed(Boolean passed) {
    this.passed = passed;
  }

  public void setBar2(Bar bar2) {
    this.bar2 = bar2;
  }

  public Boolean getPassed() {
    return passed;
  }

  public Bar getBar2() {
    return bar2;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Foo{");
    sb.append("name='").append(name).append('\'');
    sb.append(", age=").append(age);
    sb.append(", passed=").append(passed);
    sb.append(", bar=").append(bar);
    sb.append(", bar2=").append(bar2);
    sb.append('}');
    return sb.toString();
  }

}

