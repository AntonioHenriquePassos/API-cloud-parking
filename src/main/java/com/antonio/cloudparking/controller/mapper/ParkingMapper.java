package com.antonio.cloudparking.controller.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.antonio.cloudparking.controller.dto.ParkingDto;
import com.antonio.cloudparking.model.Parking;

@Component
public class ParkingMapper {
	
	private static final ModelMapper model_mapper = new ModelMapper();
	
	public ParkingDto parkingDto (Parking parking) {
		return model_mapper.map(parking, ParkingDto.class);
	}
	
	public List<ParkingDto> toParkingDtoList (List<Parking> parkingList){
		return null;
	}

}
