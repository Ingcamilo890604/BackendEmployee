package dev.camilo.repository;

import dev.camilo.classes.OfficeRecord;
import dev.camilo.general.enums.EnumCitys;
import dev.camilo.models.Office;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OfficeRepository {
    private final Map<Integer, Office> officeMap = new HashMap<>();

    public OfficeRepository() {
        officeMap.put(1, new Office("Casa Imperial", EnumCitys.ZIPAQUIRA));
        officeMap.put(2, new Office("Centro", EnumCitys.BOGOTA));
    }

    public void createOffice(OfficeRecord officeRecord) {
        Office office = new Office(officeRecord.name(), officeRecord.city());
        officeMap.put(office.getId(),office);
    }

    public Optional<Office> findById(int id) {
        return Optional.ofNullable(officeMap.get(id));
    }

    public Collection<Office> getOffices() {
        return officeMap.values();
    }
}
