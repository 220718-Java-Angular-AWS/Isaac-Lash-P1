package com.revature.DAOS;
import java.util.List;
public interface DAOScrud<L> {

    //create part of crud funtionality
    void create(L l);

    //read part of crud functionality
    L read(int id);

    List<L> readAll();

    void update(L l);

    void delete(int id);

}
