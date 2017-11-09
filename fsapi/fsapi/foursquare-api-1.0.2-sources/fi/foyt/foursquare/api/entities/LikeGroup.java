package fi.foyt.foursquare.api.entities;

/**
 * Group of Like entities
 * 
 * @author Antti Leppä
 */
public class LikeGroup extends Group<Like> {

  private static final long serialVersionUID = 9117692748414735095L;

  @Override
  public Like[] getItems() {
    return items;
  }

  private Like[] items;
}
