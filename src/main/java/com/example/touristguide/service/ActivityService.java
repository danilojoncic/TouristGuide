package com.example.touristguide.service;

import com.example.touristguide.domain.Activity;
import com.example.touristguide.domain.Destination;
import com.example.touristguide.dto.CreateActivityDTO;
import com.example.touristguide.dto.CreateDestinationDTO;
import com.example.touristguide.repository.activity.ActivityRepoInteface;

import javax.inject.Inject;
import java.util.List;

public class ActivityService {
    @Inject
    ActivityRepoInteface activityRepoInteface;


    public void add(CreateActivityDTO createActivityDTO){
        activityRepoInteface.addActivity(createActivityDTO);
    }


    public void delete(int activity_id){
        activityRepoInteface.deleteActivity(activity_id);
    }

    public List<Activity> getAll(int page, int pageSize){
        return activityRepoInteface.getAllActivities(page,pageSize);
    }

    public Activity getOne(int activity_id){
        return activityRepoInteface.getActivity(activity_id);
    }

    public void update(CreateActivityDTO createActivityDTO,int id){
        activityRepoInteface.editActivity(createActivityDTO,id);
    }


}
