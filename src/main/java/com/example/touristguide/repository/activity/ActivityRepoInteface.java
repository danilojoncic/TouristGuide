package com.example.touristguide.repository.activity;

import com.example.touristguide.domain.Activity;
import com.example.touristguide.dto.CreateActivityDTO;

import java.util.List;

public interface ActivityRepoInteface {
    void addActivity(CreateActivityDTO createActivityDTO);

    Activity getActivity(int id);

    List<Activity> getAllActivities();

    void editActivity(CreateActivityDTO createActivityDTO, int id);

    void deleteActivity(int id);
}
