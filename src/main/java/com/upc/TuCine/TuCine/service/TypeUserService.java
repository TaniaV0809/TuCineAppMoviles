package com.upc.TuCine.TuCine.service;

import com.upc.TuCine.TuCine.dto.TypeUserDto;
import com.upc.TuCine.TuCine.dto.save.TypeUser.TypeUserSaveDto;

import java.util.List;

public interface TypeUserService {

    List<TypeUserDto> getAllTypeUsers();

    TypeUserDto createTypeUser(TypeUserSaveDto typeUserDto);


}
