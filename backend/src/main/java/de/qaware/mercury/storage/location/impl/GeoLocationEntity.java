package de.qaware.mercury.storage.location.impl;

import de.qaware.mercury.business.location.GeoLocation;
import de.qaware.mercury.business.location.LocationSuggestion;
import lombok.*;

import javax.persistence.*;

@Entity
@IdClass(GeoLocationId.class) // Get hibernate to use compound id
@Getter
// See https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "geolocation")
class GeoLocationEntity {
    @Id
    private String countryCode;

    @Id
    private String zipCode;

    @Id
    private String placeName;

    @Setter
    @Column(nullable = false)
    private double latitude;

    @Setter
    @Column(nullable = false)
    private double longitude;

    @Setter
    @Column(nullable = false)
    private int accuracy;

    public GeoLocation toGeoLocation() {
        return GeoLocation.of(latitude, longitude);
    }

    public LocationSuggestion toLocationSuggestion() {
        return new LocationSuggestion(countryCode, zipCode, placeName);
    }
}
