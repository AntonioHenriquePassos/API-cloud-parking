package com.antonio.cloudparking.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.antonio.cloudparking.controller.dto.ParkingCreateDto;
import com.antonio.cloudparking.controller.dto.ParkingDto;
import com.antonio.cloudparking.model.Parking;

@Component
public class ParkingMapper {
	
	private static final ModelMapper model_mapper = new ModelMapper();
	
	public ParkingDto toParkingDto (Parking parking) {
		return model_mapper.map(parking, ParkingDto.class);
	}
	
	public List<ParkingDto> toParkingDtoList (List<Parking> parkingList){
		return parkingList.stream().map(this::toParkingDto).collect(Collectors.toList());
		// A estrutura acima foi implementada pelo Java 8
	}

	public Parking toParking(ParkingDto dto) {
		return model_mapper.map(dto, Parking.class);
	}

	public Parking toParkingCreate(ParkingCreateDto dto) {
		return model_mapper.map(dto, Parking.class);
	}

}
