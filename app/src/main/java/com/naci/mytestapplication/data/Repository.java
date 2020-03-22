package com.naci.mytestapplication.data;

import com.naci.mytestapplication.data.local.LocalDataSource;
import com.naci.mytestapplication.data.remote.RemoteDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Repository {

    //TODO : update repo via the rules of single source of truth
    //TODO: entegrate offline first

    private final LocalDataSource userLocalDataSource;
    private final RemoteDataSource userRemoteDataSource;

    @Inject
    public Repository(LocalDataSource userLocalDataSource, RemoteDataSource userRemoteDataSource) {
        this.userLocalDataSource = userLocalDataSource;
        this.userRemoteDataSource = userRemoteDataSource;
    }

    public LocalDataSource getUserLocalDataSource() {
        return userLocalDataSource;
    }

    public RemoteDataSource getUserRemoteDataSource() {
        return userRemoteDataSource;
    }
}
