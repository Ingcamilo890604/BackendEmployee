package dev.camilo.service;

import dev.camilo.classes.OfficeRecord;
import dev.camilo.general.exceptions.OfficeNotFoundException;
import dev.camilo.models.Office;
import dev.camilo.repository.OfficeRepository;


import java.util.Collection;

public class OfficeService {

    private final OfficeRepository officeRepository;

    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public void createOffice(OfficeRecord officeRecord) {
        officeRepository.createOffice(officeRecord);
    }

    public Office getOfficeById(int id) {
        return officeRepository.findById(id)
                .orElseThrow(() -> new OfficeNotFoundException("No se encontro una oficina con el id: "+ id));
    }

    public int employeeQuantity(int id) {
        return officeRepository.findById(id).map(Office::employeeQuantity)
                .orElseThrow(() -> new OfficeNotFoundException("No se encontraron empleados asociados a esta oficina"));
    }

    public Collection<Office> getAllOffices() {
        return officeRepository.getOffices();
    }
}
