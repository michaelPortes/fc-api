package com.example.fc_api.domains.categories.repository;

import com.example.fc_api.domains.categories.entity.CategoriesEntity;
import com.example.fc_api.domains.categories.model.CategoriesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Long> {

    @Query(
        value = "select * from categories",
            nativeQuery = true
    )

    List<CategoriesEntity> findAllCategories();


    @Query(
            value = "delete from categories where id = :#{#categoryId}"
    )
    public Collection<CategoriesEntity> deleteCategory(CategoriesModel categoryId);
}
