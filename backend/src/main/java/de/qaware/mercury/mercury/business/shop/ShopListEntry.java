package de.qaware.mercury.mercury.business.shop;

import de.qaware.mercury.mercury.storage.shop.ContactType;
import lombok.Value;

import java.util.List;

@Value
public class ShopListEntry {
    Shop.Id id;
    String name;
    double distance;
    List<ContactType> contactTypes;
}
