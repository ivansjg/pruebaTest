package models;

// Para meterlo en caché, debe ser serializable
import java.io.Serializable;
import java.util.List;

import play.cache.Cache;
import play.data.validation.*;

import siena.*;
import siena.embed.*;

@Table("users")
public class User extends Model implements Serializable {
  @Id(Generator.AUTO_INCREMENT)
  public Long id;

  @Required
  @MaxSize(20)
  @Column("name")
  @siena.Max(20) @NotNull
  public String name;

  @Required
  @MaxSize(200)
  @Column("password")
  @siena.Max(200) @NotNull
  public String password;

  public static Query<User> all() {
    return Model.all(User.class);
  }

  public User(String name, String password) {
    this.name = name.toUpperCase();
    this.password = password;
  }

  public User() {}

  public void create() {
    insert();
  }

  public void modify() {
    update();
  }

  public static User getById(Long id) {
    // Buscamos primero a ver si el usuario está en la caché
    User u = User.all().filter("id", id).get(); //Cache.get("user_" + id, User.class);

    return u;
  }

  public static User getByUsername(String name) {
    User u = User.all().filter("name", name).get();    
    return u;
  }

}