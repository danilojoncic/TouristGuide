package com.example.touristguide;

import com.example.touristguide.filter.JWTTokenFilter;
import com.example.touristguide.repository.activity.ActivityRepo;
import com.example.touristguide.repository.activity.ActivityRepoInteface;
import com.example.touristguide.repository.article.ArticleRepo;
import com.example.touristguide.repository.article.ArticleRepoInterface;
import com.example.touristguide.repository.comment.CommentRepo;
import com.example.touristguide.repository.comment.CommentRepoInterface;
import com.example.touristguide.repository.destination.DestinationRepo;
import com.example.touristguide.repository.destination.DestinationRepoInterface;
import com.example.touristguide.repository.user.UserRepo;
import com.example.touristguide.repository.user.UserRepoInterface;
import com.example.touristguide.service.*;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class TouristGuideApplication extends ResourceConfig {

    public TouristGuideApplication() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        register(JWTTokenFilter.class);

        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(DestinationRepo.class).to(DestinationRepoInterface.class).in(Singleton.class);
                this.bindAsContract(DestinationService.class);

                this.bind(ActivityRepo.class).to(ActivityRepoInteface.class).in(Singleton.class);
                this.bindAsContract(ActivityService.class);

                this.bind(UserRepo.class).to(UserRepoInterface.class).in(Singleton.class);
                this.bindAsContract(UserService.class);

                this.bind(ArticleRepo.class).to(ArticleRepoInterface.class).in(Singleton.class);
                this.bindAsContract(ArticleService.class);

                this.bind(CommentRepo.class).to(CommentRepoInterface.class).in(Singleton.class);
                this.bindAsContract(CommentService.class);

            }
        };
        register(binder);
        packages("com/example/touristguide/resource");
    }
}