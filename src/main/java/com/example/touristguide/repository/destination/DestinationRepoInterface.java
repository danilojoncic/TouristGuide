package com.example.touristguide.repository.destination;

import com.example.touristguide.domain.Destination;
import com.example.touristguide.dto.CreateDestinationDTO;

import java.util.List;

public interface DestinationRepoInterface {
    void addDestination(CreateDestinationDTO createDestinationDTO);
    void editDestination(CreateDestinationDTO createDestinationDTO,int destination_id);
    void deleteDestination(int destination_id);
    Destination getDestination(int destination_id);
    List<Destination> getAllDestinations();
}
