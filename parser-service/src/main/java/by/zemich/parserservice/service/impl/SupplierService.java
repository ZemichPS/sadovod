package by.zemich.parserservice.service.impl;

import by.zemich.parserservice.core.exeption.NotFoundSupplierException;
import by.zemich.parserservice.core.model.SupplierDto;
import by.zemich.parserservice.core.model.UserDto;
import by.zemich.parserservice.dao.api.SupplierRepository;
import by.zemich.parserservice.dao.entity.SupplierEntity;
import by.zemich.parserservice.dao.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SupplierService {
    private final SupplierRepository supplierDao;
    private final ModelMapper modelMapper;


    public SupplierService(SupplierRepository supplierDao, ModelMapper modelMapper) {
        this.supplierDao = supplierDao;
        this.modelMapper = modelMapper;
    }


    public SupplierEntity create(SupplierDto newSupplierDto){
        SupplierEntity supplierEntity = modelMapper.map(newSupplierDto, SupplierEntity.class);
        return supplierDao.save(supplierEntity);
    }

    public List<SupplierEntity> getAll(){
        return supplierDao.findAll();
    }

    public List<SupplierEntity> getAllByUser(UserDto userDto){
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        return supplierDao.findAllByUser(userEntity);
    }

    public SupplierEntity update(SupplierDto newSupplierDto){
        SupplierEntity supplierEntity = modelMapper.map(newSupplierDto, SupplierEntity.class);
        return supplierDao.save(supplierEntity);
    }

    public SupplierEntity delete(UUID supplierUuid){
        SupplierEntity supplierEntityForDelete = supplierDao.findById(supplierUuid).orElseThrow(()-> new NotFoundSupplierException(supplierUuid.toString()));
        supplierDao.deleteById(supplierUuid);
        return supplierEntityForDelete;
    }

    public boolean existsByUuid(UUID supplierUuid){
        return supplierDao.existsById(supplierUuid);
    }


}
