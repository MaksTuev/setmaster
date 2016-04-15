package com.two_man.setmaster.module.storage;

import com.two_man.setmaster.entity.Profile;
import com.two_man.setmaster.module.storage.db.dao.ProfileDao;
import com.two_man.setmaster.module.storage.db.entity.ProfileObj;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import timber.log.Timber;

/**
 *
 */
public class ProfileStorage {
    private ProfileDao profileDao;

    public ProfileStorage(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public void add(Profile profile) {
        profileDao.saveAsync(new ProfileObj(profile))
            .subscribe(
                    profileObj -> {/*nothing*/},
                    this::handleUnexpectedError);
    }

    private void handleUnexpectedError(Throwable throwable) {
        Timber.e(throwable, "db error");
    }

    public void remove(Profile profile) {
        profileDao.deleteAsync(profile.getId())
                .subscribe(
                        profileObj -> {/*nothing*/},
                        this::handleUnexpectedError);
    }

    public void update(Profile newProfile) {
        profileDao.saveAsync(new ProfileObj(newProfile))
                .subscribe(
                        profileObj -> {/*nothing*/},
                        this::handleUnexpectedError);
    }

    public Observable<ArrayList<Profile>> getAllProfiles() {
        return profileDao.getAllAsync()
                .map(this::transformProfileObjList);

    }

    private ArrayList<Profile> transformProfileObjList(List<ProfileObj> list) {
        ArrayList<Profile> profiles = new ArrayList<>();
        for(ProfileObj profileObj : list){
            profiles.add(profileObj.getProfile());
        }
        return profiles;
    }
}