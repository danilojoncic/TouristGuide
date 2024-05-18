package com.example.touristguide.service;

import com.example.touristguide.domain.Destination;
import com.example.touristguide.dto.CreateDestinationDTO;
import com.example.touristguide.repository.destination.DestinationRepoInterface;

import javax.inject.Inject;
import java.util.List;

public class DestinationService {
    @Inject
    DestinationRepoInterface destinationRepoInterface;

    public boolean add(CreateDestinationDTO createDestinationDTO){
        //provjeri da li destinacija sa takvim imenom vec postoji u bazi
        if(destinationRepoInterface.findByName(createDestinationDTO.getName()) == null){
            destinationRepoInterface.addDestination(createDestinationDTO);
            //ako ne postoji false u suprotnom true
            return true;
        }
        return false;
    }


    public void delete(int destination_id){
        destinationRepoInterface.deleteDestination(destination_id);
    }

    public List<Destination> getAll(){
        return destinationRepoInterface.getAllDestinations();
    }

    public Destination getOne(int destination_id){
        return destinationRepoInterface.getDestination(destination_id);
    }

    public void update(CreateDestinationDTO createDestinationDTO,int id){
        destinationRepoInterface.editDestination(createDestinationDTO,id);
    }

}
