package by.zemich.parserservice.service.api;

import by.zemich.parserservice.core.exeption.NotFoundSupplierException;
import by.zemich.parserservice.core.model.NewSupplierRequestDto;
import by.zemich.parserservice.core.model.SupplierDto;
import by.zemich.parserservice.core.model.UserDto;
import by.zemich.parserservice.dao.api.SupplierRepository;
import by.zemich.parserservice.dao.entity.SupplierEntity;
import by.zemich.parserservice.dao.entity.UserEntity;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.UUID;

public interface SupplierFacade {
    public SupplierDto create(SupplierDto newSupplierDto);
    public SupplierDto create(NewSupplierRequestDto newSupplierRequestDto);
    public List<SupplierDto> getAll();
    public List<SupplierDto> getAllByUser(UserDto userDto);
    public SupplierDto update(SupplierDto newSupplierDto);
    public SupplierDto delete(UUID supplierUuid);
    public boolean existsByUuid(UUID supplierUuid);
}
