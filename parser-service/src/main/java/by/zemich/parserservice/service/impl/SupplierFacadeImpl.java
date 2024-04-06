package by.zemich.parserservice.service.impl;

import by.zemich.parserservice.core.exeption.NotFoundUserException;
import by.zemich.parserservice.core.model.NewSupplierRequestDto;
import by.zemich.parserservice.core.model.SupplierDto;
import by.zemich.parserservice.core.model.UserDto;
import by.zemich.parserservice.dao.api.SupplierRepository;
import by.zemich.parserservice.dao.entity.SupplierEntity;
import by.zemich.parserservice.dao.entity.UserEntity;
import by.zemich.parserservice.service.api.SupplierFacade;
import by.zemich.parserservice.service.api.UserFacade;
import by.zemich.parserservice.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SupplierFacadeImpl implements SupplierFacade {

    private final SupplierService supplierService;
    private final SupplierRepository supplierRepository;
    private final UserFacade userFacade;
    private final UserService userService;

    private final ModelMapper modelMapper;

    public SupplierFacadeImpl(SupplierService supplierService, SupplierRepository supplierRepository, UserFacade userFacade, UserService userService, ModelMapper modelMapper) {
        this.supplierService = supplierService;
        this.supplierRepository = supplierRepository;
        this.userFacade = userFacade;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public SupplierDto create(SupplierDto newSupplierDto) {
        UUID userUuid = newSupplierDto.getUser().getUuid();
        if (!userFacade.existsByUuid(newSupplierDto.getUser().getUuid()))
            throw new NotFoundUserException(userUuid.toString());
        return modelMapper.map(supplierService.create(newSupplierDto), SupplierDto.class);
    }

    @Override
    public SupplierDto create(NewSupplierRequestDto newSupplierRequestDto) {

        UUID userUuid = newSupplierRequestDto.getUserUuid();
        UserDto userDto = userFacade.getByUuid(userUuid);

        SupplierDto newSupplierDto = SupplierDto.builder()
                .name(newSupplierRequestDto.getName())
                .vkId(newSupplierRequestDto.getVkId())
                .vkLink(newSupplierRequestDto.getVkLink())
                .address(newSupplierRequestDto.getAddress())
                .phone(newSupplierRequestDto.getPhone())
                .supplierType(newSupplierRequestDto.getSupplierType())
                .user(userDto)
                .build();


        return modelMapper.map(supplierService.create(newSupplierDto), SupplierDto.class);
    }

    @Override
    public List<SupplierDto> getAll() {

        return supplierService.getAll().stream()
                .map(supplierEntity -> modelMapper.map(supplierEntity, SupplierDto.class))
                .toList();
    }

    @Override
    public List<SupplierDto> getAllByUser(UserDto userDto) {
        return supplierService.getAllByUser(userDto).stream()
                .map(supplierEntity -> modelMapper.map(supplierEntity, SupplierDto.class))
                .toList();
    }

    @Override
    public SupplierDto update(SupplierDto newSupplierDto) {
        return modelMapper.map(supplierService.update(newSupplierDto), SupplierDto.class);
    }

    @Override
    public SupplierDto delete(UUID supplierUuid) {
        return modelMapper.map(supplierService.delete(supplierUuid), SupplierDto.class);
    }

    @Override
    public boolean existsByUuid(UUID supplierUuid) {
        return supplierService.existsByUuid(supplierUuid);
    }
}
