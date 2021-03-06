package de.qaware.mercury.business.reservation;

import de.qaware.mercury.business.login.LoginException;
import de.qaware.mercury.business.login.ReservationCancellationToken;
import de.qaware.mercury.business.shop.ContactType;
import de.qaware.mercury.business.shop.Shop;
import de.qaware.mercury.business.shop.ShopNotFoundException;

import java.util.List;

public interface ReservationService {
    List<Slot> listSlots(Shop shop);

    void createReservation(Shop shop, Slot.Id slotId, ContactType contactType, String contact, String name, String email);

    void cancelReservation(ReservationCancellationToken token) throws ReservationNotFoundException, LoginException, ShopNotFoundException;
}
